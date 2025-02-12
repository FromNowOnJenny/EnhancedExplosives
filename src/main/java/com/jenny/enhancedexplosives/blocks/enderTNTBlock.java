package com.jenny.enhancedexplosives.blocks;

import com.jenny.enhancedexplosives.config.ConfigClient;
import com.jenny.enhancedexplosives.entities.tnt.enderPrimedTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class enderTNTBlock extends TntBlock {
    public final float pRadius;
    public final int fuseTime;

    public enderTNTBlock(Properties p_57422_, float pRadius, int fuseTime) {
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
        explode(p_57434_, p_57435_, null, pRadius, fuseTime);
    }

    @Deprecated
    private static void explode(Level level, BlockPos blockPos, @Nullable LivingEntity entity, float pRadius, int fuseTime) {
        if (!level.isClientSide) {
            BlockPos pos = getSpawnPos(level, blockPos);
            if (pos != null) {
                enderPrimedTNT primedtnt = new enderPrimedTNT(level, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, entity, pRadius, fuseTime);
                level.addFreshEntity(primedtnt);
                level.playSound(null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(entity, GameEvent.PRIME_FUSE, blockPos);
            }
        }
        else {
            spawnParticles(level, blockPos);
        }
    }

    private static BlockPos getSpawnPos(Level level, BlockPos blockPos) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        for (int i = 0; i < 5; i++) {
            BlockPos pos = new BlockPos(
                    x + level.getRandom().nextInt(-16, 17),
                    y + level.getRandom().nextInt(-16, 17),
                    z + level.getRandom().nextInt(-16, 17));
            if (level.isEmptyBlock(pos)) {
                int ymax = level.getHeight(Heightmap.Types.WORLD_SURFACE, pos.getX(), pos.getZ());
                if (ymax < pos.getY()) {
                    return new BlockPos(pos.getX(), ymax, pos.getZ());
                }
                else {
                    return pos;
                }
            }
        }
        return null;
    }

    @Override
    public void wasExploded(Level level, @NotNull BlockPos blockPos, @NotNull Explosion pExplosion) {
        if (!level.isClientSide) {
            BlockPos pos = getSpawnPos(level, blockPos);
            if (pos != null) {
                enderPrimedTNT primedtnt = new enderPrimedTNT(level, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, pExplosion.getIndirectSourceEntity(), pRadius, fuseTime);
                int i = primedtnt.getFuse();
                primedtnt.setFuse((short) (level.random.nextInt(i / 4) + i / 8));
                level.addFreshEntity(primedtnt);
            }
        }
        else {
            spawnParticles(level, blockPos);
        }
    }

    public static void spawnParticles(Level level, BlockPos blockPos) {
        if (ConfigClient.tntParticles.get()) {
            for (int i = 1; i <= ConfigClient.calcPCount(30); i++) {
                float x = blockPos.getX() + (float) level.getRandom().nextIntBetweenInclusive(-10, 10) / 10 + 0.5F;
                float y = blockPos.getY() + (float) level.getRandom().nextIntBetweenInclusive(-10, 10) / 10;
                float z = blockPos.getZ() + (float) level.getRandom().nextIntBetweenInclusive(-10, 10) / 10 + 0.5F;
                level.addParticle(ParticleTypes.GLOW, x, y, z, 0, 0, 0);
            }
        }
    }
}
