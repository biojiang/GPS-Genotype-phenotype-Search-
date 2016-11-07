package org.mdpa.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.mdpa.dao.DBDriver;

public class OMIMSet
{
	private HashMap<String, OMIM> omimMap = null;
	
	public void initWithDB(HPONet hpoNet)
	{
		String sql = null;
		DBDriver db1 = null;
		ResultSet ret = null;
		sql = "select * from OMIM";
		db1 = new DBDriver(sql);
		omimMap = new HashMap<String, OMIM>();
		try
		{
			ret = db1.pst.executeQuery();
			while (ret.next())
			{
				OMIM tmp = new OMIM();
				tmp.setID(ret.getString(1));
				tmp.setName(ret.getString(2));
				tmp.setGene(ret.getString(3).split("\\;"));
				HPONet hpoNet1 = new HPONet();
				hpoNet1.initWithString(ret.getString(4), hpoNet);
				tmp.setHpoNet(hpoNet1);				
				tmp.setHpo(ret.getString(5).split("\\;"));
				tmp.setNhpo(ret.getString(6).split("\\;"));
				omimMap.put(ret.getString(1), tmp);
			}
			ret.close();
			db1.close();
		} catch (SQLException e)
		{

			e.printStackTrace();
		}
	}

	public HashMap<String, OMIM> getOmimMap()
	{
		return omimMap;
	}

	public void setOmimMap(HashMap<String, OMIM> omimMap)
	{
		this.omimMap = omimMap;
	}
	

}
