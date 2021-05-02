/**
 *
 * @author Chitranshu Raj and Lavanya Khular
 */

package methods;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Class graph_make is responsible for handling the Netflix data
// Class graph_make has methods to read the data and generate graph
// based on some adjacency criteria which have been formulated by us. 
public class graph_make {
    
    // A String limiter has been initialized which sets the limit for the
    // data which is being read.
    private String limiter;
    
    // The constructor takes the value of limiter from the user. 
    graph_make(int limiter){
        this.limiter=String.valueOf(limiter)+":";
    }
   
    // The customer id and their ratings for a movie are stored as HashMap.
    // The data-structure HashMap has been used to remove redundancy, due 
    // to the large volume of customer data being read.
    private Map<Integer,LinkedList<Integer>> customer_data1=new HashMap<>();
    
    // The read_data() method reads the data from the file specified in the 
    // project description.
    // The data is arranged in the form of HashMap, such that customer id is the
    // key and the movie and rating are its values. 
    // The method returns @output=HashMap customer_data1
    private Map read_data(){
        
        // If there is file not found exception, then the try block terminates 
        // the process and returns the error to the user. 
        try{
           String st;
           String st2[];
           String st3[];
           int m_id;
           int cust;
           Map<Integer,List<String>> movie_data=new HashMap<>();
           List<List<Integer>> customer=new ArrayList<List<Integer>>();
           Map<Integer,LinkedList<Integer>> customer_data=new HashMap<>();
           File file=new File("C:\\Users\\rajch\\ratings_data_1.txt");
           BufferedReader br=new BufferedReader(new FileReader(file));
           
           // Each line is read using readLine method from Buffered Reader
           while((st=br.readLine())!=null && !limiter.equals(st)){
               st2=st.split(":");
               for(String item:st2){
                   if(!(item.contains("-"))){
                       m_id=Integer.parseInt(item);
                       movie_data.put(m_id,new ArrayList<>());
                   }
                   else{
                       for(Integer s:movie_data.keySet()){
                           movie_data.get(s).add(item);
                        }
                   }
               }
            }
           
            for(Integer i:movie_data.keySet()){
                for(String j:movie_data.get(i)){
                    List<Integer> container=new ArrayList<>();
                    st3=j.split(",");
                    for(String k:st3){
                        if(!(k.contains("-"))){
                        cust=Integer.parseInt(k);
                        container.add(cust);
                        }
                    }
                container.add(i);
                customer.add(container);
                }   
            }
            
            // A HashMap customer_data is generated to store the raw data 
            // returned after the reading has finished.
            for(int x=0;x<customer.size();x++){
                customer_data.put(customer.get(x).get(0),new LinkedList<Integer>());
            }
        
            for(int y:customer_data.keySet()){
                for(int k=0;k<customer.size();k++){
                    if(y==customer.get(k).get(0)){
                        customer_data.get(y).add(customer.get(k).get(2));
                        customer_data.get(y).add(customer.get(k).get(1));
                    }
                }
            }
            
            // The customer ids are then stored from the range 0 to n as the 
            // given customer ids are long integer values. 
            // The data is sorted and stored in the private HashMap 
            // customer_data1, so graph methods can use it to generate graphs.
            int i=0;
            for(int n:customer_data.keySet()){
                customer_data1.put(i,customer_data.get(n));
                i++;
            }
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        return customer_data1;  
    }

    // Three Adjancency criterias have been defined to generate three graphs.
    // The Netflix data is read and the customers are arranged based on these criterias.
    
    public Map graph1(){
        // Users are adjacent if there are atleast two movies both have rated.
        // Method graph1() returns @output=Map graph1
        Graph<Integer> graph1=new Graph<Integer>();
        read_data();
        for(int i=0;i<customer_data1.size();i++){
            for(int j=0;j<customer_data1.size();j++){
                if(i!=j && customer_data1.get(i).size()>=4 && customer_data1.get(j).size()>=4){
                    graph1.addEdge(i,j);
                }
                else if(customer_data1.get(i).size()<4){
                    graph1.addEdge(i,i);
                    break;
                }
            }
        }
        return graph1.return_map(); 
    }
    
    public Map graph2(){
        // Users are adjancent if they have three movies in common and they have 
        // given same rating to three movies.
        // Method graph2() returns @output=Map graph2
        Graph<Integer> graph2=new Graph<Integer>();
        read_data();
        
        for(int i=0;i<customer_data1.size();i++){
            for(int j=0;j<customer_data1.size();j++){
                if(i!=j && customer_data1.get(i).size()>=6 && customer_data1.get(j).size()>=6){
                    int count=0;
                    try{
                        for(int k:customer_data1.get(i)){
                            if(customer_data1.get(j).get(count).equals(k)){
                                count+=1;
                                if(count==6){
                                    graph2.addEdge(i,j);
                                }
                            }
                        }
                    }
                    catch(Exception e){
                        graph2.addEdge(i,i);
                    }
                }
                else if(customer_data1.get(i).size()<6){
                    graph2.addEdge(i,i);
                    break;
                }
            }
        }
        return graph2.return_map();
    }
    
    
    public Map graph3(){
        // Users are adjacent if they have rated atmost 3 movies.
        // Method graph3() returns @output=Map graph3
        Graph<Integer> graph3=new Graph<Integer>();
        read_data();
        
        for(int i=0;i<customer_data1.size();i++){
            for(int j=0;j<customer_data1.size();j++){
                if(i!=j && customer_data1.get(i).size()<=6 && customer_data1.get(j).size()<=6){
                    graph3.addEdge(i,j);
                }
                else if(customer_data1.get(i).size()>6){
                    graph3.addEdge(i,i);
                    break;
                }
            }
        }
        return graph3.return_map();
    }
}
