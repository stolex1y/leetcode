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

using namespace std;

class Solution {
public:
    vector<vector<int>> findDifference(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> set1{nums1.begin(), nums1.end()};
        unordered_set<int> set2{nums2.begin(), nums2.end()};
        vector<int> diff1;
        vector<int> diff2;
        for (const auto val : set1) {
            if (!set2.contains(val))
                diff1.push_back(val);
        }
        for (const auto val : set2) {
            if (!set1.contains(val))
                diff2.push_back(val);
        }
        return {diff1, diff2};
    }
};
