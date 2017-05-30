/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasync.vistas;

import java.awt.CardLayout;
import reasync.vistas.controladores.ReaSyncController;

/**
 *
 * @author jdiaz
 */
public class ReaSync extends javax.swing.JFrame {

    /**
     * Creates new form ReaSync
     */
    public ReaSync() {
        initComponents();
        myinitComponents();
    }

    public void myinitComponents() {
        ReaSyncController control = new ReaSyncController(this);
        initActionListeners(control);
        initMouseListeners(control);
    }

    private void initActionListeners(ReaSyncController control) {
        chooseDirectoryButton.addActionListener(control.getActionController());
        saveUrlButton.addActionListener(control.getActionController());
        connectServerButton.addActionListener(control.getActionController());
        disconectServerButton.addActionListener(control.getActionController());
        syncNowButton.addActionListener(control.getActionController());
    }

    private void initMouseListeners(ReaSyncController control) {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuPanel = new javax.swing.JPanel();
        servicesMenuButton = new javax.swing.JButton();
        syncMenuButton = new javax.swing.JButton();
        statusMenuButton = new javax.swing.JButton();
        logMenuButton = new javax.swing.JButton();
        principalContainerPanel = new javax.swing.JPanel();
        homeContainerPanel = new javax.swing.JPanel();
        homeBannerPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        homeBodyPanel = new javax.swing.JPanel();
        servicesContainerPanel = new javax.swing.JPanel();
        servicesBannerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        servicesBodyPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        urlServerField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        portServerField = new javax.swing.JTextField();
        connectServerButton = new javax.swing.JButton();
        statusConnectionServerLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        deviceNameField = new javax.swing.JTextField();
        disconectServerButton = new javax.swing.JButton();
        syncContainerPanel = new javax.swing.JPanel();
        syncBannerPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        syncBodyPanel = new javax.swing.JPanel();
        directoryContainerPanel = new javax.swing.JPanel();
        chooseDirectoryButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        urlDirectoryLabel = new javax.swing.JLabel();
        saveUrlButton = new javax.swing.JButton();
        syncContainerPannel = new javax.swing.JPanel();
        syncServiceBannerPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        syncServiceBodyPannel = new javax.swing.JPanel();
        syncNowButton = new javax.swing.JButton();
        startAutoSyncButton = new javax.swing.JButton();
        stopAutoSyncButton = new javax.swing.JButton();
        logContainerPanel = new javax.swing.JPanel();
        logTitlePanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        logContainerPanel1 = new javax.swing.JPanel();
        logScroll = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(650, 450));
        setMinimumSize(new java.awt.Dimension(650, 450));
        setPreferredSize(new java.awt.Dimension(650, 450));

        menuPanel.setBackground(new java.awt.Color(255, 153, 0));
        menuPanel.setLayout(new java.awt.GridLayout(1, 4));

