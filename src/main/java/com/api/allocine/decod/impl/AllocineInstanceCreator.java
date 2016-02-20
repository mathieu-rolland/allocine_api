package com.api.allocine.decod.impl;

import java.lang.reflect.Type;

import com.api.allocine.factory.IFactory;
import com.google.gson.InstanceCreator;

public class AllocineInstanceCreator<T> implements InstanceCreator<T>{

	private IFactory factory;
	
	public AllocineInstanceCreator(IFactory factory) {
		this.factory = factory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T createInstance(Type type) {
		return (T) factory.create( type );
	}

}
