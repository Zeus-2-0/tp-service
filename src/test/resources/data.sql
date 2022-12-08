-- Authorities

-- Authority Authorities
insert into authority(authority_id, permission, created_date, updated_date) values ('67cdcff0-f180-421a-bd5f-69cbc62f3cfe', 'authority.create', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('f06cc9be-0c37-4dac-8d36-941e7dd03e95', 'authority.read', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('1fba12c8-da0d-4d15-be62-5cdcc0d40240', 'authority.update', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('4a12573c-288f-4218-9a1f-8effcd2b8637', 'authority.delete', sysdate(), sysdate());

-- Role Authorities
insert into authority(authority_id, permission, created_date, updated_date) values ('866d7126-8281-42c6-a02d-3087b0bb1cab', 'role.create', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('14e8773c-6649-4c21-9c3c-39a94017ee15', 'role.read', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('4cf0bfda-d703-4ce0-a3ab-298d5d0fd5be', 'role.update', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('baf01a6a-de78-4c2d-8ee8-28401cb5225b', 'role.delete', sysdate(), sysdate());

-- User Authorities
insert into authority(authority_id, permission, created_date, updated_date) values ('598ea376-0c17-474a-a0a7-cd26ce2c5754', 'user.create', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('9be33d52-605e-4d29-92e2-3209c1b8b79d', 'user.read', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('a0f3679a-ef3d-4175-a792-bc2376e11d08', 'user.update', sysdate(), sysdate());
insert into authority(authority_id, permission, created_date, updated_date) values ('dd14ff93-294a-469e-86e0-97289df45bb6', 'user.delete', sysdate(), sysdate());

-- Trading Partner Authorities
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('b995d822-03ba-46a1-a6cf-1d75dd51e57b', 'tp.create', sysdate(), sysdate());
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('21c69c34-3939-41af-9797-33962a701f00', 'tp.read', sysdate(), sysdate());
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('2bb12b6c-e399-4b54-85a4-383048399ce2', 'tp.update', sysdate(), sysdate());
INSERT INTO AUTHORITY(authority_id, permission, created_date, updated_date) VALUES ('e7ad0679-aace-4def-8898-26cd30147128', 'tp.delete', sysdate(), sysdate());


-- Roles

INSERT INTO ROLE(role_id, role_name, created_date, updated_date) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'ADMIN', sysdate(), sysdate());
INSERT INTO ROLE(role_id, role_name, created_date, updated_date) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', 'ENROLLMENT_SPECIALIST', sysdate(), sysdate());
INSERT INTO ROLE(role_id, role_name, created_date, updated_date) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'ENROLLMENT_MANAGER', sysdate(), sysdate());
INSERT INTO ROLE(role_id, role_name, created_date, updated_date) VALUES ('c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8', 'SERVICE', sysdate(), sysdate());

-- Role Authority Relationship

-- Admin Role
-- Trading Partner entity

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'b995d822-03ba-46a1-a6cf-1d75dd51e57b');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '2bb12b6c-e399-4b54-85a4-383048399ce2');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'e7ad0679-aace-4def-8898-26cd30147128');

-- Authority Entity
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '67cdcff0-f180-421a-bd5f-69cbc62f3cfe');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'f06cc9be-0c37-4dac-8d36-941e7dd03e95');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '1fba12c8-da0d-4d15-be62-5cdcc0d40240');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '4a12573c-288f-4218-9a1f-8effcd2b8637');

-- Role Entity
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '866d7126-8281-42c6-a02d-3087b0bb1cab');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '14e8773c-6649-4c21-9c3c-39a94017ee15');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '4cf0bfda-d703-4ce0-a3ab-298d5d0fd5be');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'baf01a6a-de78-4c2d-8ee8-28401cb5225b');

-- User Entity
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '598ea376-0c17-474a-a0a7-cd26ce2c5754');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', '9be33d52-605e-4d29-92e2-3209c1b8b79d');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'a0f3679a-ef3d-4175-a792-bc2376e11d08');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('100ad35e-04ee-4da5-952c-e840b7a8d1ea', 'dd14ff93-294a-469e-86e0-97289df45bb6');

-- Enrollment Manager Role

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'b995d822-03ba-46a1-a6cf-1d75dd51e57b');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', '2bb12b6c-e399-4b54-85a4-383048399ce2');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('fbf390fc-99b7-40ad-b368-20523dda8e12', 'e7ad0679-aace-4def-8898-26cd30147128');

-- Enrollment Specialist Role

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', '21c69c34-3939-41af-9797-33962a701f00');
INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('0bbf5895-9084-4b8d-860c-c722d649ff66', '2bb12b6c-e399-4b54-85a4-383048399ce2');

-- Service Role

INSERT INTO ROLE_AUTHORITY(role_id, authority_id) VALUES ('c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8', '21c69c34-3939-41af-9797-33962a701f00');


-- Users

