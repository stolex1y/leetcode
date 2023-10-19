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

class RandomizedSet {
public:
    RandomizedSet() {
        srand(time(0));
    }

    bool insert(int val) {
        if (map.contains(val))
            return false;
        map[val] = static_cast<int>(vec.size());
        vec.push_back(val);
        return true;
    }

    bool remove(int val) {
        if (!map.contains(val))
            return false;
        const int index = map[val];
        map.erase(val);
        if (vec.size() > 1 && index != vec.size() - 1) {
            const int last = vec.back();
            vec[index] = last;
            map[last] = index;
        }
        vec.pop_back();
        return true;
    }

    int getRandom() {
        const int index = rand() % vec.size();
        return vec[index];
    }

private:
    unordered_map<int, int> map;
    vector<int> vec;
};

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet* obj = new RandomizedSet();
 * bool param_1 = obj->insert(val);
 * bool param_2 = obj->remove(val);
 * int param_3 = obj->getRandom();
 */