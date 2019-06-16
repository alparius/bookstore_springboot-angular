package edu.mpp.bookstore2.web.controller;

import edu.mpp.bookstore2.core.model.Client;
import edu.mpp.bookstore2.core.service.AppService;
import edu.mpp.bookstore2.core.service.AppServiceImpl;
import edu.mpp.bookstore2.web.converter.ClientConverter;
import edu.mpp.bookstore2.web.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired private AppService service;
    @Autowired private ClientConverter converter;

    @RequestMapping(value = "", method = RequestMethod.GET)
    List<ClientDto> getAllClients() {
        log.trace("getAllClients --- method entered");
        List<Client> clients = service.getAllClients();
        List<ClientDto> result = new ArrayList<>(converter.convertModelsToDtos(clients));
        log.trace("getAllClients: result={}", result);
        return result;
    }
    
//    @RequestMapping(value = "/filter={key}", method = RequestMethod.GET)
//    List<ClientDto> filterClients(@PathVariable String key) {
//        log.trace("filterClients --- method entered");
//        List<Client> clients = service.filterClients(key);
//        List<ClientDto> result = new ArrayList<>(converter.convertModelsToDtos(clients));
//        log.trace("filterClients: result={}", result);
//        return result;
//    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    ClientDto saveClient(@RequestBody ClientDto dto) {
        log.trace("saveClient: dto={}", dto);
        Client saved = this.service.addClient(converter.convertDtoToModel(dto));
        ClientDto result = converter.convertModelToDto(saved);
        log.trace("saveClient: result={}", result);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long id, @RequestBody ClientDto dto) {
        log.trace("updateClient: id={},dto={}", id, dto);
        Client update = service.updateClient(id, converter.convertDtoToModel(dto));
        ClientDto result = converter.convertModelToDto(update);
        log.trace("updateClient: result={}", result);
        return result;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        log.trace("deleteClient: id={}", id);
        service.deleteClient(id);
        log.trace("deleteClient --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
