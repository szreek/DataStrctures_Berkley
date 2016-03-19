import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by luke on 10.03.2016.
 */
public class ArrayDeque<I> implements Collection<I> {

    private static final int DEAFAULT_SIZE = 8;
    private I[] array;
    private Integer head;
    private Integer tail;
    private int size;
    private int capacity;

    public ArrayDeque() {
        array = (I[]) new Object[DEAFAULT_SIZE];
        capacity = DEAFAULT_SIZE;
    }

    @Override
    public void addFirst(I item) {
        if (++size > capacity) resize();
        shiftItemsToTheRight(0, size);
        array[0] = item;

    }


    @Override
    public void addLast(I item) {
        if (++size > capacity) resize();
        array[size - 1] = item;
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
        for(int i = 0; i < size; i++){
            System.out.println(array[i]);
        }
    }

    @Override
    public I removeFirst() {
        return null;
    }

    @Override
    public I removeLast() {
        return null;
    }

    @Override
    public I get(int index) {
        return null;
    }

    @Override
    public I getRecursive(int index) {
        return null;
    }


    //############PRIVATE_HELPER_METHODS_#######################################
    private void resize() {
        capacity = 2 * capacity;
        List<I> resized = new ArrayList();
        Arrays.asList(array).stream().forEach(resized::add);
        array = (I[]) resized.toArray(new Object[capacity]);
    }

    private void shiftItemsToTheRight(int from, int to) {
        IntStream.range(from, to).map( i -> to - i + from - 1).forEach(i -> array[i + 1] = array[i]);
    }

}
