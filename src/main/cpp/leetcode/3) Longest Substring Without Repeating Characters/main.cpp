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
    int lengthOfLongestSubstring(string s) {
        array<int, 128> frequencies{};
        frequencies.fill(0);
        int max_len = INT_MIN;
        int left = 0;
        int right;
        for (right = 0; right < s.size(); right++) {
            const char ch = s[right];
            frequencies[ch]++;
            if (frequencies[ch] > 1) {
                max_len = max(max_len, right - left);
                while (frequencies[ch] > 1) {
                    frequencies[s[left++]]--;
                }
            }
        }
        max_len = max(max_len, right - left);
        return max_len;
    }
};
