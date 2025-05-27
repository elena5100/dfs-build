import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Build {

  /**
   * Prints words that are reachable from the given vertex and are strictly shorter than k characters.
   * If the vertex is null or no reachable words meet the criteria, prints nothing.
   *
   * @param vertex the starting vertex
   * @param k the maximum word length (exclusive)
   */
  public static void printShortWords(Vertex<String> vertex, int k) {
    if (vertex == null) 
  return;

    //Prints words shorter than k characters reachable from the vertex

    Set<Vertex<String>> visited = new HashSet<>();
    printHelper(vertex, k, visited);
  }

  private static void printHelper(Vertex<String> v, int k, Set<Vertex<String>> visited) {
    if (visited.contains(v)) 
    return;
    visited.add(v);

    if (v.data.length() < k) {
      System.out.println(v.data);
    }

    for (Vertex<String> neighbor : v.neighbors) {
      printHelper(neighbor, k, visited);
    }
  

  }

  /**
   * Returns the longest word reachable from the given vertex, including its own value.
   *
   * @param vertex the starting vertex
   * @return the longest reachable word, or an empty string if the vertex is null
   */
  public static String longestWord(Vertex<String> vertex) {

    if (vertex == null) return "";



    Set<Vertex<String>> visited = new HashSet<>();
    return longestHelper(vertex, visited);
  }

  private static String longestHelper(Vertex<String> v, Set<Vertex<String>> visited) {
    if (visited.contains(v)) return "";
    visited.add(v);

    String longest = v.data;

    for (Vertex<String> neighbor : v.neighbors) {
      String word = longestHelper(neighbor, visited);
      if (word.length() > longest.length()) {
        longest = word;
      }
    }

    return longest;
  
   
  }

  /**
   * Prints the values of all vertices that are reachable from the given vertex and 
   * have themself as a neighbor.
   *
   * @param vertex the starting vertex
   * @param <T> the type of values stored in the vertices
   */
  public static <T> void printSelfLoopers(Vertex<T> vertex) {
    if (vertex == null) return;


    //Prints vertices that point to themselves
    Set<Vertex<T>> visited = new HashSet<>();
    printSelfHelper(vertex, visited);
  }

  private static <T> void printSelfHelper(Vertex<T> v, Set<Vertex<T>> visited) {
    if (visited.contains(v)) return;
    visited.add(v);

    if (v.neighbors.contains(v)) {
      System.out.println(v.data);
    }

    for (Vertex<T> neighbor : v.neighbors) {
      printSelfHelper(neighbor, visited);
    }
  }

  /**
   * Determines whether it is possible to reach the destination airport through a series of flights
   * starting from the given airport. If the start and destination airports are the same, returns true.
   *
   * @param start the starting airport
   * @param destination the destination airport
   * @return true if the destination is reachable from the start, false otherwise
   */
  public static boolean canReach(Airport start, Airport destination) {
    Set<Airport> seen = new HashSet<>();
    return tryToGo(start, destination, seen);
}
    public static boolean tryToGo(Airport now, Airport goal, Set<Airport> visited) {
      if (now == null || visited.contains(now)) {
          return false;
      }
  
      if (now == goal) {
          return true;
      }
  
      visited.add(now);
  
      for (Airport next : now.getOutboundFlights()) {
          if (tryToGo(next, goal, visited)) {
              return true;
          }
      }
  
      return false;
  }
    

  /**
   * Returns the set of all values in the graph that cannot be reached from the given starting value.
   * The graph is represented as a map where each vertex is associated with a list of its neighboring values.
   *
   * @param graph the graph represented as a map of vertices to neighbors
   * @param starting the starting value
   * @param <T> the type of values stored in the graph
   * @return a set of values that cannot be reached from the starting value
   */
  public static <T> Set<T> unreachable(Map<T, List<T>> graph, T starting) {
    Set<T> seen = new HashSet<>();

    if (graph.containsKey(starting)) {
        findAll(graph, starting, seen);
    }

    Set<T> notSeen = new HashSet<>(graph.keySet());
    notSeen.removeAll(seen);
    return notSeen;
}

private static <T> void findAll(Map<T, List<T>> graph, T current, Set<T> seen) {
    if (seen.contains(current)) return;
    seen.add(current);

    List<T> nextList = graph.get(current);
    if (nextList == null) return;

    for (T next : nextList) {
        findAll(graph, next, seen);
    }
}
}

