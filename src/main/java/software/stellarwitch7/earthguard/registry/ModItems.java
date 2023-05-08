package software.stellarwitch7.earthguard.registry;

import software.stellarwitch7.earthguard.EarthguardMod;
import software.stellarwitch7.earthguard.item.armour.AvrilliumArmourItem;
import software.stellarwitch7.earthguard.item.armour.BonemailArmourItem;
import software.stellarwitch7.earthguard.item.armour.SteelArmourItem;
import software.stellarwitch7.earthguard.item.consumable.ChargedSoulGemItem;
import software.stellarwitch7.earthguard.item.consumable.EnchantedFertilizerItem;
import software.stellarwitch7.earthguard.item.simple.ActiveErrodiumItem;
import software.stellarwitch7.earthguard.item.weapon.EarthCannonItem;
import software.stellarwitch7.earthguard.material.armour.AvrilliumArmourMaterial;
import software.stellarwitch7.earthguard.material.armour.BonemailArmourMaterial;
import software.stellarwitch7.earthguard.material.armour.SteelArmourMaterial;
import software.stellarwitch7.earthguard.material.tool.AvrilliumToolMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.client.Models;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class ModItems {
	//Create simple items here
	public static final Item SOUL_GEM =
			ModRegistry.createItem("Soul Gem", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item CRYSTALLINE_ESSENCE_SHARD =
			ModRegistry.createItem("Crystalline Essence Shard", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item SHATTERED_SKULL =
			ModRegistry.createItem("Shattered Skull", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item RAW_AVRILLIUM =
			ModRegistry.createItem("Raw Avrillium", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item AVRILLIUM_INGOT =
			ModRegistry.createItem("Avrillium Ingot", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)
							.fireproof()));
	public static final Item ERRODIUM_WASTE =
			ModRegistry.createItem("Errodium Waste", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item BONE_FRAGMENT =
			ModRegistry.createItem("Bone Fragment", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item ERRODIUM_HEART =
			ModRegistry.createItem("Errodium Heart", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item AVRILLIUM_NUGGET =
			ModRegistry.createItem("Avrillium Nugget", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item WEEPING_HEART =
			ModRegistry.createItem("Weeping Heart", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(8)));
	public static final Item STEEL_INGOT =
			ModRegistry.createItem("Steel Ingot", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item LIMESTONE =
			ModRegistry.createItem("Limestone", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final Item COKE =
			ModRegistry.createItem("Coke", Models.GENERATED, false,
					new Item(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	//Create custom items here
	public static final EnchantedFertilizerItem ENCHANTED_FERTILIZER = (EnchantedFertilizerItem)
			ModRegistry.createItem("Enchanted Fertilizer", Models.GENERATED, false,
					new EnchantedFertilizerItem(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(32)));
	public static final ChargedSoulGemItem CHARGED_SOUL_GEM = (ChargedSoulGemItem)
			ModRegistry.createItem("Charged Soul Gem", Models.GENERATED, false,
					new ChargedSoulGemItem(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(8)
							.fireproof()));
	public static final Item ACTIVE_ERRODIUM =
			ModRegistry.createItem("Active Errodium", Models.GENERATED, false,
					new ActiveErrodiumItem(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)
							.fireproof()));
	//Create block items here
	//Note: the name used here is the only one that is shown in-game
	//It MUST match the exact name that is in the ModBlocks entry
	//If hasSimpleModel is true in the ModBlocks entry, set the item model to Models.CUBE_ALL
	//All entries must have isBlock set to true
	public static final BlockItem CRYSTALLINE_ESSENCE_BLOCK = (BlockItem)
			ModRegistry.createItem("Crystalline Essence Block", Models.CUBE_ALL, true,
					new BlockItem(ModBlocks.CRYSTALLINE_ESSENCE_BLOCK, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final BlockItem AVRILLIUM_BLOCK = (BlockItem)
			ModRegistry.createItem("Avrillium Block", Models.CUBE_ALL, true,
					new BlockItem(ModBlocks.AVRILLIUM_BLOCK, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)
							.fireproof()));
	public static final BlockItem AVRILLIUM_ORE = (BlockItem)
			ModRegistry.createItem("Avrillium Ore", Models.CUBE_ALL, true,
					new BlockItem(ModBlocks.AVRILLIUM_ORE, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final BlockItem ERRODIUM_ROD = (BlockItem)
			ModRegistry.createItem("Errodium Rod", Models.CUBE_ALL, true,
					new BlockItem(ModBlocks.ERRODIUM_ROD, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final BlockItem CRYSTALLINE_CORE = (BlockItem)
			ModRegistry.createItem("Crystalline Core", Models.CUBE_ALL, true,
					new BlockItem(ModBlocks.CRYSTALLINE_CORE, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final BlockItem ERRODIUM_ORE = (BlockItem)
			ModRegistry.createItem("Errodium Ore", Models.CUBE_ALL, true,
					new BlockItem(ModBlocks.ERRODIUM_ORE, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final BlockItem ERRODIUM_REACTOR = (BlockItem)
			ModRegistry.createItem("Errodium Reactor", Models.CUBE_ALL, true,
					new BlockItem(ModBlocks.ERRODIUM_REACTOR, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final BlockItem EMBERSTONE = (BlockItem)
			ModRegistry.createItem("Emberstone", Models.CUBE_ALL, true,
					new BlockItem(ModBlocks.EMBERSTONE, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	public static final BlockItem LIMESTONE_BLOCK = (BlockItem)
			ModRegistry.createItem("Limestone Block", Models.CUBE_ALL, true,
					new BlockItem(ModBlocks.LIMESTONE_BLOCK, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)
							.maxCount(64)));
	//Create weapon items here
	public static final SwordItem AVRILLIUM_SWORD = (SwordItem)
			ModRegistry.createItem("Avrillium Sword", Models.GENERATED, false,
					new SwordItem(new AvrilliumToolMaterial(), 8, -2.9f,
							new FabricItemSettings()
									.group(EarthguardMod.ITEM_GROUP)
									.fireproof()));
	public static final RangedWeaponItem EARTH_CANNON = (RangedWeaponItem)
			ModRegistry.createItem("Earth Cannon", Models.GENERATED, false,
					new EarthCannonItem(new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	//Create armour items here
	public static final ArmorItem BONEMAIL_HELMET = (ArmorItem)
			ModRegistry.createItem("Bonemail Helmet", Models.GENERATED, false,
					new BonemailArmourItem(new BonemailArmourMaterial(),
							EquipmentSlot.HEAD, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem BONEMAIL_CHESTPLATE = (ArmorItem)
			ModRegistry.createItem("Bonemail Chestplate", Models.GENERATED, false,
					new BonemailArmourItem(new BonemailArmourMaterial(),
							EquipmentSlot.CHEST, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem BONEMAIL_LEGGINGS = (ArmorItem)
			ModRegistry.createItem("Bonemail Leggings", Models.GENERATED, false,
					new BonemailArmourItem(new BonemailArmourMaterial(),
							EquipmentSlot.LEGS, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem BONEMAIL_BOOTS = (ArmorItem)
			ModRegistry.createItem("Bonemail Boots", Models.GENERATED, false,
					new BonemailArmourItem(new BonemailArmourMaterial(),
							EquipmentSlot.FEET, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem AVRILLIUM_HELMET = (ArmorItem)
			ModRegistry.createItem("Avrillium Helmet", Models.GENERATED, false,
					new AvrilliumArmourItem(new AvrilliumArmourMaterial(),
							EquipmentSlot.HEAD, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem AVRILLIUM_CHESTPLATE = (ArmorItem)
			ModRegistry.createItem("Avrillium Chestplate", Models.GENERATED, false,
					new AvrilliumArmourItem(new AvrilliumArmourMaterial(),
							EquipmentSlot.CHEST, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem AVRILLIUM_LEGGINGS = (ArmorItem)
			ModRegistry.createItem("Avrillium Leggings", Models.GENERATED, false,
					new AvrilliumArmourItem(new AvrilliumArmourMaterial(),
							EquipmentSlot.LEGS, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem AVRILLIUM_BOOTS = (ArmorItem)
			ModRegistry.createItem("Avrillium Boots", Models.GENERATED, false,
					new AvrilliumArmourItem(new AvrilliumArmourMaterial(),
							EquipmentSlot.FEET, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem STEEL_HELMET = (ArmorItem)
			ModRegistry.createItem("Steel Helmet", Models.GENERATED, false,
					new SteelArmourItem(new SteelArmourMaterial(),
							EquipmentSlot.HEAD, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem STEEL_CHESTPLATE = (ArmorItem)
			ModRegistry.createItem("Steel Chestplate", Models.GENERATED, false,
					new SteelArmourItem(new SteelArmourMaterial(),
							EquipmentSlot.CHEST, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem STEEL_LEGGINGS = (ArmorItem)
			ModRegistry.createItem("Steel Leggings", Models.GENERATED, false,
					new SteelArmourItem(new SteelArmourMaterial(),
							EquipmentSlot.LEGS, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	public static final ArmorItem STEEL_BOOTS = (ArmorItem)
			ModRegistry.createItem("Steel Boots", Models.GENERATED, false,
					new SteelArmourItem(new SteelArmourMaterial(),
							EquipmentSlot.FEET, new FabricItemSettings()
							.group(EarthguardMod.ITEM_GROUP)));
	//Create tool items here
	public static final PickaxeItem AVRILLIUM_PICKAXE = (PickaxeItem)
			ModRegistry.createItem("Avrillium Pickaxe", Models.GENERATED, false,
					new PickaxeItem(new AvrilliumToolMaterial(), 4, -2.8f,
							new FabricItemSettings()
									.group(EarthguardMod.ITEM_GROUP)
									.fireproof()));
	public static final AxeItem AVRILLIUM_AXE = (AxeItem)
			ModRegistry.createItem("Avrillium Axe", Models.GENERATED, false,
					new AxeItem(new AvrilliumToolMaterial(), 9, -3.1f,
							new FabricItemSettings()
									.group(EarthguardMod.ITEM_GROUP)
									.fireproof()));
	public static final ShovelItem AVRILLIUM_SHOVEL = (ShovelItem)
			ModRegistry.createItem("Avrillium Shovel", Models.GENERATED, false,
					new ShovelItem(new AvrilliumToolMaterial(), 4, -3.0f,
							new FabricItemSettings()
									.group(EarthguardMod.ITEM_GROUP)
									.fireproof()));
	
	//Call this to load the items
	public static void load() {}
}