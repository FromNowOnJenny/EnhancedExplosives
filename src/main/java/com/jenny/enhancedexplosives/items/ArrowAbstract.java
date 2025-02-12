package com.jenny.enhancedexplosives.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

public abstract class ArrowAbstract extends ArrowItem {
    public ArrowAbstract(Item.Properties properties){
        super(properties);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull TooltipContext pContext, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
        String key = String.format("tooltip.%s.%s", MODID, this);
        MutableComponent toolTip = Component.translatable(key);
        if (!toolTip.getString().equals(key)) {
            pTooltipComponents.add(toolTip.withStyle(ChatFormatting.DARK_BLUE));
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
        }
    }

    @Override
    @NotNull
    public abstract AbstractArrow createArrow(@NotNull Level pLevel, @NotNull ItemStack pStack, @NotNull LivingEntity pShooter);
}
