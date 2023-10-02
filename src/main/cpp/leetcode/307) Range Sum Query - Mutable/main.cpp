#include <vector>
#include <array>
#include <queue>
#include <memory>
#include <iostream>
#include <algorithm>
#include <numeric>

#include "test_utils.h"
#include "container_utils.h"
#include "list_node.h"

using namespace std;

class SegmentTree {
public:
    explicit SegmentTree(initializer_list<int> list) :
            SegmentTree(vector<int>(list)) {}

    explicit SegmentTree(const vector<int> &ints) :
            sums(vector<int>(get_sums_arr_size(ints.size()), 0)),
            size(ints.size()) {
        build_sums(ints, full_segment(), 0);
    }

    void update(int index, int val) {
        update(index, val, full_segment(), 0);
    }

    int sumRange(int left, int right) {
        return get_sum(segment{left, right + 1}, full_segment(), 0);
    }

private:
    using size_t = vector<int>::size_type;

    vector<int> sums;
    size_t size;

    struct segment {
        size_t left;
        size_t right;

        segment(int left, int right) :
                segment(size_t(left), size_t(right)) {}

        segment(size_t left, size_t right) :
                left(left),
                right(right) {}

        size_t mid() const {
            return left + (right - left) / 2;
        }

        size_t len() const {
            return right - left;
        }

        bool operator==(const segment &other) const {
            return left == other.left && right == other.right;
        }
    };

    static int get_sums_arr_size(vector<int>::size_type src_arr_size) {
        int sums_arr_size = 1;
        while (sums_arr_size < src_arr_size)
            sums_arr_size <<= 1;
        return sums_arr_size * 2;
    }

    static size_t get_left_child(size_t v) {
        return v * 2 + 1;
    }

    static size_t get_right_child(size_t v) {
        return v * 2 + 2;
    }

    segment full_segment() const {
        return segment{size_t(0), size};
    }

    void build_sums(const vector<int> &src, segment cur_seg, size_t v) {
        if (cur_seg.len() == 1) {
            sums[v] = src[cur_seg.left];
        } else {
            const size_t left_child = get_left_child(v);
            const size_t right_child = get_right_child(v);

            size_t src_m = cur_seg.mid();
            build_sums(src, segment{cur_seg.left, src_m}, left_child);
            build_sums(src, segment{src_m, cur_seg.right}, right_child);
            sums[v] = sums[left_child] + sums[right_child];
        }
    }

    int get_sum(segment req_seg, segment cur_seg, size_t v) const {
        if (req_seg.left >= req_seg.right)
            return 0;
        if (req_seg == cur_seg)
            return sums[v];
        const size_t mid = cur_seg.mid();
        return get_sum(
                segment{req_seg.left, min(mid, req_seg.right)},
                segment{cur_seg.left, mid},
                get_left_child(v)
        ) + get_sum(
                segment{max(req_seg.left, mid), req_seg.right},
                segment{mid, cur_seg.right},
                get_right_child(v)
        );
    }

    void update(size_t upd_i, int new_val, segment cur_seg, size_t v) {
        if (cur_seg.len() == 1) {
            sums[v] = new_val;
        } else {
            const size_t mid = cur_seg.mid();
            const size_t left_child = get_left_child(v);
            const size_t right_child = get_right_child(v);
            if (upd_i < mid) {
                update(upd_i, new_val, segment{cur_seg.left, mid}, left_child);
            } else {
                update(upd_i, new_val, segment{mid, cur_seg.right}, right_child);
            }
            sums[v] = sums[left_child] + sums[right_child];
        }
    }
};

using NumArray = SegmentTree;

int main() {
    vector<int> src{5, 3, 2, 4, 7};
    NumArray array(src);
    for (int i = 0; i < src.size(); i++) {
        for (int j = i; j < src.size(); j++) {
            const auto expected_sum = std::accumulate(src.begin() + i, src.begin() + j + 1, 0);
            const auto actual_sum = array.sumRange(i, j);
            assert_equals(
                    expected_sum,
                    actual_sum
            );
        }
    }
    return 0;
}

/**
 * Your SegmentTree object will be instantiated and called as such:
 * SegmentTree* obj = new SegmentTree(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */