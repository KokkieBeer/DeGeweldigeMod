package com.Egietje.degeweldigemod.proxy;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.entities.tileentities.blocks.TileEntityCheeseBoard;
import com.Egietje.degeweldigemod.entities.tileentities.blocks.TileEntityCheeseMirror;
import com.Egietje.degeweldigemod.entities.tileentities.render.RenderCheeseBoard;
import com.Egietje.degeweldigemod.entities.tileentities.render.RenderCheeseMirror;
import com.Egietje.degeweldigemod.handler.CheeseCapabilityHandler;
import com.Egietje.degeweldigemod.handler.CheeseCommonHandler;
import com.Egietje.degeweldigemod.handler.CheeseGuiHandler;
import com.Egietje.degeweldigemod.world.gen.CheeseGeneration;
import com.Egietje.degeweldigemod.world.gen.MapGenCheeseVillage;
import com.Egietje.degeweldigemod.world.gen.StructureCheeseVillagePieces;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CommonProxy {
	
	public void registerStructure() {
		MapGenStructureIO.registerStructure(MapGenCheeseVillage.Start.class, "CheeseVillage");
		StructureCheeseVillagePieces.registerVillagePieces();
	}
	
	public void registerModels() {
		
	}
	
	public void renderEntities() {
		
	}
	
	public void bindTileEntities() {
		
	}
	
	public void setTitle(String title) {
		
	}
	
	public void registerCommonEventHandler() {
		MinecraftForge.EVENT_BUS.register(new CheeseCommonHandler());
		MinecraftForge.EVENT_BUS.register(new CheeseCapabilityHandler());
	}
	
	public void registerClientEventHandler() {
		
	}
	
	public void registerWorldGen() {
		GameRegistry.registerWorldGenerator(new CheeseGeneration(), 0);
	}

	public void registerHandler(DeGeweldigeMod inst) {
		NetworkRegistry.INSTANCE.registerGuiHandler(inst, new CheeseGuiHandler());
	}
}
