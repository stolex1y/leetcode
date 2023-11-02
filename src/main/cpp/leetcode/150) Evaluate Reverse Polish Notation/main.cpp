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
    int evalRPN(vector<string> &tokens) {
        stack<int> operands;
        for (const auto &token : tokens) {
            int operand;
            if (parse_int(token, operand) != 0) {
                command cmd = parse_command(token);
                int second = operands.top();
                operands.pop();
                int first = operands.top();
                operands.pop();
                operands.emplace(perform_command(first, second, cmd));
            } else {
                operands.emplace(operand);
            }
        }
        return operands.top();
    }

private:
    enum class command {
        ADDITION,
        MULTIPLICATION,
        DIVISION,
        SUBTRACTION
    };

    static int perform_command(int first, int second, command cmd) {
        switch (cmd) {
            case command::ADDITION:
                return first + second;
            case command::MULTIPLICATION:
                return first * second;
            case command::DIVISION:
                return first / second;
            case command::SUBTRACTION:
                return first - second;
        }
    }

    static command parse_command(const string &cmd) {
        switch (cmd.front()) {
            case '+':
                return command::ADDITION;
            case '*':
                return command::MULTIPLICATION;
            case '-':
                return command::SUBTRACTION;
            case '/':
                return command::DIVISION;
            default:
                throw invalid_argument("Unknown command: " + cmd);
        }
    }

    static int parse_int(const string &s, int &result) {
        const auto parse_res = from_chars(s.data(), s.data() + s.size(), result);
        if (parse_res.ec != errc())
            return -1;
        return 0;
    }
};

int main() {
    vector<string> tokens = {"2","1","+","3","*"};
    cout << Solution().evalRPN(tokens);
}