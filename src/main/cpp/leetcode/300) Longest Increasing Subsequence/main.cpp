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

// O(n^2)
class Solution {
public:
    int lengthOfLIS(vector<int> &nums) {
        using size = vector<int>::size_type;
        vector<size> max_lens(nums.size(), 1);
        for (size count = 1; count < nums.size(); count++) {
            const size i = nums.size() - 1 - count;
            const int num = nums[i];
            size max_len = 0;
            for (size j = i + 1; j < nums.size(); j++) {
                if (nums[j] > num) {
                    max_len = max(max_lens[j], max_len);
                }
            }
            max_lens[i] = max_len + 1;
        }
        return *max_element(max_lens.begin(), max_lens.end());
    }
};

// O(n * log(n))
class Solution2 {
public:
    int lengthOfLIS(vector<int> &nums) {
        using size = vector<int>::size_type;
        vector<int> increasing;
        increasing.emplace_back(nums.front());
        size max_len = 1;
        for (size i = 1; i < nums.size(); i++) {
            const int num = nums[i];
            if (num > increasing.back()) {
                increasing.emplace_back(num);
                max_len++;
            } else {
                const auto pos = std::lower_bound(increasing.begin(), increasing.end(), num);
                *pos = num;
            }
        }
        return max_len;
    }
};

int main() {
    vector<int> nums{10, 9, 2, 5, 3, 7, 101, 18};
    cout << Solution().lengthOfLIS(nums);
}