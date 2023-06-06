package com.github.stellarwitch7.earthguard.client.entity.armour;

import com.github.stellarwitch7.earthguard.item.armour.BonemailArmourItem;
import com.github.stellarwitch7.earthguard.util.EarthguardModel;
import com.github.stellarwitch7.earthguard.util.ModelType;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class BonemailArmourRenderer extends GeoArmorRenderer<BonemailArmourItem> {
	public BonemailArmourRenderer() {
		super(new EarthguardModel<>("bonemail_armour", ModelType.ARMOUR, true));
		
		this.headBone = "armorHead";
		this.bodyBone = "armorBody";
		this.rightArmBone = "armorRightArm";
		this.leftArmBone = "armorLeftArm";
		this.rightLegBone = "armorLeftLeg";
		this.leftLegBone = "armorRightLeg";
		this.rightBootBone = "armorLeftBoot";
		this.leftBootBone = "armorRightBoot";
	}
}
