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
#include <unordered_map>

#include "container_utils.h"
#include "tree_node.h"

using namespace std;

class Solution {
public:
    vector<int> topKFrequent(vector<int> &nums, int k) {
        if (k == 0)
            return {};

        unordered_map<int, size_t> counts;
        for (const auto num: nums) {
            counts[num]++;
        }

        vector<vector<int>> buckets(nums.size());
        for (const auto [num, count]: counts) {
            buckets[count].emplace_back(num);
        }

        vector<int> top_frequent;
        top_frequent.reserve(k);
        for (auto it = buckets.rbegin(); it < buckets.rend(); ++it) {
            const auto &bucket = *it;
            if (!bucket.empty()) {
                copy(
                        bucket.begin(),
                        bucket.begin() + min(bucket.size(), k - top_frequent.size()),
                        back_inserter(top_frequent)
                );
                if (top_frequent.size() == k)
                    break;
            }
        }
        return top_frequent;
    }
};

int main() {
    vector<int> nums{1, 1, 1, 2, 2, 3};
    cout << Solution().topKFrequent(nums, 2);
}