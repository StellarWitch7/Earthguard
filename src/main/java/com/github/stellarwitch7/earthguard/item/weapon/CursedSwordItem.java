package com.github.stellarwitch7.earthguard.item.weapon;

import com.github.stellarwitch7.earthguard.item.ModSwordItem;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

import java.util.List;

public class CursedSwordItem extends ModSwordItem {
	private final List<StatusEffect> afflictions = List.of(StatusEffects.BLINDNESS,
			StatusEffects.GLOWING,
			StatusEffects.NAUSEA,
			StatusEffects.WEAKNESS,
			StatusEffects.SLOWNESS,
			StatusEffects.MINING_FATIGUE,
			StatusEffects.POISON,
			StatusEffects.WITHER);
	private final List<StatusEffect> undeadAfflictions = List.of(StatusEffects.BLINDNESS,
			StatusEffects.GLOWING,
			StatusEffects.NAUSEA,
			StatusEffects.WEAKNESS,
			StatusEffects.SLOWNESS,
			StatusEffects.MINING_FATIGUE,
			StatusEffects.REGENERATION,
			StatusEffects.WITHER);
	
	public CursedSwordItem(ToolMaterial toolMaterial, int attackDamage,
						   float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}
	
	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (target.isUndead()) {
			double doubleRandomNumber = Math.random() * undeadAfflictions.size();
			int randomNumber = (int)doubleRandomNumber;
			target.addStatusEffect(new StatusEffectInstance(undeadAfflictions.get(randomNumber),
					120, 1));
		} else {
			double doubleRandomNumber = Math.random() * afflictions.size();
			int randomNumber = (int)doubleRandomNumber;
			target.addStatusEffect(new StatusEffectInstance(afflictions.get(randomNumber),
					120, 1));
		}
		
		stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
		return true;
	}
	
	//Alpha, likely not functional
	@Override
	public void onItemEntityDestroyed(ItemEntity entity) {
		World world = entity.getWorld();
		world.spawnEntity(new LightningEntity(EntityType.LIGHTNING_BOLT, world));
	}
}
