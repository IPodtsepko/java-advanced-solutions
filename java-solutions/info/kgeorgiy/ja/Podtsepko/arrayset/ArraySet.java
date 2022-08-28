package info.kgeorgiy.ja.Podtsepko.arrayset;

import java.util.*;


public class ArraySet<E> extends AbstractSet<E> implements SortedSet<E> {
    private final List<E> elements;
    private final Comparator<? super E> comparator;

    public ArraySet() {
        this.elements = Collections.emptyList();
        this.comparator = null;
    }

    public ArraySet(Comparator<? super E> comparator) {
        this.elements = Collections.emptyList();
        this.comparator = comparator;
    }

    private ArraySet(List<E> list, Comparator<? super E> comparator) {
        this.elements = list;
        this.comparator = comparator;
    }

    public ArraySet(Collection<? extends E> elements) {
        this(elements, null);
    }

    public ArraySet(Collection<? extends E> elements, Comparator<? super E> comparator) {
        SortedSet<E> set = new TreeSet<>(comparator);
        set.addAll(elements);
        this.elements = new ArrayList<>(set);
        this.comparator = comparator;
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator;
    }

    private SortedSet<E> subSet(int from, int to) {
        if (isEmpty()) {
            return new ArraySet<E>(comparator);
        }
        return new ArraySet<>(elements.subList(from, to), comparator);
    }

    @SuppressWarnings("unchecked")
    private int compare(final E left, final E right) {
        if (Objects.isNull(comparator)) {
            return ((Comparable<E>) left).compareTo(right);
        }
        return comparator.compare(left, right);
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        if (compare(fromElement, toElement) > 0) {
            throw new IllegalArgumentException("expected fromElement <= toElement");
        }
        return subSet(indexOf(fromElement), indexOf(toElement));
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return subSet(0, indexOf(toElement));
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return subSet(indexOf(fromElement), size());
    }

    private E get(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements.get(index);
    }

    @Override
    public E first() {
        return get(0);
    }

    @Override
    public E last() {
        return get(size() - 1);
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean contains(final Object element) {
        return search(element) > -1;
    }

    @Override
    public Iterator<E> iterator() {
        return Collections.unmodifiableList(elements).iterator();
    }


    private int indexOf(final Object element) {
        int index = search(element);
        return index < 0 ? -index - 1 : index;
    }

    @SuppressWarnings("unchecked")
    private int search(final Object element) {
        return Collections.binarySearch(elements, (E) Objects.requireNonNull(element), comparator);
    }
}