package com.kropotov.asrd.dto.company;

import com.kropotov.asrd.entities.common.PageableEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDto implements PageableEntity {
    private Long id;
    private String title;
    private String email;
    private String fax;
    private String militaryRepresentation;
}
