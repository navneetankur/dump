package test

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

val random = Random(8)
var maxid=0
fun main() {
    runBlocking() {
        (1..3).asFlow().map { delay(1000);it }.collect{println(it)}
        println("ye wala")
    }
    println("done")
}
fun getList(): MutableList<Int> {
    val l = mutableListOf<Int>()
    var lower = random.nextInt(5,100)
    var upper = random.nextInt(5,100)
    if(lower == upper) {
        upper = 50
        lower = 26
    }
    else if(lower > upper)
        upper = lower.also { lower = upper }
    for(i in lower..upper) {
        l.add(i)
    }
    return l
}