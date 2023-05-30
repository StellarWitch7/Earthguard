package com.github.stellarwitch7.earthguard.util;

import net.minecraft.nbt.NbtCompound;

public interface IPlayerEntityAccessor {
	float earthguard$getBlockedDamage();
	boolean earthguard$getLycanStatus();
	void earthguard$setLycanStatus(boolean isLycan);
}
