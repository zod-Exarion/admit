
import java.io.File;
import java.io.IOException;

public class frontEnd extends javax.swing.JFrame {
    public frontEnd() {
        initComponents();
    }

    private void layerComponents(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jButton1)
                                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(145, 145, 145)
                                                .addComponent(jButton1)))
                                .addContainerGap(193, Short.MAX_VALUE))
        );

        pack();
    }

    private void initComponents() {

        //Component initialization
        {
            jScrollPane3 = new javax.swing.JScrollPane();
            jEditorPane2 = new javax.swing.JEditorPane();
            jButton1 = new javax.swing.JButton();
        }

        //File to modify
        newFile = new FileSystem("newFile.txt");


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jScrollPane3.setViewportView(jEditorPane2);


        //Button functionality
        jButton1.setText("Save");
        jEditorPane2.setText(newFile.readContent());
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        layerComponents();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        var v = newFile.override(jEditorPane2.getText());

    }

    // DO NOT CHANGE
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(frontEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frontEnd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private FileSystem newFile;
    // End of variables declaration
}
