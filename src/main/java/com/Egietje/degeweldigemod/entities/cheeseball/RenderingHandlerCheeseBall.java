package com.Egietje.degeweldigemod.entities.cheeseball;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderingHandlerCheeseBall implements IRenderFactory {
	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderCheeseBall(manager, 1.0F);
	}
}