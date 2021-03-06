package doc.inventorySystem.Client;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import doc.inventorySystem.Client.GUI.LoadoutContainer;
import doc.inventorySystem.Common.ExtendedEntityRender;
import doc.inventorySystem.Helper.LoadoutHelper;
import doc.inventorySystem.Packets.PacketHandler;

public class isKeyHandler extends KeyHandler {

	public static final String name = "Extended Inventory Hotkey";

	public isKeyHandler(KeyBinding[] keyBinding, boolean[] repeating) {
		super(keyBinding, repeating);
	}

	@Override
	public String getLabel() {
		return name;
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb,	boolean tickEnd, boolean isRepeat) {
		if (tickEnd && RegisterKeyBinding.keyMapping.containsKey(kb.keyCode)) {
			EntityPlayer ePlayer = FMLClientHandler.instance().getClient().thePlayer;
			ExtendedEntityRender props = ExtendedEntityRender.get(ePlayer);

			switch(RegisterKeyBinding.keyMapping.get(kb.keyCode)) {
			case RegisterKeyBinding.LOADOUT_INV:
				if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
					return;
				if (ePlayer.openContainer != null && ePlayer.openContainer instanceof LoadoutContainer) {
					ePlayer.closeScreen();
				} else if (FMLClientHandler.instance().getClient().inGameHasFocus) {
					PacketHandler.sendOpenGuiPacket(PacketHandler.PacketIds.openGUI);
				}
				break;
			case RegisterKeyBinding.LOADOUT_ONE:
				LoadoutHelper.swapToLoadout(1, ePlayer);
				PacketHandler.sendInventoryPacket(PacketHandler.PacketIds.updateInventory, 1);
				break;
			case RegisterKeyBinding.LOADOUT_TWO:
				LoadoutHelper.swapToLoadout(2, ePlayer);
				PacketHandler.sendInventoryPacket(PacketHandler.PacketIds.updateInventory, 2);
				break;
			case RegisterKeyBinding.LOADOUT_THR:
				LoadoutHelper.swapToLoadout(3, ePlayer);
				PacketHandler.sendInventoryPacket(PacketHandler.PacketIds.updateInventory, 3);
				break;
			case RegisterKeyBinding.LOADOUT_CLR:
				LoadoutHelper.swapToLoadout(0, ePlayer);
				PacketHandler.sendInventoryPacket(PacketHandler.PacketIds.updateInventory, 0);
				break;
			}
		}

	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}
}
