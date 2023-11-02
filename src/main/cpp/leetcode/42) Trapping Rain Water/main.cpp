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

// Monotonic stack
class Solution {
public:
    int trap(vector<int> &heights) {
        stack<size> decreasing_stack;
        int count = 0;
        for (size i = 0; i < heights.size(); i++) {
            const int height = heights[i];
            while (!decreasing_stack.empty() && heights[decreasing_stack.top()] <= height) {
                if (decreasing_stack.size() == 1) {
                    decreasing_stack.pop();
                } else {
                    const int baseline = heights[decreasing_stack.top()];
                    decreasing_stack.pop();
                    const size prev_idx = decreasing_stack.top();
                    const int prev_height = heights[prev_idx];
                    count += count_water(prev_idx, prev_height, i, height, baseline);
                }
            }
            decreasing_stack.emplace(i);
        }
        return count;
    }

private:
    using size = vector<int>::size_type;

    static int count_water(size x_start, int y_start, size x_end, int y_end, int baseline) {
        const int diff_x = int(x_end - x_start - 1);
        if (diff_x > 0) {
            const int diff_y = min(y_end, y_start) - baseline;
            return diff_y * diff_x;
        }
        return 0;
    }
};

// DP
class Solution2 {
public:
    int trap(vector<int> &heights) {
        const auto n = heights.size();
        vector<int> maximums_left(n, 0);
        vector<int> maximums_right(n, 0);
        for (size_t i = 1; i < n; i++) {
            const auto left_idx = i;
            if (left_idx > 0) {
                maximums_left[left_idx] = max(maximums_left[left_idx - 1], heights[left_idx - 1]);
            }

            const auto right_idx = n - i - 1;
            if (right_idx < n - 1) {
                maximums_right[right_idx] = max(maximums_right[right_idx + 1], heights[right_idx + 1]);
            }
        }

        int count = 0;
        for (size_t i = 1; i < n - 1; i++) {
            const int max_fill_height = min(maximums_left[i], maximums_right[i]);
            const int baseline = heights[i];
            if (max_fill_height > baseline)
                count += max_fill_height - baseline;
        }
        return count;
    }
};

// Two pointers
class Solution3 {
public:
    int trap(vector<int> &heights) {
        auto left = heights.begin();
        auto right = heights.rbegin();
        int left_max = *left++;
        int right_max = *right++;
        int count = 0;
        while (distance(left, right.base()) > 0) {
            if (left_max <= right_max) {
                const int baseline = *left++;
                if (left_max > baseline)
                    count += left_max - baseline;
                else
                    left_max = baseline;
            } else {
                const int baseline = *right++;
                if (right_max > baseline)
                    count += right_max - baseline;
                else
                    right_max = baseline;
            }
        }
        return count;
    }
};

int main() {
    vector<int> heights{4, 2, 0, 3, 2, 5};
    cout << Solution3().trap(heights);
}
