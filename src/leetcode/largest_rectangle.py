#! /usr/bin/python
#

class Solution:
    # @param height, a list of integers
    # @return an integer
    def largestRectangle(self, height):
        return self.largestByPartition(height)

    # Given array vals, partition it from lo to hi (exclusive)
    # @return a tuple of (minv, partitions)
    def partitionByMin(self, vals, lo, hi):
        minv = None
        parts = []
        if hi - lo <= 1:
            return (vals[lo], parts)
        for k,v in enumerate(vals[lo:hi]):
            if not minv:
                start = 1
                minv  = v
            else:
                if v < minv:
                    # smaller value found, flush parts and add previous
                    # as a partition
                    parts = [] # smaller value found, flush parts
                    parts.append([lo, lo+k])
                    start = k + 1
                    minv  = v
                elif v == minv:
                    if k > start: parts.append([lo+start, lo+k])
                    start = k + 1
        if lo + start < hi: parts.append([lo+start, hi])
        return (minv, parts)


    def largestByPartition(self, height):
        maxarea    = 0
        partitions = []
        partitions.append([0, len(height)])
        while partitions:
            (lo, hi) = partitions.pop()
            width          = hi - lo
            if  width == 1:
                maxarea = max(maxarea, height[lo])
            elif width > 1:
                (minv, parts) = self.partitionByMin(height, lo, hi)
                partitions   += parts
                maxarea       = max(maxarea, minv * width)
        return maxarea
