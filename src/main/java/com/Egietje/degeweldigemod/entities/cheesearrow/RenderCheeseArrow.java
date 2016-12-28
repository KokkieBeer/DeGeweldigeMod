package com.Egietje.degeweldigemod.entities.cheesearrow;

import com.Egietje.degeweldigemod.Reference;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCheeseArrow extends RenderArrow<EntityCheeseArrow>
{
    public static final ResourceLocation CHEESE_ARROW_TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/cheese_arrow.png");

    public RenderCheeseArrow(RenderManager manager) {
        super(manager);
    }

    protected ResourceLocation getEntityTexture(EntityCheeseArrow entity) {
        return CHEESE_ARROW_TEXTURES;
    }
}