package com.jenny.enhancedexplosives.entities.tnt;

import com.jenny.enhancedexplosives.entities.entities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class selectivePrimedTNT extends basePrimedTNT {
    static class SelectiveExplosionCalculator extends ExplosionDamageCalculator {
        private final String block;

        public SelectiveExplosionCalculator(String block) {
            this.block = block;
        }

        public boolean shouldBlockExplode(@NotNull Explosion pExplosion, @NotNull BlockGetter pReader, @NotNull BlockPos pPos, BlockState pState, float pPower) {
            return pState.getBlock().toString().equals(block);
        }
    }

    public selectivePrimedTNT(Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse) {
        super(entities.TNT_SELECTIVE.get(), pLevel, pOwner, new Vec3(pX, pY, pZ), fuse, power);
    }

    public selectivePrimedTNT(EntityType<selectivePrimedTNT> entityType, Level level) {
        super(entityType, level, null);
    }
    @Override
    protected void explode() {
        SelectiveExplosionCalculator dmgCalc = new SelectiveExplosionCalculator(getBlock());
        this.level().explode(this, null, dmgCalc, position(), getPower(), false, Level.ExplosionInteraction.TNT);
    }

    public String getBlock() {
        return level().getBlockState(new BlockPos((int) getX(), (int) getY() - 1, (int) getZ())).getBlock().toString();
    }

    @Override
    public Block renderBlock() {
        return level().getBlockState(new BlockPos((int) getX(), (int) getY() - 1, (int) getZ())).getBlock();
    }
 }
