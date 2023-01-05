package ru.stolexiy

private fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    var cur = root
    while (cur != null && cur.`val` != `val`) {
        cur = if (`val` < cur.`val`)
            cur.left
        else
            cur.right
    }
    return cur
}