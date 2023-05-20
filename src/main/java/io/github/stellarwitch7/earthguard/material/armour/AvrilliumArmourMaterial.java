package io.github.stellarwitch7.earthguard.material.armour;

import io.github.stellarwitch7.earthguard.registry.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class AvrilliumArmourMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[]{6, 10, 12, 6};
	private static final int[] PROTECTION_VALUES = new int[]{4, 6, 7, 4};
	
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
		return 15;
	}
	
	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ModItems.AVRILLIUM_INGOT);
	}
	
	@Override
	public String getName() {
		return "avrillium";
	}
	
	@Override
	public float getToughness() {
		return 6.0f;
	}
	
	@Override
	public float getKnockbackResistance() {
		return 2.0f;
	}
}
