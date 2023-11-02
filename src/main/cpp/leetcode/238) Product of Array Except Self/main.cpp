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

// without division
class Solution {
public:
    vector<int> productExceptSelf(vector<int> &nums) {
        vector<int> results(nums.size(), 1);

        for (size_t i = 0; i < nums.size() - 1; ++i) {
            results[i + 1] = results[i] * nums[i];
        }

        int suffix = 1;
        for (size_t i = nums.size() - 1; i > 0; --i) {
            suffix *= nums[i];
            results[i - 1] *= suffix;
        }
        return results;
    }
};

// with division
class Solution2 {
public:
    vector<int> productExceptSelf(const vector<int> &nums) {
        vector<int> results(nums.size(), 0);

        int product = 1;
        size_t zero_count = 0;
        for (const auto num : nums) {
            if (num == 0)
                ++zero_count;
            else {
                if (zero_count > 1) {
                    break;
                } else {
                    product *= num;
                }
            }
        }
        if (zero_count == 1) {
            const size_t idx = find(nums.begin(), nums.end(), 0) - nums.begin();
            results[idx] = product;
        } else if (zero_count == 0) {
            for (size_t i = 0; i < nums.size(); ++i) {
                results[i] = product / nums[i];
            }
        }
        return results;
    }
};
