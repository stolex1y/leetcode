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

using namespace std;

// Backtracking
class Solution {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> ip_addresses;
        restore_ip_addresses(s, ip_addresses);
        return ip_addresses;
    }

private:

    void restore_ip_addresses(const string &s, vector<string> &ip_addresses) {
        vector<int> groups;
        restore_ip_addresses(s, ip_addresses, 0, groups);
    }

    void restore_ip_addresses(const string &s, vector<string> &ip_addresses, int start, vector<int> &groups) {
        if (groups.size() == 4) {
            if (start == s.size())
                ip_addresses.emplace_back(make_ip(groups));
            return;
        }
        int group = 0;
        int prev = -1;
        for (int i = start; i < s.size(); i++) {
            group = group * 10 + (s[i] - '0');
            if (group == prev || group > 255)
                return;
            prev = group;
            groups.emplace_back(group);
            restore_ip_addresses(s, ip_addresses, i + 1, groups);
            groups.pop_back();
            if (group == 0)
                return;
        }
    }

    string make_ip(const vector<int> &groups) {
        string ip;
        ip += to_string(groups[0]);
        for (int i = 1; i < groups.size(); i++) {
            ip += '.';
            ip += to_string(groups[i]);
        }
        return ip;
    }
};

class Solution2 {
public:
    vector<string> restoreIpAddresses(string s) {
        vector<string> ip_addresses;
        for (int a = 1; a <= 3; a++) {
            const int A = parse_group(s, 0, a);
            if (A > 255)
                continue;
            for (int b = 1; b <= 3; b++) {
                const int B = parse_group(s, a, b);
                if (B > 255)
                    continue;
                for (int c = 1; c <= 3; c++) {
                    const int C = parse_group(s, a + b, c);
                    if (C > 255)
                        continue;
                    const int d = s.size() - (a + b + c);
                    if (d <= 0)
                        continue;
                    const int D = parse_group(s, a + b + c, d);
                    if (D > 255)
                        continue;
                    const auto ip_str = make_ip({A, B, C, D});
                    if (ip_str.size() == s.size() + 3)
                        ip_addresses.emplace_back(ip_str);
                }
            }
        }
        return ip_addresses;
    }

private:
    int parse_group(const string &s, int start, int n) {
        int group = 0;
        const auto start_ptr = s.data() + start;
        from_chars(start_ptr, start_ptr + n, group, 10);
        return group;
    }

    string make_ip(const array<int, 4> groups) {
        string ip;
        ip += to_string(groups[0]);
        for (int i = 1; i < groups.size(); i++) {
            ip += '.';
            ip += to_string(groups[i]);
        }
        return ip;
    }
};

int main() {
    string s = "25525511135";
    auto result = Solution2().restoreIpAddresses(s);
    cout << result;
}