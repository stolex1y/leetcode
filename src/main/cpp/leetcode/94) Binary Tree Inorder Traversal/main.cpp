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
// Non-recursive optimal
class Solution {
public:
    vector<int> inorderTraversal(TreeNode *root) {
        vector<int> result;
        stack<TreeNode *> nodes;
        TreeNode *curr = root;
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
};

// Non-recursive
class Solution2 {
public:
    vector<int> inorderTraversal(TreeNode *root) {
        vector<int> result;
        if (!root)
            return result;
        stack<TreeNode *> next;
        next.emplace(root);
        unordered_map<TreeNode *, int> visits;
        while (!next.empty()) {
            const auto node = next.top();
            visits[node]++;
            const int visit_count = visits[node];
            if (visit_count == 1) {
                if (node->left)
                    next.emplace(node->left);
            } else if (visit_count == 2) {
                result.emplace_back(node->val);
                if (node->right)
                    next.emplace(node->right);
            } else {
                visits.erase(node);
                next.pop();
            }
        }
        return result;
    }
};

// Recursive
class Solution3 {
public:
    vector<int> inorderTraversal(TreeNode *root) {
        vector<int> values;
        inorder_traversal(root, values);
        return values;
    }

private:

    void inorder_traversal(TreeNode *root, vector<int> &values) {
        if (!root)
            return;
        inorder_traversal(root->left, values);
        values.emplace_back(root->val);
        inorder_traversal(root->right, values);
    }
};

class LRUCache {
public:
    using key = int;
    using value = int;
    using cache_entry = pair<key, value>;

    explicit LRUCache(int capacity) : capacity(capacity) {}

    int get(key key) {
        if (not_contains(key)) {
            return -1;
        }
        auto entry_ptr = entry_pointers[key];
        update_cache_entry(entry_ptr);
        const auto value = entry_ptr->first;
        return value;
    }

    void put(int key, int value) {
        if (contains(key)) {
            update_cache_entry(entry_pointers[key]);
        } else {
            cache.emplace_front(key, value);
            entry_pointers[key] = cache.begin();
        }
        if (cache.size() > capacity) {
            remove_lru();
        }
    }

private:
    using cache_type = list<cache_entry>;
    using iterator = cache_type::iterator;

    cache_type cache;
    unordered_map<key, iterator> entry_pointers;
    int capacity;

    void update_cache_entry(iterator &ptr) {
        const auto entry = *ptr;
        cache.erase(ptr);
        cache.emplace_front(entry);
        entry_pointers[entry.first] = cache.begin();
    }

    void remove_lru() {
        const auto lru = cache.back();
        entry_pointers[lru.first];
        cache.pop_back();
    }

    bool contains(key key) const {
        return entry_pointers.contains(key);
    }

    bool not_contains(key key) const {
        return !contains(key);
    }

};

int main() {
//    TreeNode tree({1, nullopt, 2, 3});
//    cout << Solution().inorderTraversal(&tree);

    list<int> l;
    l.emplace_back(1);
    auto last = l.begin();
    const auto n1 = last;
    l.emplace_back(2);
    l.emplace_front(2);
    cout << *it << " " << *l.begin();
}