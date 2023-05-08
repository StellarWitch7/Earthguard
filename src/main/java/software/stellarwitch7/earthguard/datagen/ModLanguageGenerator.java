package software.stellarwitch7.earthguard.datagen;

import org.apache.commons.lang3.StringUtils;
import software.stellarwitch7.earthguard.EarthguardDataGenerator;
import software.stellarwitch7.earthguard.EarthguardMod;
import software.stellarwitch7.earthguard.registry.ModRegistry;
import software.stellarwitch7.earthguard.registry.registrable.RegistrableBlock;
import software.stellarwitch7.earthguard.registry.registrable.RegistrableItem;
import software.stellarwitch7.earthguard.registry.registrable.RegistrableStatusEffect;
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
		
		//Generate item translations
		for (RegistrableItem data : ModRegistry.publicRegistryItems) {
			translationBuilder.add(data.item, data.name);
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
		
		//Generate item translations
		for (RegistrableItem data : ModRegistry.publicRegistryItems) {
			translationBuilder.add(data.item, data.name);
		}
	}
}