var firstBad = 1

fun firstBadVersion(n: Int) : Int {
    var start = 1
    var end = n
    var mid: Int
    while (end > start) {
        mid = ((end.toLong() + start.toLong()) / 2).toInt()
        if (isBadVersion(mid)) {
            end = mid
        } else
            start = mid + 1
    }
    return start
}

fun isBadVersion(n: Int): Boolean {
    return n >= firstBad
}