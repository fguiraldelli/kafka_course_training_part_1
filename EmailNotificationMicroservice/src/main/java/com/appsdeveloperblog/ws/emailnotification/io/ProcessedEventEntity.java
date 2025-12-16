package com.appsdeveloperblog.ws.emailnotification.io;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table
public class ProcessedEventEntity implements Serializable{

  public ProcessedEventEntity(String messageId, String productId) {
    this.messageId = messageId;
    this.productId=productId;
  }

  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false, unique = true)
  private String messageId;

  @Column(nullable = false)
  private String productId;


}
