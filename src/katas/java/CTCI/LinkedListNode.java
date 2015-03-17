package katas.java;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class LinkedListNode<Item> extends Object implements Iterable<Item> {
    public final Item val;
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

