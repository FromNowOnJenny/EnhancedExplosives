package com.jenny.enhancedexplosives.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigClient {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<Boolean> C_ARROW_PARTICLES =
            BUILDER.comment("weather to spawn client-side particles for arrows")
                    .define("arrowParticles", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean ARROW_PARTICLES;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        ARROW_PARTICLES = C_ARROW_PARTICLES.get();
    }
}
