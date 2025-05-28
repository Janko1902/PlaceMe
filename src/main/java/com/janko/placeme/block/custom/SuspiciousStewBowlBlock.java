package com.janko.placeme.block.custom;

import com.janko.placeme.block.ModBlocks;
import com.janko.placeme.block.entity.custom.SuspiciousStewBowlBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class SuspiciousStewBowlBlock extends SoupStewBowlBlock implements BlockEntityProvider{
    public SuspiciousStewBowlBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SuspiciousStewBowlBlockEntity(pos,state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient) {
            if (world.getBlockEntity(pos) instanceof SuspiciousStewBowlBlockEntity bEntity) {
                foo = bEntity;
                bEntity.addEffects(
                        itemStack.getOrDefault(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, SuspiciousStewEffectsComponent.DEFAULT)
                );
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    SuspiciousStewBowlBlockEntity foo;

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!player.canConsume(true)) {
            return ActionResult.PASS;
        } else {
            player.getHungerManager().add(6, 7.2F);
            world.emitGameEvent(player, GameEvent.EAT, pos);

            if(!world.isClient) {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof SuspiciousStewBowlBlockEntity bEntity) {
                    bEntity.applyEffects(player);
                }
            }

            world.removeBlockEntity(pos);
            world.setBlockState(pos, ModBlocks.BOWL.getDefaultState(), 1);


            return ActionResult.SUCCESS;
        }
    }
}