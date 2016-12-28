package com.Egietje.degeweldigemod.entities.cheeseboss;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderingHandlerCheeseBoss implements IRenderFactory {
	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderCheeseBoss(manager, new ModelCheeseBoss(), 0.5F);
	}
}