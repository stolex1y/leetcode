#include <vector>
#include <array>
#include <queue>
#include <memory>
#include <iostream>
#include <algorithm>
#include <numeric>
#include <map>

#include "test_utils.h"
#include "container_utils.h"
#include "list_node.h"

using namespace std;

class MyCalendarThree {
public:
    MyCalendarThree() {

    }

    int book(int start_time, int end_time) {
        segment_points[start_time]++;
        segment_points[end_time]--;
        int count = 0;
        int max = 0;
        for (auto point : segment_points) {
            count += point.second;
            max = std::max(max, count);
        }
        return max;
    }

private:
    map<int, int> segment_points;
};

int main() {
    MyCalendarThree three;
    cout << three.book(24, 40) << " ";
    cout << three.book(43, 50) << " ";
    cout << three.book(27, 43) << " ";
    cout << three.book(5, 21) << " ";
    cout << three.book(30, 40) << " ";
    cout << three.book(14, 29) << " ";
    cout << three.book(3, 19) << " ";
    cout << three.book(3, 14) << " ";
    cout << three.book(25, 39) << " ";
    cout << three.book(6, 19) << " ";
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree* obj = new MyCalendarThree();
 * int param_1 = obj->book(startTime,endTime);
 */