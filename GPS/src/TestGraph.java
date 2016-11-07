import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.alg.AllDirectedPaths;
import org.jgrapht.graph.*;

public class TestGraph
{

	
	 public static void main(String [] args)
    {
        // create a graph based on URL objects
        DirectedGraph<String, DefaultEdge> hrefGraph = createHrefGraph();

        // note directed edges are printed as: (<v1>,<v2>)
        System.out.println(hrefGraph.toString());
        Set<String> sources = new HashSet<>();
        Set<String> targets = new HashSet<>();
        sources.add("HP:0000001");
        //sources.add("HP:0000006");
        targets.add("HP:0002816");
        //targets.add("HP:0003508");
        AllDirectedPaths<String, DefaultEdge> path = new AllDirectedPaths<String, DefaultEdge>(hrefGraph);
        List<GraphPath<String,DefaultEdge>> L = path.getAllPaths(sources, targets, true,null);
        System.out.println(L.get(0).getGraph().toString());
        System.out.println(L.get(1).toString());
        
    }
	
	
	private static DirectedGraph<String, DefaultEdge> createHrefGraph()
	{
		DirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
		File file = new File("F:/network/N1.txt");
		try
		{
			 InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			 BufferedReader bufferedReader = new BufferedReader(read);
			 String lineTxt = null;
             while((lineTxt = bufferedReader.readLine()) != null)
             {
            	 String[] terms = ParseTerms(lineTxt);
            	 for(String t:terms)
            	 {
            		 String[] aa = t.split("-");
            		 g.addVertex(aa[0]); 
            		 g.addVertex(aa[1]);
            		 g.addEdge(aa[0], aa[1]);
            	 }
            	 
             }
             read.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return g;
	}
	
	private static String[] ParseTerms(String text)
	{
		String[] tmp = text.split(",");
		return tmp;
	}
	

}
