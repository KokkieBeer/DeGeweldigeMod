package com.Egietje.degeweldigemod.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.init.CheeseBlocks;
import com.google.common.base.Predicates;
import com.sun.jna.StringArray;

import net.minecraft.block.BlockSand;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class WorldGenCheeseHouse2 extends WorldGenerator {

	private static final BlockStateMatcher IS_STONE = BlockStateMatcher.forBlock(CheeseBlocks.CHEESE_STONE);
	private final ResourceLocation HOUSE = new ResourceLocation(Reference.MODID + ":cheese_house");
	
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		while (worldIn.isAirBlock(position) && position.getY() > 2) {
			position = position.down();
		}
		if (!IS_STONE.apply(worldIn.getBlockState(position))) {
			return false;
		}
        Random random = worldIn.getChunkFromChunkCoords(position.getX(), position.getZ()).getRandomWithSeed(987234911L);
        Rotation[] arotation = Rotation.values();
        Rotation rotation = arotation[random.nextInt(arotation.length)];
        ChunkPos chunkpos = new ChunkPos(position);
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(chunkpos.getXStart() - 16, 0, chunkpos.getZStart() - 16, chunkpos.getXEnd() + 16, 256, chunkpos.getZEnd() + 16);
        PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setBoundingBox(structureboundingbox).setRandom(random);
        MinecraftServer minecraftserver = worldIn.getMinecraftServer();
		TemplateManager templatemanager = worldIn.getSaveHandler().getStructureTemplateManager();
        Template template = templatemanager.getTemplate(minecraftserver, HOUSE);
        template.addBlocksToWorld(worldIn, position, placementsettings, 2);
		return true;
	}

}
