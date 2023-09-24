#include <vector>
#include <array>
#include <cassert>
#include <algorithm>

#include "test_utils.h"

using namespace std;

class Solution {
public:
    int removeElement(vector<int> &nums, int val) {
        int newEnd = 0;
        int next = 0;
        while (next != nums.size()) {
            if (nums[next] != val) {
                nums[newEnd++] = nums[next];
            }
            next++;
        }
        return newEnd;
    }
};

static void testRemoveElement(vector<int> nums, int val) {
    auto expected = nums;
    auto expectedSize = std::distance(
            expected.begin(),
            std::remove_if(expected.begin(), expected.end(), [val](int i) { return i == val; })
    );
    auto actualSize = Solution{}.removeElement(nums, val);
    assert_equals(expected, nums);
    assert(expectedSize == actualSize);

}

int main() {
    auto test = std::vector{2, 3, 4, 2, 2};
    auto test1 = std::vector{2};
    auto test2 = std::vector<int>{};
    testRemoveElement(test, 2);
    testRemoveElement(test, 5);
    testRemoveElement(test1, 2);
    testRemoveElement(test2, 1);
    return 0;
}
