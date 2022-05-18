package casino;

import javafx.scene.image.ImageView;

import java.util.Random;

public class ActionChain {
    Player player;
    ImageView img;
    ImageView img1;
    Handler chain;
    Random generate;
    final int NUMHANDLER=4;
    final int NUMMAX = 7;

    public ActionChain(Player player1, ImageView view, ImageView view1){
        generate =new Random();
        img=view;
        img1=view1;
        player=player1;
        buildChain();
    }


    private void buildChain(){
        chain = new ShansHendler( new PositiveHandler  (new NegativeHandler(null,player),player),player);
    }

    public boolean process() {
        int type=generate.nextInt(NUMHANDLER);//розыгрыш
        return process(type);
    }

    public boolean process(Integer a) {
        return chain.process(1+a%NUMHANDLER,img,img1);//обрезка по числу имеющихся обработчиков
    }
}
