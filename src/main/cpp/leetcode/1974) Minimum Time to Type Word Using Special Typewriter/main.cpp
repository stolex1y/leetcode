#include <algorithm>
#include <array>
#include <cassert>
#include <charconv>
#include <iostream>
#include <list>
#include <map>
#include <memory>
#include <numeric>
#include <optional>
#include <queue>
#include <set>
#include <stack>
#include <unordered_set>
#include <valarray>
#include <vector>

#include "container_utils.h"
#include "tree_node.h"

using namespace std;

class Solution {
public:
  int minTimeToType(const std::string& word) {
    std::size_t count = 0;
    char cur_ch = 'a';
    for (const auto ch : word) {
      std::size_t diff;
      if (ch <= cur_ch) {
        diff = std::min(cur_ch - ch, ch + 26 - cur_ch);
      } else {
        diff = std::min(ch - cur_ch, cur_ch + 26 - ch);
      }
      count += diff + 1;
      cur_ch = ch;
    }
    return count;
  }
};
