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

class MinStack {
public:
    MinStack() {}

    void push(int val) {
        values.emplace(val);
        if (decreasing_values.empty() || val <= decreasing_values.top())
            decreasing_values.emplace(val);
    }

    void pop() {
        const auto top = values.top();
        values.pop();
        if (decreasing_values.top() == top)
            decreasing_values.pop();
    }

    int top() {
        return values.top();
    }

    int getMin() {
        return decreasing_values.top();
    }

private:
    stack<int> values;
    stack<int> decreasing_values;
};

int main() {
    MinStack stack;
    stack.push(-2);
    stack.push(0);
    stack.push(-3);
    cout << stack.getMin() << endl;
    stack.pop();
    cout << stack.top() << endl;
    cout << stack.getMin() << endl;
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(val);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */