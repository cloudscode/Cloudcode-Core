package com.cloudcode.common.util.interceptor;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.cloudcode.common.util.BeanUtils;
import com.cloudcode.usersystem.model.User;


public class DAOInterceptor extends EmptyInterceptor implements Serializable {
 
	private static final long serialVersionUID = 8289755357462077893L;
	@Override
	public void onDelete(
			Object entity, 
			Serializable id, 
			Object[] state, 
			String[] propertyNames, 
			Type[] types) {
		System.out.println("**onDelete**");
	}

	@Override
	public boolean onFlushDirty(
			Object entity, 
			Serializable id, 
			Object[] currentState, 
			Object[] previousState, 
			String[] propertyNames, 
			Type[] types) {
		System.out.println("**onFlushDirty**");
		return false;
	}

	@Override
	public boolean onLoad(
			Object entity, 
			Serializable id, 
			Object[] state, 
			String[] propertyNames, 
			Type[] types) {
		System.out.println("**onLoad**");
		return false;
	}

	@Override
	public boolean onSave(
			Object entity, 
			Serializable id, 
			Object[] state, 
			String[] propertyNames, 
			Type[] types) {
		User  user = BeanUtils.getUserDetailAdapter();
		System.out.println("**onSave**"+user.getUserName());
		return false;
	}

	@Override
	public void postFlush(Iterator entities) {
		System.out.println("**postFlush**");
	}

	@Override
	public void preFlush(Iterator entities) {
		System.out.println("**preFlush**");
	}
}

