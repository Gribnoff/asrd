package com.kropotov.asrd.entities.company;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Company.class)
public class Company_ {
    public static volatile SingularAttribute<Company, Long> id;
    public static volatile SingularAttribute<Company, String> title;
    public static volatile SingularAttribute<Company, String> email;
    public static volatile SingularAttribute<Company, String> fax;
    public static volatile SingularAttribute<Company, String> militaryRepresentation;
    public static volatile ListAttribute<Company, CompanyPhone> companyPhones;
    public static volatile ListAttribute<Company, Address> address;
    public static volatile ListAttribute<Company, Employee> employee;
}
