CREATE DATABASE bookstore;

DELETE FROM transaction;
DELETE FROM client;
DELETE FROM book;

INSERT INTO client (id, personal_id, name, email) VALUES
(99991,1111,'Dobrea Andrei','dobreaandrei@gmail.com'),
(99992,1112,'Cseke Alpar','csekealpar@gmail.com'),
(99993,1113,'John Tom','johntom@citromail.hu'),
(99994,1114,'Rob Dead','robdead@yahoo.com'),
(99995,1115,'Bookworm Beatrix','beatrix@gmail.com'),
(99996,1116,'Natalie Nerd','readalot99@gmail.com'),
(99997,1117,'Spare Me','noreply@nomail.no'),
(99998,1118,'Customer Carol','idliketospeak@yahoo.ro'),
(99999,1119,'Penguine Pango','pppppppp@gmail.com');

INSERT INTO book (id,isbn,title,author,publisher,price) VALUES
(88881,'ISBN2221','Don Quijote','Miguel Cervantes','Europa',79),
(88882,'ISBN2222','Champions Breakfast','Kurt Vonnegut','Helikon',100),
(88883,'ISBN2223','Paradise Lost','John Milton','fapadoskonyvek',35),
(88884,'ISBN2224','Faust','Goether','Europa',45),
(88885,'ISBN2225','The Black cat and other novels','Edgar Allan Poe','Alinea',59),
(88886,'ISBN2226','Hitchhikers Guide to the Galaxy','Douglas Adams','GABO',1),
(88887,'ISBN2227','Cseke Alpar','Notes','Accidentally Published',9998),
(88888,'ISBN2228','My Passport','Western Digital','China',279),
(88889,'ISBN2229','Soundcore 2','Anker','Amazon',339);

INSERT INTO transaction (id, client_id,book_id,notes) VALUES
(77771,99991,88882,'wanna read it so much'),
(77772,99991,88887,'just to show off at the store'),
(77773,99995,88881,'she reads a lot'),
(77774,99995,88884,'she really does read a lot'),
(77775,99995,88886,'she reads too much'),
(77776,99999,88885,'some penguin horror'),
(77777,99999,88889,'its not even a book, Carol');