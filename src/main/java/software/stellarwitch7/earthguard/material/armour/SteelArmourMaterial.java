package software.stellarwitch7.earthguard.material.armour;

import software.stellarwitch7.earthguard.registry.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class SteelArmourMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[]{5, 7, 9, 5};
	private static final int[] PROTECTION_VALUES = new int[]{3, 4, 5, 3};
	
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
		return 5;
	}
	
	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ModItems.STEEL_INGOT);
	}
	
	@Override
	public String getName() {
		return "steel";
	}
	
	@Override
	public float getToughness() {
		return 0.2f;
	}
	
	@Override
	public float getKnockbackResistance() {
		return 0.25f;
	}
}
