package cardGames.match;

import javax.swing.*;

public class Main {
      public static void main(String[] args){
          JFrame match = new JFrame("Match two");
          match.setDefaultCloseOperation(3);
          match.setSize(800,600);
          match.setLocationRelativeTo(null);
          match.add(new MatchPanel());
          match.setVisible(true);
      }
}
