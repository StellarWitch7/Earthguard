package software.stellarwitch7.example;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class ExampleClient implements ClientModInitializer {
	private final MinecraftClient client = MinecraftClient.getInstance();
	
	@Override
	public void onInitializeClient() {
		ExampleMod.LOGGER.info("Client initialized");
	}
}
