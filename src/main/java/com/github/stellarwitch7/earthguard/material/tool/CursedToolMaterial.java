package com.github.stellarwitch7.earthguard.material.tool;

import com.github.stellarwitch7.earthguard.registry.ModItems;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CursedToolMaterial implements ToolMaterial {
	@Override
	public int getDurability() {
		return 300;
	}
	
	@Override
	public float getMiningSpeedMultiplier() {
		return 3.0f;
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
		return 50;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ModItems.ERRODIUM_WASTE);
	}
}
