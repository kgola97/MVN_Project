/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package applicationbiblio;
package pl.pkr.kgola;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author artur
 */
public class TextDocument extends BiblioItem {

    private static int index = 0;    // indeks pozycji w ComboBox, która określa typ poozycji bibliograficznej, tj. "Tekstowy"
    private static JTextArea jTextArea = new JTextArea();
    private static JScrollPane displayComponent;    // komponent (ScrollPane), na którym wyświetlamy pozycje bibliograficzną - etykietę z obrazkiem
    private static JComboBox combo;                // do wyświetlenia typu dokumentu w oknie aplikacji


    public TextDocument(String fileName, String type) {
        super(fileName, type);
    }

    public static void setComponents(JScrollPane sp, JComboBox c)
    {
        displayComponent = sp;
        combo = c;
    }

    
    @Override
    public void show() {
        jTextArea.setText("");
        BufferedReader stream = null;
        try {
            stream = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = stream.readLine()) != null) {
                jTextArea.append(line);
                jTextArea.append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            try {
               stream.close();
            } catch (IOException exc) {
                System.err.println("Wywolanie close nie powiodlo sie");
            }
        } catch (Exception ae) {
            System.err.println(ae.getMessage());
        }
        displayComponent.getViewport().add(jTextArea);
        displayComponent.getViewport().setView(jTextArea);
        combo.setSelectedIndex(index);
    }

}
