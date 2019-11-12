package pa1;

import api.TaggedVertex;

import java.util.Comparator;

/**
 * TaggedVertex, but the values can be compared
 * @param <E>
 */
public class ComparableTaggedVertex<E> extends TaggedVertex<E>
        implements Comparable<TaggedVertex<E>>, Comparator<TaggedVertex<E>>
{

    /**
     * Constructs a TaggedVertex with the given data and value.
     * @param data
     * @param value
     */
    public ComparableTaggedVertex(E data, int value)
    {
        super(data, value);
    }

    public int compareTo(TaggedVertex v) {
        return Integer.compare(super.getTagValue(), v.getTagValue());
    }
    public int compare(TaggedVertex a, TaggedVertex b) {
        return a.getTagValue() - b.getTagValue();
    }
}
