package com.app.blockbuster.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutDTO {
    private String movieName;
    private Long userId;
}
