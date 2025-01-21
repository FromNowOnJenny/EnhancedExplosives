package com.jenny.compressedtnt.entities;

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
                    .sized(2.5f, 2.5f).build("tnt_homing"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
