#include <vector>
#include <array>
#include <queue>
#include <memory>
#include <iostream>

#include "test_utils.h"
#include "list_node.h"

using namespace std;

// with smart pointers
class Solution {
private:
    static unique_ptr<ListNodeSmart>
    add_two_nodes(
            const shared_ptr<const ListNodeSmart> &node1,
            const shared_ptr<const ListNodeSmart> &node2,
            bool &carry_flag
    ) {
        const int val1 = node1 ? node1->val : 0;
        const int val2 = node2 ? node2->val : 0;
        const int sum = val1 + val2 + (carry_flag ? 1 : 0);
        carry_flag = sum / 10;
        return make_unique<ListNodeSmart>(sum % 10);
    }

public:
    static unique_ptr<ListNodeSmart> addTwoNumbers(
            const shared_ptr<const ListNodeSmart> &l1,
            const shared_ptr<const ListNodeSmart> &l2
    ) {
        auto cur1 = l1;
        auto cur2 = l2;
        unique_ptr<ListNodeSmart> result_head;
        shared_ptr<ListNodeSmart> prev;
        bool cf = false;
        while (cur1 || cur2 || cf) {
            unique_ptr<ListNodeSmart> cur_result = add_two_nodes(cur1, cur2, cf);
            if (!result_head) {
                result_head = std::move(cur_result);
            } else {
                shared_ptr<ListNodeSmart> next_node = std::move(cur_result);
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

// with c-pointers
class Solution2 {
private:
    static ListNode *add_two_nodes(
            const ListNode *node1,
            const ListNode *node2,
            bool &carry_flag
    ) {
        const int val1 = node1 ? node1->val : 0;
        const int val2 = node2 ? node2->val : 0;
        const int sum = val1 + val2 + (carry_flag ? 1 : 0);
        carry_flag = sum / 10;
        return new ListNode(sum % 10);
    }

public:
    static ListNode *addTwoNumbers(
            const ListNode *l1,
            const ListNode *l2
    ) {
        auto cur1 = l1;
        auto cur2 = l2;
        ListNode *result_head = nullptr;
        ListNode *prev = nullptr;
        bool cf = false;
        while (cur1 || cur2 || cf) {
            ListNode *cur_result = add_two_nodes(cur1, cur2, cf);
            if (!result_head) {
                result_head = cur_result;
            } else {
                if (!prev)
                    result_head->next = cur_result;
                else
                    prev->next = cur_result;
                prev = cur_result;
            }
            cur1 = cur1 ? cur1->next : cur1;
            cur2 = cur2 ? cur2->next : cur2;
        }
        return result_head;
    }
};

/*
int main() {
    shared_ptr<const ListNodeSmart> &&first = ListNodeSmart::make_list({2, 4, 3});
    shared_ptr<const ListNodeSmart> &&second = ListNodeSmart::make_list({5, 6, 9});
    shared_ptr<const ListNodeSmart> expected = ListNodeSmart::make_list({7, 0, 3, 1});
    shared_ptr<const ListNodeSmart> actual = Solution::addTwoNumbers(first, second);
    assert_equals(expected, actual);
}
*/

int main() {
    const ListNode *first = ListNode::make_list({2, 4, 3});
    const ListNode *second = ListNode::make_list({5, 6, 9});
    const ListNode *expected = ListNode::make_list({7, 0, 3, 1});
    const ListNode *actual = Solution2::addTwoNumbers(first, second);
    assert_equals<const ListNode *>(expected, actual, ListNode::equals);
}