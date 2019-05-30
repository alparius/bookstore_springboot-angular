package edu.mpp.bookstore2.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientDto extends BaseDto {
    private int personalId;
    private String name;
    private String email;
    private Set<TransactionDto> transactions;
}
