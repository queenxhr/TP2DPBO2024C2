# TP2DPBO2024C2

# Janji
Saya Ratu Syahirah Khairunnisa [2200978] 
mengerjakan Tugas Praktikum 2
dalam mata kuliah DPBO
untuk keberkahanNya maka saya tidak melakukan kecurangan 
seperti yang telah dispesifikasikan. 
Aamiin

# Desain Program :
## Perintah :
## Lanjutkan program LP5 yang sudah kamu buat, lalu tambahkan koneksi dengan database MySQL. Ubah program dengan spesifikasi sebagai berikut:
## 1. Hubungkan semua proses CRUD dengan database.
## 2. Tampilkan dialog/prompt error jika masih ada input yang kosong saat insert/update.
## 3. Tampilkan dialog/prompt error jika sudah ada NIM yang sama saat insert.
---
Pada program ini terdapat 4 file, yaitu :
1. `Menu.java`: Kelas utama yang berfungsi sebagai frame utama aplikasi.
2. `Mahasiswa.java`: Kelas yang merepresentasikan objek mahasiswa.
3. `Menu.form` : untuk mengubah tampilan dengan menambah atau mengubah component.
4. `Database.java` : Kelas untuk menghubungkan program dengan file database.
---
### Menghubungkan CRUD dengan Database:

1. **Insert Data**: Saat melakukan operasi penambahan data, program akan mengambil nilai yang dimasukkan oleh pengguna melalui antarmuka pengguna (GUI), seperti NIM, nama, jenis kelamin, dan angkatan. Data tersebut akan dijalankan melalui proses validasi dan kemudian dimasukkan ke dalam database menggunakan perintah query `INSERT INTO`. Proses ini menghubungkan antarmuka pengguna dengan database, sehingga memungkinkan penyimpanan data yang diberikan oleh pengguna ke dalam basis data yang terkait.

2. **Update Data**: Ketika pengguna ingin memperbarui data, program akan mengambil nilai baru yang dimasukkan melalui antarmuka pengguna dan menggunakan query `UPDATE` untuk memperbarui data yang sesuai di dalam database. Proses ini memungkinkan perubahan data yang telah tersimpan di dalam basis data sesuai dengan permintaan pengguna.

3. **Delete Data**: Saat melakukan penghapusan data, program akan menggunakan query `DELETE FROM` untuk menghapus entri data tertentu dari basis data. Proses ini memungkinkan penghapusan data yang dipilih oleh pengguna dari database.

### Menampilkan Pesan Error Jika Input Kosong atau NIM Sudah Ada:

1. **Validasi Input Kosong**: Sebelum melakukan operasi penambahan atau pembaruan data, program akan memeriksa apakah semua kolom yang diperlukan telah diisi dengan benar. Jika ada kolom yang masih kosong, program akan menampilkan pesan error kepada pengguna, memberitahukan bahwa semua input harus diisi sebelum proses dapat dilanjutkan. Ini memastikan integritas data dan meminimalkan kemungkinan kesalahan input.

2. **Validasi NIM yang Sama**: Saat melakukan operasi penambahan data, program akan melakukan pengecekan terlebih dahulu terhadap keberadaan NIM yang sama dalam basis data. Jika NIM yang dimasukkan sudah ada, program akan mengirimkan pesan error kepada pengguna, memberitahukan bahwa NIM tersebut sudah ada dalam database. Hal ini menghindari duplikasi data yang tidak diinginkan dan menjaga konsistensi basis data.

Implementasi dari fitur-fitur ini dapat ditemukan di dalam method `insertData()` dan `updateData()` dalam kode program. Dalam kedua method tersebut, terdapat langkah-langkah validasi yang dilakukan sebelum operasi CRUD dilanjutkan, serta pesan error yang ditampilkan kepada pengguna jika terjadi kesalahan input atau NIM yang sama dalam database.
   
# Penjelasan Alur Program:

1. Saat program dijalankan, frame utama `Menu` akan ditampilkan.
2. Pengguna dapat menambah, mengedit, atau menghapus data mahasiswa menggunakan tombol yang disediakan.
3. Saat tombol "Add" ditekan, data mahasiswa baru akan dimasukkan ke dalam database. Namun, jika ada input yang belum diisi maka akan ada prompt "Semua input harus diisi!". Dan jika user menginput NIM yang sama dengan yang sudah pernah ada di database maka akan muncul prompt "NIM sudah ada dalam database".
4. Saat salah satu baris dalam tabel diklik, data mahasiswa akan ditampilkan di dalam form untuk diedit atau dihapus. Apabila tidak akan melakukan apa apa bisa menekan tombol "Cancel"
5. Saat tombol "Update" ditekan setelah baris dipilih, data mahasiswa akan diperbarui di database sesuai dengan perubahan yang dilakukan. Namun, jika ada input update yang belum diisi maka akan ada prompt "Semua input harus diisi!". Dan jika user mengupdate NIM yang sama dengan yang sudah pernah ada di database maka akan muncul prompt "NIM sudah ada dalam database".
6. Saat tombol "Delete" ditekan setelah baris dipilih, data mahasiswa akan dihapus setelah konfirmasi hapus data.


# Dokumentasi
## 1. Insert gagal karena inputan ada yang kosong
![1  insert gagal karena inputan ada yang kosong](https://github.com/queenxhr/TP2DPBO2024C2/assets/135084798/dc361cd6-282d-48d5-93e7-1cbd27ae8868)
## 2. Insert gagal karena input nim yang sama dengan yang sudah ada di database
![2  insert gagal karena input nim yang sama di db](https://github.com/queenxhr/TP2DPBO2024C2/assets/135084798/ac20d2a3-857a-40c6-b397-608a10efccf4)
## 3. Insert berhasil
![3  insert berhasil](https://github.com/queenxhr/TP2DPBO2024C2/assets/135084798/9bf0e616-dc81-4d93-9e27-7ce3343c1c18)
## 4. Hasil insert data
![4  hasil insert data](https://github.com/queenxhr/TP2DPBO2024C2/assets/135084798/09cc8a74-7401-484c-a752-feab3c9c0d88)
## 5. Update gagal karena inputan ada yang kosong
![5  update gagal karena inputan ada yang kosong](https://github.com/queenxhr/TP2DPBO2024C2/assets/135084798/7965b87b-7846-44b9-87e9-d83b7c6b8f05)
## 6. Update gagal karena input nim yang sama dengan yang sudah ada di database
![6  update gagal karena update nim yang sama di db](https://github.com/queenxhr/TP2DPBO2024C2/assets/135084798/a749c12a-2717-4cc2-b871-f19749bdbef7)
## 7. Update berhasil
![7  update berhasil](https://github.com/queenxhr/TP2DPBO2024C2/assets/135084798/4c8822d9-2238-4c9c-845c-c3b1ea3ba7be)
## 8. Prompt konfirmasi delte
![8  prompt konfirmasi delete](https://github.com/queenxhr/TP2DPBO2024C2/assets/135084798/eff2804d-9a57-449f-8ff1-42b48a948370)
## 9. Delete berhasil
![9  delete berhasil](https://github.com/queenxhr/TP2DPBO2024C2/assets/135084798/9b1b59ba-b7d0-4954-ac12-fa6f39452610)
