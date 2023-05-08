package software.stellarwitch7.earthguard;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class EarthguardClient implements ClientModInitializer {
	private final MinecraftClient client = MinecraftClient.getInstance();
	
	@Override
	public void onInitializeClient() {
		EarthguardMod.LOGGER.info("Client initialized");
	}
}
