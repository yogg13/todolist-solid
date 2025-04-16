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
┌─────────────┐     ┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│             │     │              │     │              │     │              │
│   TodoCLI   │────▶│ TodoService │────▶│   TodoDAO    │────▶│   Database  │
│  (View)     │     │ (Business)   │     │  (Data)      │     │   (MySQL)    │
│             │     │              │     │              │     │              │
└─────────────┘     └──────────────┘     └──────────────┘     └──────────────┘
       ▲                  ▲                   ▲                    ▲
       │                  │                   │                    │
       │                  │                   │                    │
┌──────┴──────┐     ┌─────┴──────┐      ┌─────┴──────┐      ┌──────┴─────┐
│  Interface  │     │  Service   │      │    DAO     │      │  Database  │
│   Layer     │     │   Layer    │      │   Layer    │      │  Config    │
└─────────────┘     └────────────┘      └────────────┘      └────────────┘
    TodoCLI.java     TodoService.java     TodoDao.java        MySqlConfig.java
                    TodoServiceImpl.java   TodoDaoJdbc.java    DatabaseConfig.java
                                         TodoReader.java      DatabaseConnection.java
                                         TodoWriter.java

┌─────────────────────────────────────────────────────────────────┐
│                        TodoFactory (DI Container)               │
└─────────────────────────────────────────────────────────────────┘
```

### Penjelasan Komponen Arsitektur

1. **Interface Layer (Presentation Layer)**

   - Komponen: `TodoCLI`
   - Fungsi:
     - Menangani interaksi dengan pengguna
     - Menampilkan menu dan opsi
     - Meneruskan input pengguna ke Service Layer
   - Implementasi SOLID: Single Responsibility Principle

2. **Service Layer (Business Layer)**

   - Komponen: `TodoService`, `TodoServiceImpl`
   - Fungsi:
     - Implementasi logika bisnis
     - Validasi data
     - Koordinasi antara Interface dan DAO Layer
   - Implementasi SOLID: Open/Closed Principle, Liskov Substitution

3. **DAO Layer (Data Access Layer)**

   - Komponen: `TodoDao`, `TodoDaoJdbc`, `TodoReader`, `TodoWriter`
   - Fungsi:
     - Menangani operasi CRUD ke database
     - Mengkonversi data antara objek dan database
   - Implementasi SOLID: Interface Segregation, Dependency Inversion

4. **Database Configuration**

   - Komponen: `DatabaseConfig`, `MySqlConfig`, `DatabaseConnection`
   - Fungsi:
     - Konfigurasi koneksi database
     - Manajemen koneksi database
   - Implementasi SOLID: Dependency Inversion

5. **Model**

   - Komponen: `Todo`
   - Fungsi:
     - Representasi data
     - Enkapsulasi properti todo

6. **Factory (Dependency Injection)**
   - Komponen: `TodoFactory`
   - Fungsi:
     - Inisialisasi dan konfigurasi komponen
     - Menghubungkan dependencies antar layer
   - Implementasi SOLID: Dependency Inversion

### Alur Data (Data Flow)

1. **User Input Flow**

   ```
   User → TodoCLI → TodoService → TodoDao → Database
   ```

   - User memberikan input melalui CLI
   - TodoCLI memproses input dan memanggil TodoService
   - TodoService menjalankan logika bisnis
   - TodoDao melakukan operasi database

2. **Data Output Flow**
   ```
   Database → TodoDao → TodoService → TodoCLI → User
   ```
   - Data diambil dari database melalui TodoDao
   - TodoService memproses data sesuai kebutuhan
   - TodoCLI menampilkan hasil ke user

### Keuntungan Arsitektur

1. **Modular dan Terstruktur**

   - Setiap layer memiliki tanggung jawab yang jelas
   - Mudah untuk maintenance dan testing
   - Perubahan pada satu layer tidak mempengaruhi layer lain

2. **Loose Coupling**

   - Implementasi SOLID principles
   - Interface-based programming
   - Dependency injection melalui TodoFactory

3. **Extensible**
   - Mudah menambah fitur baru
   - Mudah mengganti implementasi (misalnya database)
   - Open untuk ekstensi, closed untuk modifikasi

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
