INSERT INTO department (id, department_name, department_status)
VALUES (1, 'Sistemas', 'A');
INSERT INTO department (id, department_name, department_status)
VALUES (2, 'Contabilidad', 'A');
INSERT INTO department (id, department_name, department_status)
VALUES (3, 'RRHH', 'I');
INSERT INTO department (id, department_name, department_status)
VALUES (4, 'People', 'A');

INSERT INTO employee (id, department_id, employee_name, employee_last_name, age, salary, init_date, end_date, employee_status)
VALUES(default, 1, 'Luis', 'Perez', 22, 500, '2021-02-10', NULL, 'A');
INSERT INTO employee (id, department_id, employee_name, employee_last_name, age, salary, init_date, end_date, employee_status)
VALUES(default, 1, 'Maria', 'Gonzalez', 25, NULL, '2025-06-11', NULL, 'A');
INSERT INTO employee (id, department_id, employee_name, employee_last_name, age, salary, init_date, end_date, employee_status)
VALUES(default, 2, 'Pedro', 'Gomez', 30, NULL, '2020-03-11', '2024-05-20', 'I');
INSERT INTO employee (id, department_id, employee_name, employee_last_name, age, salary, init_date, end_date, employee_status)
VALUES(default, 2, 'Jose', 'Lopez', 20, NULL, NULL, NULL, 'A');
