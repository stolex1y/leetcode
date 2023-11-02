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
    vector<vector<int>> merge(vector<vector<int>> &intervals) {
        if (intervals.empty())
            return {};

        sort(
                intervals.begin(),
                intervals.end(),
                [](const auto &interval1, const auto &interval2) {
                    return interval1[0] < interval2[0];
                }
        );

        vector<vector<int>> merged;
        merged.emplace_back(intervals[0]);

        for (auto it = ++intervals.begin(); it < intervals.end(); ++it) {
            const auto &cur_interval = *it;
            auto &last_merged = merged.back();
            if (cur_interval[0] <= last_merged[1]) {
                last_merged[1] = max(last_merged[1], cur_interval[1]);
            } else {
                merged.emplace_back(cur_interval);
            }
        }
        return merged;
    }
};

int main() {
    vector<vector<int>> intervals = {{1,3},{2,6},{8,10},{15,18}};
    cout << Solution().merge(intervals);
}