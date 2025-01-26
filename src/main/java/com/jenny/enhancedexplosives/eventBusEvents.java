package com.jenny.enhancedexplosives;

import com.jenny.enhancedexplosives.particles.ArrowParticle;
import com.jenny.enhancedexplosives.particles.particles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class eventBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(particles.CONCUSSIVE_ARROW_PARTICLE.get(),
                ArrowParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(particles.TNT_ARROW_PARTICLE.get(),
                ArrowParticle.Provider::new);
    }
}
