package nl.enjarai.omnihopper.util;

import net.minecraft.block.Oxidizable;
import net.minecraft.data.client.TextureKey;
import net.minecraft.data.client.TextureMap;
import net.minecraft.util.Identifier;

public interface TextureMapProvider {
    static TextureMap forHopperType(Identifier id) {
        return new TextureMap()
                .put(TextureKey.PARTICLE, getSubId(id, "_side"))
                .put(TextureKey.SIDE, getSubId(id, "_side"))
                .put(TextureKey.TOP, getSubId(id, "_top"))
                .put(TextureKey.BOTTOM, getSubId(id, "_bottom"))
                .put(TextureKey.INSIDE, getSubId(id, "_inside"));
    }

    static TextureMap forOxidizableHopperType(Identifier id, Oxidizable.OxidationLevel degradationLevel) {
        var prefix = switch (degradationLevel) {
            case UNAFFECTED -> "";
            case EXPOSED -> "exposed_";
            case WEATHERED -> "weathered_";
            case OXIDIZED -> "oxidized_";
        };
        return forHopperType(id.withPath(path -> prefix + path));
    }

    static TextureMap forVanillaHopper() {
        return new TextureMap()
                .put(TextureKey.PARTICLE, Identifier.of("block/hopper_outside"))
                .put(TextureKey.SIDE, Identifier.of("block/hopper_outside"))
                .put(TextureKey.TOP, Identifier.of("block/hopper_top"))
                .put(TextureKey.BOTTOM, Identifier.of("block/hopper_outside"))
                .put(TextureKey.INSIDE, Identifier.of("block/hopper_inside"));
    }

    static Identifier getSubId(Identifier id, String suffix) {
        return id.withPath(path -> "block/" + path + suffix);
    }

    TextureMap getTextureMap();
}
