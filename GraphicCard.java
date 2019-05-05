package cardGames.match;

import cardGames.Card;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GraphicCard {

    private Card card;
    private String faceUpCardPath = "cardImages/";
    Image image = null;

    public static final String faceDownCardPath = "cardImages/b2fv.gif";
    public static final int WIDTH = 70;
    public static final  int HEIGHT =120;


    public GraphicCard(Card card){
        this.card = card;
        switch (this.card.getSuit()){
            case 0:
                faceUpCardPath+="h";
                break;
            case 1:
                faceUpCardPath+="c";
                break;
            case 2:
                faceUpCardPath+="s";
                break;
            case 3:
                faceUpCardPath+="d";
                break;
        }

        faceUpCardPath += this.card.getValue()+ ".gif";
    }
    private void setImage(){
        if (card.faceUp){
            try {
                image = ImageIO.read(new File(faceUpCardPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{//card !faceUp
            try {
                image = ImageIO.read(new File(faceDownCardPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void draw(Graphics g, int left, int top){
        setImage();
        if(image != null){
            g.drawImage(image,left,top,WIDTH,HEIGHT,null);
        }
    }
}
