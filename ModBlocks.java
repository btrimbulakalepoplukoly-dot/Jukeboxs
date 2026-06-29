package com.chaoscube;

import com.chaoscube.block.ModBlocks;
import com.chaoscube.blockentity.ModBlockEntities;
import com.chaoscube.network.ModNetwork;
import com.chaoscube.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChaosCubeMod implements ModInitializer {
    public static final String MOD_ID = "chaoscube";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModBlockEntities.register();
        ModScreenHandlers.register();
        ModNetwork.register();
        LOGGER.info("[ChaosCube] Mod loaded! Craft 4 dirt → Chaos Cube");
    }
}
