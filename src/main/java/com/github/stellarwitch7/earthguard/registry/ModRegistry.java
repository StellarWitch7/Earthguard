package com.github.stellarwitch7.earthguard.registry;

import com.github.stellarwitch7.earthguard.registry.registrable.RegistrableBlock;
import com.github.stellarwitch7.earthguard.registry.registrable.RegistrableItem;
import com.github.stellarwitch7.earthguard.registry.registrable.RegistrableStatusEffect;
import org.apache.commons.lang3.StringUtils;
import com.github.stellarwitch7.earthguard.EarthguardMod;
import net.minecraft.block.Block;
import net.minecraft.data.client.Model;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ModRegistry {
	private static final ArrayList<RegistrableBlock> registryBlocks =
			new ArrayList<>();
	public static final List<RegistrableBlock> publicRegistryBlocks =
			Collections.unmodifiableList(registryBlocks);
	private static final ArrayList<RegistrableStatusEffect> registryEffects =
			new ArrayList<>();
	public static final List<RegistrableStatusEffect> publicRegistryEffects =
			Collections.unmodifiableList(registryEffects);
	private static final ArrayList<RegistrableItem> registryItems =
			new ArrayList<>();
	public static final List<RegistrableItem> publicRegistryItems =
			Collections.unmodifiableList(registryItems);
	
	//Loads the other registry classes
	public static void loadRegistry() {
		EarthguardMod.LOGGER.info("Preparing registry");
		ModBlocks.load();
		ModEffects.load();
		ModItems.load();
		ModRenderers.load();
	}
	
	public static void register() {
		//Attempting to register anything without first
		//loading the registries will register nothing
		loadRegistry();
		
		//Register blocks
		EarthguardMod.LOGGER.info("Registering "
				+ StringUtils.capitalize(EarthguardMod.MOD_ID)
				+ " blocks");
		for (RegistrableBlock data : registryBlocks) {
			EarthguardMod.LOGGER.info("Registering block <"
					+ EarthguardMod.MOD_ID + ":"
					+ data.id + ">");
			Registry.register(Registry.BLOCK,
					new Identifier(EarthguardMod.MOD_ID, data.id),
					data.block);
		}
		
		//Register status effects
		EarthguardMod.LOGGER.info("Registering "
				+ StringUtils.capitalize(EarthguardMod.MOD_ID)
				+ " status effects");
		for (RegistrableStatusEffect data : registryEffects) {
			EarthguardMod.LOGGER.info("Registering effect <"
					+ EarthguardMod.MOD_ID + ":"
					+ data.id + ">");
			Registry.register(Registry.STATUS_EFFECT,
					new Identifier(EarthguardMod.MOD_ID, data.id),
					data.effect);
		}
		
		//Register items
		EarthguardMod.LOGGER.info("Registering "
				+ StringUtils.capitalize(EarthguardMod.MOD_ID)
				+ " items");
		for (RegistrableItem data : registryItems) {
			EarthguardMod.LOGGER.info("Registering item <"
					+ EarthguardMod.MOD_ID + ":"
					+ data.id + ">");
			Registry.register(Registry.ITEM,
					new Identifier(EarthguardMod.MOD_ID, data.id),
					data.item);
		}
	}
	
	//Clears the item list of any block items so that datagen functions don't create dupes
	public static void datagenCleanup() {
		EarthguardMod.LOGGER.info("Preparing registry for data generation");
		ArrayList<RegistrableItem> toBeRemoved = new ArrayList<>();
		
		for (RegistrableItem data : registryItems) {
			if (data.isBlock) {
				toBeRemoved.add(data);
			}
		}
		
		for (RegistrableItem data : toBeRemoved) {
			registryItems.remove(data);
		}
	}
	
	public static Block createBlock(String name, boolean hasSimpleLoot,
									boolean hasSimpleModel, Block block) {
		var newBlock = new RegistrableBlock();
		newBlock.name = name;
		newBlock.id = name.toLowerCase(Locale.ROOT).replace(" ", "_");
		newBlock.hasSimpleLoot = hasSimpleLoot;
		newBlock.hasSimpleModel = hasSimpleModel;
		newBlock.block = block;
		registryBlocks.add(newBlock);
		return block;
	}
	
	public static StatusEffect createEffect(String name, StatusEffect effect) {
		var newEffect = new RegistrableStatusEffect();
		newEffect.name = name;
		newEffect.id = name.toLowerCase(Locale.ROOT).replace(" ", "_");
		newEffect.effect = effect;
		registryEffects.add(newEffect);
		return effect;
	}
	
	public static Item createItem(String name, Model model, boolean isBlock, Item item) {
		var newItem = new RegistrableItem();
		newItem.name = name;
		newItem.id = name.toLowerCase(Locale.ROOT).replace(" ", "_");
		newItem.itemModel = model;
		newItem.isBlock = isBlock;
		newItem.item = item;
		registryItems.add(newItem);
		return item;
	}
}
