package com.noodlegamer76.shadered.event;

import com.noodlegamer76.shadered.Shadered;
import com.noodlegamer76.shadered.block.InitBlocks;
import com.noodlegamer76.shadered.client.util.ModRenderTypes;
import com.noodlegamer76.shadered.client.util.SkyblockTagMappings;
import com.noodlegamer76.shadered.mixin.accessor.ItemBlockRenderTypesAccessor;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterNamedRenderTypesEvent;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

@Mod.EventBusSubscriber(modid = Shadered.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onNamedRenderTypes(RegisterNamedRenderTypesEvent event) {
        event.register("eclipse", ModRenderTypes.ECLIPSE, Sheets.chestSheet());
        event.register("space", ModRenderTypes.SPACE, Sheets.chestSheet());
        event.register("ocean", ModRenderTypes.OCEAN, Sheets.chestSheet());
        event.register("stormy", ModRenderTypes.STORMY, Sheets.chestSheet());
        event.register("end", ModRenderTypes.END_BLOCK, Sheets.chestSheet());
        event.register("end_sky", ModRenderTypes.END_SKY, Sheets.chestSheet());
    }

    @Mod.EventBusSubscriber(modid = Shadered.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ForgeClientEvents {
        @SubscribeEvent
        public static void onTagsUpdated(TagsUpdatedEvent event) {
            applySkyblockRenderTypes();
        }
    }

    public static void applySkyblockRenderTypes() {
        Map<Block, RenderType> typeByBlock = ItemBlockRenderTypesAccessor.shaderedGetTypeByBlock();

        typeByBlock.put(InitBlocks.ECLIPSE_BLOCK.get(), ModRenderTypes.ECLIPSE);
        typeByBlock.put(InitBlocks.SPACE_BLOCK.get(), ModRenderTypes.SPACE);
        typeByBlock.put(InitBlocks.OCEAN_BLOCK.get(), ModRenderTypes.OCEAN);
        typeByBlock.put(InitBlocks.STORMY_BLOCK.get(), ModRenderTypes.STORMY);
        typeByBlock.put(InitBlocks.END_BLOCK.get(), ModRenderTypes.END_BLOCK);
        typeByBlock.put(InitBlocks.END_SKY_BLOCK.get(), ModRenderTypes.END_SKY);

        for (SkyblockTagMappings.SkyRenderLayer type : SkyblockTagMappings.SkyRenderLayer.values()) {
            for (Block block : net.minecraftforge.registries.ForgeRegistries.BLOCKS.tags().getTag(SkyblockTagMappings.getTag(type))) {
                typeByBlock.put(block, type.renderType());
            }
        }
    }
}
