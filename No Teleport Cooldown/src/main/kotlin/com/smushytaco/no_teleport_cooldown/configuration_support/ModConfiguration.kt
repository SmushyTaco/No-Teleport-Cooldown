package com.smushytaco.no_teleport_cooldown.configuration_support
import com.smushytaco.no_teleport_cooldown.NoTeleportCooldown
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment
@Config(name = NoTeleportCooldown.MOD_ID)
class ModConfiguration: ConfigData {
    @Comment("Default value is yes. If set to yes the mod will be enabled. If set to no it won't.")
    val isEnabled = true
}