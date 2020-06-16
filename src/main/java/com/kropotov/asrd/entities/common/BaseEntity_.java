package com.kropotov.asrd.entities.common;

import com.kropotov.asrd.entities.items.ControlSystem;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BaseEntity.class)
public class BaseEntity_ {
	public static volatile SingularAttribute<ControlSystem, Long> id;
}
