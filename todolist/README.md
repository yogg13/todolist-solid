# Todo List Application

Aplikasi Todo List sederhana yang diimplementasikan dengan menerapkan prinsip SOLID dalam Java.

## Struktur Project

```
todolist/
├── pom.xml                 # File konfigurasi Maven untuk mengelola dependencies
├── src/
│   ├── main/              # Folder utama source code
│   │   ├── java/         # Kode Java
│   │   │   └── com/
│   │   │       └── example/
│   │   └── resources/    # Resource seperti konfigurasi, properties, dll
│   └── test/             # Folder untuk unit test
│       └── java/
```

## Penjelasan Flow dari Package dan File

### 1. Package `com.example.config`

#### DatabaseConfig.java (Interface)

- Mendefinisikan kontrak untuk konfigurasi database
- Menerapkan Interface Segregation Principle (ISP)
- Methods:
  - `getUrl()`
  - `getUsername()`
  - `getPassword()`
  - `getDriverClassName()`

#### MySqlConfig.java

- Implementasi konkrit dari `DatabaseConfig`
- Menyediakan konfigurasi spesifik untuk MySQL
- Menerapkan Single Responsibility Principle (SRP)

### 2. Package `com.example.database`

#### DatabaseConnection.java

- Mengelola koneksi ke database
- Menerapkan Single Responsibility Principle
- Menggunakan DatabaseConfig untuk mendapatkan parameter koneksi

### 3. Package `com.example.model`

#### Todo.java

- Model data untuk Todo item
- Menerapkan Single Responsibility Principle
- Properties:
  - id
  - title
  - description
  - completed

### 4. Package `com.example.dao`

#### TodoReader.java (Interface)

- Kontrak untuk operasi pembacaan Todo
- Methods:
  - `findById()`
  - `findAll()`

#### TodoWriter.java (Interface)

- Kontrak untuk operasi penulisan Todo
- Methods:
  - `create()`
  - `update()`
  - `delete()`

#### TodoDao.java (Interface)

- Menggabungkan TodoReader dan TodoWriter
- Menerapkan Interface Segregation Principle

#### TodoDaoJdbc.java

- Implementasi TodoDao menggunakan JDBC
- Menerapkan SRP dan Liskov Substitution Principle (LSP)
- Bertanggung jawab untuk operasi database

### 5. Package `com.example.service`

#### TodoService.java (Interface)

- Mendefinisikan kontrak layanan bisnis
- Menerapkan Open/Closed Principle (OCP)

#### TodoServiceImpl.java

- Implementasi TodoService
- Menerapkan SRP dan LSP
- Menggunakan TodoDao untuk operasi database

### 6. Package `com.example.view`

#### TodoCLI.java

- Antarmuka Command Line Interface
- Menerapkan Single Responsibility Principle
- Menggunakan TodoService untuk operasi bisnis

### 7. Package `com.example.factory`

#### TodoFactory.java

- Menerapkan Factory Pattern
- Bertanggung jawab untuk membuat instance dari komponen sistem
- Menerapkan Dependency Injection

## Prinsip SOLID yang Diterapkan

1. **Single Responsibility Principle (SRP)**

   - Setiap kelas memiliki satu tanggung jawab
   - Contoh: TodoCLI hanya menangani interaksi user, TodoDaoJdbc hanya menangani operasi database

2. **Open/Closed Principle (OCP)**

   - Kelas terbuka untuk ekstensi, tertutup untuk modifikasi
   - Contoh: Interface TodoService memungkinkan implementasi baru tanpa mengubah kode yang ada

3. **Liskov Substitution Principle (LSP)**

   - Subclass dapat menggantikan base class
   - Contoh: TodoDaoJdbc bisa menggantikan TodoDao tanpa mengubah behavior

4. **Interface Segregation Principle (ISP)**

   - Interface dipecah menjadi bagian yang lebih kecil dan spesifik
   - Contoh: Pemisahan TodoReader dan TodoWriter dari TodoDao

5. **Dependency Inversion Principle (DIP)**
   - Bergantung pada abstraksi, bukan implementasi konkrit
   - Contoh: TodoService bergantung pada interface TodoDao, bukan implementasi konkritnya

## Alur Kerja Aplikasi

1. **Inisialisasi**

   - Main.java membuat TodoFactory
   - TodoFactory membuat semua komponen yang diperlukan
   - TodoCLI diinisialisasi dengan TodoService

2. **Operasi Database**

   ```
   TodoCLI → TodoService → TodoDao → DatabaseConnection → Database
   ```

   - Input user dari TodoCLI diteruskan ke TodoService
   - TodoService memproses logika bisnis dan memanggil TodoDao
   - TodoDao melakukan operasi database melalui DatabaseConnection

