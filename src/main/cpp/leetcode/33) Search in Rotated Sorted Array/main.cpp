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
    int search(vector<int>& nums, int target) {
        size_t left = 0;
        size_t right = nums.size() - 1;
        while (right > left) {
            size_t mid = left + (right - left) / 2;

            if (target == nums[mid])
                return mid;

            if (nums[mid] > nums[left]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        if (left < nums.size() && target == nums[left])
            return left;
        else
            return -1;
    }
};

int main() {
    vector<int> nums{4,5};
    cout << Solution().search(nums, 0);
}