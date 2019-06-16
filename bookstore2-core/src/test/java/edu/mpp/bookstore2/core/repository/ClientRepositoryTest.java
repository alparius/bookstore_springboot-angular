package edu.mpp.bookstore2.core.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import edu.mpp.bookstore2.core.ITConfig;
import edu.mpp.bookstore2.core.model.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/db-data.xml")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void findAll() throws Exception {
        List<Client> clients = clientRepository.findAll();
        assertEquals("there should be 4 clients", 4, clients.size());
    }

    @Test
    public void addClient() throws Exception {
        Client client = Client.builder().name("alp").email("alp@gmail.com").personalId(555).build();
        clientRepository.save(client);
        List<Client> clients = clientRepository.findAll();
        assertEquals("there should be 5 students", 5, clients.size());
    }

    @Test
    public void updateClient() throws Exception {
        Client client = Client.builder().name("alp").email("alp@gmail.com").personalId(555).build();
        client.setId(-1L);
        clientRepository.save(client);
        long nr = clientRepository.findAll().stream().filter(c -> c.getName().equals("alp")).count();
        assertEquals("name should be changed", 1L, nr);
    }

    @Test
    public void deleteClient() throws Exception {
        clientRepository.deleteById(-1L);
        List<Client> clients = clientRepository.findAll();
        assertEquals("there should be 3 clients", 3L, clients.size());
    }

}
