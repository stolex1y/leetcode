#ifndef TEST_UTILS_GRAPH_H
#define TEST_UTILS_GRAPH_H

#include <functional>
#include <string>
#include <stdexcept>

template<typename T>
void assert_equals(const T &expected, const T &actual,
                   const std::function<std::string(void)> &msgProvider = []() { return ""; }) {
    if (expected != actual)
        throw std::runtime_error(msgProvider());
}

void assert_true(bool actual,
                 const std::function<std::string(
                         void)> &msgProvider = []() { return "Expected true, but actual's false"; }) {
    assert_equals(true, actual, msgProvider);
}

void assert_false(bool actual,
                  const std::function<std::string(
                          void)> &msgProvider = []() { return "Expected false, but actual's true"; }) {
    assert_equals(false, actual, msgProvider);
}

#endif //TEST_UTILS_GRAPH_H
