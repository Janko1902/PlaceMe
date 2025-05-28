package com.janko.placeme.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class AppleBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty APPLES = IntProperty.of("apples", 1, 4);

    private static final VoxelShape ONE_APPLE_SHAPE = Block.createCuboidShape(5, 0, 5, 11, 6, 11);
    private static final VoxelShape TWO_APPLES_SHAPE = Block.createCuboidShape(0.5, 0, 1.5, 15.5, 6, 14.5);
    private static final VoxelShape THREE_APPLES_SHAPE = Block.createCuboidShape(0.5, 0, 0.5, 15.5, 6, 15.5);
    private static final VoxelShape FOUR_APPLES_SHAPE = Block.createCuboidShape(0.5, 0, 0.5, 15.5, 12, 15.5);

    public AppleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(APPLES, 1)
                .with(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(APPLES)) {
            default -> ONE_APPLE_SHAPE;
            case 2 -> TWO_APPLES_SHAPE;
            case 3 -> THREE_APPLES_SHAPE;
            case 4 -> FOUR_APPLES_SHAPE;
        };
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

        if (stack.isOf(Items.APPLE) && state.get(APPLES) < 4) {
            world.setBlockState(pos, state.with(APPLES, state.get(APPLES) + 1));
            if (!player.isCreative()) stack.decrement(1);

            return ActionResult.SUCCESS;
        }

        return tryEat(world, pos, state, player);
    }

    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            int currentApples = state.get(APPLES);

            if (currentApples > 1) {
                world.setBlockState(pos, state.with(APPLES, currentApples - 1), 3);
            } else {
                world.removeBlock(pos, false);
            }

            player.getHungerManager().add(4, 2.4F);

            return ActionResult.SUCCESS;
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(APPLES, FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction playerFacing = ctx.getHorizontalPlayerFacing().getOpposite();
        BlockState existing = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (existing.isOf(this)) {
            int count = Math.min(4, existing.get(APPLES) + 1);
            return existing.with(APPLES, count).with(FACING, playerFacing);
        }
        return this.getDefaultState().with(FACING, playerFacing);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolid();
    }

    @Override
    public boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }
}
