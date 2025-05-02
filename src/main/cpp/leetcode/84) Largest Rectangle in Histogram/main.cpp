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
    static uint64_t largestRectangleArea(vector<int>& heights) {
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

        uint64_t max_rect = 0;
        for (size_t i = 0; i < size; ++i) {
            const auto left = left_less[i];
            const auto right = right_less[i];
            max_rect = max(max_rect, uint64_t(heights[i]) * (right - left - 1));
        }
        return max_rect;
    }
};

int main() {
    vector<int> arr{2,1,5,6,2,3};
    cout << Solution::largestRectangleArea(arr);
}