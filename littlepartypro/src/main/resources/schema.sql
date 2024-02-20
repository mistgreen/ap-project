DROP TABLE IF EXISTS Tier CASCADE;
CREATE TABLE IF NOT EXISTS Tier(
    id SERIAL PRIMARY KEY,
    tier_name varchar(50) NOT NULL,
    tier_description varchar(250)
);
INSERT INTO Tier(tier_name, tier_description)
VALUES
    ('FREE', 'Basic account');

DROP TABLE IF EXISTS Vendor_Type CASCADE;
CREATE TABLE IF NOT EXISTS Vendor_Type(
    id SERIAL PRIMARY KEY,
    vendor_type_name varchar(50) NOT NULL,
    vendor_type_description varchar(250)
);
INSERT INTO Vendor_Type (vendor_type_name, vendor_type_description)
VALUES
    ('VENUE', ''),
    ('CATERER', ''),
    ('DECORATOR', ''),
    ('MISC', '');

DROP TABLE IF EXISTS Vendors CASCADE;

CREATE TABLE IF NOT EXISTS Vendors(
    id SERIAL PRIMARY KEY,
    title varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    postcode varchar(20) NOT NULL,
    short_desc varchar(50),
    long_desc varchar(255),
    tier_id INTEGER NOT NULL,
    vendor_type_id INTEGER NOT NULL,
    FOREIGN KEY (tier_id) REFERENCES Tier(id),
    FOREIGN KEY (vendor_type_id) REFERENCES Vendor_Type(id),
    url varchar(255)
);

INSERT INTO Vendors (title, address, postcode, short_desc, long_desc, tier_id, vendor_type_id, url)
VALUES
    ('Jolly Giraffes', 'Rochdale', 'OL11 5BX', 'Packaged Party', 'The best soft play centre in Rochdale! We have a 3 tier main frame and a toddler area.', '1', '1', 'https://jollygiraffesltd.com'),
    ('Bounce Central', 'Salford', 'M27 8SE', 'Packaged Party', '', '1', '1', 'https://www.bouncecentraluk.com'),
    ('Chill Factore', 'Manchester', 'M41 7JA', 'Packaged Party', '', '1', '1', 'https://www.chillfactore.com/parties'),
    ('Smithills Open Farm', 'Bolton', 'BL1 7NS', 'Packaged Party', '', '1', '1', 'https://www.smithillsopenfarm.co.uk/groups-parties'),
    ('Lovely Little Lender', 'Heywood', 'OL10', 'Luxury Venue Dressing', '', '1', '3', 'https://www.facebook.com/lovelylittlelender'),
    ('Inflatanation', 'Manchester', 'M17 1WA', 'Packaged Party', '', '1', '1', 'https://www.inflatanation.com/kids-parties'),
    ('Bensons Catering', 'Manchester', 'SK6 5HN', 'Buffet Catering', '', '1', '2', 'https://www.benonscatering.co.uk'),
    ('Scandi Soft Play', 'Bury', 'M', 'Soft Play Hire', '', '1', '4', 'https://www.scandisoftplay.co.uk'),
    ('BouncyBen', 'Manchester', 'M', 'Childrens Entertainer', '', '1', '4', 'https://www.bouncyben.co.uk'),
    ('Princess Impersonations', 'Manchester', 'M', 'Childrens Entertainer', '', '1', '4', 'https://www.princessimpersonations.co.uk');



DROP TABLE IF EXISTS Users CASCADE;
CREATE TABLE IF NOT EXISTS Users ( id SERIAL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    role_id INTEGER NOT NULL DEFAULT 1
);

ALTER TABLE Users ALTER COLUMN role_id SET DEFAULT 1;


DROP TABLE IF EXISTS Roles CASCADE;
CREATE TABLE IF NOT EXISTS Roles (
    id INTEGER PRIMARY KEY,
    role_name VARCHAR NOT NULL
    );
INSERT INTO Roles (id, role_name)
VALUES
    (1, 'ADMIN'),
    (0, 'USER');

DROP TABLE IF EXISTS User_Roles CASCADE;

