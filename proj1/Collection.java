/**
 * Created by luke on 08.03.2016.
 */
public interface Collection<I> {
    public void addFirst(I item);
    public void addLast(I Item);
    public boolean isEmpty();
    public int size();
    public void printDeque();
    public I removeFirst();
    public I removeLast();
    public I get(int index);
}
