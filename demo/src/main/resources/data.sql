 INSERT INTO positions (id, name) VALUES
  (1,'dev'),
  (2,'qa'),
  (3,'manager');

 INSERT INTO persons (id, name, last_name, address, cellphone, city_name) VALUES
  (1,'Camilo', 'Ruiz', 'cra', '124', 'Medellin'),
  (2,'Andres', 'Escobar', 'cra', '1244', 'Envigado'),
  (3,'Luis', 'Perez', 'cra', '124', 'Bogota'),
  (4,'Pedro', 'Castro', 'cra', '1244', 'Cali');

  INSERT INTO employees (id, person_id, position_id, salary) VALUES
  (1,1,2,3500),
  (2,2,2,5500),
  (3,3,2,10000),
  (4,4,1,3000);