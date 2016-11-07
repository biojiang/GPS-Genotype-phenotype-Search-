package org.mdpa.model;

public class HPO
{
	private String ID = null;
	private String name = null;
	private String definition = null;
	private String comment = null;
	private String synonym = null;
	private String parent = null;
	private String altID = null;
	private String pGroup = null;
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
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDefinition()
	{
		return definition;
	}
	public void setDefinition(String definition)
	{
		this.definition = definition;
	}
	public String getComment()
	{
		return comment;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	public String getSynonym()
	{
		return synonym;
	}
	public void setSynonym(String synonym)
	{
		this.synonym = synonym;
	}
	public String getParent()
	{
		return parent;
	}
	public void setParent(String parent)
	{
		this.parent = parent;
	}
	public String getAltID()
	{
		return altID;
	}
	public void setAltID(String altID)
	{
		this.altID = altID;
	}
	public String getpGroup()
	{
		return pGroup;
	}
	public void setpGroup(String pGroup)
	{
		this.pGroup = pGroup;
	}
	

}
