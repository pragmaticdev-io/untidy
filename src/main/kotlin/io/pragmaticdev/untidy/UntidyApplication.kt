package io.pragmaticdev.untidy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UntidyApplication

fun main(args: Array<String>) {
    runApplication<UntidyApplication>(*args)
}
