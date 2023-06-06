package com.github.stellarwitch7.earthguard.client.block;

import com.github.stellarwitch7.earthguard.block.ErrodiumReactorBlockEntity;
import com.github.stellarwitch7.earthguard.util.EarthguardModel;
import com.github.stellarwitch7.earthguard.util.ModelType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class ErrodiumReactorBlockRenderer extends GeoBlockRenderer<ErrodiumReactorBlockEntity> {
	public ErrodiumReactorBlockRenderer(BlockEntityRendererFactory.Context ctx) {
		super(new EarthguardModel<>("errodium_reactor", ModelType.BLOCK));
	}
}
