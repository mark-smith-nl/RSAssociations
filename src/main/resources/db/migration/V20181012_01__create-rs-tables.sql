CREATE TABLE created(created TIMESTAMP NOT NULL DEFAULT NOW()
    , modified TIMESTAMP NOT NULL DEFAULT NOW()
);


COMMENT ON TABLE created IS 'This table is inherited by tables in which creation and modification of records are being logged';

CREATE TABLE rownumber(row_number integer);
);
COMMENT ON TABLE rownumber IS 'This table is inherited by tables in which records have been imported from a spreadsheet';

CREATE TABLE rsemployees(persoon_id integer PRIMARY KEY
	, row_number		integer 	NOT NULL
	, amcgebruikersnaam	text		NOT NULL UNIQUE
	, amcemail			text		NOT NULL UNIQUE
	, voorkeurnaam		text
	, tussenvoegsel		text
	, voorletters		text
	, voornaam			text
	, roepnaam			text
	, geboortedatum		date
	, geslacht			varchar(1)
    , phone_number    	varchar(5)	NOT NULL
    , email           	varchar(64)
) INHERITS(created, rownumber);
COMMENT ON TABLE rsemployees IS 'All Research Support employees';

CREATE TABLE function_project_controller(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_project_administrateur(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_jurist_mta_cda(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_jurist_ip_eu(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_jurist_klinisch(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_hr_adviseur(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_hr_medewerker(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_business_developer(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_octrooi_gemachtigde(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);
CREATE TABLE function_consortium_controller(persoon_id integer NOT NULL REFERENCES rsemployees(persoon_id) ON DELETE CASCADE UNIQUE) INHERITS(created);

CREATE TABLE project_controller_administrator_link(
	controller_id integer     NOT NULL REFERENCES function_project_controller(persoon_id)   ON DELETE CASCADE
	, administrator integer NOT NULL REFERENCES function_project_administrateur(persoon_id) ON DELETE CASCADE
	, UNIQUE (controller_id, administrator)
) INHERITS(created, rownumber);

CREATE TABLE hr_advisor_employee_link(
	advisor_id integer     NOT NULL REFERENCES function_hr_adviseur(persoon_id)   ON DELETE CASCADE
	, employee_id integer NOT NULL REFERENCES function_hr_medewerker(persoon_id) ON DELETE CASCADE
	, UNIQUE (advisor_id, employee_id)
) INHERITS(created, rownumber);



