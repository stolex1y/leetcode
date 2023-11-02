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

// last index
class Solution {
public:
    vector<int> partitionLabels(string s) {
        using size = string::size_type;
        array<size, 26> last_idx{};
        last_idx.fill(-1);
        for (size i = 0; i < s.size(); i++) {
            const char ch = s[i];
            last_idx[get_char_idx(ch)] = i;
        }
        vector<int> partitions;
        size left = 0;
        size right = 0;
        for (size i = 0; i < s.size(); i++) {
            const char ch = s[i];
            right = max(right, last_idx[get_char_idx(ch)]);
            if (right == i) {
                partitions.emplace_back(right - left + 1);
                left = right + 1;
                right = right + 1;
            }
        }
        return partitions;
    }

private:
    static int get_char_idx(char ch) {
        return ch - 'a';
    }
};

// char segment
class Solution2 {
public:
    vector<int> partitionLabels(string s) {
        array<segment, 26> char_segments{};
        char_segments.fill({});
        for (size i = 0; i < s.size(); i++) {
            const char ch = s[i];
            auto &segment = char_segments[get_char_idx(ch)];
            if (segment.length() == 0) {
                segment.left = i;
                segment.right = i + 1;
            } else {
                segment.right = i + 1;
            }
        }
        vector<int> partitions;
        const auto segments_cmp = [](const segment &s1, const segment &s2){
            if (s1.left == s2.left)
                return s1.right == s2.right;
            return s1.left < s2.left;
        };
        sort(char_segments.begin(), char_segments.end(), segments_cmp);
        size i = find_if(char_segments.begin(), char_segments.end(), [](const auto& s){return s.length() > 0;}) - char_segments.begin();
        segment cur = char_segments[i++];
        while (i < char_segments.size()) {
            const auto &segment = char_segments[i++];
            if (cur.intersects(segment)) {
                cur.unite(segment);
            } else {
                partitions.emplace_back(cur.length());
                cur = segment;
            }
        }
        partitions.emplace_back(cur.length());
        return partitions;
    }

private:
    using size = string::size_type;

    struct segment {
        size left = 0;
        size right = 0;

        bool intersects(const segment &other) const {
            return right > other.left && left <= other.right;
        }

        size length() const {
            return right - left;
        }

        void unite(const segment &other) {
            left = min(left, other.left);
            right = max(right, other.right);
        }
    };

    static int get_char_idx(char ch) {
        return ch - 'a';
    }
};

int main() {
    string s = "ababcbacadefegdehijhklij";
    cout << Solution2().partitionLabels(s);
}