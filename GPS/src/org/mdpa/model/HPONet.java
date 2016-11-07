package org.mdpa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.AllDirectedPaths;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedSubgraph;
import org.mdpa.dao.DBDriver;
import org.mdpa.model.HPO;

public class HPONet
{

	private DirectedGraph<HPO, DefaultEdge> HPONet = null;
	private HashMap<String, HPO> HPOMap = null;

	public void initWithDB()
	{
		String sql = null;
		DBDriver db1 = null;
		ResultSet ret = null;
		sql = "select * from HPO";
		db1 = new DBDriver(sql);
		HPOMap = new HashMap<String, HPO>();
		try
		{
			ret = db1.pst.executeQuery();
			while (ret.next())
			{
				HPO hpo = new HPO();
				hpo.setID(ret.getString(1));
				hpo.setName(ret.getString(2));
				hpo.setDefinition(ret.getString(3));
				hpo.setComment(ret.getString(4));
				hpo.setSynonym(ret.getString(5));
				hpo.setParent(ret.getString(6));
				hpo.setAltID(ret.getString(7));
				hpo.setpGroup(ret.getString(8));
				HPOMap.put(hpo.getID(), hpo);
			}
			ret.close();
			db1.close();
		} catch (SQLException e)
		{

			e.printStackTrace();
		}
		HPONet = new DefaultDirectedGraph<>(DefaultEdge.class);
		Iterator<String> it = HPOMap.keySet().iterator();
		while (it.hasNext())
		{
			String key = (String) it.next();
			HPO p = HPOMap.get(key);
			if (p.getParent().equals(""))
			{
				continue;
			}
			if (!HPONet.containsVertex(p))
			{
				HPONet.addVertex(p);
			}
			String par = p.getParent();
			String[] terms = par.split(";");
			for (String t : terms)
			{
				HPO q = HPOMap.get(t);
				if (!HPONet.containsVertex(q))
				{
					HPONet.addVertex(q);
				}
				if (!HPONet.containsEdge(q, p))
				{
					HPONet.addEdge(q, p);
				}
			}

		}
	}

	public void initWithString(String s, HPONet hpo)
	{
		HPONet = new DefaultDirectedGraph<>(DefaultEdge.class);
		HPOMap = hpo.getHPOMap();
		String[] terms = ParseTerms(s);
		for (String t : terms)
		{
			String[] aa = t.split("-");
			HPONet.addVertex(HPOMap.get(aa[0]));
			HPONet.addVertex(HPOMap.get(aa[1]));
			HPONet.addEdge(HPOMap.get(aa[0]), HPOMap.get(aa[1]));
		}
	}
	
	public void initWithArray(String[] arr, HPONet hpoNet)
	{
		Set<HPO> sources = new HashSet<>();
		
		sources.add(hpoNet.getHPOMap().get("HP:0000001"));
		Set<HPO> targets = new HashSet<>();
		for(String a:arr)
		{
			targets.add(hpoNet.getHPOMap().get(a));			
		}
		AllDirectedPaths<HPO, DefaultEdge> allpath = new AllDirectedPaths<HPO, DefaultEdge>(hpoNet.getHPONet());
		List<GraphPath<HPO,DefaultEdge>> pathlist = allpath.getAllPaths(sources, targets, true,null);
		for(GraphPath<HPO,DefaultEdge> p: pathlist)
		{
			targets.addAll(p.getGraph().vertexSet());
		}
		targets.addAll(sources);
		HPOMap = hpoNet.getHPOMap();
		DirectedSubgraph<HPO, DefaultEdge> sub =
                new DirectedSubgraph<HPO, DefaultEdge>(hpoNet.getHPONet(), targets, null);
		HPONet = sub;
	}

	private static String[] ParseTerms(String text)
	{
		String[] tmp = text.split(",");
		return tmp;
	}

	public DirectedGraph<HPO, DefaultEdge> getHPONet()
	{
		return HPONet;
	}

	public void setHPONet(DirectedGraph<HPO, DefaultEdge> hPONet)
	{
		HPONet = hPONet;
	}

	public HashMap<String, HPO> getHPOMap()
	{
		return HPOMap;
	}

	public void setHPOMap(HashMap<String, HPO> hPOMap)
	{
		HPOMap = hPOMap;
	}

}
