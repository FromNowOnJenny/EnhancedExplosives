package com.jenny.enhancedexplosives;

import com.jenny.enhancedexplosives.blocks.blocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class creativeTab {
    public static final CreativeModeTab CREATIVE_MODE_TAB = new CreativeModeTab("enhancedexplosives") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(blocks.TNT_8_ITEM.get());
        }
    };
}
