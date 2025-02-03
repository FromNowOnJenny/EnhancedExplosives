package com.jenny.enhancedexplosives.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

public class items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> TNT_ARROW = ITEMS.register("arrow_tnt", () -> new ArrowTNT(new Item.Properties()));
    public static final RegistryObject<Item> CONCUSSIVE_ARROW = ITEMS.register("arrow_concussive", () -> new ArrowConcussive(new Item.Properties()));
    public static final RegistryObject<Item> CARPET_ARROW = ITEMS.register("arrow_carpet", () -> new ArrowCarpet(new Item.Properties()));
    public static final RegistryObject<Item> TUNNEL_ARROW =  ITEMS.register("arrow_tunnel", () -> new ArrowTunnel(new Item.Properties()));
    public static final RegistryObject<Item> DYNAMITE = ITEMS.register("dynamite", () -> new Dynamite(new Item.Properties()));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
