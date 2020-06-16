package com.kropotov.asrd.entities;

import com.kropotov.asrd.entities.common.BaseEntity_;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ extends BaseEntity_ {
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> patronymic;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> workPhone;
	public static volatile SingularAttribute<User, String> mobilePhone;
	public static volatile SingularAttribute<User, StatusUser> statusUser;
	public static volatile CollectionAttribute<User, Role> roles;
}
