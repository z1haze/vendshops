package com.z1haze.vendshops.block.vendingmachine;

import com.z1haze.vendshops.VendShops;
import com.z1haze.vendshops.setup.ModBlocks;
import com.z1haze.vendshops.setup.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.merchant.IMerchant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class VendingMachineTileEntity extends LockableLootTileEntity implements IMerchant, IInventory {

    private final MerchantOffers offers = new MerchantOffers();
    private PlayerEntity customer = null;
    private NonNullList<ItemStack> items;
    public static final int slots = 1;

    protected VendingMachineTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public VendingMachineTileEntity() {
        this(ModTileEntityTypes.VENDING_MACHINE.get());
        this.items = NonNullList.withSize(slots, ItemStack.EMPTY);
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

    @Override
    public int getContainerSize() {
        return slots;
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < slots; i++) {
            if (!getItem(i).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int index) {
        return items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ItemStackHelper.removeItem(items, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(items, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        items.set(index, itemStack);
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
        return this.level != null
                && this.level.getBlockEntity(this.worldPosition) == this
                && playerEntity.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ()) <= 64;
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + VendShops.MOD_ID + ".vending_machine");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new VendingMachineContainer(id, playerInventory, this);
    }

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        super.load(blockState, nbt);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);

        if (!trySaveLootTable(nbt)) {
            ItemStackHelper.saveAllItems(nbt, items);
        }

        return nbt;
    }
}
