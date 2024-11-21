package nl.enjarai.omnihopper.blocks.entity.hopper.behaviour;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import nl.enjarai.omnihopper.blocks.entity.hopper.HopperBlockEntity;
import org.jetbrains.annotations.Nullable;

public abstract class HopperBehaviour<T> {
    protected final HopperBlockEntity<?> blockEntity;
    private final Identifier hopperType;
    private final BlockApiLookup<Storage<T>, Direction> blockApiLookup;

    protected HopperBehaviour(Identifier hopperType, BlockApiLookup<Storage<T>, Direction> blockApiLookup, HopperBlockEntity<?> blockEntity) {
        this.hopperType = hopperType;
        this.blockEntity = blockEntity;
        this.blockApiLookup = blockApiLookup;
    }

    public Identifier getHopperType() {
        return hopperType;
    }

    public abstract Storage<T> getStorage();

    public final BlockApiLookup<Storage<T>, Direction> getBlockApiLookup() {
        return blockApiLookup;
    }

    public abstract void writeNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup);

    public abstract void readNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup);

    public long getAmountPerActivation(BlockState targetState) {
        return 1;
    }

    public int getCooldown() {
        return 8;
    }

    public boolean pickupInWorldObjects(World world, BlockPos pos, Direction suckyDirection) {
        return false;
    }

    @Nullable
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return null;
    }

    public ActionResult onUseWithItem(PlayerEntity player, Hand hand, BlockHitResult hit) {
        return ActionResult.PASS_TO_DEFAULT_BLOCK_ACTION;
    }
}
