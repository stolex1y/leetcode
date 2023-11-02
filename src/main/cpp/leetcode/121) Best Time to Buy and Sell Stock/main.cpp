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

// greedy
class Solution {
public:
    int maxProfit(vector<int> &prices) {
        using size = vector<int>::size_type;
        int min_price = INT_MAX;
        int max_profit = 0;
        for (const auto p: prices) {
            if (p < min_price)
                min_price = p;
            else {
                max_profit = max(max_profit, p - min_price);
            }
        }
        return max_profit;
    }
};
