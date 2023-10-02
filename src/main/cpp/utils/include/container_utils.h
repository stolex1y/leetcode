#ifndef LEETCODE_CONTAINER_UTILS_H
#define LEETCODE_CONTAINER_UTILS_H

#include <vector>
#include <iostream>
#include <sstream>
#include <algorithm>
#include <string>

template <typename T>
std::ostream & operator <<(std::ostream &out, std::vector<T> vector) {
    out << "[";
    for (auto it = vector.begin(), end = vector.end(); it < end; it++) {
        out << *it;
        if (it + 1 != end) {
            out << ",";
        }
    }
    out << "]";
    return out;
}

#endif //LEETCODE_CONTAINER_UTILS_H
