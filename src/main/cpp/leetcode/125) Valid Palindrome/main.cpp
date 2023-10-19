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

#include "container_utils.h"

using namespace std;

class Solution {
public:
    bool isPalindrome(string s) {
        int left = 0;
        int right = s.size() - 1;
        while (left < right) {
            const char left_ch = s[left];
            const char right_ch = s[right];
            if (!is_alpha_or_digit(left_ch)) {
                left++;
            } else if (!is_alpha_or_digit(right_ch)) {
                right--;
            } else if (tolower(left_ch) != tolower(right_ch)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }

    static bool is_alpha_or_digit(char ch) {
        return isalpha(ch) || isdigit(ch);
    }
};