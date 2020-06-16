package com.kropotov.asrd.entities.items;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.common.ItemEntity_;
import com.kropotov.asrd.entities.docs.ActInputControl;
import com.kropotov.asrd.entities.docs.File;
import com.kropotov.asrd.entities.docs.Invoice;
import com.kropotov.asrd.entities.titles.SystemTitle;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ControlSystem.class)
public class ControlSystem_ extends ItemEntity_ {
	public static volatile SingularAttribute<ControlSystem, SystemTitle> title;
	public static volatile SingularAttribute<ControlSystem, User> user;
	public static volatile ListAttribute<ControlSystem, ActInputControl> actsInputControl;
	public static volatile ListAttribute<ControlSystem, Invoice> invoices;
	public static volatile ListAttribute<ControlSystem, Device> devices;
	public static volatile ListAttribute<ControlSystem, File> files;
}
