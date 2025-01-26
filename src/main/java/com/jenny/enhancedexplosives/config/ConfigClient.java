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

    private static final ForgeConfigSpec.ConfigValue<Float> C_PARTICLE_PERCENT =
            BUILDER.comment("amount of particles to spawn (0.0 = None, 1.0 = normal, values higher are valid too)")
                    .define("arrowParticleCount", 1.0f);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean arrowParticles;
    public static float particlePercent;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        arrowParticles = C_ARROW_PARTICLES.get();
        particlePercent = C_PARTICLE_PERCENT.get();
    }

    public static int calcPCount(int pCount) {
        return Math.round(pCount * particlePercent);
    }
}
