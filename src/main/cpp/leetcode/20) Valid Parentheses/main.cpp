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
#include <unordered_map>
#include <cassert>
#include <valarray>
#include <charconv>

#include "container_utils.h"
#include "tree_node.h"

using namespace std;

class Solution {
public:
    bool isValid(string s) {
        using size = string::size_type;
        stack<bracket> brackets;
        for (const auto ch: s) {
            bracket bracket = bracket::parse(ch);
            if (!brackets.empty() && !bracket.opened &&
            brackets.top().is_complementary(bracket)) {
                brackets.pop();
            } else {
                brackets.emplace(bracket);
            }
        }
        return brackets.empty();
    }

private:
    class bracket {
    public:
        enum class form_type : int {
            PARENTHESES,
            SQUARE,
            BRACE
        };

        static bracket parse(char ch) {
            for (auto form : forms) {
                if (ch == form_opened_bracket(form))
                    return {form, true};
                else if (ch == form_closed_bracket(form))
                    return {form, false};
            }
            throw logic_error("Unknown char");
        }

        const form_type form;
        const bool opened;

        bool is_complementary(const bracket &other) const {
            return (form == other.form) && (opened != other.opened);
        }

    private:
        constexpr static array<form_type, 3> forms = {form_type::PARENTHESES, form_type::SQUARE, form_type::BRACE};

        bracket(form_type form, bool opened) : form(form), opened(opened) {}

        static char form_opened_bracket(form_type form) {
            switch (form) {
                case form_type::PARENTHESES:
                    return '(';
                case form_type::SQUARE:
                    return '[';
                case form_type::BRACE:
                    return '{';
            }
        }

        static char form_closed_bracket(form_type form) {
            switch (form) {
                case form_type::PARENTHESES:
                    return ')';
                case form_type::SQUARE:
                    return ']';
                case form_type::BRACE:
                    return '}';
            }
        }
    };
};