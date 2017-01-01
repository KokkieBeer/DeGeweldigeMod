package com.Egietje.degeweldigemod.init;

import com.Egietje.degeweldigemod.DeGeweldigeMod;
import com.Egietje.degeweldigemod.Reference;
import com.Egietje.degeweldigemod.items.*;
import com.Egietje.degeweldigemod.items.tools.*;

import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CheeseItems {
	public static ToolMaterial CHEESE_TOOL_MATERIAL;
	
	public static ArmorMaterial CHEESE_ARMOR_MATERIAL;
	
	//Tools
	public static Item CHEESE_SWORD;
	public static Item CHEESE_PICKAXE;
	public static Item CHEESE_AXE;
	public static Item CHEESE_SHOVEL;
	public static Item CHEESE_HOE;
	public static Item CHEESE_FLY_STICK;
	public static Item CHEESE_BOW;
	public static Item CHEESE_MULTITOOL;
	public static Item FLINT_AND_CHEESE;
	public static Item CHEESE_SLOW_WAND;
	public static Item CHEESE_POISON_WAND;
	public static Item CHEESE_DAMAGE_WAND;
	public static Item CHEESE_FIRE_WAND;
	
	//Armor
	public static Item CHEESE_HELMET;
	public static Item CHEESE_CHESTPLATE;
	public static Item CHEESE_LEGGINGS;
	public static Item CHEESE_BOOTS;
	
	//Tool heads
	public static Item CHEESE_PICKAXE_HEAD;
	public static Item CHEESE_AXE_HEAD;
	public static Item CHEESE_SHOVEL_HEAD;
	
	//Food
	public static Item CHEESE;
	public static Item CHEESE_COOKED;
	public static Item BREAD_CHEESE;
	public static Item CHEESE_APPLE;
	public static Item CHEESE_BUCKET;
	public static Item CHEESE_SEEDS;
	
	//Other
	public static Item CHEESE_INGOT;
	public static Item CHEESE_ARROW;
	
	public CheeseItems() {
		init();
		register();
	}
	
	public static void init() {
		//Materials
		CHEESE_TOOL_MATERIAL = EnumHelper.addToolMaterial("cheese_tool_material", 2, 1265, 7.0F, 2.5F, 17);
		CHEESE_ARMOR_MATERIAL = EnumHelper.addArmorMaterial("cheese_armor_material", "", 22, new int[] {3, 6, 8, 3}, 17, SoundEvents.BLOCK_SLIME_BREAK, 3);
		
		//Armor
		CHEESE_HELMET = new CheeseArmor(CHEESE_ARMOR_MATERIAL, EntityEquipmentSlot.HEAD).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_CHESTPLATE = new CheeseArmor(CHEESE_ARMOR_MATERIAL, EntityEquipmentSlot.CHEST).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_LEGGINGS = new CheeseArmor(CHEESE_ARMOR_MATERIAL, EntityEquipmentSlot.LEGS).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_BOOTS = new CheeseArmor(CHEESE_ARMOR_MATERIAL, EntityEquipmentSlot.FEET).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		
		//Tools
		CHEESE_SWORD = new CheeseSword(CHEESE_TOOL_MATERIAL).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_PICKAXE = new CheesePickaxe(CHEESE_TOOL_MATERIAL).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_AXE = new CheeseAxe(CHEESE_TOOL_MATERIAL).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_SHOVEL = new CheeseSpade(CHEESE_TOOL_MATERIAL).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_HOE = new CheeseHoe(CHEESE_TOOL_MATERIAL).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_FLY_STICK = new CheeseFlyStick().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_BOW = new CheeseBow().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_MULTITOOL = new CheeseMultitool(CHEESE_TOOL_MATERIAL, 4671).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		FLINT_AND_CHEESE = new FlintAndCheese().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_SLOW_WAND = new CheeseWand(MobEffects.SLOWNESS, "Slows all entitys in a 2x2x2 area").setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_POISON_WAND = new CheeseWand(MobEffects.POISON, "Poisons all entitys in a 2x2x2 area").setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_DAMAGE_WAND = new CheeseWand(MobEffects.INSTANT_DAMAGE, "Damages all entitys in a 2x2x2 area").setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_FIRE_WAND = new CheeseWand(null, "Sets all entitys in a 2x2x2 area on fire").setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		
		//Tool heads
		CHEESE_PICKAXE_HEAD = new Item().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_AXE_HEAD = new Item().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_SHOVEL_HEAD = new Item().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		
		//Food
		CHEESE = new Cheese(4, 0.8F, false).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_COOKED = new ItemFood(7, 1.3F, true).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		BREAD_CHEESE = new ItemFood(15, 1.7F, true).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_APPLE = new CheeseApple().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_BUCKET = new CheeseBucket().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_SEEDS = new ItemSeeds(CheeseBlocks.CHEESE_PLANT, Blocks.FARMLAND).setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		
		//Other
		CHEESE_INGOT = new Item().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		CHEESE_ARROW = new CheeseArrow().setCreativeTab(CheeseTabs.CHEESE_ITEMS);
		
		//Food
		CheeseUtils.setNames(CHEESE, "cheese");
		CheeseUtils.setNames(CHEESE_COOKED, "cheese_cooked");
		CheeseUtils.setNames(BREAD_CHEESE, "bread_cheese");
		CheeseUtils.setNames(CHEESE_APPLE, "cheese_apple");
		CheeseUtils.setNames(CHEESE_BUCKET, "cheese_bucket");
		CheeseUtils.setNames(CHEESE_SEEDS, "cheese_seeds");
		
		//Other
		CheeseUtils.setNames(CHEESE_INGOT, "cheese_ingot");
		CheeseUtils.setNames(CHEESE_ARROW, "cheese_arrow");
		
		//Tool heads
		CheeseUtils.setNames(CHEESE_PICKAXE_HEAD, "cheese_pickaxe_head");
		CheeseUtils.setNames(CHEESE_AXE_HEAD, "cheese_axe_head");
		CheeseUtils.setNames(CHEESE_SHOVEL_HEAD, "cheese_shovel_head");
		
		//Tools
		CheeseUtils.setNames(CHEESE_SWORD, "cheese_sword");
		CheeseUtils.setNames(CHEESE_PICKAXE, "cheese_pickaxe");
		CheeseUtils.setNames(CHEESE_AXE, "cheese_axe");
		CheeseUtils.setNames(CHEESE_SHOVEL, "cheese_shovel");
		CheeseUtils.setNames(CHEESE_HOE, "cheese_hoe");
		CheeseUtils.setNames(CHEESE_FLY_STICK, "cheese_fly_stick");
		CheeseUtils.setNames(CHEESE_BOW, "cheese_bow");
		CheeseUtils.setNames(CHEESE_MULTITOOL, "cheese_multitool");
		CheeseUtils.setNames(FLINT_AND_CHEESE, "flint_and_cheese");
		CheeseUtils.setNames(CHEESE_SLOW_WAND, "cheese_slow_wand");
		CheeseUtils.setNames(CHEESE_POISON_WAND, "cheese_poison_wand");
		CheeseUtils.setNames(CHEESE_DAMAGE_WAND, "cheese_damage_wand");
		CheeseUtils.setNames(CHEESE_FIRE_WAND, "cheese_fire_wand");
		
		//Armor
		CheeseUtils.setNames(CHEESE_HELMET, "cheese_helmet");
		CheeseUtils.setNames(CHEESE_CHESTPLATE, "cheese_chestplate");
		CheeseUtils.setNames(CHEESE_LEGGINGS, "cheese_leggings");
		CheeseUtils.setNames(CHEESE_BOOTS, "cheese_boots");
	}	
	
	public static void register() {
		//Tools
		GameRegistry.register(CHEESE_SWORD);
		GameRegistry.register(CHEESE_PICKAXE);
		GameRegistry.register(CHEESE_AXE);
		GameRegistry.register(CHEESE_SHOVEL);
		GameRegistry.register(CHEESE_HOE);
		GameRegistry.register(CHEESE_FLY_STICK);
		GameRegistry.register(CHEESE_BOW);
		GameRegistry.register(CHEESE_MULTITOOL);
		GameRegistry.register(FLINT_AND_CHEESE);
		GameRegistry.register(CHEESE_SLOW_WAND);
		GameRegistry.register(CHEESE_POISON_WAND);
		GameRegistry.register(CHEESE_DAMAGE_WAND);
		GameRegistry.register(CHEESE_FIRE_WAND);
		
		//Armor
		GameRegistry.register(CHEESE_HELMET);
		GameRegistry.register(CHEESE_CHESTPLATE);
		GameRegistry.register(CHEESE_LEGGINGS);
		GameRegistry.register(CHEESE_BOOTS);
		
		//Tool heads
		GameRegistry.register(CHEESE_PICKAXE_HEAD);
		GameRegistry.register(CHEESE_AXE_HEAD);
		GameRegistry.register(CHEESE_SHOVEL_HEAD);
		
		//Food
		GameRegistry.register(CHEESE);
		GameRegistry.register(CHEESE_COOKED);
		GameRegistry.register(BREAD_CHEESE);
		GameRegistry.register(CHEESE_APPLE);
		GameRegistry.register(CHEESE_BUCKET);
		GameRegistry.register(CHEESE_SEEDS);
		
		//Other
		GameRegistry.register(CHEESE_INGOT);
		GameRegistry.register(CHEESE_ARROW);
	}
}
