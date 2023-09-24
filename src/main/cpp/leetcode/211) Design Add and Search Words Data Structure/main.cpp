#include <vector>
#include <array>
#include <cassert>
#include <algorithm>
#include <map>
#include <string>
#include <sstream>

#include "test_utils.h"

using namespace std;

class WordDictionary {

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
            const Node *last = searchLast(*this, word.begin(), word.end());
            return last != nullptr && last->value > 0;
        }

    private:
        static Node *searchLast(const Node &root, string::const_iterator begin, string::const_iterator end) {
            if (begin == end)
                return const_cast<Node *>(&root);

            const char ch = *begin;
            if (ch == '.') {
                for (auto &pair: root.children) {
                    auto foundLast = searchLast(pair.second, begin + 1, end);
                    if (foundLast)
                        return foundLast;
                }
                return nullptr;
            } else {
                auto nextNode = root.children.find(ch);
                if (nextNode != root.children.end()) {
                    return searchLast(nextNode->second, begin + 1, end);
                } else {
                    return nullptr;
                }
            }
        }
    };

public:
    void addWord(string word) {
        root.insert(word);
    }

    bool search(string word) {
        return root.search(word);
    }

private:
    Node root;
};


int main() {
    WordDictionary dictionary;
    stringstream sstream;
    stringstream dots;
    for (int i = 0; i < 25; i++) {
        sstream << 'a';
        dots << '.';
    }
    for (int i = 0; i < 25; i++) {
        const string s = sstream.str().substr(0, 25 - i);
        const string pattern = dots.str().substr(0, 25 - i);
        dictionary.addWord(s);
    }
    for (int i = 0; i < 25; i++) {
        const string s = sstream.str().substr(0, 25 - i);
        const string pattern = dots.str().substr(0, 25 - i);
        assert_true(dictionary.search(s), [s](){return "Couldnt find: " + s;});
        assert_true(dictionary.search(pattern), [pattern](){return "Couldnt find: " + pattern;});
    }
    string str1 = "bad";
    string str2 = "dad";
    string str3 = "mada";
    string str4 = "madaa";
    dictionary.addWord(str1);
    dictionary.addWord(str2);
    dictionary.addWord(str3);

    assert_true(dictionary.search(str1));
    assert_true(dictionary.search(str2));
    assert_true(dictionary.search(str3));
    assert_false(dictionary.search(str3 + str1));
    assert_false(dictionary.search(str1 + "e"));
    assert_true(dictionary.search(".ad"));
    assert_true(dictionary.search("mad."));
    assert_true(dictionary.search("m.d."));
    assert_true(dictionary.search(".."));
    assert_true(dictionary.search("...."));
    return 0;
}
