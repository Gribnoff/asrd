package com.kropotov.asrd.entities.items;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.common.ItemEntity_;
import com.kropotov.asrd.entities.docs.ActInputControl;
import com.kropotov.asrd.entities.docs.Invoice;
import com.kropotov.asrd.entities.titles.DeviceTitle;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.io.File;

@StaticMetamodel(Device.class)
public class Device_ extends ItemEntity_ {
	public static volatile SingularAttribute<Device, DeviceTitle> title;
	public static volatile SingularAttribute<Device, ControlSystem> system;
	public static volatile SingularAttribute<Device, User> user;
	public static volatile ListAttribute<Device, DeviceComponent> components;
	public static volatile ListAttribute<Device, Invoice> invoices;
	public static volatile ListAttribute<Device, ActInputControl> actsInputControl;
	public static volatile ListAttribute<Device, File> files;
}
