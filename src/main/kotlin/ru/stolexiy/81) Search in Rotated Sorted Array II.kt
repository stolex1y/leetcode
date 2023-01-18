package ru.stolexiy

private object Solution81 {
    fun search(nums: IntArray, target: Int): Boolean {
        var l = 0
        var r = nums.size - 1
        while (r > l) {
            val m = l + (r - l) / 2
            if (nums[m] == target)
                return true

            while (r > l && (nums[m] == nums[r]) && (nums[m] == nums[l])) { //pivot can be everywhere
                if (nums[l] == target)
                    return true
                l++
                r--
            }

            if (nums[m] >= nums[l]) { //left part sorted
                if (target in nums[l]..nums[m])
                    r = m - 1
                else
                    l = m + 1
            } else { //right part sorted
                if (target in nums[m]..nums[r])
                    l = m + 1
                else
                    r = m - 1
            }
        }
        return nums[l] == target
    }
}