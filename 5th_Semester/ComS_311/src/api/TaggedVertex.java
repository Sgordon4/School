package api;

/**
 * Represents a tuple consisting of a graph node and an associated tag (numeric value).
 * @param <E>
 */
public class TaggedVertex<E>
{
  private final E vertexData;
  private final int tagValue;
  
  /**
   * Constructs a TaggedVertex with the given data and value.
   * @param data
   * @param value
   */
  public TaggedVertex(E data, int value)
  {
    vertexData = data;
    tagValue = value;
  }
  
  /**
   * Returns the data portion of this tuple.
   * @return
   */
  public E getVertexData()
  {
    return vertexData;
  }

  /**
   * Returns the associated tag value for this tuple.
   * @return
   */
  public int getTagValue()
  {
    return tagValue;
  }

}
