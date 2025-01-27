package com.jenny.enhancedexplosives.particles;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

public class particles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);

    public static final RegistryObject<SimpleParticleType> CONCUSSIVE_ARROW_PARTICLE =
            PARTICLES.register("particle_concussive_arrow", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TNT_ARROW_PARTICLE =
            PARTICLES.register("particle_tnt_arrow", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> CARPET_ARROW_PARTICLE =
            PARTICLES.register("particle_carpet_arrow", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> TUNNEL_ARROW_PARTICLE =
            PARTICLES.register("particle_tunnel_arrow", () -> new SimpleParticleType(true));

    public static void register(IEventBus bus) {
        PARTICLES.register(bus);
    }
}
