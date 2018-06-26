package pl.pkr.kgola;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public interface DataReaderDAO {

    ArrayList<BiblioItem> getBiblioItems(JFileChooser fileChooser,
                                         JMenuItem jMenuItemOpen,
                                         File file,
                                         String baseFileName);

}
