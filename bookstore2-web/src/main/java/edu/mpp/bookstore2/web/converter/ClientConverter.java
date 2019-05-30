package edu.mpp.bookstore2.web.converter;

import edu.mpp.bookstore2.core.model.Client;
import edu.mpp.bookstore2.core.model.Transaction;
import edu.mpp.bookstore2.web.dto.ClientDto;
import edu.mpp.bookstore2.web.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ClientConverter extends AbstractConverterBaseEntity<Client, ClientDto> {

    @Autowired TransactionConverter transactionConverter;

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = Client.builder()
                .personalId(dto.getPersonalId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        Set<Transaction> transactionSet = client.getTransactions();
        Set<TransactionDto> transactionDtos = new HashSet<>();
        transactionSet.forEach(t -> transactionDtos.add(transactionConverter.convertModelToDto(t)));

        ClientDto dto = ClientDto.builder()
                .personalId(client.getPersonalId())
                .name(client.getName())
                .email(client.getEmail())
                .transactions(transactionDtos)
                .build();
        dto.setId(client.getId());
        return dto;
    }
}