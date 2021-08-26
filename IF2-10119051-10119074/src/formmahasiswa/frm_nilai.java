/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formmahasiswa;

import java.awt.event.KeyEvent;
import javax.swing.*;
//fungsi sql
import java.sql.*;
//fungsi unutk pengguna
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
/**
 *IF2  RAHMAYUDHI PRAKOSO - 10119051
 *IF2  HANDRIAN RIVALDI - 10119074
 * 
 */

public class frm_nilai extends javax.swing.JFrame {

    koneksi dbsetting;
    String driver,database,user,pass;
    Object tabel;
    
    
    public frm_nilai()
    {
        initComponents();
            
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_nilai.setModel(tableModel);
        settableload();
        getDataComboBox();
        nonaktif_teks();
    }
   
    private javax.swing.table.DefaultTableModel tableModel=getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel()
    {
     //membuat judul header
        return new javax.swing.table.DefaultTableModel
        (
             new Object[][] {},
             new String []  {"Nama",
                             "Nama M.K",
                             "Absensi",
                             "Tugas 1",
                             "Tugas 2",
                             "Tugas 3",
                             "UTS",
                             "UAS",
                             "Nilai Absensi",
                             "Nilai Tugas",
                             "Nilai UTS",
                             "Nilai UAS",
                             "Nilai Akhir",
                             "Indeks",
                             "Keterangan"}
                 
        )
        //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[]
           {
              false, false, false, false,false  
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
       };
    }
    int row = 0;
    public void getDataComboBox() 
    {
        row = tabel_nilai.getSelectedRow();
        try 
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT nama FROM t_mahasiswa";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next())
            {
                String nama = res.getString(1);
                cmb_nama.addItem(nama);
            }
            
            String sql = "SELECT nama_mk FROM t_mata_kuliah";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) 
            {
                String nama_mk = rs.getString(1);
                cmb_matkul.addItem(nama_mk);
            }
            res.close();
            stt.close();
            kon.close();
        } 
        catch (Exception ex) 
        {
              JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Error",
                        JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    
    String data[] = new String [15];
    private void settableload()
    {
        String stat = "";
        
        try 
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT nama, nama_mk, absensi, tugas_1, tugas_2, tugas_3, uts, uas, nilai_absensi, nilai_tugas, nilai_uts, nilai_uas, nilai_akhir, indeks, keterangan FROM t_nilai";
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
                
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),"Error",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

   
    public void membersihkan_teks()
    {
        cmb_nama.setSelectedIndex(0);
        cmb_matkul.setSelectedIndex(0);
        txt_nim.setText("");
        txt_kehadiran.setText("");
        txt_tugas1.setText("");
        txt_tugas2.setText("");
        txt_tugas3.setText("");
        txt_kodeMK.setText("");
        txt_UTS.setText("");
        txt_UAS.setText("");
        txt_angkatan.setDate(null);
    }
   public void nonaktif_teks()
    {
        cmb_nama.setEnabled(false);
        txt_nim.setEnabled(false);
        txt_kehadiran.setEnabled(false);
        txt_tugas1.setEnabled(false);
        txt_tugas2.setEnabled(false);
        txt_tugas3.setEnabled(false);
        cmb_matkul.setEnabled(false);
        txt_kodeMK.setEnabled(false);
        txt_UTS.setEnabled(false);
        txt_UAS.setEnabled(false);
        txt_angkatan.setEnabled(false);
    }
    public void aktif_teks()
    {
        cmb_nama.setEnabled(true);
        txt_nim.setEnabled(true);
        txt_kehadiran.setEnabled(true);
        txt_tugas1.setEnabled(true);
        txt_tugas2.setEnabled(true);
        txt_tugas3.setEnabled(true);
        cmb_matkul.setEnabled(true);
        txt_kodeMK.setEnabled(true);
        txt_UTS.setEnabled(true);
        txt_UAS.setEnabled(true);
        txt_angkatan.setEnabled(true);
    }
    
    
    public void tampil_field()
    {
        row = tabel_nilai.getSelectedRow();
        cmb_nama.setSelectedItem(tableModel.getValueAt(row, 0).toString());
        cmb_matkul.setSelectedItem(tableModel.getValueAt(row, 1).toString());
        txt_kehadiran.setText(tableModel.getValueAt(row, 2).toString());
        txt_tugas1.setText(tableModel.getValueAt(row, 3).toString());
        txt_tugas2.setText(tableModel.getValueAt(row, 4).toString());
        txt_tugas3.setText(tableModel.getValueAt(row, 5).toString());
        txt_UTS.setText(tableModel.getValueAt(row, 6).toString());
        txt_UAS.setText(tableModel.getValueAt(row, 7).toString());
            
        aktif_teks();
        
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
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_masukData = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_nilai = new javax.swing.JTable();
        btn_tambah = new javax.swing.JButton();
        btn_ubah = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_simpan = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        btn_keluar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_nim = new javax.swing.JTextField();
        txt_kehadiran = new javax.swing.JTextField();
        txt_tugas1 = new javax.swing.JTextField();
        txt_tugas2 = new javax.swing.JTextField();
        txt_tugas3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_kodeMK = new javax.swing.JTextField();
        txt_UTS = new javax.swing.JTextField();
        txt_UAS = new javax.swing.JTextField();
        cmb_nama = new javax.swing.JComboBox<>();
        cmb_matkul = new javax.swing.JComboBox<>();
        txt_angkatan = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("FORM NILAI MAHASISWA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));

        txt_masukData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_masukDataActionPerformed(evt);
            }
        });
        txt_masukData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_masukDataKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Masukan Data");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel9)
                .addGap(35, 35, 35)
                .addComponent(txt_masukData, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_masukData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        tabel_nilai.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_nilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_nilaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_nilai);

        btn_tambah.setBackground(new java.awt.Color(51, 255, 0));
        btn_tambah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_tambah.setText("TAMBAH");
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });

        btn_ubah.setBackground(new java.awt.Color(255, 204, 0));
        btn_ubah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_ubah.setText("UBAH");
        btn_ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ubahActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 0, 0));
        btn_hapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_simpan.setBackground(new java.awt.Color(51, 255, 0));
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

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NIM");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kehadiran");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tugas 1");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tugas 2");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tugas 3");

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pencarian Data");

        txt_tugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tugas2ActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Pertemuan");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Nama Mata Kuliah");

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Kode MK");

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("UTS");

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("UAS");

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Angkatan");

        cmb_nama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH NAMA", " " }));
        cmb_nama.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmb_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_namaActionPerformed(evt);
            }
        });

        cmb_matkul.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH MATKU", " " }));
        cmb_matkul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_matkulActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txt_tugas2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                                    .addComponent(txt_kehadiran, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_tugas1, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel10))
                                            .addComponent(cmb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(44, 44, 44)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel15))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txt_UAS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                                .addComponent(txt_UTS, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(cmb_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txt_angkatan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txt_kodeMK, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel11)
                    .addComponent(cmb_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_matkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txt_kodeMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13)
                    .addComponent(txt_UTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(txt_UAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txt_tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_angkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btn_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_keluarActionPerformed
        //VALIDASI KELUAR
        int validasi = JOptionPane.showOptionDialog(this, 
                    "Yakin Ingin Keluar?", 
                    "Konfirmasi", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (validasi == JOptionPane.YES_OPTION) 
        {
        frm_utama utm = new frm_utama();
        utm.setVisible(true);
        
        this.setVisible(false);
        }
    }//GEN-LAST:event_btn_keluarActionPerformed

    private void txt_tugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tugas2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tugas2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        frm_utama utama = new frm_utama();
        utama.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String[15];
        
        if ((txt_kehadiran.getText().isEmpty()) || (txt_kodeMK.getText().isEmpty()) || (txt_tugas1.getText().isEmpty() || (txt_tugas2.getText().isEmpty() || (txt_tugas3.getText().isEmpty()) || (txt_UTS.getText().isEmpty() || (txt_UAS.getText().isEmpty()) || (txt_angkatan.getDate() == null)  )))) {
            JOptionPane.showMessageDialog(null, "Data nilai mahasiswa tidak boleh kosong, Ulangi!");
            cmb_nama.requestFocus();
            
        }
        else 
        {
            try 
            {
                
                int absen = Integer.parseInt(txt_kehadiran.getText());
                
                if (absen > 14) {
                    JOptionPane.showMessageDialog(null, "Maximal pertemuan adalah 14");
                    txt_kehadiran.requestFocus();
                }
                
                int nilai_absen = ((absen/14)*100*5)/100;
                
                //hitung nilai tugas
                Double tugas_1 = Double.valueOf(txt_tugas1.getText());
                Double tugas_2 = Double.valueOf(txt_tugas2.getText());
                Double tugas_3 = Double.valueOf(txt_tugas3.getText());
                Double nilai_tugas = (((tugas_1+tugas_2+tugas_3)/3)*(0.25));
                
                    //hitung nilai uts
                    Double uts = Double.valueOf(txt_UTS.getText());
                    Double nilai_uts = (uts*0.3);
                
                        //hitung nilai uas
                        Double uas = Double.valueOf(txt_UAS.getText());
                        Double nilai_uas = (uas*0.4);
                
                            //hitung nilai akhir 
                            Double nilai_akhir = nilai_absen + nilai_tugas + nilai_uts + nilai_uas;
                            char indeks;
                            String keterangan;
                //INDEKS
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
                
                if (absen < 11) {
                    keterangan = "Tidak Lulus";
                }
                
                if ((tugas_1 > 100) || (tugas_2 > 100) || (tugas_3 > 100) || (uts > 100) || (uas > 100)) {
                    JOptionPane.showMessageDialog(null, "Maximal nilai mahasiswa adalah 100, Ulangi!");
                    txt_tugas1.requestFocus();
                } else {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "INSERT INTO t_nilai (nama,"
                        + "nama_mk,"
                        + "absensi,"
                        + "tugas_1,"
                        + "tugas_2,"
                        + "tugas_3,"
                        + "uts,"
                        + "uas,"
                        + "nilai_absensi,"
                        + "nilai_tugas,"
                        + "nilai_uts,"
                        + "nilai_uas,"
                        + "nilai_akhir,"
                        + "indeks,"
                        + "keterangan) "
                        + "VALUES "
                        + "('"+cmb_nama.getSelectedItem()+"',"
                        + "'"+cmb_matkul.getSelectedItem()+"',"
                        + "'"+absen+"',"
                        + "'"+tugas_1+"',"
                        + "'"+tugas_2+"',"
                        + "'"+tugas_3+"',"
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
                data[0] = cmb_nama.getSelectedItem().toString();
                data[1] = cmb_matkul.getSelectedItem().toString();
                data[2] = String.valueOf(absen);
                data[3] = String.valueOf(tugas_1);
                data[4] = String.valueOf(tugas_2);
                data[5] = String.valueOf(tugas_3);
                data[6] = String.valueOf(uts);
                data[7] = String.valueOf(uas);
                data[8] = String.valueOf(nilai_absen);
                data[9] = String.valueOf(nilai_tugas);
                data[10] = String.valueOf(nilai_uts);
                data[11] = String.valueOf(nilai_uas);
                data[12] = String.valueOf(nilai_akhir);
                data[13] = String.valueOf(indeks);
                data[14] = keterangan;
                
                tableModel.insertRow(0, data);
                
                btn_ubah.setEnabled(true);
                btn_hapus.setEnabled(true);
                membersihkan_teks();
                
                JOptionPane.showMessageDialog(null, "Data Nilai Mahasiswa Berhasil Tersimpan");
                
                stt.close();
                kon.close();
                } //END IF 
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }   
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void cmb_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_namaActionPerformed
        // TODO add your handling code here:
        String nama = cmb_nama.getSelectedItem().toString();
        
        if (nama == "===Pilih Nama===") {
            JOptionPane.showMessageDialog(null, "Silahkan pilih nama terlebih dahulu");
        } else {
            try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT nim FROM t_mahasiswa "
                        + "WHERE nama= '"+nama+"' ";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                txt_nim.setText(res.getString(1));  
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
    }//GEN-LAST:event_cmb_namaActionPerformed

    private void cmb_matkulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_matkulActionPerformed
        // TODO add your handling code here:
        String nama_mk = cmb_matkul.getSelectedItem().toString();
        
        if (nama_mk == "===Pilih Mata Kuliah===") {
            JOptionPane.showMessageDialog(null, "Silahkan pilih Nama Mata Kuliah terlebih dahulu");
        } 
        else
        {
            try
            {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT kd_mk FROM t_mata_kuliah "
                        + "WHERE nama_mk= '"+nama_mk+"' ";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) 
            {
                txt_kodeMK.setText(res.getString(1));  
            }
            
            res.close();
            stt.close();
            kon.close();
            }
            catch (Exception ex) 
            {
              JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Error",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_cmb_matkulActionPerformed

    private void tabel_nilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_nilaiMouseClicked
        // TODO add your handling code here:
          if (evt.getClickCount()== 1) {
            tampil_field();
        }
    }//GEN-LAST:event_tabel_nilaiMouseClicked

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        btn_simpan.setEnabled(true);
        btn_tambah.setEnabled(true);
        btn_keluar.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_ubah.setEnabled(true);
        
        membersihkan_teks();
        nonaktif_teks();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        cmb_nama.requestFocus();
        btn_hapus.setEnabled(false);
        btn_ubah.setEnabled(false);
        
        aktif_teks();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int jawab = JOptionPane.showOptionDialog(this, 
                    "Apakah Anda Yakin Untuk Menghapus Data Nilai Mahasiswa Ini?", 
                    "Konfirmasi", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
        
        if (jawab == JOptionPane.YES_OPTION) {
        
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "DELETE FROM t_nilai "
                         + "WHERE "
                         + "nama='"+tableModel.getValueAt(row, 0).toString()+"'";
            
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
       
            stt.close();
            kon.close();
            
            membersihkan_teks();
        }
        catch(Exception e) 
        {
            System.err.println(e.getMessage());
        }
       }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void txt_masukDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_masukDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_masukDataActionPerformed

    private void txt_masukDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_masukDataKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tableModel.setRowCount(0);
        
        
        String cari = txt_masukData.getText();
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "SELECT * FROM t_nilai WHERE nama LIKE '%" + cari + "%'"; 
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) 
            {
                
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
                tableModel.addRow(data);
            }
            
            res.close();
            kon.close();
            stt.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            }
        }
    }//GEN-LAST:event_txt_masukDataKeyPressed

    private void btn_ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ubahActionPerformed
        // TODO add your handling code here:
        String nama_mhs = cmb_nama.getSelectedItem().toString();
        String nama_mk = String.valueOf(cmb_matkul.getSelectedIndex());
        String nim = txt_nim.getText();
        String kehadiran = txt_kehadiran.getText();
        String tugas_1 = txt_tugas1.getText();
        String tugas_2 = txt_tugas2.getText();
        String tugas_3 = txt_tugas3.getText();
        String kd_mk = txt_kodeMK.getText();
        String uts = txt_UTS.getText();
        String uas = txt_UAS.getText();
        
        if ((txt_kehadiran.getText().isEmpty()) || (txt_kodeMK.getText().isEmpty()) || (txt_tugas1.getText().isEmpty() || (txt_tugas2.getText().isEmpty() || (txt_tugas3.getText().isEmpty()) || (txt_nim.getText().isEmpty())))) {
            
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE t_nilai "
                        + "SET "
                        + "nama = '"+nama_mhs+"',"
                        + "nama_mk = '"+nama_mk+"',"
                        + "absensi = '"+kehadiran+"',"
                        + "tugas_1 = '"+tugas_1+"',"
                        + "tugas_2 = '"+tugas_2+"',"
                        + "tugas_3 = '"+tugas_3+"',"
                        + "nilai_uts = '"+uts+"',"
                        + "nilai_uas = '"+uas+"'"
                        + "WHERE nama= '" + tableModel.getValueAt(row, 0).toString() + "'";
                
                stt.executeUpdate(SQL);
                
                
                data[0] = nama_mhs;
                data[1] = nama_mk;
                data[2] = kehadiran;
                data[3] = tugas_1;
                data[4] = tugas_2;
                data[5] = tugas_3;
                data[6] = uts;
                data[7] = uas;
                
                tableModel.removeRow(row);
                tableModel.insertRow(row, data);
                
                JOptionPane.showMessageDialog(null, "Data nilai mahasiswa berhasil diubah");
                
                stt.close();
                kon.close();
                membersihkan_teks();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_ubahActionPerformed

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
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_nilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_nilai().setVisible(true);
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
    private javax.swing.JComboBox<String> cmb_nama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_nilai;
    private javax.swing.JTextField txt_UAS;
    private javax.swing.JTextField txt_UTS;
    private com.toedter.calendar.JDateChooser txt_angkatan;
    private javax.swing.JTextField txt_kehadiran;
    private javax.swing.JTextField txt_kodeMK;
    private javax.swing.JTextField txt_masukData;
    private javax.swing.JTextField txt_nim;
    private javax.swing.JTextField txt_tugas1;
    private javax.swing.JTextField txt_tugas2;
    private javax.swing.JTextField txt_tugas3;
    // End of variables declaration//GEN-END:variables

    
}
