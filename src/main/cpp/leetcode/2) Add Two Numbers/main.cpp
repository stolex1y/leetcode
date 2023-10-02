#include <vector>
#include <array>
#include <queue>
#include <memory>
#include <iostream>

#include "test_utils.h"
#include "list_node.h"

using namespace std;

class Solution {
private:
    pair<unique_ptr<ListNode>, int>
    add_two_nodes(const shared_ptr<const ListNode> &node1, const shared_ptr<const ListNode> &node2, bool carry_flag = 0) {
        const int val1 = node1 ? node1->val : 0;
        const int val2 = node2 ? node2->val : 0;
        const int sum = val1 + val2 + (carry_flag ? 1 : 0);
        return make_pair(make_unique<ListNode>(sum % 10), sum / 10);
    }

public:
    unique_ptr<ListNode> addTwoNumbers(
            const shared_ptr<const ListNode> &l1,
            const shared_ptr<const ListNode> &l2
    ) {
        auto cur1 = l1;
        auto cur2 = l2;
        unique_ptr<ListNode> result_head;
        shared_ptr<ListNode> prev;
        bool cf = 0;
        while (cur1 || cur2 || cf) {
            unique_ptr<ListNode> cur_result;
            tie(cur_result, cf) = add_two_nodes(cur1, cur2, cf);
            if (!result_head) {
                result_head = std::move(cur_result);
            } else {
                shared_ptr<ListNode> next_node = std::move(cur_result);
                if (!prev)
                    result_head->next = next_node;
                else
                    prev->next = next_node;
                prev = next_node;
            }
            cur1 = cur1 ? cur1->next : cur1;
            cur2 = cur2 ? cur2->next : cur2;
        }
        return result_head;
    }
};

int main() {
    shared_ptr<const ListNode> &&first = ListNode::make_list({2, 4, 3});
    shared_ptr<const ListNode> &&second = ListNode::make_list({5, 6, 9});
    shared_ptr<const ListNode> expected = ListNode::make_list({7, 0, 3, 1});
    shared_ptr<const ListNode> actual = Solution().addTwoNumbers(first, second);
    assert_equals(expected, actual);
}

