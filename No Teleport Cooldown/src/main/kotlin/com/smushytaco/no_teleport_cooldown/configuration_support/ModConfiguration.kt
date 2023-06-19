package com.smushytaco.no_teleport_cooldown.configuration_support
import com.smushytaco.no_teleport_cooldown.NoTeleportCooldown
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment
@Config(name = NoTeleportCooldown.MOD_ID)
class ModConfiguration: ConfigData {
    @Comment("Default value is yes. If set to yes the ender pearl cooldown will be disabled. If set to no it won't be.")
    val isEnderPearlCooldownDisabled = true
    @Comment("Default value is yes. If set to yes the chorus fruit cooldown will be disabled. If set to no it won't be.")
    val isChorusFruitCooldownDisabled = true
}