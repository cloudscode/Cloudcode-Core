package com.cloudcode.common.util;


import java.util.UUID;

public class GUID 
{
	public static  String NEWID()
	{
		return UUID.randomUUID().toString();
	}
}
