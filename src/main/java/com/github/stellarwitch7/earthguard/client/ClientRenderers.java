package com.github.stellarwitch7.earthguard.client;

import com.github.stellarwitch7.earthguard.client.block.ErrodiumReactorBlockRenderer;
import com.github.stellarwitch7.earthguard.client.entity.WildLycanEntityRenderer;
import com.github.stellarwitch7.earthguard.client.entity.armour.AvrilliumArmourRenderer;
import com.github.stellarwitch7.earthguard.client.entity.armour.BonemailArmourRenderer;
import com.github.stellarwitch7.earthguard.client.entity.armour.SteelArmourRenderer;
import com.github.stellarwitch7.earthguard.registry.ModBlockEntities;
import com.github.stellarwitch7.earthguard.registry.ModEntities;
import com.github.stellarwitch7.earthguard.registry.ModItems;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ClientRenderers {
	//Call this to load the renderers
	public static void load() {
		//Create new block entity renderers here
		BlockEntityRendererRegistry.register(ModBlockEntities.ERRODIUM_REACTOR_BLOCK_ENTITY_TYPE,
				ErrodiumReactorBlockRenderer::new);
		
		//Create new entity renderers here
		EntityRendererRegistry.register(ModEntities.WILD_LYCAN, WildLycanEntityRenderer::new);
		
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
