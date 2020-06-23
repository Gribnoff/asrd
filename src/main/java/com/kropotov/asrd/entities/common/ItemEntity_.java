package com.kropotov.asrd.entities.common;

import com.kropotov.asrd.entities.enums.Location;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(ItemEntity.class)
public class ItemEntity_ extends InfoEntity_{
	public static volatile SingularAttribute<ItemEntity, String> number;
	public static volatile SingularAttribute<ItemEntity, Location> location;
	public static volatile SingularAttribute<ItemEntity, String> purpose;
	public static volatile SingularAttribute<ItemEntity, String> purposePassport;
	public static volatile SingularAttribute<ItemEntity, LocalDate> vintage;
	public static volatile SingularAttribute<ItemEntity, Integer> vpNumber;
	public static volatile SingularAttribute<ItemEntity, LocalDate> otkDate;
	public static volatile SingularAttribute<ItemEntity, LocalDate> vpDate;
}
