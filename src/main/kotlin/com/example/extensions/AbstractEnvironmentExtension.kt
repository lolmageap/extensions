package com.example.extensions

import org.springframework.core.env.AbstractEnvironment

val AbstractEnvironment.isLocal: Boolean
    get() = activeProfiles.contains("local")

val AbstractEnvironment.isDev: Boolean
    get() = activeProfiles.contains("dev")

val AbstractEnvironment.isProd: Boolean
    get() = activeProfiles.contains("prod")

val AbstractEnvironment.isTest: Boolean
    get() = activeProfiles.contains("test")