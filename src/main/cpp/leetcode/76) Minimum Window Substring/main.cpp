#include <vector>
#include <array>
#include <queue>
#include <memory>
#include <iostream>
#include <algorithm>
#include <numeric>
#include <map>
#include <unordered_map>
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

using namespace std;

class Solution {
public:
    string minWindow(string s, string t) {
        char_counts req_counts = count_chars(t);
        char_counts window_counts;
        size_t min = SIZE_MAX;
        size_t min_start = SIZE_MAX;
        for (size_t start = 0, end = 1; end <= s.size(); ++end) {
            ++window_counts[s[end - 1]];
            while (contain_chars(window_counts, req_counts)) {
                if (end - start < min) {
                    min = end - start;
                    min_start = start;
                }
                --window_counts[s[start]];
                ++start;
            }
        }
        if (min_start == SIZE_MAX)
            return "";
        else
            return s.substr(min_start, min);
    }

private:
    using char_counts = unordered_map<char, size_t>;

    static bool contain_chars(const char_counts &source_counts, const char_counts &req_counts) {
        return all_of(
                req_counts.begin(),
                req_counts.end(),
                [&source_counts](const auto &pair) {
                    const auto found = source_counts.find(pair.first);
                    return found != source_counts.end() && found->second >= pair.second;
                });
    }

    static unordered_map<char, size_t> count_chars(const string &s) {
        unordered_map<char, size_t> count_map;
        for (const auto ch: s) {
            ++count_map[ch];
        }
        return count_map;
    }
};

int main() {
    cout << Solution().minWindow("ADOBECODEBANC", "ABC");
}