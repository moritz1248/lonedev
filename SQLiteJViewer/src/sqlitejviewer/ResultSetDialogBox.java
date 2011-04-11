/*
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ResultSetDialogBox.java
 *
 * Created on 10-Apr-2011, 18:04:50
 */

package sqlitejviewer;

import javax.swing.JFrame;

/**
 *
 * @author Richard
 */
public class ResultSetDialogBox extends javax.swing.JDialog {
    private String tableName;
    /** Creates new form ResultSetDialogBox */
    public ResultSetDialogBox(JFrame parent, boolean modal, String tableName) {
        super(parent, modal);
        this.tableName = tableName;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultSetPanel = new ResultSetPanel(tableName);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultSetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resultSetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sqlitejviewer.ResultSetPanel resultSetPanel;
    // End of variables declaration//GEN-END:variables

}
