package com.kropotov.asrd.entities.common;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DocEntity.class)
public class DocEntity_ extends InfoEntity_{
	public static volatile SingularAttribute<DocEntity, String> path;
}
