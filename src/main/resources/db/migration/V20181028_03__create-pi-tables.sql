CREATE TABLE principalinvestigators(principal_investigator_id bigserial PRIMARY KEY
    , title					varchar(64)
    , initials    			varchar(32)
    , first_name			varchar(32)
    , middle_name			varchar(32)
    , last_name				varchar(64)	NOT NULL
    , gender				varchar(1)
    , room_number			varchar(32)
    , email					varchar(64)
    , function				varchar(32)
    , phone_number			varchar(10)
    , address				varchar(64)
    , UNIQUE (initials, last_name)
) INHERITS(created, rownumber);
COMMENT ON TABLE principalinvestigators IS 'All Principal Investigators';

