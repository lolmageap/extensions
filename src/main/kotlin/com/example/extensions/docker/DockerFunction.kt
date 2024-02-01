package com.example.extensions.docker


fun dockerFile(function: DockerFile.() -> Unit): DockerFile {
    val dockerFile = DockerFile()
    dockerFile.function()
    dockerFile.build()
    return dockerFile
}

@DockerDSL
fun DockerFile.build() {
    dockerFile[0] = "FROM $image"
    dockerFile.forEach {
        println(it)
    }
    TODO("""
        실제로 도커 파일을 만드는 로직을 작성 해야 합니다.
        Gradle 에서 run 을 실행 하면 도커 파일을 만들고 실행 해야 합니다.
        """)
}