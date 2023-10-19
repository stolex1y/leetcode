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
    int minDistance(const string& word1, const string& word2) {
        if (word1.size() < word2.size()) {
            return minDistance(word2, word1);
        }
        vector<int> next_min_distances(word2.size() + 1);
        vector<int> min_distances(word2.size() + 1);
        for (int start1 = word1.size(); start1 >= 0; start1--) {
            swap(min_distances, next_min_distances);
            for (int start2 = word2.size(); start2 >= 0; start2--) {
                min_distances[start2] = minDistance(
                        word1, start1, word2, start2, min_distances, next_min_distances
                );
            }
        }
        return min_distances[0];
    }

    static inline int minDistance(
            const string &s1, int start1,
            const string &s2, int start2,
            const vector<int> &min_distances,
            const vector<int> &next_min_distances
    ) {
        if (str_equals(s1, start1, s2, start2))
            return 0;
        if (start1 == s1.size())
            return s2.size() - start2;
        if (start2 == s2.size())
            return s1.size() - start1;
        const int with_equals = (s1[start1] == s2[start2]) ?
                                next_min_distances[start2 + 1] : INT_MAX;
        const int with_adding = min_distances[start2 + 1] + 1;
        const int with_replace = next_min_distances[start2 + 1] + 1;
        const int with_remove = next_min_distances[start2] + 1;
        const int min = valarray<int>{with_equals, with_replace, with_remove, with_adding}.min();
        return min;
    }

    static bool str_equals(
            const string &s1, int start1,
            const string &s2, int start2
    ) {
        const auto first1 = s1.begin() + max(0, start1 - 1);
        const auto first2 = s2.begin() + max(0, start2 - 1);
        return equal(first1, s1.end(), first2, s2.end());
    }
};

int main() {
    string s1 = "intention";
    string s2 = "execution";
    cout << Solution().minDistance(s1, s2);
}