package doc.inventorySystem.Client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;


public class RegisterKeyBinding {
	public static final int LOADOUT_INV = 0;
	public static final int LOADOUT_ONE = 1;
	public static final int LOADOUT_TWO = 2;
	public static final int LOADOUT_THR = 3;
	public static final int LOADOUT_CLR = 4;
	
	public static final String[] description = { "Loadout Inventory", "Loadout 1", "Loadout 2", "Loadout 3", "Clear Current Loadout" };
	public static final int[] keyValues = { Keyboard.KEY_Z, Keyboard.KEY_NUMPAD1, Keyboard.KEY_NUMPAD2, Keyboard.KEY_NUMPAD3, Keyboard.KEY_NUMPAD0 };
	
	public static final Map<Integer, Integer> keyMapping = new HashMap<Integer, Integer>();
	
	public static void init() {
		KeyBinding[] key = new KeyBinding[description.length];
		boolean[] repeat = new boolean[description.length];
		
		for (int i = 0; i < description.length; i++) {
			key[i] = new KeyBinding(description[i], keyValues[i]);
			repeat[i] = false;
			keyMapping.put(key[i].keyCode, i);
		}
		
		KeyBindingRegistry.registerKeyBinding(new isKeyHandler(key, repeat));
	}
}
