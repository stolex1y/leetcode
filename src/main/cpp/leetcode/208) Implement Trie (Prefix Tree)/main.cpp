#include <string>
#include <map>

#include "test_utils.h"

using namespace std;

class Trie {
private:
    class Node {
    public:
        int value = 0;
        map<char, Node> children;

        Node() = default;

        void insert(const string &word) {
            auto currCharIt = word.begin();
            Node *currNode = this;
            while (currCharIt != word.end()) {
                currNode = &(currNode->children[*currCharIt]);
                currCharIt++;
            }
            currNode->value++;
        }

        bool search(const string &word) const {
            const Node *last = searchLast(word);
            return last != nullptr && last->value > 0;
        }

        bool startsWith(const string &prefix) const {
            const Node *last = searchLast(prefix);
            return last != nullptr;
        }

    private:
        Node *searchLast(const string &word) const {
            auto currCharIt = word.begin();
            const Node *currNode = this;
            while (currCharIt != word.end()) {
                auto nextNode = currNode->children.find(*currCharIt);
                if (nextNode != currNode->children.end()) {
                    currNode = &(nextNode->second);
                } else {
                    return nullptr;
                }
                currCharIt++;
            }
            return const_cast<Node *>(currNode);
        }
    };

public:
    Trie() = default;

    void insert(string word) {
        root.insert(word);
    }

    bool search(string word) {
        return root.search(word);
    }

    bool startsWith(string prefix) {
        return root.startsWith(prefix);
    }

private:
    Node root;
};

int main() {
    Trie trie;
    string str1 = "app";
    string str2 = "apple";
    string str3 = "application";
    trie.insert(str1);
    trie.insert(str2);
    trie.insert(str3);

    assert_true(trie.search(str1));
    assert_true(trie.search(str2));
    assert_true(trie.search(str3));
    assert_false(trie.search(str3 + str1));
    assert_false(trie.search(str1 + "e"));
    assert_true(trie.startsWith("a"));
    assert_true(trie.startsWith("ap"));
    assert_false(trie.startsWith("asdf"));
    return 0;
}
