package com.jenny.compressedtnt.entities;

import com.jenny.compressedtnt.entities.client.BaseTNTRenderer;
import com.jenny.compressedtnt.entities.client.clusterTNTRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.jenny.compressedtnt.Compressedtnt.MODID;

public class entities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);

    public static final RegistryObject<EntityType<homingPrimedTNT>> TNT_HOMING =
            ENTITY_TYPES.register("tnt_homing", () -> EntityType.Builder.<homingPrimedTNT>of(homingPrimedTNT::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F).fireImmune().clientTrackingRange(8).build("tnt_homing"));

    public static final RegistryObject<EntityType<StrongerPrimedTNT>> TNT_STRONGER =
            ENTITY_TYPES.register("tnt_stronger", () -> EntityType.Builder.<StrongerPrimedTNT>of(StrongerPrimedTNT::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F).fireImmune().clientTrackingRange(8).build("tnt_stronger"));

    public static final RegistryObject<EntityType<ClusterPrimedTNT>> TNT_CLUSTER =
            ENTITY_TYPES.register("tnt_cluster", () -> EntityType.Builder.<ClusterPrimedTNT>of(ClusterPrimedTNT::new, MobCategory.MISC)
                    .sized(0.48F, 0.48F).fireImmune().clientTrackingRange(8).build("tnt_cluster"));

    public static final RegistryObject<EntityType<blackHolePrimedTNT>> TNT_BLACK_HOLE =
            ENTITY_TYPES.register("tnt_chunk", () -> EntityType.Builder.<blackHolePrimedTNT>of(blackHolePrimedTNT::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F).fireImmune().clientTrackingRange(8).build("tnt_chunk"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    public static void registerRenderers () {
        EntityRenderers.register(TNT_STRONGER.get(), BaseTNTRenderer::new);
        EntityRenderers.register(TNT_HOMING.get(), BaseTNTRenderer::new);
        EntityRenderers.register(TNT_BLACK_HOLE.get(), BaseTNTRenderer::new);
        EntityRenderers.register(TNT_CLUSTER.get(), clusterTNTRenderer::new);


    }
}