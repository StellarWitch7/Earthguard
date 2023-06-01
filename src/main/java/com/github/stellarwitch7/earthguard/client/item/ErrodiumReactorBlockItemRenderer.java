package com.github.stellarwitch7.earthguard.client.item;

import com.github.stellarwitch7.earthguard.item.block.ErrodiumReactorBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ErrodiumReactorBlockItemRenderer extends GeoItemRenderer<ErrodiumReactorBlockItem> {
	public ErrodiumReactorBlockItemRenderer() {
		super(new ErrodiumReactorBlockItemModel());
	}
}