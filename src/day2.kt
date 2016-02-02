package day2

import java.io.File

fun main(args: Array<String>) {
    if (args.size < 1) error("no input")
    if ("test" == args.first()) {
        test()
    } else {
        val list = File(args.first()).readLines().map {
            val dimension = it.trim().split("x").map { it.toInt() }
            Dim(dimension[0], dimension[1], dimension[2])
        }
        val square = sumSurface(list)
        println("square is $square")

        val ribbonLength = sumLength(list)
        println("ribbon length is $ribbonLength")
    }
}

fun test() {
    assert(sumSurface(listOf(Dim(2, 3, 4))) == 58)
    assert(sumSurface(listOf(Dim(1, 1, 10))) == 43)

    assert(sumLength(listOf(Dim(2, 3, 4))) == 34)
    assert(sumLength(listOf(Dim(1, 1, 10))) == 14)
    print("tests OK")
}

fun sumSurface(list: List<Dim>): Int =
        list.sumBy {
            val lw = it.l * it.w
            val wh = it.w * it.h
            val hl = it.h * it.l
            2 * (lw + wh + hl) + (listOf(lw, wh, hl).min()!!)
        }

fun sumLength(list: List<Dim>): Int =
        list.sumBy {
            val bow = it.l * it.w * it.h
            val length = listOf(it.l, it.w, it.h).sorted().subList(0, 2).map { it * 2 }.sum()
            length + bow
        }

data class Dim(val l: Int, val w: Int, val h: Int)