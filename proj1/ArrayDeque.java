import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by luke on 10.03.2016.
 */
public class ArrayDeque<I> implements Collection<I> {

    private static final int DEFAULT_SIZE = 8;
    private I[] array;
    private int size;
    private int capacity;
    private int currentHeadPos;
    private int nextHeadPos;
    private int currentTailPos;
    private int nextTailPos;

    public ArrayDeque() {
        array = (I[]) new Object[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
    }

    @Override
    public void addFirst(I item) {
        if (++size > capacity) resize();
        array[nextHeadPos] = item;
        currentHeadPos = nextHeadPos;
        nextHeadPos = movePositionOneBackwards(nextHeadPos);
    }

    @Override
    public void addLast(I item) {
        if (++size > capacity) resize();
        array[nextTailPos] = item;
        currentTailPos = nextTailPos;
        nextTailPos = movePositionOneForward(nextTailPos);
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
        IntStream.iterate(currentHeadPos, i -> (i + 1) % capacity).limit(capacity).forEach(i ->  System.out.print(array[i] + " "));
    }

    @Override
    public I removeFirst() {
        if (--size < capacity / 2) resizeDownwards();
        nextHeadPos = currentHeadPos;
        currentHeadPos = movePositionOneForward(currentHeadPos);
        return array[nextHeadPos];
    }


    @Override
    public I removeLast() {
        if (--size < capacity / 2) resizeDownwards();
        nextTailPos = currentTailPos;
        currentTailPos = movePositionOneBackwards(currentTailPos);
        return array[nextTailPos];
    }

    @Override
    public I get(int index) {
        if (index >= size || index < 0)  throw new IndexOutOfBoundsException();
        return array[(currentHeadPos + index) % capacity];
    }


    //############PRIVATE_HELPER_METHODS_#######################################
    private void resize() {
        int old_capacity = capacity;
        List<I> resized = new ArrayList();
        IntStream.iterate(currentHeadPos, i -> (i + 1) % capacity).limit(capacity)
                    .forEach(i -> resized.add(array[i]));
        capacity = capacity * 2;
        array = (I[]) resized.toArray(new Object[capacity]);
        nextHeadPos = movePositionOneBackwards(0);
        nextTailPos = movePositionOneForward(old_capacity);
    }

    private void resizeDownwards() {
        
    }

    private int movePositionOneBackwards(int position){
        return (position - 1 < 0) ? capacity : position - 1;
    }

    private int movePositionOneForward(int position) {
        return (position + 1) % capacity;
    }
}
