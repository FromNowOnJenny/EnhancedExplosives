package com.jenny.enhancedexplosives;

import com.jenny.enhancedexplosives.blocks.blocks;

import com.jenny.enhancedexplosives.items.items;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

public class creativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("enhancedexplosives", () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.SPAWN_EGGS).icon(() -> blocks.TNT_8_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.acceptAll(Arrays.stream(getBlocks()).toList());
        output.acceptAll(Arrays.stream(getItems()).toList());
    }).title(Component.literal("Enhanced Explosives")).build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }

    public static ItemStack[] getBlocks() {
        ItemStack[] ret = new ItemStack[blocks.ITEMS.getEntries().size()];
        int i = 0;
        for (RegistryObject<Item> item : blocks.ITEMS.getEntries()) {
            ret[i] = item.get().getDefaultInstance();
            i++;
        }
        return ret;
    }

    public static ItemStack[] getItems() {
        ItemStack[] ret = new ItemStack[items.ITEMS.getEntries().size()];
        int i = 0;
        for (RegistryObject<Item> item : items.ITEMS.getEntries()) {
            ret[i] = item.get().getDefaultInstance();
            i++;
        }
        return ret;
    }
}
