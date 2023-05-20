package io.github.stellarwitch7.earthguard.material.tool;

import io.github.stellarwitch7.earthguard.registry.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class AvrilliumToolMaterial implements ToolMaterial {
	@Override
	public int getDurability() {
		return 500;
	}
	
	@Override
	public float getMiningSpeedMultiplier() {
		return 3.5f;
	}
	
	@Override
	public float getAttackDamage() {
		return 0;
	}
	
	@Override
	public int getMiningLevel() {
		return 2;
	}
	
	@Override
	public int getEnchantability() {
		return 15;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ModItems.AVRILLIUM_INGOT);
	}
}
