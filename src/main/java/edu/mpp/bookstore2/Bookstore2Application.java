package edu.mpp.bookstore2;

import edu.mpp.bookstore2.core.model.*;
import edu.mpp.bookstore2.core.service.AppService;
import edu.mpp.bookstore2.core.service.AppServiceImpl;
import edu.mpp.bookstore2.core.service.UserServiceImpl;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Bookstore2Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Bookstore2Application.class, args);
//        AppService service = context.getBean(AppServiceImpl.class);
//        List<Client> clients = service.getClientsBasic();
//        Set<Transaction> empty = new HashSet<>();
//        System.out.println("------------------");
//        clients.forEach(Client::getTransactions);
//        System.out.println("------------------");
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        clients.forEach(c -> c.getTransactions().size());
    }

    // Bootstrap some test data into the database
    @Bean
    ApplicationRunner init(AppServiceImpl service, UserServiceImpl uService) {
        Client c1 = new Client(1111,"Dobrea Andrei","dobreaandrei@gmail.com",null);
        Client c2 = new Client(1112,"Cseke Alpar","csekealpar@gmail.com",null);
        Client c3 = new Client(1113,"John Tom","johntom@citromail.hu",null);
        Client c4 = new Client(1114,"Rob Dead","robdead@yahoo.com",null);
        Client c5 = new Client(1115,"Bookworm Beatrix","beatrix@gmail.com",null);
        Client c6 = new Client(1116,"Natalie Nerd","readalot99@gmail.com",null);
        Client c7 = new Client(1117,"Spare Me","noreply@nomail.no",null);
        Client c8 = new Client(1118,"Customer Carol","idliketospeak@yahoo.ro",null);
        Client c9 = new Client(1119,"Penguine Pango","pppppppp@gmail.com",null);

        Book b1 = new Book("ISBN2221","Don Quijote","Miguel Cervantes","Europa",79,null);
        Book b2 = new Book("ISBN2222","Champions Breakfast","Kurt Vonnegut","Helikon",100,null);
        Book b3 = new Book("ISBN2223","Paradise Lost","John Milton","fapadoskonyvek",35,null);
        Book b4 = new Book("ISBN2224","Faust","Goether","Europa",45,null);
        Book b5 = new Book("ISBN2225","The Black cat and other novels","Edgar Allan Poe","Alinea",59,null);
        Book b6 = new Book("ISBN2226","Hitchhikers Guide to the Galaxy","Douglas Adams","GABO",1,null);
        Book b7 = new Book("ISBN2227","Cseke Alpar","Notes","Accidentally Published",9998,null);
        Book b8 = new Book("ISBN2228","My Passport","Western Digital","China",279,null);
        Book b9 = new Book("ISBN2229","Soundcore 2","Anker","Amazon",339,null);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User client = new User("Somebody","Stranger","client",passwordEncoder.encode("client"), UserRole.CLIENT);
        User vendor = new User("Caserie","Casablanca","vendor",passwordEncoder.encode("vendor"), UserRole.VENDOR);
        User admin = new User("Sys","Armin","admin",passwordEncoder.encode("admin"), UserRole.ADMIN);

        return args -> {
            Stream.of(c1,c2,c3,c4,c5,c6,c7,c8,c9).forEach(service::addClient);
            Stream.of(b1,b2,b3,b4,b5,b6,b7,b8,b9).forEach(service::addBook);
            service.addTransaction(c1.getId(),b2.getId(),"wanna gonna reddit");
            service.addTransaction(c1.getId(),b7.getId(),"just to show off at the store");
            service.addTransaction(c5.getId(),b1.getId(),"she reads a lot");
            service.addTransaction(c5.getId(),b4.getId(),"she really does read a lot");
            service.addTransaction(c5.getId(),b6.getId(),"she reads too much");
            service.addTransaction(c9.getId(),b5.getId(),"some penguin horror");
            service.addTransaction(c9.getId(),b9.getId(),"its not even a book, Carol");
            uService.addUser(client);
            uService.addUser(vendor);
            uService.addUser(admin);
        };
    }

//    TODO Lab 14: testing and security
//      - Testing
//	        - Write integration tests for your repositories and services; use DbUnit, xml datasets [2p]
//          - Write unit tests for your controllers using Mockito [3p]
//      - Security
//	        - Secure the REST API; use Spring Security; passwords will be encrypted; restrict access to certain endpoints based on user roles [2p]
//          - Add a login-logout feature to your application (UI); (the rest api must be secured) [2p]
//          - In the UI, restrict access to certain routes and certain web page elements based on user roles (the rest api must be secured) [1p]

}
