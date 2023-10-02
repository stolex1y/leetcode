#include <vector>
#include <array>
#include <queue>
#include <memory>
#include <iostream>

#include "test_utils.h"
#include "container_utils.h"
#include "list_node.h"

using namespace std;

class Solution {
public:
    void generate_parenthesis_recursively(vector<string> &strings, int n, string &&str = "",
                                          int open = 0, int close = 0) {
        if (close == n && open == n) {
            strings.push_back(str);
            return;
        }
        if (open < n) {
            generate_parenthesis_recursively(strings, n, str + "(", open + 1, close);
        }
        if (close < open) {
            generate_parenthesis_recursively(strings, n, str + ")", open, close + 1);
        }
    }

    vector<string> generateParenthesis(int n) {
        vector<string> results;
        generate_parenthesis_recursively(results, n);
        return results;
    }
};

int main() {
    auto result = Solution().generateParenthesis(3);
    cout << result;
}