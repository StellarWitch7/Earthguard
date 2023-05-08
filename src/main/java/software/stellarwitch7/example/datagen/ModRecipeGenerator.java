package software.stellarwitch7.example.datagen;

import software.stellarwitch7.example.ExampleDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;

import java.util.function.Consumer;

public class ModRecipeGenerator {
	public static void generate() {
		ExampleDataGenerator.fabricDataGenerator.addProvider(CraftingRecipeGenerator::new);
		ExampleDataGenerator.fabricDataGenerator.addProvider(SmeltingRecipeGenerator::new);
	}
}

class CraftingRecipeGenerator extends FabricRecipeProvider {
	public CraftingRecipeGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}
	
	@Override
	public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
	}
}

class SmeltingRecipeGenerator extends FabricRecipeProvider {
	public SmeltingRecipeGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}
	@Override
	public void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
	}
}