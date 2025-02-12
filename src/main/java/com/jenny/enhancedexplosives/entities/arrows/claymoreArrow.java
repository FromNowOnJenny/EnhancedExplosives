package com.jenny.enhancedexplosives.entities.arrows;

import com.jenny.enhancedexplosives.config.ConfigServer;
import com.jenny.enhancedexplosives.entities.entities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class claymoreArrow extends baseArrow{
    private final int r = level().isClientSide ? 0 : (ConfigServer.claymoreInstantDespawn.get() ? -20 : level().getRandom().nextInt(0, 20));
    
    public claymoreArrow(EntityType<claymoreArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public claymoreArrow(@NotNull  Level pLevel, @NotNull LivingEntity pShooter) {
        super(pLevel, pShooter, entities.ARROW_CLAYMORE.get());
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity pTarget) {
        pTarget.hurt(damageSources().mobProjectile(this, (LivingEntity) getOwner()), 15);
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide && this.inGroundTime > 20 + r) {
            discard();
        }
    }
}
