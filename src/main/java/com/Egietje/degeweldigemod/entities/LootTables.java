package com.Egietje.degeweldigemod.entities;

import java.util.Set;

import net.minecraft.world.storage.loot.LootTableList;

import com.Egietje.degeweldigemod.Reference;
import com.google.common.collect.Sets;

import net.minecraft.util.ResourceLocation;

public class LootTables extends LootTableList {
	public static final ResourceLocation ENTITIES_CHEESE_COW = register("cheese_cow");
	public static final ResourceLocation CHESTS_CHEESE = register("cheese_chest");
	
	private static ResourceLocation register(String id) {
        return register(new ResourceLocation(Reference.MODID, id));
    }
}
