package casino;

import javafx.scene.image.ImageView;

public abstract class Handler {
    public static int SUCCESS = 1;
    public static int CHANCE = 3;
    private Handler processor;
    public Player player1;
    public Handler(Handler processor, Player player){
        this.processor = processor;
        this.player1=player;
    }

    public boolean process(Integer request,ImageView img,ImageView img1){
        if(processor != null)
            return processor.process(request,img,img1);
        else
            return true;
    }
}
