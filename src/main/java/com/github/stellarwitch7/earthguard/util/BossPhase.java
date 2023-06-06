package com.github.stellarwitch7.earthguard.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class BossPhase {
	private final String id;
	
	public BossPhase(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public static final BossPhase ONE = new BossPhase("one");
	public static final BossPhase TWO = new BossPhase("two");
	
	public static final Codec<BossPhase> CODEC =
			RecordCodecBuilder.create(instance
					-> instance.group(Codec.STRING.fieldOf("id").forGetter(BossPhase::getId))
					.apply(instance, BossPhase::new));
}
