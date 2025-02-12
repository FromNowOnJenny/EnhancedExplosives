package com.jenny.enhancedexplosives.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigClient {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> arrowParticles;
    public static final ForgeConfigSpec.ConfigValue<Boolean> tntParticles;
    public static final ForgeConfigSpec.ConfigValue<Double> particlePercent;

    static {
        arrowParticles =
                BUILDER.comment("particles from arrows arrows")
                        .define("arrow_particles", true);
        tntParticles =
                BUILDER.comment("particles from tnt")
                        .define("tnt_particles", true);
        particlePercent =
                BUILDER.comment("amount of spawned particles (0.0 = None, 1.0 = normal, values higher are valid too)")
                        .define("particle_amount", 1.0D);

        SPEC = BUILDER.build();

    }

    public static int calcPCount(int pCount) {
        return (int) Math.round(pCount * particlePercent.get());
    }
}
