package com.jenny.enhancedexplosives.items;

import com.jenny.enhancedexplosives.entities.arrows.baseArrow;
import com.jenny.enhancedexplosives.entities.entities;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import static com.jenny.enhancedexplosives.EnhancedExplosives.MODID;
import static com.jenny.enhancedexplosives.creativeTab.CREATIVE_MODE_TAB;

public class items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> TNT_ARROW = ITEMS.register("arrow_tnt", () -> new ArrowTNT(new Item.Properties()));
    public static final RegistryObject<Item> CONCUSSIVE_ARROW = ITEMS.register("arrow_concussive", () -> new ArrowConcussive(new Item.Properties().tab(CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> CARPET_ARROW = ITEMS.register("arrow_carpet", () -> new ArrowCarpet(new Item.Properties().tab(CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> TUNNEL_ARROW = ITEMS.register("arrow_tunnel", () -> new ArrowTunnel(new Item.Properties().tab(CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> DYNAMITE = ITEMS.register("dynamite", () -> new Dynamite(new Item.Properties().tab(CREATIVE_MODE_TAB)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    public static void registerDispenser() {
        class ArrowDispenseBehaviour extends AbstractProjectileDispenseBehavior {
            private final EntityType<? extends baseArrow> arrowType;

            public ArrowDispenseBehaviour(EntityType<? extends baseArrow> arrow) {
                this.arrowType = arrow;
            }

            @Override
            protected @NotNull Projectile getProjectile(@NotNull Level pLevel, @NotNull Position pPosition, @NotNull ItemStack pStack) {
                baseArrow arrow = arrowType.create(pLevel);
                assert arrow != null;
                arrow.setPos(pPosition.x(), pPosition.y(), pPosition.z());
                arrow.pickup = AbstractArrow.Pickup.ALLOWED;
                return arrow;
            }
        }

        DispenserBlock.registerBehavior(TNT_ARROW.get(), new ArrowDispenseBehaviour(entities.ARROW_TNT.get()));
        DispenserBlock.registerBehavior(CONCUSSIVE_ARROW.get(), new ArrowDispenseBehaviour(entities.ARROW_CONCUSSIVE.get()));
        DispenserBlock.registerBehavior(CARPET_ARROW.get(), new ArrowDispenseBehaviour(entities.ARROW_CARPET.get()));
        DispenserBlock.registerBehavior(TUNNEL_ARROW.get(), new ArrowDispenseBehaviour(entities.ARROW_TUNNEL.get()));
    }
}
