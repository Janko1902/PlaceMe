package com.janko.placeme.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class EnchantedGoldenAppleBlock extends AppleBlock{
    public EnchantedGoldenAppleBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(Hand.MAIN_HAND);

        if (stack.isOf(Items.ENCHANTED_GOLDEN_APPLE) && state.get(APPLES) < 4) {
            world.setBlockState(pos, state.with(APPLES, state.get(APPLES) + 1));
            if (!player.isCreative()) stack.decrement(1);

            return ActionResult.SUCCESS;
        }

        if (tryEat(world, pos, state, player).isAccepted()) {
            return ActionResult.SUCCESS;
        }

        if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
            return ActionResult.CONSUME;
        }

        return tryEat(world, pos, state, player);
    }

    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(true)) {
            return ActionResult.PASS;
        } else {
            int currentApples = state.get(APPLES);

            if (currentApples > 1) {
                world.setBlockState(pos, state.with(APPLES, currentApples - 1), 3);
            } else {
                world.removeBlock(pos, false);
            }

            player.getHungerManager().add(4, 2.4F);

            player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), player);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1), player);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000), player);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000), player);

            return ActionResult.SUCCESS;
        }
    }
}
