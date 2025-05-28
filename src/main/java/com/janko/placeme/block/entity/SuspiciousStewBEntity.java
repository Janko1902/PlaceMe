package com.janko.placeme.block.entity;

import com.janko.placeme.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class SuspiciousStewBEntity extends BlockEntity{

    private SuspiciousStewEffectsComponent EFFECTS = SuspiciousStewEffectsComponent.DEFAULT;

    public SuspiciousStewBEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SUS_STEW_B_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        int index = 0;
        for(SuspiciousStewEffectsComponent.StewEffect stewEffect : EFFECTS.effects()) {
            String ID = stewEffect.effect().getIdAsString();
            int duration = stewEffect.duration();
            nbt.putString(EFFECT_ID+index,ID);
            nbt.putInt(EFFECT_DURATION+index,duration);
            index++;
        }
        super.writeNbt(nbt, registryLookup);
    }

    private static final String EFFECT_ID = "effectid";
    private static final String EFFECT_DURATION = "effectduration";


    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        Set<String> allKeys = nbt.getKeys();

        String[] effectKeys = allKeys.stream().filter(s -> s.contains(EFFECT_ID))
                .sorted(Comparator.comparingInt(s -> Integer.parseInt(s.replaceAll("\\D", "")))).toList().toArray(new String[0]);

        if (effectKeys.length > 0) {
            String[] durationKeys = allKeys.stream().filter(s -> s.contains(EFFECT_DURATION))
                    .sorted(Comparator.comparingInt(s -> Integer.parseInt(s.replaceAll("\\D", "")))).toList().toArray(new String[0]);

            int len = effectKeys.length;

            for(int i = 0; i < len; i++){
                String effectString = nbt.getString(effectKeys[i]);
                if(Identifier.validate(effectString).isSuccess()) {

                    RegistryEntry<StatusEffect> effect = RegistryEntry.of(
                            Registries.STATUS_EFFECT.get(Identifier.of(effectString)));
                    int duration = nbt.getInt(durationKeys[i]);

                    EFFECTS = EFFECTS.with(new SuspiciousStewEffectsComponent.StewEffect(effect, duration));
                }
            }
        }

        super.readNbt(nbt, registryLookup);
    }

    public void applyEffects(PlayerEntity user) {
        for(SuspiciousStewEffectsComponent.StewEffect stewEffect : EFFECTS.effects()) {
            user.addStatusEffect(stewEffect.createStatusEffectInstance());
        }
        EFFECTS = SuspiciousStewEffectsComponent.DEFAULT;
    }

    public void addEffects(SuspiciousStewEffectsComponent effects) {
        EFFECTS = effects;
    }
}