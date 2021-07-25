package com.z1haze.vendshops.block.vendingmachine;

import com.z1haze.vendshops.block.RotateContainerBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class VendingMachineBlock extends RotateContainerBase {

    public static final BooleanProperty BOTTOM = BlockStateProperties.BOTTOM;

    public VendingMachineBlock(Properties builder) {
        super(builder);

        registerDefaultState(getStateDefinition().any().setValue(HORIZONTAL_FACING, Direction.NORTH).setValue(BOTTOM, true));
    }

    public static boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    public static int lightValue(BlockState state) {
        return 8;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return defaultBlockState().setValue(HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockPos pos2 = state.getValue(BOTTOM) ? pos.above() : pos.below();

            if (world.getBlockState(pos2).getBlock() == this) {
                world.setBlock(pos2, Blocks.AIR.defaultBlockState(), 3);
            }

            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }

        super.onRemove(state, world, pos, newState, isMoving);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, BOTTOM);
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);

        world.setBlock(pos.above(), state.setValue(BOTTOM, false), 3);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
