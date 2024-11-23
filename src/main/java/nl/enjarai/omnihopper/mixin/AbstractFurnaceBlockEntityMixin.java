package nl.enjarai.omnihopper.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SidedStorageBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nl.enjarai.omnihopper.OmniHopper;
import nl.enjarai.omnihopper.util.FurnaceFuelBucketStorage;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = AbstractFurnaceBlockEntity.class, priority = 1200)
public abstract class AbstractFurnaceBlockEntityMixin extends LockableContainerBlockEntity implements SidedStorageBlockEntity {
    protected AbstractFurnaceBlockEntityMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Shadow public abstract void setStack(int slot, ItemStack stack);

    @Unique
    private final FurnaceFuelBucketStorage fluidStorage = new FurnaceFuelBucketStorage() {
        @Override
        protected ItemStack getFuelStack() {
            return AbstractFurnaceBlockEntityMixin.this.getStack(1);
        }

        @Override
        protected void setFuelStack(ItemStack fuelItem) {
            AbstractFurnaceBlockEntityMixin.this.setStack(1, fuelItem);
        }
    };

    @Override
    public @Nullable Storage<FluidVariant> getFluidStorage(Direction side) {
        if (side == null || side.getAxis().isHorizontal()) {
            return fluidStorage;
        }
        return null;
    }

    @ModifyExpressionValue(
            method = "canExtract",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            )
    )
    private boolean removeExtractionExceptions(boolean original) {
        if (getWorld() instanceof ServerWorld serverWorld && serverWorld.getGameRules().getBoolean(OmniHopper.REMOVE_FURNACE_EXCEPTIONS)) {
            return false;
        }
        return original;
    }
}
