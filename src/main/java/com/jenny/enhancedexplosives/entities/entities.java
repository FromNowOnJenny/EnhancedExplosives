package com.jenny.enhancedexplosives.entities;

import com.jenny.enhancedexplosives.entities.arrows.*;
import com.jenny.enhancedexplosives.entities.client.*;
import com.jenny.enhancedexplosives.entities.throwable.dynamite;
import com.jenny.enhancedexplosives.entities.tnt.*;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

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
            ENTITY_TYPES.register("tnt_blackhole", () -> EntityType.Builder.<blackHolePrimedTNT>of(blackHolePrimedTNT::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F).fireImmune().clientTrackingRange(8).build("tnt_blackhole"));

    public static final RegistryObject<EntityType<selectivePrimedTNT>> TNT_SELECTIVE =
            ENTITY_TYPES.register("tnt_selective", () -> EntityType.Builder.<selectivePrimedTNT>of(selectivePrimedTNT::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F).fireImmune().clientTrackingRange(8).build("tnt_selective"));

    public static final RegistryObject<EntityType<claymorePrimedTNT>> TNT_CLAYMORE =
            ENTITY_TYPES.register("tnt_claymore", () -> EntityType.Builder.<claymorePrimedTNT>of(claymorePrimedTNT::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F).fireImmune().clientTrackingRange(8).build("tnt_claymore"));

    public static final RegistryObject<EntityType<enderPrimedTNT>> TNT_ENDER =
            ENTITY_TYPES.register("tnt_ender", () -> EntityType.Builder.<enderPrimedTNT>of(enderPrimedTNT::new, MobCategory.MISC)
                    .sized(0.98F, 0.7F).fireImmune().clientTrackingRange(8).build("tnt_ender"));

    public static final RegistryObject<EntityType<tntArrow>> ARROW_TNT =
            ENTITY_TYPES.register("arrow_tnt", () -> EntityType.Builder.<tntArrow>of(tntArrow::new, MobCategory.MISC)
                    .sized(0.48F, 0.48F).clientTrackingRange(64).build("arrow_tnt"));

    public static final RegistryObject<EntityType<concussiveArrow>> ARROW_CONCUSSIVE =
            ENTITY_TYPES.register("arrow_concussive", () -> EntityType.Builder.<concussiveArrow>of(concussiveArrow::new, MobCategory.MISC)
                    .sized(0.48F, 0.48F).clientTrackingRange(64).build("arrow_concussive"));

    public static final RegistryObject<EntityType<carpetArrow>> ARROW_CARPET =
            ENTITY_TYPES.register("arrow_carpet", () -> EntityType.Builder.<carpetArrow>of(carpetArrow::new, MobCategory.MISC)
                    .sized(0.48F, 0.48F).clientTrackingRange(64).build("arrow_carpet"));

    public static final RegistryObject<EntityType<claymoreArrow>> ARROW_CLAYMORE =
            ENTITY_TYPES.register("arrow_claymore", () -> EntityType.Builder.<claymoreArrow>of(claymoreArrow::new, MobCategory.MISC)
                    .sized(0.48F, 0.48F).clientTrackingRange(64).build("arrow_claymore"));

    public static final RegistryObject<EntityType<dynamite>> DYNAMITE =
            ENTITY_TYPES.register("dynamite", () -> EntityType.Builder.<dynamite>of(dynamite::new, MobCategory.MISC)
                    .sized(0.48F, 0.48F).clientTrackingRange(64).build("dynamite"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    public static void registerRenderers () {
        EntityRenderers.register(TNT_STRONGER.get(), BaseTNTRenderer::new);
        EntityRenderers.register(TNT_HOMING.get(), BaseTNTRenderer::new);
        EntityRenderers.register(TNT_BLACK_HOLE.get(), BaseTNTRenderer::new);
        EntityRenderers.register(TNT_CLAYMORE.get(), BaseTNTRenderer::new);
        EntityRenderers.register(TNT_SELECTIVE.get(), BaseTNTRenderer::new);
        EntityRenderers.register(TNT_ENDER.get(), BaseTNTRenderer::new);

        EntityRenderers.register(TNT_CLUSTER.get(), clusterTNTRenderer::new);
        EntityRenderers.register(DYNAMITE.get(), clusterTNTRenderer::new);

        EntityRenderers.register(ARROW_TNT.get(), TNTArrowRenderer::new);
        EntityRenderers.register(ARROW_CONCUSSIVE.get(), TNTArrowRenderer::new);
        EntityRenderers.register(ARROW_CARPET.get(), TNTArrowRenderer::new);
        EntityRenderers.register(ARROW_CLAYMORE.get(), baseArrowRenderer::new);
    }
}