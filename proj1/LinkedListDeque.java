/**
 * Created by luke on 08.03.2016.
 */
public class LinkedListDeque<I> implements Collection<I>{
    private class LldNode<I> {

        private I value;
        private LldNode<I> next;
        private LldNode<I> prev;


        private LldNode(I value, LldNode<I> prev, LldNode<I> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        private LldNode(I value) {
            this.value = value;
        }


    }

    private LldNode<I> head;
    private LldNode<I> tail;
    private int size;

    public LinkedListDeque(){

    }

    public LinkedListDeque(I value){
        LldNode<I> node = new LldNode(value);
        head = node;
        tail = node;
        size++;
    }


    @Override
    public void addFirst(I item) {
        head = new LldNode(item, null, head);
        size++;
    }

    @Override
    public void addLast(I item) {
        if (tail != null) {
            tail.next = new LldNode(item, tail, null);
            tail = tail.next;
            size++;
        }else addFirst(item);
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (LldNode<I> n = head; n != null; n = n.next) {
            System.out.println(n.value + " -> ");
        }
    }

    @Override
    public I removeFirst() {
        if (head != null){
            LldNode<I> oldFirst = head;
            head = head.next;
             return oldFirst.value;
        } else return null;
    }

    @Override
    public I removeLast() {
        if (tail != null) {
            LldNode<I> oldLast = tail;
            tail = tail.prev;
            return oldLast.value;
        } else return null;
    }

    @Override
    public I get(int index) {
        if (index >= size || head == null) {
            return null;
        }
        else {
            int i = 0;
            for (LldNode<I> n = head;  n != null; n = n.next){
                if (i == index) return head.value;
                i++;
            } return null;
        }
    }
}
