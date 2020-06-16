package com.kropotov.asrd.entities.common;

import com.kropotov.asrd.entities.enums.Location;
import com.kropotov.asrd.entities.items.ControlSystem;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

@StaticMetamodel(ItemEntity.class)
public class ItemEntity_ extends InfoEntity_{
	public static volatile SingularAttribute<ControlSystem, String> number;
	public static volatile SingularAttribute<ControlSystem, Location> location;
	public static volatile SingularAttribute<ControlSystem, String> purpose;
	public static volatile SingularAttribute<ControlSystem, String> purposePassport;
	public static volatile SingularAttribute<ControlSystem, LocalDate> vintage;
	public static volatile SingularAttribute<ControlSystem, Integer> vpNumber;
	public static volatile SingularAttribute<ControlSystem, LocalDate> otkDate;
	public static volatile SingularAttribute<ControlSystem, LocalDate> vpDate;
}
