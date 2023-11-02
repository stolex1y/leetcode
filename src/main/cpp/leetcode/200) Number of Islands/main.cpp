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

class Solution {
public:
    using size = vector<vector<char>>::size_type;

    size numIslands(vector<vector<char>> &matrix) {
        auto grid = make_grid(matrix);
        return count_connected_components(grid);
    }

private:
    struct node_type;
    using grid_type = vector<vector<node_type>>;
    using coordinate = size;

    enum class node_form : char {
        WATER = '0',
        LAND = '1'
    };

    struct node_type {
        coordinate x;
        coordinate y;
        node_form form;
        bool visited = false;

        node_type(coordinate x, coordinate y, node_form form) : x(x), y(y), form(form) {}

        void visit() {
            visited = true;
        }

        bool is_not_visited() const {
            return !visited;
        }
    };

    static grid_type make_grid(vector<vector<char>> matrix) {
        const auto height = matrix.size();
        const auto width = matrix[0].size();
        grid_type grid(height);
        for (size y = 0; y < height; y++) {
            auto &row = grid[y];
            row.reserve(width);
            for (size x = 0; x < width; x++) {
                row.emplace_back(x, y, static_cast<node_form>(matrix[y][x]));
            }
        }
        return grid;
    }

    static void find_adjacent(const node_type &node, grid_type &grid, vector<node_type *> &adjacent) {
        const coordinate x = node.x;
        const coordinate y = node.y;
        const auto width = grid[0].size();
        const auto height = grid.size();
        if (x > 0 && grid[y][x - 1].form == node_form::LAND) {
            adjacent.emplace_back(&grid[y][x - 1]);
        }
        if (x < width - 1 && grid[y][x + 1].form == node_form::LAND) {
            adjacent.emplace_back(&grid[y][x + 1]);
        }
        if (y > 0 && grid[y - 1][x].form == node_form::LAND) {
            adjacent.emplace_back(&grid[y - 1][x]);
        }
        if (y < height - 1 && grid[y + 1][x].form == node_form::LAND) {
            adjacent.emplace_back(&grid[y + 1][x]);
        }
    }

    static void dfs_visit(
            node_type &start_node,
            grid_type &grid
    ) {
        stack<node_type *> next_nodes;
        next_nodes.push(&start_node);
        start_node.visited = true;
        vector<node_type *> adjacent;
        while (!next_nodes.empty()) {
            const auto node = next_nodes.top();
            next_nodes.pop();
            find_adjacent(*node, grid, adjacent);
            for (const auto adj: adjacent) {
                if (adj->is_not_visited()) {
                    next_nodes.push(adj);
                    adj->visit();
                }
            }
        }
    }

    static size count_connected_components(
            grid_type &grid
    ) {
        size count = 0;
        for (auto &row: grid) {
            for (auto &node: row) {
                if (node.form == node_form::LAND && node.is_not_visited()) {
                    dfs_visit(node, grid);
                    count++;
                }
            }
        }
        return count;
    }
};

int main() {
    vector<vector<char>> grid = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
    };
    cout << Solution().numIslands(grid);
}