package com.Egietje.degeweldigemod.world.biome;

import java.util.Map;
import java.util.Random;

import com.Egietje.degeweldigemod.entities.cheeseboss.EntityCheeseBoss;
import com.Egietje.degeweldigemod.entities.cheesecow.EntityCheeseCow;
import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.Egietje.degeweldigemod.world.gen.WorldGenCheeseBossHouse;
import com.Egietje.degeweldigemod.world.gen.WorldGenCheeseHouse;
import com.Egietje.degeweldigemod.world.gen.WorldGenCheeseHouse2;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandLocate;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkProviderOverworld;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeCheeseDimension extends Biome {
	public BiomeCheeseDimension(Biome.BiomeProperties properties) {
		super(properties);
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.theBiomeDecorator.treesPerChunk = 0;
		this.theBiomeDecorator.extraTreeChance = 0;
		this.theBiomeDecorator.flowersPerChunk = 0;
		this.theBiomeDecorator.grassPerChunk = 0;
		this.topBlock = CheeseBlocks.CHEESE_STONE.getDefaultState();
		this.fillerBlock = CheeseBlocks.CHEESE_STONE.getDefaultState();
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0xD6FF42;
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0xD6FF42;
	}

	public void decorate(World worldIn, Random rand, BlockPos pos) {
		final WorldGenCheeseHouse2 CHEESE_HOUSE_GENERATOR = new WorldGenCheeseHouse2();
		
		if (rand.nextInt(100) == 0) {
			int i = rand.nextInt(16) + 8;
            int j = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
            CHEESE_HOUSE_GENERATOR.generate(worldIn, rand, blockpos);
		}
		super.decorate(worldIn, rand, pos);
	}

	@Override
	public void addDefaultFlowers() {
	}

	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return TREE_FEATURE;
	}
}