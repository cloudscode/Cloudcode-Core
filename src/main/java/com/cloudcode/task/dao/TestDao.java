package com.cloudcode.task.dao;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class TestDao {
	public void test(HashMap<String, Object> map) throws Exception {
		System.out.println("5秒执行一次");
	}
	public void test2(HashMap<String, Object> map) throws Exception {
		System.out.println("1秒执行一次");
	}
}
