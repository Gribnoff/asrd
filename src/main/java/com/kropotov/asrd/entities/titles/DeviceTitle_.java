package com.kropotov.asrd.entities.titles;

import com.kropotov.asrd.entities.common.TitleEntity_;
import com.kropotov.asrd.entities.items.Device;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DeviceTitle.class)
public class DeviceTitle_ extends TitleEntity_ {
    public static volatile SingularAttribute<DeviceTitle, String> path;
    public static volatile ListAttribute<DeviceTitle, Device> devices;
    public static volatile ListAttribute<DeviceTitle, SystemTitle> systemTitles;
    public static volatile ListAttribute<DeviceTitle, DeviceComponentTitle> componentTitles;
}
