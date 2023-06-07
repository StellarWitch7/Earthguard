package com.github.stellarwitch7.earthguard.registry;

import com.github.stellarwitch7.earthguard.util.accessor.IPlayerEntityAccessor;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class ModEvents {
	//Call this to load the events
	public static void load() {
		//Copy lycan status across deaths
		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
			((IPlayerEntityAccessor)newPlayer)
					.earthguard$setLycanStatus(((IPlayerEntityAccessor)oldPlayer)
					.earthguard$getLycanStatus());
			if (alive) {
				((IPlayerEntityAccessor)newPlayer)
						.earthguard$setLycanForm(((IPlayerEntityAccessor)oldPlayer)
								.earthguard$getLycanForm());
				((IPlayerEntityAccessor)newPlayer)
						.earthguard$setMonsterFormTime(((IPlayerEntityAccessor)oldPlayer)
								.earthguard$getMonsterFormTime());
			}
		});
	}
}
