/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formmahasiswa;

import javax.swing.*;
//fungsi sql
import java.sql.*;
//fungsi unutk pengguna
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Acer
 */
public class frm_nilaiAkhir extends javax.swing.JFrame {
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    /**
     * 
     */
    public frm_nilaiAkhir() 
    
    {
        
        initComponents();
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword"); 
        
        tabel_nilaiAkhir.setModel(tableModel);
        settableload();
        getDataComboBox();
    }
    
    
    String data[] = new String [18];
        private void settableload(){
        String stat = "";
        
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "Select * From t_nilaiAkhir";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                data[5] = res.getString(6);
                data[6] = res.getString(7);
                data[7] = res.getString(8);
                data[8] = res.getString(9);
                data[9] = res.getString(10);
                data[10] = res.getString(11);
                data[11] = res.getString(12);
                data[12] = res.getString(13);
                data[13] = res.getString(14);
                data[14] = res.getString(15);
                data[15] = res.getString(16);
                data[16] = res.getString(17);
                data[17] = res.getString(18);
                
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch(Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
        
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTableModel();
    private javax.swing.table.DefaultTableModel getDefaultTableModel () {
        
        //membuat judul header
        return new javax.swing.table.DefaultTableModel
                (
                        new Object[][] {},
                        new String [] {
                            "Nama MK",
                            "Presentase Absen",
                            "Presentase Tugas",
                            "Presentase UTS",
                            "Presentase UAS",
                            "Absensi",
                            "Tugas 1",
                            "Tugas 2",
                            "Tugas 3",
                            "UTS",
                            "UAS",
                            "Nilai Absen",
                            "Nilai Tugas",
                            "Nilai UTS",
                            "Nilai UAS",
                            "Nilai Akhir",
                            "Index",
                            "Keterangan"
                        }
                )
                        
                {
                    boolean[] canEdit = new boolean[] {
                        false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
                    };
                    
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
    }
        public void getDataComboBox() {
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            
            String sql = "Select nama_mk FROM t_mata_kuliah";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                String nama_mk = rs.getString(1);
                cmb_matkul.addItem(nama_mk);
            }
            rs.close();
            stt.close();
            kon.close();
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Error",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
     
    int row=0;
    public void tampil_field () {
        row = tabel_nilaiAkhir.getSelectedRow();
        
        aktif_teks();
        
        cmb_matkul.setSelectedItem(tableModel.getValueAt(row, 0).toString());
        txt_absen.setText(tableModel.getValueAt(row, 1).toString());
        txt_tugas.setText(tableModel.getValueAt(row, 2).toString());
        txt_presentaseuts.setText(tableModel.getValueAt(row, 3).toString());
        txt_presentaseuas.setText(tableModel.getValueAt(row, 4).toString());
        txt_kehadiran.setText(tableModel.getValueAt(row, 5).toString());
        txt_tugas1.setText(tableModel.getValueAt(row, 6).toString());
        txt_tugas2.setText(tableModel.getValueAt(row, 7).toString());
        txt_tugas3.setText(tableModel.getValueAt(row, 8).toString());
        txt_uts.setText(tableModel.getValueAt(row, 9).toString());
        txt_uas.setText(tableModel.getValueAt(row, 10).toString());
    }
    
    public void nonaktif_teks() {
        txt_kodeMK.setEnabled(false);
        txt_absen.setEnabled(false);
        txt_tugas.setEnabled(false);
        txt_presentaseuts.setEnabled(false);
        txt_presentaseuas.setEnabled(false);
        txt_kehadiran.setEnabled(false);
        txt_tugas1.setEnabled(false);
        txt_tugas2.setEnabled(false);
        txt_tugas3.setEnabled(false);
        txt_uts.setEnabled(false);
        txt_uas.setEnabled(false);
    }
    
    public void aktif_teks () {
        txt_kodeMK.setEnabled(true);
        txt_absen.setEnabled(true);
        txt_tugas.setEnabled(true);
        txt_presentaseuts.setEnabled(true);
        txt_presentaseuas.setEnabled(true);
        txt_kehadiran.setEnabled(true);
        txt_tugas1.setEnabled(true);
        txt_tugas2.setEnabled(true);
        txt_tugas3.setEnabled(true);
        txt_uts.setEnabled(true);
        txt_uas.setEnabled(true);
    }
    
    public void membersihkan_teks() {
        txt_kodeMK.setText("");
        txt_absen.setText("");
        txt_tugas.setText("");
        txt_presentaseuts.setText("");
        txt_presentaseuas.setText("");
        txt_kehadiran.setText("");
        txt_tugas1.setText("");
        txt_tugas2.setText("");
        txt_tugas3.setText("");
        txt_uts.setText("");
        txt_uas.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_kodeMK = new javax.swing.JTextField();
        txt_absen = new javax.swing.JTextField();
        txt_tugas = new javax.swing.JTextField();
        txt_presentaseuts = new javax.swing.JTextField();
        txt_presentaseuas = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_kehadiran = new javax.swing.JTextField();
        txt_tugas1 = new javax.swing.JTextField();
        txt_tugas2 = new javax.swing.JTextField();
        txt_tugas3 = new javax.swing.JTextField();
        txt_uts = new javax.swing.JTextField();
        txt_uas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_nilaiAkhir = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cmb_matkul = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM SIMULASI NILAI AKHIR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Nama Mata Kuliah");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Kode MK");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Presentase Absen");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Presentase Tugas");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Presentase UTS");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Presentase UAS");

        txt_absen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_absenActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Kehadiran");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Tugas 1");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Tugas 2");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Tugas 3");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("UTS");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("UAS");

        txt_kehadiran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_kehadiranActionPerformed(evt);
            }
        });

        txt_uas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_uasActionPerformed(evt);
            }
        });

        tabel_nilaiAkhir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_nilaiAkhir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_nilaiAkhirMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_nilaiAkhir);

        btn_tambah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_tambah.setText("TAMBAH");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_ubah.setText("UBAH");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_hapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_batal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_keluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_keluar.setText("KELUAR");
        btn_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_keluarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Pertemuan");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("%");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("%");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("%");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("%");

        cmb_matkul.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmb_matkul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_matkulActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_absen, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_presentaseuts, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                            .addComponent(txt_tugas)
                                            .addComponent(txt_presentaseuas))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel17))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15)))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(24, 24, 24))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_tugas1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                            .addComponent(txt_kehadiran)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11))
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tugas3)
                                            .addComponent(txt_uts)
                                            .addComponent(txt_uas)
                                            .addComponent(txt_tugas2))))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(71, 71, 71)
                                .addComponent(txt_kodeMK, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 8, Short.MAX_VALUE)
                                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmb_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_kodeMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_absen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_tugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_presentaseuts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_presentaseuas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_uts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_uas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_ubah)
                    .addComponent(btn_hapus)
                    .addComponent(btn_simpan)
                    .addComponent(btn_batal)
                    .addComponent(btn_keluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_kehadiranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_kehadiranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_kehadiranActionPerformed

    private void txt_absenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_absenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_absenActionPerformed

    private void txt_uasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_uasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_uasActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        btn_hapus.setEnabled(false);
        btn_ubah.setEnabled(false);
        cmb_matkul.requestFocus();
        aktif_teks();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String [18];
        
        if ((txt_kodeMK.getText().isEmpty()) || (txt_absen.getText().isEmpty()) || (txt_tugas1.getText().isEmpty() || (txt_tugas2.getText().isEmpty() || (txt_tugas3.getText().isEmpty()) || (txt_tugas.getText().isEmpty() || (txt_presentaseuas.getText().isEmpty() || (txt_presentaseuts.getText().isEmpty()) || (txt_kehadiran.getText().isEmpty()) || (txt_uts.getText().isEmpty()) ||(txt_uas.getText().isEmpty()) ))))) {
            JOptionPane.showMessageDialog(null, "Data untuk simulasi tidak boleh kosong, Harap isi !");
            txt_absen.requestFocus();
            
        }
        else {
            try {
                // nilai absen
                Double hadir = Double.valueOf(txt_kehadiran.getText());
                
                if (hadir > 14) {
                    JOptionPane.showMessageDialog(null, "Maximal Pertemuan adalah 14");
                    txt_kehadiran.requestFocus();
                } else {
                Double absen = Double.valueOf(txt_absen.getText());
                
                Double nilai_absen = (((hadir/14)*100*absen)/100);
                
               
                Double presentase_tugas = Double.valueOf(txt_tugas.getText());
                Double tugas1 = Double.valueOf(txt_tugas1.getText());
                Double tugas2 = Double.valueOf(txt_tugas2.getText());
                Double tugas3 = Double.valueOf(txt_tugas3.getText());
                Double nilai_tugas = (((tugas1+tugas2+tugas3)/3)*(presentase_tugas/100));
                
                        
                        Double presentase_uts = Double.valueOf(txt_uts.getText());
                        Double uts = Double.valueOf(txt_presentaseuts.getText());
                        Double nilai_uts = uts * (presentase_uts/100);
                
                                
                                Double presentase_uas = Double.valueOf(txt_presentaseuas.getText());
                                Double uas = Double.valueOf(txt_uas.getText());
                                Double nilai_uas = uas * (presentase_uas/100);
                
                                       
                                        Double nilai_akhir = nilai_absen + nilai_tugas + nilai_uts + nilai_uas;
                                        char indeks;
                                        String keterangan;

                if (nilai_akhir >= 80 && nilai_akhir <=100) {
                    indeks = 'A';
                    keterangan = "Lulus";
                } else if(nilai_akhir >= 68) {
                    indeks = 'B';
                    keterangan = "Lulus";
                } else if(nilai_akhir >= 56) {
                    indeks = 'C';
                    keterangan = "Lulus";
                } else if(nilai_akhir >= 45) {
                    indeks = 'D';
                    keterangan = "Tidak Lulus";
                } else {
                    indeks = 'E';
                    keterangan = "Tidak Lulus";
                }
                
                if (hadir < 11) {
                    keterangan = "Tidak Lulus";
                }
                
                if ((tugas1 > 100) || (tugas2 > 100) || (tugas3 > 100) || (uts > 100) || (uas > 100)) {
                    JOptionPane.showMessageDialog(null, "Maximal nilai adalah 100, Ulangi!");
                    txt_tugas1.requestFocus();
                } else {
                
                
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO t_nilaiAkhir (nama_mk,"
                        + "p_absen,"
                        + "p_tugas,"
                        + "p_uts,"
                        + "p_uas,"
                        + "absensi,"
                        + "tugas_1,"
                        + "tugas_2,"
                        + "tugas_3,"
                        + "uts,"
                        + "uas,"
                        + "nilai_absen,"
                        + "nilai_tugas,"
                        + "nilai_uts,"
                        + "nilai_uas,"
                        + "nilai_akhir,"
                        + "indeks,"
                        + "keterangan) "
                        + "VALUES "
                        + "('"+cmb_matkul.getSelectedItem()+"',"
                        + "'"+absen+"',"
                        + "'"+presentase_tugas+"',"
                        + "'"+presentase_uts+"',"
                        + "'"+presentase_uas+"',"
                        + "'"+hadir+"',"
                        + "'"+tugas1+"',"
                        + "'"+tugas2+"',"
                        + "'"+tugas3+"',"
                        + "'"+uts+"',"
                        + "'"+uas+"',"
                        + "'"+nilai_absen+"',"
                        + "'"+nilai_tugas+"',"
                        + "'"+nilai_uts+"',"
                        + "'"+nilai_uas+"',"
                        + "'"+nilai_akhir+"',"
                        + "'"+indeks+"',"
                        + "'"+keterangan+"') ";
                
                stt.executeUpdate(SQL);
                data[0] = cmb_matkul.getSelectedItem().toString();
                data[1] = String.valueOf(absen);
                data[2] = String.valueOf(presentase_tugas);
                data[3] = String.valueOf(presentase_uts);
                data[4] = String.valueOf(presentase_uas);
                data[5] = String.valueOf(hadir);
                data[6] = String.valueOf(tugas1);
                data[7] = String.valueOf(tugas2);
                data[8] = String.valueOf(tugas3);
                data[9] = String.valueOf(uts);
                data[10] = String.valueOf(uas);
                data[11] = String.valueOf(nilai_absen);
                data[12] = String.valueOf(nilai_tugas);
                data[13] = String.valueOf(nilai_uts);
                data[14] = String.valueOf(nilai_uas);
                data[15] = String.valueOf(nilai_akhir);
                data[16] = String.valueOf(indeks);
                data[17] = String.valueOf(keterangan);
                
                tableModel.insertRow(0, data);
                
                btn_hapus.setEnabled(true);
                btn_ubah.setEnabled(true);
                
                JOptionPane.showMessageDialog(null, "Data berhasil tersimpan");
                
                stt.close();
                kon.close();
                    } //END IF
                } //END IF VALIDASI
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
       frm_mhs mhs = new frm_mhs();
         mhs.setVisible(false);
    }//GEN-LAST:event_formWindowClosed

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        // TODO add your handling code here:
         int jawab = JOptionPane.showOptionDialog(this, 
                    "Apakah Anda Yakin Ingin Keluar?", 
                    "Konfirmasi", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if (jawab == JOptionPane.YES_OPTION) {
        
        frm_utama utm = new frm_utama();
        utm.setVisible(true);
        
        this.setVisible(false);
        
        }
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void cmb_matkulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_matkulActionPerformed
        // TODO add your handling code here:
        String mata_kuliah = cmb_matkul.getSelectedItem().toString();
        
        if (mata_kuliah == "-----Pilih Mata Kuliah----") {
            JOptionPane.showMessageDialog(null, "Dahulukan Pillih Nama");
        } else {
            try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "select kd_mk from t_mata_kuliah "
                        + "where nama_mk= '"+mata_kuliah+"' ";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                txt_kodeMK.setText(res.getString(1));  
            }
            
            res.close();
            stt.close();
            kon.close();
          } catch (Exception ex) {
              JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Error",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_cmb_matkulActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        btn_hapus.setEnabled(true);
        btn_ubah.setEnabled(true);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void tabel_nilaiAkhirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_nilaiAkhirMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            tampil_field();
        }
    }//GEN-LAST:event_tabel_nilaiAkhirMouseClicked

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        String nama_mk = cmb_matkul.getSelectedItem().toString();
        String presentase_absen = txt_absen.getText();
        String presentase_tugas = txt_tugas.getText();
        String presentase_uts = txt_presentaseuts.getText();
        String presentase_uas = txt_presentaseuas.getText();
        String kehadiran = txt_kehadiran.getText();
        String tugas_1 = txt_tugas1.getText();
        String tugas_2 = txt_tugas2.getText();
        String tugas_3 = txt_tugas3.getText();
        String uts = txt_uts.getText();
        String uas = txt_uas.getText();
        
    if ((txt_kodeMK.getText().isEmpty()) || (txt_absen.getText().isEmpty()) || (txt_tugas1.getText().isEmpty() || (txt_tugas2.getText().isEmpty() || (txt_tugas3.getText().isEmpty()) || (txt_tugas.getText().isEmpty() || (txt_presentaseuas.getText().isEmpty() || (txt_presentaseuts.getText().isEmpty()) ))))) {
            
        } else {
            try {
                 //menghitung nilai absen
                Double hadir = Double.valueOf(txt_kehadiran.getText());
                
                if (hadir > 14) {
                    JOptionPane.showMessageDialog(null, "Maximal Pertemuan adalah 14");
                    txt_kehadiran.requestFocus();
                } else {
                Double absen = Double.valueOf(txt_absen.getText());
                
                Double nilai_absen = (((hadir/14)*100*absen)/100);
                
                //nilai_tugas
                Double presentase_Tugas = Double.valueOf(txt_tugas.getText());
                Double tugas1 = Double.valueOf(txt_tugas1.getText());
                Double tugas2 = Double.valueOf(txt_tugas2.getText());
                Double tugas3 = Double.valueOf(txt_tugas3.getText());
                Double nilai_tugas = (((tugas1+tugas2+tugas3)/3)*(presentase_Tugas/100));
                
                        //nilai_uts
                        Double presentase_UTS = Double.valueOf(txt_presentaseuts.getText());
                        Double UTS = Double.valueOf(txt_uts.getText());
                        Double nilai_uts = UTS * (presentase_UTS/100);
                
                                //nilai_uas
                                Double presentase_UAS = Double.valueOf(txt_presentaseuas.getText());
                                Double UAS = Double.valueOf(txt_uas.getText());
                                Double nilai_uas = UAS * (presentase_UAS/100);
                
                
                Double nilai_akhir = nilai_absen + nilai_tugas + nilai_uts + nilai_uas;
                char indeks;
                String keterangan;
                
                if (nilai_akhir >= 80 && nilai_akhir <=100) {
                    indeks = 'A';
                    keterangan = "Lulus";
                } else if(nilai_akhir >= 68) {
                    indeks = 'B';
                    keterangan = "Lulus";
                } else if(nilai_akhir >= 56) {
                    indeks = 'C';
                    keterangan = "Lulus";
                } else if(nilai_akhir >= 45) {
                    indeks = 'D';
                    keterangan = "Tidak Lulus";
                } else {
                    indeks = 'E';
                    keterangan = "Tidak Lulus";
                }
                
                if (hadir < 11) {
                    keterangan = "Tidak Lulus";
                }
                
                if ((tugas1 > 100) || (tugas2 > 100) || (tugas3 > 100) || (UTS > 100) || (UAS > 100)) {
                    JOptionPane.showMessageDialog(null, "Maximal nilai adalah 100, Ulangi!");
                    txt_tugas1.requestFocus();
                } else {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE t_nilaiAkhir "
                        + "SET "
                        + "nama_mk = '"+nama_mk +"',"
                        + "p_absen = '"+presentase_absen+"',"
                        + "p_tugas = '"+presentase_tugas+"',"
                        + "p_uts = '"+presentase_uts+"',"
                        + "p_uas = '"+presentase_uas+"',"
                        + "absensi = '"+kehadiran+"',"
                        + "tugas_1 = '"+tugas_1+"',"
                        + "tugas_2 = '"+tugas_2+"',"
                        + "tugas_3 = '"+tugas_3+"',"
                        + "uts = '"+UTS+"',"
                        + "uas = '"+UAS+"', "
                        + "nilai_absen = '"+nilai_absen+"', "
                        + "nilai_tugas = '"+nilai_tugas+"', "
                        + "nilai_uts = '"+nilai_uts+"', "
                        + "nilai_uas = '"+nilai_uas+"', "
                        + "nilai_akhir = '"+nilai_akhir+"' ,"
                        + "indeks = '"+indeks+"', "
                        + "keterangan = '"+keterangan+"'"
                        + "WHERE nama_mk= '" + tableModel.getValueAt(row, 0).toString() + "'";
                
                stt.executeUpdate(SQL);
                
                
                data[0] = nama_mk;
                data[1] = presentase_absen;
                data[2] = presentase_tugas;
                data[3] = presentase_uts;
                data[4] = presentase_uas;
                data[5] = kehadiran;
                data[6] = tugas_1;
                data[7] = String.valueOf(tugas2);
                data[8] = String.valueOf(tugas3);
                data[9] = String.valueOf(uts);
                data[10] = String.valueOf(uas);
                data[11] = String.valueOf(nilai_absen);
                data[12] = String.valueOf(nilai_tugas);
                data[13] = String.valueOf(nilai_uts);
                data[14] = String.valueOf(nilai_uas);
                data[15] = String.valueOf(nilai_akhir);
                data[16] = String.valueOf(indeks);
                data[17] = String.valueOf(keterangan);
                
                tableModel.removeRow(row);
                tableModel.insertRow(row, data);
                
                JOptionPane.showMessageDialog(null, "Perubahaan data berhasil disimpan");
                
                stt.close();
                kon.close();
                
                membersihkan_teks();
                    } //end if validasi max nilai input 100
                } //endif
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int jawab = JOptionPane.showOptionDialog(this, 
                    "Apakah Anda Yakin Untuk Menghapus Data Ini?", 
                    "Konfirmasi", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if (jawab == JOptionPane.YES_OPTION) {
        
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "DELETE from t_nilaiAkhir "
                         + "where "
                         + "nama_mk='"+tableModel.getValueAt(row, 0).toString()+"'";
            
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_teks();
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
      }
    }//GEN-LAST:event_btn_hapusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_nilaiAkhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_nilaiAkhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_nilaiAkhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_nilaiAkhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_nilaiAkhir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_keluar;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_ubah;
    private javax.swing.JComboBox<String> cmb_matkul;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_nilaiAkhir;
    private javax.swing.JTextField txt_absen;
    private javax.swing.JTextField txt_kehadiran;
    private javax.swing.JTextField txt_kodeMK;
    private javax.swing.JTextField txt_presentaseuas;
    private javax.swing.JTextField txt_presentaseuts;
    private javax.swing.JTextField txt_tugas;
    private javax.swing.JTextField txt_tugas1;
    private javax.swing.JTextField txt_tugas2;
    private javax.swing.JTextField txt_tugas3;
    private javax.swing.JTextField txt_uas;
    private javax.swing.JTextField txt_uts;
    // End of variables declaration//GEN-END:variables

    
}
