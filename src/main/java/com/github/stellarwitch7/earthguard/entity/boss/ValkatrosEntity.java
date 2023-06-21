package com.github.stellarwitch7.earthguard.entity.boss;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.entity.SeekerEntity;
import com.github.stellarwitch7.earthguard.entity.projectile.ChaosProjectile;
import com.github.stellarwitch7.earthguard.registry.ModEntities;
import com.github.stellarwitch7.earthguard.util.BossPhase;
import com.github.stellarwitch7.earthguard.util.SpecialValues;
import com.mojang.serialization.DataResult;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.concurrent.ThreadLocalRandom;

/*
Valkatros, deity of death and destruction.
Fire, explosions, raw chaos
Phase one: human-like, on the ground, short-range attacks
Phase two: exposed core, in the air, long ranged attacks
Lightning, explosive projectiles
*/

public class ValkatrosEntity extends HostileEntity implements RangedAttackMob, IAnimatable {
	private final AnimationFactory factory = new AnimationFactory(this);
	private final ServerBossBar bossBar = new ServerBossBar(this.getDisplayName(),
			BossBar.Color.RED,
			BossBar.Style.PROGRESS);
	private final double specialAttackDistance = 0.8d;
	private final double targetDistance = 20.0d;
	private final float transitionThreshold = this.getMaxHealth() / 6;
	private final int summonCooldown = SpecialValues.TICK_SECOND * 30;
	private final int seekerSpawnCount = 12;
	private final double dashDistance = 20.0d;
	private final int dashLength = (int)(SpecialValues.TICK_SECOND * 0.8);
	private final int dashCooldown = SpecialValues.TICK_SECOND * 15;
	private final double dashSpeed = 1.8d;
	private final int lightningDelay = SpecialValues.TICK_SECOND * 3;
	private final double chaosProjectileSpeed = 1.5d;
	private final MoveControl phaseOneControl;
	private final MoveControl phaseTwoControl;
	private BossPhase bossPhase;
	private boolean approachTarget = false;
	private boolean transitioning = false;
	private boolean isDashing = false;
	private int dashTimer;
	private int dashCooldownLeft;
	private LivingEntity dashTarget;
	private Vec3d dashVector;
	private boolean callingLightning = false;
	private int lightningTimer;
	private Vec3d lightningTargetPos;
	private int summonCooldownLeft;
	
