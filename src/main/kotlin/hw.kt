class FibIterator<T>(val n: Int):Iterator<Int> {
    override fun hasNext(): Boolean {
        return f1 <= n
    }
    var f1 = 0
    var f2 = 1
    var f3 = 1
    override fun next(): Int {
        f3 = f1 + f2
        f1 = f2
        f2 = f3
        return f1
    }
}

open class FibIterable(val n: Int): Iterable<Int>{

    override fun iterator(): FibIterator<Int> {
        return FibIterator<Int>(n)
    }
}


class FibCollection(n: Int, override val size: Int): Collection<Int>, FibIterable(n) {

    override fun contains(element: Int): Boolean {
        var iter = FibIterable(n)
        while (iter.iterator().hasNext()) {

        }
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<Int>): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        FibIterable(n).count() == 0
        TODO("Not yet implemented")
    }
}


fun main() {
    val n = readLine()!!.toInt()
    println("${FibIterable(n).count()}")
}