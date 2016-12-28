package com.Egietje.degeweldigemod.init;

import java.util.List;
import java.util.Random;

import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.world.biome.BiomeCheese;
import com.Egietje.degeweldigemod.world.biome.BiomeCheeseDimension;
import com.google.common.collect.Lists;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.terraingen.BiomeEvent;
import net.minecraftforge.event.terraingen.BiomeEvent.GetVillageBlockID;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class CheeseBiomes {
	
	public static Biome CHEESE_BIOME;
	public static Biome CHEESE_DIMENSION_BIOME;
	
	public CheeseBiomes() {
		init();
		register();
	}
	
	public static void init() {
		CHEESE_BIOME = new BiomeCheese(new BiomeProperties(TextFormatting.YELLOW + "Cheese" + TextFormatting.RESET).setWaterColor(0xD6FF42).setRainDisabled()).setRegistryName(Reference.MODID, "cheese_biome");
		CHEESE_DIMENSION_BIOME = new BiomeCheeseDimension(new BiomeProperties(TextFormatting.YELLOW + "Cheese Dimension" + TextFormatting.RESET).setWaterColor(0xD6FF42).setRainDisabled()).setRegistryName(Reference.MODID, "cheese_dimension_biome");
	}
	
	public static void register() {
		BiomeManager.addBiome(BiomeType.WARM, new BiomeEntry(CHEESE_BIOME, 3));
		GameRegistry.register(CHEESE_BIOME);
		GameRegistry.register(CHEESE_DIMENSION_BIOME);
	}
	
}
