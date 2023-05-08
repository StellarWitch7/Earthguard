package software.stellarwitch7.example.datagen;

import software.stellarwitch7.example.ExampleDataGenerator;
import software.stellarwitch7.example.ExampleMod;
import software.stellarwitch7.example.registry.ModRegistry;
import software.stellarwitch7.example.registry.registrable.RegistrableBlock;
import software.stellarwitch7.example.registry.registrable.RegistrableItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModModelGenerator {
	public static void generate() {
		ExampleDataGenerator.fabricDataGenerator.addProvider(SimpleModelGenerator::new);
		ExampleDataGenerator.fabricDataGenerator.addProvider(CustomModelGenerator::new);
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
				ExampleMod.LOGGER.warn("The block model for <"
						+ ExampleMod.MOD_ID + ":" + data.id
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