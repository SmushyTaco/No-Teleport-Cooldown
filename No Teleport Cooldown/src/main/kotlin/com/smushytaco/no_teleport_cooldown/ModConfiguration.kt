package com.smushytaco.no_teleport_cooldown
import io.wispforest.owo.config.Option
import io.wispforest.owo.config.annotation.Config
import io.wispforest.owo.config.annotation.Modmenu
import io.wispforest.owo.config.annotation.Sync
@Modmenu(modId = NoTeleportCooldown.MOD_ID)
@Config(name = NoTeleportCooldown.MOD_ID, wrapperName = "ModConfig")
@Suppress("UNUSED")
class ModConfiguration {
    @JvmField
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    var isEnderPearlCooldownDisabled = true
    @JvmField
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    var isChorusFruitCooldownDisabled = true
}