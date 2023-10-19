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
#include <span>
#include <valarray>

using namespace std;

class Solution {
public:
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int max_count = INT_MIN;
        int count = 0;
        for (const auto num : nums) {
            if (num == 1) {
                count++;
            } else {
                max_count = max(max_count, count);
                count = 0;
            }
        }
        max_count = max(max_count, count);
        return max_count;
    }
};
