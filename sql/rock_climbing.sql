drop database if exists rock_climbing;
create database rock_climbing;
use rock_climbing;

create table route_grade (
	route_grade_id int primary key auto_increment,
    grading_system varchar(25) not null,
	grade_level varchar(7) not null
);

create table gym (
	gym_id int primary key auto_increment,
    gym_name varchar(25) not null,
    city varchar(30) not null,
    state varchar(2) not null
);

create table route (
	route_id int primary key auto_increment,
    gym_id int not null,
	route_grade_id int not null,
	route_type varchar(15) not null,
    attempts int not null,
    set_date date,
    constraint fk_route_gym_id
		foreign key (gym_id)
        references gym(gym_id),
	constraint fk_route_route_grade_id
		foreign key (route_grade_id)
        references route_grade(route_grade_id)
);

create table climber (
	climber_id int primary key auto_increment,
    climber_name varchar(25) not null,
    climber_age int not null,
    length_of_time_climbing int not null
);

create table climber_route (
	climber_id int not null,
    route_id int not null,
    constraint pk_climber_route
		primary key (climber_id, route_id),
	constraint fk_climber_route_climber_id
		foreign key (climber_id)
        references climber(climber_id),
	constraint fk_climber_route_route_id
		foreign key (route_id)
        references route(route_id)
);

-- password is set to "P@ssw0rd!"
-- data
insert into route_grade (grading_system, grade_level)
	values
	('BOULDERING', 'VIntro'),
    ('BOULDERING', 'V0'),
    ('BOULDERING', 'V1'),
    ('BOULDERING', 'V2'),
    ('BOULDERING', 'V3'),
    ('BOULDERING', 'V4'),
    ('BOULDERING', 'V5'),
    ('BOULDERING', 'V6'),
    ('BOULDERING', 'V7'),
	('BOULDERING', 'V8'),
    ('BOULDERING', 'V9'),
    ('BOULDERING', 'V10'),
    ('BOULDERING', 'V11'),
    ('BOULDERING', 'V12'),
    ('YOSEMITE', '5.Intro'),
    ('YOSEMITE', '5.4'),
    ('YOSEMITE', '5.5'),
    ('YOSEMITE', '5.6'),
    ('YOSEMITE', '5.7'),
    ('YOSEMITE', '5.8'),
    ('YOSEMITE', '5.9'),
    ('YOSEMITE', '5.10a'),
    ('YOSEMITE', '5.10b'),
    ('YOSEMITE', '5.10c'),
    ('YOSEMITE', '5.10d'),
    ('YOSEMITE', '5.11a'),
    ('YOSEMITE', '5.11b'),
    ('YOSEMITE', '5.11c'),
    ('YOSEMITE', '5.11d'),
    ('YOSEMITE', '5.12a'),
    ('YOSEMITE', '5.12b'),
    ('YOSEMITE', '5.12c'),
    ('YOSEMITE', '5.12d');

insert into gym (gym_name, city, state)
	values
	('Test 1 name', 'test 1 city', 'AA'),
	('Test 2 name', 'test 2 city', 'BB'),
	('Test 3 name', 'test 3 city', 'CC');
	
insert into route (gym_id, route_grade_id, route_type, attempts)
	values
	(1, 1, 'BOULDERING', 1),
	(2, 2, 'BOULDERING', 2),
	(3, 23, 'TOP_ROPE', 3);
	
insert into climber (climber_name, climber_age, length_of_time_climbing)
	values
	('Kayti', 24, 5);
	
insert into climber_route (climber_id, route_id)
	values
	(1, 1),
	(1, 2),
	(1, 3);