package com.jenny.enhancedexplosives.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigServer {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> claymoreInstantDespawn;
    public static final ForgeConfigSpec.ConfigValue<Boolean> homingAtPlayers;
    public static final ForgeConfigSpec.ConfigValue<Boolean> carpetCompactDetonation;

    static {
        claymoreInstantDespawn =
                BUILDER.comment("Claymore arrows despawn instantly upon hitting a surface")
                        .define("claymore_instant_arrow_despawn", false);
        homingAtPlayers =
                BUILDER.comment("Homing TNTs are allowed to follow players")
                        .define("homing_tnt_target_players", true);
        carpetCompactDetonation =
                BUILDER.comment("Carpet Arrow TNTs explode upon explosion damage; This reduces spread")
                        .define("carpet_arrow_compact_detonation", true);

        SPEC = BUILDER.build();
    }
}
