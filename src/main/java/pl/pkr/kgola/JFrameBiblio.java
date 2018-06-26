//package applicationbiblio;
package pl.pkr.kgola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Position.Bias;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wzorzec strukturalny Most został opisany w pliku BiblioItem.java.
 */


/**
 * Wzorzec projektowy OBSERWATOR.
 */
interface Observer {
    void notifyObject(String type);
}

/**
 * Klasa, której obiekty są obserwowane. Obiekt tej klasy informuje o zmianie
 * dokumentu wyświetlanego na ekranie, tzn. gdy użytkownik zmieni pozycję
 * bibliograficzną z użyciem komponentu JList, to informacja o tym jest przekazywana do obiektu/obiektów
 * obserwujących. W tym programie jest jeden obiekt obserwujący, który zlicza
 * ile razy zostały wyświetlone dokumenty tekstowe oraz ile razy dokumenty
 * zeskanowane. Klasa daje możliwość dodania większej liczby obserwatorów a
 * także usunięcia wskazanego obserwatora
 */
class Counter {

    protected List<Observer> observers = new ArrayList<Observer>();

    /**
     * dodaje obserwatora do kolejki
     *
     * @param observer - obserwator, który zostanie dodany
     */
    public void addObserver(Observer observer) {

        if (observer != null) {
            observers.add(observer);
        }
    }

    /**
     * Usuwa obeserwatora z kolejki
     *
     * @param observer - obserwator, który zostanie usunięty
     */
    public void deleteObserver(Observer observer) {

        if (observer != null) {
            observers.remove(observer);
        }
    }

    /**
     * Informowanie o zmianie.
     * @param type - określa typ wyświetlanego pliku tekstowy/zeskanowany
     */
    public void notifyAllObject(String type) {
        for (Observer observer : observers) {
            if (observer instanceof Observer) {
                observer.notifyObject(type);
            }
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }
}

/**
 * Klasa implementująca interfej Observer.
 * Jest infrmowana gdy użytkownik wyświetla na ekranie jakąś pozycję biblioteczną
 * i wypisuje na ekranie statystyki - liczbę obejrzanych pozycji tekstowych i zeskanowanych
 */
class JStatisticLabel extends JLabel implements Observer {

    private int textFiles = 0;
    private int scanFiles = 0;

    public JStatisticLabel() {
        super();
    }
    
    /**
     * Resetuje liczniki.
     */
    public void reset()
    {
        textFiles = 0;
        scanFiles = 0;
        setText("Obejrzanych plików tekstowych: " + textFiles + ", skanow: " + scanFiles);
        this.repaint();
    }

    @Override
    /**
     * Służy do powiadomienia.
     * type -  określa typ pliku - tekstowy/zeskanowany
     */
    public void notifyObject(String type) {
       if (type.equals("Tekstowy") == true)
       {
           textFiles++;
       }
       else
       {
           scanFiles++;
       }
       setText("Obejrzanych plików tekstowych: " + textFiles + ", skanow: " + scanFiles);
       this.repaint();
    }

    public int getTextFiles() {
        return textFiles;
    }

    public int getScanFiles() {
        return scanFiles;
    }
}

/**
 * @author Krzysztof Gola
 * @brief Statyczna klasa fabrykująca - wzorzec projektowy STATIC FACTORY,
 * odpowiada za utworzenie odpowiedniej pozycji bibliotecznej (dokumnetu
 * tekstowego lub jego skanu)
 */
class StaticFactory {

    /**
     * Tworzy obiekt.
     * @param fileName - nazwa pliku źródłowego
     * @param type - typu pliku (tekstowy lub zdjęcie)
     * @return w zależności od parametru "type" obiekt typu ScanDocument lub TextDocument 
     */
    static BiblioItem productProcess(String fileName, String type) {
        BiblioItem product = null;
        if (type.equals("Tekstowy")) {
            product = new TextDocument(fileName, type);
        } else {
            product = new ScaneDocument(fileName, type);
        }
        return product;
    }
}

/**
 * Główna klasa aplikacji
 *
 * @author Krzysztof Gola
 */
public class JFrameBiblio extends javax.swing.JFrame 
{


