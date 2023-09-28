#include <vector>
#include <array>
#include <queue>

#include "test_utils.h"

using namespace std;

class Solution {
public:
    bool canJump_dp(vector<int> &nums) {
        for (int i = nums.size() - 1; i >= 0; i--) {
            const int max_next = min(i + nums[i], int(nums.size()) - 1);
            nums[i] = i;
            for (int next = i + 1; next <= max_next; next++) {
                nums[i] = max(nums[i], nums[next]);
                if (nums[i] == nums.size() - 1)
                    break;
            }
        }
        return nums[0] == nums.size() - 1;
    }

    bool canJump(vector<int> &nums) {
        int max_pos = 0;
        for (int curr = 0; curr < nums.size() && curr <= max_pos; curr++) {
            max_pos = max(max_pos, curr + nums[curr]);
            if (max_pos >= nums.size() - 1)
                return true;
        }
        return false;
    }
};

int main() {
    vector<int> nums {{2,3,1,1,4}};
    auto expected = true;
    assert_equals(expected, Solution().canJump(nums));
}

