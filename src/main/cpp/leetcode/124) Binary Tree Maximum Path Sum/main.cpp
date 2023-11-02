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
class Solution {
public:
    static int maxPathSum(TreeNode *root) {
        const auto [max_sum, finished_max_sum] = max_path_sum(root);
        return max(max_sum.value_or(0), finished_max_sum.value_or(0));
    }

private:
    static pair<optional<int>, optional<int>> max_path_sum(TreeNode *root) {
        if (!root)
            return make_pair(nullopt, nullopt);
        const auto [left_max, finished_left_max] = max_path_sum(root->left);
        const auto [right_max, finished_right_max] = max_path_sum(root->right);
        const auto max_with_root = get_max_with_root(left_max.value_or(0), root->val, right_max.value_or(0));
        const auto finished_max_with_root = max(
                max(
                        max(left_max.value_or(INT_MIN), finished_left_max.value_or(INT_MIN)),
                        max(right_max.value_or(INT_MIN), finished_right_max.value_or(INT_MIN))
                ),
                left_max.value_or(0) + right_max.value_or(0) + root->val
        );
        return make_pair(max_with_root, finished_max_with_root);
    }

    static int get_max_with_root(int left_sum, int root, int right_sum) {
        return max(
                root,
                max(left_sum + root, root + right_sum)
        );
    }
};

int main() {
    TreeNode root({1, 2, 3});
    cout << Solution::maxPathSum(&root);
}