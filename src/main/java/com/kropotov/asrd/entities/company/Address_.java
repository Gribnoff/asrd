package com.kropotov.asrd.entities.company;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Address.class)
public class Address_ {
    public static volatile SingularAttribute<Address, Long> id;
    public static volatile SingularAttribute<Address, String> zipCode;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, String> place;
    public static volatile SingularAttribute<Address, String> description;
    public static volatile SingularAttribute<Address, Company> company;
}
