#ifndef UTILS_GRAPH_H
#define UTILS_GRAPH_H

#include <functional>
#include <string>
#include <stdexcept>
#include <sstream>
#include <optional>
#include <boost/range/combine.hpp>

template<typename T>
void assert_equals(const T &expected, const T &actual,
                   const std::function<bool(const T &, const T &)> equal_to = [](const auto &t1,
                                                                                 const auto &t2) { return t1 == t2; },
                   const std::function<std::string(const T &, const T &)> &msg_provider = [](const T &expected,
                                                                                             const T &actual) {
                       std::stringstream stream;
                       stream << "Expected " << expected << ", but actual's " <<
                              actual;
                       return stream.str();
                   }
) {
    if (!equal_to(expected, actual))
        throw std::runtime_error(msg_provider(expected, actual));
}

template<typename T>
void assert_equals(const T &expected, const T &actual,
                   const std::function<std::string(const T &, const T &)> &msg_provider) {
    assert_equals<T>(expected, actual, [](const auto &t1,
                                          const auto &t2) { return t1 == t2; }, msg_provider);
}

template<typename T, typename Equal>
void assert_equals(const T &expected, const T &actual,
                   const std::function<std::string(const T &, const T &)> &msg_provider = [](const T &expected,
                                                                                             const T &actual) {
                       std::stringstream stream;
                       stream << "Expected " << expected << ", but actual's " <<
                              actual;
                       return stream.str();
                   }
) {
    assert_equals<T>(
            expected,
            actual,
            [equal = Equal()](const T &t1, const T &t2) { return equal(t1, t2); },
            msg_provider
    );
}

void assert_true(bool actual,
                 const std::function<std::string(
                         void)> &msg_provider = []() { return "Expected true, but actual's false"; }) {
    assert_equals(true, actual, {[&msg_provider](auto &_1, auto &_2) { return msg_provider(); }});
}

void assert_false(bool actual,
                  const std::function<std::string(
                          void)> &msg_provider = []() { return "Expected false, but actual's true"; }) {
    assert_equals(false, actual, {[&msg_provider](auto &_1, auto &_2) { return msg_provider(); }});
}

template<typename T, typename Container>
void assert_equals_content(
        const Container &expected,
        const Container &actual,
        const std::function<std::string(const T &, const T &)> &msg_provider
) {
    for (auto [t1, t2]: boost::combine(expected, actual)) {
        assert_equals(t1, t2, msg_provider);
    }
}

template<typename T, typename Container>
void assert_equals_content(
        const Container &expected,
        const Container &actual
) {
    for (auto [t1, t2]: boost::combine(expected, actual)) {
        assert_equals(t1, t2);
    }
}

/*template<typename T, typename Container, typename ValueToString>
void assert_equals_content(
        const Container &expected,
        const Container &actual,
        const ValueToString &value_to_string
) {
    for (auto [t1, t2]: boost::combine(expected, actual)) {
        assert_equals(t1, t2, [value_to_string](const auto &expected, const auto &actual){
            std::stringstream stream;
            stream << "Expected " << value_to_string(expected) << ", but actual's " <<
                   value_to_string(actual);
            return stream.str();
        });
    }
}*/

#endif //UTILS_GRAPH_H
