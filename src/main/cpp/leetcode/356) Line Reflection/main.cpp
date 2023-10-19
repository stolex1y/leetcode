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
    bool isReflected(vector<pair<int, int>>& points) {
        using point = pair<int, int>;
        const auto [min, max] = minmax_x(points);
        const int sum = min + max;
        const auto point_hash = [](const point& p) { return p.first ^ p.second; };
        unordered_set<point, decltype(point_hash)> point_set(points.begin(), points.end());
        const bool reflected = std::all_of(points.begin(), points.end(), [sum, &point_set](const auto &p){
            return point_set.contains(make_pair(sum - p.first, p.second));
        });
        return reflected;
    }

    pair<int, int> minmax_x(const vector<pair<int, int>>& points) {
        int min = INT_MAX;
        int max = INT_MIN;
        for (const auto &point : points) {
            if (point.first < min)
                min = point.first;
            else if (point.first > max)
                max = point.first;
        }
        return make_pair(min, max);
    }
};