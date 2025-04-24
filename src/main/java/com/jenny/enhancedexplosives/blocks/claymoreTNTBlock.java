package com.jenny.enhancedexplosives.blocks;

import com.jenny.enhancedexplosives.entities.tnt.claymorePrimedTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class claymoreTNTBlock extends TntBlock {
    public final float pRadius;
    public final int fuseTime;
    public final int projectileCount;

    public claymoreTNTBlock(Properties p_57422_, float pRadius, int fuseTime, int projectileCount) {
        super(p_57422_);
        this.pRadius = pRadius;
        this.fuseTime = fuseTime;
        this.projectileCount = projectileCount;
    }

    @Override
    public void onCaughtFire(@NotNull BlockState state, @NotNull Level world, @NotNull BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter, this.pRadius, this.fuseTime, this.projectileCount);
    }

    @Deprecated
    public static void explode(Level p_57434_, BlockPos p_57435_, float pRadius, int fuseTime, int projectileCount) {
        explode(p_57434_, p_57435_, (LivingEntity)null, pRadius, fuseTime, projectileCount);
    }

    @Deprecated
    private static void explode(Level level, BlockPos blockPos, @Nullable LivingEntity entity, float pRadius, int fuseTime, int projectileCount) {
        if (!level.isClientSide) {
            claymorePrimedTNT primedtnt = new claymorePrimedTNT(level, (double) blockPos.getX() + (double) 0.5F, (double) blockPos.getY(), (double) blockPos.getZ() + (double) 0.5F, entity, pRadius, fuseTime, projectileCount);
            level.addFreshEntity(primedtnt);
            level.gameEvent(entity, GameEvent.PRIME_FUSE, blockPos);
            level.playSound((Player) null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    @Override
    public void wasExploded(Level level, @NotNull BlockPos blockPos, @NotNull Explosion pExplosion) {
        if (!level.isClientSide) {
            int ft = (short) (level.random.nextInt(fuseTime / 4) + fuseTime / 8);
            claymorePrimedTNT primedtnt = new claymorePrimedTNT(level, (double) blockPos.getX() + (double) 0.5F, (double) blockPos.getY(), (double) blockPos.getZ() + (double) 0.5F, pExplosion.getSourceMob(), pRadius, ft, projectileCount);
            level.addFreshEntity(primedtnt);
        }
    }
}
