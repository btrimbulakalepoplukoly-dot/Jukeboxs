package com.chaoscube.blockentity;

import com.chaoscube.ChaosCubeMod;
import com.chaoscube.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static BlockEntityType<ChaosCubeBlockEntity> CHAOS_CUBE_BE;

    public static void register() {
        CHAOS_CUBE_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(ChaosCubeMod.MOD_ID, "chaos_cube_be"),
                BlockEntityType.Builder.create(ChaosCubeBlockEntity::new, ModBlocks.CHAOS_CUBE).build()
        );
    }
}
