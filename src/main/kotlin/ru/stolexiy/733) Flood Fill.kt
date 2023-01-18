package ru.stolexiy

private object Solution733 {
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

    private enum class Direction(val dir: Pair<Int, Int>) {
        LEFT(0 to -1),
        RIGHT(0 to 1),
        UP(-1 to 0),
        DOWN(1 to 0)
    }
}