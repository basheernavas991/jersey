--1st December 2018
--Saneesh
--Creating schema settings:
create schema settings;

--1st December 2018
--Saneesh
--Creating table settings.lookup:
create table settings.lookup(
id bigserial primary key not null,
reference text not null,
name text not null,
description text); 
		
--1st December 2018
--Saneesh
--Creating table settings.lookup_values:
CREATE TABLE settings.lookup_values
(
  id bigserial primary key NOT NULL,
  lookup_id bigint references settings.lookup(id) NOT NULL,
  code text NOT NULL,
  name text NOT NULL,
  context text,
  reference text,
  description text,
  system_value boolean NOT NULL,
  boolean1 boolean DEFAULT false,
  text1 character varying(25),
  integer1 integer,
  integer2 integer);
  	
--1st December 2018
--Saneesh
--Creating schema security:
create schema security;

--1st December 2018
--Saneesh
--Creating table security.users:
CREATE TABLE security.users
(id serial primary key NOT NULL ,
  username text NOT NULL,
  password text NOT NULL,
  first_name text,
  last_name text,
  email text,
  user_privacy_accepted boolean NOT NULL,
  last_accessed_date timestamp without time zone,
  enabled boolean,
  token_expired boolean
  );

--1st December 2018
--Saneesh
--Creating table settings.persons:
create table settings.persons
(id serial primary key NOT NULL,
  reference text NOT NULL,
  type bigint references settings.lookup(id) NOT NULL,
  title bigint references settings.lookup(id) ,
  first_name text NOT NULL,
  last_name text NOT NULL,
  gender bigint references settings.lookup(id) ,
  dob timestamp without time zone,
  status bigint references settings.lookup(id)  NOT NULL,
  second_name text,
  third_name text,
  notes text,
  created_by integer references security.users(id) NOT NULL,
  created_date timestamp with time zone NOT NULL,
  last_modified_by integer references security.users(id) NOT NULL,
  last_modified_date timestamp with time zone NOT NULL,
  version integer NOT NULL);

 --1st December 2018
--Saneesh
--Adding columns to table security.users: 
alter table security.users
add column created_by integer references security.users(id) ,
add column created_date timestamp with time zone,
add column last_modified_by integer references security.users(id) ,
add column last_modified_date timestamp with time zone,
add column version integer,
add column person_id integer references settings.persons(id);

--1st December 2018
--Saneesh
--Creating table security.roles:
CREATE TABLE security.roles
(
  id serial primary key NOT NULL,
  name text NOT NULL,
  description text,
  created_by integer references security.users(id),
  created_date timestamp with time zone,
  last_modified_by integer references security.users(id),
  last_modified_date timestamp with time zone,
  version integer);

--1st December 2018
--Saneesh
--Creating table security.user_roles:  
CREATE TABLE security.user_roles
(
  id serial primary key NOT NULL,
  user_id integer references security.users(id) NOT NULL,
  role_id integer references security.roles(id) NOT NULL);

--1st December 2018
--Saneesh
--Creating table security.permissions:    
CREATE TABLE security.permissions
(
  id serial primary key NOT NULL,
  name text NOT NULL,
  readable_name text,
  description text); 

--1st December 2018
--Saneesh
--Creating table security.role_permissions:  
CREATE TABLE security.role_permissions
(
  id serial primary key NOT NULL,
  role_id integer references security.roles(id) NOT NULL,
  permission_id integer references security.roles(id) NOT NULL); 
  
--1st December 2018
--Saneesh
--Creating table security.permission_contexts:    
CREATE TABLE security.permission_contexts
(
  id integer primary key NOT NULL,
  name text NOT NULL,
  description text);  
  
--1st December 2018
--Saneesh
--Creating table settings.versions:  
CREATE TABLE settings.versions
(
  id serial primary key NOT NULL,
  version text NOT NULL,
  date timestamp without time zone NOT NULL,
  deployer text NOT NULL,
  active boolean NOT NULL);

--1st December 2018
--Saneesh
--Creating table settings.person_address:   
CREATE TABLE settings.person_address
(
  id serial primary key NOT NULL,
  person_id integer references settings.persons(id) NOT NULL,
  type bigint references settings.lookup(id) NOT NULL,
  address text,
  country bigint references settings.lookup(id)  ,
  post_code text,
  notes text,
  active boolean NOT NULL,
  created_by integer references security.users(id)  NOT NULL,
  created_date timestamp with time zone NOT NULL,
  last_modified_by integer references security.users(id) NOT NULL,
  last_modified_date timestamp with time zone NOT NULL,
  version integer NOT NULL);
  
--1st December 2018
--Saneesh
--Creating table settings.person_telephone:  
CREATE TABLE settings.person_telephone
(
  id serial primary key NOT NULL,
  person_id integer references settings.persons(id)  NOT NULL,
  type bigint references settings.lookup(id) NOT NULL,
  country bigint references settings.lookup(id),
  notes text,
  active boolean NOT NULL,
  created_by integer references security.users(id) NOT NULL,
  created_date timestamp with time zone NOT NULL,
  last_modified_by integer references security.users(id) NOT NULL,
  last_modified_date timestamp with time zone NOT NULL,
  version integer NOT NULL,
  telephone_number text);
  
--07th January 2019
--Saneesh
--Creating View user_meta_view:
CREATE VIEW security.user_meta_view as
SELECT u.id as user_id,
u.first_name,
u.last_name,
u.last_accessed_date,
u.enabled,
u.username
from security.users u
left join hr.staffs s on u.person_id = s.person_id
where s.is_individual_user = true;   
  