package ru.stolexiy

private fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode? {
    var cur: TreeNode? = root
    var p: TreeNode? = root
    while (cur != null) {
        p = cur
        cur = if (`val` < cur.`val`)
            cur.left
        else
            cur.right
    }
    p?.let { addingParent ->
        if (`val` < addingParent.`val`)
            addingParent.left = TreeNode(`val`)
        else
            addingParent.right = TreeNode(`val`)
    } ?: return TreeNode(`val`)
    return root
}