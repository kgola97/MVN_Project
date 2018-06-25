/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package applicationbiblio;
package pl.pkr.kgola;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author artur
 */
public class ScaneDocument extends BiblioItem {

    private static int index = 1;                  // indeks pozycji w ComboBox, która określa typ poozycji bibliograficznej, tj. "Skanowany"
    private static JLabel jLabel = new JLabel();   // etykieta, na której wyświetlamy obrazy
    private static JScrollPane displayComponent;    // komponent (ScrollPane), na którym wyświetlamy pozycje bibliograficzną - etykietę z obrazkiem
    private static JComboBox combo;                // do wyświetlenia typu dokumentu w oknie aplikacji

    
    public ScaneDocument(String fileName, String type) {
        super(fileName, type);
    }

    public static void setComponents(JScrollPane sp, JComboBox c)
    {
        displayComponent = sp;
        combo = c;
    }
    
    @Override
    public void show() {
        jLabel.setIcon(new ImageIcon(fileName));
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        displayComponent.getViewport().setView(jLabel);
        combo.setSelectedIndex(index);
    }

}
