#ifndef LEETCODE_TREENODE_H
#define LEETCODE_TREENODE_H

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;

    TreeNode() : val(0), left(nullptr), right(nullptr) {}

    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}

    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}

    /*void insert(int val) {
        TreeNode *pred = nullptr;
        TreeNode *cur = this;
        while (cur != nullptr) {
            pred = cur;
            if (cur->val <= val) {
                cur = cur->left;
            } else {
                cur = cur->right;
            }
        }
        auto addedNode = new TreeNode(val);
        if (val <= pred->val)
            pred->left = addedNode;
        else
            pred->right = addedNode;
    }*/

    /*static TreeNode makeTree(std::vector<int> list) {
        auto root = TreeNode(list.at(0));
        for (size_t i = 1; i < list.size(); i++) {
            root.insert(list.at(i));
        }
        return root;
    }*/
};


#endif //LEETCODE_TREENODE_H
