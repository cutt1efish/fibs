class FibIterator<T>(val n: Int):Iterator<Int> {
    override fun hasNext(): Boolean {
        return f2 <= n
    }
    var f1 = 1 //изначально минус первый элемент
    var f2 = 0
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

//______________________________________________________________________________________________________________________

open class FibCollection(n: Int, override val size: Int = FibIterable(n).count()): Collection<Int>, FibIterable(n){

    var f1 = 0
    var f2 = 1
    var f3 = 1
    override fun contains(element: Int): Boolean {
        while (f1 <= n) {
            if (f1 == element) return true
            f1 = f2
            f2 = f3
            f3 = f2 + f1
        }
        return false

    }

    override fun containsAll(elements: Collection<Int>): Boolean {
        for (i in elements) {
            if (i != f1) return false
            f1 = f2
            f2 = f3
            f3 = f2 + f1
        }
        return true
    }

    override fun isEmpty(): Boolean {
        return FibIterable(n).count() == 0
    }
}

//______________________________________________________________________________________________________________________

class FibList(n: Int, override val size: Int = FibIterable(n).count()): List<Int>, FibCollection(n) {
    override fun get(index: Int): Int {
        f1 = 0
        f2 = 1
        f3 = 1
        for (i in 0..index) {
            f1 = f2
            f2 = f3
            f3 = f1 + f2
        }
        val ans = f1
        f1 = 0
        f2 = 1
        f3 = 1
        return ans
    }

    override fun indexOf(element: Int): Int {
        val index = -1
        for (i in 0..size) {
            if (get(i) == element) return i
        }
        return index
    }

    override fun lastIndexOf(element: Int): Int {
        var index = -1
        for (i in 0..size) {
            if (get(i) == element) index = i
        }
        return index
    }

    override fun listIterator(): ListIterator<Int> {
        return listIterator(0)
    }

    override fun listIterator(index: Int): ListIterator<Int> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<Int> {
        TODO("Not yet implemented")
    }

}

//______________________________________________________________________________________________________________________

fun main() {
    val n = readLine()!!.toInt()
    println(FibIterable(n).count())
    for (i in FibCollection(n)) print("$i ")
}