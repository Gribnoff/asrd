package com.kropotov.asrd.entities.common;

import com.kropotov.asrd.entities.titles.SystemTitle;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TitleEntity.class)
public class TitleEntity_ extends BaseEntity_{
	public static volatile SingularAttribute<SystemTitle, String> title;
}
