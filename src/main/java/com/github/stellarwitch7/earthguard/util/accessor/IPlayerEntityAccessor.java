package com.github.stellarwitch7.earthguard.util.accessor;

import com.github.stellarwitch7.earthguard.util.LycanForm;
import net.minecraft.nbt.NbtCompound;

public interface IPlayerEntityAccessor {
	float earthguard$getBlockedDamage();
	void earthguard$decrementBlockedDamage();
	void earthguard$decrementHealth();
	void earthguard$incrementBlockedDamage();
	boolean earthguard$getLycanStatus();
	void earthguard$setLycanStatus(boolean isLycan);
	LycanForm earthguard$getLycanForm();
	boolean earthguard$setLycanForm(LycanForm newForm);
	int earthguard$getMonsterFormTime();
	void earthguard$setMonsterFormTime(int ticks);
}
