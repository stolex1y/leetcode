#ifndef LEETCODE_LIST_NODE_H
#define LEETCODE_LIST_NODE_H

#include <memory>
#include <string>
#include <sstream>

struct ListNode {
    int val;
    std::shared_ptr<ListNode> next;

    ListNode() : val(0), next(nullptr) {}

    ListNode(int x) : val(x), next(nullptr) {}

    ListNode(int x, std::shared_ptr<ListNode> next) : val(x), next(next) {}

    static std::unique_ptr<ListNode> make_list(const std::initializer_list<int> &init_list) {
        std::unique_ptr<ListNode> head;
        std::shared_ptr<ListNode> prev;
        for (auto i : init_list) {
            if (!head)
                head = std::make_unique<ListNode>(i);
            else {
                const auto node = std::make_shared<ListNode>(i);
                if (!prev)
                    head->next = node;
                else
                    prev->next = node;
                prev = node;
            }
        }
        return head;
    }

    static bool equals(
            const std::shared_ptr<const ListNode> &first_head,
            const std::shared_ptr<const ListNode> &second_head
    ) {
        auto first = first_head;
        auto second = second_head;
        while (first && second) {
            if (first->val != second->val)
                return false;
            first = first->next;
            second = second->next;
        }
        return !first && !second;
    }

    friend std::ostream & operator<<(std::ostream &os, const std::shared_ptr<const ListNode> head) {
        auto cur = head;
        os << "[";
        while (cur) {
            if (cur != head)
                os << ",";
            os << cur->val;
            cur = cur->next;
        }
        os << "]";
        return os;
    }
};

namespace std {
    template <>
    struct equal_to<shared_ptr<const ListNode>> {
        bool operator()(
                const shared_ptr<const ListNode> &first,
                const shared_ptr<const ListNode> &second
                ) {
            return ListNode::equals(first, second);
        }
    };
}

#endif //LEETCODE_LIST_NODE_H
