#include <stack>
#include "graph.h"

static void dfs_visit(
        Node &start_node,
        const std::vector<std::vector<Node *>> &adj_lists,
        int &global_time
) {
    std::stack<Node *> next_nodes;
    next_nodes.push(&start_node);
    start_node.visit();
    while (!next_nodes.empty()) {
        auto node = next_nodes.top();
        if (node->is_finished()) {
            node->finish_time = ++global_time;
            next_nodes.pop();
            continue;
        }
        start_node.discover_time = ++global_time;
        for (const auto adj: adj_lists[node->index]) {
            if (adj->is_not_visited()) {
                next_nodes.push(adj);
                adj->visit();
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

void dfs(
        std::vector<Node> &nodes,
        const std::vector<std::vector<Node *>> &adj_lists
) {
    dfs_init(nodes);
    int time = 0;
    for (auto &node: nodes) {
        if (node.is_not_visited()) {
            dfs_visit(node, adj_lists, time);
        }
    }
}

std::vector<Node> make_nodes(int size) {
    std::vector<Node> nodes;
    nodes.reserve(size);
    for (int i = 0; i < size; i++) {
        nodes.emplace_back(i);
    }
    return nodes;
}

std::vector<std::vector<Node *>> make_adjacency_lists(
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

std::vector<std::vector<Node *>> make_adjacency_lists_from_matrix(
        std::vector<Node> &nodes,
        const std::vector<std::vector<int>> &adj_matrix
) {
    std::vector<std::vector<Node *>> adj_lists(nodes.size());
    for (int i = 0; i < nodes.size(); i++) {
        auto &adj_list = adj_lists[i];
        for (int j = 0; j < nodes.size(); j++) {
            if (j == i)
                continue;
            const int adj = adj_matrix[i][j];
            if (adj == 1)
                adj_list.push_back(&nodes[adj]);
        }
    }
    return adj_lists;
}

std::vector<std::vector<Node *>> reverse_adj_lists(
        std::vector<Node> &nodes,
        const std::vector<std::vector<Node *>> &adj_lists
) {
    std::vector<std::vector<Node *>> reversed(adj_lists.size());
    for (auto &s: nodes) {
        for (const auto t: adj_lists[s.index]) {
            reversed[t->index].push_back(&s);
        }
    }
    return reversed;
}

std::vector<int> count_node_degrees(
        const std::vector<std::vector<Node *>> &adj_lists
) {
    std::vector<int> degrees(adj_lists.size());
    for (int i = 0; i < adj_lists.size(); i++) {
        degrees[i] = adj_lists[i].size();
    }
    return degrees;
}

static void topological_sort_visit(
        Node &start_node,
        const std::vector<std::vector<Node *>> &adj_lists,
        int &global_time,
        std::deque<Node *> &sorted
) {
    std::stack<Node *> next_nodes;
    next_nodes.push(&start_node);
    start_node.visit();
    while (!next_nodes.empty()) {
        auto node = next_nodes.top();
        if (node->is_finished()) {
            node->finish_time = ++global_time;
            next_nodes.pop();
            sorted.push_front(node);
            continue;
        }
        start_node.discover_time = ++global_time;
        for (const auto adj: adj_lists[node->index]) {
            if (adj->is_not_visited()) {
                next_nodes.push(adj);
                adj->visit();
            }
        }
        node->finish();
    }
}

std::vector<Node *> topological_sort(
        std::vector<Node> &nodes,
        const std::vector<std::vector<Node *>> &adj_lists
) {
    dfs_init(nodes);
    int time = 0;
    std::deque<Node *> sorted;
    for (auto &node: nodes) {
        if (node.is_not_visited()) {
            topological_sort_visit(node, adj_lists, time, sorted);
        }
    }
    return {sorted.begin(), sorted.end()};
}
