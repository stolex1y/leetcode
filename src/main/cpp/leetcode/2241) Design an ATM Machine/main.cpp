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

class ATM {
public:
    ATM() : banknotes(denominations.size(), 0) {

    }

    void deposit(vector<int> banknotes_count) {
        assert(banknotes_count.size() == denominations.size());
        for (int i = 0; i < banknotes_count.size(); i++) {
            banknotes[i] += banknotes_count[i];
        }
    }

    vector<int> withdraw(int amount) {
        int rest = amount;
        vector<int> result(denominations.size(), 0);
        for (int i = denominations.size() - 1; i >= 0; i--) {
            const int banknote = denominations[i];
            if (rest >= banknote) {
                const int banknote_count = min(banknotes[i], static_cast<long>(rest / banknote));
                rest -= banknote_count * banknote;
                result[i] = banknote_count;
            }
        }
        if (rest > 0)
            return {-1};
        withdraw(result);
        return result;
    }

private:
    const vector<int> denominations{20, 50, 100, 200, 500};
    vector<long> banknotes;

    void withdraw(const vector<int> &withdrawn) {
        for (int i = 0; i < denominations.size(); i++) {
            banknotes[i] = max(0L, banknotes[i] - withdrawn[i]);
        }
    }
};
