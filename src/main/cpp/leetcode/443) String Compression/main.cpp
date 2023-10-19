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
#include <cctype>
#include <charconv>

#include "container_utils.h"

using namespace std;

class Solution {
public:
    int compress(vector<char> &chars) {
        if (chars.empty())
            return 0;
        char ch = chars[0];
        int count = 1;
        int new_idx = 0;
        for (int idx = 1; idx < chars.size(); idx++) {
            const char curr = chars[idx];
            if (curr != ch) {
                new_idx += compress(ch, count, chars, new_idx);
                count = 1;
                ch = curr;
            } else {
                count++;
            }
        }
        new_idx += compress(ch, count, chars, new_idx);
        return new_idx;
    }

    static int compress(char ch, int count, vector<char> &chars, int start) {
        chars[start] = ch;
        if (count > 1) {
            auto start_ptr = chars.data() + start + 1;
            auto end_ptr =
                    to_chars(start_ptr, chars.data() + chars.size(), count, 10).ptr;
            return int(end_ptr - start_ptr) + 1;
        }
        return 1;
    }
};

int main() {
    vector<char> chars({'a', 'a', 'b', 'b', 'c', 'c', 'c'});
    cout << Solution().compress(chars) << endl;
    cout << chars;
}