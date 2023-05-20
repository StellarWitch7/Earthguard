package com.github.stellarwitch7.earthguard.mixin;

import com.github.stellarwitch7.earthguard.util.IExplosiveItem;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Debug(export = true)
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
	@Inject(method = "damage",
			at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
	private void damage(DamageSource source, float amount,
						CallbackInfoReturnable<Boolean> info) {
		//Allows items implementing IExplosiveItem to be explosion immune
		if (((ItemEntity)(Object)this).getStack().getItem() instanceof IExplosiveItem
				&& source.isExplosive()) {
			info.setReturnValue(false);
		}
	}
}
