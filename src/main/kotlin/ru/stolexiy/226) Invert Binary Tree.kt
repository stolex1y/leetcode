package ru.stolexiy

private fun invertTree(root: TreeNode?): TreeNode? {
    root?.let {
        val temp = it.left
        it.left = it.right
        it.right = temp
        invertTree(it.left)
        invertTree(it.right)
    }
    return root
}