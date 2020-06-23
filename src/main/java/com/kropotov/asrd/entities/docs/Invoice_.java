package com.kropotov.asrd.entities.docs;

import com.kropotov.asrd.entities.User;
import com.kropotov.asrd.entities.common.DocEntity_;
import com.kropotov.asrd.entities.company.Company;
import com.kropotov.asrd.entities.items.ControlSystem;
import com.kropotov.asrd.entities.items.Device;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(Invoice.class)
public class Invoice_ extends DocEntity_ {
    public static volatile SingularAttribute<Invoice, String> number;
    public static volatile SingularAttribute<Invoice, LocalDate> date;
    public static volatile SingularAttribute<Invoice, Company> from;
    public static volatile SingularAttribute<Invoice, Company> destination;
    public static volatile SingularAttribute<Invoice, String> description;
    public static volatile SingularAttribute<Invoice, User> user;
    public static volatile ListAttribute<Invoice, ControlSystem> systems;
    public static volatile ListAttribute<Invoice, Device> devices;
}
