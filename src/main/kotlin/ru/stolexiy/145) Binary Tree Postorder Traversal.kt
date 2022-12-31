package ru.stolexiy

private fun postorderTraversal(root: TreeNode?): List<Int> {
    return mutableListOf<Int>().apply { postorderTraversal(root, this) }
}

private fun postorderTraversal(node: TreeNode?, list: MutableList<Int>) {
    if (node != null) {
        postorderTraversal(node.left, list)
        postorderTraversal(node.right, list)
        list += node.`val`
    }
}