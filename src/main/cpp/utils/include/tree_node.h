#ifndef LEETCODE_TREENODE_H
#define LEETCODE_TREENODE_H

#include <optional>

#include "container_utils.h"

struct TreeNode {
    int val = 0;
    TreeNode *left = nullptr;
    TreeNode *right = nullptr;

    TreeNode() = default;

    TreeNode(int x) : val(x) {}

    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}

    TreeNode(std::vector<std::optional<int>> values) {
        std::queue<TreeNode *> next_root;
        this->val = values[0].value();
        next_root.emplace(this);
        for (int i = 1; i < values.size(); i++) {
            const auto root = next_root.front();
            next_root.pop();
            const auto left_value = values[i++];
            std::optional<int> right_value;
            if (i < values.size())
                right_value = values[i];
            else
                right_value = std::nullopt;
            if (left_value.has_value()) {
                root->left = new TreeNode(left_value.value());
                next_root.emplace(root->left);
            }
            if (right_value.has_value()) {
                root->right = new TreeNode(right_value.value());
                next_root.emplace(root->right);
            }
        }
    }

    friend std::ostream &operator<<(std::ostream &out, const TreeNode &tree) {
        return out << inorderTraversal(&tree);
    }

private:
    static std::vector<int> inorderTraversal(const TreeNode *root) {
        using namespace std;

        vector<int> result;
        stack<const TreeNode *> nodes;
        const TreeNode *curr = root;
        while (true) {
            if (curr) {
                nodes.emplace(curr);
                curr = curr->left;
            } else {
                if (nodes.empty())
                    break;
                curr = nodes.top();
                nodes.pop();
                result.emplace_back(curr->val);
                curr = curr->right;
            }
        }
        return result;
    }

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
