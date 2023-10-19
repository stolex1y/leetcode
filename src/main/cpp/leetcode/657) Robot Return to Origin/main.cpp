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
    bool judgeCircle(string moves) {
        int dx = 0;
        int dy = 0;
        for (const auto ch : moves) {
            switch (ch) {
                case 'U':
                    dy++;
                    break;
                case 'R':
                    dx++;
                    break;
                case 'D':
                    dy--;
                    break;
                case 'L':
                    dx--;
                    break;
            }
        }
        return dx == 0 && dy == 0;
    }
};