
CREATE TABLE Biler (
    BilID INT PRIMARY KEY AUTO_INCREMENT,
    MaerkeModelbiler VARCHAR(50),
    BraendstofType VARCHAR(50),
    Registreringsnummer VARCHAR(20),
    RegistreringDATO DATE,
    KoeretoejKilometer INT
);

CREATE TABLE BilGrupper (
    GruppeID INT PRIMARY KEY AUTO_INCREMENT,
    GruppeNavn VARCHAR(4) UNIQUE,
    MaerkeModel VARCHAR(50)
);

CREATE TABLE Biler_BilGrupper (
    BilID INT,
    GruppeID INT,
    PRIMARY KEY (BilID, GruppeID),
    FOREIGN KEY (BilID) REFERENCES Biler(BilID),
    FOREIGN KEY (GruppeID) REFERENCES BilGrupper(GruppeID)
);

CREATE TABLE Lejere (
    LejerID INT PRIMARY KEY AUTO_INCREMENT,
    Navn VARCHAR(100),
    Adresse VARCHAR(200),
    PostnummerID INT,
    Mobiltelefon VARCHAR(15),
    ByNavn VARCHAR(100),
    Telefon VARCHAR(15),
    Email VARCHAR(100),
    KoerekortNummer VARCHAR(20),
    KoerekortUdstedelsesdato DATE,
    FOREIGN KEY (PostnummerID) REFERENCES Postnumre(PostnummerID)
);


CREATE TABLE Postnumre (
    PostnummerID INT PRIMARY KEY AUTO_INCREMENT,
    Postnummer VARCHAR(4) UNIQUE,
    ByNavn VARCHAR(100)
);


CREATE TABLE Lejekontrakter (
    KontraktID INT PRIMARY KEY AUTO_INCREMENT,
    LejerID INT,
    BilID INT,
    FraDatoTid DATETIME,
    TilDatoTid DATETIME,
    MaxKilometer INT,
    StartKilometer INT,
    FOREIGN KEY (LejerID) REFERENCES Lejere(LejerID),
    FOREIGN KEY (BilID) REFERENCES Biler(BilID)
);


INSERT INTO Biler (MaerkeModelbiler, BraendstofType, Registreringsnummer, RegistreringDATO, KoeretoejKilometer) VALUES
('Mercedes S-Class', 'Benzin', 'ABC123', '2022-01-01', 5000),
('Toyota Sienna', 'Benzin', 'XYZ789', '2022-02-01', 3000),
('Porsche 911', 'Benzin', 'DEF456', '2022-03-01', 1000);

INSERT INTO BilGrupper (GruppeNavn) VALUES 
('Luksus'),
('Familie'),
('Sport');

INSERT INTO Lejere (Navn, Adresse, PostnummerID, ByNavn, Mobiltelefon, Telefon, Email, KoerekortNummer, KoerekortUdstedelsesdato) VALUES
('John Doe', '123 Main St', 1, 'Cityville', '1234567890', '0987654321', 'john@example.com', 'ABC12345', '2020-01-01'),
('Jane Smith', '456 Oak St', 2, 'Townsville', '9876543210', '0123456789', 'jane@example.com', 'XYZ54321', '2019-12-01');

INSERT INTO Postnumre (Postnummer) VALUES 
('12345'),
('54321');

INSERT INTO Lejekontrakter (LejerID, BilID, FraDatoTid, TilDatoTid, MaxKilometer, StartKilometer) VALUES 
(1, 1, '2022-04-01 12:00:00', '2022-04-10 12:00:00', 1000, 100),
(2, 2, '2022-05-01 14:00:00', '2022-05-15 14:00:00', 1500, 200);