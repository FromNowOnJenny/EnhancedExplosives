package com.jenny.enhancedexplosives.blocks;

import com.jenny.enhancedexplosives.entities.tnt.homingPrimedTNT;
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

public class homingTNTBlock extends TntBlock {
    public final float pRadius, speed;
    public final int fuseTime;

    public homingTNTBlock(BlockBehaviour.Properties p_57422_, float pRadius, int fuseTime, float speed) {
        super(p_57422_);
        this.pRadius = pRadius;
        this.fuseTime = fuseTime;
        this.speed = speed;
    }

    @Override
    public void onCaughtFire(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter, this.pRadius, this.fuseTime, this.speed);
    }

    @Deprecated
    public static void explode(Level p_57434_, BlockPos p_57435_, float pRadius, int fuseTime, float speed) {
        explode(p_57434_, p_57435_, (LivingEntity)null, pRadius, fuseTime, speed);
    }

    @Deprecated
    private static void explode(Level level, BlockPos blockPos, @Nullable LivingEntity entity, float pRadius, int fuseTime, float speed) {
        if (!level.isClientSide) {
            homingPrimedTNT primedtnt = new homingPrimedTNT(level, (double)blockPos.getX() + (double)0.5F, (double)blockPos.getY(), (double)blockPos.getZ() + (double)0.5F, entity, pRadius, fuseTime, speed);
            level.addFreshEntity(primedtnt);
            level.playSound((Player)null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(entity, GameEvent.PRIME_FUSE, blockPos);
        }
    }

    @Override
    public void wasExploded(Level level, @NotNull BlockPos blockPos, @NotNull Explosion pExplosion) {
        if (!level.isClientSide) {
            homingPrimedTNT primedtnt = new homingPrimedTNT(level, (double) blockPos.getX() + (double) 0.5F, (double) blockPos.getY(), (double) blockPos.getZ() + (double) 0.5F, pExplosion.getSourceMob(), pRadius, fuseTime, speed);
            int i = primedtnt.getFuse();
            primedtnt.setFuse((short) (level.random.nextInt(i / 4) + i / 8));
            level.addFreshEntity(primedtnt);
        }
    }
}
