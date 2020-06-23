package com.kropotov.asrd.entities.titles;


import com.kropotov.asrd.entities.common.TitleEntity_;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SystemComponentTitle.class)
public class SystemComponentTitle_ extends TitleEntity_ {
    public static volatile SingularAttribute<SystemComponentTitle, String> path;
    public static volatile ListAttribute<SystemComponentTitle, SystemTitle> systemTitles;

}
