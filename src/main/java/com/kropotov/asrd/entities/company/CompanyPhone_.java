package com.kropotov.asrd.entities.company;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CompanyPhone.class)
public class CompanyPhone_ {
    public static volatile SingularAttribute<CompanyPhone, Long> id;
    public static volatile SingularAttribute<CompanyPhone, String> phone;
    public static volatile SingularAttribute<CompanyPhone, String> description;
    public static volatile SingularAttribute<CompanyPhone, Company> company;
}
