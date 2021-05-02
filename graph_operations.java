/**
 *
 * @author Chitranshu Raj
 */

package methods;
import java.util.Map;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;

// Class graph_operations performs operations connected_components, 
// one cycle and shortest path on the given graph.
public class graph_operations{
    
    // The Graph Operations are carried out with the use of Map Data-Structure.
    // A private Map<K,V> is initialized so all the methods can access it. 
    // The key<Integer> of the map is the vertex.
    // The value LinkedList<Integer> is the adjacency list of the vertex. 
    private Map<Integer,LinkedList<Integer>> Graph;
    
    public graph_operations(Map x){
        this.Graph=x;
    }
    
    // The DFS_comp() method finds the path of the given vertex if any exists.
    private void DFS_comp(int vertex,boolean[] visited,ArrayList<Integer> traversal_list){
        if(!visited[vertex]){
            traversal_list.add(vertex);
            visited[vertex] = true;
            for(int v:Graph.get(vertex)){
                DFS_comp(v,visited,traversal_list);
            }
        }
    }
 
    // The connected_components() method returns @output= List components.
    // The list shows the components present in the graph by implementing DFS. 
    public List connected_components(){
        boolean[] visited= new boolean[Graph.size()];
        List<List<Integer>> component_list=new ArrayList<List<Integer>>();
        for(int i=0;i<Graph.size();i++){
            ArrayList<Integer> traversal=new ArrayList<>();
            if(!visited[i]){
                DFS_comp(i,visited,traversal);
                component_list.add(traversal);
            }
        }
        return component_list;
    }
    
    // The DFS_cycle method performs a DFS algorithm to find the path from each vertex
    // The method returns @output as List which contains the vertices visited from 
    // a source vertex.
    private List DFS_cycle(int vertex,boolean []visited,ArrayList<Integer> container){
        visited[vertex]=true;
        for(int k:Graph.get(vertex)){
            if(!visited[k]){
            container.add(vertex);
            DFS_cycle(k,visited,container);
            }
            else{
                container.add(vertex);
                container.add(k);
                return container;
            }
        }
        return container;
    }
    
    // The one_cycle() method returns a single cycle if it exists in the graph. 
    // The method uses the DFS algorithm to return @output=List cycle.
    // If there is no cycle detected in the graph, then method one_cycle() returns
    // @ output= List empty_list. 
    public List one_cycle(){
        ArrayList<Integer> empty_list=new ArrayList<>();
        List<List<Integer>> paths=new ArrayList<>(); //contains path for each vertex
        ArrayList<Integer> cycle=new ArrayList<Integer>();
        
        for(int k:Graph.keySet()){
            boolean[] visited=new boolean[Graph.size()];
            ArrayList<Integer> container=new ArrayList<>();
            paths.add(DFS_cycle(k,visited,container));
        }
        for(int i=0;i<paths.size();i++){
            HashSet<Integer> unique_list=new HashSet<Integer>(paths.get(i));
            ArrayList<Integer> raw_list=new ArrayList<Integer>(paths.get(i));
            if(i==raw_list.get(paths.get(i).size()-1) && unique_list.size()>=3){
                for(int v:unique_list){
                    cycle.add(v);
                }
                return cycle;
            }
            else{
                break;
            }
        }
        return empty_list;
    }
    
    // The BFS Algorithm has been implemented using method BFS(). 
    // The method returns a boolean @output= true if there exists a path from source to destination
    // and @output=false if there does not exists a path. 
    // The method Djikstra_Algorithm uses BFS, as this is an unweighted and undirected graph.  
    private static boolean BFS(Map<Integer,LinkedList<Integer>> m,int src,int dest,int v,int pred[],int dist[]){
 
        LinkedList<Integer> queue=new LinkedList<Integer>();
        boolean visited[] = new boolean[v];
        for (int i=0;i<v;i++) {
            visited[i]=false;
            dist[i]=Integer.MAX_VALUE;
            pred[i]=-1;
        }
        visited[src]=true;
        dist[src]=0;
        queue.add(src);
        while (!queue.isEmpty()) {
            int u=queue.remove();
            for(int i:m.get(u)){
                if (visited[i]==false) {
                    visited[i]=true;
                    dist[i]=dist[u]+1;
                    pred[i]=u;
                    queue.add(i);
                    if(i==dest){
                        return true;
                    }     
                }
            }
        }
        return false;
    }
    
    // The Djikstra_Algorithm returns @output=List sp[v]
    // List sp[v] contains the vertices encountered while traversing the 
    // shortest path between the source and vertex v. 
    private static List Djikstra_Algorithm(Map<Integer,LinkedList<Integer>> m,int s,int dest,int v){
        
        // prev[i] array stores parent/node visited while traversing to i 
        int prev[] = new int[v];
        // dist[i] array stores distance of i from source 
        int dist[] = new int[v];
        // BFS is performed here to initialize a source containing list
        // This is done to show there is no link b/w source and destination
        if(BFS(m,s,dest,v,prev,dist) == false) {
            List<Integer> source_list=new ArrayList<>();
            source_list.add(s);
            return source_list;
        }
 
        // The path list contains the path from vertex v to source
        LinkedList<Integer> path = new LinkedList<Integer>();
        
        // Djikstra implementation to add all vertices visited from final vertex to source
        // The vertices forming the shortest path to source from v are added to sp[v]
        int final_v=dest;
        path.add(final_v);
        while (prev[final_v] != -1) {
            path.add(prev[final_v]);
            final_v = prev[final_v];
        }
        List<Integer> sp=new ArrayList<Integer>();
        for (int i=path.size()-1;i>=0;i--){
            sp.add(path.get(i));
        }
        return sp;
    }
 
    // The method shortest_path() uses the Djikstra Agorithm, which is defined
    // in method Djikstra_Algorithm() to find the shortest path for a set of
    // vertex from each of the vertices. 
    public Map shortest_path(){
        Map<Integer,List<List<Integer>>> shortest_path=new HashMap<>();
        for(int x:Graph.keySet()){
            List<List<Integer>> container=new ArrayList<List<Integer>>();
            for(int y:Graph.keySet()){
                container.add(Djikstra_Algorithm(Graph,x,y,Graph.size()));
            }
            shortest_path.put(x,container);
        }
        return shortest_path;
    }  
}
