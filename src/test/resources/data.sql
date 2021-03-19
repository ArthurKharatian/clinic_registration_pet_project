insert into public.admin(id, email, name, phone_number, staff_name)
values (1, 'Amigo@mail.ag', 'Amigo', 112, 'SysAdmin');

insert into public.client(id, email, name, phone_number, birthdate)
values (1, 'mvi@gov.ru', 'Matvienko Valentina Ivanovna', 777555, '1949-04-07');

insert into public.clinic_branch(id, address, close_time, name, open_time, admin_id)
values (1, 'B.P. 110', '21:00', 'Petrogradsky', '9:00', 1);

insert into public.clinic_lab(id, close_time, open_time, position_name, worker_name, branch_id)
values (1, '16:00', '7:00', 'Laboratory assistant', 'Borisov Aleksandr Petrovich', 1);

insert into public.clinic_procedure(id, duration, name)
values (1, 60, 'GYM');

insert into public.doctor(id, position_name, add_position_name, birthdate, email, name, phone_number)
values (1, 'military doctor', 'medical doctor', '1850-07-07', 'watson@gmail.com', 'John H. Watson', 911);


insert into public.doctor_appointment(id, visit_date, branch_id, client_id, doctor_id)
values (1, '2022-04-22', 1, 1, 1);

insert into public.procedure_appointment(id, visit_date, branch_id, client_id, procedure_id)
values (1, '2122-09-01', 1, 1, 1);

insert into public.take_test_appointment(id, name, visit_date, client_id, lab_id)
values (1, 'Blood test', '2022-04-22', 1, 1);
