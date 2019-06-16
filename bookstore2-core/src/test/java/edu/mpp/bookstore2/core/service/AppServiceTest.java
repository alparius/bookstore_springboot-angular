package edu.mpp.bookstore2.core.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import edu.mpp.bookstore2.core.ITConfig;
import edu.mpp.bookstore2.core.model.Client;
import edu.mpp.bookstore2.core.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ITConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("/db-data.xml")
public class AppServiceTest {

    @Qualifier("appServiceImpl")
    @Autowired private AppService service;
    //@Autowired private ClientRepository repo;


    @Test
    public void findAll() throws Exception {
        List<Client> clients = service.getAllClients();
        assertEquals("there should be four clients", 4, clients.size());
    }

    @Test
    public void updateClient() throws Exception {
        Client client = Client.builder().name("alp").email("alp@gmail.com").personalId(555).build();
        client.setId(-1L);
        service.updateClient(-1L, client);
        long nr = service.getClientsBasic().stream().filter(c -> c.getName().equals("alp")).count();
        assertEquals("name should be changed", 1L, nr);
    }

    @Test
    public void createClient() throws Exception {
        Client client = Client.builder().name("alp").email("alp@gmail.com").personalId(555).build();
        service.addClient(client);
        List<Client> clients = service.getAllClients();
        assertEquals("there should be 5 clients", 5, clients.size());
    }

    @Test
    public void deleteClient() throws Exception {
        service.deleteClient(-1L);
        List<Client> clients = service.getAllClients();
        assertEquals("there should be 3 clients", 3L, clients.size());
    }

}
