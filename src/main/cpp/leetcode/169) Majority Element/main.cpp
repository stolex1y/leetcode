#include <random>
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
#include "test_utils.h"

using namespace std;

//std solution
class Solution0 {
public:
    int majorityElement(vector<int>& nums) {
        size_t mid = nums.size() / 2;
        nth_element(nums.begin(), nums.begin() + mid, nums.end());
        return nums[mid];
    }
};

// best
class Solution1 {
public:
    int majorityElement(vector<int>& nums) {
        int x = 0;
        int count = 0;
        for (const int num : nums) {
            if (count == 0) {
                x = num;
                count++;
            } else if (x == num) {
                count++;
            } else if (x != num) {
                count--;
            }
        }
        return x;
    }
};

// map
class Solution2 {
public:
    int majorityElement(vector<int>& nums) {
        unordered_map<int, size_t> m;
        for (int num : nums) {
            m[num]++;
        }

        int x = 0;
        size_t max_count = 0;
        for (const auto [num, count] : m) {
            if (count > max_count) {
                max_count = count;
                x = num;
            }
        }
        return x;
    }
};

// k order statistic
class Solution3 {
public:
    int majorityElement(vector<int> &nums) {
        std::shuffle(nums.begin(), nums.end(), std::mt19937(std::random_device()()));
        return k_order_statistic(nums, nums.size() / 2 + 1);
    }

    template<typename BidirIt, typename Predicate>
    static BidirIt partition(BidirIt first, BidirIt last, Predicate p) {
        while (first != last) {
            first = find_if(first, last, p);
            last = find_if_not(
                    make_reverse_iterator(last),
                    make_reverse_iterator(first),
                    p
            ).base();
            if (first == last) break;
            iter_swap(first++, --last);
        }
        return first;
    }

    static int k_order_statistic(vector<int> &nums, size_t k) {
        auto begin = nums.begin();
        auto end = nums.end();
        while (true) {
            const auto pivot = *(end - 1);
            const auto part_point = partition(begin, end - 1,
                                              [pivot](int i) { return i > pivot; });
            iter_swap(part_point, end - 1);
            const size_t part_point_idx = part_point - nums.begin();
            if (part_point_idx == k - 1)
                return *part_point;
            else if (part_point_idx < k - 1) {
                begin = part_point + 1;
            } else {
                end = part_point;
            }
        }
    }
};
