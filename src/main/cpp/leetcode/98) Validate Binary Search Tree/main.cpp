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

// Recursive
class Solution {
public:
    bool isValidBST(TreeNode* root) {
        return is_valid_bst(root);
    }

private:
    bool is_valid_bst(TreeNode *root, long long min = LONG_LONG_MIN, long long max = LONG_LONG_MAX) {
        if (root == nullptr)
            return true;
        return root->val < max && root->val > min &&
            is_valid_bst(root->left, min, std::min(max, static_cast<long long>(root->val))) &&
                is_valid_bst(root->right, std::max(min, static_cast<long long>(root->val)), max);
    }
};

// Recursive
class Solution2 {
public:
    bool isValidBST(TreeNode* root) {
        queue<tuple<TreeNode *, long long, long long>> next;
        next.emplace(root, LONG_LONG_MIN, LONG_LONG_MAX);
        while (!next.empty()) {
            const auto layer_size = next.size();
            for (int i = 0; i < layer_size; i++) {
                const auto [node, min, max] = next.front();
                next.pop();
                if (node->val > min && node->val < max) {
                    if (node->left) {
                        next.emplace(node->left, min, std::min(max, static_cast<long long>(node->val)));
                    }
                    if (node->right) {
                        next.emplace(node->right, std::max(min, static_cast<long long>(node->val)), max);
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }
};