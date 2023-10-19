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
#include <charconv>

#include "container_utils.h"
#include "tree_node.h"

using namespace std;

class LRUCache {
public:
    using key = int;
    using value = int;
    using cache_entry = pair<key, value>;

    explicit LRUCache(int capacity) : capacity(capacity) {}

    int get(key key) {
        if (not_contains(key)) {
            return -1;
        }
        auto entry_ptr = entry_pointers[key];
        const auto value = entry_ptr->second;
        update_cache_entry(entry_ptr);
        return value;
    }

    void put(int key, int value) {
        if (contains(key)) {
            entry_pointers[key]->second = value;
            update_cache_entry(entry_pointers[key]);
        } else {
            cache.emplace_front(key, value);
            entry_pointers[key] = cache.begin();
            if (cache.size() > capacity) {
                remove_lru();
            }
        }
    }

private:
    using cache_type = list<cache_entry>;
    using iterator = cache_type::iterator;

    cache_type cache;
    unordered_map<key, iterator> entry_pointers;
    int capacity;

    void update_cache_entry(iterator &ptr) {
        const auto entry = *ptr;
        cache.erase(ptr);
        cache.emplace_front(entry);
        entry_pointers[entry.first] = cache.begin();
    }

    void remove_lru() {
        const auto lru = cache.back();
        entry_pointers.erase(lru.first);
        cache.pop_back();
    }

    bool contains(key key) const {
        return entry_pointers.contains(key);
    }

    bool not_contains(key key) const {
        return !contains(key);
    }

};

int main() {
    LRUCache cache(2);
    vector<int> entries;
    for (int i = 1; i < 10; i++) {
        entries.emplace_back(i);
    }
    cache.put(entries[0], entries[0]);
    cache.put(entries[1], entries[1]);
    cout << cache.get(entries[0]) << endl;
    cache.put(entries[2], entries[2]);
    cout << cache.get(entries[1]) << endl;
    cache.put(entries[3], entries[3]);
    cout << cache.get(entries[0]) << endl;
    cout << cache.get(entries[2]) << endl;
    cout << cache.get(entries[3]) << endl;
}