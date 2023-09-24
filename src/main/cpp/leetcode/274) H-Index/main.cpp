#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int hIndex(vector<int> &citations) {
        std::sort(citations.begin(), citations.end(), std::greater());
        int i = 0;
        while (i < citations.size() && citations[i] >= i + 1) { i++; }
        return i;
    }
};
