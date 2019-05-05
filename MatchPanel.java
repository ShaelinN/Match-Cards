package cardGames.match;

import cardGames.Card;
import cardGames.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

public class MatchPanel extends JPanel implements MouseListener {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<GraphicCard> graphicCards = new ArrayList<>();
    private int pairCount = 6;
    private Deck deck;
    private int pairsFound;
    private Card firstSelected;
    private Card secondSelected;

    JButton newGame = new JButton("NEW GAME");

    public MatchPanel(){
        addMouseListener(this);
        newGame.addActionListener(e -> setUpNewGame());
        setLayout(new BorderLayout());
        setBackground(Color.GREEN);
        add(newGame,BorderLayout.SOUTH);
        setUpNewGame();

    }

    public void setUpNewGame(){
        pairsFound =0;
        deck = new Deck();
        deck.shuffle();
        cards = new ArrayList<>();
        graphicCards = new ArrayList<>();
        for (int i = 0; i < pairCount; i++) {
            Card c = deck.deal();

            cards.add(new Card(c.getSuit(),c.getValue()));
            cards.add(new Card(c.getSuit(),c.getValue()));

        }
        Collections.shuffle(cards);
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).flip();
            graphicCards.add(new GraphicCard(cards.get(i)));
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int left = 0;
        int top = 0;
        //for each card
        for (int i = 0; i < graphicCards.size(); i++) {
            if (graphicCards.get(i) != null){
                graphicCards.get(i).draw(g, left, top);
            }
                left += 100;

            if (left == 400) {
                left = 0;
                top += 200;
            }
        }
    }

    private void clickCard(int i){
        cards.get(i).flip();
        repaint();
        if (firstSelected==null){
            firstSelected = cards.get(i);
        }
        else{
            secondSelected = cards.get(i);

            if((firstSelected.getSuit()==secondSelected.getSuit()) && (firstSelected.getValue()==secondSelected.getValue())){
                pairsFound++;
                if (pairsFound==pairCount){

                }
            }
            else{
                firstSelected.flip();

                secondSelected.flip();
                repaint();
            }
            firstSelected =null;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //was any card clicked
        int i;
        if ((e.getX()%100<=70 && e.getY()%200<=120) && e.getX()<=400){
            //which card
            int row = e.getY()/200;
            int col = e.getX()/100;

            i = 4*row + col;
            try {
                clickCard(i);
            }catch (IndexOutOfBoundsException iobe){

            }
        }

        //comparison


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
