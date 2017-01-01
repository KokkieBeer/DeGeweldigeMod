package com.Egietje.degeweldigemod.commands;

import com.Egietje.degeweldigemod.capability.cheese.CheeseProvider;
import com.Egietje.degeweldigemod.capability.cheese.ICheese;
import com.Egietje.degeweldigemod.handler.CheesePacketHandler;
import com.Egietje.degeweldigemod.packet.CheeseFillMessage;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class FillCheese extends CommandBase {

	@Override
	public String getName() {
		return "fillcheese";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.fillcheese.usage";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length > 0) {
			throw new WrongUsageException("commands.fillcheese.usage", new Object[0]);
		} else {
			Entity entity = sender.getCommandSenderEntity();
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;
				CheesePacketHandler.INSTANCE.sendTo(new CheeseFillMessage(), (EntityPlayerMP) player);
				notifyCommandListener(sender, this, "commands.fillcheese.succes",
						new Object[] { player.getDisplayName() });
			}

		}
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return index == 0;
	}
}
