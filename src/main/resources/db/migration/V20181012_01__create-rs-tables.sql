CREATE TABLE created(created TIMESTAMP NOT NULL DEFAULT NOW()
    , modified TIMESTAMP NOT NULL DEFAULT NOW()
);


COMMENT ON TABLE created IS 'This table is inherited by tables in which creation and modification of records are being logged';

CREATE TABLE rownumber(row_number integer);
);
COMMENT ON TABLE rownumber IS 'This table is inherited by tables in which records have been imported from a spreadsheet';

CREATE TABLE rsemployees(rs_employee_id bigserial PRIMARY KEY
    , full_name       varchar(64) NOT NULL UNIQUE
    , phone_number    varchar(5)  NOT NULL
    , email           varchar(64) NOT NULL
    , secondary_email varchar(64)
) INHERITS(created, rownumber);
COMMENT ON TABLE rsemployees IS 'All Research Support employees';

CREATE TABLE function_project_controller(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_project_administrateur(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_jurist_mta_cda(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_jurist_ip_eu(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_jurist_klinisch(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_hr_adviseur(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_hr_medewerker(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_business_developer(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_octrooi_gemachtigde(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_consortium_controller(rs_employee_id integer NOT NULL REFERENCES rsemployees(rs_employee_id) ON DELETE CASCADE UNIQUE) INHERITS(created);

CREATE TABLE project_controller_administrator_link(
	controller_id integer     NOT NULL REFERENCES function_project_controller(rs_employee_id)   ON DELETE CASCADE
	, administrator integer NOT NULL REFERENCES function_project_administrateur(rs_employee_id) ON DELETE CASCADE
	, UNIQUE (controller_id, administrator)
) INHERITS(created, rownumber);

CREATE TABLE hr_advisor_employee_link(
	advisor_id integer     NOT NULL REFERENCES function_hr_adviseur(rs_employee_id)   ON DELETE CASCADE
	, employee_id integer NOT NULL REFERENCES function_hr_medewerker(rs_employee_id) ON DELETE CASCADE
	, UNIQUE (advisor_id, employee_id)
) INHERITS(created, rownumber);



