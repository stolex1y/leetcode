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

using namespace std;

class Solution {
public:
    int maximalNetworkRank(int n, vector<vector<int>>& roads) {
        std::vector<std::unordered_set<int>> adj_lists = make_adjacency_lists(n, roads);
        vector<int> degrees = count_node_degrees(adj_lists);
        int max_rank = INT_MIN;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = degrees[i] + degrees[j];
                if (adj_lists[i].contains(j)) {
                    rank--;
                }
                max_rank = max(max_rank, rank);
            }
        }
        return max_rank;
    }

private:
    static std::vector<int> count_node_degrees(
            const std::vector<std::unordered_set<int>> &adj_lists
    ) {
        std::vector<int> degrees(adj_lists.size());
        for (int i = 0; i < adj_lists.size(); i++) {
            degrees[i] = adj_lists[i].size();
        }
        return degrees;
    }

    static std::vector<std::unordered_set<int>> make_adjacency_lists(
            int n,
            const std::vector<std::vector<int>> &edges
    ) {
        std::vector<std::unordered_set<int>> adj_lists(n);
        for (const auto &edge: edges) {
            const int first = edge[0];
            const int second = edge[1];
            adj_lists[first].insert(second);
            adj_lists[second].insert(first);
        }
        return adj_lists;
    }

};

int main() {
    vector<vector<int>> roads{{0,1},{0,3},{1,2},{1,3},{2,3},{2,4}};
    cout << Solution().maximalNetworkRank(5, roads);
}
