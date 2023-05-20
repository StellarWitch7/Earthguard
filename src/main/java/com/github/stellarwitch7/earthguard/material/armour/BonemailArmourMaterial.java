package com.github.stellarwitch7.earthguard.material.armour;

import com.github.stellarwitch7.earthguard.registry.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class BonemailArmourMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[]{4, 6, 8, 4};
	private static final int[] PROTECTION_VALUES = new int[]{2, 3, 4, 2};
	
	@Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * 100;
	}
	
	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return PROTECTION_VALUES[slot.getEntitySlotId()];
	}
	
	@Override
	public int getEnchantability() {
		return 10;
	}
	
	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ModItems.BONE_FRAGMENT);
	}
	
	@Override
	public String getName() {
		return "bonemail";
	}
	
	@Override
	public float getToughness() {
		return 4.5f;
	}
	
	@Override
	public float getKnockbackResistance() {
		return 1.0f;
	}
}
