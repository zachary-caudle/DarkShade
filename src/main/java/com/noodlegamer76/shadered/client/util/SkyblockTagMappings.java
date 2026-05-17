package com.noodlegamer76.shadered.client.util;

import com.noodlegamer76.shadered.Shadered;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.EnumMap;
import java.util.Map;

public class SkyblockTagMappings {

    public enum SkyRenderLayer {
        ECLIPSE("eclipse", ModRenderTypes.ECLIPSE),
        SPACE("space", ModRenderTypes.SPACE),
        OCEAN("ocean", ModRenderTypes.OCEAN),
        STORMY("stormy", ModRenderTypes.STORMY),
        END("end", ModRenderTypes.END_BLOCK),
        END_SKY("end_sky", ModRenderTypes.END_SKY);

        private final String id;
        private final net.minecraft.client.renderer.RenderType renderType;

        SkyRenderLayer(String id, net.minecraft.client.renderer.RenderType renderType) {
            this.id = id;
            this.renderType = renderType;
        }

        public String id() {
            return id;
        }

        public net.minecraft.client.renderer.RenderType renderType() {
            return renderType;
        }
    }

    private static final Map<SkyRenderLayer, TagKey<Block>> SKYBLOCK_TAGS = new EnumMap<>(SkyRenderLayer.class);

    static {
        for (SkyRenderLayer type : SkyRenderLayer.values()) {
            SKYBLOCK_TAGS.put(type, TagKey.create(Registries.BLOCK, new ResourceLocation(Shadered.MODID, "skyblock/" + type.id())));
        }
    }

    public static TagKey<Block> getTag(SkyRenderLayer type) {
        return SKYBLOCK_TAGS.get(type);
    }
}
