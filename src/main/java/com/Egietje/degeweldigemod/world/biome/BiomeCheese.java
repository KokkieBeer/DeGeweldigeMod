package com.Egietje.degeweldigemod.world.biome;

import java.util.Map;
import java.util.Random;

import com.Egietje.degeweldigemod.entities.cheesecow.EntityCheeseCow;
import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.Egietje.degeweldigemod.world.gen.WorldGenCheeseBossHouse;
import com.Egietje.degeweldigemod.world.gen.WorldGenCheeseHouse;

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

public class BiomeCheese extends Biome {
	public BiomeCheese(Biome.BiomeProperties properties) {
		super(properties);
		this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityCheeseCow.class, 8, 4, 4));
		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.extraTreeChance = 0.05F;
		this.theBiomeDecorator.flowersPerChunk = 5;
		this.theBiomeDecorator.grassPerChunk = 7;
		this.topBlock = CheeseBlocks.CHEESE_GRASS.getDefaultState();
		this.fillerBlock = CheeseBlocks.CHEESE_DIRT.getDefaultState();
	}
	
	@Override
	public int getGrassColorAtPos(BlockPos pos) {
		return 0xD6FF42;
	}
	
	@Override
	public int getFoliageColorAtPos(BlockPos pos) {
		return 0xD6FF42;
	}

	public BlockFlower.EnumFlowerType pickRandomFlower(Random rand, BlockPos pos) {
		double d0 = GRASS_COLOR_NOISE.getValue((double) pos.getX() / 200.0D, (double) pos.getZ() / 200.0D);

		if (d0 < -0.8D) {
			int j = rand.nextInt(4);

			switch (j) {
			case 0:
				return BlockFlower.EnumFlowerType.ORANGE_TULIP;
			case 1:
				return BlockFlower.EnumFlowerType.RED_TULIP;
			case 2:
				return BlockFlower.EnumFlowerType.PINK_TULIP;
			case 3:
			default:
				return BlockFlower.EnumFlowerType.WHITE_TULIP;
			}
		} else if (rand.nextInt(3) > 0) {
			int i = rand.nextInt(3);
			return i == 0 ? BlockFlower.EnumFlowerType.POPPY
					: (i == 1 ? BlockFlower.EnumFlowerType.HOUSTONIA : BlockFlower.EnumFlowerType.OXEYE_DAISY);
		} else {
			return BlockFlower.EnumFlowerType.DANDELION;
		}
	}

	public void decorate(World worldIn, Random rand, BlockPos pos) {
		double d0 = GRASS_COLOR_NOISE.getValue((double) (pos.getX() + 8) / 200.0D, (double) (pos.getZ() + 8) / 200.0D);
		final WorldGenCheeseHouse CHEESE_HOUSE_GENERATOR = new WorldGenCheeseHouse();
		final WorldGenCheeseBossHouse CHEESE_BOSS_HOUSE_GENERATOR = new WorldGenCheeseBossHouse();
		
		if (rand.nextInt(100) == 0) {
			int i = rand.nextInt(16) + 8;
            int j = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
            CHEESE_HOUSE_GENERATOR.generate(worldIn, rand, blockpos);
		} else if (rand.nextInt(999) == 0) {
			int i = rand.nextInt(16) + 8;
            int j = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
            CHEESE_BOSS_HOUSE_GENERATOR.generate(worldIn, rand, blockpos);
		}
		this.theBiomeDecorator.flowersPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = 10;
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);
		
		for (int i = 0; i < 7; ++i) {
			int j = rand.nextInt(16) + 8;
			int k = rand.nextInt(16) + 8;
			int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
			DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
		}
		super.decorate(worldIn, rand, pos);
	}

	@Override
	public void addDefaultFlowers() {
		BlockFlower red = net.minecraft.init.Blocks.RED_FLOWER;
		BlockFlower yel = net.minecraft.init.Blocks.YELLOW_FLOWER;
		addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.ORANGE_TULIP),
				3);
		addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.RED_TULIP), 3);
		addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.PINK_TULIP), 3);
		addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.WHITE_TULIP), 3);
		addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.POPPY), 20);
		addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.HOUSTONIA), 20);
		addFlower(red.getDefaultState().withProperty(red.getTypeProperty(), BlockFlower.EnumFlowerType.OXEYE_DAISY),
				20);
		addFlower(yel.getDefaultState().withProperty(yel.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 30);
	}

	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		return (WorldGenAbstractTree) (rand.nextInt(3) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
	}
}