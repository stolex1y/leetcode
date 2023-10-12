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

using namespace std;

class Solution {
public:
    int maxSumSubmatrix(vector<vector<int>> &matrix, int k) {
        const size_t width = matrix[0].size();
        const size_t height = matrix.size();
        build_prefix_sums(matrix);
        vector<int> row_sums(height);
        int max_sum = INT_MIN;
        for (int left = 0; left < width; left++) {
            for (int right = left; right < width; right++) {
                for (int row = 0; row < height; row++) {
                    const int sum = (left > 0) ?
                                    (matrix[row][right] - matrix[row][left - 1]) :
                                    matrix[row][right];
                    row_sums[row] = sum;
                }
                const optional<int> local_max = find_max_subarray(row_sums, k);
                if (local_max.has_value())
                    max_sum = max(max_sum, local_max.value());
            }
        }
        return max_sum;
    }

private:
    static multimap<int, int> build_prefix_sums(const vector<int> &nums) {
        multimap<int, int> prefix_sums;
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums[i];
            prefix_sums.insert(make_pair(sum, i));
        }
        return prefix_sums;
    }

    static void build_prefix_sums(vector<vector<int>> &matrix) {
        const size_t width = matrix[0].size();
        const size_t height = matrix.size();
        for (int col = 1; col < width; col++) {
            for (int row = 0; row < height; row++) {
                matrix[row][col] += matrix[row][col - 1];
            }
        }
    }

    static optional<int> find_max_subarray(vector<int> &vec, int sum_limit = INT_MAX) {
        const auto prefix_sums = build_prefix_sums(vec);
        optional<int> max_sum;
        for (const auto right: prefix_sums) {
            if (right.first <= sum_limit)
                max_sum = max(max_sum.value_or(INT_MIN), right.first);
            const int left = right.first - sum_limit;
            const auto found = prefix_sums.upper_bound(left - 1);
            if (found != prefix_sums.end()) {
                if (*found != right || prefix_sums.count(found->first) > 1)
                    max_sum = max(max_sum.value_or(INT_MIN), right - *found);
            }
        }
        return max_sum;
    }
};

int main() {
    vector<vector<int>> matrix{{5,-4,-3,4},{-3,-4,4,5},{5,1,5,-4}};
    cout << Solution().maxSumSubmatrix(matrix, 3);
}