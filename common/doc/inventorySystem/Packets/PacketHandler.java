package doc.inventorySystem.Packets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import doc.inventorySystem.InventorySystemCore;

public class PacketHandler implements IPacketHandler {

	public static class PacketIds {
		public static final int openGUI = 1;
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream input = new DataInputStream(new ByteArrayInputStream(packet.data));
		byte packT;
		
		try {
			packT = input.readByte();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		if (packet.channel.equals("inventorySystem")) {
			switch (packT) {
			case PacketIds.openGUI: handleOpenGui(packet, (EntityPlayer) player, input); break;
			}
		}
	}
	
	private void handleOpenGui(Packet250CustomPayload packet, EntityPlayer player, DataInputStream input) {
		player.openGui(InventorySystemCore.instance, PacketIds.openGUI, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}

	public static final void sendOpenGuiPacket(int guiID) {
		ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(bAOS);
		
		try {
			output.write(PacketHandler.PacketIds.openGUI);
			output.write(guiID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("inventorySystem", bAOS.toByteArray()));
	}
}
