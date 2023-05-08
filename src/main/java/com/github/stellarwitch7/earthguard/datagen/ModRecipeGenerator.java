package com.github.stellarwitch7.earthguard.datagen;

import com.github.stellarwitch7.earthguard.EarthguardDataGenerator;
import com.github.stellarwitch7.earthguard.registry.ModBlocks;
import com.github.stellarwitch7.earthguard.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeGenerator {
	public static void generate() {
		EarthguardDataGenerator.fabricDataGenerator.addProvider(CraftingRecipeGenerator::new);
		EarthguardDataGenerator.fabricDataGenerator.addProvider(SmeltingRecipeGenerator::new);
	}
}

class CraftingRecipeGenerator extends FabricRecipeProvider {
	public CraftingRecipeGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}
	
	@Override
	public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
		//Compacting
		offerReversibleCompactingRecipes(exporter,
				ModItems.CRYSTALLINE_ESSENCE_SHARD,
				ModBlocks.CRYSTALLINE_ESSENCE_BLOCK);
		offerReversibleCompactingRecipes(exporter,
				ModItems.AVRILLIUM_INGOT,
				ModBlocks.AVRILLIUM_BLOCK);
		offerReversibleCompactingRecipes(exporter,
				ModItems.LIMESTONE,
				ModBlocks.LIMESTONE_BLOCK);
		
		//Shaped
		ShapedRecipeJsonBuilder.create(ModItems.SOUL_GEM)
				.pattern("###")
				.pattern("#I#")
				.pattern("###")
				.input('I', ModItems.AVRILLIUM_INGOT)
				.input('#', ModItems.CRYSTALLINE_ESSENCE_SHARD)
				.criterion(RecipeProvider.hasItem(ModItems.AVRILLIUM_INGOT),
						RecipeProvider.conditionsFromItem(ModItems.AVRILLIUM_INGOT))
				.criterion(RecipeProvider.hasItem(ModItems.CRYSTALLINE_ESSENCE_SHARD),
						RecipeProvider.conditionsFromItem(ModItems.CRYSTALLINE_ESSENCE_SHARD))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.SOUL_GEM)));
		ShapedRecipeJsonBuilder.create(ModItems.COKE)
				.pattern("III")
				.pattern("LLL")
				.pattern("CCC")
				.input('I', Items.RAW_IRON)
				.input('L', ModItems.LIMESTONE)
				.input('C', Items.COAL)
				.criterion(RecipeProvider.hasItem(Items.RAW_IRON),
						RecipeProvider.conditionsFromItem(Items.RAW_IRON))
				.criterion(RecipeProvider.hasItem(ModItems.LIMESTONE),
						RecipeProvider.conditionsFromItem(ModItems.LIMESTONE))
				.criterion(RecipeProvider.hasItem(Items.COAL),
						RecipeProvider.conditionsFromItem(Items.COAL))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.COKE)));
		//Armour
		ShapedRecipeJsonBuilder.create(ModItems.AVRILLIUM_HELMET)
				.pattern("###")
				.pattern("#I#")
				.input('I', ModItems.STEEL_HELMET)
				.input('#', ModItems.AVRILLIUM_INGOT)
				.criterion(RecipeProvider.hasItem(ModItems.STEEL_HELMET),
						RecipeProvider.conditionsFromItem(ModItems.STEEL_HELMET))
				.criterion(RecipeProvider.hasItem(ModItems.AVRILLIUM_INGOT),
						RecipeProvider.conditionsFromItem(ModItems.AVRILLIUM_INGOT))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.AVRILLIUM_HELMET)));
		ShapedRecipeJsonBuilder.create(ModItems.AVRILLIUM_CHESTPLATE)
				.pattern("#I#")
				.pattern("###")
				.pattern("###")
				.input('I', ModItems.STEEL_CHESTPLATE)
				.input('#', ModItems.AVRILLIUM_INGOT)
				.criterion(RecipeProvider.hasItem(ModItems.STEEL_CHESTPLATE),
						RecipeProvider.conditionsFromItem(ModItems.STEEL_CHESTPLATE))
				.criterion(RecipeProvider.hasItem(ModItems.AVRILLIUM_INGOT),
						RecipeProvider.conditionsFromItem(ModItems.AVRILLIUM_INGOT))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.AVRILLIUM_CHESTPLATE)));
		ShapedRecipeJsonBuilder.create(ModItems.AVRILLIUM_LEGGINGS)
				.pattern("###")
				.pattern("#I#")
				.pattern("# #")
				.input('I', ModItems.STEEL_LEGGINGS)
				.input('#', ModItems.AVRILLIUM_INGOT)
				.criterion(RecipeProvider.hasItem(ModItems.STEEL_LEGGINGS),
						RecipeProvider.conditionsFromItem(ModItems.STEEL_LEGGINGS))
				.criterion(RecipeProvider.hasItem(ModItems.AVRILLIUM_INGOT),
						RecipeProvider.conditionsFromItem(ModItems.AVRILLIUM_INGOT))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.AVRILLIUM_LEGGINGS)));
		ShapedRecipeJsonBuilder.create(ModItems.AVRILLIUM_BOOTS)
				.pattern("# #")
				.pattern("#I#")
				.input('I', ModItems.STEEL_BOOTS)
				.input('#', ModItems.AVRILLIUM_INGOT)
				.criterion(RecipeProvider.hasItem(ModItems.STEEL_BOOTS),
						RecipeProvider.conditionsFromItem(ModItems.STEEL_BOOTS))
				.criterion(RecipeProvider.hasItem(ModItems.AVRILLIUM_INGOT),
						RecipeProvider.conditionsFromItem(ModItems.AVRILLIUM_INGOT))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.AVRILLIUM_BOOTS)));
		ShapedRecipeJsonBuilder.create(ModItems.STEEL_HELMET)
				.pattern("###")
				.pattern("# #")
				.input('#', ModItems.STEEL_INGOT)
				.criterion(RecipeProvider.hasItem(ModItems.STEEL_INGOT),
						RecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.STEEL_HELMET)));
		ShapedRecipeJsonBuilder.create(ModItems.STEEL_CHESTPLATE)
				.pattern("# #")
				.pattern("###")
				.pattern("###")
				.input('#', ModItems.STEEL_INGOT)
				.criterion(RecipeProvider.hasItem(ModItems.STEEL_INGOT),
						RecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.STEEL_CHESTPLATE)));
		ShapedRecipeJsonBuilder.create(ModItems.STEEL_LEGGINGS)
				.pattern("###")
				.pattern("# #")
				.pattern("# #")
				.input('#', ModItems.STEEL_INGOT)
				.criterion(RecipeProvider.hasItem(ModItems.STEEL_INGOT),
						RecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.STEEL_LEGGINGS)));
		ShapedRecipeJsonBuilder.create(ModItems.STEEL_BOOTS)
				.pattern("# #")
				.pattern("# #")
				.input('#', ModItems.STEEL_INGOT)
				.criterion(RecipeProvider.hasItem(ModItems.STEEL_INGOT),
						RecipeProvider.conditionsFromItem(ModItems.STEEL_INGOT))
				.offerTo(exporter, new Identifier(RecipeProvider
						.getRecipeName(ModItems.STEEL_BOOTS)));
	}
}

class SmeltingRecipeGenerator extends FabricRecipeProvider {
	public SmeltingRecipeGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}
	
	@Override
	public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
		offerSmelting(exporter, List.of(ModItems.RAW_AVRILLIUM), ModItems.AVRILLIUM_INGOT,
				3f, 300, "avrillium");
		offerBlasting(exporter, List.of(ModItems.COKE), ModItems.STEEL_INGOT,
				5f, 600, "steel");
	}
}