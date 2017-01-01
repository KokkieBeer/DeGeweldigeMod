package com.Egietje.degeweldigemod.init;

import com.Egietje.degeweldigemod.commands.FillCheese;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CheeseCommands {

	public static CommandBase FILL_CHEESE;
	

	public CheeseCommands(FMLServerStartingEvent event) {
		init();
		register(event);
	}
	
	public void init() {
		FILL_CHEESE = new FillCheese();
	}
	
	public void register(FMLServerStartingEvent event) {
		event.registerServerCommand(FILL_CHEESE);
	}
}