	public ValkatrosEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.bossPhase = BossPhase.ONE;
		this.phaseOneControl = this.moveControl;
		this.phaseTwoControl = new FlightMoveControl(this, 10, true);
		this.experiencePoints = 100;
	}
	
	@Override
	protected EntityNavigation createNavigation(World world) {
		BirdNavigation birdNavigation = new BirdNavigation(this, world);
		birdNavigation.setCanPathThroughDoors(false);
		birdNavigation.setCanSwim(true);
		birdNavigation.setCanEnterOpenDoors(true);
		
		if (bossPhase == BossPhase.TWO) {
			return birdNavigation;
		}
		
		return super.createNavigation(world);
	}
	
	public static DefaultAttributeContainer.Builder setAttributes() {
		return HostileEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 640.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 16.0f)
				.add(EntityAttributes.GENERIC_ATTACK_SPEED, 3.5f)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7f)
				.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 4.0f)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 3.5f)
				.add(EntityAttributes.GENERIC_FLYING_SPEED, 1.0f)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0f)
				.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, SpecialValues.BIG_FLOAT);
	}
	
	@Override
	public void initGoals() {
		this.goalSelector.add(1, new ProjectileAttackGoal(this, 3.0d, 20, 16.0f));
		this.goalSelector.add(2, new FlyGoal(this, 1.5d));
		this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 16.0f));
		this.goalSelector.add(4, new LookAroundGoal(this));
		
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, false));
	}
	
	@Override
	public void attack(LivingEntity target, float pullProgress) {
		this.attackSelector(target);
	}
	
	private void attackSelector(LivingEntity target) {
		if (bossPhase == BossPhase.ONE) {
			if (dashCooldownLeft <= 0
					&& dashDistance > this.getPos().distanceTo(target.getPos())) {
				this.dashAttack(target);
				dashCooldownLeft = dashCooldown;
			} else {
				this.navigation.startMovingTo(this.getTarget(), 3.0d);
			}
		} else if (bossPhase == BossPhase.TWO) {
			if (!callingLightning) {
				this.callLightning(target);
			} else if (random.nextBoolean()) {
				this.launchChaosProjectile(target);
			} else if (summonCooldownLeft <= 0 && !transitioning) {
				this.summonSeekers();
				summonCooldownLeft = summonCooldown;
			}
		}
	}
	
	private void summonSeekers() {
		for (int i = 0; i < seekerSpawnCount * 2; i++) {
			var rand = ThreadLocalRandom.current();
			final int max = 16;
			final int min = max * -1;
			var bolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
			Vec3d vector = new Vec3d(rand.nextDouble(this.getPos().x + min,
					this.getPos().x + max + 1), rand.nextDouble(this.getPos().y + min,
					this.getPos().y + max + 1), rand.nextDouble(this.getPos().z + min,
					this.getPos().z + max + 1));
			bolt.setPosition(vector);
			world.spawnEntity(bolt);
		}
		
		for (int i = 0; i < seekerSpawnCount; i++) {
			var rand = ThreadLocalRandom.current();
			final int max = 12;
			final int min = max * -1;
			var newSeeker = new SeekerEntity(ModEntities.SEEKER, world);
			Vec3d vector = new Vec3d(rand.nextDouble(this.getPos().x + min,
					this.getPos().x + max + 1), rand.nextDouble(this.getPos().y + min,
					this.getPos().y + max + 1), rand.nextDouble(this.getPos().z + min,
					this.getPos().z + max + 1));
			newSeeker.setPosition(vector);
			world.spawnEntity(newSeeker);
		}
	}
	
	private void detonate(float strength) {
		world.createExplosion(this,
				this.getX(),
				this.getY(),
				this.getZ(),
				strength, Explosion.DestructionType.DESTROY);
	}
	
	private void dashAttack(LivingEntity target) {
		dashTarget = target;
		dashTimer = dashLength;
		dashVector = target.getPos()
				.subtract(this.getPos())
				.normalize()
				.multiply(dashSpeed);
		isDashing = true;
	}
	
	private boolean dashLogic(LivingEntity target) {
		if (dashTimer > 0) {
			dashTimer--;
		} else {
			return false;
		}
		
		if (target.getPos().subtract(this.getPos()).length() < specialAttackDistance) {
			this.tryAttack(target);
			this.setVelocity(target.getPos()
					.subtract(this.getPos())
					.normalize()
					.multiply(dashSpeed * -0.3));
			return false;
		}
		
		this.setVelocity(dashVector);
		return true;
	}
	
	private void launchChaosProjectile(LivingEntity target) {
		var newProjectile = new ChaosProjectile(ModEntities.CHAOS_PROJECTILE, world);
		Vec3d vector = this.getPos();
		
		if (target.getPos().y <= this.getPos().y) {
			vector = vector.subtract(0.0d, 3.0d, 0.0d);
		} else {
			vector = vector.add(0.0d, 3.0d, 0.0d);
		}
		
		newProjectile.setPosition(vector);
		newProjectile.setTargetMovement(target.getPos()
				.subtract(vector)
				.normalize()
				.multiply(chaosProjectileSpeed));
		world.spawnEntity(newProjectile);
	}
	
	private void callLightning(LivingEntity target) {
		/*
		Add logic here to summon an entity at the position of the target
		that will simply be a marker to let player's know where the next strike
		will be. //TODO lightning stuff
		*/
		
		lightningTargetPos = target.getPos();
		lightningTimer = lightningDelay;
		callingLightning = true;
	}
	
	private boolean lightningLogic(Vec3d targetPos) {
		if (lightningTimer > 0) {
			lightningTimer--;
		} else {
			var bolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
			bolt.setPosition(targetPos);
			world.spawnEntity(bolt);
			
			return false;
		}
		
		return true;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (bossPhase == BossPhase.TWO) {
			this.setNoGravity(true);
		}
		
		if (transitioning) {
			transitioning = transitionLogic();
		}
		
		if (isDashing) {
			isDashing = dashLogic(dashTarget);
		}
		
		if (callingLightning) {
			callingLightning = lightningLogic(lightningTargetPos);
		}
		
		if (bossBar != null && !this.world.isClient()) {
			bossBar.setPercent(this.getHealth() / this.getMaxHealth());
		}
		
		if (this.bossPhase == BossPhase.ONE && this.getHealth() < transitionThreshold) {
			startTransition();
		}
		
		dashCooldownLeft--;
		summonCooldownLeft--;
	}
	
	@Override
	public void move(MovementType movementType, Vec3d movement) {
		super.move(movementType, movement);
		this.checkBlockCollision();
	}
	
	@Override
	public boolean damage(DamageSource source, float amount) {
		var attacker = source.getAttacker();
		
		if (transitioning) {
			return false;
		}
		if (this.isInvulnerableTo(source)) {
			return false;
		}
		if (attacker instanceof ValkatrosEntity) {
			return false;
		}
		if (source.isExplosive()) {
			return false;
		}
		if (source == DamageSource.DROWN
				|| source == DamageSource.WITHER
				|| source == DamageSource.LIGHTNING_BOLT) {
			return false;
		}
		
		if (bossPhase == BossPhase.ONE && this.getHealth() - amount < transitionThreshold) {
			startTransition();
			return false;
		}
		
		return super.damage(source, amount);
	}
	
	public void startTransition() {
		transitioning = true;
		this.setBossPhase(BossPhase.TWO);
		this.addVelocity(0.0d, 2.0d, 0.0d);
	}
	
	public boolean transitionLogic() {
		this.heal(1.0f);
		
		if (this.getHealth() >= this.getMaxHealth()) {
			this.summonSeekers();
			return false;
		}
		
		return true;
	}
	
	@Override
	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.addPlayer(player);
	}
	
	@Override
	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.removePlayer(player);
	}
	
	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		
		nbt.putFloat("health", this.getHealth());
		
		DataResult<NbtElement> value =
				BossPhase.CODEC.encodeStart(NbtOps.INSTANCE,
						bossPhase);
		var element = value.resultOrPartial(EarthguardMod.LOGGER::error).orElseThrow();
		nbt.put("bossPhase", element);
	}
	
	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		var newBossPhase = BossPhase.ONE;
		
		if (nbt.contains("bossPhase")) {
			NbtElement element = nbt.get("bossPhase");
			DataResult<BossPhase> value = BossPhase.CODEC.parse(NbtOps.INSTANCE, element);
			newBossPhase = value.resultOrPartial(EarthguardMod.LOGGER::error).orElseThrow();
		}
		
		this.setBossPhase(newBossPhase);
	}
	
	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier,
									DamageSource damageSource) {
		return false;
	}
	
	@Override
	public boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source) {
		return false;
	}
	
	@Override
	public boolean canUsePortals() {
		return false;
	}
	
	public void setBossPhase(BossPhase newPhase) {
		if (newPhase == BossPhase.ONE) {
			this.moveControl = phaseOneControl;
		} else if (newPhase == BossPhase.TWO) {
			this.moveControl = phaseTwoControl;
		}
		
		bossPhase = newPhase;
		this.navigation = this.createNavigation(world);
	}
	
	private PlayState predicate(AnimationEvent event) {
		var animations = new AnimationBuilder();
		
		if (isDashing && bossPhase == BossPhase.ONE) {
			animations.addAnimation("animation.valkatros.dash");
			event.getController().setAnimation(animations);
			return PlayState.CONTINUE;
		}
		
		//Idle
		if (bossPhase == BossPhase.TWO) {
			if (transitioning) {
				animations.addAnimation("animation.valkatros.transition", true);
			} else {
				animations.addAnimation("animation.valkatros.phase_two_idle", true);
			}
		} else if (bossPhase == BossPhase.ONE) {
			animations.addAnimation("animation.valkatros.phase_one_idle", true);
		}
		
		event.getController().setAnimation(animations);
		return PlayState.CONTINUE;
	}
	
	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController(this, "controller",
				0, this::predicate));
	}
	
	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}
