#include <vector>
#include <array>
#include <cassert>
#include <algorithm>
#include <queue>
#include <iostream>

#include "TreeNode.h"

using namespace std;

class Solution {
public:
    vector<int> rightSideView(TreeNode *root) {
        vector<int> result;
        queue<TreeNode *> queue;
        queue.push(root);
        while (!queue.empty()) {
            size_t levelSize = queue.size();
            for (size_t i = 1; i <= levelSize; i++) {
                auto node = queue.front();
                queue.pop();
                if (node->left)
                    queue.push(node->left);
                if (node->right)
                    queue.push(node->right);
                if (i == levelSize)
                    result.push_back(node->val);
            }
        }
        return result;
    }
};

int main() {
    TreeNode tree = TreeNode(
            1,
            new TreeNode(2, nullptr, new TreeNode(5)),
            new TreeNode(3, nullptr, new TreeNode(4))
    );
    auto result = Solution().rightSideView(&tree);
    std::for_each(result.begin(), result.end(), [](auto i){ cout << i << " ";});
}

