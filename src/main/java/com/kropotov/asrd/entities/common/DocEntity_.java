package com.kropotov.asrd.entities.common;

import com.kropotov.asrd.entities.items.ControlSystem;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DocEntity.class)
public class DocEntity_ {
	public static volatile SingularAttribute<ControlSystem, String> path;
}
