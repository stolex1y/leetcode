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

using namespace std;

struct Node {
public:
    int index;
    bool safe = false;

    explicit Node(int index) : index(index) {}

    bool is_visited() const {
        return color != WHITE;
    }

    bool is_finished() const {
        return color == BLACK;
    }

    bool is_not_visited() const {
        return !is_visited();
    }

    void visit() {
        color = GREY;
    }

    void finish() {
        color = BLACK;
    }

    void reset() {
        color = WHITE;
        safe = false;
    }

private:
    enum Color {
        WHITE = 0,
        GREY = 1,
        BLACK = 2
    };

    Color color = WHITE;
};

static std::vector<std::vector<Node *>> make_adjacency_lists(
        std::vector<Node> &nodes,
        const std::vector<std::vector<int>> &indexed_adj_lists
) {
    std::vector<std::vector<Node *>> adj_lists(nodes.size());
    for (auto s: nodes) {
        for (auto t: indexed_adj_lists[s.index]) {
            adj_lists[s.index].push_back(&nodes[t]);
        }
    }
    return adj_lists;
}

static void check_node_safety(
        Node &node,
        const std::vector<std::vector<Node *>> &adj_lists
) {
    if (!node.safe) {
        if (adj_lists[node.index].empty()) {
            node.safe = true;
        } else {
            bool all_children_safe = std::all_of(
                    adj_lists[node.index].begin(),
                    adj_lists[node.index].end(),
                    [](auto n) { return n->safe; }
            );
            node.safe = all_children_safe;
        }
    }
}

static void dfs_visit(
        Node &start_node,
        const std::vector<std::vector<Node *>> &adj_lists
) {
    std::stack<Node *> next_nodes;
    next_nodes.push(&start_node);
    while (!next_nodes.empty()) {
        auto node = next_nodes.top();

        if (node->is_finished()) {
            check_node_safety(*node, adj_lists);
            next_nodes.pop();
            continue;
        }

        if (node->is_not_visited()) {
            node->visit();
        } else {
            next_nodes.pop();
            continue;
        }

        for (const auto adj: adj_lists[node->index]) {
            if (adj->is_not_visited()) {
                next_nodes.push(adj);
            }
        }
        node->finish();
    }
}

static void dfs_init(std::vector<Node> &nodes) {
    for (auto &node: nodes) {
        node.reset();
    }
}

static std::vector<int> find_safe(
        std::vector<Node> &nodes,
        const std::vector<std::vector<Node *>> &adj_lists
) {
    dfs_init(nodes);
    for (auto &node: nodes) {
        if (node.is_not_visited()) {
            dfs_visit(node, adj_lists);
        }
    }
    vector<int> safes;
    for (auto &v: nodes) {
        if (v.safe)
            safes.push_back(v.index);
    }
    return safes;
}

std::vector<Node> make_nodes(int size) {
    std::vector<Node> nodes;
    nodes.reserve(size);
    for (int i = 0; i < size; i++) {
        nodes.emplace_back(i);
    }
    return nodes;
}

class Solution {
public:
    vector<int> eventualSafeNodes(vector<vector<int>> &graph) {
        vector<Node> nodes = make_nodes(graph.size());
        auto adj_lists = make_adjacency_lists(nodes, graph);
        return find_safe(nodes, adj_lists);
    }
};

class Solution2 {
public:
    vector<int> eventualSafeNodes(vector<vector<int>> &graph) {
        const int n = static_cast<int>(graph.size());
        auto reversed_adj_lists = reverse_adj_lists(graph);
        vector<int> node_degrees(n);
        std::transform(
                graph.begin(),
                graph.end(),
                node_degrees.begin(),
                [](const auto &adj_list) { return adj_list.size(); }
        );
        vector<bool> node_safety(n, false);
        stack<int> to_remove;
        for (int i = 0; i < n; i++) {
            if (node_degrees[i] == 0) {
                to_remove.push(i);
                node_safety[i] = true;
            }
        }
        while (!to_remove.empty()) {
            const int safe_node = to_remove.top();
            to_remove.pop();
            for (auto v : reversed_adj_lists[safe_node]) {
                node_degrees[v]--;
                if (!node_safety[v] && node_degrees[v] == 0) {
                    to_remove.push(v);
                    node_safety[v] = true;
                }
            }
        }
        vector<int> safe_nodes;
        for (int i = 0; i < n; i++) {
            if (node_safety[i])
                safe_nodes.push_back(i);
        }
        return safe_nodes;
    }

private:
    static std::vector<std::vector<int>> reverse_adj_lists(
            const std::vector<std::vector<int>> &adj_lists
    ) {
        std::vector<std::vector<int>> reversed(adj_lists.size());
        for (int s = 0; s < adj_lists.size(); s++) {
            for (const auto t: adj_lists[s]) {
                reversed[t].push_back(s);
            }
        }
        return reversed;
    }
};

int main() {
    vector<vector<int>> graph = {{1, 3, 4, 5},
                                 {},
                                 {},
                                 {},
                                 {},
                                 {2, 4}};
    Solution().eventualSafeNodes(graph);
}
