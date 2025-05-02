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

using namespace std;

class Solution {
public:
    static string subStrHash(string s, uint32_t power, uint32_t modulo, size_t k, uint64_t req_hash) {
        std::reverse(s.begin(), s.end());
        vector<uint64_t> prefix_hashes(s.size() + 1, 0);
        for (size_t i = 1; i < prefix_hashes.size(); ++i) {
            prefix_hashes[i] = ((prefix_hashes[i - 1] * (power % modulo)) % modulo + (s[i - 1] - 'a' + 1) % modulo) % modulo;
        }

        vector<uint64_t> powers(s.size() + 1, 1);
        for (size_t i = 1; i < powers.size(); ++i) {
            powers[i] = (powers[i - 1] * (power % modulo)) % modulo;
        }

        string result = "";
        for (size_t first = 0, last = k; last <= s.size(); ++first, ++last) {
            uint64_t cur_hash = substr_hash(first, last, prefix_hashes, powers, modulo);
            if (req_hash == cur_hash) {
                result = s.substr(first, k);
                std::reverse(result.begin(), result.end());
            }
        }
        return result;
    }

    static uint64_t substr_hash(size_t first, size_t last, const vector<uint64_t> &prefix_hashes, const vector<uint64_t> &powers, uint32_t modulo) {
        int64_t hash = (int64_t(prefix_hashes[last]) - int64_t((prefix_hashes[first] * powers[last - first])) % modulo) % modulo;
        if (hash < 0)
            return modulo + hash;
        else
            return hash;
    }
};

int main() {
    string s = "xxterzixjqrghqyeketqeynekvqhc";
    cout << Solution::subStrHash(s, 15, 94, 4, 16);
}
