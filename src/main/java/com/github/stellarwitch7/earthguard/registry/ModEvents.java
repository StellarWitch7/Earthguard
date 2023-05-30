package com.github.stellarwitch7.earthguard.registry;

import com.github.stellarwitch7.earthguard.util.IPlayerEntityAccessor;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class ModEvents {
	//Call this to load the events
	public static void load() {
		//Copy lycan status across deaths
		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) ->
				((IPlayerEntityAccessor)newPlayer)
						.earthguard$setLycanStatus(((IPlayerEntityAccessor)oldPlayer)
								.earthguard$getLycanStatus()));
	}
}
