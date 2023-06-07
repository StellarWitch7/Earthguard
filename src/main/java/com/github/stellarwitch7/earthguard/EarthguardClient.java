package com.github.stellarwitch7.earthguard;

import com.github.stellarwitch7.earthguard.client.ClientRegistry;
import com.github.stellarwitch7.earthguard.registry.ModMusic;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;

public class EarthguardClient implements ClientModInitializer {
	private final MinecraftClient client = MinecraftClient.getInstance();
	
	@Override
	public void onInitializeClient() {
		EarthguardMod.LOGGER.info("Client initialized");
		ClientRegistry.register();
		//client.getMusicTracker().play(ModMusic.SHIMMERTHING); //plays music
	}
}