3. **Dependency Injection**
   - TodoFactory bertanggung jawab untuk membuat dan menghubungkan komponen
   - Setiap komponen menerima dependencies melalui constructor
   - Contoh: TodoServiceImpl menerima TodoDao, TodoCLI menerima TodoService

## Fitur Aplikasi

1. **Manajemen Todo**

   - Menambah todo baru
   - Melihat semua todo
   - Menandai todo sebagai selesai
   - Mengupdate todo
   - Menghapus todo

2. **Database Integration**
   - Menggunakan MySQL sebagai database
   - Koneksi database yang aman dan efisien
   - Operasi CRUD yang terstruktur

## Arsitektur Sistem

```
+-------------------+     +-------------------+     +------------------+
|                   |     |                   |     |                  |
|     TodoCLI       |     |   TodoService     |     |     TodoDao      |
|    (View Layer)   |     | (Business Layer)  |     |   (Data Layer)   |
|                   |     |                   |     |                  |
+--------+----------+     +---------+---------+     +---------+--------+
         |                          |                         |
         |                          |                         |
         |                          |                         |
         |                          |                         |
    User Input                Business Logic            Data Access
         |                          |                         |
         |                          |                         |
         v                          v                         v
+------------------+      +-------------------+     +-------------------+
|                  |      |                   |     |                   |
|   User Interface |      | Data Validation   |     |    Database      |
|   Input/Output   |      | Business Rules    |     |    Operations    |
|                  |      |                   |     |                   |
+------------------+      +-------------------+     +-------------------+
         |                          |                         |
         |                          |                         |
         |                          |                         |
         v                          v                         v
+----------------------------------------------------------|
|                                                           |
|                      MySQL Database                       |
|                                                           |
+-----------------------------------------------------------+

```

### Penjelasan Komponen Arsitektur

1. **View Layer (Presentation Layer)**

   - Implementasi: `TodoCLI.java`
   - Tanggung jawab:
     - Menampilkan interface ke user
     - Menerima input user
     - Menampilkan output/hasil
   - Prinsip SOLID: Single Responsibility Principle

2. **Service Layer (Business Layer)**

   - Implementasi: `TodoService.java`, `TodoServiceImpl.java`
   - Tanggung jawab:
     - Implementasi business logic
     - Validasi data
     - Koordinasi antara View dan Data layer
   - Prinsip SOLID: Open/Closed Principle, Liskov Substitution Principle

3. **Data Access Layer**

   - Implementasi: `TodoDao.java`, `TodoDaoJdbc.java`
   - Interface: `TodoReader.java`, `TodoWriter.java`
   - Tanggung jawab:
     - Operasi database (CRUD)
     - Mapping data antara aplikasi dan database
   - Prinsip SOLID: Interface Segregation Principle

4. **Model**

   - Implementasi: `Todo.java`
   - Tanggung jawab:
     - Representasi data
     - Enkapsulasi properti todo

5. **Database Configuration**

   - Implementasi: `DatabaseConfig.java`, `MySqlConfig.java`
   - Tanggung jawab:
     - Konfigurasi koneksi database
     - Abstraksi detail koneksi

6. **Factory**
   - Implementasi: `TodoFactory.java`
   - Tanggung jawab:
     - Object creation
     - Dependency injection
   - Prinsip SOLID: Dependency Inversion Principle

### Alur Data (Data Flow)

1. User berinteraksi dengan `TodoCLI`
2. `TodoCLI` meneruskan request ke `TodoService`
3. `TodoService` memproses business logic
4. `TodoDao` melakukan operasi database
5. Data mengalir kembali ke user melalui jalur yang sama

### Keuntungan Arsitektur

1. **Loose Coupling**

   - Setiap layer independen dan dapat dimodifikasi tanpa mempengaruhi layer lain
   - Memudahkan maintenance dan testing

2. **High Cohesion**

   - Setiap komponen memiliki tanggung jawab yang jelas dan terfokus
   - Implementasi SOLID principles yang konsisten

3. **Scalability**

   - Mudah untuk menambah fitur baru
   - Mudah untuk mengganti implementasi (misalnya mengubah database)

4. **Maintainability**
   - Kode terorganisir dengan baik
   - Mudah untuk debug dan troubleshoot

## Requirements

- Java Development Kit (JDK)
- MySQL Database
- Maven

## Setup Project

1. **Database Setup**

   - Buat database MySQL dengan nama `todolist-sol`
   - Sesuaikan konfigurasi database di `MySqlConfig.java`

2. **Build dan Run**
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="com.example.Main"
   ```

## Catatan Pengembangan

- Project ini menerapkan prinsip-prinsip SOLID untuk memastikan kode yang maintainable
- Menggunakan pattern Factory untuk Dependency Injection
- Interface yang terpecah memungkinkan fleksibilitas dalam pengembangan
- Error handling yang konsisten di semua layer