        servicesMenuButton.setBackground(new java.awt.Color(255, 153, 0));
        servicesMenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reasync/vistas/imagenes/database.png"))); // NOI18N
        servicesMenuButton.setBorderPainted(false);
        servicesMenuButton.setContentAreaFilled(false);
        servicesMenuButton.setDefaultCapable(false);
        servicesMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                servicesMenuButtonActionPerformed(evt);
            }
        });
        menuPanel.add(servicesMenuButton);

        syncMenuButton.setBackground(new java.awt.Color(255, 153, 0));
        syncMenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reasync/vistas/imagenes/dir.png"))); // NOI18N
        syncMenuButton.setBorderPainted(false);
        syncMenuButton.setContentAreaFilled(false);
        syncMenuButton.setDefaultCapable(false);
        syncMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syncMenuButtonActionPerformed(evt);
            }
        });
        menuPanel.add(syncMenuButton);

        statusMenuButton.setBackground(new java.awt.Color(255, 153, 0));
        statusMenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reasync/vistas/imagenes/statuslogo.png"))); // NOI18N
        statusMenuButton.setBorderPainted(false);
        statusMenuButton.setContentAreaFilled(false);
        statusMenuButton.setDefaultCapable(false);
        statusMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusMenuButtonActionPerformed(evt);
            }
        });
        menuPanel.add(statusMenuButton);

        logMenuButton.setBackground(new java.awt.Color(255, 153, 0));
        logMenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reasync/vistas/imagenes/file.png"))); // NOI18N
        logMenuButton.setBorderPainted(false);
        logMenuButton.setContentAreaFilled(false);
        logMenuButton.setDefaultCapable(false);
        logMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logMenuButtonActionPerformed(evt);
            }
        });
        menuPanel.add(logMenuButton);

        getContentPane().add(menuPanel, java.awt.BorderLayout.PAGE_END);

        principalContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        principalContainerPanel.setLayout(new java.awt.CardLayout());

        homeContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        homeContainerPanel.setLayout(new java.awt.BorderLayout());

        homeBannerPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setText("ReaSync");

        javax.swing.GroupLayout homeBannerPanelLayout = new javax.swing.GroupLayout(homeBannerPanel);
        homeBannerPanel.setLayout(homeBannerPanelLayout);
        homeBannerPanelLayout.setHorizontalGroup(
            homeBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeBannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeBannerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 515, Short.MAX_VALUE))
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        homeBannerPanelLayout.setVerticalGroup(
            homeBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeBannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        homeContainerPanel.add(homeBannerPanel, java.awt.BorderLayout.PAGE_START);

        homeBodyPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout homeBodyPanelLayout = new javax.swing.GroupLayout(homeBodyPanel);
        homeBodyPanel.setLayout(homeBodyPanelLayout);
        homeBodyPanelLayout.setHorizontalGroup(
            homeBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        homeBodyPanelLayout.setVerticalGroup(
            homeBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );

        homeContainerPanel.add(homeBodyPanel, java.awt.BorderLayout.CENTER);

        principalContainerPanel.add(homeContainerPanel, "homeContainerPanel");

        servicesContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        servicesContainerPanel.setLayout(new java.awt.BorderLayout());

        servicesBannerPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Conexión con el servidor");

        javax.swing.GroupLayout servicesBannerPanelLayout = new javax.swing.GroupLayout(servicesBannerPanel);
        servicesBannerPanel.setLayout(servicesBannerPanelLayout);
        servicesBannerPanelLayout.setHorizontalGroup(
            servicesBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(servicesBannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(servicesBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(servicesBannerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 448, Short.MAX_VALUE)))
                .addContainerGap())
        );
        servicesBannerPanelLayout.setVerticalGroup(
            servicesBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(servicesBannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        servicesContainerPanel.add(servicesBannerPanel, java.awt.BorderLayout.PAGE_START);

        servicesBodyPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Dirección del servidor:");

        urlServerField.setText("127.0.0.1");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Puerto:");

        portServerField.setText("9000");
        portServerField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portServerFieldActionPerformed(evt);
            }
        });

        connectServerButton.setText("Conectar");
        connectServerButton.setActionCommand("connectServerButton");

        statusConnectionServerLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        statusConnectionServerLabel.setText("No estas conectado con el servidor de ReaSync");
        statusConnectionServerLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombre Identificador:");

        deviceNameField.setText("July");

        disconectServerButton.setText("Desconectar");
        disconectServerButton.setActionCommand("disconectServerButton");

        javax.swing.GroupLayout servicesBodyPanelLayout = new javax.swing.GroupLayout(servicesBodyPanel);
        servicesBodyPanel.setLayout(servicesBodyPanelLayout);
        servicesBodyPanelLayout.setHorizontalGroup(
            servicesBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(servicesBodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(servicesBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(servicesBodyPanelLayout.createSequentialGroup()
                        .addGroup(servicesBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(connectServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(servicesBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(urlServerField)
                                .addComponent(jLabel3)
                                .addComponent(portServerField, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
                        .addGap(18, 62, Short.MAX_VALUE)
                        .addGroup(servicesBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                            .addComponent(disconectServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deviceNameField)))
                    .addComponent(statusConnectionServerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        servicesBodyPanelLayout.setVerticalGroup(
            servicesBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(servicesBodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(servicesBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(servicesBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlServerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deviceNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(portServerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(servicesBodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectServerButton)
                    .addComponent(disconectServerButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(statusConnectionServerLabel)
                .addGap(62, 62, 62))
        );

        servicesContainerPanel.add(servicesBodyPanel, java.awt.BorderLayout.CENTER);

        principalContainerPanel.add(servicesContainerPanel, "servicesContainerPanel");

        syncContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        syncContainerPanel.setLayout(new java.awt.BorderLayout());

        syncBannerPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Elija la carpeta de sincronización");

        javax.swing.GroupLayout syncBannerPanelLayout = new javax.swing.GroupLayout(syncBannerPanel);
        syncBannerPanel.setLayout(syncBannerPanelLayout);
        syncBannerPanelLayout.setHorizontalGroup(
            syncBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(syncBannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(syncBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(syncBannerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 374, Short.MAX_VALUE))
                    .addComponent(jSeparator3))
                .addContainerGap())
        );
        syncBannerPanelLayout.setVerticalGroup(
            syncBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(syncBannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        syncContainerPanel.add(syncBannerPanel, java.awt.BorderLayout.PAGE_START);

        syncBodyPanel.setBackground(new java.awt.Color(255, 255, 255));
        syncBodyPanel.setLayout(new java.awt.GridLayout(1, 0));

        directoryContainerPanel.setBackground(new java.awt.Color(255, 255, 255));

        chooseDirectoryButton.setText("Elegir Carpeta");
        chooseDirectoryButton.setActionCommand("chooseDirectoryButton");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("La ruta especificada es:");

        urlDirectoryLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        urlDirectoryLabel.setText("ruta");

        saveUrlButton.setText("Guardar Ruta");
        saveUrlButton.setActionCommand("saveUrlButton");
        saveUrlButton.setEnabled(false);

        javax.swing.GroupLayout directoryContainerPanelLayout = new javax.swing.GroupLayout(directoryContainerPanel);
        directoryContainerPanel.setLayout(directoryContainerPanelLayout);
        directoryContainerPanelLayout.setHorizontalGroup(
            directoryContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directoryContainerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(directoryContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(directoryContainerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 193, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directoryContainerPanelLayout.createSequentialGroup()
                        .addGroup(directoryContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(saveUrlButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(urlDirectoryLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chooseDirectoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        directoryContainerPanelLayout.setVerticalGroup(
            directoryContainerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directoryContainerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chooseDirectoryButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(urlDirectoryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                .addComponent(saveUrlButton)
                .addContainerGap())
        );

        syncBodyPanel.add(directoryContainerPanel);

        syncContainerPannel.setBackground(new java.awt.Color(250, 250, 250));
        syncContainerPannel.setLayout(new java.awt.BorderLayout());

        syncServiceBannerPanel.setBackground(new java.awt.Color(245, 245, 245));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Servicio de sincronización");

        javax.swing.GroupLayout syncServiceBannerPanelLayout = new javax.swing.GroupLayout(syncServiceBannerPanel);
        syncServiceBannerPanel.setLayout(syncServiceBannerPanelLayout);
        syncServiceBannerPanelLayout.setHorizontalGroup(
            syncServiceBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(syncServiceBannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(syncServiceBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(syncServiceBannerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 151, Short.MAX_VALUE))
                    .addComponent(jSeparator5))
                .addContainerGap())
        );
        syncServiceBannerPanelLayout.setVerticalGroup(
            syncServiceBannerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(syncServiceBannerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        syncContainerPannel.add(syncServiceBannerPanel, java.awt.BorderLayout.PAGE_START);

        syncServiceBodyPannel.setBackground(new java.awt.Color(245, 245, 245));
        syncServiceBodyPannel.setLayout(new java.awt.GridLayout(1, 1));

        syncNowButton.setBackground(new java.awt.Color(255, 153, 51));
        syncNowButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reasync/vistas/imagenes/transfer.png"))); // NOI18N
        syncNowButton.setToolTipText("Iniciar Sincronización ahora");
        syncNowButton.setActionCommand("syncNowButton");
        syncNowButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        syncNowButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        syncServiceBodyPannel.add(syncNowButton);

        startAutoSyncButton.setBackground(new java.awt.Color(255, 255, 153));
        startAutoSyncButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reasync/vistas/imagenes/play-button.png"))); // NOI18N
        startAutoSyncButton.setToolTipText("Iniciar Sincronización Automatica");
        startAutoSyncButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAutoSyncButtonActionPerformed(evt);
            }
        });
        syncServiceBodyPannel.add(startAutoSyncButton);

        stopAutoSyncButton.setBackground(new java.awt.Color(255, 255, 153));
        stopAutoSyncButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reasync/vistas/imagenes/stop-button.png"))); // NOI18N
        stopAutoSyncButton.setToolTipText("Detener Sincronización automatica");
        stopAutoSyncButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        syncServiceBodyPannel.add(stopAutoSyncButton);

        syncContainerPannel.add(syncServiceBodyPannel, java.awt.BorderLayout.CENTER);

        syncBodyPanel.add(syncContainerPannel);

        syncContainerPanel.add(syncBodyPanel, java.awt.BorderLayout.CENTER);

        principalContainerPanel.add(syncContainerPanel, "syncContainerPanel");

        logContainerPanel.setBackground(new java.awt.Color(255, 255, 255));
        logContainerPanel.setLayout(new java.awt.BorderLayout());

        logTitlePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("¿Qué ha ocurrido?");

        javax.swing.GroupLayout logTitlePanelLayout = new javax.swing.GroupLayout(logTitlePanel);
        logTitlePanel.setLayout(logTitlePanelLayout);
        logTitlePanelLayout.setHorizontalGroup(
            logTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logTitlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logTitlePanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 504, Short.MAX_VALUE))
                    .addComponent(jSeparator4))
                .addContainerGap())
        );
        logTitlePanelLayout.setVerticalGroup(
            logTitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logTitlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        logContainerPanel.add(logTitlePanel, java.awt.BorderLayout.PAGE_START);

        logContainerPanel1.setBackground(new java.awt.Color(255, 255, 255));
        logContainerPanel1.setLayout(new java.awt.GridLayout(1, 0));

        logScroll.setBackground(new java.awt.Color(255, 255, 255));

        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        logScroll.setViewportView(logTextArea);

        logContainerPanel1.add(logScroll);

        logContainerPanel.add(logContainerPanel1, java.awt.BorderLayout.CENTER);

        principalContainerPanel.add(logContainerPanel, "logContainerPanel");

        getContentPane().add(principalContainerPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void servicesMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_servicesMenuButtonActionPerformed
        CardLayout panel = (CardLayout) principalContainerPanel.getLayout();
        panel.show(principalContainerPanel, "servicesContainerPanel");
    }//GEN-LAST:event_servicesMenuButtonActionPerformed

    private void syncMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syncMenuButtonActionPerformed
        CardLayout panel = (CardLayout) principalContainerPanel.getLayout();
        panel.show(principalContainerPanel, "syncContainerPanel");
    }//GEN-LAST:event_syncMenuButtonActionPerformed

    private void statusMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusMenuButtonActionPerformed
        CardLayout panel = (CardLayout) principalContainerPanel.getLayout();
        panel.show(principalContainerPanel, "homeContainerPanel");
    }//GEN-LAST:event_statusMenuButtonActionPerformed

    private void logMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logMenuButtonActionPerformed
        CardLayout panel = (CardLayout) principalContainerPanel.getLayout();
        panel.show(principalContainerPanel, "logContainerPanel");
    }//GEN-LAST:event_logMenuButtonActionPerformed

    private void startAutoSyncButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startAutoSyncButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startAutoSyncButtonActionPerformed

    private void portServerFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portServerFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portServerFieldActionPerformed

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
            java.util.logging.Logger.getLogger(ReaSync.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReaSync.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReaSync.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReaSync.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReaSync().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton chooseDirectoryButton;
    public javax.swing.JButton connectServerButton;
    public javax.swing.JTextField deviceNameField;
    public javax.swing.JPanel directoryContainerPanel;
    public javax.swing.JButton disconectServerButton;
    public javax.swing.JPanel homeBannerPanel;
    public javax.swing.JPanel homeBodyPanel;
    public javax.swing.JPanel homeContainerPanel;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JSeparator jSeparator2;
    public javax.swing.JSeparator jSeparator3;
    public javax.swing.JSeparator jSeparator4;
    public javax.swing.JSeparator jSeparator5;
    public javax.swing.JPanel logContainerPanel;
    public javax.swing.JPanel logContainerPanel1;
    public javax.swing.JButton logMenuButton;
    public javax.swing.JScrollPane logScroll;
    public javax.swing.JTextArea logTextArea;
    public javax.swing.JPanel logTitlePanel;
    public javax.swing.JPanel menuPanel;
    public javax.swing.JTextField portServerField;
    public javax.swing.JPanel principalContainerPanel;
    public javax.swing.JButton saveUrlButton;
    public javax.swing.JPanel servicesBannerPanel;
    public javax.swing.JPanel servicesBodyPanel;
    public javax.swing.JPanel servicesContainerPanel;
    public javax.swing.JButton servicesMenuButton;
    public javax.swing.JButton startAutoSyncButton;
    public javax.swing.JLabel statusConnectionServerLabel;
    public javax.swing.JButton statusMenuButton;
    public javax.swing.JButton stopAutoSyncButton;
    public javax.swing.JPanel syncBannerPanel;
    public javax.swing.JPanel syncBodyPanel;
    public javax.swing.JPanel syncContainerPanel;
    public javax.swing.JPanel syncContainerPannel;
    public javax.swing.JButton syncMenuButton;
    public javax.swing.JButton syncNowButton;
    public javax.swing.JPanel syncServiceBannerPanel;
    public javax.swing.JPanel syncServiceBodyPannel;
    public javax.swing.JLabel urlDirectoryLabel;
    public javax.swing.JTextField urlServerField;
    // End of variables declaration//GEN-END:variables
}
