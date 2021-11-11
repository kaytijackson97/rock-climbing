drop database if exists rock_climbing;
create database rock_climbing;
use rock_climbing;

create table grading_system (
	grading_id int primary key auto_increment,
    grade_name varchar(15) not null
);

create table route_grade (
	route_grade_id int primary key auto_increment,
    grading_id int not null,
	route_grade varchar(7) not null,
    constraint fk_route_grade_grading
		foreign key (grading_id)
        references grading_system(grading_id)
);

create table route (
	route_id int primary key auto_increment,
	route_grade_id int not null,
	route_type varchar(15) not null,
    attempts int not null,
    set_date date,
	constraint fk_route_route_grade_id
		foreign key (route_grade_id)
        references route_grade(route_grade_id)
);

create table gym (
	gym_id int primary key auto_increment,
    gym_name varchar(25) not null,
    city varchar(30) not null,
    state varchar(2) not null
);

create table climber (
	climber_id int primary key auto_increment,
    route_id int,
    gym_id int,
    climber_name varchar(25) not null,
    climber_age int not null,
    length_of_time_climbing int not null,
    hardest_climb varchar(5),
    constraint fk_climber_route_id
		foreign key (route_id)
        references route(route_id),
	constraint fk_climber_gym_id
		foreign key (gym_id)
        references gym(gym_id)
);

-- data
insert into grading_system (grade_name)
	values
	('Bouldering'),
    ('Yosemite');
    
insert into route_grade (grading_id, route_grade)
	values
	(1, 'VIntro'),
    (1, 'V0'),
    (1, 'V1'),
    (1, 'V2'),
    (1, 'V3'),
    (1, 'V4'),
    (1, 'V5'),
    (1, 'V6'),
    (1, 'V7'),
	(1, 'V8'),
    (1, 'V9'),
    (1, 'V10'),
    (1, 'V11'),
    (1, 'V12'),
    (2, '5.Intro'),
    (2, '5.4'),
    (2, '5.5'),
    (2, '5.6'),
    (2, '5.7'),
    (2, '5.8'),
    (2, '5.9'),
    (2, '5.10a'),
    (2, '5.10b'),
    (2, '5.10c'),
    (2, '5.10d'),
    (2, '5.11a'),
    (2, '5.11b'),
    (2, '5.11c'),
    (2, '5.11d'),
    (2, '5.12a'),
    (2, '5.12b'),
    (2, '5.12c'),
    (2, '5.12d');
    