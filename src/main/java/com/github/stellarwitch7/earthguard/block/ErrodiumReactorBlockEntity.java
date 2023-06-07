package com.github.stellarwitch7.earthguard.block;

import com.github.stellarwitch7.earthguard.registry.ModBlockEntities;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ErrodiumReactorBlockEntity extends BlockEntity implements IAnimatable {
	private boolean isGenerating = false;
	private AnimationFactory factory = new AnimationFactory(this);
	public ErrodiumReactorBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.ERRODIUM_REACTOR_BLOCK_ENTITY_TYPE, pos, state);
	}
	
	private PlayState predicate(AnimationEvent event) {
		var animations = new AnimationBuilder();
		
		if (isGenerating) {
			animations.addAnimation("animation.errodium_reactor.generating", true);
			event.getController().setAnimation(animations);
			return PlayState.CONTINUE;
		} else {
			animations.addAnimation("animation.errodium_reactor.idle", true);
			event.getController().setAnimation(animations);
			return PlayState.CONTINUE;
		}
	}
	
	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController(this, "controller",
				0, this::predicate));
	}
	
//	@Override
//	public BlockRenderType getRenderType(BlockState state) {
//		return BlockRenderType.ENTITYBLOCK_ANIMATED;
//	}
	
	@Override
	public AnimationFactory getFactory() {
		return factory;
	}
}
