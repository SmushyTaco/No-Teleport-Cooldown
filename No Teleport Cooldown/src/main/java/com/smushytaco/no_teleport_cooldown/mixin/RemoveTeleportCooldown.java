package com.smushytaco.no_teleport_cooldown.mixin;
import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.smushytaco.no_teleport_cooldown.NoTeleportCooldown;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import java.util.Map;
@Mixin(ItemCooldownManager.class)
public abstract class RemoveTeleportCooldown {
    @WrapWithCondition(method = "set", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    @SuppressWarnings("unused")
    private <K, V> boolean hookSetPut(Map<K, V> instance, K key, V value, Item item, int duration) { return (item != Items.ENDER_PEARL || !NoTeleportCooldown.INSTANCE.getConfig().isEnderPearlCooldownDisabled()) && (item != Items.CHORUS_FRUIT || !NoTeleportCooldown.INSTANCE.getConfig().isChorusFruitCooldownDisabled()); }
    @WrapWithCondition(method = "set", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ItemCooldownManager;onCooldownUpdate(Lnet/minecraft/item/Item;I)V"))
    @SuppressWarnings("unused")
    private boolean hookSetOnCooldownUpdate(ItemCooldownManager instance, Item item, int duration, Item itemTwo, int durationTwo) { return (itemTwo != Items.ENDER_PEARL || !NoTeleportCooldown.INSTANCE.getConfig().isEnderPearlCooldownDisabled()) && (itemTwo != Items.CHORUS_FRUIT || !NoTeleportCooldown.INSTANCE.getConfig().isChorusFruitCooldownDisabled()); }
}