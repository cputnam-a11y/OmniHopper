package nl.enjarai.omnihopper.blocks.entity.hopper.behaviour;

import net.minecraft.util.Identifier;
import nl.enjarai.omnihopper.blocks.entity.hopper.HopperBlockEntity;

public class VanillaHopperBehaviour extends ItemHopperBehaviour {
    public static final Identifier TYPE_ID = Identifier.of("hopper");

    public VanillaHopperBehaviour(HopperBlockEntity<?> blockEntity) {
        super(TYPE_ID, blockEntity);
    }
}
