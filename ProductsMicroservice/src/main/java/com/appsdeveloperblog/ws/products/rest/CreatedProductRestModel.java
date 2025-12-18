package com.appsdeveloperblog.ws.products.rest;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatedProductRestModel {

  private String title;
  private BigDecimal price;
  private Integer quantity;

}
