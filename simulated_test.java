/**
 *
 * @author Chitranshu Raj
 */
package methods;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Class simulated_test contains the main function for generating simulated graphs 
// and running the operations specified in Class graph_operations on it.
public class simulated_test {
    
    // The ncyclic_test() generates the ncyclic graph from Class graph_simulator.
    // The method takes as @argument=Integer v, which is the number of vertices.
    // The method performs operations and prints the result to the output window.
    public static void ncyclic_test(int v){
        graph_simulator gs=new graph_simulator();
        Map cyc=gs.ncyclic_graph(v);
        System.out.println("Graph: "+cyc);
        graph_operations go1=new graph_operations(cyc);
        System.out.println("Connected Components: "+go1.connected_components());
        System.out.println("Cycle: "+go1.one_cycle());
        System.out.println("Shortest Path Map: "+go1.shortest_path());
    }
    
    // The complete_test() generates the complete graph from Class graph_simulator.
    // The method takes as @argument=Integer v, which is the number of vertices.
    // The method performs operations and prints the result to the output window.
    public static void complete_test(int v){
        graph_simulator gs=new graph_simulator();
        Map comp=gs.complete_graph(v);
        System.out.println("Graph: "+comp);
        graph_operations go2=new graph_operations(comp);
        System.out.println("Connected Components: "+go2.connected_components());
        System.out.println("Cycle: "+go2.one_cycle());
        System.out.println("Shortest Path Map: "+go2.shortest_path());
    }
    
    // The empty_test() generates the empty graph from Class graph_simulator.
    // The method takes as @argument=Integer v, which is the number of vertices.
    // The method performs operations and prints the result to the output window.
    public static void empty_test(int v){
        graph_simulator gs=new graph_simulator();
        Map emp=gs.empty_graph(v);
        System.out.println("Graph: "+emp);
        graph_operations go3=new graph_operations(emp);
        System.out.println("Connected Components: "+go3.connected_components());
        System.out.println("Cycle: "+go3.one_cycle());
        System.out.println("Shortest Path Map: "+go3.shortest_path());
    }
    
    // The heap_test() generates the heap-tree graph from Class graph_simulator.
    // The method takes as @argument=Integer v, which is the number of vertices.
    // The method performs operations and prints the result to the output window.
    public static void heap_test(int v){
        graph_simulator gs=new graph_simulator();
        Map heap=gs.heap_graph(v);
        System.out.println("Graph: "+heap);
        graph_operations go4=new graph_operations(heap);
        System.out.println("Connected Components: "+go4.connected_components());
        System.out.println("Cycle: "+go4.one_cycle());
        System.out.println("Shortest Path Map: "+go4.shortest_path());
    }
    
    // The mod_test() generates the equivalent modK graph from Class graph_simulator.
    // The method takes two @argument1=Integer k and @argument2=Integer v
    // @argument1 specifies the K value and @argument2 specfies the number of vertices.
    // The method performs operations and prints the result to the output window.
    public static void mod_test(int k,int v){
        graph_simulator gs=new graph_simulator();
        Map modk=gs.modkgraph(k,v);
        System.out.println("Graph: "+modk);
        graph_operations go5=new graph_operations(modk);
        System.out.println("Connected Components: "+go5.connected_components());
        System.out.println("Cycle: "+go5.one_cycle());
        System.out.println("Shortest Path Map: "+go5.shortest_path());
    }
    
    public static void main(String args[]){
        
        //The start time of the CPU is noted.
        long start = System.nanoTime();
        //Scanner is initialized to capture user inputs.
        Scanner sc=new Scanner(System.in);
        
        System.out.println("Choose the type of Graph:-");
        System.out.print("1.ncyclic 2.complete 3.empty 4.heap 5.modk: ");
        int choice=sc.nextInt();
        System.out.println();
        System.out.print("Enter the number of vertices: ");
        int v=sc.nextInt();
        System.out.println();
        
        switch(choice){
            case 1:
                ncyclic_test(v);
                break;
            case 2:
                complete_test(v);
                break;
            case 3:
                empty_test(v);
                break;
            case 4:
                heap_test(v);
                break;
            case 5:
                System.out.print("Enter the K value(It should be less than/equal to number of vertices): ");
                int k=sc.nextInt();
                System.out.println();
                mod_test(k,v);
                break;
            default:
                System.out.println("No Input Passed/Wrong input/Wrong Data-Type/Enter integer value!");
                break;
        }
        
        // Memory Usage is being computed here.
        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();
        long total = 0;
        for (MemoryPoolMXBean memoryPoolMXBean : pools){
            if (memoryPoolMXBean.getType() == MemoryType.HEAP)
                {
                  long peakUsed = memoryPoolMXBean.getPeakUsage().getUsed();
                  total = total + peakUsed;
                }
        }
        
        //The end time is noted to find out total CPU time.
        Long end=System.nanoTime();
        System.out.println("CPU Time: "+(end-start)+" nanoseconds");
        System.out.println("Total heap peak memory used: " + total+" bytes"); 
        System.out.println("Used Memory   :  " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) + " bytes");
        System.out.println("Total Memory  : " + Runtime.getRuntime().totalMemory() + " bytes");
    }
}
