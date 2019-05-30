package edu.mpp.bookstore2;

import edu.mpp.bookstore2.core.model.Book;
import edu.mpp.bookstore2.core.model.Client;
import edu.mpp.bookstore2.core.service.AppService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class Bookstore2Application {

    public static void main(String[] args) {
        SpringApplication.run(Bookstore2Application.class, args);
    }

    // Bootstrap some test data into the in-memory database
    @Bean
    ApplicationRunner init(AppService service) {
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
        };
    }

    // TODO: Lab 13: handling the n + 1 select problem
    //      - Use Spring Boot
    //      - All associations must be lazily loaded
    //      - Query the entities using:
    //          - Spring Queries with Named Entity Graphs
    //          - one of the following:
    //		        - JPQL
    //		        - Criteria API
    //		        - Native SQL
    //  Note:
    //      1. you will have to alternate between Entity Graphs and one of the other query approaches (JPQL, Criteria API, Native SQL)  within the same repository
    //      2. There will be one repository per aggregate root (e.g: StudentRepo, DisciplineRepo; but no StudentDisciplineRepo or no GradeRepo)

}
