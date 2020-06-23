package com.kropotov.asrd.entities.titles;

import com.kropotov.asrd.entities.common.TitleEntity_;
import com.kropotov.asrd.entities.items.DeviceComponent;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DeviceComponentTitle.class)
public class DeviceComponentTitle_ extends TitleEntity_ {
    public static volatile SingularAttribute<DeviceComponentTitle, DeviceTitle> deviceTitle;
    public static volatile ListAttribute<DeviceComponentTitle, DeviceComponent> deviceComponents;
}
