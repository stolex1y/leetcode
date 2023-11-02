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

// deque
class Solution {
public:
    vector<int> maxSlidingWindow(vector<int> &nums, int k) {
        deque<size> increase_deque;
        vector<int> maximums(max(size(0), nums.size() - k + 1));
        for (size i = 0; i < min(size(k), nums.size()); i++) {
            const int num = nums[i];
            emplace_front_to_increase_deque(nums, increase_deque, num, i);
        }
        maximums[0] = nums[increase_deque.back()];
        for (size right = k; right < nums.size(); right++) {
            const int num = nums[right];
            const size left_idx = right - k + 1;
            while (!increase_deque.empty() &&
                   increase_deque.back() < left_idx) {
                increase_deque.pop_back();
            }
            emplace_front_to_increase_deque(nums, increase_deque, num, right);
            maximums[right - k + 1] = nums[increase_deque.back()];
        }
        return maximums;
    }

private:
    using size = vector<int>::size_type;
    static void emplace_front_to_increase_deque(const vector<int> &nums, deque<size> &increase_deque, int num, size idx) {
        while (!increase_deque.empty() &&
               nums[increase_deque.front()] <= num) {
            increase_deque.pop_front();
        }
        increase_deque.emplace_front(idx);
    }
};

// map
class Solution2 {
public:
    vector<int> maxSlidingWindow(vector<int> &nums, int k) {
        using size = vector<int>::size_type;

        map<int, size, greater<>> window;
        vector<int> maximums(max(size(0), nums.size() - k + 1));
        for (size i = 0; i < min(nums.size(), size(k)); i++) {
            window[nums[i]]++;
        }
        maximums[0] = window.begin()->first;
        size new_right = k;
        while (new_right < nums.size()) {
            const int old_left_num = nums[new_right - k];
            if (window[old_left_num] == 1)
                window.erase(old_left_num);
            else
                window[old_left_num]--;
            window[nums[new_right]]++;
            maximums[new_right - k + 1] = window.begin()->first;
            new_right++;
        }
        return maximums;
    }
};
