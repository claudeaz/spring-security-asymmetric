package com.example.tuto_spring_security.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false, nullable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", insertable = false)
    private String lastModifiedBy;
}
