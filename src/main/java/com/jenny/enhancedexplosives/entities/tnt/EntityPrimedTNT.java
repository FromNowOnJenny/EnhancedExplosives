package com.jenny.enhancedexplosives.entities.tnt;

import com.jenny.enhancedexplosives.blocks.blocks;
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
import java.util.Objects;

public class EntityPrimedTNT extends basePrimedTNT {
    static class noEntityExplosionCalculator extends ExplosionDamageCalculator {
        public boolean shouldBlockExplode(@NotNull Explosion pExplosion, @NotNull BlockGetter pReader, @NotNull BlockPos pPos, @NotNull BlockState pState, float pPower) {
            return false;
        }
    }

    public EntityPrimedTNT(Level pLevel, double pX, double pY, double pZ, @Nullable LivingEntity pOwner, float power, int fuse) {
        super(entities.TNT_ENTITY.get(), pLevel, pOwner, new Vec3(pX, pY, pZ), fuse, power);
    }

    public EntityPrimedTNT(EntityType<EntityPrimedTNT> entityType, Level level) {
        super(entityType, level, null);
    }
    @Override
    protected void explode() {
        noEntityExplosionCalculator dmgCalc = new noEntityExplosionCalculator();
        this.level().explode(this, null, dmgCalc, position(), getPower(), false, Level.ExplosionInteraction.TNT);
    }

    public BlockPos getBlockBelow() {
        return new BlockPos((int) Math.floor(getX()), (int) Math.floor(getY()) - 1, (int) Math.floor(getZ()));
    }

    public String getBlock() {
        return level().getBlockState(getBlockBelow()).getBlock().toString();
    }

    @Override
    public Block renderBlock() {
        return blocks.TNT_ENTITY.get();
    }
}
