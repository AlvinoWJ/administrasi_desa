# Aplikasi Administrasi Desa Mandiri

Aplikasi ini merupakan sistem administrasi untuk desa yang dirancang agar dapat membantu dalam pengelolaan dan pelayanan administrasi kepada masyarakat secara mandiri.  
Dibangun menggunakan **Java** dengan pendekatan **OOP (Object-Oriented Programming)**, aplikasi ini memudahkan pembuatan, pencetakan, dan pengarsipan surat-surat penting desa.

## ✨ Fitur Utama
- **CRUD Data Surat**  
  Menambahkan, mengubah, menghapus, dan melihat data surat.
- **Jenis Surat yang Didukung**
  - Surat Keterangan Usaha (SKU)
  - Surat Keterangan Tidak Mampu (SKTM)
  - Surat Keterangan Kepemilikan Tanah
  - Surat Keterangan Kepemilikan Rumah
  - Surat Domisili
- **Nomor Surat Otomatis**  
  Nomor surat digenerate secara otomatis dengan format seperti: `473/V/suket/2025`.
- **Pencetakan Surat**  
  Preview dan cetak surat langsung dari aplikasi.
- **Penyimpanan Data di Database**  
  Menggunakan MySQL untuk menyimpan seluruh data administrasi.

## 🛠️ Teknologi yang Digunakan
- **Bahasa Pemrograman**: Java
- **GUI**: Java Swing
- **Database**: MySQL
- **JDBC Connector**: MySQL Connector/J
- **IDE yang Direkomendasikan**: NetBeans / IntelliJ IDEA / VS Code

## 📦 Instalasi & Menjalankan Aplikasi

### 1. Clone Repository
    git clone https://github.com/AlvinoWJ/administrasi_desa.git

### 2️. Import Project ke IDE
  1. Buka IDE pilihan Anda (NetBeans, IntelliJ IDEA, atau VS Code).
  2. Pilih **Open Project** atau **Import Project** dari menu utama.
  3. Arahkan ke folder hasil clone repository.
  4. Pastikan struktur folder seperti berikut:

    src/
      ├── config/
      ├── controller/
      ├── dao/
      ├── model/
      ├── utils/
      └── view/
    lib/
      └── mysql-connector-java-9.2.0.jar
    db/
      └── database.sql

### 3. Setup Database
  1. Jalankan MySQL di komputer Anda (XAMPP, Laragon, atau MySQL Server).
  2. Buka phpMyAdmin atau MySQL CLI.
  3. Buat database baru:

    CREATE DATABASE administrasi_desa;
  4. Import file SQL:

    mysql -u root -p administrasi_desa < db/database.sql
  5. Sesuaikan konfigurasi koneksi database di:

    src/config/DatabaseConfig.java

## 📸 Tampilan Aplikasi
<img width="1920" height="1080" alt="Screenshot 2025-08-11 081432" src="https://github.com/user-attachments/assets/52dd88a4-34cf-4aa3-82fd-ab137cb68989" />

### 1. User
<img width="1920" height="1080" alt="Screenshot 2025-08-13 085534" src="https://github.com/user-attachments/assets/09dcdb8d-7584-4313-8f0b-24d5889d728d" />

<img width="1920" height="1080" alt="Screenshot 2025-08-13 085805" src="https://github.com/user-attachments/assets/2d8eba28-d4c7-4598-9c97-30607a37413a" />

### 2. Admin
<img width="1920" height="1080" alt="Screenshot 2025-08-13 085545" src="https://github.com/user-attachments/assets/84b231e9-fc3d-40b6-9f53-1fe656351f7c" />

<img width="1920" height="1080" alt="Screenshot 2025-08-13 085647" src="https://github.com/user-attachments/assets/1f93ab07-b5ac-44d1-b671-28042c679d31" />

<img width="1920" height="1080" alt="Screenshot 2025-08-13 085855" src="https://github.com/user-attachments/assets/214a4c5b-b12a-48e1-bfeb-4a9460eb0dac" />

<img width="1920" height="1080" alt="Screenshot 2025-08-13 085907" src="https://github.com/user-attachments/assets/559a81c8-d576-47b5-97d5-1edb8a19f012" />


<img width="1920" height="1080" alt="Screenshot 2025-08-13 085914" src="https://github.com/user-attachments/assets/4fb63146-0d8b-4cc2-9476-29a0988e76dc" />

<img width="1920" height="1080" alt="Screenshot 2025-08-13 085919" src="https://github.com/user-attachments/assets/dca47f11-a3ce-41d4-b247-b5d9cd755e69" />
