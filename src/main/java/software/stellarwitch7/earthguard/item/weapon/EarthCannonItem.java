package software.stellarwitch7.earthguard.item.weapon;

import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;

import java.util.function.Predicate;

public class EarthCannonItem extends RangedWeaponItem {
	public EarthCannonItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public Predicate<ItemStack> getProjectiles() {
		return null;
	}
	
	@Override
	public int getRange() {
		return 0;
	}
}
