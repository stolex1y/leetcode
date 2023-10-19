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

using namespace std;

class Solution {
public:
    vector<vector<int>> diagonalSort(const vector<vector<int>> &mat) {
        vector<vector<int>> result(mat.size(), vector<int>(mat[0].size()));
        vector<int> diagonal(mat[0].size());
        for (int j = 0; j < mat[0].size(); j++) {
            int right = fill_diagonal(mat, diagonal, 0, j);
            sort(diagonal.begin(), diagonal.begin() + right);
            set_diagonal(result, diagonal, 0, j);
        }
        for (int i = 1; i < mat.size(); i++) {
            int right = fill_diagonal(mat, diagonal, i, 0);
            sort(diagonal.begin(), diagonal.begin() + right);
            set_diagonal(result, diagonal, i, 0);
        }
        return result;
    }

private:
    int fill_diagonal(const vector<vector<int>> &mat, vector<int> &vec,
                      int i, int j) {
        int vec_i = 0;
        while (i < mat.size() && j < mat[0].size()) {
            vec[vec_i++] = mat[i++][j++];
        }
        return vec_i;
    }

    void set_diagonal(vector<vector<int>> &mat, vector<int> &vec,
                      int i, int j) {
        int vec_i = 0;
        while (i < mat.size() && j < mat[0].size()) {
            mat[i++][j++] = vec[vec_i++];
        }
    }
};