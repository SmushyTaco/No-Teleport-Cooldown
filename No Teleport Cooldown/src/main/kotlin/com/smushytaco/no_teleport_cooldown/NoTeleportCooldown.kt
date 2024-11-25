package com.smushytaco.no_teleport_cooldown
import net.fabricmc.api.ModInitializer
object NoTeleportCooldown : ModInitializer {
    const val MOD_ID = "no_teleport_cooldown"
    val config = ModConfig.createAndLoad()
    override fun onInitialize() {}
}