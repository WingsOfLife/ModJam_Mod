package doc.inventorySystem.Client;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;


public class RegisterKeyBinding {
	public static final int KEY_INDEX = 0;
	
	public static final String[] description = { "Extended Inventory" };
	public static final int[] keyValues = { Keyboard.KEY_Z };
	
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
