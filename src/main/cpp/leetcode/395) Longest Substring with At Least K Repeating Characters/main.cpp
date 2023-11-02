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

// нужен грамотный подсчет символов, которые никак
// не могут попасть в результирующую подстроку
// может быть такая ситуация: пусть символ 'a' встречается k раз, а символ 'b' < k,
// при этом часть символов 'a' разделена символом 'b', поэтому 'a' также не может
// попасть в подстроку
class Solution {
public:
    string::size_type longestSubstring(const string &s, int k) {
        using size = string::size_type;
        unordered_map<char, size> max_counts = count_chars(s);
        unordered_map<char, size> curr_counts;
        unordered_set<char> curr_lower;
        size max_count = 0;
        size left = 0;
        for (size right = 0; right < s.size(); right++) {
            const char current = s[right];
            if (max_counts[current] < k) {
                while (left <= right) {
                    const char left_ch = s[left];
                    max_counts[left_ch]--;
                    curr_counts[left_ch]--;
                    if (curr_counts[left_ch] == 0) {
                        curr_lower.erase(left_ch);
                    } else if (curr_counts[left_ch] < k) {
                        curr_lower.insert(left_ch);
                    }
                    left++;
                    if (curr_lower.empty())
                        max_count = max(max_count, right - left + 1);
                }
                curr_counts.clear();
                curr_lower.clear();
            } else {
                curr_counts[current]++;
                if (curr_counts[current] < k)
                    curr_lower.insert(current);
                else
                    curr_lower.erase(current);
            }
            if (curr_lower.empty())
                max_count = max(max_count, right - left + 1);
        }
        return max_count;
    }

    unordered_map<char, string::size_type> count_chars(const string &s) {
        using size = string::size_type;
        unordered_map<char, size> counts;
        for (char i: s) {
            counts[i]++;
        }
        return counts;
    }
};

int main() {
    cout << Solution().longestSubstring("aaabb", 3);
}