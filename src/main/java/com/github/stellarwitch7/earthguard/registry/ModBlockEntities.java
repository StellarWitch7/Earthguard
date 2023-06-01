package com.github.stellarwitch7.earthguard.registry;

import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.block.ErrodiumReactorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
	public static BlockEntityType<ErrodiumReactorBlockEntity> ERRODIUM_REACTOR_BLOCK_ENTITY_TYPE =
			Registry.register(Registry.BLOCK_ENTITY_TYPE,
					new Identifier(EarthguardMod.MOD_ID, "errodium_reactor_block_entity_type"),
					FabricBlockEntityTypeBuilder.create(ErrodiumReactorBlockEntity::new,
							ModBlocks.ERRODIUM_REACTOR).build());
	
	//Call this to load the mod entities
	public static void load() {
	
	}
}
