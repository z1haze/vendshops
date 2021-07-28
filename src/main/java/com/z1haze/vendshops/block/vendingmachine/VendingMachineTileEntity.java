package com.z1haze.vendshops.block.vendingmachine;

import com.z1haze.vendshops.setup.ModBlocks;
import com.z1haze.vendshops.setup.ModTileEntityTypes;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class VendingMachineTileEntity extends TileEntity implements IMerchant {

    MerchantOffers offers = new MerchantOffers();
    PlayerEntity customer = null;

    public VendingMachineTileEntity() {
        super(ModTileEntityTypes.VENDING_MACHINE.get());
    }

    public void openGui(PlayerEntity playerEntity) {
        setTradingPlayer(playerEntity);
        TranslationTextComponent name = new TranslationTextComponent(ModBlocks.VENDING_MACHINE.get().getDescriptionId());
        openTradingScreen(playerEntity, name, 5);
    }

    @Override
    public void setTradingPlayer(@Nullable PlayerEntity playerEntity) {
        customer = playerEntity;
    }

    @Nullable
    @Override
    public PlayerEntity getTradingPlayer() {
        return customer;
    }

    @Override
    public MerchantOffers getOffers() {
        return offers;
    }

    @Override
    public void overrideOffers(@Nullable MerchantOffers merchantOffers) {

    }

    @Override
    public void notifyTrade(MerchantOffer merchantOffer) {

    }

    @Override
    public void notifyTradeUpdated(ItemStack itemStack) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int i) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return null;
    }
}
