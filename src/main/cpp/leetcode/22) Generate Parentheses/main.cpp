#include <vector>
#include <array>
#include <queue>
#include <memory>
#include <iostream>

#include "test_utils.h"
#include "list_node.h"

using namespace std;

class Solution {
public:
    int find(string haystack, string needle, bool need_first = true) {
        int found_start = -1;
        int same_count = 0;
        for (int i = 0; i < haystack.size(); i++) {
            const auto ch = haystack[i];
            if (ch == needle[same_count]) {
                same_count++;
                if (same_count == needle.size()) {
                    found_start = i - same_count + 1;
                    if (need_first)
                        return found_start;
                }
            } else {
                same_count = ch == needle[0] ? 1 : 0;
            }
        }
        return found_start;
    }

    int strStr(string haystack, string needle) {
        int r = find(haystack, needle);
        if (r != -1)
            return r;
        std::reverse(haystack.begin(), haystack.end());
        std::reverse(needle.begin(), needle.end());
        r = find(haystack, needle, false);
        if (r == -1)
            return -1;
        return int(haystack.size()) - r - int(needle.size());
    }
};