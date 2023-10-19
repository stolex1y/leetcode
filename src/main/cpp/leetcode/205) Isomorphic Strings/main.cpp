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

using namespace std;

class Solution {
public:
    bool isIsomorphic(string s, string t) {
        array<char, 128> mapped{};
        array<bool, 128> used{};
        mapped.fill(0);
        used.fill(false);
        for (int i = 0; i < s.size(); i++) {
            const char source = s[i];
            const char target = t[i];
            if (mapped[source] != 0) {
                const char mapped_ch = mapped[source];
                if (mapped_ch != target)
                    return false;
            } else {
                if (used[target])
                    return false;
                mapped[source] = target;
                used[target] = true;
            }
        }
        return true;
    }
};

int main() {
    string s = "12";
    string t = "\u0067\u0067";
    cout << Solution().isIsomorphic(s, t);
}