import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu extends JFrame {
    // index baris yang diklik
    private int selectedIndex = -1;
    private Database database;
    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JComboBox angkatanComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JLabel angkatanLabel;

    // constructor
    public Menu() {
        //buat objek database
        database = new Database();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box jenis kelamin
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // atur isi combo box angkatan
        String[] angkatanData = {"", "2019", "2020", "2021", "2022", "2023"};
        angkatanComboBox.setModel(new DefaultComboBoxModel(angkatanData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputValid()) {
                    if (selectedIndex == -1) {
                        insertData();
                    } else {
                        updateData();
                    }
                }
            }
        });

        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });

        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 3).toString();
                String selectedAngkatan = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);
                angkatanComboBox.setSelectedItem(selectedAngkatan);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama", "Jenis Kelamin", "Angkatan"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");

            int i = 0;
            while (resultSet.next()) {
                Object[] row = new Object[5];

                row[0] = i + 1;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] = resultSet.getString("jenis_kelamin");
                row[4] = resultSet.getString("angkatan");

                temp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return temp;
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String angkatan = angkatanComboBox.getSelectedItem().toString();

        // periksa apakah ada input yang kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || angkatan.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua input harus diisi");
            return;
        }

        // periksa apakah NIM sudah ada dalam database
        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa WHERE nim = '" + nim + "'");
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "NIM sudah ada dalam database");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // tambahkan data ke dalam database
        String sql = "INSERT INTO mahasiswa VALUES (null, '" + nim + "', '" + nama + "', '" + jenisKelamin + "', '" + angkatan + "')";
        database.insertUpdateDeleteQuery(sql);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Insert berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }

    public void updateData() {
        // Ambil data dari inputan pengguna
        String oldNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString(); // NIM sebelum diperbarui
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String angkatan = angkatanComboBox.getSelectedItem().toString();

        // Periksa apakah ada input yang kosong
        if (nim.isEmpty() || nama.isEmpty() || jenisKelamin.isEmpty() || angkatan.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua input harus diisi");
            return;
        }

        try {
            // Periksa apakah NIM yang baru sudah ada di database (selain NIM yang sedang diupdate)
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa WHERE nim = '" + nim + "' AND nim != '" + oldNim + "'");
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "NIM sudah ada dalam database");
                return;
            }

            // Perbarui data di database
            String sql = "UPDATE mahasiswa SET nim = '" + nim + "', nama = '" + nama + "', jenis_kelamin = '" + jenisKelamin + "', angkatan = '" + angkatan + "' WHERE nim = '" + oldNim + "'";
            int rowsAffected = database.insertUpdateDeleteQuery(sql);

            // Periksa apakah pembaruan berhasil
            if (rowsAffected > 0) {
                // Update tabel
                mahasiswaTable.setModel(setTable());

                // Bersihkan form
                clearForm();

                // Beri feedback kepada pengguna
                System.out.println("Update berhasil!");
                JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal memperbarui data");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat memperbarui data: " + e.getMessage());
        }
    }


    public void deleteData() {
        // ambil NIM yang akan dihapus
        String nim = nimField.getText();
        int dialogResult = JOptionPane.showConfirmDialog(null, "Hapus data?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
        //hapus data dari database
        if(dialogResult == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM mahasiswa WHERE nim = '" + nim + "'";
            int rowsAffected = database.insertUpdateDeleteQuery(sql);
            if (rowsAffected > 0) {
                // update tabel
                mahasiswaTable.setModel(setTable());

                // bersihkan form
                clearForm();

                // feedback
                System.out.println("Delete berhasil!");
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus data");
            }
        }

    }


    public boolean isInputValid() {
        if (nimField.getText().isEmpty() || namaField.getText().isEmpty() || jenisKelaminComboBox.getSelectedItem().toString().isEmpty() || angkatanComboBox.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua input harus diisi");
            return false;
        }
        return true;
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedItem("");
        angkatanComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // buat object window
                Menu window = new Menu();

                // atur ukuran window
                window.setSize(480, 560);
                // letakkan window di tengah layar
                window.setLocationRelativeTo(null);
                // isi window
                window.setContentPane(window.mainPanel);
                // ubah warna background
                window.getContentPane().setBackground(Color.white);
                // tampilkan window
                window.setVisible(true);
                // agar program ikut berhenti saat window diclose
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }



}