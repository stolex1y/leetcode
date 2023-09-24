#include <vector>
#include <array>
#include <cassert>
#include <algorithm>
#include <stdexcept>
#include <sstream>
#include <iostream>

using namespace std;

class Solution {
public:
    void merge(vector<int> &nums1, int m, vector<int> &nums2, int n) {
        auto insert = nums1.rbegin();
        auto item1 = nums1.rbegin() + n;
        auto item2 = nums2.rbegin();
        while (item1.base() > nums1.begin() || item2.base() > nums2.begin()) {
            if (item1.base() == nums1.begin()) {
                *insert = *item2;
                item2++;
            } else if (item2.base() == nums2.begin()) {
                *insert = *item1;
                item1++;
            } else {
                if (*item1 >= *item2) {
                    *insert = *item1;
                    item1++;
                } else {
                    *insert = *item2;
                    item2++;
                }
            }
            insert++;
        }
    }
};

template<typename T, typename MsgProvider>
static void assertEquals(const T &expected, const T &actual, MsgProvider provider = []() { return ""; }) {
    if (expected != actual)
        throw runtime_error(provider());
}

template<typename T>
static std::string vectorToString(const vector<T> &vec) {
    std::stringstream str;
    str << "[";
    for (auto i = vec.begin(); i != vec.end(); i++) {
        str << std::to_string(*i);
        if (i + 1 != vec.end())
            str << ", ";
    }
    str << "]";
    return str.str();
}

static void testMerge(const vector<int> &nums1, const vector<int> &nums2) {
    const int m = int(nums1.size() - nums2.size());
    const int n = int(nums2.size());
    assert(m > 0);
    assert(nums1.size() == m + n);
    auto nums1Actual = nums1;
    auto nums2Actual = nums2;
    auto nums1Expected = vector<int>(nums1.size());
    std::merge(
            nums1.begin(),
            nums1.end() - n,
            nums2.begin(),
            nums2.end(),
            nums1Expected.begin()
    );
    Solution().merge(nums1Actual, m, nums2Actual, n);
    assertEquals(
            nums1Expected,
            nums1Actual,
            [nums1Expected, nums1Actual]() {
                std::stringstream msg;
                msg << "Expected: " << vectorToString(nums1Expected);
                msg << ", but actual: " << vectorToString(nums1Actual);
                return msg.str();
            }
    );
    std::cout << "Successful test execution!";
}

int main() {
    auto nums1 = std::vector<int>{1, 0, 0};
    auto nums2 = std::vector<int>{1, 2};
    testMerge(nums1, nums2);
    return 0;
}
