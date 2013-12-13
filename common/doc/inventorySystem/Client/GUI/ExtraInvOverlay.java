package doc.inventorySystem.Client.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import doc.inventorySystem.Common.ExtendedEntityRender;

@SideOnly(Side.CLIENT)
public class ExtraInvOverlay extends Gui {
	public static RenderItem itemRenderer = new RenderItem();
	private Minecraft mc;
	
	int xPos = 2;
	int yPos = 2;
	
	int textureXSize = 184;
	int textureYSize = 22;
	
	public static final ResourceLocation extraInventory = new ResourceLocation("textures/gui/widgets.png");
	
	public ExtraInvOverlay(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.isCancelable() && event.type == ElementType.HOTBAR)
			event.setCanceled(true);
		
		if (event.type != ElementType.HOTBAR)
			return;
		
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
		//Icon icon = new ItemStack(Item.diamond, 1).getIconIndex();
		//itemRenderer.renderIcon(xCenter, yCenter, icon, 16, 16);
		mc.renderEngine.func_110577_a(extraInventory); //TODO: Bind Texture updating ForgeVersion
		drawTexturedModalRect(xCenter - textureXSize, yCenter + 86, 0, 0, textureXSize, textureYSize);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
	}
}
