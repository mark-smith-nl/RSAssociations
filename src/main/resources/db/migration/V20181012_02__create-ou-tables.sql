CREATE TABLE ou_divisions(id bigserial PRIMARY KEY
    , name        varchar(64) NOT NULL UNIQUE
) INHERITS(created);

CREATE TABLE ou_departments(id bigserial PRIMARY KEY
    , name        varchar(64) NOT NULL
    , division_id integer     NOT NULL REFERENCES ou_divisions(id)
    , UNIQUE(name, division_id)
) INHERITS(created);

CREATE TABLE ou_subdepartments(id bigserial PRIMARY KEY
    , name          varchar(64) NOT NULL
    , department_id integer     NOT NULL REFERENCES ou_departments(id)
    , UNIQUE(name, department_id)
) INHERITS(created);

