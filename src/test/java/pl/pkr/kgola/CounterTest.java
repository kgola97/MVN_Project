package pl.pkr.kgola;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

public class CounterTest {

    //@Test
    public void shouldAddObserve() {
        Observer observer = new JStatisticLabel();

        Counter counter = new Counter();

        int expectedListSize = 1;
        assertNotNull(counter.getObservers());
        assertEquals(0, counter.getObservers().size());
        counter.addObserver(observer);
        assertEquals(expectedListSize, counter.getObservers().size());
    }

    //@Test
    public void shouldDeleteObserve() {
        Observer observer  = new JStatisticLabel();

        List<Observer> observers = new ArrayList<Observer>();
        observers.add(observer);

        Counter counter = new Counter();
        counter.addObservers(observers);

        assertNotNull(counter.getObservers());
        assertTrue(counter.getObservers().size() == 1);
        counter.deleteObserver(observer);
        assertTrue(counter.getObservers().size() == 0);
    }

    //@Test
    public void shouldNotifyAllObjects() {
        Counter counter = new Counter();

        JStatisticLabel jStatisticLabel = new JStatisticLabel();

        List<Observer> observers = new ArrayList<Observer>(3);
        observers.add(jStatisticLabel);

        counter.addObservers(observers);

        String type = "Tekstowy";
        counter.notifyAllObject(type);

        assertEquals(0, jStatisticLabel.getScanFilesCount());
        assertEquals(1, jStatisticLabel.getTextFilesCount());
    }
}