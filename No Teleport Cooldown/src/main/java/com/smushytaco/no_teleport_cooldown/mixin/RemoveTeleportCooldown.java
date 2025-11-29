package com.smushytaco.no_teleport_cooldown.mixin;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.smushytaco.no_teleport_cooldown.NoTeleportCooldown;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Map;
@Mixin(ItemCooldowns.class)
public abstract class RemoveTeleportCooldown {
    @WrapOperation(method = "addCooldown(Lnet/minecraft/resources/ResourceLocation;I)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private <K, V> V hookSetPut(Map<K, V> instance, K key, V value, Operation<V> original, ResourceLocation groupId, int duration) {
        if ((groupId != BuiltInRegistries.ITEM.getKey(Items.ENDER_PEARL) || !NoTeleportCooldown.INSTANCE.getConfig().isEnderPearlCooldownDisabled()) && (groupId != BuiltInRegistries.ITEM.getKey(Items.CHORUS_FRUIT) || !NoTeleportCooldown.INSTANCE.getConfig().isChorusFruitCooldownDisabled())) return original.call(instance, key, value);
        return null;
    }
    @WrapWithCondition(method = "addCooldown(Lnet/minecraft/resources/ResourceLocation;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;onCooldownStarted(Lnet/minecraft/resources/ResourceLocation;I)V"))
    private boolean hookSetOnCooldownUpdate(ItemCooldowns instance, ResourceLocation groupId, int duration, ResourceLocation groupIdTwo, int durationTwo) { return (groupIdTwo != BuiltInRegistries.ITEM.getKey(Items.ENDER_PEARL) || !NoTeleportCooldown.INSTANCE.getConfig().isEnderPearlCooldownDisabled()) && (groupIdTwo != BuiltInRegistries.ITEM.getKey(Items.CHORUS_FRUIT) || !NoTeleportCooldown.INSTANCE.getConfig().isChorusFruitCooldownDisabled()); }
}