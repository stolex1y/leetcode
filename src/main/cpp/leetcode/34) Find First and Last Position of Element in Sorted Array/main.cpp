#include <vector>
#include <array>
#include <queue>
#include <memory>
#include <iostream>

#include "test_utils.h"
#include "list_node.h"

using namespace std;

class Solution {
    int search_first(const vector<int> &nums, int target) {
        if (nums.size() == 0 || nums[0] > target)
            return -1;
        if (nums.front() == target)
            return 0;

        int left = 0;
        int right = nums.size() - 1;
        while (right - left > 1) {
            const int mid = left + (right - left) / 2;
            const int mid_num = nums[mid];
            if (target <= mid_num)
                right = mid;
            else
                left = mid;
        }
        if (nums[right] == target)
            return right;
        else
            return -1;
    }

    int search_last(const vector<int> &nums, int target) {
        if (nums.size() == 0 || nums[0] > target)
            return -1;
        if (nums.back() == target)
            return nums.size() - 1;

        int left = 0;
        int right = nums.size() - 1;
        while (right - left > 1) {
            const int mid = left + (right - left) / 2;
            const int mid_num = nums[mid];
            if (target >= mid_num)
                left = mid;
            else
                right = mid;
        }
        if (nums[left] == target)
            return left;
        else
            return -1;
    }

public:
    vector<int> searchRange(vector<int> &nums, int target) {
        return {search_first(nums, target), search_last(nums, target)};
    }
};