package com.Egietje.degeweldigemod.blocks;

import com.Egietje.degeweldigemod.Reference;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class CheeseFluid extends BlockFluidClassic {
	
	public static final CheeseFluid INSTANCE = new CheeseFluid();
	
	public CheeseFluid() {
		super(CheeseFluid2.INSTANCE, Material.WATER);
		this.setRegistryName("molten_cheese");
		this.setUnlocalizedName("molten_cheese");
	}

	public static final class CheeseFluid2 extends Fluid {
		public static final String NAME = "molten_cheese";
		public static final CheeseFluid2 INSTANCE = new CheeseFluid2();

		private CheeseFluid2() {
			super(NAME, new ResourceLocation(Reference.MODID + ":blocks/water_still"),
					new ResourceLocation(Reference.MODID + ":blocks/water_flow"));
		}

		@Override
		public int getColor() {
			return 0xDD9E881E;
		}
	}
}
