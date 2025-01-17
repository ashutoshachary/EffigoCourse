package src.test;

import static org.junit.Assert.assertEquals;;

import org.junit.Test;

import src.main.Main;

public class checkoutTest {

    @SuppressWarnings("deprecation")
    @Test
    public void subtotalIsValid() {
        assertEquals(19.2, Main.getSubtotal());

    }

    @SuppressWarnings("deprecation")
    @Test
    public void taxIsValid() {
        assertEquals(3.9, Main.getTax(30));
    }
}
