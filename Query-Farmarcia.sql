Create database Farmacia;
use Farmacia;

-- Creacion de la tabla productos e ingreso de registros
CREATE TABLE productos(
	CodProd INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NomProd VARCHAR(45) NOT NULL,
	DesProd VARCHAR(80) NOT NULL,
	StoProd INT NOT NULL,
    ValVentProd DECIMAL(10,2) NOT NULL,
    IvaProd TINYINT NOT NULL,
    RucProvProd VARCHAR(30) NOT NULL
) AUTO_INCREMENT = 1;
INSERT INTO productos (NomProd, DesProd, StoProd, ValVentProd, IvaProd, RucProvProd)
VALUES
    ('Amoxicilina 500mg', 'Cápsulas de Amoxicilina, 500mg', 100, 4.25, 12, 'PharmaCorp'),
    ('Loratadina 10mg', 'Tabletas de Loratadina, 10mg', 300, 6.99, 12, 'MediPharm'),
    ('Omeprazol 20mg', 'Cápsulas de Omeprazol, 20mg', 250, 7.50, 12, 'MediPharm'),
    ('Cetirizina 5mg', 'Tabletas de Cetirizina, 5mg', 200, 5.25, 12, 'PharmaCorp'),
    ('Aspirina 100mg', 'Tabletas de Aspirina, 100mg', 500, 1.99, 12, 'GenericoMeds'),
    ('Ibuprofeno 200mg', 'Tabletas de Ibuprofeno, 200mg', 400, 3.25, 12, 'PharmaCorp'),
    ('Paracetamol 1000mg', 'Tabletas de Paracetamol, 1000mg', 150, 3.99, 12, 'MediPharm'),
    ('Ranitidina 150mg', 'Tabletas de Ranitidina, 150mg', 300, 4.75, 12, 'PharmaCorp'),
    ('Simvastatina 20mg', 'Tabletas de Simvastatina, 20mg', 200, 9.50, 12, 'MediPharm'),
    ('Metformina 500mg', 'Tabletas de Metformina, 500mg', 350, 6.25, 12, 'PharmaCorp'),
    ('Clorfeniramina 4mg', 'Tabletas de Clorfeniramina, 4mg', 100, 2.99, 12, 'GenericoMeds'),
    ('Enalapril 10mg', 'Tabletas de Enalapril, 10mg', 120, 7.25, 12, 'MediPharm'),
    ('Dexametasona 0.5mg', 'Tabletas de Dexametasona, 0.5mg', 180, 5.75, 12, 'PharmaCorp'),
    ('Losartan 50mg', 'Tabletas de Losartan, 50mg', 250, 8.99, 12, 'MediPharm'),
    ('Salbutamol Inhalador', 'Inhalador de Salbutamol', 80, 12.99, 12, 'PharmaCorp'),
    ('Atorvastatina 10mg', 'Tabletas de Atorvastatina, 10mg', 150, 11.25, 12, 'MediPharm'),
    ('Paroxetina 20mg', 'Tabletas de Paroxetina, 20mg', 100, 15.50, 12, 'PharmaCorp'),
    ('Furosemida 40mg', 'Tabletas de Furosemida, 40mg', 200, 4.75, 12, 'GenericoMeds'),
    ('Diclofenac 50mg', 'Tabletas de Diclofenac, 50mg', 300, 6.99, 12, 'MediPharm'),
    ('Levotiroxina 50mcg', 'Tabletas de Levotiroxina, 50mcg', 150, 9.25, 12, 'PharmaCorp');
    
SELECT * FROM productos;
-- SELECT * FROM productos WHERE NomProd LIKE '%detergente%';
-- SELECT CodProd, NomProd, DesProd, StoProd, ValVentProd FROM productos;
-- SELECT CodProd, DesProd, ValVentProd FROM productos WHERE CodProd = 1;
-- SELECT MAX(CodProd) FROM productos;

