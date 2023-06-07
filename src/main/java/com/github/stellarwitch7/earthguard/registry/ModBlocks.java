package com.github.stellarwitch7.earthguard.registry;

import com.github.stellarwitch7.earthguard.block.ErrodiumReactorBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;

public class ModBlocks {
	//Create new blocks here
	//Set the hasSimpleLoot field to false if you don't want it to auto-create a simple loot table
	//Set the hasSimpleModel field to false if you don't want it to auto-create a simple six-sided model
	//Blocks between this comment and the next should have hasSimpleLoot and hasSimpleModel set to false
	public static final Block CRYSTALLINE_CORE =
			ModRegistry.createBlock("Crystalline Core", false, false,
					new Block(FabricBlockSettings.of(Material.METAL)
							.hardness(4.0f)
							.resistance(7.0f)
							.requiresTool()));
	public static final Block ERRODIUM_REACTOR =
			ModRegistry.createBlock("Errodium Reactor", false, false,
					new ErrodiumReactorBlock(FabricBlockSettings.of(Material.METAL)
							.hardness(4.0f)
							.resistance(7.0f)
							.requiresTool()
							.nonOpaque()));
	//Blocks after this comment should have only hasSimpleLoot set to true
	public static final Block ERRODIUM_ROD =
			ModRegistry.createBlock("Errodium Rod", true, false,
					new Block(FabricBlockSettings.of(Material.METAL)
							.hardness(4.0f)
							.resistance(7.0f)
							.requiresTool()));
	//Blocks after this comment should have only hasSimpleModel set to true
	public static final Block AVRILLIUM_ORE =
			ModRegistry.createBlock("Avrillium Ore", false, true,
					new Block(FabricBlockSettings.of(Material.STONE)
							.hardness(3.0f)
							.resistance(6.0f)
							.requiresTool()));
	public static final Block CRYSTALLINE_ESSENCE_BLOCK =
			ModRegistry.createBlock("Crystalline Essence Block", false, true,
					new Block(FabricBlockSettings.of(Material.AMETHYST)
							.hardness(1.0f)
							.resistance(12.0f)
							.requiresTool()));
	public static final Block LIMESTONE_BLOCK =
			ModRegistry.createBlock("Limestone Block", false, true,
					new Block(FabricBlockSettings.of(Material.STONE)
							.hardness(2.5f)
							.resistance(3.5f)
							.requiresTool()));
	//Blocks after this comment should have both hasSimpleLoot and hasSimpleModel set to true
	public static final Block AVRILLIUM_BLOCK =
			ModRegistry.createBlock("Avrillium Block", true, true,
					new Block(FabricBlockSettings.of(Material.METAL)
							.hardness(4.0f)
							.resistance(7.0f)
							.requiresTool()));
	public static final Block ERRODIUM_ORE =
			ModRegistry.createBlock("Errodium Ore", true, true,
					new Block(FabricBlockSettings.of(Material.STONE)
							.hardness(3.0f)
							.resistance(6.0f)
							.requiresTool()));
	public static final Block EMBERSTONE =
			ModRegistry.createBlock("Emberstone", true, true,
					new Block(FabricBlockSettings.of(Material.STONE)
							.hardness(3.0f)
							.resistance(6.0f)
							.requiresTool()));
	
	//Call this to load the blocks
	public static void load() {}
	
}
