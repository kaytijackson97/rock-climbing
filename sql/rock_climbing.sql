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

create table climber_gym (
	climber_id int,
    gym_id int,
    constraint pk_climber_gym
		primary key (climber_id, gym_id),
	constraint fk_climber_gym_climber_id
		foreign key (climber_id)
        references climber(climber_id),
	constraint fk_climber_gym_gym_id
		foreign key (gym_id)
        references gym(gym_id)
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
