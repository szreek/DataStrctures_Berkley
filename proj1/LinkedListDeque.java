/**
 * Created by luke on 08.03.2016.
 */
public class LinkedListDeque<I> implements Collection<I>{
    private class LldNode<I> {

        private I value;
        private LldNode<I> next;
        private LldNode<I> prev;

        private LldNode(){
            next = this;
            prev = this;
        }

        private LldNode(I value, LldNode<I> prev, LldNode<I> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private LldNode<I> sentinel = new LldNode<I>();
    private int size;

    public LinkedListDeque(){
    }

    public LinkedListDeque(I value){
        sentinel.next = new LldNode(value, sentinel, sentinel);
        size++;
    }


    @Override
    public void addFirst(I item) {
        sentinel.next = new LldNode(item, sentinel, sentinel.next);
        size++;
    }

    @Override
    public void addLast(I item) {
        sentinel.prev = new LldNode<>(item, sentinel.prev, sentinel);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (LldNode<I> n = sentinel.next; n != sentinel; n = n.next) {
            System.out.print(n + " ");
        }

    }

    @Override
    public I removeFirst() {
        LldNode<I> first = sentinel.next;
        sentinel.next = first.next;
        size --;
        return first.value;

    }

    @Override
    public I removeLast() {
        LldNode<I> last = sentinel.prev;
        sentinel.prev = last.prev;
        size --;
        return last.value;
    }

    @Override
    public I get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        return (index > size/2) ? findFromTail(--size - index) : findFromHead(index);
    }


    public I getRecursive(int index) {
        if (index >= size || index < 0)  throw new IndexOutOfBoundsException();
        return getRecursiveHelper(index, sentinel.next);
    }


    //#####################_PRIVATE_HELPER_METHODS_#####################################
    private I getRecursiveHelper(int index, LldNode<I> head) {
        if(index == 0) return head.value;
        else return getRecursiveHelper(index--, head.next);
    }

    private I findFromTail(int indexFromTail){
        int i  = 0;
        LldNode<I> node = sentinel;
        for(LldNode n = sentinel.prev; n!= sentinel; n = sentinel.prev){
            if (i == indexFromTail) node = n;
            else i++;
        }
        return node.value;
    }

    private I findFromHead(int index){
        int i = 0;
        LldNode<I> node = sentinel;
        for (LldNode n = sentinel.next; n != sentinel; n = sentinel.next){
            if (i == index) node =  n;
            else i++;
        }
        return node.value;
    }
}
