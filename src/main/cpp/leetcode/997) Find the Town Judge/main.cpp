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

using namespace std;

class Solution {
public:
    int findJudge(int n, vector<vector<int>> &trust) {
        const auto adj_lists = make_adj_lists(n, trust);
        const auto reversed_adj_lists = reverse_adj_lists(adj_lists);
        const auto degrees = count_node_degrees(adj_lists);
        const auto reversed_degrees = count_node_degrees(reversed_adj_lists);
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 0 && reversed_degrees[i] == n - 1)
                return i + 1;
        }
        return -1;
    }

private:
    static vector<vector<int>> make_adj_lists(int n, vector<vector<int>> &trust) {
        vector<vector<int>> adj_lists(n);
        for (const auto &edge: trust) {
            adj_lists[edge[0] - 1].push_back(edge[1] - 1);
        }
        return adj_lists;
    }

    static std::vector<std::vector<int>> reverse_adj_lists(
            const std::vector<std::vector<int>> &adj_lists
    ) {
        std::vector<std::vector<int>> reversed(adj_lists.size());
        for (int s = 0; s < adj_lists.size(); s++) {
            for (const auto t: adj_lists[s]) {
                reversed[t].push_back(s);
            }
        }
        return reversed;
    }

    static std::vector<int> count_node_degrees(
            const std::vector<std::vector<int>> &adj_lists
    ) {
        std::vector<int> degrees(adj_lists.size());
        for (int i = 0; i < adj_lists.size(); i++) {
            degrees[i] = adj_lists[i].size();
        }
        return degrees;
    }
};

int main() {
    vector<vector<int>> trust{{1, 2}};
    cout << Solution().findJudge(2, trust);
}
