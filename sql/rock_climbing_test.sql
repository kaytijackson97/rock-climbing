drop database if exists rock_climbing_test;
create database rock_climbing_test;
use rock_climbing_test;

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

delimiter //
create procedure set_known_good_state()
begin
	set sql_safe_updates = 0;
    delete from climber_gym;
    delete from route;
    alter table route auto_increment = 1;
    delete from climber;
    alter table climber auto_increment = 1;
    delete from gym;
    alter table gym auto_increment = 1;
    delete from route_grade;
    alter table route_grade auto_increment = 1;
    set sql_safe_updates = 1;

	-- password is set to "P@ssw0rd!"
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
		
	insert into gym (gym_name, city, state)
		values
		('Test 1 name', 'test 1 city', 'AA'),
		('Test 2 name', 'test 2 city', 'BB'),
		('Test 3 name', 'test 3 city', 'CC');
		
	insert into route (gym_id, route_grade_id, route_type, attempts)
		values
		(1, 1, 'Bouldering', 1),
		(2, 2, 'Bouldering', 2),
		(3, 3, 'Yosemite', 3);
		
	insert into climber (climber_name, climber_age, length_of_time_climbing)
		values
		('Joe', 24, 4),
		('John', 30, 1),
		('Jane', 32, 10);
		
	insert into climber_gym (climber_id, gym_id)
		values
		(1, 1),
		(2, 2),
		(3, 3);
    
end //
delimiter ;