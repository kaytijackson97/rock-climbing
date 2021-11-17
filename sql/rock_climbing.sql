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
	('Bouldering', 'VIntro'),
    ('Bouldering', 'V0'),
    ('Bouldering', 'V1'),
    ('Bouldering', 'V2'),
    ('Bouldering', 'V3'),
    ('Bouldering', 'V4'),
    ('Bouldering', 'V5'),
    ('Bouldering', 'V6'),
    ('Bouldering', 'V7'),
	('Bouldering', 'V8'),
    ('Bouldering', 'V9'),
    ('Bouldering', 'V10'),
    ('Bouldering', 'V11'),
    ('Bouldering', 'V12'),
    ('Yosemite', '5.Intro'),
    ('Yosemite', '5.4'),
    ('Yosemite', '5.5'),
    ('Yosemite', '5.6'),
    ('Yosemite', '5.7'),
    ('Yosemite', '5.8'),
    ('Yosemite', '5.9'),
    ('Yosemite', '5.10a'),
    ('Yosemite', '5.10b'),
    ('Yosemite', '5.10c'),
    ('Yosemite', '5.10d'),
    ('Yosemite', '5.11a'),
    ('Yosemite', '5.11b'),
    ('Yosemite', '5.11c'),
    ('Yosemite', '5.11d'),
    ('Yosemite', '5.12a'),
    ('Yosemite', '5.12b'),
    ('Yosemite', '5.12c'),
    ('Yosemite', '5.12d');
    