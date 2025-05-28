package com.janko.placeme.block;

import com.janko.placeme.PlaceMe;
import com.janko.placeme.block.entity.SuspiciousStewBEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {

    public static final BlockEntityType<SuspiciousStewBEntity> SUS_STEW_B_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, PlaceMe.id("b_e_sus_stew"),
                    BlockEntityType.Builder.create(SuspiciousStewBEntity::new,
                            ModBlocks.SUSPICIOUS_STEW_BOWL
                    ).build());

    public static void registerBlockEntities() {
        PlaceMe.LOGGER.info("Registering Block Entities for " + PlaceMe.MOD_ID);
    }
}