package stacks;

/**
 * Interface for basic stack operations.
 */
public interface PureStack<E>
{
  /**
   * Adds an element to the top of the stack.
   * @param item the element to be added
   */
  void push(E item);
  
  /**
   * Removes and returns the top element of the stack.
   * @return the top element of the stack.
   * @throws NoSuchElementException if the stack is empty
   */
  E pop();
  
  /**
   * Returns the top element of the stack without removing it.
   * @return the top element of the stack.
   * @throws NoSuchElementException if the stack is empty
   */  
  E peek();
  
  /**
   * Determines whether the stack is empty.
   * @return true if the stack is empty, false otherwise 
   */
  boolean isEmpty();
  
  /**
   * Returns the number of elements in the stack.
   * @return the number of elements in the stack
   */
  int size();
}
