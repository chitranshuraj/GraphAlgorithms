
/**
 *
 * @author Chitranshu Raj and Lavanya Khular
 */

package methods;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Class real_test contains the main function for generating three graphs based 
// on some adjacency criteria. The operations from Class graph_operations are 
// then run on these graphs.
public class real_test {
    
    // The method graph1() generates the first graph and performs the operations.
    // from Class graph_operations.
    // The adjacency criteria is as follows:-
    // Users are adjacent if there are at least two movies both have rated.
    // If the users satisfy this criterion, they are arranged in the graph.
    // Each operation is carried out and result is printed to the output box.
    public static void graph1(){
        graph_make s=new graph_make(8);
        Map<Integer,LinkedList<Integer>> g=s.graph1();
        graph_operations go1=new graph_operations(g);
        System.out.println("Graph1: "+g);
        System.out.println("Cycle: "+go1.one_cycle());
        List<List<Integer>> components=go1.connected_components();
        System.out.println("Connected Components: "+components);
        System.out.println("Number of Components: "+components.size());
        System.out.print("Size of each component: "+"[ ");
        for(int x=0;x<components.size();x++){
            if(x==components.size()-1){
                System.out.println(components.get(x).size()+" ]");
            }
            else{
                System.out.print(components.get(x).size()+",");
            }
        }
        //System.out.println(go1.shortest_path());
    }
    
    // The method graph2() generates the second graph and performs the operations
    // from Class graph_operations.
    // The adjacency criteria is as follows:-
    // Two users are adjacent if they have three movies in common and they have
    // given same rating to three movies.
    // If the users satisfy this criterion, they are arranged in the graph.
    // Each operation is carried out and result is printed to the output box.
    public static void graph2(){
        graph_make s=new graph_make(8);
        Map<Integer,LinkedList<Integer>> g=s.graph2();
        graph_operations go1=new graph_operations(g);
        System.out.println("Graph1: "+g);
        System.out.println("Cycle: "+go1.one_cycle());
        List<List<Integer>> components=go1.connected_components();
        System.out.println("Connected Components: "+components);
        System.out.println("Number of Components: "+components.size());
        System.out.print("Size of each component: "+"[ ");
        for(int x=0;x<components.size();x++){
            if(x==components.size()-1){
                System.out.println(components.get(x).size()+" ]");
            }
            else{
                System.out.print(components.get(x).size()+",");
            }
            
        }
        //System.out.println(go1.shortest_path());
    }
    
    // The method graph3() generates the third graph and performs the operations
    // from Class graph_operations.
    // The adjacency criteria is as follows:-
    // Two users are adjacent if they have rated at most 3 movies. 
    // If the users satisfy this criterion, they are arranged in the graph.
    // Each operation is carried out and result is printed to the output box.
    public static void graph3(){
        graph_make s=new graph_make(8);
        Map<Integer,LinkedList<Integer>> g=s.graph3();
        graph_operations go1=new graph_operations(g);
        System.out.println("Graph1: "+g);
        System.out.println("Cycle: "+go1.one_cycle());
        List<List<Integer>> components=go1.connected_components();
        System.out.println("Connected Components: "+components);
        System.out.println("Number of Components: "+components.size());
        System.out.print("Size of each component: "+"[ ");
        for(int x=0;x<components.size();x++){
            if(x==components.size()-1){
                System.out.println(components.get(x).size()+" ]");
            }
            else{
                System.out.print(components.get(x).size()+",");
            }
            
        }
        //System.out.println(go1.shortest_path());
    }
    
    public static void main(String []args){
        long start = System.nanoTime();

        Scanner sc=new Scanner(System.in);
        
        System.out.println("Enter the type of graph: ");
        System.out.print("1.Graph1 2.Graph2 3.Graph3: ");
        int choice=sc.nextInt();
        System.out.println();
        
        switch(choice){
            case 1:
                graph1();
                break;
            case 2:
                graph2();
                break;
            case 3:
                graph3();
                break;
            default:
                break;
        }
        
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        long total = 0;
        for (MemoryPoolMXBean memoryPoolMXBean : pools){
            if (memoryPoolMXBean.getType() == MemoryType.HEAP)
                {
                  long peakUsed = memoryPoolMXBean.getPeakUsage().getUsed();
                  total = total + peakUsed;
                }
        }
        
        Long end=System.nanoTime();
        System.out.println("CPU Time: "+(end-start)+" nanoseconds");
        System.out.println("Total heap peak memory used: " + total+" bytes");
        System.out.println("Used Memory   :  " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " bytes");
        System.out.println("Total Memory  : " + Runtime.getRuntime().totalMemory() + " bytes");

        }
}
