package com.smushytaco.no_teleport_cooldown.mixin;
import com.smushytaco.no_teleport_cooldown.NoTeleportCooldown;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ItemCooldownManager.class)
public abstract class RemoveTeleportCooldown {
    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    private void hookSet(Item item, int duration, CallbackInfo ci) {
        if ((item == Items.ENDER_PEARL || item == Items.CHORUS_FRUIT) && NoTeleportCooldown.INSTANCE.getConfig().isEnabled()) ci.cancel();
    }
}