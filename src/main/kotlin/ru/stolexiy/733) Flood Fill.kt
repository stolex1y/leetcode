package ru.stolexiy

fun floodFill(image: Array<IntArray>, sr: Int, sc: Int, color: Int): Array<IntArray> {
    val resultImage = image.copyOf()
    for (i in resultImage.indices) {
        resultImage[i] = image[i].copyOf()
    }
    val sourceColor = image[sr][sc]
    if (color == sourceColor)
        return resultImage

    val queue = ArrayDeque<Pair<Int, Int>>()
    queue += sr to sc
    while (queue.isNotEmpty()) {
        val currPixel = queue.removeFirst()
        if (resultImage[currPixel.first][currPixel.second] == color)
            continue
        resultImage[currPixel.first][currPixel.second] = color
        putChildrenInQueue(resultImage, currPixel, sourceColor, queue)
    }
    return resultImage
}

fun putChildrenInQueue(image: Array<IntArray>, source: Pair<Int, Int>, color: Int, queue: ArrayDeque<Pair<Int, Int>>) {
    Direction.values().forEach {
        val coords = source + it.dir
        val pixel = image.getOrNull(coords.first)?.getOrNull(coords.second)
        if (pixel != null && pixel == color)
            queue.addFirst(coords)
    }
}