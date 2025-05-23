# HiringGo-Course

# Implementasi High-Level Networking : REST API dengan Spring Boot

Proyek ini menggunakan **REST API berbasis Spring Boot** untuk membangun komunikasi jaringan tingkat tinggi (High-Level Networking). Berikut adalah beberapa alasan utama mengapa saya memilih teknologi ini:

## 1. **Mudah Dikembangkan**

Spring Boot menyediakan pengaturan otomatis dan konfigurasi minimal untuk membangun aplikasi berbasis REST API. Dengan menggunakan Spring Boot, pengembangan aplikasi menjadi lebih cepat dan efisien, karena sebagian besar konfigurasi sudah tersedia dan mudah disesuaikan. Penggunaan Spring Boot juga memudahkan dalam pemeliharaan dan pengembangan aplikasi ke depannya.

## 2. **Aman (Otorisasi Berbasis Role)**

Keamanan merupakan prioritas utama dalam pengembangan aplikasi modern. Dengan menggunakan Spring Security yang terintegrasi dengan Spring Boot, saya dapat dengan mudah menambahkan otorisasi berbasis role. Ini memastikan bahwa hanya pengguna yang berwenang yang dapat mengakses endpoint tertentu dalam aplikasi, meningkatkan keamanan dan mencegah akses yang tidak sah.

## 3. **Dapat Diuji**

REST API yang dibangun dengan Spring Boot mendukung pengujian otomatis dengan alat seperti JUnit dan Mockito. Spring Boot menyediakan framework pengujian yang kuat untuk memastikan bahwa setiap bagian dari aplikasi dapat diuji dengan mudah, mulai dari controller hingga lapisan layanan dan repositori. Ini membantu meningkatkan kualitas dan stabilitas aplikasi selama pengembangan.

## 4. **Scalable dan Fleksibel**

REST API dengan Spring Boot sangat scalable dan fleksibel, memungkinkan aplikasi untuk menangani volume permintaan yang besar. Dengan arsitektur berbasis REST, aplikasi dapat dengan mudah berinteraksi dengan berbagai platform dan layanan lain, serta mendukung kebutuhan untuk skalabilitas horizontal (menambah lebih banyak server) tanpa kesulitan.

## Kesimpulan

Dengan menggunakan Spring Boot dan REST API, saya membangun aplikasi yang mudah dikembangkan, aman, dapat diuji, serta scalable dan fleksibel. Teknologi ini membantu dalam menciptakan solusi jaringan yang efisien dan dapat dengan mudah beradaptasi dengan kebutuhan aplikasi yang berkembang.

## Code Diagram Course
![Code Diagram Course ](image/CodeDiagramCourse-.drawio.png)
## Component Diagram Course
![Component Diagram Course ](image/ComponentDiagramCourse.drawio.png)


## Alasan Penggunaan Asynchronous Programming

Pada proyek ini, saya mulai menerapkan **Asynchronous Programming** pada endpoint `GET /admin/matakuliah/async`, dengan memanfaatkan anotasi `@Async` dan `CompletableFuture` dari Spring Framework. Berikut adalah alasan mengapa pendekatan ini digunakan:

### 1. Meningkatkan Responsivitas Aplikasi

Asynchronous programming memungkinkan sistem untuk **menjalankan proses lain tanpa harus menunggu proses saat ini selesai**. Dalam konteks ini, saat client meminta data mata kuliah, thread utama tidak perlu “diam” menunggu hasil dari database—sehingga sistem tetap responsif terhadap permintaan lainnya.

### 2. Efisiensi Resource Server

Dengan menggunakan asynchronous method (`CompletableFuture`), saya bisa **menghindari blocking pada thread pool**, yang sangat penting ketika jumlah pengguna atau permintaan meningkat. Hal ini membuat aplikasi lebih hemat resource dan siap untuk skala yang lebih besar.

### 3. Cocok untuk Sistem Real-Time dan Modern

Asynchronous programming adalah **standar di sistem modern**, termasuk aplikasi real-time seperti sistem notifikasi, chat, atau live update. Meskipun belum digunakan untuk fitur tersebut saat ini, dengan memulai menggunakan pendekatan async, sistem jadi lebih siap untuk integrasi fitur-fitur tersebut di masa depan.

### 4. Mendukung Pengujian dan Pengembangan Modular

Dengan membungkus hasil dalam `CompletableFuture`, fungsi menjadi **lebih mudah diuji dan lebih modular**, serta bisa diproses lebih fleksibel dalam pipeline asynchronous lainnya.

### 5. Implementasi Sederhana dengan Dampak Besar

Spring Boot menyediakan dukungan native terhadap asynchronous programming melalui `@Async`, sehingga implementasinya relatif sederhana namun memberikan **manfaat besar terhadap performa dan skalabilitas sistem**.




