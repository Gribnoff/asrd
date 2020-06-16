package com.kropotov.asrd.entities.titles;

import com.kropotov.asrd.entities.common.TitleEntity_;
import com.kropotov.asrd.entities.items.ControlSystem;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SystemTitle.class)
public class SystemTitle_ extends TitleEntity_ {
	public static volatile SingularAttribute<SystemTitle, String> path;
	public static volatile ListAttribute<SystemTitle, ControlSystem> systems;
	public static volatile ListAttribute<SystemTitle, Topic> topics;
	public static volatile ListAttribute<SystemTitle, DeviceTitle> deviceTitles;
	public static volatile ListAttribute<SystemTitle, SystemComponentTitle> systemComponentTitles;
}
