package com.Egietje.degeweldigemod.world.gen;

import java.util.Random;

import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.Egietje.degeweldigemod.init.CheeseDimensions;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class CheeseGeneration implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		int cheese = CheeseDimensions.CHEESE_DIMENSION_ID;
		if(cheese == 2) {
			switch (world.provider.getDimension()) {
				case -1:
					generateNether(world, random, chunkX, chunkZ);
					break;
				case 0:
					generateOverworld(world, random, chunkX, chunkZ);
					break;
				case 1:
					generateEnd(world, random, chunkX, chunkZ);
					break;
				case 2:
					generateCheese(world, random, chunkX, chunkZ);
					break;
			}	
		}
		
	}

	public void generateNether(World world, Random rand, int x, int z) {
		generateOre(CheeseBlocks.CHEESE_ORE_NETHER, world, rand, x, z, 3, 10, 35, 0, 256, Blocks.NETHERRACK);
	}

	public void generateOverworld(World world, Random rand, int x, int z) {
		generateOre(CheeseBlocks.CHEESE_ORE, world, rand, x, z, 3, 10, 47, 0, 256, Blocks.STONE);
	}

	public void generateEnd(World world, Random rand, int x, int z) {
		generateOre(CheeseBlocks.CHEESE_ORE_END, world, rand, x, z, 3, 10, 23, 0, 256, Blocks.END_STONE);
	}
	
	public void generateCheese(World world, Random rand, int x, int z) {
		generateOre(CheeseBlocks.CHEESE_DIRT, world, rand, x, z, 7, 23, 26, 0, 256, CheeseBlocks.CHEESE_STONE);
	}

	public void generateOre(Block block, World world, Random random, int chunkX, int chunkZ, int minVienSize, int maxVienSize, int chance, int minY, int maxY, Block generateIn) {
		int vienSize = minVienSize + random.nextInt(maxVienSize - minVienSize);
		int heightRange = maxY - minY;
		WorldGenMinable gen = new WorldGenMinable(block.getDefaultState(), vienSize, BlockMatcher.forBlock(generateIn));
		for(int i = 0; i < chance; i++) {
			int xRand = chunkX * 16 + random.nextInt(16);
			int yRand = random.nextInt(heightRange) + minY;
			int zRand = chunkZ * 16 + random.nextInt(16);
			BlockPos orePos = new BlockPos(xRand, yRand, zRand);
			gen.generate(world, random, orePos);
		}
	}
}
