package com.Egietje.degeweldigemod.entities;

import java.util.Map;

import com.Egietje.degeweldigemod.entities.cheesecow.EntityCheeseCow;
import com.google.common.collect.Maps;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.passive.EntityCow;

public class CheeseSpawnPlacementRegistry extends EntitySpawnPlacementRegistry {
	private final Map < Class<?>, EntityLiving.SpawnPlacementType > ENTITY_PLACEMENTS = Maps. < Class<?>, EntityLiving.SpawnPlacementType > newHashMap();
	public CheeseSpawnPlacementRegistry() {
		ENTITY_PLACEMENTS.put(EntityCheeseCow.class, EntityLiving.SpawnPlacementType.ON_GROUND);
	}
	
}
