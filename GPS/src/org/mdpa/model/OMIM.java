package org.mdpa.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mdpa.dao.DBDriver;

public class OMIM
{
	private String ID = null;
	private String Name = null;
	private String[] Gene = null;
	private HPONet hpoNet = null;
	private String[] hpo = null;
	private String[] nhpo = null;
	//private String des = null;
	
	public void initWithID(String id, HPONet hpoMap)
	{
		String sql = "select * from OMIM where OMIM_ID ="+id;
		DBDriver db1 = new DBDriver(sql);
		ResultSet ret = null;
		try
		{
			ret = db1.pst.executeQuery();
			while (ret.next())
			{
				ID = id;
				Name = ret.getString(2);
				Gene = ret.getString(3).split("\\;");
				hpoNet = new HPONet();
				hpoNet.initWithString(ret.getString(4), hpoMap);
				hpo = ret.getString(5).split("\\;");
				nhpo = ret.getString(6).split("\\;");
				
			}
			ret.close();
			db1.close();
		} catch (SQLException e)
		{

			e.printStackTrace();
		}
		
		
	}
	
	
	
	public String getID()
	{
		return ID;
	}
	public void setID(String iD)
	{
		ID = iD;
	}
	public String getName()
	{
		return Name;
	}
	public void setName(String name)
	{
		Name = name;
	}
	public String[] getGene()
	{
		return Gene;
	}
	public void setGene(String[] gene)
	{
		Gene = gene;
	}
	public HPONet getHpoNet()
	{
		return hpoNet;
	}
	public void setHpoNet(HPONet hpoNet)
	{
		this.hpoNet = hpoNet;
	}
	public String[] getHpo()
	{
		return hpo;
	}
	public void setHpo(String[] hpo)
	{
		this.hpo = hpo;
	}
	public String[] getNhpo()
	{
		return nhpo;
	}
	public void setNhpo(String[] nhpo)
	{
		this.nhpo = nhpo;
	}


	
	

}
