package com.janko.placeme.mixin;

import com.janko.placeme.block.ModBlocks;
import com.janko.placeme.util.ItemBlockPair;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(Item.class)
public class InjectItem {
    @Inject(
            method = "useOnBlock",
            at = @At("TAIL"),
            cancellable = true
    )
    private void useItems(ItemUsageContext ctx, CallbackInfoReturnable<ActionResult> cir) {
        ItemBlockPair[] pairs = {
                new ItemBlockPair(Items.PUMPKIN_PIE, ModBlocks.PUMPKIN_PIE),
                new ItemBlockPair(Items.BOWL, ModBlocks.BOWL),
                new ItemBlockPair(Items.BEETROOT_SOUP, ModBlocks.BEETROOT_SOUP_BOWL),
                new ItemBlockPair(Items.MUSHROOM_STEW, ModBlocks.MUSHROOM_STEW_BOWL),
                new ItemBlockPair(Items.RABBIT_STEW, ModBlocks.RABBIT_STEW_BOWL),
                new ItemBlockPair(Items.SUSPICIOUS_STEW, ModBlocks.SUSPICIOUS_STEW_BOWL)
        };

        for (ItemBlockPair pair : pairs) {
            if (ctx.getStack().isOf(pair.item)) {
                pair.block.asItem().useOnBlock(ctx);
                cir.setReturnValue(ActionResult.SUCCESS);
                return;
            }
        }
    }
}