CREATE TABLE cajeros(
	IdCaj INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NomCaj VARCHAR(20) NOT NULL,
	ApeCaj VARCHAR(20) NOT NULL,
    DirCaj VARCHAR(80) ,
    CorCaj VARCHAR(30) NOT NULL,
    TelCaj VARCHAR(12) NOT NULL,
	UsuCaj VARCHAR(15) NOT NULL,
	ConCaj VARCHAR(20) NOT NULL
) AUTO_INCREMENT=1;
select * from cajeros;
INSERT INTO cajeros (NomCaj, ApeCaj, DirCaj, CorCaj, TelCaj, UsuCaj, ConCaj)
VALUES
('Pablo','Arroyo','Quito, Miravalle 1, Carlos Dousdebes','pablo.arroyo@onixfarm.com','0998151451','cj1','cj1'),
('Maria','Fernandez','Quito, Centro Historico','maria.fernandez@onixfarm.com','0987127781','cj2','cj2'),
('Domenica','Lamar','Quito, Cumbaya, parque central','domenica.lamar@onixfarm.com','0992344242','cj3','cj3'),
('Michael','Ramirez','Quito, La Tola','michael.ramirez@onixfarm.com','0983451562','cj4','cj4');
SELECT * FROM cajeros;
-- SELECT UsuCaj, ConCaj, CONCAT(NomCaj,' ',ApeCaj), CorCaj FROM cajeros WHERE UsuCaj LIKE 'cj1';
-- SELECT UsuCaj, ConCaj, IdCaj FROM cajeros WHERE UsuCaj LIKE 'cj1';

-- Creacion de tabla cabeceras de facturas
CREATE TABLE cabecerasFacturas(
	NumFact INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    IdCajFact VARCHAR(10) NOT NULL REFERENCES cajeros(IdCaj),
	NomCliFact VARCHAR(60) NOT NULL,
	FecEmiFact DATE NOT NULL,
	SubFact DECIMAL(10,2),
    IvaFact DECIMAL(10,2),
    TotFact DECIMAL(10,2) 
);
INSERT INTO cabecerasFacturas (IdCajFact, NomCliFact, FecEmiFact, SubFact, IvaFact, TotFact)
VALUES
(1,'Juan Perez','2020-02-15',NULL,NULL,NULL),
(1,'Steven Sanchez','2020-03-17',NULL,NULL,NULL),
(1,'Ronald Guaman','2020-02-10',NULL,NULL,NULL),
(2,'Cristian Cevallos','2020-03-15',NULL,NULL,NULL);
-- SELECT * FROM cabecerasFacturas;
-- SELECT MAX(NumFact) FROM cabecerasFacturas;

-- Creacion de detalles de factura
CREATE TABLE detallesFacturas(
	CodDet INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NumFactDet INT NOT NULL REFERENCES cabecerasFacturas(NumFact),
	CodProdDet INT NOT NULL REFERENCES productos(CodProd),
	CanDet INT NOT NULL,
    ValVenDet DECIMAL(10,2) NOT NULL REFERENCES productos(ValVentProd),
    TotDet DECIMAL(10,2) 
);
INSERT INTO detallesFacturas (NumFactDet, CodProdDet, CanDet, ValVenDet, TotDet)
VALUES
(1,1,1,9.43,9.43),
(1,2,1,15.43,15.43),
(1,3,1,1.47,1.47),
(2,4,3,3.95,11.85);
-- SELECT * FROM detallesFacturas;

CREATE TABLE administradores(
	IdAdm INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NomAdm VARCHAR(20) NOT NULL,
	ApeAdm VARCHAR(20) NOT NULL,
    DirAdm VARCHAR(80) ,
    CorAdm VARCHAR(30) NOT NULL,
    TelAdm VARCHAR(12) NOT NULL,
	UsuAdm VARCHAR(15) NOT NULL,
	ConAdm VARCHAR(20) NOT NULL
);
INSERT INTO administradores (NomAdm, ApeAdm, DirAdm, CorAdm, TelAdm, UsuAdm, ConAdm)
VALUES
('Jesus','Colcha','Quito, Tumbaco','jesus.colcha@onixfarm.com','0992387466','admin1','admin1'),
('Cristhian','Gomez','Quito, Pifo','cristhian.gomez@onixfarm.com','0985353412','admin2','admin2');
-- USE minimarketearf
SELECT * FROM administradores;
-- SELECT UsuAdm, ConAdm FROM administradores WHERE UsuAdm LIKE 'admin1';
