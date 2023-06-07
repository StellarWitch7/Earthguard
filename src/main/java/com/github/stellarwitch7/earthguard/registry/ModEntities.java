package com.github.stellarwitch7.earthguard.registry;

import com.github.stellarwitch7.earthguard.entity.WildLycanEntity;
import com.github.stellarwitch7.earthguard.entity.boss.ValkatrosEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class ModEntities {
	public static final EntityType WILD_LYCAN =
			ModRegistry.createEntity("Wild Lycan",
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, WildLycanEntity::new)
							.dimensions(EntityDimensions.fixed(1f, 2.5f)).build(),
					WildLycanEntity.setAttributes());
	public static final EntityType VALKATROS =
			ModRegistry.createEntity("Valkatros",
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ValkatrosEntity::new)
							.dimensions(EntityDimensions.fixed(1f, 1.5f)).build(),
					ValkatrosEntity.setAttributes());
	//Call this to load the entities
	public static void load() {}
}
