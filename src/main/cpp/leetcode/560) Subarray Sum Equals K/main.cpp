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
#include <cctype>
#include <charconv>

#include "container_utils.h"

using namespace std;

class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        int sum = 0;
        int count = 0;
        unordered_map<int, int> prefix_sums;
        prefix_sums[0] = 1;
        for (int num : nums) {
            sum += num;
            count += prefix_sums[sum - k];
            prefix_sums[sum]++;
        }
        return count;
    }
};