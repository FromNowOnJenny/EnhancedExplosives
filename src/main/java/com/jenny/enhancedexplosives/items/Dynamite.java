package com.jenny.enhancedexplosives.items;

import com.jenny.enhancedexplosives.entities.throwable.dynamite;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;

public class Dynamite extends Item {
    public Dynamite(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    @NotNull
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!pLevel.isClientSide) {
            dynamite d = new dynamite(pLevel, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), pPlayer, 4, 80);
            d.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), -20.0F, 0.5F);
            pLevel.addFreshEntity(d);
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
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
}
