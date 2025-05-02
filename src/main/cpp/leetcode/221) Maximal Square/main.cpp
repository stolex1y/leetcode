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
    static size_t maximalSquare(vector<vector<char>> &matrix) {
        const auto height = matrix.size();
        if (height == 0)
            return 0;

        const auto width = matrix.front().size();
        if (width == 0)
            return 0;

        vector<size_t> heights(width, 0);

        size_t max_square_area = 0;
        for (size_t row_idx = 0; row_idx < height; ++row_idx) {
            const auto &row = matrix[row_idx];
            update_heights(row, heights);
            max_square_area = max(max_square_area, maximal_square_area(heights));
        }
        return max_square_area;
    }

private:
    static void update_heights(const vector<char> &row, vector<size_t> &heights) {
        for (size_t col_idx = 0; col_idx < row.size(); ++col_idx) {
            const auto &elem = row[col_idx];
            if (elem == '0')
                heights[col_idx] = 0;
            else
                heights[col_idx] += 1;
        }
    }

    static size_t maximal_square_area(const vector<size_t> &heights) {
        const auto size = ssize_t(heights.size());

        if (size == 0)
            return 0;

        vector<ssize_t> left_less(size);
        vector<ssize_t> right_less(size);

        left_less.front() = -1;
        for (ssize_t i = 1; i < size; ++i) {
            ssize_t less = i - 1;
            const auto height = heights[i];
            while (less >= 0 && heights[less] >= height) {
                less = left_less[less];
            }
            left_less[i] = less;
        }

        right_less.back() = size;
        for (ssize_t i = size - 2; i >= 0; --i) {
            ssize_t less = i + 1;
            const auto height = heights[i];
            while (less < size && heights[less] >= height) {
                less = right_less[less];
            }
            right_less[i] = less;
        }

        size_t max_square = 0;
        for (size_t i = 0; i < size; ++i) {
            const auto left = left_less[i];
            const auto right = right_less[i];
            const auto side = min(heights[i], size_t(right - left - 1));
            max_square = max(max_square, side * side);
        }
        return max_square * max_square;
    }
};

class Solution2 {
public:
    static size_t maximalSquare(vector<vector<char>> &matrix) {
        const auto height = matrix.size();
        if (height == 0)
            return 0;

        const auto width = matrix.front().size();
        if (width == 0)
            return 0;

        vector<vector<size_t>> max_squares(height + 1, vector<size_t>(width + 1, 0));

        size_t max_square = 0;
        for (size_t row_idx = 1; row_idx <= height; ++row_idx) {
            const auto &row = matrix[row_idx - 1];
            for (size_t col_idx = 1; col_idx <= width; ++col_idx) {
                const auto &elem = row[col_idx - 1];
                max_squares[row_idx][col_idx] =
                        (elem == '0') ?
                        0 :
                        min(
                                max_squares[row_idx][col_idx - 1],
                                min(max_squares[row_idx - 1][col_idx - 1], max_squares[row_idx - 1][col_idx])
                        ) + 1;
                max_square = max(max_square, max_squares[row_idx][col_idx]);
            }
        }
        return max_square;
    }
};

int main() {
    vector<vector<char>> matrix{{'0','1'},{'1','0'}};
    cout << Solution2::maximalSquare(matrix);
}