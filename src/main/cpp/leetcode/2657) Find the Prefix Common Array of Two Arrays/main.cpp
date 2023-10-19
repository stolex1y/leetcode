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
    vector<int> findThePrefixCommonArray(vector<int>& A, vector<int>& B) {
        int count = 0;
        const int n = A.size();
        vector<int> result(n, 0);

        vector<bool> freqs1(n, false);
        vector<bool> freqs2(n, false);
        for (int i = 0; i < n; i++) {
            const int num1 = A[i];
            const int num2 = B[i];
            freqs1[num1 - 1] = true;
            freqs2[num2 - 1] = true;
            if (freqs1[num2 - 1])
                count++;
            if (freqs2[num1 - 1])
                count++;
            if (num1 == num2)
                count--;
            result[i] = count;
        }
        return result;
    }
};