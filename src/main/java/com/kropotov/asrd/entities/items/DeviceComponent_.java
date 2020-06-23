package com.kropotov.asrd.entities.items;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.common.ItemEntity_;
import com.kropotov.asrd.entities.titles.DeviceComponentTitle;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DeviceComponent.class)
public class DeviceComponent_ extends ItemEntity_ {
    public static volatile SingularAttribute<DeviceComponent, DeviceComponentTitle> title;
    public static volatile SingularAttribute<DeviceComponent, Device> device;
    public static volatile SingularAttribute<DeviceComponent, User> user;
}
