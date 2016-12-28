package com.Egietje.degeweldigemod.handler;

import com.Egietje.degeweldigemod.gui.*;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CheeseGuiHandler implements IGuiHandler {
	
	public static int COOKIEGUIID = 0;
	public static int COMPLIMENTGUIID = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == COOKIEGUIID) {
			return new CheeseCookieGui(player);
		} else if(ID == COMPLIMENTGUIID) {
			return new ComplimentMachineGui();
		} else return null;
	}

}
