package doc.inventorySystem.Client.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ExtraInvOverlay extends Gui {

	private Minecraft mc;
	public static final ResourceLocation extraInventory = new ResourceLocation("", "");
	
	public ExtraInvOverlay(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.isCancelable() && event.type != ElementType.HOTBAR) {
			return;
		}
		
		
	}
}
