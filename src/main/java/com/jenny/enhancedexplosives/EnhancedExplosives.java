package com.jenny.enhancedexplosives;

import com.jenny.enhancedexplosives.blocks.blocks;
import com.jenny.enhancedexplosives.config.ConfigClient;
import com.jenny.enhancedexplosives.config.ConfigServer;
import com.jenny.enhancedexplosives.entities.entities;
import com.jenny.enhancedexplosives.items.items;
import com.jenny.enhancedexplosives.particles.particles;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(EnhancedExplosives.MODID)
public class EnhancedExplosives {

    public static final String MODID = "enhancedexplosives";
    private static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("removal")
    public EnhancedExplosives() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        particles.register(modEventBus);
        blocks.register(modEventBus);
        items.register(modEventBus);
        entities.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigClient.SPEC, "enhancedexplosives-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigServer.SPEC, "enhancedexplosives-server.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        items.registerDispenser();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}