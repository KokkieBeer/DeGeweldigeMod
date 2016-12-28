package com.Egietje.degeweldigemod.entities.cheesecow;

import com.Egietje.degeweldigemod.entities.*;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderingHandlerCheeseCow implements IRenderFactory {
	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderCheeseCow(manager, new ModelCheeseCow(), 0.5F);
	}
}
