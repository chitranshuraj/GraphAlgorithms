
/**
 *
 * @author Chitranshu Raj and Lavanya Khular
 */

package methods;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


class Graph<T>{ 
    
    // Class Graph is the data structure we have designed to store our graph.
    // The user has the freedom to set the data-type of graph, ie. <T>
    // A Hashmap is used to store the vertices in our graph.
    // Every vertex is represented as key <T>, where the value List<T>
    // represents the adjacency list for the following vertex. 
    
    private Map<T,List<T>> map = new HashMap<>();
  
    // Everytime a graph is initialized, method addVertex() is used to add 
    // a new vertex to the graph. 
    public void addVertex(T s) 
    { 
        map.put(s,new LinkedList<T>()); 
    } 
  
    // The method addEdge() is used to add an edge between two vertices. 
    public void addEdge(T source,T destination) 
    {  
        if (!map.containsKey(source)){
            addVertex(source); 
        }   
        if (!map.containsKey(destination)){
            if(destination!=null){
                addVertex(destination);
            }
        }
        
        // The destination vertex is added to the adjancency list if the source.
        map.get(source).add(destination);
    } 
    
    // The method return_map() is used to return Graph<T> as a HashMap data structure.
    public Map return_map(){
        return map;
    }
} 

// Class graph_simulator is responsible for deploying different types
// of graphs outlined in the assignment. 
// Five graphs have been simulated from the list of graphs specified. 
public class graph_simulator{
    
    // The first graph is ncyclic graph. 
    // The vertices form an unique cycle of length n. 
    // There is one connected component.
    // The user enters @argument as Integer: number of vertices 
    // The method returns @output as a HashMap of the graph.
    public Map ncyclic_graph(int vertices){
        Graph<Integer> cygr=new Graph<Integer>();
        for(int j=0;j<vertices;j++){
            if(j!=vertices-1){
                cygr.addEdge(j,j+1);
                cygr.addEdge(j+1,j);
            }
            else{
                cygr.addEdge(vertices-1,0);
                cygr.addEdge(0,vertices-1);
            }
        }
        return cygr.return_map();
    }
    
    // The second graph is complete graph.
    // Every pair of distinct vertices share an edge. 
    // There is one connected component and there are many cycles.
    // The user enters @argument as Integer: number of vertices 
    // The method returns @output as a HashMap of the graph
    public Map complete_graph(int vertices){
        Graph<Integer> cgr=new Graph<Integer>();
        for(int a=0;a<vertices;a++){
            for(int b=0;b<vertices;b++){
                if(a!=b){
                   cgr.addEdge(a,b); 
                }    
            }
        }
        return cgr.return_map();
    }
    
    // The third graph is an empty graph.
    // The vertices are independent ie. there are no edges and no cycles.
    // There are n connected components.
    // The user enters @argument as Integer: number of vertices 
    // The method returns @output as a HashMap of the graph
    public Map empty_graph(int vertices){
        Graph<Integer> egr=new Graph<Integer>();
        for(int x=0;x<vertices;x++){
            egr.addEdge(x,x);
        }
        return egr.return_map();
    }
    
    // The fourth graph is representation of binary heap as graph.
    // The vertices are connected to each other in the form of a tree.
    // The neighbours of a vertex are 2v-1 or 2v+2. 
    // There are no cycles.
    // The user enters @argument as Integer: number of vertices
    // The method returns @output as a HashMap of the graph
    public Map heap_graph(int vertices){
        Graph<Integer> hgr=new Graph<Integer>();
        for(int y=0;y<vertices;y++){
            hgr.addEdge(y,2*y+1);
            hgr.addEdge(y,2*y+2);
        }
        return hgr.return_map();
    }
    
    // The fifth graph is the equivalence modK graph.
    // The vertices u and v are connected if (u-v)%K==0. 
    // There are K components and each component is a complete graph.
    // The user enters @argument as Integer: number of vertices
    // The method returns @output as a HashMap of the graph
    public Map modkgraph(int k,int vertices){
        Graph<Integer> mkgr=new Graph<Integer>();
        if(k<=vertices){
            for(int p=0;p<vertices;p++){
                for(int q=0;q<vertices;q++){
                    if((p-q)%k==0 && p!=q){
                        mkgr.addEdge(p,q);
                    }
                    else if(p==q){
                        mkgr.addEdge(p,p);
                    }
                }
            }
           
        }
        return mkgr.return_map();
    }
} 