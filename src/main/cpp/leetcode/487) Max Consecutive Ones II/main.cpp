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
    int longestOnes(vector<int>& nums, int max_flip = 1) {
        int max_count = INT_MIN;
        int segment = 0;
        int flipped = 0;
        int count = 0;
        queue<int> ones_segments;
        for (const auto num : nums) {
            if (num == 1) {
                segment++;
                count++;
            } else {
                ones_segments.push(segment);
                segment = 0;
                if (flipped < max_flip) {
                    flipped++;
                    count++;
                } else {
                    int prev_segment = ones_segments.front();
                    ones_segments.pop();
                    max_count = max(max_count, count);
                    count -= prev_segment;
                }
            }
        }
        max_count = max(max_count, count);
        return max_count;
    }
};

// Optimal
class Solution2 {
public:
    int longestOnes(vector<int>& nums, int max_flip = 1) {
        int max_count = INT_MIN;
        int flipped = 0;
        int left = 0;
        int right;
        for (right = 0; right < nums.size(); right++) {
            if (nums[right] == 0) {
                flipped++;
                if (flipped > max_flip) {
                    max_count = max(max_count, right - left);
                    while (flipped > max_flip) {
                        if (nums[left] == 0)
                            flipped--;
                        left++;
                    }
                }
            }
        }
        max_count = max(max_count, right - left);
        return max_count;
    }
};

int main() {
    vector<int> arr{1,1,1};
    cout << Solution2().longestOnes(arr, 0);
}