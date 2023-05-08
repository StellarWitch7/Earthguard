package software.stellarwitch7.example;

import net.minecraft.item.Items;
import org.apache.commons.lang3.StringUtils;
import software.stellarwitch7.example.registry.ModRegistry;
import software.stellarwitch7.example.world.feature.ModConfiguredFeatures;
import software.stellarwitch7.example.world.gen.ModOreGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	public static final String MOD_ID = "example";
	public static final Logger LOGGER =
			LoggerFactory.getLogger(StringUtils.capitalize(MOD_ID));
	public static final ItemGroup ITEM_GROUP =
			FabricItemGroupBuilder.build(new Identifier(ExampleMod.MOD_ID,
					"main_item_group"), () -> new ItemStack(Items.EMERALD));
	
	@Override
	public void onInitialize() {
		LOGGER.info(StringUtils.capitalize(MOD_ID) + " loaded");
		ModConfiguredFeatures.register();
		ModRegistry.register();
		ModOreGeneration.generate();
	}
}