    public JFrameBiblio() {
        this.baseFileName = null;
        this.current = -1;
        imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        textFilter = new FileNameExtensionFilter("TEXT FILES", "txt", "tex", "java", "c", "cpp", "py");

        fileChooser.setFileFilter(imageFilter);
        initComponents();
        enableInterface(false);
        ScaneDocument.setComponents(jScrollPanel, jComboBoxState);
        TextDocument.setComponents(jScrollPanel, jComboBoxState);
        jListItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        counter.addObserver((JStatisticLabel)jLabelStatistics);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelType = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListItems = new javax.swing.JList(listModel);
        jComboBoxState = new javax.swing.JComboBox<String>();
        jLabelType = new javax.swing.JLabel();
        jButtonPath = new javax.swing.JButton();
        jLabelPath = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jLabelStatistics = new JStatisticLabel();
        jScrollPanel = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemNew = new javax.swing.JMenuItem();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemSaveAs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuAbout = new javax.swing.JMenu();
        jMenuItemAuthors = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jListItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListItemsMousePressed(evt);
            }
        });
        jListItems.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jListItemsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jListItemsKeyReleased(evt);
            }
        });
        jListItems.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListItemsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListItems);

        jComboBoxState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tekstowy", "Zdjęcie" }));

        jLabelType.setText("Rodzaj pliku");

        jButtonPath.setText("Plik zrodlowy");
        jButtonPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPathActionPerformed(evt);
            }
        });

        jLabelPath.setText("jLabel1");

        jButtonAdd.setText("Dodaj");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonRemove.setText("Usun");
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jLabelStatistics.setText("Statystyki");

        javax.swing.GroupLayout jPanelTypeLayout = new javax.swing.GroupLayout(jPanelType);
        jPanelType.setLayout(jPanelTypeLayout);
        jPanelTypeLayout.setHorizontalGroup(
            jPanelTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanelTypeLayout.createSequentialGroup()
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTypeLayout.createSequentialGroup()
                        .addComponent(jLabelPath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanelTypeLayout.createSequentialGroup()
                        .addComponent(jLabelType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxState, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanelTypeLayout.createSequentialGroup()
                        .addComponent(jButtonPath)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelTypeLayout.createSequentialGroup()
                        .addComponent(jLabelStatistics)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelTypeLayout.setVerticalGroup(
            jPanelTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTypeLayout.createSequentialGroup()
                .addComponent(jButtonPath)
                .addGap(11, 11, 11)
                .addComponent(jLabelPath)
                .addGap(18, 18, 18)
                .addGroup(jPanelTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelType)
                    .addComponent(jComboBoxState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabelStatistics)
                .addGap(30, 30, 30)
                .addGroup(jPanelTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonRemove))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuFile.setText("Plik");
        jMenuFile.setActionCommand("Pliki");

        jMenuItemNew.setText("Nowy");
        jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNewActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemNew);

        jMenuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemOpen.setText("Otworz ...");
        jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpen);

        jMenuItemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSave.setText("Zapisz");
        jMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSave);

        jMenuItemSaveAs.setText("Zapisz jako...");
        jMenuItemSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSaveAsActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemSaveAs);
        jMenuFile.add(jSeparator1);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemExit.setText("Zakoncz");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBar1.add(jMenuFile);

        jMenuAbout.setText("O programie");

        jMenuItemAuthors.setText("O programie");
        jMenuItemAuthors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAuthorsActionPerformed(evt);
            }
        });
        jMenuAbout.add(jMenuItemAuthors);

        jMenuBar1.add(jMenuAbout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPanel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenActionPerformed
        saveBase();
        fileChooser.setFileFilter(null);

        DataReaderDAO dataReaderDAO = new DataReaderDAOImpl();
        list = dataReaderDAO.getBiblioItems(fileChooser, jMenuItemOpen, file, baseFileName);

        for (int i = 0; i < list.size(); ++i) {
            listModel.addElement(list.get(i).getFileName());
        }

        current = list.size() - 1;
        jListItems.setSelectedIndex(current);
        jListItems.ensureIndexIsVisible(current);
        jButtonRemove.setEnabled(true);
        jButtonAdd.setEnabled(false);
        isModified = false;

        list.get(current).show();
        String info = list.get(current).toString();
        this.setTitle(info);

    }//GEN-LAST:event_jMenuItemOpenActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        Runtime.getRuntime().exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNewActionPerformed
        //     isModified = true;
        if (list.size() > 0 || isModified == true) {  // zapytanie o zapisanie bazy, gdyż uległa modyfikacji
            final int optionPane = JOptionPane.showConfirmDialog(this, "Czy zapisać zmiany?", "Zmodyfikowana zawartość bazy", JOptionPane.YES_NO_OPTION);
            if (optionPane == JOptionPane.YES_OPTION) {
                // zapisz bazę
            }
            isModified = false;
        }
        // przygotowanie do stworzenia nowej bazy
        list.clear();
        // wyczyszczenie listy JList
        jListItems.setSelectionInterval(0, jListItems.getModel().getSize() - 1);
        jListItems.clearSelection();
        // wyczyszczenie obrau/pola tekstowego
        jLabel.setIcon(null);
        enableInterface(true);
        jButtonRemove.setEnabled(false);
        jButtonAdd.setEnabled(false);
        ((JStatisticLabel)jLabelStatistics).reset();
    }//GEN-LAST:event_jMenuItemNewActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if (jLabelPath.getText().compareTo("") == 0) {
            JOptionPane.showMessageDialog(null, " Najpierw wczytaj plik", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String name = jLabelPath.getText();
        if (alreadyInList(name)) { // jeżeli jest na liście, to nie dodajemy drugi raz
            jButtonAdd.setEnabled(false);
            return;
        }
        current = jListItems.getSelectedIndex(); //get selected index
        if (current == -1) { // brak zaznaczonego elementu, dodajemy nowy element na początek listy
            current = 0;
        } else {           // dodajemy za zaznaczonym elementem na liście
            current++;
        }
        DefaultListModel listModel = (DefaultListModel) jListItems.getModel();
        listModel.insertElementAt(name, current);
        String type = (String) (jComboBoxState.getSelectedItem());
        BiblioItem biblio = StaticFactory.productProcess(name, type);
        list.add(current, biblio);
        jLabelPath.setText("");
        jListItems.setSelectedIndex(current);
        jListItems.ensureIndexIsVisible(current);
        jButtonRemove.setEnabled(true);
        jButtonAdd.setEnabled(false);
        isModified = true;
    }//GEN-LAST:event_jButtonAddActionPerformed
    
    /**
     * Sprawdza, czy podana nazwa jest już na liście.
     * @param name - nazwa pliku
     * @return true lub false w zależności od spełnienia warunku
     */
    protected boolean alreadyInList(String name) {
        if (jListItems.getModel().getSize() <= 0) {
            return false;
        }
        int index = jListItems.getNextMatch(name, 0, Bias.Forward);
        return index != -1;
    }

        /**
     * Uaktywnia lub dezaktywuje komponenty, 
     * @param state - określa, czy uaktywnić, czy zdezaktywować komponenty
     */
    private void enableInterface(boolean state) {
        jMenuItemSave.setEnabled(state);
        jMenuItemSaveAs.setEnabled(state);
        jButtonPath.setEnabled(state);
        jComboBoxState.setEnabled(state);
        jButtonAdd.setEnabled(state);
        jButtonRemove.setEnabled(state);
        jLabelPath.setText("");
    }

    /**
     * Zapisuje plik bazy.
     */
    private void saveBase() {
        if (isModified == true) {  // zapytanie o zapisanie bazy, gdyż uległa modyfikacji
            final int optionPane = JOptionPane.showConfirmDialog(this, "Czy zapisać zmiany?", "Zmodyfikowana zawartość bazy", JOptionPane.YES_NO_OPTION);
            if (optionPane == JOptionPane.YES_OPTION) {
                jMenuItemSaveActionPerformed(null);
            }
            isModified = false;
        }
        // przygotowanie do stworzenia nowej bazy
        list.clear();
        // wyczyszczenie listy JList
        jListItems.setSelectionInterval(0, jListItems.getModel().getSize() - 1);
        jListItems.clearSelection();
        // wyczyszczenie obrau/pola tekstowego
        jLabel.setIcon(null);
        enableInterface(true);
        jButtonRemove.setEnabled(false);
        jButtonAdd.setEnabled(false);
    }

    // Pozostałe metody służą do obsługi zdarzeń
    
    private void jButtonPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPathActionPerformed

        switch (jComboBoxState.getSelectedIndex()) {
            case 0: // textowy
                fileChooser.setFileFilter(textFilter);
                break;
            default: // obrazy
                fileChooser.setFileFilter(imageFilter);
                break;
        }

        if (fileChooser.showOpenDialog(jMenuItemOpen) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            jTextArea.setText("");
            switch (jComboBoxState.getSelectedIndex()) {
                case 0: // textowy
                    BufferedReader stream = null;
                    try {
                        stream = new BufferedReader(new FileReader(file.toString()));
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
                        ae.printStackTrace();
                    }
                    jScrollPanel.getViewport().setView(jTextArea);
                    break;
                default: // obrazy
                    jLabel.setIcon(new ImageIcon(file.toString()));
                    jLabel.setHorizontalAlignment(JLabel.CENTER);
                    jScrollPanel.getViewport().setView(jLabel);
                    break;

            }
            jLabelPath.setText(file.toString());
            jButtonAdd.setEnabled(true);
        }
    }//GEN-LAST:event_jButtonPathActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

        current = jListItems.getSelectedIndex();
        if (current < 0) {
            return;
        }
        isModified = true;
        listModel.remove(current);
        list.remove(current);
        this.setTitle("");
        int size = listModel.getSize();
        //jListItems.remove(current);
        if (size == 0) { // lista jest pusta
            jButtonRemove.setEnabled(false);
            jScrollPanel.setViewportView(null);
        } else { // Zaznaczenie 
            if (current == listModel.getSize()) { // usunięta z ostateniej pozycji
                current--;
            }
            jListItems.setSelectedIndex(current);
            jListItems.ensureIndexIsVisible(current);
            list.get(current).show();
            String info = list.get(current).toString();
            this.setTitle(info);
        }
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    public void valueChanged(ListSelectionEvent e) {
    }

    private void jListItemsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListItemsValueChanged
    }//GEN-LAST:event_jListItemsValueChanged

    private void jListItemsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListItemsMousePressed
        current = jListItems.getSelectedIndex();
        if (current >= 0) {
            jLabelPath.setText("");
            list.get(current).show();
            String info = list.get(current).toString();
            this.setTitle(info);
            jButtonAdd.setEnabled(false);
            counter.notifyAllObject(list.get(current).getType());
        }
    }//GEN-LAST:event_jListItemsMousePressed

    private void jListItemsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListItemsKeyPressed
        if (current != jListItems.getSelectedIndex()) {
            current = jListItems.getSelectedIndex();
            if (current >= 0) {
                jLabelPath.setText("");
                list.get(current).show();
                String info = list.get(current).toString();
                this.setTitle(info);
                jButtonAdd.setEnabled(false);
                counter.notifyAllObject(list.get(current).getType());
            }
        }
    }//GEN-LAST:event_jListItemsKeyPressed

    private void jListItemsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListItemsKeyReleased
        jListItemsKeyPressed(evt);
    }//GEN-LAST:event_jListItemsKeyReleased

    private void jMenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveActionPerformed
        if (baseFileName == null) {
            jMenuItemSaveAsActionPerformed(evt);
            return;
        }
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(baseFileName));
            outputStream.writeObject(list);
            outputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JFrameBiblio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JFrameBiblio.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(JFrameBiblio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItemSaveActionPerformed

    private void jMenuItemSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSaveAsActionPerformed
        if (fileChooser.showSaveDialog(jMenuItemSave) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            baseFileName = file.toString();
            jMenuItemSaveActionPerformed(evt);
        }

    }//GEN-LAST:event_jMenuItemSaveAsActionPerformed

    private void jMenuItemAuthorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAuthorsActionPerformed
        JOptionPane.showMessageDialog(null, "Projekt zaliczeniowy: \"Biblioteka multimedialna\"\n" +
                "Autorzy: Krzysztof Gola, Jakub Jamróz, Rafał Gierek, Marcin Jamróz, Bartłoiej Cipirski\n" +
                "\n" +
                "Dzięki naszemu programowi może łatwo i bez przeszkud stworzyć baze składającą się z plików txt oraz jpg,\n" +
                " zapisać ją na komputrze a potem gdy tego potzebuje możemy ją otworzyć w każdym momencie. \n" +
                "Dodatkowo pliki txt możemy wygodnie edytować bezpośrednio w programie.\n" +
                "\n" +
                "Sterowanie:\n" +
                "-Tworzenie bazy: \n" +
                "1. Klikamy \"plik\" po czym \"nowy\" \n" +
                "2. wybieramy rodzaj pliku z listy: tekstowy lub jpg i\n" +
                "3. Klikamy plik zródłowy i wybieramy iteresujący nas plik o własciwym rozszerzeniu które wybraliśmy wcześniej i klikamy \"open\"\n" +
                "4. Wybrany plik pojawia nam się w oknie po prawej, jeśli jest on txt możemy go edytować.\n" +
                "5. Nastepnie jeżeli chcemy takowy plik dodać do naszej bazy klikamy przcisk \"Dodaj\"\n" +
                "6. Możemy również usuwać elementy naszej bazy dzięki przyciskowi \"Usuń\"\n" +
                "7. Jeżeli nasza baza jest już gotowa klikamy \"Plik\" po czym zapisz jako i zapisuje nasz plik z roszerzenia  \".base\"\n" +
                "\n" +
                "\n" +
                "-Otwieranie bazy:\n" +
                "1. Klikamy \"plik\" po czym \"otwórz\" \n" +
                "2. wybieramy naszą baze i klikamy \"open\"\n" +
                "3. Po otowrzeniu bazy może znowu dodawać nowe elementy, usuwać stare, przeglądać zdjęcia i pliki txt oraz je edytować", "Informacja", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItemAuthorsActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameBiblio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameBiblio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameBiblio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameBiblio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameBiblio().setVisible(true);
            }
        });
    }


    private final JFileChooser fileChooser = new JFileChooser();          // okno dialogowe wyboru pliku do otwarcia
    private File file;                                                    // plik do wczytania
    private boolean isModified = false;                                   // info, czy baza została zmodyfikowana
    private ArrayList<BiblioItem> list = new ArrayList<>();         // lista z pozycjami bibliograficznymi
    private int current;                                                  // określa aktualną pozycję na liście pozycji bibliograficznych
    private final DefaultListModel listModel = new DefaultListModel();    // Pozycje, które są wyświetlanie na liście w aplikacji
    private final JLabel jLabel = new JLabel();
    private final JTextArea jTextArea = new JTextArea();
    private final FileFilter imageFilter;
    private final FileNameExtensionFilter textFilter;
    private String baseFileName;                                     // nazwa pliku bazy danych (aktualnie otwartego
    Counter counter = new Counter();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonPath;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JComboBox<String> jComboBoxState;
    private javax.swing.JLabel jLabelPath;
    private javax.swing.JLabel jLabelStatistics;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JList<String> jListItems;
    private javax.swing.JMenu jMenuAbout;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItemAuthors;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemNew;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JPanel jPanelType;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
