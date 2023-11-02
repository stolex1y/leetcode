#include <vector>
#include <array>
#include <queue>
#include <memory>
#include <iostream>
#include <algorithm>
#include <numeric>
#include <map>
#include <set>
#include <optional>
#include <list>
#include <stack>
#include <unordered_set>
#include <cassert>
#include <valarray>
#include <charconv>

#include "container_utils.h"
#include "tree_node.h"

using namespace std;

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
// iterative
class Solution {
public:
    bool isSymmetric(TreeNode *root) {
        if (!root)
            return true;

        deque<TreeNode *> cur_level;
        deque<TreeNode *> next_level;
        visit_node(root, cur_level);
        while (!cur_level.empty()) {
            if (cur_level.size() % 2 != 0)
                return false;
            for (auto node: cur_level) {
                visit_node(node, next_level);
            }
            while (!cur_level.empty()) {
                const auto left = cur_level.front();
                cur_level.pop_front();
                const auto right = cur_level.back();
                cur_level.pop_back();

                if (left == nullptr ^ right == nullptr)
                    return false;
                if (left != right && left->val != right->val)
                    return false;
            }
            swap(cur_level, next_level);
        }
        return true;
    }

private:
    static void visit_node(const TreeNode *node, deque<TreeNode *> &next) {
        if (node) {
            next.emplace_back(node->left);
            next.emplace_back(node->right);
        }
    }
};

//recursive
class Solution2 {
public:
    bool isSymmetric(TreeNode *root) {
        return is_symmetric(root, root);
    }

private:
    bool is_symmetric(TreeNode *left, TreeNode *right) {
        if (left == nullptr ^ right == nullptr)
            return false;
        if (!left && !right)
            return true;
        if (left->val != right->val)
            return false;
        return is_symmetric(left->left, right->right) && is_symmetric(left->right, right->left);
    }
};

int main() {
    TreeNode tree = TreeNode({2, 3, 3, 4, 5, 5, 4, nullopt, nullopt, 8, 9, 9, 8});
    cout << Solution().isSymmetric(&tree);
}