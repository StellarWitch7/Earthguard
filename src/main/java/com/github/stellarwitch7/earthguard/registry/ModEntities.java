package com.github.stellarwitch7.earthguard.registry;

import com.github.stellarwitch7.earthguard.entity.WildLycanEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class ModEntities {
	public static final EntityType WILD_LYCAN =
			ModRegistry.createEntity("Wild Lycan",
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, WildLycanEntity::new)
							.dimensions(EntityDimensions.fixed(0.4f, 0.3f)).build(),
					WildLycanEntity.setAttributes());
	//Call this to load the entities
	public static void load() {}
}
