#ifndef LEETCODE_LIST_NODE_H
#define LEETCODE_LIST_NODE_H

#include <memory>
#include <string>
#include <sstream>
#include <utility>

struct ListNodeSmart {
    int val;
    std::shared_ptr<ListNodeSmart> next;

    ListNodeSmart() : val(0), next(nullptr) {}

    explicit ListNodeSmart(int x) : val(x), next(nullptr) {}

    ListNodeSmart(int x, const std::shared_ptr<ListNodeSmart> &next) :
            val(x),
            next(next) {}

    static std::unique_ptr<ListNodeSmart> make_list(const std::initializer_list<int> &init_list) {
        std::unique_ptr<ListNodeSmart> head;
        std::shared_ptr<ListNodeSmart> prev;
        for (auto i: init_list) {
            if (!head)
                head = std::make_unique<ListNodeSmart>(i);
            else {
                const auto node = std::make_shared<ListNodeSmart>(i);
                if (!prev)
                    head->next = node;
                else
                    prev->next = node;
                prev = node;
            }
        }
        return head;
    }

    friend bool operator==(
            const std::shared_ptr<const ListNodeSmart> &first_head,
            const std::shared_ptr<const ListNodeSmart> &second_head
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

    friend std::ostream &operator<<(std::ostream &os, const std::shared_ptr<const ListNodeSmart> &head) {
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

struct ListNode {
    int val = 0;
    ListNode *next = nullptr;

    ListNode() = default;

    explicit ListNode(int x) : val(x), next(nullptr) {}

    ListNode(int x, ListNode *next) :
            val(x),
            next(next) {}

    static ListNode *make_list(const std::initializer_list<int> &init_list) {
        ListNode *head = nullptr;
        ListNode *prev = nullptr;
        for (auto i: init_list) {
            auto new_node = new ListNode(i);
            if (!head)
                head = new_node;
            else {
                if (!prev)
                    head->next = new_node;
                else
                    prev->next = new_node;
                prev = new_node;
            }
        }
        return head;
    }

    static bool equals(const ListNode *first, const ListNode *second) {
        while (first && second) {
            if (first->val != second->val)
                return false;
            first = first->next;
            second = second->next;
        }
        return !first && !second;
    }

    friend std::ostream &operator<<(std::ostream &os, const ListNode *head) {
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

#endif //LEETCODE_LIST_NODE_H
