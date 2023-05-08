package software.stellarwitch7.earthguard.registry;

import software.stellarwitch7.earthguard.client.entity.armour.AvrilliumArmourRenderer;
import software.stellarwitch7.earthguard.client.entity.armour.BonemailArmourRenderer;
import software.stellarwitch7.earthguard.client.entity.armour.SteelArmourRenderer;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ModRenderers {
	//Call this to load the renderers
	public static void load() {
		//Create new armour renderers here
		GeoArmorRenderer.registerArmorRenderer(new BonemailArmourRenderer(),
				ModItems.BONEMAIL_BOOTS,
				ModItems.BONEMAIL_LEGGINGS,
				ModItems.BONEMAIL_CHESTPLATE,
				ModItems.BONEMAIL_HELMET);
		GeoArmorRenderer.registerArmorRenderer(new AvrilliumArmourRenderer(),
				ModItems.AVRILLIUM_BOOTS,
				ModItems.AVRILLIUM_LEGGINGS,
				ModItems.AVRILLIUM_CHESTPLATE,
				ModItems.AVRILLIUM_HELMET);
		GeoArmorRenderer.registerArmorRenderer(new SteelArmourRenderer(),
				ModItems.STEEL_BOOTS,
				ModItems.STEEL_LEGGINGS,
				ModItems.STEEL_CHESTPLATE,
				ModItems.STEEL_HELMET);
	}
}
