package com.jenny.enhancedexplosives.entities.client;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;

import org.jetbrains.annotations.NotNull;

import static net.minecraft.client.renderer.entity.TippableArrowRenderer.NORMAL_ARROW_LOCATION;

public class baseArrowRenderer extends ArrowRenderer<AbstractArrow> {
    public baseArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @NotNull
    public ResourceLocation getTextureLocation(@NotNull AbstractArrow pEntity) {
        return NORMAL_ARROW_LOCATION;
    }
}
