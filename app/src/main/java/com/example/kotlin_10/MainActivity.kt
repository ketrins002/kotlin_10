package com.example.kotlin_10
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        launch {
            delay(1000)
            println("World")
        }

        val sequentialExecutionTime = measureTimeMillis {
            val number1 = getNumber1()
            val number2 = getNumber2()
            println("Sequential sum: ${number1 + number2}")
        }

        val asyncExecutionTime = measureTimeMillis {
            val deferredNumber1 = async { getNumber1() }
            val deferredNumber2 = async { getNumber2() }

            val number1 = deferredNumber1.await()
            val number2 = deferredNumber2.await()

            println("Async sum: ${number1 + number2}")
        }

        println("Sequential execution time: $sequentialExecutionTime ms")
        println("Async execution time: $asyncExecutionTime ms")

        repeat(3) {
            println("I'm sleeping $it ...")
            delay(1000)
        }
        println("main: I'm tired of waiting! I'm running finally.")
        println("main: Now I can quit.")
    }
}

suspend fun getNumber1(): Int {
    delay(2000)
    return 9
}

suspend fun getNumber2(): Int {
    delay(1500)
    return 10
}
