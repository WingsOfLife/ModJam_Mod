package doc.inventorySystem.Client.GUI;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import doc.inventorySystem.Common.ExtendedEntityRender;

@SideOnly(Side.CLIENT)
public class ExtraInvOverlay extends Gui {

	private Minecraft mc;
	
	int xPos = 2;
	int yPos = 2;
	
	int textureXSize = 0;
	int textureYSize = 0;
	
	public static final ResourceLocation extraInventory = new ResourceLocation("inventorySystems", "textures/gui/liquidTank.png");
	
	public ExtraInvOverlay(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.isCancelable() && event.type != ElementType.HOTBAR) {
			return;
		}
		
		ExtendedEntityRender props = new ExtendedEntityRender(mc.thePlayer);
		if (props == null)
			return;
		
		int xCenter = (event.resolution.getScaledWidth() + textureXSize) / 2;
		int yCenter = (event.resolution.getScaledHeight() + textureYSize) / 2;
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		mc.renderEngine.func_110577_a(extraInventory); //TODO: Bind Texture updating ForgeVersion
		drawTexturedModalRect(xCenter, yCenter, 0, 0, textureXSize, textureYSize);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
	}
}
