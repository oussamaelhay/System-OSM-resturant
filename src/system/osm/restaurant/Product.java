/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package system.osm.restaurant;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Product extends javax.swing.JFrame {

    public Product() {
        initComponents();
        initCombobox();
    }
    private String path2;
    private String selectedFilePath;
    private List<String> newCategorys = new ArrayList<>();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        product_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        upload = new javax.swing.JButton();
        image = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        possibledCategorys = new javax.swing.JComboBox<>();
        delete = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        categorys = new javax.swing.JComboBox<>();
        products = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        addProduct = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        newCategory = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        addCategory = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel10.setText("Gérer les produites");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jLabel1.setText("Généralités");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("Nom du produit");

        product_name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        product_name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        product_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_nameActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("et recommandé pour être unique.");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("Description");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Définissez une description du produit pour une meilleure visibilité.");

        description.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descriptionKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(description);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Définissez l’image miniature du produit. Seulement");
        jLabel15.setToolTipText("Définissez l’image miniature du produit. Seulement *.png, *.jpeg .*jpg");

        jLabel16.setBackground(new java.awt.Color(0, 0, 0));
        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("*.png,  *.jpeg, *.jpg les fichiers image sont acceptés.");
        jLabel16.setToolTipText("");

        upload.setBackground(new java.awt.Color(0, 0, 0));
        upload.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        upload.setForeground(new java.awt.Color(255, 255, 255));
        upload.setText("Télécharger IMG");
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadActionPerformed(evt);
            }
        });

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(upload)
                            .addGap(43, 43, 43))))
                .addGap(232, 232, 232))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upload, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(46, 46, 46))
        );

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel9.setText("Categorie");

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Un nom de produit est requis ");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setText("Prix de base");

        price.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        price.setForeground(new java.awt.Color(0, 153, 51));
        price.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Entrez le prix TTC.");

        possibledCategorys.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        possibledCategorys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                possibledCategorysActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(255, 0, 0));
        delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Supprimer");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        update.setBackground(new java.awt.Color(0, 204, 0));
        update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Mettre à jour");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel9))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(possibledCategorys, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(product_name)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel17)
                                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(38, 38, 38)))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(product_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(possibledCategorys, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jLabel11.setText("Détails des produites");

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel12.setText("Catégories");

        categorys.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sélectionnez une categorie", "Burger", "Rouleau de poulet", "Repas de riz", "Boisson", "Frites", "Desserts", "Cocktail moelleux", "Milk-shake" }));
        categorys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorysActionPerformed(evt);
            }
        });

        products.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sélectionnez un produit", "Burger", "Rouleau de poulet", "Repas de riz", "Boisson", "Frites", "Desserts", "Cocktail moelleux", "Milk-shake" }));
        products.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productsActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel21.setText("Produites");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(products, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categorys, 0, 329, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(categorys, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(products, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        addProduct.setBackground(new java.awt.Color(0, 102, 255));
        addProduct.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addProduct.setForeground(new java.awt.Color(255, 255, 255));
        addProduct.setText("Ajouter un produit");
        addProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 255));
        jLabel5.setText("Se déconnecter");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 204, 0));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/software-engineer.png"))); // NOI18N
        jLabel20.setText("Administrateur");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        newCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCategoryActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel22.setText("Ajouter une categorie");

        addCategory.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        addCategory.setForeground(new java.awt.Color(102, 204, 0));
        addCategory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/add-product.png"))); // NOI18N
        addCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addCategoryMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel22)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(newCategory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel20))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(addCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(234, 234, 234))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void categorysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorysActionPerformed
        initProducts();
        selectProduct();
    }//GEN-LAST:event_categorysActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        Login dash = new Login();
        dash.setVisible(true);  // use setVisible(true) instead of show()
        this.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void addProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductActionPerformed

        String productName = product_name.getText();
        String productCategory = (String) possibledCategorys.getSelectedItem();
        String productDescription = description.getText();
        String productPrice = price.getText();

        // Generate a random product_id (3 to 4 digits)
        int productId = ThreadLocalRandom.current().nextInt(100, 10000); // Adjust the range as needed

        // Database connection parameters
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";

        // SQL query to insert a new product with image path
        String insertQuery = "INSERT INTO products (product_id, product_name, category, description, price, image_path) VALUES (?, ?, ?, ?, ?, ?)";

        if (selectedFilePath != null && !selectedFilePath.isEmpty()) {
            try (
                    // Establish a database connection
                    Connection connection = DriverManager.getConnection(url, username, password); // Prepare the SQL statement
                     PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Set parameters for the prepared statement
                preparedStatement.setInt(1, productId);
                preparedStatement.setString(2, productName);
                preparedStatement.setString(3, productCategory);
                preparedStatement.setString(4, productDescription);
                preparedStatement.setString(5, productPrice);

                // Set the image path in the database
                preparedStatement.setString(6, selectedFilePath);

                // Execute the update
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the product was added successfully
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Produit ajouté avec succès!");
                    initCombobox();
                } else {
                    JOptionPane.showMessageDialog(this, "Echec de l’ajout du produit. Veuillez réessayer.");
                }

            } catch (SQLException ex) {
                // Handle any SQL exceptions
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données. Veuillez vérifier votre connexion.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Aucun fichier sélectionné. Veuillez choisir un fichier image.");
        }
    }//GEN-LAST:event_addProductActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        String productName = product_name.getText();
        String productCategory = (String) possibledCategorys.getSelectedItem();
        String productDescription = description.getText();
        String productPrice = price.getText();

        // Choose a file using JFileChooser for the new image
        JFileChooser fileChooser = new JFileChooser(Constantes.pathProject + "\\product image");
        fileChooser.showOpenDialog(null);
        File selectedFile = fileChooser.getSelectedFile();

        // Database connection parameters
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";

        // SQL query to update an existing product
        String updateQuery = "UPDATE products SET category=?, description=?, price=?, image_path=? WHERE product_name=?";

        try (
                // Establish a database connection
                Connection connection = DriverManager.getConnection(url, username, password); // Prepare the SQL statement
                 PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            // Set parameters for the prepared statement
            preparedStatement.setString(1, productCategory);
            preparedStatement.setString(2, productDescription);
            preparedStatement.setString(3, productPrice);

            // If a new image is selected, save it locally and update the image path
            if (selectedFile != null) {
                String localImagePath = Constantes.pathProject + "\\product image\\local_img.jpg";
                Files.copy(selectedFile.toPath(), Paths.get(localImagePath), StandardCopyOption.REPLACE_EXISTING);
                preparedStatement.setString(4, localImagePath);
            } else {
                // If no new image is selected, keep the existing image path
                preparedStatement.setString(4, image.getText());
            }

            preparedStatement.setString(5, productName);

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the product was updated successfully
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Produit mis à jour avec succès!");
                initCombobox();
            } else {
                JOptionPane.showMessageDialog(this, "Echec de la mise à jour du produit. Veuillez réessayer.");
            }

        } catch (SQLException | IOException ex) {
            // Handle any SQL or IO exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données. Veuillez vérifier votre connexion.");
        }
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        String productName = product_name.getText();
        String productCategory = (String) categorys.getSelectedItem();

        // Database connection parameters
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";

        // SQL query to delete an existing product
        String deleteQuery = "DELETE FROM products WHERE product_name=? AND category=?";

        try (
                // Establish a database connection
                Connection connection = DriverManager.getConnection(url, username, password); // Prepare the SQL statement
                 PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            // Set parameters for the prepared statement
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, productCategory);

            // Execute the delete
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the product was deleted successfully
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Produit supprimé avec succès!");
                initCombobox();
            } else {
                JOptionPane.showMessageDialog(this, "Impossible de supprimer le produit. Veuillez vérifier le nom et la catégorie du produit.");
            }

        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données. Veuillez vérifier votre connexion.");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void initCombobox() {
        initCategorys();
        initProducts();
        selectProduct();
    }

    private void initProducts() {
        String category = (String) categorys.getSelectedItem();
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";

        // SQL query to delete an existing product
        String categorieQuery = "SELECT distinct `product_name` FROM `products` WHERE category=?;";
        try (
                // Establish a database connection
                Connection connection = DriverManager.getConnection(url, username, password); // Prepare the SQL statement
                 PreparedStatement preparedStatement = connection.prepareStatement(categorieQuery)) {
            preparedStatement.setString(1, category);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> list = new ArrayList();
            while (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                list.add(productName);
            }
            products.setModel(new javax.swing.DefaultComboBoxModel<>(list.toArray(new String[]{})));
            products.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    productsActionPerformed(evt);
                }
            });
            preparedStatement.close();
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données. Veuillez vérifier votre connexion.");
        }
    }

    private void initCategorys() {
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";

        // SQL query to delete an existing product
        String categorieQuery = "SELECT distinct `category` FROM `products` WHERE 1;";
        try (
                // Establish a database connection
                Connection connection = DriverManager.getConnection(url, username, password); // Prepare the SQL statement
                 PreparedStatement preparedStatement = connection.prepareStatement(categorieQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> list = new ArrayList();
            while (resultSet.next()) {
                String categoryName = resultSet.getString("category");
                list.add(categoryName);
            }
            categorys.setModel(new javax.swing.DefaultComboBoxModel<>(list.toArray(new String[]{})));
            categorys.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    categorysActionPerformed(evt);
                }
            });
            possibledCategorys.setModel(new javax.swing.DefaultComboBoxModel<>(list.toArray(new String[]{})));
            possibledCategorys.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    possibledCategorysActionPerformed(evt);
                }
            });
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données. Veuillez vérifier votre connexion.");
        }
    }

    private void selectProduct() {
        String product = (String) products.getSelectedItem();
        // Database connection parameters
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";

        // SQL query to delete an existing product
        String categorieQuery1 = "SELECT * FROM `products` WHERE `product_name`=?;";

        try (
                // Establish a database connection
                Connection connection = DriverManager.getConnection(url, username, password); // Prepare the SQL statement
                 PreparedStatement preparedStatement1 = connection.prepareStatement(categorieQuery1)) {
            preparedStatement1.setString(1, product);

            
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            String productName = resultSet.getString("product_name");
            String productDescription = resultSet.getString("description");
            String productImage = Constantes.pathProject + resultSet.getString("image_path");
            String productPrice = resultSet.getString("price");
            String productCategory = resultSet.getString("category");
            product_name.setText(productName);
            price.setText(productPrice);
            description.setText(productDescription);
            possibledCategorys.setSelectedItem(productCategory);
            ImageIcon icon = new ImageIcon(productImage);
            if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
                icon = new ImageIcon(Constantes.pathProject + "\\add-product-image.jpg");
            }
            Image img = icon.getImage();
            Image newImg = img.getScaledInstance(220, 220, Image.SCALE_SMOOTH); // Scale the image
            ImageIcon newIcon = new ImageIcon(newImg);
            image.setIcon(newIcon);
            update.setEnabled(false);
            delete.setEnabled(true);
            addProduct.setEnabled(false);
            newCategory.setText("");
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données. Veuillez vérifier votre connexion.");
        }
    }

    private void updatePossibledCategorys(String defaultCategory) {
        if (defaultCategory == null) {
            defaultCategory = (String) possibledCategorys.getSelectedItem();
        }
        // Database connection parameters
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";

        // SQL query to delete an existing product
        String categorieQuery1 = "SELECT distinct `product_name` FROM `products` WHERE `category`=?;";

        try (
                // Establish a database connection
                Connection connection = DriverManager.getConnection(url, username, password); // Prepare the SQL statement
                 PreparedStatement preparedStatement1 = connection.prepareStatement(categorieQuery1)) {
            preparedStatement1.setString(1, defaultCategory);

            List<String> list = new ArrayList();
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                String categoryName = resultSet.getString("product_name");
                list.add(categoryName);
            }
            list.addAll(this.newCategorys);
            possibledCategorys.setModel(new javax.swing.DefaultComboBoxModel<>(list.toArray(new String[]{})));
            possibledCategorys.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    possibledCategorysActionPerformed(evt);
                }
            });
            resultSet.close();
            preparedStatement1.close();
            connection.close();
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données. Veuillez vérifier votre connexion.");
        }
        possibledCategorys.setSelectedItem(defaultCategory);
    }

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        // TODO add your handling code here:
        AdminPage dash = new AdminPage();
        dash.setVisible(true);  // use setVisible(true) instead of show()
        this.setVisible(false);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadActionPerformed
        // Specify the initial directory
        String initialDirectory = Constantes.pathProject + "\\Product Image";

        // Create the file chooser with the initial directory
        JFileChooser chooser = new JFileChooser(initialDirectory);
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (selectedFile != null) {
                selectedFilePath = selectedFile.getAbsolutePath();
                try {
                    BufferedImage bufferedImage = ImageIO.read(selectedFile);
                    if (bufferedImage != null) {
                        Image scaledImage = bufferedImage.getScaledInstance(220, 220, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(scaledImage);
                        image.setIcon(icon);
                        update.setEnabled(true);
                    } else {
                        // Handle the case where the selected file is not an image
                        System.err.println("Selected file is not a valid image.");
                    }
                } catch (IOException ex) {
                    // Handle the case where an error occurs during file reading
                    Logger.getLogger(Product.class.getName()).log(Level.SEVERE, "Erreur lors de la lecture du fichier image", ex);
                    // Provide user feedback about the error
                    JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier image: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Handle the case where no file is selected
                System.err.println("Aucun fichier sélectionné.");
            }
        } else {
            // Handle the case where the user cancels file selection
            System.out.println("Sélection de fichiers annulée par l’utilisateur.");
        }
    }//GEN-LAST:event_uploadActionPerformed

    private void productsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productsActionPerformed
        selectProduct();
    }//GEN-LAST:event_productsActionPerformed

    private void possibledCategorysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_possibledCategorysActionPerformed
        update.setEnabled(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_possibledCategorysActionPerformed

    private void addCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCategoryMouseClicked
        newCategorys.add(newCategory.getText());

        updatePossibledCategorys(newCategory.getText());
        update.setEnabled(true);
        newCategory.setText("");

    }//GEN-LAST:event_addCategoryMouseClicked

    private void newCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newCategoryActionPerformed

    private void product_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_nameActionPerformed
        String product = (String) products.getSelectedItem();
        String name = product_name.getText();
        if (!name.equals(product)) {
            addProduct.setEnabled(true);
            update.setEnabled(false);
            delete.setEnabled(false);
        }
        else{
            addProduct.setEnabled(false);
            update.setEnabled(true);
            delete.setEnabled(true);
        }
    }//GEN-LAST:event_product_nameActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        update.setEnabled(true);
    }//GEN-LAST:event_priceActionPerformed

    private void descriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionKeyReleased
        update.setEnabled(true);
    }//GEN-LAST:event_descriptionKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            Product productWindow = new Product();
            productWindow.setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addCategory;
    private javax.swing.JButton addProduct;
    private javax.swing.JComboBox<String> categorys;
    private javax.swing.JButton delete;
    private javax.swing.JTextPane description;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField newCategory;
    private javax.swing.JComboBox<String> possibledCategorys;
    private javax.swing.JTextField price;
    private javax.swing.JTextField product_name;
    private javax.swing.JComboBox<String> products;
    private javax.swing.JButton update;
    private javax.swing.JButton upload;
    // End of variables declaration//GEN-END:variables
}
