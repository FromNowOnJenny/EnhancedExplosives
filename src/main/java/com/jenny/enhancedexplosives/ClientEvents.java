package com.jenny.enhancedexplosives;

import com.jenny.enhancedexplosives.entities.entities;
import com.jenny.enhancedexplosives.particles.ArrowParticle;
import com.jenny.enhancedexplosives.particles.particles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(particles.CONCUSSIVE_ARROW_PARTICLE.get(),
                ArrowParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(particles.TNT_ARROW_PARTICLE.get(),
                ArrowParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(particles.CARPET_ARROW_PARTICLE.get(),
                ArrowParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(particles.TUNNEL_ARROW_PARTICLE.get(),
                ArrowParticle.Provider::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        entities.registerRenderers();
    }
}
