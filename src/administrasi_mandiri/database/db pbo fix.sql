`db_sistem_pelayanan_desa`

-- TABEL USER 
-- CREATE
CREATE TABLE USER (
    id_user INT PRIMARY KEY,
    nama VARCHAR(45),
    alamat VARCHAR(45),
    email VARCHAR(45)
);

-- INSERT
INSERT INTO `User` (id_user, nama, alamat, email) 
VALUES (1, 'Nama Lengkap', 'Alamat lengkap', 'email@example.com');

-- SELECT semua data
SELECT * FROM `User`;

-- SELECT 
SELECT * FROM `User` WHERE id_user = 1;

-- UPDATE data 
UPDATE `User` 
SET nama = 'Nama Baru', alamat = 'Alamat Baru', email = 'baru@example.com' 
WHERE id_user = 1;

-- DELETE data 
DELETE FROM `User` WHERE id_user = 1;

-- ALTER tabel
ALTER TABLE `User` ADD no_telepon VARCHAR(20);

-- DROP tabel 
DROP TABLE `User`;


-- TABEL ADMIN

-- CREATE
CREATE TABLE Admin (
    id_adm INT PRIMARY KEY,
    nama VARCHAR(45),
    jabatan VARCHAR(45)
);

-- INSERT (template tanpa data nyata)
INSERT INTO `Admin` (id_adm, nama, jabatan) 
VALUES (0, '', '');

-- SELECT semua data
SELECT * FROM `Admin`;

-- SELECT dengan kondisi
SELECT * FROM `Admin` WHERE id_adm = 0;

-- UPDATE data 
UPDATE `Admin`
SET nama = '', jabatan = ''
WHERE id_adm = 0;

-- DELETE data 
DELETE FROM `Admin`
WHERE id_adm = 0;

-- ALTER tabel
ALTER TABLE `Admin` ADD no_telp VARCHAR(20);

-- DROP tabel 
DROP TABLE `Admin`;


-- TABEL SURAT

-- CREATE
CREATE TABLE Surat (
    id_surat INT PRIMARY KEY,
    jenis_surat VARCHAR(45),
    STATUS VARCHAR(45),
    tanggal_pengajuan DATE,
    catatan VARCHAR(45)
);

-- INSERT 
INSERT INTO `Surat` (id_surat, jenis_surat, STATUS, tanggal_pengajuan, catatan)
VALUES (0, '', '', NULL, '');

-- SELECT semua data
SELECT * FROM `Surat`;

-- SELECT dengan kondisi
SELECT * FROM `Surat` WHERE STATUS = 'Pending';

-- UPDATE status surat
UPDATE `Surat`
SET STATUS = 'Selesai'
WHERE id_surat = 0;

-- DELETE data
DELETE FROM `Surat` WHERE id_surat = 0;

-- ALTER TABLE
ALTER TABLE `Surat` ADD lampiran_url VARCHAR(100);

-- DROP tabel
DROP TABLE `Surat`;



-- TABEL DOKUMEN_PENDUKUNG

-- CREATE
CREATE TABLE Dokumen_Pendukung (
    id_dokum INT PRIMARY KEY,
    jenis_dokumen VARCHAR(45),
    file_url VARCHAR(45)
);

-- INSERT
INSERT INTO `Dokumen_Pendukung` (id_dokum, jenis_dokumen, file_url) 
VALUES (0, '', '');

-- SELECT
SELECT * FROM Dokumen_Pendukung;

-- SELECT WHERE
SELECT * FROM Dokumen_Pendukung WHERE jenis_dokumen = 'KTP';

-- UPDATE
UPDATE Dokumen_Pendukung SET file_url = '' WHERE id_dokum = 0;

-- DELETE
DELETE FROM Dokumen_Pendukung WHERE id_dokum = 0;

-- ALTER
ALTER TABLE Dokumen_Pendukung ADD tanggal_upload DATE;

-- DROP
DROP TABLE Dokumen_Pendukung;


-- TABEL RIWAYAT_PENGAJUAN

-- CREATE
CREATE TABLE Riwayat_Pengajuan (
    id_riwayat INT PRIMARY KEY,
    id_user INT,
    id_surat INT,
    waktu_pengajuan VARCHAR(45)
);

-- INSERT
INSERT INTO Riwayat_Pengajuan (id_riwayat, id_user, id_surat, waktu_pengajuan)
VALUES (0, '', '', NULL);

-- SELECT
SELECT * FROM Riwayat_Pengajuan;

-- SELECT WHERE
SELECT * FROM Riwayat_Pengajuan WHERE id_user = 0;

-- UPDATE
UPDATE Riwayat_Pengajuan SET waktu_pengajuan = '' WHERE id_riwayat = 0;

-- DELETE
DELETE FROM Riwayat_Pengajuan WHERE id_riwayat = 0;

-- ALTER
ALTER TABLE Riwayat_Pengajuan ADD status_pengajuan VARCHAR(45);

-- DROP
DROP TABLE Riwayat_Pengajuan;


-- TABEL VERIFIKASI

-- CREATE TABLE 
DROP TABLE IF EXISTS Verifikasi;

CREATE TABLE Verifikasi (
    id_verifikasi INT PRIMARY KEY,
    id_surat DATE,
    id_adm INT,
    tanggal_verifikasi DATE,
    status_verifikasi VARCHAR(45),
    catatan VARCHAR(45)
);

-- INSERT 
INSERT INTO Verifikasi (id_verifikasi, id_surat, id_adm, tanggal_verifikasi, status_verifikasi, catatan)
VALUES (0, NULL, 0, NULL, '', NULL);

-- SELECT semua data dari tabel
SELECT * FROM Verifikasi;

-- SELECT dengan kondisi 
SELECT * FROM Verifikasi WHERE status_verifikasi = '';

-- UPDATE 
UPDATE Verifikasi
SET status_verifikasi = ''
WHERE id_verifikasi = 0;

-- DELETE 
DELETE FROM Verifikasi WHERE id_verifikasi = 0;

-- ALTER TABLE 
ALTER TABLE Verifikasi ADD waktu_verifikasi_baru TIME;

-- DROP TABLE Verifikasi
DROP TABLE Verifikasi;