INSERT INTO SECURITY_USER(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('49d18e1a-1018-4961-a261-2873ffe5bd02', 'ref-data', '{bcrypt}$2a$10$jkkZzebnoP4E48PHrLm1VeXfUCsCzEJcIkHp.MSR8.38Ou4O80prS', true, true, true, true, sysdate(), sysdate());
INSERT INTO SECURITY_USER(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('3375d2ab-5b0f-4da2-a26b-abf7354086f4', 'john', '{bcrypt}$2a$10$RILDxbGK4jPwVu4LLtAzJ.inZyTZIIif0L8JdFAZo7BscxhtQCEgu', true, true, true, true, sysdate(), sysdate());
INSERT INTO SECURITY_USER(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled, created_date, updated_date) VALUES ('ac5f043b-67b0-4878-8819-5d47ed8dad29', 'mary', '{bcrypt}$2a$10$A//IPwppA4uxB9ElZk/R.OqSy9xs5RPwBWCyTjtiqVcUlPv/2e6E6', true, true, true, true, sysdate(), sysdate());
INSERT INTO SECURITY_USER(user_id, username, password, account_not_expired, account_not_locked, credentials_not_expired, enabled,created_date, updated_date) VALUES ('cabcab65-a744-4e09-a727-ba686d20127c', 'cindy', '{bcrypt}$2a$10$JhPtDZiWLU.G3YX.oAhz2uXB0PYBhzlJ7q6QTf2a1ZhU83SheFtq.', true, true, true, true, sysdate(), sysdate());

-- User Role Relationship

INSERT INTO USER_ROLE(user_id, role_id) VALUES ('49d18e1a-1018-4961-a261-2873ffe5bd02', 'c4ab9dca-f85e-4c88-8afc-5b81fa17a6d8');
INSERT INTO USER_ROLE(user_id, role_id) VALUES ('3375d2ab-5b0f-4da2-a26b-abf7354086f4', '100ad35e-04ee-4da5-952c-e840b7a8d1ea');
INSERT INTO USER_ROLE(user_id, role_id) VALUES ('ac5f043b-67b0-4878-8819-5d47ed8dad29', '0bbf5895-9084-4b8d-860c-c722d649ff66');
INSERT INTO USER_ROLE(user_id, role_id) VALUES ('cabcab65-a744-4e09-a727-ba686d20127c', 'fbf390fc-99b7-40ad-b368-20523dda8e12');

-- Trading Partner

INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('15cc9dbb-73e8-473e-9f6b-e772a781e6dc','060641618A', 'FFM AR TP', 'Arkansas FFM Trading Partner', 'CMSFFM', '060641618A', 'HIX', 'MP_AR', 'AR', 'FFM', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('de452af8-b789-47c5-b517-39cbe2ae083c','TP011643', 'SBE AR TP', 'Arkansas SBE Trading Partner', '716007869', 'TP011643', 'HIX', 'MP_AR', 'AR', 'SBE', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('0375a5fa-db10-4704-97b8-1cebe43c2c9a','363097810', 'FFM AZ TP', 'Arizona FFM Trading Partner', 'CMSFFM', '363097810', 'HIX', 'MP_AZ', 'AZ', 'FFM', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('a85b80c4-e7c8-4250-aab1-30aa9e532af0','91450', 'OFFX AZ TP', 'Arizona OFFX Trading Partner', 'AZOFFX', '91450', 'HIX', 'MP_AZ', 'AZ', 'OFFX', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('2b937843-e6d3-4d1d-809c-148b95beda93','954402957052', 'SBE CA TP', 'California SBE Trading Partner', 'CA0', '954402957052', 'HIX', 'MP_CA', 'CA', 'SBE', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('152b4196-1cb1-433a-b3ff-ea4d567a1788','954402957063', 'SBE CA TP', 'California SBE Trading Partner', 'CA0', '954402957063', 'HIX', 'MP_CA', 'CA', 'SBE', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('5c985138-8921-4661-8992-05d9d4ab0ca5','730654885040', 'SBE CA TP', 'California SBE Trading Partner', 'CA0', '730654885040', 'HIX', 'MP_CA', 'CA', 'SBE', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('81c1bbcc-1ffc-4554-ae56-50f721aed469','730654885034', 'SBE CA TP', 'California SBE Trading Partner', 'CA0', '730654885034', 'HIX', 'MP_CA', 'CA', 'SBE', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('fd981496-6db2-497d-8944-a780b758a00c','67138052', 'OFFX CA TP', 'California OFFX Trading Partner', 'CAOFFX', '67138052', 'HIX', 'MP_CA', 'CA', 'OFFX', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('96f76431-49ea-41e6-bac9-b08dca355b57','67138063', 'OFFX CA TP', 'California OFFX Trading Partner', 'CAOFFX', '67138063', 'HIX', 'MP_CA', 'CA', 'OFFX', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('e2c4ff60-48ec-4105-abe2-0b6852e60ec2','99110040', 'OFFX CA TP', 'California OFFX Trading Partner', 'CAOFFX', '99110040', 'HIX', 'MP_CA', 'CA', 'OFFX', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('f3dee461-7e07-4bfc-839b-68ad813d0ef2','99110034', 'OFFX CA TP', 'California OFFX Trading Partner', 'CAOFFX', '99110034', 'HIX', 'MP_CA', 'CA', 'OFFX', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('6a7bd316-ca78-4c9e-b466-d27d31c04db9','21663', 'FFM FL TP', 'Florida FFM Trading Partner', 'CMSFFM', '21663', 'HIX', 'MP_FL', 'FL', 'FFM', sysdate(), sysdate());
INSERT INTO trading_partner_detail(trading_partner_sk, trading_partner_id, name, description, sender_id, receiver_id, line_of_business_type_code, business_unit_type_code, state_type_code, marketplace_type_code, created_date, updated_date) VALUES ('abb5a90e-57e6-4520-98c3-73057bd29a05','70893', 'FFM GA TP', 'Georgia FFM Trading Partner', 'CMSFFM', '70893', 'HIX', 'MP_GA', 'GA', 'FFM', sysdate(), sysdate());