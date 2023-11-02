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
    vector<string> removeInvalidParentheses(string s) {
        bracket to_remove = calc_parentheses_to_remove(s);
        unordered_set<string> valid_parentheses;
        remove_invalid_parentheses(s, valid_parentheses, to_remove);
        return {valid_parentheses.begin(), valid_parentheses.end()};
    }

private:
    struct bracket {
        int open = 0;
        int close = 0;
    };

    void remove_invalid_parentheses(
            const string &s,
            unordered_set<string> &valid_parentheses,
            bracket to_remove
    ) {
        vector<char> curr_parentheses;
        remove_invalid_parentheses(
                s,
                valid_parentheses,
                0,
                curr_parentheses,
                {},
                to_remove
        );
    }

    void remove_invalid_parentheses(
            const string &s,
            unordered_set<string> &valid_parentheses,
            int start,
            vector<char> &curr_parentheses,
            bracket count,
            bracket to_remove
    ) {
        int added = 0;
        for (int i = start; i < s.size(); i++) {
            const char ch = s[i];
            if (ch == '(') {
                if (to_remove.open > 0) {
                    remove_invalid_parentheses(
                            s,
                            valid_parentheses,
                            i + 1,
                            curr_parentheses,
                            {.open = count.open, .close = count.close},
                            {.open = to_remove.open - 1, .close = to_remove.close}
                    );
                }
                count.open++;
            } else if (ch == ')') {
                if (to_remove.close > 0) {
                    remove_invalid_parentheses(
                            s,
                            valid_parentheses,
                            i + 1,
                            curr_parentheses,
                            {.open = count.open, .close = count.close},
                            {.open = to_remove.open, .close = to_remove.close - 1}
                    );
                }
                count.close++;
                if (count.close > count.open)
                    break;
            }
            curr_parentheses.emplace_back(ch);
            added++;
        }
        if (count.open == count.close && to_remove.open == 0
            && to_remove.close == 0) {
            valid_parentheses.insert(make_string(curr_parentheses));
        }
        for (int i = 0; i < added; i++)
            curr_parentheses.pop_back();
    }

    bracket calc_parentheses_to_remove(const string &s) {
        int open_to_delete = 0;
        int close_to_delete = 0;
        for (const auto ch: s) {
            if (ch == '(') {
                open_to_delete++;
            } else if (ch == ')') {
                if (open_to_delete > 0)
                    open_to_delete--;
                else
                    close_to_delete++;
            }
        }
        return {open_to_delete, close_to_delete};
    }

    string make_string(const vector<char> &chars) {
        string s;
        s.reserve(chars.size());
        for (const auto ch: chars) {
            s.push_back(ch);
        }
        return s;
    }
};

int main() {
    string s = "((())";
    auto result = Solution().removeInvalidParentheses(s);
    cout << result;
}