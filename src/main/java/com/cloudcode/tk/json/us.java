package com.cloudcode.tk.json;

import java.util.ArrayList;
import java.util.List;

public class us {
	private int age=00;
	private String name="cloudcode";
	private List<String> messages=new ArrayList<String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = -2464622904258110704L;

		{
		add("tk 1");
		add("tk 2");
		}
	};
	public String toString(){
		return "us [age="+age+",name="+name+","+"messages="+messages+"]";
	}
}
