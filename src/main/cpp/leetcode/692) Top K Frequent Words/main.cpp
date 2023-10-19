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

class Solution {
public:
    vector<string> topKFrequent(vector<string> &words, int k) {
        unordered_map<string, int> frequencies;
        for (const auto &word: words) {
            frequencies[word]++;
        }
        using key = pair<string, int>;
        auto greater = [](const key &first, const key &second) {
            if (first.second == second.second)
                return first.first < second.first;
            else
                return first.second > second.second;
        };
        priority_queue<key, vector<key>, decltype(greater)> queue;
        for (const auto &pair: frequencies) {
            if (queue.size() < k) {
                queue.emplace(pair);
            } else if (greater(pair, queue.top())) {
                queue.pop();
                queue.emplace(pair);
            }
        }
        vector<string> result;
        result.reserve(queue.size());
        while (!queue.empty()) {
            auto word = queue.top().first;
            queue.pop();
            result.emplace_back(std::move(word));
        }
        std::reverse(result.begin(), result.end());
        return result;
    }
};

int main() {
    vector<string> words{"i","love","leetcode","i","love","coding"};
    cout << Solution().topKFrequent(words, 2);
}