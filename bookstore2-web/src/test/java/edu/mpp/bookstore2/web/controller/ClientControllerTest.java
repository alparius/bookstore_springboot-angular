package edu.mpp.bookstore2.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mpp.bookstore2.core.model.Client;
import edu.mpp.bookstore2.core.service.AppService;
import edu.mpp.bookstore2.web.converter.ClientConverter;
import edu.mpp.bookstore2.web.dto.ClientDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClientControllerTest {

    private MockMvc mockMvc;

    @InjectMocks private ClientController clientController;
    @Mock private AppService service;
    @Mock private ClientConverter clientConverter;

    private Client client1;
    private Client client2;
    private ClientDto clientDto1;
    private ClientDto clientDto2;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(clientController)
                .build();
        initData();
    }

    private void initData() {
        client1 = Client.builder().name("name1").personalId(111).email("name1@gmail.com").build();
        client1.setId(1L);
        client2 = Client.builder().name("name2").personalId(222).email("name2@gmail.com").build();
        client2.setId(2L);

        clientDto1 = createClientDto(client1);
        clientDto2 = createClientDto(client2);
    }

    private String toJsonString(Map<String, ClientDto> clientDtoMap) {
        try {
            return new ObjectMapper().writeValueAsString(clientDtoMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String toJsonString(ClientDto clientDto) {
        try {
            return new ObjectMapper().writeValueAsString(clientDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private ClientDto createClientDto(Client client) {
        ClientDto clientDto = ClientDto.builder()
                .personalId(client.getPersonalId())
                .name(client.getName())
                .email(client.getEmail())
                .build();
        clientDto.setId(client.getId());
        return clientDto;
    }

    @Test
    public void getClients() throws Exception {
        List<Client> clients = Arrays.asList(client1, client2);
        Set<ClientDto> clientDtos =
                new HashSet<>(Arrays.asList(clientDto1, clientDto2));
        when(service.getAllClients()).thenReturn(clients);
        when(clientConverter.convertModelsToDtos(clients)).thenReturn(clientDtos);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", anyOf(is("name1"), is("name2"))))
                .andExpect(jsonPath("$[1].personalId", anyOf(is(111), is(222))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println(result);

        verify(service, times(1)).getAllClients();
        verify(clientConverter, times(1)).convertModelsToDtos(clients);
        verifyNoMoreInteractions(service, clientConverter);
    }

    @Test
    public void updateClient() throws Exception {
        when(clientConverter.convertDtoToModel(any(ClientDto.class))).thenReturn(client1);
        when(clientConverter.convertModelToDto(any(Client.class))).thenReturn(clientDto1);
        when(service.updateClient(client1.getId(),client1)).thenReturn(client1);

//        Map<String, ClientDto> clientDtoMap = new HashMap<>();
//        clientDtoMap.put("client", clientDto1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        //.put("/api/clients/{id}", client1.getId(), clientDto1)
                        .put("/api/clients/{id}", client1.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(clientDto1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("name1")));

        verify(service, times(1)).updateClient(client1.getId(), client1);
        verify(clientConverter, times(1)).convertModelToDto(client1);
        verify(clientConverter, times(1)).convertDtoToModel(any(ClientDto.class));
        verifyNoMoreInteractions(service, clientConverter);
    }
    
    @Test
    public void createClient() throws Exception {
        when(clientConverter.convertDtoToModel(any(ClientDto.class))).thenReturn(client1);
        when(clientConverter.convertModelToDto(any(Client.class))).thenReturn(clientDto1);
        when(service.addClient(client1)).thenReturn(client1);

//        Map<String, StudentDto> studentDtoMap = new HashMap<>();
//        studentDtoMap.put("student", studentDto1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        //.post("/api/clients",clientDto1)
                        .post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(clientDto1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("name1")));

        verify(service, times(1)).addClient(client1);
        verify(clientConverter, times(1)).convertModelToDto(client1);
        verify(clientConverter, times(1)).convertDtoToModel(any(ClientDto.class));
        verifyNoMoreInteractions(service, clientConverter);
    }

    @Test
    public void deleteClient() throws Exception {
        doNothing().when(service).deleteClient(client1.getId());

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/clients/{id}", client1.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(""))
                .andExpect(status().isOk());
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        verify(service, times(1)).deleteClient(client1.getId());
        verifyNoMoreInteractions(service);
    }
}