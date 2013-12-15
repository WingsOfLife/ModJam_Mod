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
import doc.inventorySystem.Client.GUI.LoadoutContainer;
import doc.inventorySystem.Helper.LoadoutHelper;

public class PacketHandler implements IPacketHandler {

	public static class PacketIds {
		public static final int openGUI         = 1;
		public static final int updateInventory = 2;
		public static final int updateLoadout   = 3;
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream input = new DataInputStream(new ByteArrayInputStream(packet.data));
		int packId;
		int loadoutId;
		
		try {
			packId = input.readInt();
			loadoutId = input.readInt();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		if (packet.channel.equals("inventorySystem")) {
			switch (packId) {
			case PacketIds.openGUI: handleOpenGui(packet, (EntityPlayer) player, input); break;
			case PacketIds.updateInventory: LoadoutHelper.swapToLoadout(loadoutId, (EntityPlayer) player); break;
			case PacketIds.updateLoadout: LoadoutContainer.saveLoadouts((EntityPlayer) player);
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
			output.writeInt(PacketHandler.PacketIds.openGUI);
			output.writeInt(guiID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("inventorySystem", bAOS.toByteArray()));
	}
	
	public static final void sendInventoryPacket(int packetId, int loadoutId) {
		ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(bAOS);
		try {
			output.writeInt(packetId);
			output.writeInt(loadoutId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("inventorySystem", bAOS.toByteArray()));
	}
	
	public static final void updateLoadouts() {
		ByteArrayOutputStream bAOS = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(bAOS);
		try {
			output.writeInt(PacketHandler.PacketIds.updateLoadout);
			output.writeInt(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket("inventorySystem", bAOS.toByteArray()));
	}
}
