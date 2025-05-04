#include <algorithm>
#include <array>
#include <cassert>
#include <charconv>
#include <functional>
#include <iostream>
#include <list>
#include <map>
#include <memory>
#include <mutex>
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

template <size_t philosopher_count = 5>
class DiningPhilosophers {
   public:
    void wantsToEat(
        int philosopher,
        function<void()> pickLeftFork,
        function<void()> pickRightFork,
        function<void()> eat,
        function<void()> putLeftFork,
        function<void()> putRightFork
    ) {
        unique_lock<mutex> left_fork(_forks_availability[getLeftForkForPhilosopher(philosopher)], defer_lock);
        unique_lock<mutex> right_fork(_forks_availability[getRightForkForPhilosopher(philosopher)], defer_lock);
        if (philosopher % 2 == 0) {
            left_fork.lock();
            right_fork.lock();
        } else {
            right_fork.lock();
            left_fork.lock();
        }
        pickLeftFork();
        pickRightFork();
        eat();
        putRightFork();
        putLeftFork();
    }

   private:
    void waitForkAvailability(size_t fork_idx) {
        bool was_available = true;
        while (not _forks_availability[fork_idx].compare_exchange_strong(was_available, false)) {
        }
    }

    size_t getLeftForkForPhilosopher(size_t phil_id) {
        return phil_id;
    }

    size_t getRightForkForPhilosopher(size_t phil_id) {
        return (phil_id + 1) % philosopher_count;
    }

    std::array<mutex, philosopher_count> _forks_availability{};
};

template <size_t philosopher_count = 5>
class DiningPhilosophersWithSpinLock {
   public:
    void wantsToEat(
        int philosopher,
        function<void()> pickLeftFork,
        function<void()> pickRightFork,
        function<void()> eat,
        function<void()> putLeftFork,
        function<void()> putRightFork
    ) {
        const auto left_fork_idx = getLeftForkForPhilosopher(philosopher);
        const auto right_fork_idx = getRightForkForPhilosopher(philosopher);
        if (philosopher % 2 == 0) {
            waitForkAvailability(left_fork_idx);
            waitForkAvailability(right_fork_idx);
        } else {
            waitForkAvailability(right_fork_idx);
            waitForkAvailability(left_fork_idx);
        }
        pickLeftFork();
        pickRightFork();
        eat();
        putRightFork();
        putLeftFork();
        unlockFork(left_fork_idx);
        unlockFork(right_fork_idx);
    }

   private:
    void waitForkAvailability(size_t fork_idx) {
        while (_forks_lock[fork_idx].test_and_set(memory_order_acquire)) {
        }
    }

    size_t getLeftForkForPhilosopher(size_t phil_id) {
        return phil_id;
    }

    size_t getRightForkForPhilosopher(size_t phil_id) {
        return (phil_id + 1) % philosopher_count;
    }

    void unlockFork(size_t fork_idx) {
        _forks_lock[fork_idx].clear(memory_order_release);
    }

    array<atomic_flag, philosopher_count> _forks_lock{};
};

enum class ForkType {
    e_none = 0,
    e_left = 1,
    e_right = 2,
};

enum class ActionType {
    e_pick = 1,
    e_put = 2,
    e_eat = 3,
};

std::string fork_type_to_string(ForkType type) {
    switch (type) {
        case ForkType::e_none:
            break;
        case ForkType::e_left:
            break;
        case ForkType::e_right:
            break;
    }
}

struct Command {
    uint32_t philosopher;
    ForkType fork_type;
    ActionType action_type;

    [[nodiscard]] std::string toString() const {
        std::stringstream ss;
        ss << '[' << philosopher << ',' << static_cast<uint32_t>(fork_type) << ',' << static_cast<uint32_t>(action_type)
           << ']';
        return ss.str();
    }
};

std::vector<Command> dining_log;
std::mutex log_mutex;

auto make_pick_left_fork_function(size_t phil_id) {
    return [phil_id]() {
        std::lock_guard lock(log_mutex);
        dining_log.emplace_back(phil_id, ForkType::e_left, ActionType::e_pick);
    };
}

auto make_pick_right_fork_function(size_t phil_id) {
    return [phil_id]() {
        std::lock_guard lock(log_mutex);
        dining_log.emplace_back(phil_id, ForkType::e_right, ActionType::e_pick);
    };
}

auto make_put_left_fork_function(size_t phil_id) {
    return [phil_id]() {
        std::lock_guard lock(log_mutex);
        dining_log.emplace_back(phil_id, ForkType::e_left, ActionType::e_put);
    };
}

auto make_put_right_fork_function(size_t phil_id) {
    return [phil_id]() {
        std::lock_guard lock(log_mutex);
        dining_log.emplace_back(phil_id, ForkType::e_right, ActionType::e_put);
    };
}

auto make_eat_function(size_t phil_id) {
    return [phil_id]() {
        std::lock_guard lock(log_mutex);
        dining_log.emplace_back(phil_id, ForkType::e_none, ActionType::e_eat);
    };
}

int main() {
    std::vector<thread> philosophers;

    DiningPhilosophersWithSpinLock dining;
    constexpr size_t eat_count = 40;
    for (int i = 0; i < 5; ++i) {
        philosophers.emplace_back([eat_count, i, &dining]() {
            for (size_t j = 0; j < eat_count; ++j) {
                dining.wantsToEat(
                    i,
                    make_pick_left_fork_function(i),
                    make_pick_right_fork_function(i),
                    make_eat_function(i),
                    make_put_left_fork_function(i),
                    make_put_right_fork_function(i)
                );
            }
        });
    }

    for (auto &phil_thread : philosophers) {
        phil_thread.join();
    }

    std::stringstream ss;
    ss << "[\n";
    for (size_t i = 0; i < dining_log.size(); ++i) {
        ss << dining_log[i].toString();
        if (i + i < dining_log.size()) {
            ss << ",\n";
        } else {
            ss << "\n";
        }
    }
    ss << ']';
    std::cout << ss.str();
}
