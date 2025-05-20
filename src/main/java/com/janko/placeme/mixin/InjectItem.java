package com.janko.placeme.mixin;

import com.janko.placeme.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class InjectItem {
    @Inject(
            method = "useOnBlock",
            at = @At("TAIL"),
            cancellable = true)    private void useItems(ItemUsageContext ctx, CallbackInfoReturnable<ActionResult> cir) {
        if (ctx.getStack().isOf(Items.PUMPKIN_PIE)) {
            ModBlocks.PUMPKIN_PIE.asItem().useOnBlock(ctx);
            cir.setReturnValue(ActionResult.SUCCESS);
        }

        if (ctx.getStack().isOf(Items.BOWL)) {
            ModBlocks.BOWL.asItem().useOnBlock(ctx);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
        if (ctx.getStack().isOf(Items.BEETROOT_SOUP)) {
            ModBlocks.BEETROOT_SOUP_BOWL.asItem().useOnBlock(ctx);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
        if (ctx.getStack().isOf(Items.MUSHROOM_STEW)) {
            ModBlocks.MUSHROOM_STEW_BOWL.asItem().useOnBlock(ctx);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
        if (ctx.getStack().isOf(Items.RABBIT_STEW)) {
            ModBlocks.RABBIT_STEW_BOWL.asItem().useOnBlock(ctx);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
        if (ctx.getStack().isOf(Items.SUSPICIOUS_STEW)) {
            ModBlocks.SUSPICIOUS_STEW_BOWL.asItem().useOnBlock(ctx);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
