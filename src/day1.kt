fun main(args: Array<String>) {
    if (args.size < 1) error("no input")
    if ("test" == args.first()) {
        test()
    } else {
        val floor = findFloor(args.first())
        println("floor is $floor")

        val basementPos = basementPosition(args.first())
        println("basement position is $basementPos")
    }
}

fun test() {
    assert(findFloor("(())") == 0)
    assert(findFloor("()()") == 0)
    assert(findFloor("(((") == 3)
    assert(findFloor("(()(()(") == 3)
    assert(findFloor("())") == -1)
    assert(findFloor("))(") == -1)
    assert(findFloor(")))") == -3)
    assert(findFloor(")))") == -3)
    assert(findFloor(")())())") == -3)

    assert(basementPosition(")") == 1)
    assert(basementPosition("()())") == 5)
    print("tests OK")
}

fun findFloor(seq: String): Int {
    var floor = 0;
    seq.forEachIndexed { i, c ->
        when (c) {
            '(' -> floor++
            ')' -> floor--
        }
    }
    return floor
}

fun basementPosition(seq:String):Int {
    var floor = 0;
    seq.forEachIndexed { i, c ->
        when (c) {
            '(' -> floor++
            ')' -> floor--
        }
        if(floor==-1) return i+1
    }

    return -1
}