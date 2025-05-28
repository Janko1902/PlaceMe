package com.janko.placeme;

import com.janko.placeme.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

import java.util.Map;

public class PlaceMeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Map<Block, RenderLayer> renderLayerMap = Map.of(
                ModBlocks.RABBIT_STEW_BOWL, RenderLayer.getCutout(),

                ModBlocks.RAW_CHICKEN, RenderLayer.getCutout(),
                ModBlocks.COOKED_CHICKEN, RenderLayer.getCutout(),
                ModBlocks.LAVA_CHICKEN, RenderLayer.getCutout(),

                ModBlocks.APPLE, RenderLayer.getCutout(),
                ModBlocks.GOLDEN_APPLE, RenderLayer.getCutout(),
                ModBlocks.ENCHANTED_GOLDEN_APPLE, RenderLayer.getCutout()
        );

        renderLayerMap.forEach((block, layer) -> {
            BlockRenderLayerMap.INSTANCE.putBlock(block, layer);
        });
    }
}
