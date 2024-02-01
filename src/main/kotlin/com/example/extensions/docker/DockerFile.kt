package com.example.extensions.docker

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


@DockerDSL
class DockerFile {
    var dockerFile = mutableListOf<String>()
    private lateinit var currentDirectory: String

    var image: String? by object : ReadWriteProperty<DockerFile, String?> {

        override fun getValue(thisRef: DockerFile, property: KProperty<*>): String {
            if (thisRef.image == null) {
                throw IllegalStateException("Image is not initialized")
            }
            return thisRef.image!!
        }

        override fun setValue(thisRef: DockerFile, property: KProperty<*>, value: String?) {
            if (thisRef.image != null) {
                throw IllegalStateException("Image is already initialized")
            }

            thisRef.image = value
        }
    }

    fun from(init: () -> String) {
        this.image = init()
    }

    fun run(command: String) {
        TODO()
    }

    fun copy(source: String, destination: String) {
        dockerFile.add("COPY $source $destination")
    }

    fun workdir(directory: String) {
        this.currentDirectory = directory
        dockerFile.add("WORKDIR $directory")
    }

    fun entrypoint(command: () -> String) {
        val invoke = command.invoke()
        println("invoke = $invoke")
        dockerFile.add("ENTRYPOINT $invoke")
    }

    fun cmd(vararg command: String) {
        val cmd =  "[ ${command.joinToString(", ")} ]"
        dockerFile.add("CMD $cmd")
    }

    fun expose(port: Int) {
        if (port < 0 || port > 65535) {
            throw IllegalArgumentException("Port must be between 0 and 65535")
        }

        dockerFile.add("EXPOSE $port")
    }

    fun env(environment: Environment) {
        dockerFile.add("ENV ${environment.key}=${environment.value}")
    }

    fun volume(mountPoint: String) {
        dockerFile.add("VOLUME $mountPoint")
    }

    fun arg(key: String, value: String) {
        TODO()
    }

    fun label(key: String, value: String) {
        TODO()
    }

    operator fun String.minus(value: String): Environment {
        return Environment(
            key = this,
            value = value
        )
    }
}
