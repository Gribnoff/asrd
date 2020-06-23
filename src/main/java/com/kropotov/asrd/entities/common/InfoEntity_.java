package com.kropotov.asrd.entities.common;

import com.kropotov.asrd.entities.enums.Status;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

@StaticMetamodel(InfoEntity.class)
public class InfoEntity_ extends BaseEntity_{
	public static volatile SingularAttribute<InfoEntity, Status> entityStatus;
	public static volatile SingularAttribute<InfoEntity, LocalDateTime> createdAt;
	public static volatile SingularAttribute<InfoEntity, LocalDateTime> updatedAt;
}
