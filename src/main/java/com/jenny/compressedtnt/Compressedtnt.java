package com.jenny.compressedtnt;

import com.jenny.compressedtnt.blocks.*;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Compressedtnt.MODID)
public class Compressedtnt {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "compressedtnt";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<strongerTNTBlock> TNT_8 = BLOCKS.register("tnt_8", () -> new strongerTNTBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE), 8.0f, 80));
    public static final RegistryObject<Item> TNT_8_ITEM = ITEMS.register("tnt_8", () -> new BlockItem(TNT_8.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_16 = BLOCKS.register("tnt_16", () -> new strongerTNTBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE), 16.0f, 80));
    public static final RegistryObject<Item> TNT_16_ITEM = ITEMS.register("tnt_16", () -> new BlockItem(TNT_16.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_32 = BLOCKS.register("tnt_32", () -> new strongerTNTBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE), 32.0f, 80));
    public static final RegistryObject<Item> TNT_32_ITEM = ITEMS.register("tnt_32", () -> new BlockItem(TNT_32.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_64 = BLOCKS.register("tnt_64", () -> new strongerTNTBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE), 64.0f, 80));
    public static final RegistryObject<Item> TNT_64_ITEM = ITEMS.register("tnt_64", () -> new BlockItem(TNT_64.get(), new Item.Properties()));

    public static final RegistryObject<Block> TNT_128 = BLOCKS.register("tnt_128", () -> new strongerTNTBlock(BlockBehaviour.Properties.of().mapColor(MapColor.STONE), 128.0f, 80));
    public static final RegistryObject<Item> TNT_128_ITEM = ITEMS.register("tnt_128", () -> new BlockItem(TNT_128.get(), new Item.Properties()));


    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("compressedtnt", () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> TNT_8_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(TNT_8.get());
        output.accept(TNT_16.get());
        output.accept(TNT_32.get());
        output.accept(TNT_64.get());
        output.accept(TNT_128.get());
    }).title(Component.literal("Compressed TNT")).build());

    public Compressedtnt() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}