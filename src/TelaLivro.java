import model.Sessao;
import dao.LivroDAO;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;


public class TelaLivro extends javax.swing.JFrame {
    
    private TelaMenu menu;
    
    public TelaLivro(TelaMenu menu) {
    this();           
    this.menu = menu; 
}
    
    private void configurarTabelaEdicao() {
    javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(
        new Object[][]{},
        new String[]{"ID", "Título", "Gênero", "Qtde."}
    ) {
        @Override
        public boolean isCellEditable(int row, int col) {
            boolean podeEditar = "ADMIN".equals(Sessao.perfil) || "BIBLIOTECARIO".equals(Sessao.perfil);
            return podeEditar && col == 3; // só Qtde.
        }
    };

    jTable1.setModel(model);
}
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaLivro.class.getName());

public TelaLivro() {
    initComponents();

    System.out.println("TelaLivro abriu com perfil = [" + Sessao.perfil + "]");

    configurarTabelaEdicao();
    configurarPermissaoLivro();
    carregarTabelaLivros();
}
    
public void configurarPermissaoLivro() {
    boolean podeEditar = "ADMIN".equals(Sessao.perfil) || "BIBLIOTECARIO".equals(Sessao.perfil);

    
    btnMenu.setEnabled(true);

    
    btnSalvar.setEnabled(podeEditar);
    btnExcluir.setEnabled(podeEditar);
    btnBuscar.setEnabled(true);
}
 
private void carregarTabelaLivros() {
    try {
        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
        m.setRowCount(0);

        ResultSet rs = LivroDAO.listar();
        while (rs.next()) {
            m.addRow(new Object[] {
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("genero"),
                rs.getInt("quantidade")
            });
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void carregarTabelaLivrosFiltrado(String titulo, String genero, Integer qtdMin) {
    try {
        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
        m.setRowCount(0);

        ResultSet rs = LivroDAO.buscar(titulo, genero, qtdMin);
        while (rs.next()) {
            m.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("genero"),
                rs.getInt("quantidade")
            });
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao buscar: " + e.getMessage());
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Titulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Genero = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Quantidade = new javax.swing.JTextField();
        btnMenu = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 38)); // NOI18N
        jLabel1.setText("Cadastro Livro");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Título:");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setText("Gênero:");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setText("Qtde. Disponível:");

        btnMenu.setText("MENU");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Título", "Gênero", "Qtde."
            }
        ));
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(226, 226, 226))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Genero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
    if (menu != null) {
        menu.setVisible(true);
    } else {
        // caso a tela tenha sido aberta sem passar o menu
        TelaMenu m = new TelaMenu();
        m.configurarPermissoes();
        m.setVisible(true);
    }
    this.dispose();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
         try {
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um livro na tabela.");
            return;
        }

        int id = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
        LivroDAO.excluirPorId(id);

        JOptionPane.showMessageDialog(this, "Livro excluído!");
        carregarTabelaLivros();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
         try {
        boolean podeEditar = "ADMIN".equals(Sessao.perfil) || "BIBLIOTECARIO".equals(Sessao.perfil);
        if (!podeEditar) {
            JOptionPane.showMessageDialog(this, "Você não tem permissão para salvar.");
            return;
        }

        
        if (jTable1.isEditing()) {
            jTable1.getCellEditor().stopCellEditing();
        }

        int row = jTable1.getSelectedRow();

       
        if (row != -1) {
            int id = Integer.parseInt(jTable1.getValueAt(row, 0).toString());
            int novaQtd = Integer.parseInt(jTable1.getValueAt(row, 3).toString());

            if (novaQtd < 0) {
                JOptionPane.showMessageDialog(this, "Quantidade não pode ser negativa.");
                return;
            }

            LivroDAO.atualizarQuantidade(id, novaQtd);
            JOptionPane.showMessageDialog(this, "Quantidade atualizada!");
            carregarTabelaLivros();
            return;
        }

        // CASO 2: sem linha selecionada -> cadastrar livro novo (INSERT)
        String titulo = Titulo.getText().trim();
        String genero = Genero.getText().trim();
        int qtd = Integer.parseInt(Quantidade.getText().trim());

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o título.");
            return;
        }
        if (qtd < 0) {
            JOptionPane.showMessageDialog(this, "Quantidade não pode ser negativa.");
            return;
        }

        LivroDAO.inserir(titulo, genero, qtd);
        JOptionPane.showMessageDialog(this, "Livro cadastrado!");

        Titulo.setText("");
        Genero.setText("");
        Quantidade.setText("");

        carregarTabelaLivros();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Quantidade inválida (digite um número).");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    String titulo = Titulo.getText().trim();
    String genero = Genero.getText().trim();

    Integer qtdMin = null;
    String qtdTxt = Quantidade.getText().trim();

    if (!qtdTxt.isEmpty()) {
        try {
            qtdMin = Integer.parseInt(qtdTxt);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida. Digite um número.");
            return;
        }
    }

       carregarTabelaLivrosFiltrado(titulo, genero, qtdMin);
    }//GEN-LAST:event_btnBuscarActionPerformed

 
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
        java.awt.EventQueue.invokeLater(() -> new TelaLivro().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Genero;
    private javax.swing.JTextField Quantidade;
    private javax.swing.JTextField Titulo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
