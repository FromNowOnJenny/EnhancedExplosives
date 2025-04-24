package com.jenny.enhancedexplosives.blocks;

import com.jenny.enhancedexplosives.entities.tnt.StrongerPrimedTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class strongerTNTBlock extends TntBlock {
    public final float pRadius;
    public final int fuseTime;

    public strongerTNTBlock(BlockBehaviour.Properties p_57422_, float pRadius, int fuseTime) {
        super(p_57422_);
        this.pRadius = pRadius;
        this.fuseTime = fuseTime;
    }

    @Override
    public void onCaughtFire(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter, this.pRadius, this.fuseTime);
    }

    @Deprecated
    public static void explode(Level p_57434_, BlockPos p_57435_, float pRadius, int fuseTime) {
        explode(p_57434_, p_57435_, (LivingEntity)null, pRadius, fuseTime);
    }

    @Deprecated
    private static void explode(Level p_57437_, BlockPos p_57438_, @Nullable LivingEntity p_57439_, float pRadius, int fuseTime) {
        if (!p_57437_.isClientSide) {
            StrongerPrimedTNT primedtnt = new StrongerPrimedTNT(p_57437_, (double)p_57438_.getX() + (double)0.5F, (double)p_57438_.getY(), (double)p_57438_.getZ() + (double)0.5F, p_57439_, pRadius, fuseTime);
            p_57437_.addFreshEntity(primedtnt);
            p_57437_.playSound((Player)null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            p_57437_.gameEvent(p_57439_, GameEvent.PRIME_FUSE, p_57438_);
        }

    }

    @Override
    public void wasExploded(Level level, @NotNull BlockPos blockPos, @NotNull Explosion pExplosion) {
        if (!level.isClientSide) {
            int ft = (short) (level.random.nextInt(fuseTime / 4) + fuseTime / 8);
            StrongerPrimedTNT primedtnt = new StrongerPrimedTNT(level, (double) blockPos.getX() + (double) 0.5F, (double) blockPos.getY(), (double) blockPos.getZ() + (double) 0.5F, pExplosion.getSourceMob(), pRadius, ft);
            level.addFreshEntity(primedtnt);
        }
    }
}
