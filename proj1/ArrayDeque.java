import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by luke on 10.03.2016.
 * <todo>
 *     make one function out of resize and resizeDownwards
 *     add one-of-type to avoid null checking. Use Options
 * </todo>
 */
public class ArrayDeque<I> implements Collection<I> {

    private static final int DEFAULT_SIZE = 8;
    private static final int REDUCTION_FACTOR = 4;
    private static final int INCREASE_FACTOR = 2;
    private I[] array;
    private int size;
    private int capacity;
    private int currentHeadPos;
    private int currentTailPos;

    public ArrayDeque() {
        array = (I[]) new Object[DEFAULT_SIZE];
        capacity = DEFAULT_SIZE;
    }

    @Override
    public void addFirst(I item) {
        if (++size > capacity) resize();
        currentHeadPos = array[currentHeadPos] == null ? currentHeadPos : calculatePreviousPosition(currentHeadPos);
        array[currentHeadPos] = item;

    }

    @Override
    public void addLast(I item) {
        if (++size > capacity) resize();
        currentTailPos = array[currentTailPos] == null ?  currentTailPos : calculateNextPosition(currentTailPos);
        array[currentTailPos] = item;
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
        IntStream.iterate(currentHeadPos, i -> (i + 1) % capacity).limit(size)
                .forEach(i ->  System.out.print(array[i] + " "));
    }

    @Override
    public I removeFirst() {
        if (--size < capacity / REDUCTION_FACTOR) resizeDownwards();
        currentHeadPos = array[currentHeadPos] == null ? currentHeadPos : calculateNextPosition(currentHeadPos);
        return array[calculatePreviousPosition(currentHeadPos)];
    }


    @Override
    public I removeLast() {
        if (--size < capacity / REDUCTION_FACTOR) resizeDownwards();
        currentTailPos = array[currentTailPos] == null ? currentTailPos : calculatePreviousPosition(currentTailPos);
        return array[calculatePreviousPosition(currentTailPos)];
    }

    @Override
    public I get(int index) {
        if (index >= size || index < 0)  throw new IndexOutOfBoundsException();
        return array[(currentHeadPos + index) % capacity];
    }


    //############PRIVATE_HELPER_METHODS_#######################################
    private void resize() {
        List<I> resized = new ArrayList();
        IntStream.iterate(currentHeadPos, i -> (i + 1) % capacity).limit(size)
                    .forEach(i -> resized.add(array[i]));
        capacity = capacity * INCREASE_FACTOR;
        array = (I[]) resized.toArray(new Object[capacity]);

    }

    private void resizeDownwards() {
        List<I> resized = new ArrayList();
        IntStream.iterate(currentHeadPos, i -> (i + 1) % capacity).limit(size)
                .forEach(i -> resized.add(array[i]));
        capacity = capacity / REDUCTION_FACTOR;
        array = (I[]) resized.toArray(new Object[capacity]);
    }

    private int calculatePreviousPosition(int position){
        return (position - 1 < 0) ? capacity : position - 1;
    }

    private int calculateNextPosition(int position) {
        return (position + 1) % capacity;
    }
}
