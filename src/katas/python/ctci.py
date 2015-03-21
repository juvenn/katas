import functools

class LinkedListNode:
    def __init__(self, val):
        self.val = val;
        self.next = None;

    def __iter__(self):
        self.nav = self
        return self

    def __next__(self):
        if not self.nav: raise StopIteration
        val      = self.nav.val
        self.nav = self.nav.next
        return val

    def from_seq(seq):
        if len(seq) == 0:
            raise ValueError("Trying to create linked list with empty sequence")
        head = LinkedListNode(seq[0])
        nav  = head
        for val in seq[1:]:
            nav.next = LinkedListNode(val)
            nav = nav.next
        return head

    def append(self, val):
        nav = self
        while nav.next: nav = nav.next
        nav.next = LinkedListNode(val)
        return self


# Solutions to Cracking the code interview
class CTCI:

    ## Remove duplicate items in linked list
    # qn: 2.1
    def remove_duplicate_in_linkedlist(head):
        _set = set([head.val])
        nav  = head
        while nav.next:
            if nav.next.val in _set:
                nav.next = nav.next.next
            else:
                _set.add(nav.next.val)
                nav = nav.next
        return head

    ## Find nth item to last
    # qn: 2.2
    def find_nth_to_last(head, n):
        nav1 = nav2 = head
        if n < 0:
            for i in range(1, -n):
                if nav1: nav1 = nav1.next
                else: raise IndexError("Out of range error")
            return nav1.val
        for i in range(0, n):
            if nav2: nav2 = nav2.next
            else: raise IndexError("Out of range error")
        while nav2.next:
            nav1 = nav1.next
            nav2 = nav2.next
        return nav1.val
