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

    private static final ForgeConfigSpec.ConfigValue<Integer> C_ARROW_PARTICLE_COUNT =
            BUILDER.comment("amount of particles to spawn per arrow per tick")
                    .define("arrowParticleCount", 3);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean arrowParticles;
    public static int arrowParticleCount;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        arrowParticles = C_ARROW_PARTICLES.get();
        arrowParticleCount = C_ARROW_PARTICLE_COUNT.get();
    }
}
