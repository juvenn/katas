package katas.java;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class LinkedListNode<Item> extends Object implements Iterable<Item> {
    public Item val;
    public LinkedListNode<Item> next = null;
    public LinkedListNode(Item item) { val = item; }

    /**
     * Append item at end
     */
    public void append(Item item) {
        LinkedListNode<Item> nav = this;
        while (nav.next != null) nav = nav.next;
        nav.next = new LinkedListNode<Item>(item);
    }

    /**
     * Walk number of nodes down the singly linked list, return that node.
     */
    public LinkedListNode<Item> walk(int n) {
        LinkedListNode<Item> nav = this;
        for (int i = 0; i < n; i++) {
            if (nav.next != null) nav = nav.next;
            else throw new NoSuchElementException();
        }
        return nav;
    }

    public Iterator<Item> iterator() {
        return new NodeIterator<Item>(this);
    }

    public class NodeIterator<Item> implements Iterator<Item> {
        private LinkedListNode<Item> nav;

        public NodeIterator(LinkedListNode<Item> node) { nav = node; }
        public boolean hasNext() { return nav != null; }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = nav.val;
            nav = nav.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

