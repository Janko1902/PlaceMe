package com.janko.placeme.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ItemBlockPair {
    public final Item item;
    public final Block block;
    public ItemBlockPair(Item item, Block block) {
        this.item = item;
        this.block = block;
    }
}
