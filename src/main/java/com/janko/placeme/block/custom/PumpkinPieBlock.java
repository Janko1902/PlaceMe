package com.janko.placeme.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class PumpkinPieBlock extends Block {
    public static final int MAX_BITES = 4;
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES, FACING);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction,
                                                BlockState neighborState, WorldAccess world,
                                                BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolid();
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return (MAX_BITES - state.get(BITES)) * 2;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }
}


