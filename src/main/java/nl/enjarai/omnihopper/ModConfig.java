package nl.enjarai.omnihopper;

import net.fabricmc.loader.api.FabricLoader;
import nl.enjarai.cicada.api.util.AbstractModConfig;

public class ModConfig extends AbstractModConfig {
    public static ModConfig INSTANCE = loadConfigFile(
            FabricLoader.getInstance().getConfigDir().resolve("omnihopper-server.json"), new ModConfig());

    public boolean removeFurnaceExtractionExceptions = true;
}
