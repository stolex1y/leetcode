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
#include <span>
#include <valarray>

using namespace std;

class Solution {
public:
    vector<int> findClosestElements(vector<int> &arr, int k, int x) {
        int start = find_insertion_position(arr, x);
        k = min(static_cast<int>(arr.size()), k);
        if (start == 0)
            return {arr.begin(), arr.begin() + k};
        if (start == arr.size())
            return {arr.begin() + (arr.size() - k), arr.end()};
        // result = arr[left,right - 1];
        int right = start;
        int left = start;
        for (int j = 0; j < k; j++) {
            if (left == 0) {
                right++;
            } else if (right >= arr.size()) {
                left--;
            } else if (x - arr[left - 1] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        return {arr.begin() + left, arr.begin() + right};
    }

private:
    static int find_insertion_position(const vector<int> &arr, int x) {
        int left = 0;
        int right = arr.size() - 1;
        // arr[left] < x, arr[right] >= x
        if (arr[left] >= x)
            return 0;
        if (arr[right] < x)
            return arr.size();
        while (right - left > 1) {
            const int mid = right - (right - left) / 2;
            if (arr[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left + 1;
    }
};

int main() {
    vector<int> arr{1, 2, 3, 4, 5};
    auto result = Solution().findClosestElements(arr, 4, 3);
    copy(result.begin(), result.end(), ostream_iterator<int>(cout, " "));
    return 0;
}