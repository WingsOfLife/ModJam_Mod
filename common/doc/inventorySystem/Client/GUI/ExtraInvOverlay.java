package doc.inventorySystem.Client.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
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
	private Minecraft mc;

	int xPos = 2;
	int yPos = 2;

	int textureXSize = 182;
	int textureYSize = 22;

	FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
	
	public static RenderItem itemRenderer = new RenderItem();
	
	public static final ResourceLocation extraInventory = new ResourceLocation("textures/gui/widgets.png");
	public static final ResourceLocation ITEM_TEXTURE = TextureMap.field_110576_c;

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

		int xStart = 0;
		int yStart = 0;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		
		mc.renderEngine.func_110577_a(extraInventory); //TODO: Bind Texture updating ForgeVersion
		drawTexturedModalRect(xCenter - textureXSize, yCenter + 86, 0, 0, textureXSize, textureYSize);
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		
		mc.renderEngine.func_110577_a(ITEM_TEXTURE);
		for (int i = 0; i < mc.thePlayer.inventory.mainInventory.length; i++) {
			if (mc.thePlayer.inventory.mainInventory[i] != null) {
				Icon icon = mc.thePlayer.inventory.mainInventory[i].getIconIndex();
				ItemStack toRender = new ItemStack(mc.thePlayer.inventory.mainInventory[i].getItem());
				itemRenderer.renderItemAndEffectIntoGUI(fontRenderer, mc.func_110434_K(), toRender, xCenter - textureXSize + (16 * i) + 6, yCenter + 89);
				//itemRenderer.renderIcon(xCenter - textureXSize + (16 * i) + 6, yCenter + 89, icon, 16, 16);
			}
		}
	}
}
