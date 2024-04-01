INSERT INTO draw VALUES(1);
INSERT INTO pot VALUES(1, 1);
INSERT INTO pot VALUES(2, 2);
INSERT INTO pot VALUES(3, 3);
INSERT INTO pot VALUES(4, 4);
INSERT INTO draw_group (letter, draw_group_id, draw_id) VALUES('A', 1, 1);
INSERT INTO draw_group (letter, draw_group_id, draw_id) VALUES('B', 2, 1);
INSERT INTO draw_group (letter, draw_group_id, draw_id) VALUES('C', 3, 1);
INSERT INTO draw_group (letter, draw_group_id, draw_id) VALUES('D', 4, 1);
INSERT INTO draw_group (letter, draw_group_id, draw_id) VALUES('E', 5, 1);
INSERT INTO draw_group (letter, draw_group_id, draw_id) VALUES('F', 6, 1);
INSERT INTO draw_group (letter, draw_group_id, draw_id) VALUES('G', 7, 1);
INSERT INTO draw_group (letter, draw_group_id, draw_id) VALUES('H', 8, 1);

-- Inserir times do Pote 1
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Fluminense', 2, 1, 1);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Palmeiras', 2, 1, 2);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('River Plate', 0, 1, 3);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Flamengo', 2, 1, 4);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Grêmio', 2, 1, 5);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Peñarol', 20, 1, 6);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('São Paulo', 2, 1, 7);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('LDU', 8, 1, 8);

-- Inserir times do Pote 2
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Atlético-MG', 2, 2, 9);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Independiente Del Valle', 8, 2, 10);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Libertad', 16, 2, 11);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Carro Porteño', 16, 2, 12);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Estudiantes', 0, 2, 13);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Barcelona de Guayaquil', 8, 2, 14);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Bolívar', 1, 2, 15);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Junior Barranquilla', 4, 2, 16);

-- Inserir times do Pote 3
INSERT INTO team (name, country, pot_id, team_id) VALUES ('San Lorenzo', 0, 3, 17);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('The Strongest', 1, 3, 18);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Universitario', 17, 3, 19);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Deportivo Táchira', 19, 3, 20);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Rosário Central', 0, 3, 21);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Alianza Lima', 17, 3, 22);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Millonarios', 4, 3, 23);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Talleres', 0, 3, 24);

-- Inserir times do Pote 4
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Caracas', 19, 4, 25);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Liverpool', 20, 4, 26);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Huachipato', 3, 4, 27);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Cobresal', 3, 4, 28);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Botafogo', 2, 4, 29);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Palestino', 3, 4, 30);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Colo Colo', 3, 4, 31);
INSERT INTO team (name, country, pot_id, team_id) VALUES ('Nacional', 20, 4, 32);
