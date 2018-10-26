CREATE TABLE IF NOT EXISTS persons(person_id bigserial PRIMARY KEY
    , last_name        varchar(64) NOT NULL
    , first_name       varchar(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS belongings(
	person_id integer NOT NULL REFERENCES persons(person_id) ON DELETE CASCADE
    , name        varchar(64) NOT NULL
    , UNIQUE(person_id, name)
);

CREATE TABLE IF NOT EXISTS mariages(
	husband_id integer NOT NULL REFERENCES persons(person_id)
 	, wife_id integer NOT NULL REFERENCES persons(person_id)
    , UNIQUE(husband_id, wife_id)
);