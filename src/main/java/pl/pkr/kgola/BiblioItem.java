/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package applicationbiblio;
package pl.pkr.kgola;

import java.io.Serializable;
import java.util.Objects;
import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 * Strukturalny wzorzec projekotwy MOST
 * Oddziela abstrakcję obiektu od jego implementacji.
 * W naszym przypadku klasa abstrakcyjna BiblioItem dostarcza informacji o 
 * pozycji bibliograficznej, natomiast implementacja dostarcza interfejs do 
 * "wyświetlenia" tej pozycji na ekranie monitora (tekst lub obraz).
 * @author Krzysztof
 * 
 */
public abstract class BiblioItem implements Serializable {
    protected String fileName;      // ścieżka dostępu do pliku źróódłowego
    protected String type;          // rodzaj pozycji w bazie danych (plik tekstowy, plik dźwiękowy, film)
    
    
    protected BiblioItem(String fileName, String type)
    {
        this.fileName = fileName;
        this.type = type;
    }
    
    public String getFileName() {return fileName;}
    
    public String getType() {return type;}
    
    @Override
    public String toString() {
        return "<" + type + ">" + "\t" + fileName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;  // ta sama referencja
        if (o == null)
            return false; // porównanie obiektu z null
        if (getClass() != o.getClass())
            return false;   // obiekty są różnych typów
        BiblioItem sd = (BiblioItem)(o);
        if (fileName == null)
        {
            if (sd.fileName != null)
                return false;
        }
        else
            if (sd.fileName == null)
                return false;
            else
              if (!fileName.equals(sd.fileName))
                 return false;
        
        if (type == null)
        {
            if (sd.type != null)
                return false;
        }
        else
            if (sd.type == null)
                return false;
            else
              if (!type.equals(sd.type))
                 return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.fileName);
        hash = 67 * hash + Objects.hashCode(this.type);
        return hash;
    }
    
    abstract public void show();   
    
}
