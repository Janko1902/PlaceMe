package com.janko.placeme.mixin;

import com.janko.placeme.block.ModBlocks;
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
            cancellable = true)
    private void usePumpkinPie(ItemUsageContext ctx, CallbackInfoReturnable<ActionResult> cir)
    {
        if (ctx.getStack().isOf(Items.PUMPKIN_PIE))
        {
            ModBlocks.PUMPKIN_PIE.asItem().useOnBlock(ctx);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
