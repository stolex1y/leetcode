#ifndef TEST_UTILS_GRAPH_H
#define TEST_UTILS_GRAPH_H

#include <functional>
#include <string>
#include <stdexcept>
#include <sstream>
#include <boost/range/combine.hpp>

template<typename T, typename Equal = std::equal_to<T>>
void assert_equals(const T &expected, const T &actual,
                   const std::function<std::string(const T &, const T &)> &msgProvider = [](const T &expected,
                                                                                            const T &actual) {
                       std::stringstream stream;
                       stream << "Expected" << expected << ", but actual's " <<
                              actual;
                       return stream.str();
                   }
) {
    Equal equal;
    if (!equal(expected, actual))
        throw std::runtime_error(msgProvider(expected, actual));
}

void assert_true(bool actual,
                 const std::function<std::string(
                         void)> &msgProvider = []() { return "Expected true, but actual's false"; }) {
    assert_equals(true, actual, {[&msgProvider](auto &_1, auto &_2) { return msgProvider(); }});
}

void assert_false(bool actual,
                  const std::function<std::string(
                          void)> &msgProvider = []() { return "Expected false, but actual's true"; }) {
    assert_equals(false, actual, {[&msgProvider](auto &_1, auto &_2) { return msgProvider(); }});
}

template<typename T, typename Container>
void assert_equals_content(
        const Container &expected,
        const Container &actual,
        const std::function<std::string(
                const T &, const T &)> &msgProvider = [](T expected, T actual) {
            std::stringstream stream;
            stream << "Expected" << expected << ", but actual's " <<
                   actual;
            return stream.str();
        }
) {
    for (auto tuple: boost::combine(expected, actual)) {
        assert_equals(expected, actual, [&msgProvider, &tuple]() { return msgProvider(get<0>(tuple), get<1>(tuple)); });
    }
}

#endif //TEST_UTILS_GRAPH_H
