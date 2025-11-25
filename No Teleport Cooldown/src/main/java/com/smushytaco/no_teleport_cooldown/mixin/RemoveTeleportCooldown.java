package com.smushytaco.no_teleport_cooldown.mixin;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.smushytaco.no_teleport_cooldown.NoTeleportCooldown;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Map;
@Mixin(ItemCooldownManager.class)
public abstract class RemoveTeleportCooldown {
    @WrapOperation(method = "set(Lnet/minecraft/util/Identifier;I)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private <K, V> V hookSetPut(Map<K, V> instance, K key, V value, Operation<V> original, Identifier groupId, int duration) {
        if ((groupId != Registries.ITEM.getId(Items.ENDER_PEARL) || !NoTeleportCooldown.INSTANCE.getConfig().isEnderPearlCooldownDisabled()) && (groupId != Registries.ITEM.getId(Items.CHORUS_FRUIT) || !NoTeleportCooldown.INSTANCE.getConfig().isChorusFruitCooldownDisabled())) return original.call(instance, key, value);
        return null;
    }
    @WrapWithCondition(method = "set(Lnet/minecraft/util/Identifier;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ItemCooldownManager;onCooldownUpdate(Lnet/minecraft/util/Identifier;I)V"))
    private boolean hookSetOnCooldownUpdate(ItemCooldownManager instance, Identifier groupId, int duration, Identifier groupIdTwo, int durationTwo) { return (groupIdTwo != Registries.ITEM.getId(Items.ENDER_PEARL) || !NoTeleportCooldown.INSTANCE.getConfig().isEnderPearlCooldownDisabled()) && (groupIdTwo != Registries.ITEM.getId(Items.CHORUS_FRUIT) || !NoTeleportCooldown.INSTANCE.getConfig().isChorusFruitCooldownDisabled()); }
}