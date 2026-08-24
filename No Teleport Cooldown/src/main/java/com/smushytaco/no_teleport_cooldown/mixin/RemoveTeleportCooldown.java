package com.smushytaco.no_teleport_cooldown.mixin;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.smushytaco.no_teleport_cooldown.NoTeleportCooldown;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Map;
@Mixin(ItemCooldowns.class)
public abstract class RemoveTeleportCooldown {
    @WrapOperation(method = "addCooldown(Lnet/minecraft/resources/Identifier;I)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private <K, V> V hookSetPut(Map<K, V> instance, K key, V value, Operation<V> original, Identifier cooldownGroup, int time) {
        if ((cooldownGroup != BuiltInRegistries.ITEM.getKey(Items.ENDER_PEARL) || !NoTeleportCooldown.INSTANCE.getConfig().isEnderPearlCooldownDisabled()) && (cooldownGroup != BuiltInRegistries.ITEM.getKey(Items.CHORUS_FRUIT) || !NoTeleportCooldown.INSTANCE.getConfig().isChorusFruitCooldownDisabled())) return original.call(instance, key, value);
        return null;
    }
    @WrapWithCondition(method = "addCooldown(Lnet/minecraft/resources/Identifier;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemCooldowns;onCooldownStarted(Lnet/minecraft/resources/Identifier;I)V"))
    private boolean hookSetOnCooldownUpdate(ItemCooldowns instance, Identifier cooldownGroup, int duration, @SuppressWarnings("NameDoesntMatchTargetClass") Identifier cooldownGroupTwo, int time) { return (cooldownGroupTwo != BuiltInRegistries.ITEM.getKey(Items.ENDER_PEARL) || !NoTeleportCooldown.INSTANCE.getConfig().isEnderPearlCooldownDisabled()) && (cooldownGroupTwo != BuiltInRegistries.ITEM.getKey(Items.CHORUS_FRUIT) || !NoTeleportCooldown.INSTANCE.getConfig().isChorusFruitCooldownDisabled()); }
}