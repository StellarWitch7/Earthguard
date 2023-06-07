package com.github.stellarwitch7.earthguard.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class LycanForm {
	private final String id;
	
	public LycanForm(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public static final LycanForm MONSTER = new LycanForm("monster");
	public static final LycanForm HUMAN = new LycanForm("human");
	public static final LycanForm BEAST = new LycanForm("beast");
	
	public static final Codec<LycanForm> CODEC =
			RecordCodecBuilder.create(instance
					-> instance.group(Codec.STRING.fieldOf("id").forGetter(LycanForm::getId))
					.apply(instance, LycanForm::new));
}
