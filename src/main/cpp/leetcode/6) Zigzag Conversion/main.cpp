#include <vector>
#include <array>
#include <cassert>
#include <algorithm>
#include <list>
#include <iostream>

#include "test_utils.h"

using namespace std;

void printLists(const vector<string> &lists) {
    ostream_iterator<char, char> outIt(cout, " ");
    for (const auto &list: lists) {
        copy(list.begin(), list.end(), outIt);
        cout << '\n';
    }
}

class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1)
            return s;
        vector<string> rows(numRows);
        int curRow = 0;
        bool reversed = false;
        for (const auto ch: s) {
            rows[curRow] += ch;
            if (curRow == numRows - 1) {
                reversed = true;
            } else if (curRow == 0) {
                reversed = false;
            }
            if (reversed) {
                curRow--;
            } else {
                curRow++;
            }
        }
        string result;
        result.reserve(s.size());
        for (const auto &row: rows) {
            result += row;
        }
        return result;
    }
};

int main() {
    string s = "AB";
    int numRows = 1;
    cout << Solution().convert(s, numRows);
    return 0;
}


