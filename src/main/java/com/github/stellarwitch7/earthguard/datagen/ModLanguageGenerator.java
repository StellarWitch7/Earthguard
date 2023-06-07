package com.github.stellarwitch7.earthguard.datagen;

import com.github.stellarwitch7.earthguard.EarthguardDataGenerator;
import com.github.stellarwitch7.earthguard.EarthguardMod;
import com.github.stellarwitch7.earthguard.registry.ModRegistry;
import com.github.stellarwitch7.earthguard.util.registrable.*;
import org.apache.commons.lang3.StringUtils;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModLanguageGenerator {
	public static void generate() {
		EarthguardDataGenerator.fabricDataGenerator.addProvider(UkEnglishLangProvider::new);
		EarthguardDataGenerator.fabricDataGenerator.addProvider(UsEnglishLangProvider::new);
	}
}

//UK english translation
class UkEnglishLangProvider extends FabricLanguageProvider {
	public UkEnglishLangProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator, "en_gb");
	}
	
	@Override
	public void generateTranslations(TranslationBuilder translationBuilder) {
		translationBuilder.add(EarthguardMod.ITEM_GROUP,
				StringUtils.capitalize(EarthguardMod.MOD_ID));
		
		//Generate block translations
		for (RegistrableBlock data : ModRegistry.publicRegistryBlocks) {
			translationBuilder.add(data.block, data.name);
		}
		
		//Generate status effect translations
		for (RegistrableStatusEffect data : ModRegistry.publicRegistryEffects) {
			translationBuilder.add(data.effect, data.name);
		}
		
		//Generate entity translations
		for (RegistrableEntity data : ModRegistry.publicRegistryEntities) {
			translationBuilder.add(data.entity, data.name);
		}
		
		//Generate item translations
		for (RegistrableItem data : ModRegistry.publicRegistryItems) {
			translationBuilder.add(data.item, data.name);
		}
		
		//Generate sound translations
		for (RegistrableSound data : ModRegistry.publicRegistrySounds) {
			translationBuilder.add("subtitles." + EarthguardMod.MOD_ID + "." + data.id, data.name);
		}
	}
}

//US english translation
class UsEnglishLangProvider extends FabricLanguageProvider {
	public UsEnglishLangProvider(FabricDataGenerator dataGenerator) {
		super(dataGenerator, "en_us");
	}
	
	@Override
	public void generateTranslations(TranslationBuilder translationBuilder) {
		translationBuilder.add(EarthguardMod.ITEM_GROUP,
				StringUtils.capitalize(EarthguardMod.MOD_ID));
		
		//Generate block translations
		for (RegistrableBlock data : ModRegistry.publicRegistryBlocks) {
			translationBuilder.add(data.block, data.name);
		}
		
		//Generate status effect translations
		for (RegistrableStatusEffect data : ModRegistry.publicRegistryEffects) {
			translationBuilder.add(data.effect, data.name);
		}
		
		//Generate entity translations
		for (RegistrableEntity data : ModRegistry.publicRegistryEntities) {
			translationBuilder.add(data.entity, data.name);
		}
		
		//Generate item translations
		for (RegistrableItem data : ModRegistry.publicRegistryItems) {
			translationBuilder.add(data.item, data.name);
		}
		
		//Generate sound translations
		for (RegistrableSound data : ModRegistry.publicRegistrySounds) {
			translationBuilder.add("subtitles." + EarthguardMod.MOD_ID + "." + data.id, data.name);
		}
	}
}