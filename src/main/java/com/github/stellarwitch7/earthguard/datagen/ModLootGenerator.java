package com.github.stellarwitch7.earthguard.datagen;

import com.github.stellarwitch7.earthguard.EarthguardDataGenerator;
import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.registry.ModRegistry;
import com.github.stellarwitch7.earthguard.util.registrable.RegistrableBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class ModLootGenerator {
	public static void generate() {
		EarthguardDataGenerator.fabricDataGenerator.addProvider(SimpleBlockLootGenerator::new);
		EarthguardDataGenerator.fabricDataGenerator.addProvider(CustomBlockLootGenerator::new);
		EarthguardDataGenerator.fabricDataGenerator.addProvider(EntityLootGenerator::new);
	}
}

//For simple block drops
class SimpleBlockLootGenerator extends SimpleFabricLootTableProvider {
	public SimpleBlockLootGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator, LootContextTypes.BLOCK);
	}
	
	@Override
	public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
		for (RegistrableBlock data : ModRegistry.publicRegistryBlocks) {
			if (data.hasSimpleLoot) {
				identifierBuilderBiConsumer.accept(new Identifier(EarthguardMod.MOD_ID,
								"blocks/" + data.id),
						BlockLootTableGenerator.drops(data.block));
			} else {
				EarthguardMod.LOGGER.warn("The loot table for "
						+ EarthguardMod.MOD_ID + ":" + data.id
						+ " has not been generated because hasSimpleLoot is set to false");
			}
		}
	}
}

class CustomBlockLootGenerator extends SimpleFabricLootTableProvider {
	public CustomBlockLootGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator, LootContextTypes.BLOCK);
	}
	
	@Override
	public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
	}
}

class EntityLootGenerator extends SimpleFabricLootTableProvider {
	public EntityLootGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator, LootContextTypes.ENTITY);
	}
	
	@Override
	public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {
	}
}