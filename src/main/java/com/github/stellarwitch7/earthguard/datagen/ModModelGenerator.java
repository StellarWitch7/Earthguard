package com.github.stellarwitch7.earthguard.datagen;

import com.github.stellarwitch7.earthguard.EarthguardDataGenerator;
import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.registry.ModRegistry;
import com.github.stellarwitch7.earthguard.util.registrable.RegistrableBlock;
import com.github.stellarwitch7.earthguard.util.registrable.RegistrableItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModModelGenerator {
	public static void generate() {
		EarthguardDataGenerator.fabricDataGenerator.addProvider(SimpleModelGenerator::new);
		EarthguardDataGenerator.fabricDataGenerator.addProvider(CustomModelGenerator::new);
	}
}

class SimpleModelGenerator extends FabricModelProvider {
	public SimpleModelGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}
	
	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		for (RegistrableBlock data : ModRegistry.publicRegistryBlocks) {
			if (data.hasSimpleModel) {
				blockStateModelGenerator.registerCubeAllModelTexturePool(data.block);
			} else {
				EarthguardMod.LOGGER.warn("The block model for <"
						+ EarthguardMod.MOD_ID + ":" + data.id
						+ "> has not been generated because hasSimpleModel is set to false");
			}
		}
	}
	
	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		for (RegistrableItem data : ModRegistry.publicRegistryItems) {
			itemModelGenerator.register(data.item, data.itemModel);
		}
	}
}

class CustomModelGenerator extends FabricModelProvider {
	public CustomModelGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}
	
	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
	
	}
	
	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
	
	}
}