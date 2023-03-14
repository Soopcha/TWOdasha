package Classes;

import java.util.Iterator;
/*
21 (*) В списке целых чисел поменять местами первый элемент, содержащий наименьшее
значение, с последним элементом, содержащим наибольшее значение. Обратите
внимание: менять значение (Value) в элементах списка запрещено, необходимо именно
переставить элементы. Допускается не более одного прохода по списку
 */

public class SimpleLinkedList<T> implements Iterable<Integer> {

    public static class SimpleLinkedListException extends Exception {
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }

    private class SimpleLinkedListNode<T> {
        public int value;
        public SimpleLinkedListNode<Integer> next;

        public SimpleLinkedListNode(int value, SimpleLinkedListNode<Integer> next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListNode(Integer value) {
            this(value, null);
        }
    }

    private SimpleLinkedListNode<Integer> head = null;
    private SimpleLinkedListNode<Integer> tail = null;
    private int count = 0;


    public void addFirst(int value) {
        head = new SimpleLinkedListNode<>(value, head);
        if (count == 0) {
            tail = head;
        }
        count++;
    }

    public void addLast(int value) {
        SimpleLinkedListNode<Integer> temp = new SimpleLinkedListNode<>(value);
        if (count == 0) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        count++;
    }

    private void checkEmpty() throws SimpleLinkedListException {
        if (count == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    private SimpleLinkedListNode<Integer> getNode(int index) {
        int i = 0;
        for (SimpleLinkedListNode<Integer> curr = head; curr != null; curr = curr.next, i++) {
            if (i == index) {
                return curr;
            }
        }
        return null;
    }

    public int removeFirst() throws SimpleLinkedListException {
        checkEmpty();

        int value = head.value;
        head = head.next;
        if (count == 1) {
            tail = null;
        }
        count--;
        return value;
    }

    public int removeLast() throws SimpleLinkedListException {
        return remove(count - 1);
    }

    public int remove(int index) throws SimpleLinkedListException {
        checkEmpty();
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }

        int value;
        if (index == 0) {
            value = head.value;
            head = head.next;
        } else {
            SimpleLinkedListNode<Integer> prev = getNode(index - 1);
            SimpleLinkedListNode<Integer> curr = prev.next;
            value = curr.value;
            prev.next = curr.next;
            if (index == count - 1) {
                tail = prev;
            }
        }
        count--;
        return value;
    }

    public void insert(int index, int value) throws SimpleLinkedListException {
        if (index < 0 || index > count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            addFirst(value);
        } else {
            SimpleLinkedListNode<Integer> prev = getNode(index - 1);
            prev.next = new SimpleLinkedListNode<>(value, prev.next);
            if (index == count) {
                tail = prev.next;
            }
        }
        count++;
    }

    public int size() {
        return count;
    }

    public int getFirst() throws SimpleLinkedListException {
        checkEmpty();

        return head.value;
    }

    public int getLast() throws SimpleLinkedListException {
        checkEmpty();

        return tail.value;
    }

    public int get(int index) throws SimpleLinkedListException {
        if (index < 0 || index >= count) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        return getNode(index).value;
    }
    public void getAnswer(){
        int n = size();
        int maxValue = Integer.MIN_VALUE;
        int indexMax= 0;

        int minValue = Integer.MAX_VALUE;
        int indexMin= 0;
        for (int i = 1; i < n; ++i) {
            int value = getNode(i).value;
            if( value < minValue){
                minValue = value;
                indexMin = i;
            }
            if( value >= maxValue){
                maxValue = value;
                indexMax = i;
            }
            //
        }
    }
    //    .\input.txt .\output.txt


    @Override
    public Iterator<Integer> iterator() {
        class SimpleLinkedListIterator implements Iterator<Integer> {
            SimpleLinkedListNode<Integer> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public Integer next() {
                int value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new SimpleLinkedListIterator();
    }
}
