import java.awt.*;
import javax.swing.*;

public class UI {

    private Menu() {
        JFrame main = new JFrame("Giga Chess");
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setSize(600, 800);

        main.setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}

