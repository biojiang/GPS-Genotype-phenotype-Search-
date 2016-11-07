package org.mdpa.test;

import org.mdpa.model.HPONet;
import org.mdpa.model.OMIM;
import org.mdpa.model.OMIMSet;

public class Test
{

	public static void main(String[] args)
	{
		HPONet hpNet = new HPONet();
		long t1 = 0;
		long t2 = 0;
		t1 = System.currentTimeMillis();
		hpNet.initWithDB();
		t2 = System.currentTimeMillis();
		System.out.println("HPO Net time:"+(t2-t1)+"ms");
		//String s = "HP:0000001-HP:0000118,HP:0000118-HP:0001626,HP:0001626-HP:0002597,HP:0001679-HP:0001724,HP:0001724-HP:0004942,HP:0001724-HP:0005112,HP:0002597-HP:0002617,HP:0002597-HP:0011004,HP:0002617-HP:0004942,HP:0004942-HP:0004953,HP:0005112-HP:0004953,HP:0011004-HP:0001679";
		//HPONet on = new HPONet();
		//on.initWithString(s, hpNet.getHPOMap());
		//OMIM om = new OMIM();
		//om.initWithID("100050", hpNet);
		//System.out.println(om);
		//String[] s = {"HP:0100261","HP:0012374","HP:0010886"};
		//HPONet hp2 = new HPONet();
		//hp2.initWithArray(s, hpNet);
		//System.out.println(hp2.getHPONet().toString());
		OMIMSet os = new OMIMSet();
		t1 = System.currentTimeMillis();
		os.initWithDB(hpNet);
		t2 = System.currentTimeMillis();
		System.out.println("OMIM Set time:"+(t2-t1)+"ms");
		t1 = System.currentTimeMillis();
		System.out.println(os.getOmimMap().get("100100").getName());
		t2 = System.currentTimeMillis();
		System.out.println("Item time:"+(t2-t1)+"ms");
	}

}
