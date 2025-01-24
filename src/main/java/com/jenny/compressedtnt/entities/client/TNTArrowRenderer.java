package com.jenny.compressedtnt.entities.client;

import com.jenny.compressedtnt.entities.arrows.tntArrow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import org.jetbrains.annotations.NotNull;

public class TNTArrowRenderer extends EntityRenderer<tntArrow> {
    private final BlockRenderDispatcher blockRenderer;
    private float i = 0;

    public TNTArrowRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0.0F;
        this.blockRenderer = pContext.getBlockRenderDispatcher();
    }

    public void render(@NotNull tntArrow pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, 0.5F, 0.0F);
        pPoseStack.scale(0.5f, 0.5f, 0.5f);
        i += 0.1f;
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        pPoseStack.translate(-0.5F, -0.5F, 0.5F);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, Blocks.TNT.defaultBlockState(), pPoseStack, pBuffer, pPackedLight, Math.round(Math.sin(i)) == 0);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @NotNull
    public ResourceLocation getTextureLocation(@NotNull tntArrow pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
