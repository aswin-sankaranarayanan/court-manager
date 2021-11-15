insert into client (id,email, name, owner) values (1,'client1@gmail.com', 'Client 1', 'Client 1');
insert into client (id,email, name, owner) values (2,'client2@gmail.com', 'Client 2', 'Client 2');
insert into client (id,email, name, owner) values (3,'client3@gmail.com', 'Client 3', 'Client 3');
insert into client (id,email, name, owner) values (4,'client4@gmail.com', 'Client 4', 'Client 4');

insert into client_branch (id,client_fk, court_fee, guest_fee, location, monthly_fee) values (1,1,400.0, 150.0, 'Chrompet', 1000.0);
insert into client_branch (id,client_fk, court_fee, guest_fee, location, monthly_fee) values (2,2,400.0, 100.0, 'Pallvaram', 1200.0);



