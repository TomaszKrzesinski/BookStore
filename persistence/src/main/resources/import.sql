insert into book (id, title, authors, status) values (1, 'First book', 'Jan Kowalski', 'FREE');
insert into book (id, title, authors, status) values (2, 'Second book', 'Zbigniew Nowak', 'FREE');
insert into book (id, title, authors, status) values (3, 'Third book', 'Janusz Jankowski', 'FREE');
insert into book (id, title, authors, status) values (4, 'Starter kit book', 'Kacper Ossoliński', 'FREE');
insert into book (id, title, authors, status) values (5, 'Z kamerą wśród programistów', 'Krystyna Czubówna', 'MISSING');

insert into user (id, user_name, password, enabled, role) values (1, 'admin', '$2a$10$t5IEr07MU1on1QngANuVa.LH.CnenbTcQhlqggEvwwQPVCb0V43wq', true, 'ADMIN');
insert into user (id, user_name, password, enabled, role) values (2, 'Tomasz', '$2a$10$ObN6ptqmieIiN07y6Qj2xOUjwxGJEjfngUv9TmJajaM5u8d/9QjIu', true, 'ADMIN');
insert into user (id, user_name, password, enabled, role) values (3, 'Marek', '$2a$10$gM018qpwBSDGbxC6HCBaiOur2oD/uf5FhFLDj3tGhU.YZuoX51fRC', true, 'USER');
insert into user (id, user_name, password, enabled, role) values (4, 'Damian', '$2a$10$itz8AbwScZoAGMyj4ZXuv.ADOZYqS5iaaaxWNVkz5wwOl1BqdJSEa', true, 'USER');