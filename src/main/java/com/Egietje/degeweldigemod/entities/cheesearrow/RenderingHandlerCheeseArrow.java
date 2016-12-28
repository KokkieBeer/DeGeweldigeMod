package com.Egietje.degeweldigemod.entities.cheesearrow;

import com.Egietje.degeweldigemod.entities.*;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderingHandlerCheeseArrow implements IRenderFactory {
	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderCheeseArrow(manager);
	}
}
