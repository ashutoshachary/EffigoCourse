import java.util.Arrays;

public class UpdateArray {
    public static void main(String[] args) {
        String[] menu = { "Expresso", "Iced Cofee", "Macciato" };
        String[] menu1 = new String[5];
        menu[2] = "Cappuccino";
        String menuString = Arrays.toString(menu);
        System.out.println("Menu: " + menuString);
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ": " + menu[i]);
            menu1[i] = menu[i];
        }
        menu1[3] = "Cofee";
        menu1[4] = "Macciato";
        for (int i = 0; i < menu1.length; i++) {
            System.out.println(i + ": " + menu1[i]);
        }

        System.out.println("Menu after update: " + Arrays.toString(menu1));
    }

}
