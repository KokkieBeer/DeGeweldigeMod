package com.Egietje.degeweldigemod.items;

import com.Egietje.degeweldigemod.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CheeseArmor extends ItemArmor {

	public CheeseArmor(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, 0, equipmentSlotIn);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		if (slot == EntityEquipmentSlot.HEAD || slot == EntityEquipmentSlot.CHEST || slot == EntityEquipmentSlot.FEET) {
			return Reference.MODID + ":textures/models/armor/cheese_layer_1.png";
		} else if (slot == EntityEquipmentSlot.LEGS) {
			return Reference.MODID + ":textures/models/armor/cheese_layer_2.png";
		} else {
			return null;
		}
	}

}
