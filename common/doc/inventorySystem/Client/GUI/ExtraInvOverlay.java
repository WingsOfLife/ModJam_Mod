package doc.inventorySystem.Client.GUI;

import net.minecraft.client.Minecraft;
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
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import doc.inventorySystem.Client.RegisterKeyBinding;
import doc.inventorySystem.Common.ExtendedEntityRender;

@SideOnly(Side.CLIENT)
public class ExtraInvOverlay extends Gui {
	private Minecraft mc;

	int xPos = 2;
	int yPos = 2;

	int textureXSize = 182;
	int textureYSize = 22;

	public static RenderItem itemRenderer = new RenderItem();

	public static final ResourceLocation extraInventory = new ResourceLocation("textures/gui/widgets.png");
	public static final ResourceLocation ITEM_TEXTURE = TextureMap.locationItemsTexture;

	public ExtraInvOverlay(Minecraft mc) {
		super();
		this.mc = mc;
	}
	
	@ForgeSubscribe(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event) {
		if (event.type != ElementType.HOTBAR)
			return;

		if (Keyboard.isKeyDown(RegisterKeyBinding.keyValues[0]) && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			
			if (event.isCancelable() && event.type == ElementType.HOTBAR)
				event.setCanceled(true);

			ExtendedEntityRender props = new ExtendedEntityRender(mc.thePlayer);
			ItemStack[] loadOut = new ItemStack[9];

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

			mc.renderEngine.bindTexture(extraInventory); //TODO: Bind Texture updating ForgeVersion
			drawTexturedModalRect(xCenter - textureXSize, yCenter, 0, 0, textureXSize, textureYSize);

			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(true);

			GL11.glEnable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			mc.renderEngine.bindTexture(ITEM_TEXTURE);
			/*for (int j = 0; j < props.loadOuts.size(); j++) {
				for (int i = 0; i < props.loadOuts.get(j).length; i++) {
					itemRenderer.renderItemAndEffectIntoGUI(null, mc.renderEngine, props.loadOuts.get(j)[i], 8 + i * 18, 26 + j * 18);
				}
			}*/
			/*for (int i = 0; i < mc.thePlayer.inventory.mainInventory.length; i++) {
				if (mc.thePlayer.inventory.mainInventory[i] != null) {
					Icon icon = mc.thePlayer.inventory.mainInventory[i].getIconIndex();
					ItemStack toRender = new ItemStack(mc.thePlayer.inventory.mainInventory[i].getItem(), 1, mc.thePlayer.inventory.mainInventory[i].getItemDamage());
					itemRenderer.renderItemAndEffectIntoGUI(null, mc.renderEngine, toRender, xCenter - textureXSize + (16 * i) + 6, yCenter);
					//itemRenderer.renderItemOverlayIntoGUI(fontRenderer, mc.func_110434_K(), toRender, xCenter - textureXSize + (16 * i) + 6, yCenter );
					//itemRenderer.renderIcon(xCenter - textureXSize + (16 * i) + 6, yCenter + 89, icon, 16, 16);
				}
			}*/
			GL11.glDisable(GL11.GL_LIGHTING);
		}
	}
}
