package com.jenny.compressedtnt.entities.client;

import com.jenny.compressedtnt.blocks.blocks;
import com.jenny.compressedtnt.entities.basePrimedTNT;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class BaseTNTRenderer extends EntityRenderer<basePrimedTNT> {
    private final BlockRenderDispatcher blockRenderer;

    public BaseTNTRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.shadowRadius = 0.5F;
        this.blockRenderer = pContext.getBlockRenderDispatcher();
    }

    public void render(basePrimedTNT pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.0F, 0.5F, 0.0F);
        int i = pEntity.getFuse();
        if ((float)i - pPartialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float)i - pPartialTicks + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f *= f;
            f *= f;
            float f1 = 1.0F + f * 0.3F;
            pPoseStack.scale(f1, f1, f1);
        }
        String renderID = pEntity.getRenderID();
        if (renderID == null) {
            renderID = "";
        }
        Block block = switch (renderID) {
            case "stronger_8" -> blocks.TNT_8.get();
            case "stronger_16" -> blocks.TNT_16.get();
            case "stronger_32" -> blocks.TNT_32.get();
            case "stronger_64" -> blocks.TNT_64.get();
            case "stronger_128" -> blocks.TNT_128.get();
            case "homing" -> blocks.TNT_HOMING.get();
            default -> Blocks.NETHER_PORTAL; // placeholder for debugging
        };

        pPoseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
        pPoseStack.translate(-0.5F, -0.5F, 0.5F);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(this.blockRenderer, block.defaultBlockState(), pPoseStack, pBuffer, pPackedLight, i / 5 % 2 == 0);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @NotNull
    public ResourceLocation getTextureLocation(@NotNull basePrimedTNT pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
