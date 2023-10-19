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
    bool validPalindrome(string s) {
        int left = 0;
        int right = s.size() - 1;
        while (left < right) {
            const char chl = s[left];
            const char chr = s[right];
            if (chl != chr) {
                return is_palindrome(s, left + 1, right) ||
                       is_palindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

private:
    bool is_palindrome(const string &s, int left, int right) {
        while (left < right) {
            if (s[left] != s[right])
                return false;
            left++;
            right--;
        }
        return true;
    }
};