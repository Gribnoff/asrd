package com.kropotov.asrd.entities.common;

import com.kropotov.asrd.entities.enums.Status;
import com.kropotov.asrd.entities.items.ControlSystem;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(InfoEntity.class)
public class InfoEntity_ extends BaseEntity_{
	public static volatile SingularAttribute<ControlSystem, Status> entityStatus;
	public static volatile SingularAttribute<ControlSystem, LocalDateTime> createdAt;
	public static volatile SingularAttribute<ControlSystem, LocalDateTime> updatedAt;
}
