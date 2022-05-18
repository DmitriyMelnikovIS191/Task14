package casino;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShansHendler extends Handler {
    public ShansHendler(Handler processor, Player player) {
        super(processor, player);
    }
    public boolean process(Integer request, ImageView img, ImageView img1) {
        if(request!=CHANCE) return super.process(request,img,img1);// не свой запрос передается дальше по цепочке
        else {//свой, обрабатывается здесь
            img.setImage(new Image("Money.png"));
            img1.setImage(new Image("NoMoney.png"));
            player1.addNumber(1);
            return true;
        }
    }

}
