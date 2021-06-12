/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node nextF, nextB;
    }

    private Node first, last;
    private int count;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return (first == null) || (last == null);
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            node.nextB = null;
            node.nextF = null;
            first = node;
            last = node;
        }
        else {
            node.nextF = first;
            node.nextB = null;
            first.nextB = node;
            first = node;
        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("argument cannot be null");
        }
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            node.nextB = null;
            node.nextF = null;
            first = node;
            last = node;
        }
        else {
            node.nextF = null;
            node.nextB = last;
            last.nextF = node;
            last = node;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty!");
        }
        Item item = first.item;
        first = first.nextF;
        if (isEmpty()) {
            last = null;
        }
        else {
            first.nextB = null;
        }
        count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty!");
        }
        Item item = last.item;
        last = last.nextB;
        if (isEmpty()) {
            first = null;
        } else {
            last.nextF = null;
        }
        count--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException("no items to return");
            }
            Item item = current.item;
            current = current.nextF;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("you cannot remove");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("aaa");
        deque.addFirst("sss");
        deque.addFirst("ddd");
        deque.addLast("LLLL");
        System.out.println("Deque:");
        for (String el : deque) {
            System.out.println(el);
        }
        System.out.println("deque.isEmpty()? - " + deque.isEmpty());
        System.out.println("size = " + deque.size());
        System.out.println("removed first = " + deque.removeFirst());
        System.out.println("removed last = " + deque.removeLast());
        System.out.println("Deque after removing of several items:");
        for (String el : deque) {
            System.out.println(el);
        }
    }

}