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
#include <tree_node.h>

#include "container_utils.h"

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
class Solution {
public:
    int maxDepth(TreeNode* root) {
        return max_depth(root, 0);
    }

private:
    static int max_depth(TreeNode *root, int cur_depth) {
        if (!root)
            return cur_depth;
        return max(max_depth(root->left, cur_depth + 1), max_depth(root->right, cur_depth + 1));
    }
};

class Solution2 {
public:
    int maxDepth(TreeNode* root) {
        size_t level_count = 0;
        queue<TreeNode *> next_level_nodes;
        next_level_nodes.emplace(root);

        while (!next_level_nodes.empty()) {
            const size_t level_size = next_level_nodes.size();
            level_count++;
            for (size_t i = 0; i < level_size; i++) {
                const auto node = next_level_nodes.front();
                next_level_nodes.pop();

                if (node->left)
                    next_level_nodes.emplace(node->left);
                if (node->right)
                    next_level_nodes.emplace(node->right);
            }
        }
        return level_count;
    }
};