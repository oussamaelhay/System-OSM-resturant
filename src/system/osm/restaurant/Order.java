/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package system.osm.restaurant;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author OSM
 */
public class Order extends javax.swing.JFrame {

    // Declare combo box for category selection
    private javax.swing.JComboBox<String> categoryComboBox;
    private String table = "";

    public Order() {
        initComponents();
        billHeader();
        categoryComboBox = new JComboBox<>();
        populateCategoryComboBox(); // Call method to populate category combo box

        // Set layout for jPanel5
        jPanel5.setLayout(null); // Use null layout for precise positioning
        jPanel5.setPreferredSize(new Dimension(159, 35)); // Set preferred size

        // Add combo box to jPanel5 with specific size
        categoryComboBox.setBounds(0, 0, 155, 34); // Set bounds
        jPanel5.add(categoryComboBox); // Add combo box to the panel

        populateProductTable(product_table, null); // Initially populate all products

        // Attach ActionListener to categoryComboBox
        categoryComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                categoryComboBoxActionPerformed(e);
            }
        });
    }

    // Method to populate the category combo box
    private void populateCategoryComboBox() {
        categoryComboBox = new JComboBox<>();
        // Add default option
        categoryComboBox.addItem("Sélectionner une catégorie");
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String selectQuery = "SELECT DISTINCT category FROM products ORDER BY category ASC";
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectQuery)) {
                // Clear the combo box before adding categories
                while (resultSet.next()) {
                    String category = resultSet.getString("category");
                    categoryComboBox.addItem(category);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des catégories à partir de la base de données.");
        }
    }

    // Event handler for category combo box selection
    private void categoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // Get the selected category from the combo box
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        // Populate the product table based on the selected category
        populateProductTable(product_table, selectedCategory);
    }

    private void populateProductTable(JTable productTable, String selectedCategory) {
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";
        String selectQuery = "SELECT product_name AS Name FROM products";

        // If a category is selected, add a WHERE clause to filter by category
        if (selectedCategory != null && !selectedCategory.isEmpty()) {
            selectQuery += " WHERE category = ?";
        }

        try (
                Connection connection = DriverManager.getConnection(url, username, password); PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            // If a category is selected, set the category parameter
            if (selectedCategory != null && !selectedCategory.isEmpty()) {
                statement.setString(1, selectedCategory);
            }

            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel() {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return Object.class;
                }
            };
            tableModel.addColumn("Name");

            while (resultSet.next()) {
                String productName = resultSet.getString("Name");
                Vector<Object> row = new Vector<>();
                row.add(productName);
                tableModel.addRow(row);
            }

            productTable.setModel(tableModel);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données. Veuillez vérifier votre connexion.");
        }
        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && productTable.getSelectedRow() != -1) {
                    int selectedRowIndex = productTable.getSelectedRow();
                    String selectedProductName = productTable.getValueAt(selectedRowIndex, 0).toString();
                    String selectedProductDescription = getProductDescription(selectedProductName);
                    String selectedProductPrice = getProductPrice(selectedProductName); // Get product price
                    String selectedProductImagePath = getProductImagePath(selectedProductName); // Get product image path

                    product_id.setText(selectedProductName);
                    description.setText(selectedProductDescription);
                    price.setText(selectedProductPrice); // Display product price

                    // Load and display image
                    ImageIcon icon = new ImageIcon(selectedProductImagePath);
                    if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
                        icon = new ImageIcon(Constantes.pathProject + "\\add-product-image.jpg");
                    }
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(220, 220, Image.SCALE_SMOOTH); // Scale the image
                    ImageIcon newIcon = new ImageIcon(newImg);
                    image.setIcon(newIcon); // Set the scaled ImageIcon to the JLabel
                }
            }
        });
    }

    private String getProductDescription(String selectedProductName) {
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";
        String selectQuery = "SELECT description FROM products WHERE product_name = ?";

        try (
                Connection connection = DriverManager.getConnection(url, username, password); PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, selectedProductName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("description");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération de la description du produit à partir de la base de données.");
        }

        return "";
    }

    private String getProductPrice(String selectedProductName) {
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";
        String selectQuery = "SELECT price FROM products WHERE product_name = ?";

        try (
                Connection connection = DriverManager.getConnection(url, username, password); PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, selectedProductName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("price");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération du prix du produit à partir de la base de données.");
        }

        return "";
    }

    private String getProductImagePath(String selectedProductName) {
        String url = "jdbc:mysql://localhost/rms";
        String username = "root";
        String password = "";
        String selectQuery = "SELECT image_path FROM products WHERE product_name = ?";

        try (
                Connection connection = DriverManager.getConnection(url, username, password); PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, selectedProductName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Constantes.pathProject + resultSet.getString("image_path");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération du chemin d’accès à l’image du produit à partir de la base de données.");
        }

        return null;
    }

    private void billHeader() {
        // Générer un numéro de facture aléatoire
        Random random = new Random();
        int billNumber = random.nextInt(10000);

        // Obtenir la date actuelle
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        String dateFormatted = currentDateAndTime.format(
                DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy, HH:mm", Locale.FRENCH)
        );

        String html
                = "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: 'Arial', sans-serif; font-size: 12px; }"
                + "h1, h2 { text-align: center; margin: 4px 0; }"
                + ".center { text-align: center; }"
                + ".details, .items { width: 100%; border-collapse: collapse; }"
                + ".items th, .items td { border: 1px solid #000; padding: 4px; text-align: center; }"
                + ".header { margin-top: 20px; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<h1>Oussama Resto SARL</h1>"
                + "<div class='center'>N° 256, Rue Nasser</div>"
                + "<div class='center'>Temara, Rabat</div>"
                + "<div class='center'>Numéro de TVA : 12345678</div>"
                + "<h2>FACTURE FISCALE</h2>"
                + "<div class='center'><strong>Copie Originale</strong></div>"
                + "<div class='header'>"
                + "<strong>Facture N°:</strong> #" + String.format("%04d", billNumber) + "<br>"
                + "<strong>Date:</strong> " + dateFormatted
                + "</div>"
                + "<hr>"
                + "<table class='items'>"
                + "<tr><th>Article</th><th>Qté</th><th>Prix Unitaire</th><th>Total</th></tr>"
                + (table==null?"":table)
                +"</table>"
                + "<hr>"
                + "</body></html>";

        // Affichage dans un composant Swing (ex: JEditorPane)
        bill.setContentType("text/html");
        bill.setText(html);
        bill.setEditable(false); // Pour éviter les modifications
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        product_id = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextPane();
        add = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        totalItem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        currentPrice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        totalPrice = new javax.swing.JTextField();
        cash = new javax.swing.JButton();
        next = new javax.swing.JButton();
        print = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cashIn = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        change = new javax.swing.JTextField();
        currentItem = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bill = new javax.swing.JEditorPane();
        jLabel27 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1260, 720));
        setMinimumSize(new java.awt.Dimension(1260, 720));
        setPreferredSize(new java.awt.Dimension(1260, 720));
        setSize(new java.awt.Dimension(1260, 720));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1087, 643));
        jPanel1.setMinimumSize(new java.awt.Dimension(1087, 643));

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel10.setText("Commande");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel13.setText("Produits");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nom du produit"
            }
        ));
        jScrollPane3.setViewportView(product_table);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("Image");

        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setText("Description:");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setText("Nom du produit");
        jLabel3.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setText("Prix:");

        product_id.setBackground(new java.awt.Color(255, 255, 255));
        product_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        product_id.setEnabled(false);

        price.setBackground(new java.awt.Color(255, 255, 255));
        price.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        price.setForeground(new java.awt.Color(0, 153, 102));
        price.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        price.setEnabled(false);

        jScrollPane4.setMaximumSize(new java.awt.Dimension(64, 18));
        jScrollPane4.setMinimumSize(new java.awt.Dimension(64, 18));

        description.setEditable(false);
        description.setBackground(new java.awt.Color(255, 255, 255));
        description.setBorder(null);
        description.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        description.setMaximumSize(new java.awt.Dimension(213, 16));
        description.setMinimumSize(new java.awt.Dimension(213, 16));
        description.setPreferredSize(new java.awt.Dimension(213, 16));
        jScrollPane4.setViewportView(description);

        add.setBackground(new java.awt.Color(255, 102, 0));
        add.setText("Ajouter");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Nombre d’articles");

        totalItem.setEditable(false);
        totalItem.setBackground(new java.awt.Color(255, 255, 255));
        totalItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalItem.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Total de l’article :");

        currentPrice.setEditable(false);
        currentPrice.setBackground(new java.awt.Color(255, 255, 255));
        currentPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Prix actuel :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Prix total :");

        totalPrice.setEditable(false);
        totalPrice.setBackground(new java.awt.Color(255, 255, 255));
        totalPrice.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        totalPrice.setForeground(new java.awt.Color(0, 0, 255));
        totalPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cash.setBackground(new java.awt.Color(133, 187, 101));
        cash.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cash.setForeground(new java.awt.Color(255, 255, 255));
        cash.setText("En  espèces");
        cash.setToolTipText("En  espèces");
        cash.setMaximumSize(new java.awt.Dimension(75, 25));
        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });

        next.setBackground(new java.awt.Color(0, 153, 255));
        next.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        next.setForeground(new java.awt.Color(255, 255, 255));
        next.setText("Commande suivante");
        next.setToolTipText("Commande suivante");
        next.setMaximumSize(new java.awt.Dimension(75, 25));
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        print.setBackground(new java.awt.Color(255, 0, 51));
        print.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        print.setForeground(new java.awt.Color(255, 255, 255));
        print.setText("Imprimer la facture");
        print.setToolTipText("Imprimer la facture");
        print.setMaximumSize(new java.awt.Dimension(75, 25));
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Encaissement :");

        cashIn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cashIn.setForeground(new java.awt.Color(0, 0, 255));
        cashIn.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Changement:");

        change.setEditable(false);
        change.setBackground(new java.awt.Color(255, 255, 255));
        change.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        change.setForeground(new java.awt.Color(255, 0, 51));
        change.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        currentItem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        currentItem.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(currentPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(cash, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(next, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(currentItem, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalItem, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cashIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(change, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(184, 184, 184)
                                .addComponent(jLabel3))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(product_id, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(add, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(product_id, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currentItem, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalItem, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(change)
                    .addComponent(cashIn, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(print, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(next, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel15.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel15.setText("Détails des produits");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/products.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel13)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel14.setText("Facture");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        bill.setBorder(null);
        bill.setMaximumSize(new java.awt.Dimension(2147483647, 400));
        bill.setMinimumSize(new java.awt.Dimension(101, 400));
        bill.setPreferredSize(new java.awt.Dimension(101, 400));
        jScrollPane2.setViewportView(bill);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Bell.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addGap(25, 235, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Dubai Medium", 0, 14)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/enter.png"))); // NOI18N
        jLabel12.setText("Se déconnecter");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/order.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel25))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
        try {
            // Lire l’entrée
            double cashInput = Double.parseDouble(cashIn.getText());
            double totalPriceValue = Double.parseDouble(totalPrice.getText());
            double changeF = cashInput - totalPriceValue;
            change.setText(Double.toString(changeF));

            billHeader();
            String existingHTML = bill.getText();

            // Insérer les infos avant la fermeture </body>
            int bodyClose = existingHTML.lastIndexOf("</body>");
            if (bodyClose == -1) {
                bodyClose = existingHTML.length();
            }

            String detailsSection = """
				<table width='100%%' style='font-size: 12px; margin-top: 10px;'>
					<tr><td colspan='4'><hr></td></tr>
					<tr>
						<td colspan='2'><b>Total Articles</b></td>
						<td colspan='2' align='right'>%s</td>
					</tr>
					<tr>
						<td colspan='2'><b>Montant Net</b></td>
						<td colspan='2' align='right'>%.2f</td>
					</tr>
					<tr>
						<td colspan='2'><b>Espèces données</b></td>
						<td colspan='2' align='right'>%.2f</td>
					</tr>
					<tr>
						<td colspan='2'><b>Rendu</b></td>
						<td colspan='2' align='right'>%.2f</td>
					</tr>
					<tr><td colspan='4'><hr></td></tr>
				</table>
				<div style='text-align: center; font-size: 11px; margin-top: 10px;'>
					<p>Téléphone : +212 604 851 063</p>
					<p>Merci pour votre visite!</p>
				</div>
			""".formatted(
                    totalItem.getText(),
                    totalPriceValue,
                    cashInput,
                    changeF
            );

            // Reconstituer le HTML complet
            String newHTML = existingHTML.substring(0, bodyClose) + detailsSection + existingHTML.substring(bodyClose);
            bill.setText(newHTML);

            // Enregistrement dans la base
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/rms", "root", "");
            String query = "INSERT INTO bill (net, total) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, (float) totalPriceValue);
            preparedStatement.setInt(2, Integer.parseInt(totalItem.getText()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            JOptionPane.showMessageDialog(this, "Transaction terminée !");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
	}//GEN-LAST:event_cashActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        Login dash = new Login();
        dash.setVisible(true);  // use setVisible(true) instead of show()
        this.setVisible(false);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // Get the bounds of the bill TextArea
        Rectangle bounds = bill.getBounds();

        // Create a BufferedImage with the same size as the bill TextArea
        BufferedImage billImage = new BufferedImage(bounds.width, bounds.height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics context from the BufferedImage
        bill.paint(billImage.getGraphics());

        // Define the directory where the bill images will be saved
        String directoryPath = Constantes.pathProject + "\\Bill";

        // Create the directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate a unique filename using the current timestamp
        String timestamp = Long.toString(System.currentTimeMillis());
        String fileName = "bill_" + timestamp + ".png";

        // Combine directory path and file name
        String filePath = directoryPath + "\\" + fileName;

        // Save the image to file
        try {
            ImageIO.write(billImage, "png", new File(filePath));

            // Show success message
            JOptionPane.showMessageDialog(this, "Facture a économisé avec succès!");
        } catch (IOException ex) {
            // Show error message if failed to save
            JOptionPane.showMessageDialog(this, "N’a pas réussi à sauver la facture:" + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_printActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed
        Order dash = new Order();
        dash.setVisible(true);  // use setVisible(true) instead of show()
        this.setVisible(false);
    }//GEN-LAST:event_nextActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // Get the selected product name
        int selectedRowIndex = product_table.getSelectedRow();
        if (selectedRowIndex == -1) {
            // No product is selected
            JOptionPane.showMessageDialog(Order.this, "Veuillez sélectionner un produit.");
            return;
        }
        String selectedProductName = product_table.getValueAt(selectedRowIndex, 0).toString();

        // Get the selected product price
        String selectedProductPrice = getProductPrice(selectedProductName);
        if (selectedProductPrice.isEmpty()) {
            JOptionPane.showMessageDialog(Order.this, "Prix du produit introuvable.");
            return;
        }
        double pricePerItem = Double.parseDouble(selectedProductPrice);

        // Initialize totalItem and totalPrice to "0" if they are empty
        int totalItemCount = totalItem.getText().isEmpty() ? 0 : Integer.parseInt(totalItem.getText());
        double totalPriceValue = totalPrice.getText().isEmpty() ? 0.0 : Double.parseDouble(totalPrice.getText());

        // Update the currentItem spinner and totalItem TextField
        int currentItemValue = Integer.parseInt(currentItem.getText());
        totalItem.setText(Integer.toString(currentItemValue + totalItemCount));

        // Calculate the current price
        double currentPriceValue = pricePerItem * currentItemValue;
        currentPrice.setText(Double.toString(currentPriceValue)); // Show price of current item

        // Update the totalPrice TextField
        double updatedTotalPrice;
        try {
            updatedTotalPrice = currentPriceValue + Double.parseDouble(totalPrice.getText());
        } catch (NumberFormatException ex) {
            // If the totalPrice TextField is empty or not a valid double, set updatedTotalPrice to currentPriceValue
            updatedTotalPrice = currentPriceValue;
        }
        totalPrice.setText(Double.toString(updatedTotalPrice));

        // Construct the text to display in the bill
        String billText = String.format("<tr><td>%-30s</td><td>%-10s</td><td>%-10s</td><td>%-10s</td></tr>", product_id.getText(), currentItem.getText(), price.getText(), currentPrice.getText());
        table += billText;
        // Append the bill text to the existing bill content
        String currentBillContent = bill.getText();
        bill.setText(currentBillContent);
    }//GEN-LAST:event_addActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JEditorPane bill;
    private javax.swing.JButton cash;
    private javax.swing.JTextField cashIn;
    private javax.swing.JTextField change;
    private javax.swing.JTextField currentItem;
    private javax.swing.JTextField currentPrice;
    private javax.swing.JTextPane description;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton next;
    private javax.swing.JLabel price;
    private javax.swing.JButton print;
    private javax.swing.JLabel product_id;
    private javax.swing.JTable product_table;
    private javax.swing.JTextField totalItem;
    private javax.swing.JTextField totalPrice;
    // End of variables declaration//GEN-END:variables

    private void populateProductTable() {
        throw new UnsupportedOperationException("Pas encore pris en charge."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
