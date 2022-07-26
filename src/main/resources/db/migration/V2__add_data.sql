insert into public.admin(email, name, phone_number, staff_name)
values ('Amigo@mail.ag', 'Amigo', 112, 'SysAdmin');

insert into public.client(email, name, phone_number, birthdate)
values ('mvi@gov.ru', 'Matvienko Valentina Ivanovna', 777555, '1949-04-07');

insert into public.clinic_branch(address, close_time, name, open_time, admin_id)
values ('B.P. 110', '21:00', 'Petrogradsky', '9:00', 1);

insert into public.clinic_lab(close_time, open_time, position_name, worker_name, branch_id)
values ('16:00', '7:00', 'Laboratory assistant', 'Borisov Aleksandr Petrovich', 1);

insert into public.clinic_procedure(duration, name)
values (60, 'GYM');

insert into public.doctor(position_name, add_position_name, birthdate, email, name, phone_number)
values ('military doctorDto', 'medical doctorDto', '1850-07-07', 'watson@gmail.com', 'John H. Watson', 911);

insert into public.doctor_appointment(visit_date, branch_id, client_id, doctor_id)
values ('2022-04-22', 1, 1, 1);

insert into public.procedure_appointment(visit_date, branch_id, client_id, procedure_id)
values ('2122-09-01', 1, 1, 1);

insert into public.take_test_appointment(name, visit_date, client_id, lab_id)
values ('Blood test', '2022-04-22', 1, 1);
