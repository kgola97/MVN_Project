package pl.pkr.kgola;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataReaderDAOImpl implements DataReaderDAO {

    @Override
    public ArrayList<BiblioItem> getBiblioItems(JFileChooser fileChooser,
                                                JMenuItem jMenuItemOpen,
                                                File file,
                                                String baseFileName) {
        if (fileChooser.showOpenDialog(jMenuItemOpen) == JFileChooser.APPROVE_OPTION) {
            ObjectInputStream iStream = null;
            try {
                file = fileChooser.getSelectedFile();
                baseFileName = file.toString();
                iStream = new ObjectInputStream(new FileInputStream(baseFileName));
                return (ArrayList<BiblioItem>) iStream.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JFrameBiblio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(JFrameBiblio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JFrameBiblio.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    iStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(JFrameBiblio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return new ArrayList<>();
    }
}