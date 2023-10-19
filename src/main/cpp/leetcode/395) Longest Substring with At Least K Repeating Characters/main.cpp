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

using namespace std;

class Solution {
public:
    int lengthOfLongestSubstringKDistinct(string s, int k) {
        int distinct = 0;
        array<int, 26> chars{0};
        int max_count = INT_MIN;
        int left = 0;
        int right;
        for (right = 0; right < s.size(); right++) {
            const char ch = s[right];
            chars[get_char_index(ch)]++;
            if (chars[get_char_index(ch)] == 1) {
                distinct++;
                if (distinct > k) {
                    max_count = max(max_count, right - left);
                    while (distinct > k) {
                        if (--chars[get_char_index(s[left])] == 0)
                            distinct--;
                        left++;
                    }
                }
            }
        }
        max_count = max(max_count, right - left);
        return max_count;
    }

private:
    int get_char_index(char ch) {
        return ch % 'a';
    }
};

int main() {
    cout << Solution().lengthOfLongestSubstringKDistinct("tt", 1);
}