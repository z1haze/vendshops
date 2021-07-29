package com.z1haze.vendshops.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.z1haze.vendshops.VendShops;
import com.z1haze.vendshops.block.vendingmachine.VendingMachineContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VendingMachineScreen extends ContainerScreen<VendingMachineContainer> {

    private static final ResourceLocation VENDING_MACHINE_GUI = new ResourceLocation(VendShops.MOD_ID, "textures/gui/vending_machine_gui.png");

    public VendingMachineScreen(VendingMachineContainer container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);

        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 175;
        this.imageHeight = 201;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        this.font.draw(matrixStack, this.inventory.getDisplayName(), (float) this.inventoryLabelX, (float) this.inventoryLabelY, 4210752);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        //noinspection deprecation
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.textureManager.bind(VENDING_MACHINE_GUI);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        blit(matrixStack, x, y, 0, 0, imageWidth, imageHeight);
    }
}
