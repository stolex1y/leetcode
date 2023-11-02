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
#include <unordered_map>
#include <functional>

#include "container_utils.h"

using namespace std;

struct Point {
    int x;
    int y;

    bool operator==(const Point &p) const {
        return x == p.x && y == p.y;
    }
};

class Solution {
public:
    bool isReflected(vector<Point> &points) {
        const auto [min, max] = minmax_x(points);
        const int sum = min + max;
        const auto point_hash = [](const Point &p) { return p.x ^ p.y; };
        unordered_map<Point, size_t, decltype(point_hash)> point_map;
        for (const auto &p : points) {
            point_map[p]++;
        }
        const bool reflected = std::all_of(points.begin(), points.end(), [sum, &point_map](const auto &p) {
            const Point sym_point{sum - p.x, p.y};
            if (point_map[sym_point] == 0)
                return false;
            point_map[sym_point]--;
            return true;
        });
        return reflected;
    }

    pair<int, int> minmax_x(const vector<Point> &points) {
        int min = INT_MAX;
        int max = INT_MIN;
        for (const auto &point: points) {
            if (point.x < min)
                min = point.x;
            if (point.x > max)
                max = point.x;
        }
        return make_pair(min, max);
    }
};

int main() {
    vector<Point> points{{1,1},{-1,1}};
    cout << Solution().isReflected(points);
}