#include <vector>
#include <array>

#include "test_utils.h"

using namespace std;

class Solution {
private:
    void reverse(vector<int>::iterator begin, vector<int>::iterator end) {
        auto end_reversed = reverse_iterator(end);
        while (begin < end_reversed.base()) {
            auto temp = *begin;
            *begin = *end_reversed;
            *end_reversed = temp;
            begin++;
            end_reversed++;
        }
    }

public:
    /*
 1,2,3,4,5,6,7,8
 8,7,6,5,4,3,2,1
 7,8,1,2,3,4,5,6
 */
    void rotate(vector<int> &nums, int k) {
        k = k % nums.size();
        if (k == 0)
            return;
        reverse(nums.begin(), nums.end());
        reverse(nums.begin(), nums.begin() + k);
        reverse(nums.begin() + k, nums.end());
    }
};

static vector<int> expected_rotate(vector<int> nums, int k) {
    vector<int> rotated_nums = vector<int>(nums.size());
    for (int i = 0; i < nums.size(); i++) {
        rotated_nums[(i + k) % nums.size()] = nums[i];
    }
    return rotated_nums;
}

int main() {
    vector<int> nums{{1, 2, 3, 4, 5, 6, 7, 8}};
    for (int k = 0; k <= nums.size(); k++) {
        vector<int> expected = expected_rotate(nums, k);
        vector<int> actual = nums;
        Solution().rotate(actual, k);
        assert_equals_content<int>(expected, actual);
    }
    return 0;
}

