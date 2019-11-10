package api;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract representation of a directed graph with generic vertex type.  
 * The array returned by vertexData() identifies each vertex with an
 * integer index.  The adjacency list for the vertex is
 * a list of these indices, as returned by getNeighbors(). Implementations
 * should ensure that there are no duplicate elements in the vertex data,
 * no self-edges, and no duplicate edges.
 */
public interface Graph<E>
{
  /**
   * Returns an ArrayList of the actual objects constituting the vertices
   * of this graph.
   * @return
   *   ArrayList of objects in the graph
   */
  public ArrayList<E> vertexData();
  
  /**
   * Returns an ArrayList that is identical to that returned by vertexData(), except
   * that each vertex is associated with its incoming edge count.
   * @return
   *   ArrayList of objects in the graph, each associated with its incoming edge count
   */
  public ArrayList<TaggedVertex<E>> vertexDataWithIncomingCounts();
  
  /**
   * Returns a list of outgoing edges, that is, a list of indices for neighbors
   * of the vertex with given index.
   * This method may throw ArrayIndexOutOfBoundsException if the index 
   * is invalid.
   * @param index
   *   index of the given vertex according to vertexData()
   * @return
   *   list of outgoing edges
   */
  public List<Integer> getNeighbors(int index);
  
  /**
   * Returns a list of incoming edges, that is, a list of indices for vertices 
   * having the given vertex as a neighbor.
   * This method may throw ArrayIndexOutOfBoundsException if the index 
   * is invalid. 
   * @param index
   *   index of the given vertex according to vertexData()
   * @return
   *   list of incoming edges
   */
  public List<Integer> getIncoming(int index);
}



