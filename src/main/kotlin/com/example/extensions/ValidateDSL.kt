package com.example.extensions


fun validate(function: Validator.() -> Unit) {
    val validator = Validator()
    validator.function()
}


class Validator {
    fun <T: Any> T?.isNull(message: () -> Unit): T {
        if (this == null)
            throw IllegalArgumentException(message.toString())

        return this
    }

}
