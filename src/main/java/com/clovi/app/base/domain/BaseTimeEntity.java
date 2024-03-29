package com.clovi.app.base.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseTimeEntity {

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createDate;

  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

  private Boolean deleted = false;

  public void delete(){
    this.deleted = true;
  }

}
