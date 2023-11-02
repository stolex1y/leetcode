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
    string simplifyPath(string path) {
        deque<string> dirs;
        size left = 0;
        for (size right = 0; right <= path.size(); right++) {
            if (path[right] == '/' || right == path.size()) {
                auto &&dir = path.substr(left, right - left);
                if (dir == "..") {
                    if (!dirs.empty())
                        dirs.pop_back();
                } else if (dir != "." && !dir.empty()) {
                    dirs.emplace_back(dir);
                }
                left = right + 1;
            }
        }
        return build_path(dirs);
    }

private:
    using size = string::size_type;

    static string build_path(const deque<string> &dirs) {
        if (dirs.empty()) {
            return "/";
        }
        string result;
        for (const auto &s: dirs) {
            result.append("/");
            result.append(s);
        }
        return result;
    }
};

int main() {
    cout << Solution().simplifyPath("/../");
}