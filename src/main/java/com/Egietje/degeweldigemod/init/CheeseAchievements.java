package com.Egietje.degeweldigemod.init;

import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

public class CheeseAchievements {
	public static Achievement JOIN;
	public static Achievement FLY;
	public static Achievement COOKIE;
	public static Achievement EAT;
	public static Achievement KILL;
	
	public CheeseAchievements() {
		init();
		register();
	}
	
	private void init() {
		JOIN = new Achievement("achievement.join", "join", 0, 0, CheeseItems.CHEESE, (Achievement)null).initIndependentStat().setSpecial();
		EAT = new Achievement("achievement.eat_cheese", "eat_cheese", 5, 2, CheeseItems.CHEESE, JOIN).setSpecial();
		FLY = new Achievement("achievement.fly", "fly", 2, 1, CheeseItems.CHEESE_FLY_STICK, EAT).setSpecial();
		COOKIE = new Achievement("achievement.cookie", "cookie", 3, 3, Items.COOKIE, FLY).setSpecial();
		KILL = new Achievement("achievement.kill_boss", "kill_boss", 6, -1, CheeseItems.CHEESE_SWORD, COOKIE).setSpecial();
	}
	
	private void register() {
		JOIN.registerStat();
		EAT.registerStat();
		FLY.registerStat();
		COOKIE.registerStat();
		KILL.registerStat();
		AchievementPage.registerAchievementPage(new AchievementPage("Cheese Achievements", new Achievement[] { JOIN, EAT, FLY, COOKIE, KILL }));
	}
}
