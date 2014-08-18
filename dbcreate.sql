CREATE TABLE Person (
Person_UID INTEGER NOT NULL AUTO_INCREMENT,
PNC_ID VARCHAR(15),
Gender CHAR NOT NULL CHECK (Gender IN ('M', 'F', 'X', 'U')),
Occupation INTEGER,
Marital_Status CHAR NOT NULL CHECK (Marital_Status IN ('D', 'M', 'N', 'P', 'S', 'W')),
Photograph_URL VARCHAR(255),
PRIMARY KEY (Person_UID)
);

CREATE TABLE Statuses (
Status_UID INTEGER NOT NULL AUTO_INCREMENT,
Description VARCHAR(255) NOT NULL,
PRIMARY KEY (Status_UID)
);

CREATE TABLE Person_Status (
ID INTEGER NOT NULL AUTO_INCREMENT,
Person_UID INTEGER NOT NULL,
Status_UID INTEGER NOT NULL,
Reason VARCHAR(255),
Related_Event_UID INTEGER,
Status_Record_Date DATE,
Status_Expiry_Date DATE,
PRIMARY KEY (ID),
FOREIGN KEY (Person_UID) REFERENCES Person(Person_UID),
FOREIGN KEY (Status_UID) REFERENCES Statuses(Status_UID)
);

CREATE TABLE Identifier (
Identifier_UID INTEGER NOT NULL AUTO_INCREMENT,
Person_UID INTEGER NOT NULL,
Identifier_Type CHAR NOT NULL CHECK (Identifier_Type IN ('C', 'D', 'L', 'N', 'P')),
Identifier VARCHAR(255) NOT NULL,
PRIMARY KEY (Identifier_UID),
FOREIGN KEY (Person_UID) REFERENCES Person(Person_UID)
);

CREATE TABLE Nationality (
Nationality_UID INTEGER NOT NULL,
Nationality VARCHAR(255) NOT NULL,
PRIMARY KEY (Nationality_UID)
);

CREATE TABLE Person_Nationality (
Person_UID INTEGER NOT NULL,
Nationality_UID INTEGER NOT NULL,
FOREIGN KEY (Person_UID) REFERENCES Person(Person_UID),
FOREIGN KEY (Nationality_UID) REFERENCES Nationality(Nationality_UID)
);

CREATE TABLE Location (
Location_UID INTEGER NOT NULL AUTO_INCREMENT,
Person_UID INTEGER NOT NULL,
PO_Box VARCHAR(20),
Extended_Address VARCHAR(255),
Street_Address1 VARCHAR(255),
Street_Address2 VARCHAR(255),
Street_Address3 VARCHAR(255),
Locality VARCHAR(100),
Region VARCHAR(100),
Postal_Code VARCHAR(15),
Country_Name VARCHAR(100),
Notes VARCHAR(255),
Address_Date DATE,
Address_Force_Code VARCHAR(20),
PRIMARY KEY (Location_UID),
FOREIGN KEY (Person_UID) REFERENCES Person(Person_UID)
);

CREATE TABLE Name_Alias (
Name_UID INTEGER NOT NULL AUTO_INCREMENT,
Person_UID INTEGER NOT NULL,
Name_Type CHAR NOT NULL CHECK (Name_Type IN ('P', 'A', 'D')),
Place_of_Birth VARCHAR(255),
Date_of_Birth DATE,
Forename1 VARCHAR(255) NOT NULL,
Forename2 VARCHAR(255),
Forename3 VARCHAR(255),
Forename4 VARCHAR(255),
Surname VARCHAR(255) NOT NULL,
Forename1_Metaphone VARCHAR(255),
Forename2_Metaphone VARCHAR(255),
Forename3_Metaphone VARCHAR(255),
Forename4_Metaphone VARCHAR(255),
Surname_Metaphone VARCHAR(255),
PRIMARY KEY (Name_UID),
FOREIGN KEY (Person_UID) REFERENCES Person(Person_UID)
);

CREATE TABLE Alias_DOB (
Alias_DOB_UID INT NOT NULL AUTO_INCREMENT,
Person_UID INTEGER NOT NULL,
Date_of_Birth DATE NOT NULL,
PRIMARY KEY (Alias_DOB_UID),
FOREIGN KEY (Person_UID) REFERENCES Person(Person_UID)
);

CREATE TABLE Event (
Event_UID INTEGER NOT NULL AUTO_INCREMENT,
Person_UID INTEGER NOT NULL,
Event_Date DATE NOT NULL,
Event_Type VARCHAR(2) NOT NULL CHECK (Event_Type IN ('AS', 'RM', 'CC', 'DD', 'OC', 'CS', 'CA', 'DA', 'CU')),
End_Date DATE,
Force_Code VARCHAR(20) NOT NULL,
Force_Reference VARCHAR(100),
Offence VARCHAR(255),
Power_of_Arrest VARCHAR(255),
Report_Date DATE NOT NULL,
PRIMARY KEY (Event_UID),
FOREIGN KEY (Person_UID) REFERENCES Person(Person_UID)
);

CREATE TABLE Occupation (
Occupation_UID INTEGER NOT NULL,
Occupation VARCHAR(255) NOT NULL,
PRIMARY KEY (Occupation_UID)
);

CREATE TABLE Duplicate_Person (
Person_UID INTEGER NOT NULL,
Related_UID INTEGER NOT NULL,
Relationship VARCHAR(255),
FOREIGN KEY (Person_UID) REFERENCES Person(Person_UID),
FOREIGN KEY (Related_UID) REFERENCES Person(Person_UID)
);

CREATE TABLE GUID (
GUID VARCHAR(255) NOT NULL,
Organisation VARCHAR(255),
Point_of_Contact VARCHAR(255),
Contact_Number VARCHAR(255),
Blocked BOOLEAN default false,
PRIMARY KEY (GUID)
);

-- Statuses import

INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('1', 'Wanted');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('2', 'Missing');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('3', 'High Threat Level');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('4', 'Medium Threat Level');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('5', 'Overstaying a Visa');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('6', 'Disqualified Driver');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('7', 'Impending Prosecution');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('8', 'Currently Convicted');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('9', 'Breach of Visa Conditions');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('10', 'Court Order');
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('11', 'For Information'); 
INSERT INTO `Statuses` (`Status_UID`, `Description`) VALUES ('12', 'Wanted/Missing'); 

