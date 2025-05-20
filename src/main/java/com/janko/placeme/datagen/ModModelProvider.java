package com.janko.placeme.datagen;

import com.janko.placeme.block.ModBlocks;
import com.janko.placeme.block.custom.PumpkinPieBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(ModBlocks.PUMPKIN_PIE)
                        .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING, PumpkinPieBlock.BITES)
                                .register(Direction.NORTH, 0, createVariant(0, VariantSettings.Rotation.R0))
                                .register(Direction.EAST, 0, createVariant(0, VariantSettings.Rotation.R90))
                                .register(Direction.SOUTH, 0, createVariant(0, VariantSettings.Rotation.R180))
                                .register(Direction.WEST, 0, createVariant(0, VariantSettings.Rotation.R270))

                                .register(Direction.NORTH, 1, createVariant(1, VariantSettings.Rotation.R0))
                                .register(Direction.EAST, 1, createVariant(1, VariantSettings.Rotation.R90))
                                .register(Direction.SOUTH, 1, createVariant(1, VariantSettings.Rotation.R180))
                                .register(Direction.WEST, 1, createVariant(1, VariantSettings.Rotation.R270))

                                .register(Direction.NORTH, 2, createVariant(2, VariantSettings.Rotation.R0))
                                .register(Direction.EAST, 2, createVariant(2, VariantSettings.Rotation.R90))
                                .register(Direction.SOUTH, 2, createVariant(2, VariantSettings.Rotation.R180))
                                .register(Direction.WEST, 2, createVariant(2, VariantSettings.Rotation.R270))

                                .register(Direction.NORTH, 3, createVariant(3, VariantSettings.Rotation.R0))
                                .register(Direction.EAST, 3, createVariant(3, VariantSettings.Rotation.R90))
                                .register(Direction.SOUTH, 3, createVariant(3, VariantSettings.Rotation.R180))
                                .register(Direction.WEST, 3, createVariant(3, VariantSettings.Rotation.R270))
                        )
        );

        blockStateModelGenerator.registerSimpleState(ModBlocks.BOWL);
        blockStateModelGenerator.registerSimpleState(ModBlocks.BEETROOT_SOUP_BOWL);
        blockStateModelGenerator.registerSimpleState(ModBlocks.MUSHROOM_STEW_BOWL);
        blockStateModelGenerator.registerSimpleState(ModBlocks.RABBIT_STEW_BOWL);
        blockStateModelGenerator.registerSimpleState(ModBlocks.SUSPICIOUS_STEW_BOWL);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    private BlockStateVariant createVariant(int bite, VariantSettings.Rotation rotation) {
        return BlockStateVariant.create()
                .put(VariantSettings.MODEL, Identifier.of("placeme", "block/pumpkin_pie_" + bite))
                .put(VariantSettings.Y, rotation);
    }
}
