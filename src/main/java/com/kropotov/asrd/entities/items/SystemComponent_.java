package com.kropotov.asrd.entities.items;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.common.ItemEntity_;
import com.kropotov.asrd.entities.titles.SystemComponentTitle;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SystemComponent.class)
public class SystemComponent_ extends ItemEntity_ {
    public static volatile SingularAttribute<SystemComponent, SystemComponentTitle> title;
    public static volatile SingularAttribute<SystemComponent, User> user;
}
