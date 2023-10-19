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
    bool isValidPalindrome(string s, int k) {
        // x a b y e h c b d a

        /*
         x a b y e h c b d    k - 1
         a b y e h c b d a    k - 1

         a b y e h c b d    k - 2
         x a b y e h c b    k - 2
         a b y e h c b d a    k - 1
         */
        /*
         *     0 0 0 0 0
         */
        // f[l,r,k] = f[
    }
};