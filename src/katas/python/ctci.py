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
