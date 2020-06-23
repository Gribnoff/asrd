package com.kropotov.asrd.entities.docs;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.common.DocEntity_;
import com.kropotov.asrd.entities.enums.Result;
import com.kropotov.asrd.entities.items.ControlSystem;
import com.kropotov.asrd.entities.items.Device;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(ActInputControl.class)
public class ActInputControl_ extends DocEntity_ {
    public static volatile SingularAttribute<ActInputControl, String> number;
    public static volatile SingularAttribute<ActInputControl, Invoice> invoice;
    public static volatile SingularAttribute<ActInputControl, LocalDate> date;
    public static volatile SingularAttribute<ActInputControl, Result> result;
    public static volatile SingularAttribute<ActInputControl, String> description;
    public static volatile SingularAttribute<ActInputControl, User> user;
    public static volatile ListAttribute<ActInputControl, ControlSystem> systems;
    public static volatile ListAttribute<ActInputControl, Device> devices;
}
