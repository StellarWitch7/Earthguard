package com.github.stellarwitch7.earthguard;

import com.github.stellarwitch7.earthguard.registry.ModItems;
import com.github.stellarwitch7.earthguard.registry.ModRegistry;
import org.apache.commons.lang3.StringUtils;
import com.github.stellarwitch7.earthguard.world.feature.ModConfiguredFeatures;
import com.github.stellarwitch7.earthguard.world.gen.ModOreGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EarthguardMod implements ModInitializer {
	public static final String MOD_ID = "earthguard";
	public static final Logger LOGGER =
			LoggerFactory.getLogger(StringUtils.capitalize(MOD_ID));
	public static final ItemGroup ITEM_GROUP =
			FabricItemGroupBuilder.build(new Identifier(EarthguardMod.MOD_ID,
					"main_item_group"), () -> new ItemStack(ModItems.AVRILLIUM_INGOT));
	
	@Override
	public void onInitialize() {
		LOGGER.info(StringUtils.capitalize(MOD_ID) + " loaded");
		ModConfiguredFeatures.register();
		ModRegistry.register();
		ModOreGeneration.generate();
	}
}