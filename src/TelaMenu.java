import model.Sessao;

public class TelaMenu extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaMenu.class.getName());

    public TelaMenu() {
        initComponents();
    }
    
    public void configurarPermissoes() {
    // Somente ADMIN pode ver o cadastro de login
    btnCadastroLogin.setVisible(false);

    // Livro só ADMIN e BIBLIOTECARIO
    btnCadastroLivro.setEnabled(false);

    if ("ADMIN".equals(Sessao.perfil)) {
    btnCadastroLogin.setVisible(true);
    btnCadastroLivro.setEnabled(true);
} else if ("BIBLIOTECARIO".equals(Sessao.perfil)) {
    btnCadastroLivro.setEnabled(true);
} else if ("ATENDENTE".equals(Sessao.perfil)) {
    btnCadastroLivro.setEnabled(true); // pode abrir para consultar
    btnCadastroLogin.setVisible(false);
}
    
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCadastroCliente = new javax.swing.JButton();
        btnCadastroLivro = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnCadastroLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 38)); // NOI18N
        jLabel1.setText("MENU");

        btnCadastroCliente.setText("CADASTRO CLIENTE");
        btnCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroClienteActionPerformed(evt);
            }
        });

        btnCadastroLivro.setText("CADASTRO LIVRO");
        btnCadastroLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroLivroActionPerformed(evt);
            }
        });

        btnConsulta.setText("CONSULTA");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        btnCadastroLogin.setText("CADASTRO LOGIN");
        btnCadastroLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCadastroLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCadastroLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(96, 96, 96))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(btnCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCadastroLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCadastroLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroClienteActionPerformed
        TelaCliente t = new TelaCliente(this);
        t.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCadastroClienteActionPerformed

    private void btnCadastroLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroLivroActionPerformed
        TelaLivro t = new TelaLivro(this);
    t.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_btnCadastroLivroActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        TelaConsulta t = new TelaConsulta(this);
    t.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnCadastroLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroLoginActionPerformed
        TelaAdmin t = new TelaAdmin(this);
    t.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_btnCadastroLoginActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaMenu().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastroCliente;
    private javax.swing.JButton btnCadastroLivro;
    private javax.swing.JButton btnCadastroLogin;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
