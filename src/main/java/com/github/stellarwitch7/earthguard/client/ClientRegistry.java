package com.github.stellarwitch7.earthguard.client;

public class ClientRegistry {
	private static void loadRegistry() {
		ClientRenderers.load();
	}
	
	public static void register() {
		loadRegistry();
	}
}
