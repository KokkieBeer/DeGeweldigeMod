package com.Egietje.degeweldigemod.world.dimension;

import com.Egietje.degeweldigemod.init.CheeseBiomes;
import com.Egietje.degeweldigemod.init.CheeseDimensions;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;
import scala.reflect.runtime.Settings;

public class WorldProviderCheese extends WorldProvider {

	@Override
	public DimensionType getDimensionType() {
		return CheeseDimensions.CHEESE_DIMENSION;
	}

	@Override
	protected void init() {
		this.biomeProvider = new BiomeProviderSingle(CheeseBiomes.CHEESE_DIMENSION_BIOME);
		this.hasNoSky = true;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderCheese(world, this.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), world.getWorldInfo().getGeneratorOptions());
	}

	@Override
	public Vec3d getCloudColor(float partialTicks) {
		return new Vec3d(0, 0, 0);
	}
	
	@Override
	public void setDimension(int dim) {
		super.setDimension(dim);
	}
	
	@Override
	public boolean isSurfaceWorld() {
		return false;
	}
	
	@Override
	public String getWelcomeMessage() {
		return "Entering Cheese";
	}
	
	@Override
	public String getDepartMessage() {
		return "Leaving Cheese";
	}
	
	@Override
	public double getMovementFactor() {
		return 0.5;
	}

	@Override
	public int getActualHeight() {
		return 256;
	}
	
	@Override
	public boolean canDoLightning(Chunk chunk) {
		return true;
	}

	@Override
	public boolean doesXZShowFog(int x, int z) {
		return true;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk) {
		return false;
	}

}
