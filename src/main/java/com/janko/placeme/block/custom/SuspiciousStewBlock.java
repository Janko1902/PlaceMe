package com.janko.placeme.block.custom;

import com.janko.placeme.block.ModBlocks;
import com.janko.placeme.block.entity.SuspiciousStewBEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class SuspiciousStewBlock extends SoupStewBowlBlock implements BlockEntityProvider{
    public SuspiciousStewBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SuspiciousStewBEntity(pos,state);
    }

    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(4, 0, 4, 12, 1, 12),
            Block.createCuboidShape(3, 1, 3, 13, 4, 13)
    );

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient) {
            if (world.getBlockEntity(pos) instanceof SuspiciousStewBEntity bEntity) {
                foo = bEntity;
                bEntity.addEffects(
                        itemStack.getOrDefault(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffectsComponent.DEFAULT)
                );
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    SuspiciousStewBEntity foo;

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.getHungerManager().add(6, 7.2F);
            world.emitGameEvent(player, GameEvent.EAT, pos);

            if(!world.isClient) {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof SuspiciousStewBEntity bEntity) {
                    bEntity.applyEffects(player);
                }
            }

            world.removeBlockEntity(pos);
            world.setBlockState(pos, ModBlocks.BOWL.getDefaultState(), 1);


            return ActionResult.SUCCESS;
        }
    }

    /*protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {

    }*/

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

}