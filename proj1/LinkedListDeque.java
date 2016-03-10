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
            size --;
             return oldFirst.value;
        } else return null;
    }

    @Override
    public I removeLast() {
        if (tail != null) {
            LldNode<I> oldLast = tail;
            tail = tail.prev;
            size --;
            return oldLast.value;
        } else return null;
    }

    @Override
    public I get(int index) {
        if (index >= size || head == null) {
            return null;
        }
        else {
            I returnValue = null;
            int i = 0;
            for (LldNode<I> n = head;  n != null; n = n.next){
                if (index == i++)  returnValue = head.value;
            }return returnValue;
        }
    }

    @Override
    public I getRecursive(int index) {
        I returnValue = null;
        if (index >= size || index < 0 || head == null) {
            return null;
        }
        else {
            returnValue  = getRecursiveHelper(index, head);
        } return returnValue;
    }

    private I getRecursiveHelper(int index, LldNode<I> head) {
        if(index == 0) return head.value;
        else return getRecursiveHelper(index--, head.next);
    }


}
