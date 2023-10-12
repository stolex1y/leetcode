#ifndef UTILS_GRAPH_H
#define UTILS_GRAPH_H

#include <functional>
#include <string>
#include <list>
#include <vector>

void dfs(const std::vector<std::list<int>> &adj_lists);

struct Node {
public:
    int index;
    int discover_time = 0;
    int finish_time = 0;

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
        discover_time = 0;
        finish_time = 0;
    }

private:
    enum Color {
        WHITE = 0,
        GREY = 1,
        BLACK = 2
    };

    Color color = WHITE;
};

#endif //UTILS_GRAPH_H
