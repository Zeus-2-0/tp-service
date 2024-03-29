-- Authorities

-- Authority Authorities
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('67cdcff0-f180-421a-bd5f-69cbc62f3cfe', 'authority.create', sysdate(), sysdate());
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('f06cc9be-0c37-4dac-8d36-941e7dd03e95', 'authority.read', sysdate(), sysdate());
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('1fba12c8-da0d-4d15-be62-5cdcc0d40240', 'authority.update', sysdate(), sysdate());
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('4a12573c-288f-4218-9a1f-8effcd2b8637', 'authority.delete', sysdate(), sysdate());

-- Role Authorities
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('866d7126-8281-42c6-a02d-3087b0bb1cab', 'role.create', sysdate(), sysdate());
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('14e8773c-6649-4c21-9c3c-39a94017ee15', 'role.read', sysdate(), sysdate());
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('4cf0bfda-d703-4ce0-a3ab-298d5d0fd5be', 'role.update', sysdate(), sysdate());
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('baf01a6a-de78-4c2d-8ee8-28401cb5225b', 'role.delete', sysdate(), sysdate());

-- User Authorities
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('598ea376-0c17-474a-a0a7-cd26ce2c5754', 'user.create', sysdate(), sysdate());
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('9be33d52-605e-4d29-92e2-3209c1b8b79d', 'user.read', sysdate(), sysdate());
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('a0f3679a-ef3d-4175-a792-bc2376e11d08', 'user.update', sysdate(), sysdate());
insert into tpdb.authority(authority_id, permission, created_date, updated_date) values ('dd14ff93-294a-469e-86e0-97289df45bb6', 'user.delete', sysdate(), sysdate());

-- Trading Partner Authorities
INSERT INTO tpdb.authority(authority_id, permission, created_date, updated_date) VALUES ('b995d822-03ba-46a1-a6cf-1d75dd51e57b', 'tp.create', sysdate(), sysdate());
INSERT INTO tpdb.authority(authority_id, permission, created_date, updated_date) VALUES ('21c69c34-3939-41af-9797-33962a701f00', 'tp.read', sysdate(), sysdate());
INSERT INTO tpdb.authority(authority_id, permission, created_date, updated_date) VALUES ('2bb12b6c-e399-4b54-85a4-383048399ce2', 'tp.update', sysdate(), sysdate());
INSERT INTO tpdb.authority(authority_id, permission, created_date, updated_date) VALUES ('e7ad0679-aace-4def-8898-26cd30147128', 'tp.delete', sysdate(), sysdate());


-- Roles

INSERT INTO tpdb.role(role_id, role_name, created_date, updated_date) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'ADMIN', sysdate(), sysdate());
INSERT INTO tpdb.role(role_id, role_name, created_date, updated_date) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', 'ENROLLMENT_SPECIALIST', sysdate(), sysdate());
INSERT INTO tpdb.role(role_id, role_name, created_date, updated_date) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'ENROLLMENT_MANAGER', sysdate(), sysdate());
INSERT INTO tpdb.role(role_id, role_name, created_date, updated_date) VALUES ('c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8', 'SERVICE', sysdate(), sysdate());

-- Role Authority Relationship

-- Admin Role
-- Trading Partner entity

INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'b995d822-03ba-46a1-a6cf-1d75dd51e57b');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '2bb12b6c-e399-4b54-85a4-383048399ce2');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'e7ad0679-aace-4def-8898-26cd30147128');

-- Authority Entity
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '67cdcff0-f180-421a-bd5f-69cbc62f3cfe');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'f06cc9be-0c37-4dac-8d36-941e7dd03e95');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '1fba12c8-da0d-4d15-be62-5cdcc0d40240');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '4a12573c-288f-4218-9a1f-8effcd2b8637');

