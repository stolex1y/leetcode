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

class Solution {
public:
    int findCircleNum(vector<vector<int>> &isConnected) {
        DisjointSetForest forest(isConnected.size());
        for_each_edge(isConnected, [&forest](const auto edge) {
            forest.unite(edge.first, edge.second);
        });
        return forest.connected_components_count();
    }

private:
    class DisjointSetForest {
    private:
        class Node {
            Node *parent = nullptr;
            int rank = 0;
            int value;

        public:
            explicit Node(int value) : value(value) {}

            Node &find_root() {
                list<Node *> prev;
                auto current = this;
                while (current->parent != nullptr) {
                    prev.push_back(current);
                    current = current->parent;
                }
                const auto root = current;
                for (const auto prev_node: prev) {
                    prev_node->parent = root;
                }
                return *root;
            }

            void unite(Node &other) {
                auto &first_root = find_root();
                auto &second_root = other.find_root();
                if (first_root == second_root)
                    return;
                if (second_root.rank < first_root.rank)
                    second_root.parent = &first_root;
                else {
                    first_root.parent = &second_root;
                    if (first_root.rank == second_root.rank)
                        second_root.rank++;
                }
            }

            bool operator==(Node &other) {
                return this == &other;
            }

            int get_value() {
                return value;
            }
        };

        vector<Node> nodes;

    public:
        explicit DisjointSetForest(int size) {
            nodes.reserve(size);
            for (int i = 0; i < size; i++) {
                nodes.emplace_back(i);
            }
        }

        void unite(int i, int j) {
            nodes[i].unite(nodes[j]);
        }

        int connected_components_count() {
            vector<bool> visits(nodes.size());
            int count = 0;
            for (auto &node: nodes) {
                int root = node.find_root().get_value();
                if (!visits[root]) {
                    count++;
                    visits[root] = true;
                }
            }
            return count;
        }
    };

    struct Edge {
        int first;
        int second;
    };

    static void for_each_edge(
            const vector<vector<int>> &adjacency_lists,
            const function<void(Edge)> &fun
    ) {
        for (int i = 0; i < adjacency_lists.size(); i++) {
            const auto &adj_list = adjacency_lists[i];
            for (int j = i + 1; j < adj_list.size(); j++) {
                if (adj_list[j] == 1)
                    fun({.first = i, .second = j});
            }
        }
    }
};

class Solution2 {
public:
    int findCircleNum(vector<vector<int>> &isConnected) {
        vector<Node> nodes = make_nodes(isConnected.size());
        vector<list<Node *>> adj_lists = make_adjacency_lists(nodes, isConnected);
        return dfs(nodes, adj_lists);
    }

private:
    struct Node {
    public:
        int index;

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
        }

    private:
        enum Color {
            WHITE = 0,
            GREY = 1,
            BLACK = 2
        };

        Color color = WHITE;
    };

    static void dfs_visit(const std::vector<std::list<Node *>> &adj_lists, Node &start_node, int &global_time) {
        std::stack<Node *> next_nodes;
        next_nodes.push(&start_node);
        start_node.visit();
        while (!next_nodes.empty()) {
            auto node = next_nodes.top();
            next_nodes.pop();
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

    static int dfs(std::vector<Node> &nodes, const std::vector<std::list<Node *>> &adj_lists) {
        dfs_init(nodes);
        int time = 0;
        int count = 0;
        for (auto &node: nodes) {
            if (node.is_not_visited()) {
                dfs_visit(adj_lists, node, time);
                count++;
            }
        }
        return count;
    }

    std::vector<Node> make_nodes(int size) {
        std::vector<Node> nodes;
        nodes.reserve(size);
        for (int i = 0; i < size; i++) {
            nodes.emplace_back(i);
        }
        return nodes;
    }

    std::vector<std::list<Node *>> make_adjacency_lists(
            std::vector<Node> &nodes,
            const std::vector<std::vector<int>> &adj_matrix
    ) {
        std::vector<std::list<Node *>> adj_lists(nodes.size());
        for (int i = 0; i < nodes.size(); i++) {
            auto &adj_list = adj_lists[i];
            for (int j = 0; j < nodes.size(); j++) {
                if (j == i)
                    continue;
                const int adj = adj_matrix[i][j];
                if (adj == 1)
                    adj_list.push_back(&nodes[j]);
            }
        }
        return adj_lists;
    }
};

int main() {
    vector<vector<int>> adj_matrix{{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
    cout << Solution2().findCircleNum(adj_matrix);
}