package com.jenny.enhancedexplosives.blocks;

import com.jenny.enhancedexplosives.entities.tnt.ClusterPrimedTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ClusterTNTBlock extends TntBlock {
    public final float pRadius;
    public final int fuseTime;
    public final int childCount;
    public final int childRange;

    public ClusterTNTBlock(BlockBehaviour.Properties p_57422_, float pRadius, int fuseTime, int childCount, int childRange) {
        super(p_57422_);
        this.pRadius = pRadius;
        this.fuseTime = fuseTime;
        this.childCount = childCount;
        this.childRange = childRange;
    }

    @Override
    public void onCaughtFire(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter, this.pRadius, this.fuseTime, this.childCount, this.childRange);
    }

    @Deprecated
    public static void explode(Level p_57434_, BlockPos p_57435_, float pRadius, int fuseTime, int childCount, int childRange) {
        explode(p_57434_, p_57435_, (LivingEntity)null, pRadius, fuseTime, childCount, childRange);
    }

    public static Vec3 getMove(Level level, int childRange) {
        RandomSource rng = level.getRandom();
        float offsetX = (float) rng.nextInt(- childRange, childRange + 1) / 15;
        float offsetZ = (float) rng.nextInt(- childRange, childRange + 1) / 15;
        return new Vec3(offsetX, 0,  offsetZ);
    }

    @Deprecated
    private static void explode(Level level, BlockPos blockPos, @Nullable LivingEntity entity, float pRadius, int fuseTime, int childCount, int childRange) {
        if (!level.isClientSide) {
            for (int i = 0; i < childCount; i++) {
                ClusterPrimedTNT primedtnt = new ClusterPrimedTNT(level, (double) blockPos.getX() + (double) 0.5F, (double) blockPos.getY(), (double) blockPos.getZ() + (double) 0.5F, entity, pRadius, fuseTime, getMove(level, childRange));
                level.addFreshEntity(primedtnt);
                level.gameEvent(entity, GameEvent.PRIME_FUSE, blockPos);
            }
            level.playSound((Player) null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void wasExploded(Level level, @NotNull BlockPos blockPos, @NotNull Explosion pExplosion) {
        if (!level.isClientSide) {
            for (int i = 0; i < childCount; i++) {
                int ft = (short) (level.random.nextInt(fuseTime / 4) + fuseTime / 8);
                ClusterPrimedTNT primedtnt = new ClusterPrimedTNT(level, (double) blockPos.getX() + (double) 0.5F, (double) blockPos.getY(), (double) blockPos.getZ() + (double) 0.5F, pExplosion.getIndirectSourceEntity(), pRadius, ft, getMove(level, childRange));
                level.addFreshEntity(primedtnt);
            }
        }
    }
}
