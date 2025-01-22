package com.jenny.compressedtnt;

import com.jenny.compressedtnt.blocks.blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.jenny.compressedtnt.Compressedtnt.MODID;

public class creativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("compressedtnt", () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> blocks.TNT_8_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(blocks.TNT_8.get());
        output.accept(blocks.TNT_16.get());
        output.accept(blocks.TNT_32.get());
        output.accept(blocks.TNT_64.get());
        output.accept(blocks.TNT_128.get());
        output.accept(blocks.TNT_CLUSTER_2.get());
        output.accept(blocks.TNT_CLUSTER_4.get());
        output.accept(blocks.TNT_CLUSTER_8.get());
        output.accept(blocks.TNT_HOMING.get());
        output.accept(blocks.TNT_BLACK_HOLE.get());
    }).title(Component.literal("Compressed TNT")).build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
