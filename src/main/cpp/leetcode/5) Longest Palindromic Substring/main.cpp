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

// DP
class Solution {
public:
    string longestPalindrome(string s) {
        int max = 1;
        int max_palindrome_start = 0;
        array<vector<bool>, 2> is_palindrome_arr{
                vector<bool>(s.size(), true),
                vector<bool>(s.size(), true)
        };
        for (int len = 2; len <= s.size(); len++) {
            bool has_palindrome = false;
            for (int start = 0; start < s.size() - len + 1; start++) {
                const int end = start + len - 1;
                auto &is_palindrome = (len % 2 == 0) ? is_palindrome_arr[0] : is_palindrome_arr[1];
                if (s[start] == s[end] && is_palindrome[start + 1]) {
                    is_palindrome[start] = true;
                    if (!has_palindrome) {
                        has_palindrome = true;
                        max = len;
                        max_palindrome_start = start;
                    }
                } else {
                    is_palindrome[start] = false;
                }
            }
        }
        return s.substr(max_palindrome_start, max);
    }
};

// Brute-force, but optimal
class Solution2 {
public:
    string longestPalindrome(string s) {
        int max_len = 1;
        int max_center = 0;
        for (int center = 0; center < s.size(); center++) {
            const int curr_max = max(max_palindrome(s, center, true), max_palindrome(s, center, false));
            if (curr_max > max_len) {
                max_len = curr_max;
                max_center = center;
            }
        }
        return s.substr(max_center - (max_len - 1) / 2, max_len);
    }

private:
    static int max_palindrome(const string &s, int start, bool even) {
        int end = start + 1;
        int len = 0;
        if (!even) {
            len++;
            start--;
        }
        while (start >= 0 && end < s.size() && s[start--] == s[end++]) {
            len += 2;
        }
        return len;
    }
};

int main() {
    string s = "babad";
    cout << Solution().longestPalindrome(s);
}