/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import model.User;
import service.HotelService;

/**
 *
 * @author Lenovo
 */
public class ViewHotelAvailable extends javax.swing.JFrame {

    private User logged;
    private HotelService hotelService;

    public ViewHotelAvailable(User user) {
        initComponents();
        txtUsername.setText(user.getUsername());
        setTitle("Usuario");
        setLocationRelativeTo(null);
        hotelService = new HotelService();

        // Obtén el número de registros de la base de datos
        int numRegistros = 100;

        // Crea un JPanel para contener las tarjetas
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new GridLayout(numRegistros / 3 + 1, 3, 10, 10));

        jPanelButtons.setLayout(new GridLayout(numRegistros / 3 + 1, 3, 10, 10));

        // Crea las tarjetas dinámicamente y añádelas al JPanel
        for (int i = 0; i < numRegistros; i++) {
            // Crea un nuevo JPanel para la tarjeta
            JPanel card = new JPanel();
            card.setBorder(new EmptyBorder(10, 10, 10, 10));  // Agrega un borde vacío para simular un padding
            card.setBackground(Color.WHITE);  // Establece el color de fondo de la tarjeta
            card.setLayout(new BorderLayout());  // Establece el layout de la tarjeta

            // Crea un JLabel para el título de la tarjeta y añádelo al JPanel
            JLabel title = new JLabel("Hotel " + (i + 1));
            title.setFont(new Font("Arial", Font.BOLD, 14));  // Establece la fuente del título
            card.add(title, BorderLayout.NORTH);

            // Crea un JLabel para el contenido de la tarjeta y añádelo al JPanel
            JLabel content = new JLabel("Nombre:  " + (i + 1));
            card.add(content, BorderLayout.CENTER);

            // Crea un JTextField para el campo de texto adicional y añádelo al JPanel
            // Crea una variable final que contenga el valor de i
            final int index = i;

            // Crea un borde compuesto con un borde vacío y un borde de línea
            Border padding = new EmptyBorder(10, 10, 10, 10);
            Border borderLine = BorderFactory.createLineBorder(Color.BLACK, 1);
            Border border = new CompoundBorder(borderLine, padding);
            card.setBorder(border);

            // Agrega un MouseListener al JPanel
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Este código se ejecutará cuando se haga clic en el JPanel
                    System.out.println("Se hizo clic en la tarjeta " + (index + 1));

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // Cambia el color del borde a azul cuando el ratón entra en el JPanel
                    Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
                    Border compound = new CompoundBorder(blueBorder, padding);
                    card.setBorder(compound);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Cambia el color del borde al color inicial cuando el ratón sale del JPanel
                    card.setBorder(border);
                }
            });

            // Cambia el cursor a un puntero de mano cuando el ratón está sobre el JPanel
            card.setCursor(Cursor.getDefaultCursor().getPredefinedCursor(Cursor.HAND_CURSOR));

            cardsPanel.add(card);
        }

        // Crea un JScrollPane y añade el JPanel a él
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setPreferredSize(new Dimension(jPanelButtons.getWidth(), jPanelButtons.getHeight()));

        // Añade el JScrollPane a tu panel principal
        jPanelButtons.setLayout(new BorderLayout());
        jPanelButtons.add(scrollPane, BorderLayout.CENTER);

        // Actualiza el JPanel para mostrar los nuevos botones
        jPanelButtons.revalidate();
        jPanelButtons.repaint();
    }

    public void logger(User user) {
        logged = user;
        jLabelUser.setText("Bienvenido" + user.getUsername());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelButtons = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        btnReserve = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 889, Short.MAX_VALUE)
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Bienvenido:");

        btnReserve.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnReserve.setText("Reservar");
        btnReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReserveActionPerformed(evt);
            }
        });

        btnLogOut.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnLogOut.setText("Cerrar Sesion");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-volver-48.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(387, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReserve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(388, 388, 388))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelButtons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReserve)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLogOut)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReserveActionPerformed


    }//GEN-LAST:event_btnReserveActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        ViewLogin login = new ViewLogin();
        login.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.setVisible(false);
        ViewLogin login = new ViewLogin();
        login.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnReserve;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
