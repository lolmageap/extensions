package com.example.extensions

import com.example.extensions.docker.dockerFile

fun main() {
    dockerFile {
        from { "openjdk:8-jdk-alpine" }
        copy("source", "destination")
        workdir("/app")
        env("key" - "value")
        expose(8080)
        volume("/tmp")
        entrypoint { "java, -jar, app.jar" }
        entrypoint {
            "java"
            "-jar"
            "app.jar"
        }
    }
}
