package com.kropotov.asrd.entities.common;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TitleEntity.class)
public class TitleEntity_ extends BaseEntity_{
	public static volatile SingularAttribute<TitleEntity, String> title;
}
