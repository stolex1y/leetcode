#include <algorithm>
#include <array>
#include <cassert>
#include <charconv>
#include <functional>
#include <iostream>
#include <list>
#include <map>
#include <memory>
#include <numeric>
#include <optional>
#include <queue>
#include <set>
#include <stack>
#include <thread>
#include <unordered_set>
#include <valarray>
#include <vector>

#include "container_utils.h"
#include "tree_node.h"

using namespace std;

class Foo {
   public:
    Foo() {
    }

    void first(function<void()> printFirst) {
        // printFirst() outputs "first". Do not change or remove this line.
        printFirst();
        f1.store(true, memory_order::release);
        f1.notify_one();
    }

    void second(function<void()> printSecond) {
        // printSecond() outputs "second". Do not change or remove this line.
        f1.wait(false, memory_order::acquire);
        printSecond();
        f2.store(true, memory_order::release);
        f2.notify_one();
    }

    void third(function<void()> printThird) {
        // printThird() outputs "third". Do not change or remove this line.
        f2.wait(false, memory_order::acquire);
        printThird();
    }

   private:
    atomic_bool f1{false};
    char padding[std::hardware_destructive_interference_size];
    atomic_bool f2{false};
};

int main() {
    int a, b, c;
    cin >> a >> b >> c;
    Foo foo{};
    const auto thread_print = [&foo](int num) {
        if (num == 1) {
            foo.first([] {
                cout << "first";
            });
        } else if (num == 2) {
            foo.second([] {
                cout << "second";
            });
        } else if (num == 3) {
            foo.third([] {
                cout << "third";
            });
        } else {
            cout << "invalid";
        }
    };
    std::jthread t_a{[a, thread_print]() {
        thread_print(a);
    }};
    std::jthread t_b{[b, thread_print]() {
        thread_print(b);
    }};
    std::jthread t_c{[c, thread_print]() {
        thread_print(c);
    }};
}