-- Nationalities import
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('', 'UNKNOWN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('1', 'UNITED KINGDOM');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('2', 'ENGLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('3', 'NORTHERN IRELAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('4', 'SCOTLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('5', 'WALES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('6', 'CHANNEL ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('7', 'ISLE OF MAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('10', 'AFGHANISTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('12', 'ALBANIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('14', 'ALGERIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('16', 'AMERICAN SAMOA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('18', 'ANDORRA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('20', 'ANGOLA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('22', 'ANGUILLA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('24', 'ANTARCTICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('26', 'ANTIGUA AND BARBUDA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('28', 'ARGENTINA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('30', 'ARMENIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('32', 'ARUBA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('34', 'AUSTRALIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('36', 'AUSTRIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('38', 'AZERBAIJAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('40', 'BAHAMAS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('42', 'BAHRAIN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('44', 'BANGLADESH');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('46', 'BARBADOS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('48', 'BELGIUM');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('50', 'BELIZE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('52', 'BENIN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('54', 'BERMUDA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('56', 'BHUTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('58', 'BOLIVIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('60', 'BOSNIA-HERZEGOVINA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('62', 'BOTSWANA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('64', 'BOUVET ISLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('66', 'BRAZIL');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('68', 'BRITISH INDIAN OCEAN TERRITORY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('70', 'BRUNEI DARUSSALAM');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('72', 'BULGARIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('74', 'BURKINA FASO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('78', 'BURUNDI');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('80', 'BELARUS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('82', 'CAMBODIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('84', 'CAMEROON');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('86', 'CANADA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('88', 'CAPE VERDE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('90', 'CAYMAN ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('92', 'CENTRAL AFRICAN REPUBLIC');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('94', 'CHAD');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('96', 'CHILE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('98', 'CHINA (PEOPLE\'S REP. OF)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('100', 'CHRISTMAS ISLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('102', 'COCOS (KEELING) ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('104', 'COLOMBIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('106', 'COMOROS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('108', 'CONGO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('110', 'COOK ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('112', 'COSTA RICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('114', 'COTE D\'IVOIRE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('115', 'CROATIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('116', 'CUBA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('118', 'CYPRUS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('120', 'CZECHOSLOVAKIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('121', 'CZECH REPUBLIC');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('122', 'DENMARK');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('124', 'DJIBOUTI');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('126', 'DOMINICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('128', 'DOMINICAN REPUBLIC');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('130', 'EAST TIMOR');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('132', 'ECUADOR');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('134', 'EGYPT');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('136', 'EL SALVADOR');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('138', 'EQUATORIAL GUINEA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('139', 'ERITREA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('140', 'ESTONIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('142', 'ETHIOPIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('144', 'FALKLAND ISLANDS (MALVINAS)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('146', 'FAROE ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('148', 'FIJI');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('150', 'FINLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('152', 'FRANCE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('154', 'FRENCH GUIANA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('156', 'FRENCH POLYNESIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('158', 'FRENCH SOUTHERN TERRITORIES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('160', 'GABON');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('162', 'GAMBIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('164', 'GEORGIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('166', 'GERMANY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('168', 'GHANA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('170', 'GIBRALTAR');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('172', 'GREECE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('174', 'GREENLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('176', 'GRENADA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('178', 'GUADELOUPE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('180', 'GUAM');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('182', 'GUATEMALA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('184', 'GUINEA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('186', 'GUINEA-BISSAU');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('188', 'GUYANA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('190', 'HAITI');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('192', 'HEARD AND MCDONALD ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('194', 'HONDURAS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('196', 'HONG KONG');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('198', 'HUNGARY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('200', 'ICELAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('202', 'INDIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('204', 'INDONESIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('206', 'IRAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('208', 'IRAQ');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('210', 'IRISH REPUBLIC');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('212', 'ISRAEL');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('214', 'ITALY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('218', 'JAMAICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('220', 'JAPAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('222', 'JORDAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('224', 'KAZAKHSTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('226', 'KENYA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('228', 'KIRIBATI');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('230', 'KOREA, DEM. P. REP. (NORTH KOREA)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('232', 'KOREA, REPUBLIC OF (SOUTH KOREA)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('234', 'KUWAIT');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('236', 'KYRGYZSTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('238', 'LAOS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('240', 'LATVIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('242', 'LEBANON');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('244', 'LESOTHO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('246', 'LIBERIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('248', 'LIBYA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('250', 'LIECHTENSTEIN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('252', 'LITHUANIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('254', 'LUXEMBOURG');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('256', 'MACAO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('258', 'MACEDONIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('260', 'MADAGASCAR');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('262', 'MALAWI');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('264', 'MALAYSIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('266', 'MALDIVES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('268', 'MALI');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('270', 'MALTA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('272', 'MARSHALL ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('274', 'MARTINIQUE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('276', 'MAURITANIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('278', 'MAURITIUS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('280', 'MAYOTTE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('282', 'MEXICO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('284', 'MICRONESIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('286', 'MOLDOVA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('288', 'MONACO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('290', 'MONGOLIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('292', 'MONTENEGRO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('294', 'MONTSERRAT');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('296', 'MOROCCO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('298', 'MOZAMBIQUE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('300', 'MYANMAR, UNION OF (BURMA)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('302', 'NAMIBIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('304', 'NAURU');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('306', 'NEPAL');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('308', 'NETHERLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('310', 'NETHERLANDS ANTILLES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('312', 'NEUTRAL ZONE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('314', 'NEW CALEDONIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('316', 'NEW ZEALAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('318', 'NICARAGUA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('320', 'NIGER');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('322', 'NIGERIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('324', 'NIUE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('326', 'NORFOLK ISLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('328', 'NORTHERN MARIANA ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('330', 'NORWAY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('332', 'OMAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('334', 'PAKISTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('336', 'PALAU');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('337', 'PALESTINE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('338', 'PANAMA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('340', 'PAPUA NEW GUINEA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('342', 'PARAGUAY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('344', 'PERU');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('346', 'PHILIPPINES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('348', 'PITCAIRN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('350', 'POLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('352', 'PORTUGAL');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('354', 'PUERTO RICO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('356', 'QATAR');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('358', 'REUNION');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('360', 'ROMANIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('362', 'ROSS DEPENDENCY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('364', 'RUSSIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('366', 'RWANDA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('367', 'ST. BARTHELEMY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('368', 'ST. HELENA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('370', 'ST. KITTS AND NEVIS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('372', 'ST. LUCIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('373', 'ST. MARTIN (FRENCH)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('374', 'ST. PIERRE AND MIQUELON');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('376', 'ST. VINCENT AND THE GRENADINES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('378', 'SAMOA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('380', 'SAN MARINO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('382', 'SAO TOME AND PRINCIPE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('384', 'SAUDI ARABIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('386', 'SENEGAL');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('388', 'SERBIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('390', 'SEYCHELLES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('392', 'SIERRA LEONE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('394', 'SINGAPORE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('395', 'SLOVAK REPUBLIC');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('396', 'SLOVENIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('398', 'SOLOMON ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('400', 'SOMALIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('402', 'SOUTH AFRICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('404', 'SOUTH GEORGIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('405', 'SOUTH SUDAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('406', 'SPAIN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('408', 'SRI LANKA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('410', 'SUDAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('412', 'SURINAM (E)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('414', 'SVALBARD AND JAN MAYEN ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('416', 'SWAZILAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('418', 'SWEDEN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('420', 'SWITZERLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('422', 'SYRIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('424', 'TAIWAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('426', 'TAJIKISTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('428', 'TANZANIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('430', 'THAILAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('432', 'TOGO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('434', 'TOKELAU');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('436', 'TONGA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('438', 'TRINIDAD AND TOBAGO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('440', 'TUNISIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('442', 'TURKEY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('444', 'TURKMENISTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('446', 'TURKS AND CAICOS ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('448', 'TUVALU');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('450', 'UGANDA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('452', 'UKRAINE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('454', 'UNITED ARAB EMIRATES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('456', 'UNITED STATES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('458', 'USA MINOR OUTLYING ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('460', 'URUGUAY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('464', 'UZBEKISTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('466', 'VANUATU');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('468', 'VATICAN CITY (HOLY SEE)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('470', 'VENEZUELA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('472', 'VIETNAM');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('474', 'VIRGIN ISLANDS (BRITISH)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('476', 'VIRGIN ISLANDS (US)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('478', 'WALLIS AND FUTUNA ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('480', 'WESTERN SAHARA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('482', 'YEMEN (ARAB REPUBLIC)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('483', 'YEMEN (REPUBLIC OF)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('484', 'YEMEN (DEM. PEOPLES REPUBLIC)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('486', 'ZAIRE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('488', 'ZAMBIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('490', 'ZIMBABWE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('492', 'KOSOVO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('500', 'ABYSSINIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('502', 'ADEN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('506', 'BASUTOLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('508', 'BECHUANALAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('510', 'BELGIAN CONGO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('512', 'BOHEMIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('514', 'BRITISH CENTRAL AFRICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('516', 'BRITISH EAST AFRICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('518', 'BRITISH GUIANA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('520', 'BRITISH HONDURAS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('522', 'BRITISH NORTH BORNEO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('524', 'BRITISH SUBJECT - NO CITIZENSHIP');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('525', 'BURMA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('526', 'CANTON AND ENDERBURY ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('528', 'CENTRAL AFRICAN EMPIRE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('530', 'CEYLON');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('532', 'CHANDERNAGORE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('534', 'CITIZEN OF THE UK. AND COLONIES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('536', 'CONGO (BRAZZAVILLE)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('538', 'DAHOMEY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('540', 'DAMAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('542', 'DAORA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('544', 'DIU');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('546', 'DRONNING MAUD LAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('548', 'DUTCH EAST INDIES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('550', 'DUTCH GUIANA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('552', 'EAST GERMANY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('554', 'EAST PAKISTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('556', 'EAST PRUSSIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('558', 'EIRE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('560', 'FED. OF RHODESIA AND NYASALAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('562', 'FRENCH GUINEA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('564', 'FRENCH SUDAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('566', 'FRENCH TOGOLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('568', 'GERMAN DEMOCRATIC REPUBLIC');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('570', 'GERMAN EAST AFRICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('572', 'GERMAN SOUTHWEST AFRICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('574', 'GERMANY, FEDERAL REPUBLIC OF');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('576', 'GILBERT ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('578', 'GOA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('580', 'GOLD COAST');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('582', 'HAWAIIAN ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('584', 'INDOCHINA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('586', 'IRISH FREE STATE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('587', 'IVORY COAST');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('588', 'JAVA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('590', 'JOHNSTON ISLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('592', 'KAMPUCHEA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('594', 'KARIKAL');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('596', 'KOREA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('598', 'MAHE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('600', 'MALAGASY REPUBLIC');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('602', 'MALAYA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('604', 'MIDDLE CONGO');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('606', 'MIDWAY ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('608', 'NAGAR HAVELI');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('610', 'NETHERLANDS EAST INDIES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('612', 'NEWFOUNDLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('614', 'NORTH VIETNAM');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('616', 'NORTHERN RHODESIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('618', 'NUPE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('620', 'NYASALAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('622', 'PACIFIC ISLANDS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('624', 'PALESTINE');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('626', 'PERSIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('628', 'PONDICHERRY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('630', 'PORTUGUESE EAST AFRICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('632', 'PORTUGUESE GUINEA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('634', 'PORTUGUESE WEST AFRICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('636', 'RHODESIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('638', 'RUANDA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('640', 'SENEGAMBIA AND NIGER TERRITORIES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('644', 'SIAM');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('646', 'SLOVAKIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('647', 'SOMALILAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('648', 'SOUTH VIETNAM');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('650', 'SOUTH WEST AFRICA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('652', 'SOUTHERN RHODESIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('654', 'SOUTHERN YEMEN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('656', 'SPANISH GUIANA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('658', 'SPANISH SAHARA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('660', 'ST. KITTS-NEVIS-ANGUILLA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('662', 'TANGANYIKA (AND ZANZIBAR)');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('664', 'TIBET');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('666', 'TOGOLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('668', 'TRANSJORDAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('670', 'TRUCIAL STATES');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('672', 'UNITED ARAB REPUBLICS');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('674', 'UPPER SENEGAL AND NIGER');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('676', 'UPPER VOLTA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('678', 'URUNDI');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('679', 'USSR');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('680', 'WAKE ISLAND');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('682', 'WEST GERMANY');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('684', 'WEST PAKISTAN');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('686', 'YANAM');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('688', 'YUGOSLAVIA');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('690', 'ZANZIBAR');
INSERT INTO `Nationality` (`Nationality_UID`, `Nationality`) VALUES ('999', 'STATELESS/NO COUNTRY');

-- Occupation import
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00101', 'CARAVAN SITE OWNER/WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00102', 'CHAMBERMAID');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00103', 'HOTELIER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00104', 'HOTEL DOMESTIC STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00105', 'HOTEL MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00106', 'HOTEL PORTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00107', 'HOTEL RECEPTIONIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00108', 'NIGHT PORTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00109', 'ROOMING AGENCY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00201', 'AGRICULTURAL ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00202', 'AGRICULTURAL SALESMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00203', 'CROFTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00204', 'DAIRY WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00205', 'DEER STALKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00206', 'FARMER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00207', 'FARM MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00208', 'FARM WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00209', 'FORESTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00210', 'GAMEKEEPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00211', 'HORTICULTURIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00212', 'MARKET GARDENER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00213', 'POULTRY WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00214', 'RANGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00215', 'SHEPHERD');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00216', 'SMALLHOLDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00217', 'STOCKMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00218', 'TREE SURGEON');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00301', 'AIRCRAFT MAINTENANCE CREW');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00302', 'AIRCRAFT CABIN CREW');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00303', 'AIRCRAFT CATERER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00304', 'AIRCRAFT CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00305', 'AIRCRAFT ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00306', 'AIR TRAFFIC CONTROLLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00307', 'AIRLINE STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00308', 'BRITISH AIRPORTS AUTHORITY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00309', 'BAGGAGE HANDLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00310', 'CIVIL AVIATION AUTHORITY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00311', 'CUSTOMER SERVICE AGENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00312', 'FLIGHT ATTENDANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00313', 'FLIGHT DECK OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00314', 'PILOT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00315', 'TICKET CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00401', 'ANIMAL BEAUTICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00402', 'ANIMAL CHARITY WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00403', 'ANIMAL NURSE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00404', 'ANIMAL TRAINER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00405', 'BREEDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00406', 'DOG WARDEN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00407', 'FARRIER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00408', 'RSPCA EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00409', 'STABLE WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00410', 'TAXIDERMIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00411', 'VETERINARY SURGEON');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00412', 'ZOO KEEPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00501', 'AIR FORCE PERSONNEL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00502', 'ARMY PERSONNEL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00503', 'FOREIGN FORCES PERSONNEL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00504', 'MARINE PERSONNEL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00505', 'NAVY PERSONNEL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00601', 'ARTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00602', 'ARTISTS MODEL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00603', 'ARTS ADMINISTRATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00604', 'CARTOONIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00605', 'DESIGNER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00606', 'DEVELOPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00607', 'ENGRAVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00608', 'ILLUSTRATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00609', 'MODEL MAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00610', 'PHOTOGRAPHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00611', 'PHOTOGRAPHIC TECHNICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00612', 'SCULPTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00613', 'SIGNWRITER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00701', 'CANTEEN WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00702', 'CATERING MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00703', 'CATERING WHOLESALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00704', 'CHEF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00705', 'COOK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00706', 'FAST FOOD EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00707', 'KITCHEN WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00708', 'RESTAURATEUR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00709', 'WAITER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00710', 'WAITRESS');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00801', 'COLLECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00802', 'DISTRIBUTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00803', 'VOLUNTEER ORGANISER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00804', 'VOLUNTARY WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00901', 'CIVIL SERVANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00902', 'CUSTOMS & EXCISE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00903', 'GOVERNMENT EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('00904', 'INLAND REVENUE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01001', 'CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01002', 'COMMERCIAL CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01003', 'CONTRACT CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01004', 'DOMESTIC CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01005', 'DRAIN CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01006', 'DRY CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01007', 'INDUSTRIAL CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01008', 'LAUNDRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01009', 'LAUNDERETTE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01010', 'OFFICE CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01101', 'COMPUTER CONSULTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01102', 'COMPUTER INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01103', 'COMPUTER MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01104', 'COMPUTER OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01105', 'COMPUTER PROGRAMMER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01106', 'COMPUTER SCIENTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01107', 'DATABASE MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01109', 'COMPUTER ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01110', 'SYSTEMS ANALYST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01201', 'ARCHITECT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01202', 'ASPHALTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01203', 'BRICKLAYER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01204', 'BUILDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01205', 'BUILDERS AGENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01206', 'BUILDING LABOURER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01207', 'BUILDERS MERCHANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01208', 'BUILDING SURVEYOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01209', 'CAVITY WALL INSULATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01210', 'CEILING FIXER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01211', 'CLERK OF WORKS');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01212', 'CONCRETE/CEMENT WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01213', 'CONSTRUCTION WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01214', 'DEMOLITION WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01215', 'FLOOR LAYER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01216', 'FOREMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01217', 'HOD CARRIER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01218', 'LABOURER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01219', 'LAND SURVEYOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01221', 'PLASTERER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01222', 'PROPERTY DEVELOPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01223', 'QUANTITY SURVEYOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01224', 'ROAD WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01225', 'ROOFER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01226', 'SCAFFOLDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01227', 'SITE AGENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01228', 'STEEL ERECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01229', 'STEEPLEJACK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01230', 'STONEMASON');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01231', 'TARMAC LAYER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01232', 'TILER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01233', 'TIMBER YARD WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01234', 'TUNNELLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01235', 'WINDOW FITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01301', 'ANTIQUE DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01302', 'ART DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01303', 'CATTLE DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01304', 'COIN DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01305', 'FIREARMS DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01306', 'GAME/POULTRY DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01307', 'SALVAGE DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01308', 'SCRAP METAL DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01309', 'SECOND HAND DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01310', 'STAMP DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01401', 'COMPANY DIRECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01402', 'DIRECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01403', 'FINANCIAL DIRECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01404', 'MANAGING DIRECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01405', 'SALES DIRECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01501', 'AU PAIR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01502', 'BUTLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01503', 'DOMESTIC EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01504', 'HOUSEKEEPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01505', 'MAID');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01506', 'PORTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01507', 'NANNY');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01508', 'VALET');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01509', 'HANDYMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01601', 'BURSAR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01602', 'CAREERS OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01603', 'COLLEGE STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01604', 'CRECHE ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01605', 'EDUCATIONAL PSYCHOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01606', 'EDUCATION SOCIAL WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01607', 'EDUCATIONAL WELFARE OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01608', 'GOVERNESS');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01609', 'INSTRUCTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01610', 'LECTURER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01611', 'NURSERY NURSE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01612', 'NURSERY WORKER (EDUCATION)');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01613', 'PLAYGROUP WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01614', 'PLAYLEADER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01615', 'PROFESSOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01616', 'RESEARCHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01617', 'SCHOOL CARETAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01618', 'SCHOOL CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01619', 'SCHOOL LIBRARIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01620', 'SCHOOL MEAL ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01621', 'SCHOOL SECRETARY');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01622', 'SCHOOL TECHNICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01623', 'SPECIAL WELFARE ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01624', 'SPORTS INSTRUCTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01625', 'TEACHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01626', 'TUTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01627', 'UNIVERSITY ACADEMIC STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01628', 'YOUTH & COMMUNITY WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01629', 'CLASSROOM ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01630', 'CLASSROOM HELPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01701', 'AMBULANCE SERVICE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01702', 'AMBULANCE SERVICE CIVILIAN EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01703', 'COASTGUARD');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01704', 'FIRE SERVICE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01705', 'FIRE SERVICE CIVILIAN EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01706', 'NON HOME OFFICE POLICE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01707', 'POLICE SERVICE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01708', 'POLICE SERVICE CIVILIAN EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01709', 'TRAFFIC WARDEN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01801', 'AIR CONDITIONING ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01802', 'BOILERMAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01803', 'CHEMICAL ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01804', 'CIVIL ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01805', 'DRAUGHTSMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01806', 'ELECTRICAL ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01807', 'ELECTRONIC ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01808', 'ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01809', 'FIELD ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01810', 'FITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01812', 'LIFT ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01814', 'MAINTENANCE ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01816', 'MARINE ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01817', 'METAL POLISHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01818', 'OFFSHORE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01819', 'PANEL BEATER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01820', 'PIPE FITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01822', 'TECHNICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01823', 'TOOLMAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01824', 'TURNER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01825', 'SERVICE ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01826', 'SHEET METAL WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01827', 'STEEL WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01828', 'VENTILATION ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01829', 'WELDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01830', 'WORKSHOP ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01901', 'ACTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01902', 'AMUSEMENT ARCADE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01903', 'ARTISTS AGENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01904', 'ASTROLOGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01906', 'BETTING OFFICE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01907', 'BINGO EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01908', 'BOOKMAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01909', 'BOWLING ALLEY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01910', 'CASINO EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01911', 'CINEMA EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01912', 'CIRCUS EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01913', 'CLUB OWNER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01914', 'CROUPIER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01915', 'CURATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01916', 'DANCER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01918', 'DISC JOCKEY');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01919', 'DOORMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01920', 'ENTERTAINER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01921', 'FAIRGROUND EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01922', 'FILM INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01923', 'HOSTESS');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01924', 'MUSEUM EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01925', 'MUSICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01926', 'PARTY ORGANISER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01927', 'PROMOTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01928', 'PROJECTIONIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01929', 'SINGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01930', 'TATTOOIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01931', 'THEATRE DIRECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01932', 'THEATRE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('01933', 'TOURIST GUIDE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02001', 'ASSEMBLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02002', 'PRODUCTION CONTROLLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02003', 'DESPATCH WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02004', 'FACTORY MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02005', 'GLASS INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02006', 'MACHINE OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02007', 'MAINTENANCE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02009', 'MANUFACTURING EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02010', 'PACKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02011', 'POTTERY INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02012', 'PROGRESS CHASER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02013', 'QUALITY CONTROLLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02014', 'SHEETMETAL WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02015', 'SHOE INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02016', 'STOCK CONTROLLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02017', 'STOCK TAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02018', 'STOREKEEPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02019', 'STOREMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02020', 'SUPERVISOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02022', 'WAREHOUSEMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02023', 'FACTORY WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02101', 'CUTTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02102', 'DRAPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02103', 'DRESSMAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02104', 'FASHION DESIGNER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02105', 'FINISHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02106', 'KNITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02107', 'MACHINIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02108', 'MILLINER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02109', 'MILL WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02110', 'MODEL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02111', 'PRESSER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02112', 'REPAIRER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02113', 'TAILOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02114', 'TEXTILE WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02115', 'SEAMSTRESS');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02116', 'WEAVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02201', 'ACCOUNTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02202', 'ACCOUNTS CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02203', 'ACTUARY');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02204', 'ASSESSOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02205', 'AUDITOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02206', 'BANKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02207', 'BANK EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02208', 'BANK MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02209', 'BOOKKEEPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02210', 'BROKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02211', 'BUILDING SOCIETY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02212', 'CLAIMS MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02213', 'COMMODITIES BROKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02214', 'COMPTOMETER OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02215', 'CONSUMER CREDIT EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02216', 'ECONOMIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02217', 'FINANCIAL ADVISER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02218', 'FINANCE CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02219', 'FINANCE OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02220', 'FINANCIAL CONSULTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02221', 'FINANCIAL SERVICES EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02222', 'INSPECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02223', 'INSURANCE BROKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02224', 'INSURANCE CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02225', 'INSURANCE UNDERWRITER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02226', 'LOSS ADJUSTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02227', 'MORTGAGE CONSULTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02228', 'TAX SPECIALIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02229', 'SECURITIES DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02230', 'STOCKBROKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02231', 'UNDERWRITER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02232', 'VALUER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02233', 'WAGES CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02301', 'FISHERMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02302', 'FISH FARMER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02303', 'FISHING INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02304', 'FISH MERCHANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02401', 'BAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02402', 'BREWER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02403', 'BUTCHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02404', 'CONFECTIONER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02405', 'DISTILLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02406', 'DRINKS MAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02407', 'FISHMONGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02408', 'FOOD SCIENTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02409', 'FOOD TECHNOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02410', 'GREEN GROCER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02411', 'GROCER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02412', 'PRODUCER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02413', 'SLAUGHTERER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02415', 'WINE/SPIRIT DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02501', 'FENCE ERECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02502', 'GARDENER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02503', 'GARDEN CENTRE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02504', 'GREEN KEEPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02505', 'GROUNDSMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02506', 'LANDSCAPE GARDENER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02507', 'NURSERY EMPLOYEE (HORTICULTURE)');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02601', 'CARETAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02602', 'CONVEYANCER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02603', 'ESTATE MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02604', 'ESTATE AGENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02605', 'JANITOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02606', 'LANDLORD');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02607', 'PROPERTY MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02608', 'SURVEYOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02610', 'WARDEN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02701', 'HOUSEWIFE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02702', 'HOUSEHUSBAND');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02801', 'INTERPRETER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02802', 'TRANSLATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02901', 'ADVOCATE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02902', 'ARTICLED CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02903', 'BAILIFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02904', 'BARRISTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02905', 'COURT EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02906', 'COURT USHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02907', 'CROWN PROSECUTION SERVICE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02908', 'JUDGE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02909', 'STIPENDIARY MAGISTRATE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02910', 'MAGISTRATES CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02911', 'PROCURATOR FISCAL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02912', 'RECORDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02913', 'SHERIFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('02914', 'SOLICITOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03001', 'BAR STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03002', 'BAR STEWARD');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03003', 'BREWERY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03004', 'CELLARMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03005', 'COOPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03006', 'DISTILLERY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03007', 'DRAYMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03008', 'LICENSEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03009', 'OFF LICENCE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03010', 'PUBLICAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03101', 'AUTHOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03102', 'ARCHIVIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03104', 'BOOKSELLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03105', 'INDEXER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03106', 'LIBRARIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03107', 'LIBRARY ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03108', 'LITERARY AGENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03109', 'PROOFREADER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03110', 'PUBLISHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03111', 'TECHNICAL AUTHOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03112', 'WRITER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03201', 'ENVIRONMENTAL HEALTH OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03202', 'LOCAL AUTHORITY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03203', 'PARKS STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03204', 'PLANNING OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03205', 'PUBLIC CONVENIENCE ATTENDANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03206', 'PUBLIC HEALTH OFFICIAL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03207', 'REFUSE WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03208', 'ROAD SWEEPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03209', 'SCHOOL CROSSING PATROL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03210', 'TRADING STANDARDS OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03211', 'TRAFFIC MANAGEMENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03301', 'BARGEMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03302', 'BOATBUILDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03303', 'BOATYARD EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03304', 'CREWMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03305', 'DECKHAND');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03306', 'DIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03307', 'DOCKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03308', 'HARBOURMASTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03309', 'LINER STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03310', 'MARINE PILOT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03311', 'MERCHANT SEAMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03312', 'OIL RIG WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03313', 'SAILMAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03314', 'SHIPBUILDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03315', 'SHIPYARD EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03316', 'SHIPWRIGHT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03317', 'TUGMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03318', 'YACHT BUILDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03319', 'YACHTSMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03320', 'MARITIME ARMED GUARD');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03401', 'ADVERTISING EXECUTIVE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03402', 'BILL POSTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03403', 'CAMERAMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03404', 'EDITOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03405', 'MARKET RESEARCH EXECUTIVE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03406', 'MARKET RESEARCH INTERVIEWER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03407', 'MEDIA EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03408', 'NEWSPAPER JOURNALIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03409', 'NEWSPAPER STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03410', 'PUBLICITY OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03411', 'RADIO NEWS STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03412', 'RADIO PRESENTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03413', 'RADIO PRODUCER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03414', 'RADIO STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03415', 'TV NEWS STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03416', 'TV PRESENTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03417', 'TV PRODUCER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03418', 'TV STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03501', 'ACUPUNCTURIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03502', 'ADMINISTRATION STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03503', 'ANAESTHETIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03504', 'ANALYST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03505', 'BLOOD TRANSFUSION EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03506', 'CHIROPODIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03507', 'CHIROPRACTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03508', 'COUNSELLOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03509', 'DENTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03510', 'DENTAL HYGIENIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03511', 'DENTAL TECHNICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03512', 'DOCTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03513', 'GENERAL PRACTITIONER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03514', 'HEALTH VISITOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03515', 'HERBALIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03516', 'HOMOEOPATHIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03517', 'HOSPITAL CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03518', 'HOSPITAL MAINTENANCE STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03519', 'HOSPITAL PORTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03520', 'LABORATORY TECHNICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03521', 'LIMB MAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03522', 'MEDICAL PHOTOGRAPHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03523', 'MEDICAL RECORDS CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03524', 'MEDICAL STUDENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03525', 'MEDICAL TECHNICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03526', 'MENTAL NURSE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03527', 'MIDWIFE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03528', 'MORTICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03529', 'MORTUARY ATTENDANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03530', 'NATUROPATH');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03531', 'NURSE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03532', 'NURSING ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03533', 'NURSING AUXILIARY');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03534', 'NURSING HOME EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03536', 'OPERATING DEPARTMENT ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03537', 'OPTICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03538', 'OPTOMETRIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03539', 'ORTHOPTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03540', 'OSTEOPATH');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03541', 'PATHOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03542', 'PHARMACIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03543', 'PHYSICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03544', 'PHYSIOTHERAPIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03545', 'PROSTHETIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03546', 'PSYCHIATRIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03547', 'PSYCHOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03548', 'RADIOGRAPHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03549', 'RADIOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03550', 'MEDICAL RECEPTIONIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03551', 'REFLEXOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03552', 'SPEECH THERAPIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03553', 'SURGEON');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03554', 'DIETICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03555', 'MEDICAL LABORATORY SCIENTIFIC OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03556', 'ORTHOTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03557', 'ART THERAPIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03558', 'DRAMA THERAPIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03559', 'MUSIC THERAPIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03560', 'MEDICAL PRACTITIONER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03601', 'MINER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03602', 'MINE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03603', 'QUARRYMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03604', 'PROSPECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03701', 'AUTO ELECTRICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03702', 'BODY REPAIRER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03703', 'BRAKE FITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03704', 'BREAKDOWN SERVICE STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03705', 'CAR BREAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03706', 'CAR SALESMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03707', 'COACH BUILDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03708', 'FORECOURT ATTENDANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03709', 'GARAGE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03710', 'GARAGE PROPRIETOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03711', 'MECHANIC');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03712', 'MOTOR DEALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03713', 'SPRAY PAINTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03714', 'TYRE/EXHAUST FITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03715', 'VEHICLE EXAMINER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03716', 'VEHICLE HIRER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03717', 'VEHICLE REMOVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03801', 'ADMINISTRATIVE ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03802', 'CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03803', 'CLERICAL ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03804', 'COMPANY SECRETARY');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03805', 'DATA ENTRY CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03806', 'DESK TOP PUBLISHING OPERATIVE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03807', 'FILING CLERK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03808', 'MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03809', 'MESSENGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03810', 'PERSONAL ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03811', 'PERSONNEL MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03812', 'RECEPTIONIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03813', 'SECRETARY');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03814', 'OFFICE SUPERVISOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03815', 'TELEPHONIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03816', 'TYPIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03817', 'VDU OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03901', 'AROMATHERAPIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03902', 'BARBER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03903', 'BEAUTICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03904', 'COSMETICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03905', 'ESCORT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03906', 'HAIRDRESSER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03907', 'MAKEUP ARTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03908', 'MANICURIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('03909', 'MASSEUR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04001', 'CRANE DRIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04002', 'FORK LIFT DRIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04003', 'JCB DRIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04004', 'PLANT DRIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04005', 'PLANT OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04006', 'SKIP HIRER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04101', 'MEMBER OF PARLIAMENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04102', 'POLITICAL RESEARCHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04103', 'POLITICAL SCIENTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04201', 'COUNTER STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04202', 'POSTMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04203', 'POSTMASTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04204', 'POST OFFICE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04205', 'SORTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04301', 'ATOMIC ENERGY AUTHORITY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04302', 'ELECTRICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04303', 'ELECTRICITY BOARD EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04304', 'ELECTRICAL FITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04305', 'ELECTRIC METER READER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04306', 'ELECTRICAL WHOLESALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04307', 'ELECTRICAL WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04308', 'GAS BOARD EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04309', 'GAS FITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04310', 'GAS METER READER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04311', 'GAS WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04312', 'HYDRO-ELECTRIC INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04313', 'NUCLEAR INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04314', 'OIL INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04401', 'BOOKBINDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04402', 'COMPOSITOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04403', 'LINOTYPE OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04404', 'LITHOGRAPHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04405', 'PRINT EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04406', 'SILK SCREEN PRINTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04407', 'PRINTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04501', 'CHAPLAIN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04502', 'GOVERNOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04503', 'PRISON CIVILIAN EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04504', 'PRISON OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04601', 'CHURCH OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04602', 'EVANGELIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04603', 'MINISTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04604', 'MISSIONARY WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04605', 'MONK');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04606', 'NUN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04607', 'PRIEST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04608', 'SALVATION ARMY OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04609', 'VERGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04610', 'VICAR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04701', 'AUCTIONEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04702', 'BUYER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04703', 'CASHIER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04704', 'CHECKOUT OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04705', 'FLORIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04706', 'FLOWER SELLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04707', 'IRONMONGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04708', 'JEWELLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04709', 'NEWS AGENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04710', 'NEWS VENDOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04711', 'PAWNBROKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04712', 'RETAIL EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04713', 'SALES ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04714', 'SALES REPRESENTATIVE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04715', 'SHOP EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04716', 'SHOPKEEPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04717', 'SHOP MANAGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04718', 'STALL HOLDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04719', 'STORE DEMONSTRATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04720', 'STREET TRADER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04721', 'TOBACCONIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04722', 'TRADER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04723', 'TRAVEL AGENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04724', 'VIDEO RETAILER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04725', 'WHOLESALER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04726', 'WINDOW DRESSER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04801', 'BREADMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04802', 'COAL MERCHANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04803', 'DEBT COLLECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04804', 'DELIVERYMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04805', 'DOOR TO DOOR SALESPERSON');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04806', 'INSURANCE COLLECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04807', 'MILKMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04808', 'PEDLAR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04809', 'POOLS COLLECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04810', 'RENT COLLECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04811', 'VAN DELIVERY AGENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04901', 'ANTHROPOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04902', 'ARCHAEOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04903', 'ASTRONOMER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04904', 'BIOCHEMIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04905', 'BIOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04906', 'BOTANIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04907', 'CARTOGRAPHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04908', 'CHEMIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04909', 'FORENSIC SCIENTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04910', 'GEOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04911', 'MATHEMATICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04912', 'METALLURGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04913', 'METEOROLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04914', 'OCEANOGRAPHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04915', 'PALAEONTOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04916', 'PHYSICIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04917', 'SCIENTIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04918', 'STATISTICIAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('04919', 'ZOOLOGIST');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05001', 'ALARM INSTALLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05002', 'SECURITY CONTROLLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05003', 'INVESTIGATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05005', 'PATROLMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05006', 'PRIVATE DETECTIVE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05007', 'SECURITY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05008', 'SECURITY GUARD');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05009', 'STORE DETECTIVE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05010', 'WATCHMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05011', 'CCTV OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05101', 'SELF EMPLOYED');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05201', 'TRADE UNION OFFICIAL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05202', 'CLUB OFFICIAL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05301', 'CARE ASSISTANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05302', 'CHILD CARER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05303', 'CHILD GUIDANCE OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05304', 'CHILDRENS HOME EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05305', 'CHILDMINDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05306', 'COMMUNITY WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05307', 'FAMILY CAREWORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05308', 'HOME HELP');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05309', 'INTERMEDIATE TREATMENT STAFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05310', 'PROBATION OFFICER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05311', 'PROBATION SERVICE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05312', 'RESIDENTIAL CARE WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05313', 'SOCIAL SERVICES EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05314', 'SOCIAL WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05315', 'WELFARE WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05316', 'YOUTH WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05401', 'ATHLETE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05402', 'ATTENDANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05403', 'BATHS ATTENDANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05404', 'BOXER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05405', 'COACH');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05406', 'CRICKETER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05407', 'FOOTBALLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05408', 'GATEMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05409', 'GOLFER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05410', 'JOCKEY');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05411', 'LIFE GUARD');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05412', 'PROFESSIONAL SPORTSMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05413', 'RIDING INSTRUCTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05414', 'RUGBY FOOTBALLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05415', 'SKI INSTRUCTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05416', 'SPORTS CENTRE EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05417', 'SPORTS OFFICIAL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05418', 'STEWARD');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05419', 'TENNIS PLAYER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05420', 'TRAINER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05501', 'GRADUATE STUDENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05502', 'FULL TIME STUDENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05503', 'POST GRADUATE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05504', 'SCHOOLBOY');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05505', 'SCHOOLGIRL');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05506', 'STUDENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05601', 'BRITISH TELECOM EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05602', 'LINESMAN');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05603', 'RADAR OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05604', 'TELECOMMUNICATIONS EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05605', 'TELECOMMUNICATIONS ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05701', 'AERIAL ERECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05702', 'ART/ANTIQUE RESTORER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05703', 'BLACKSMITH');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05704', 'CABINET MAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05705', 'CARPENTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05706', 'CARPET FITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05707', 'CHIMNEY SWEEP');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05708', 'DECORATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05709', 'DOUBLE GLAZER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05710', 'FRENCH POLISHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05711', 'FURNITURE MAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05712', 'FURNITURE REMOVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05713', 'GLAZIER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05714', 'GOLDSMITH');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05715', 'HEATING ENGINEER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05716', 'JOINER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05717', 'KEY CUTTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05718', 'KNIFE GRINDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05719', 'LOCKSMITH');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05720', 'PAINTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05721', 'PIANO TUNER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05722', 'PLUMBER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05723', 'REMOVALS OPERATIVE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05724', 'SATELLITE DISH RIGGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05725', 'SHOE REPAIRER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05726', 'SHOP FITTER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05727', 'SILVERSMITH');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05728', 'THATCHER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05729', 'WATCHMAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05730', 'WINDOW CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05731', 'UPHOLSTERER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05801', 'BUS CONDUCTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05802', 'CAR PARK ATTENDANT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05803', 'CHAUFFEUR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05804', 'COACH OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05805', 'COURIER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05806', 'DESPATCH RIDER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05807', 'DRIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05808', 'DRIVING INSTRUCTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05809', 'FLYING INSTRUCTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05810', 'HAULIER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05811', 'HGV DRIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05812', 'FREIGHT HANDLER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05813', 'MINI CAB DRIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05814', 'PRIVATE HIRE OPERATOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05815', 'PCV DRIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05816', 'RAILWAY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05817', 'TAXI DRIVER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05818', 'TRANSPORT CLEANER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05819', 'TRAVEL INDUSTRY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05820', 'CAR VALETER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05901', 'CEMETERY SUPERINTENDENT');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05902', 'CREMATORIUM WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05903', 'EMBALMER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05904', 'FUNERAL DIRECTOR');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05905', 'GRAVE DIGGER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('05906', 'UNDERTAKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06001', 'UNEMPLOYED');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06101', 'LOCK KEEPER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06102', 'SEWAGE WORKER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06103', 'WATER AUTHORITY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06104', 'WATER BAILIFF');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06105', 'WATERWAY EMPLOYEE');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06200', 'PENSIONER');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06300', 'RETIRED');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06401', 'HEALTH AND SAFETY PERSON');
INSERT INTO `Occupation` (`Occupation_UID`, `Occupation`) VALUES ('06501', 'NOT STATED');

insert into person values (1, '00/ABCDE', 'M', NULL, NULL, NULL), (2, '01/ABCDE', 'F', NULL, NULL, NULL), (3, '02/ABCDE', 'X', NULL, NULL, NULL);

insert into name_alias values (1, 1, 'P', NULL, NULL, 'David', NULL, NULL, NULL, 'Testman', NULL, NULL, NULL, NULL, NULL), (2, 2, 'P', NULL, NULL, 'Hillary', NULL, NULL, NULL, 'Testman', NULL, NULL, NULL, NULL, NULL), (3, 3, 'P', NULL, NULL, 'David', NULL, NULL, NULL, 'Otherman', NULL, NULL, NULL, NULL, NULL);

insert into event values (1, 1, NOW(), 'RM', NULL, '01', NULL, 'Public Disorder', NULL, NOW()), (2, 2, NOW(), 'AS', NULL, 'ab', NULL, 'Impersonating a Police Officer', NULL, NOW()), (3, 3, NOW(), 'RM', NULL, 'gre', NULL, 'Murder', NULL, NOW());

insert into person_status values (1, 1, 10, NULL, 1, NOW(), NOW()), (2, 2, 11, 'Introduced herself as PC Plod', 2, NOW(), NOW()), (3, 3, 9, 'Arrest on sight', 3, NOW(), NOW()), (4, 3, 7, 'Dangerous: presume armed', 2, NOW(), NOW());