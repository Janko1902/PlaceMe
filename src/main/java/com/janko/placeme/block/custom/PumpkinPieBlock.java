package com.janko.placeme.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class PumpkinPieBlock extends Block {
    public static final IntProperty BITES = IntProperty.of("bites", 0, 3);
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    private static final VoxelShape[][] SHAPES = new VoxelShape[4][4];

    static {
        SHAPES[0] = new VoxelShape[] {
                Block.createCuboidShape(3, 0, 3, 13, 3, 13),
                Block.createCuboidShape(3, 0, 3, 13, 3, 13),
                Block.createCuboidShape(3, 0, 3, 13, 3, 13),
                Block.createCuboidShape(3, 0, 3, 13, 3, 13)
        };

        SHAPES[1] = new VoxelShape[] {
                VoxelShapes.union(
                        Block.createCuboidShape(3, 0, 3, 13, 3, 8),
                        Block.createCuboidShape(3, 0, 3, 8, 3, 13)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(3, 0, 3, 13, 3, 8),
                        Block.createCuboidShape(8, 0, 8, 13, 3, 13)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(3, 0, 8, 13, 3, 13),
                        Block.createCuboidShape(8, 0, 3, 13, 3, 13)
                ),
                VoxelShapes.union(
                        Block.createCuboidShape(8, 0, 8, 13, 3, 13),
                        Block.createCuboidShape(3, 0, 3, 8, 3, 13)
                )
        };

        SHAPES[2] = new VoxelShape[] {
                Block.createCuboidShape(3, 0, 3, 13, 3, 8),
                Block.createCuboidShape(8, 0, 3, 13, 3, 13),
                Block.createCuboidShape(3, 0, 8, 13, 3, 13),
                Block.createCuboidShape(3, 0, 3, 8, 3, 13)
        };

        SHAPES[3] = new VoxelShape[] {
                Block.createCuboidShape(8, 0, 3, 13, 3, 8),
                Block.createCuboidShape(8, 0, 8, 13, 3, 13),
                Block.createCuboidShape(3, 0, 8, 8, 3, 13),
                Block.createCuboidShape(3, 0, 3, 8, 3, 8)
        };
    }

    public PumpkinPieBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(BITES, 0)
                .with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int bite = state.get(BITES);
        Direction facing = state.get(FACING);
        return SHAPES[bite][facing.getHorizontal()];
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            if (tryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return tryEat(world, pos, state, player);
    }

    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.getHungerManager().add(2, 1.2F);
            int i = (Integer)state.get(BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (i < 3) {
                world.setBlockState(pos, state.with(BITES, i + 1), Block.NOTIFY_ALL);
            } else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES, FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
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