-- Role Entity
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '866d7126-8281-42c6-a02d-3087b0bb1cab');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '14e8773c-6649-4c21-9c3c-39a94017ee15');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '4cf0bfda-d703-4ce0-a3ab-298d5d0fd5be');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'baf01a6a-de78-4c2d-8ee8-28401cb5225b');

-- User Entity
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '598ea376-0c17-474a-a0a7-cd26ce2c5754');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '9be33d52-605e-4d29-92e2-3209c1b8b79d');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'a0f3679a-ef3d-4175-a792-bc2376e11d08');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'dd14ff93-294a-469e-86e0-97289df45bb6');

-- Enrollment Manager Role

INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'b995d822-03ba-46a1-a6cf-1d75dd51e57b');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', '2bb12b6c-e399-4b54-85a4-383048399ce2');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'e7ad0679-aace-4def-8898-26cd30147128');

-- Enrollment Specialist Role

INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', '2bb12b6c-e399-4b54-85a4-383048399ce2');

-- Service Role

INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8', 'b995d822-03ba-46a1-a6cf-1d75dd51e57b');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8', '2bb12b6c-e399-4b54-85a4-383048399ce2');
INSERT INTO tpdb.role_authority(role_id, authority_id) VALUES ('c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8', 'e7ad0679-aace-4def-8898-26cd30147128');


-- Users

INSERT INTO tpdb.security_user(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('49d18e1a-1018-4961-a261-2873ffe5bd02', 'ref-data', '$2a$10$EOvrNhwKZJB1acbdT5BFG.L12vFCwMtTcMrj1s.dUD16eoM3HR.7q', true, true, true, true, sysdate(), sysdate());
INSERT INTO tpdb.security_user(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('3375d2ab-5b0f-4da2-a26b-abf7354086f4', 'john', '$2a$10$1Y.65GeDjNUbAzZtgUUWAOrXdZxZhRVr3NNv79aMvvsLkh5KqGmri', true, true, true, true, sysdate(), sysdate());
INSERT INTO tpdb.security_user(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('ac5f043b-67b0-4878-8819-5d47ed8dad29', 'mary', '$2a$10$dDUUKmdYDrdGG9imkwqcCOEV0UOxHO06h970a2Jua5YnW9rRjfUO2', true, true, true, true, sysdate(), sysdate());
INSERT INTO tpdb.security_user(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled,created_date, updated_date) VALUES ('cabcab65-a744-4e09-a727-ba686d20127c', 'cindy', '$2a$10$OTvFt8JIYasoFwtcgyqZuubH7ZKG/hsMk9x9u3YBwgUbaDRd3GZnS', true, true, true, true, sysdate(), sysdate());
INSERT INTO tpdb.security_user(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled,created_date, updated_date) VALUES ('bb6286a4-ddf6-4731-b285-cbf532c1d316', 'api-gateway', '$2a$10$0OZLWG7VNJnBxWf913vHdOVH/T2EN3B5B23kwbZ9.nQ6YhtJjBUei', true, true, true, true, sysdate(), sysdate());

-- User Role Relationship

INSERT INTO tpdb.user_role(user_id, role_id) VALUES ('49d18e1a-1018-4961-a261-2873ffe5bd02', 'c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8');
INSERT INTO tpdb.user_role(user_id, role_id) VALUES ('3375d2ab-5b0f-4da2-a26b-abf7354086f4', '100ad35e-04ee-4da5-952c-e840b7a8d1ea');
INSERT INTO tpdb.user_role(user_id, role_id) VALUES ('ac5f043b-67b0-4878-8819-5d47ed8dad29', '0bbf5895-9084-4b8d-860c-c722d649ff66');
INSERT INTO tpdb.user_role(user_id, role_id) VALUES ('cabcab65-a744-4e09-a727-ba686d20127c', 'fbf390fc-99b7-40ad-b368-20523dda8e12');
INSERT INTO tpdb.user_role(user_id, role_id) VALUES ('bb6286a4-ddf6-4731-b285-cbf532c1d316', 'c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8');