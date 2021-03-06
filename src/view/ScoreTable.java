package view;

import model.ScoreResult;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class ScoreTable extends JTable {
    private DefaultTableModel dtm;
    private List<ScoreResult> results;

    public ScoreTable(List<ScoreResult> results) {
        this.results = results;
        dtm = new DefaultTableModel();
        String[] columnNames = {"NAME", "DIFFICULTY", "DATE", "SCORE"};
        for (String columnName : columnNames) {
            dtm.addColumn(columnName);
        }
        for(ScoreResult result : results) {
            Vector v = result.returnVec();
            dtm.addRow(v);
        }
        setModel(dtm);
        setRowHeight(25);
        getColumnModel().getColumn(0).setPreferredWidth(80);
        getColumnModel().getColumn(1).setPreferredWidth(80);
        getColumnModel().getColumn(2).setPreferredWidth(100);
        getColumnModel().getColumn(3).setPreferredWidth(50);

        setShowGrid(false);
        setOpaque(false);
        setEnabled(false);
    }

    public void removeData() {
        for (int i = 0; i < results.size(); i++)
            for(int j = 0; j < 4; j++) {
                dtm.setValueAt("", i, j);
            }
    }
}
