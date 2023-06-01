package com.github.stellarwitch7.earthguard.client.block;

import com.github.stellarwitch7.earthguard.block.ErrodiumReactorBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class ErrodiumReactorBlockRenderer extends GeoBlockRenderer<ErrodiumReactorBlockEntity> {
	public ErrodiumReactorBlockRenderer(BlockEntityRendererFactory.Context ctx) {
		super(new ErrodiumReactorBlockModel());
	}
}
