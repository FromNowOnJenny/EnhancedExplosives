package com.jenny.compressedtnt.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.jenny.compressedtnt.Compressedtnt.MODID;

public class BlockItemTooltip extends BlockItem {
    public BlockItemTooltip(Block pBlock, Item.Properties pProperties){
        super(pBlock, pProperties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        String key = String.format("tooltip.%s.%s", MODID, this);
        MutableComponent toolTip = Component.translatable(key);
        if (!toolTip.getString().equals(key)) {
            pTooltipComponents.add(toolTip.withStyle(ChatFormatting.DARK_BLUE));
            super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        }
    }
}
