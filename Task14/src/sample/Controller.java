package sample;

import casino.ActionChain;
import casino.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField name;
    public Button addMoney;
    public Button restart;

    public Button goplay;
    public TextField money;
    public int sizeimage;

    @FXML
    ImageView view1;
    @FXML
    ImageView view2;
    @FXML
    ImageView view3;
    ActionChain action=null;
    Player player1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetimage();
        view1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            sizeimage=sizeimage+1;
            view1.setDisable(true);

        });
        view2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            sizeimage=sizeimage+2;
            view2.setDisable(true);
        });
        view3.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            sizeimage=sizeimage+3;
            view3.setDisable(true);
        });
    }

    public void goPlayClick(ActionEvent actionEvent)
    {
        if (player1!=null) {
            player1.pay(1);
            onStart(sizeimage);
            if (action == null) return;//если цепочка обработки отсутствует
            if (action.process()) init();//продолжить играть и проверить наличия монетки у игрока
            else action = null;//завершить игру, сделав обработку недоступной
            money.setText(player1.getNumber().toString());
        }
    }
    public void createPlayer()
    {
        player1=new Player(name.getText(),100);
        money.setText(player1.getNumber().toString());
        addMoney.setDisable(false);
        restart.setDisable(false);
    }
    public void onPay(ActionEvent actionEvent) {
        player1.addNumber(1);
        money.setText(player1.getNumber().toString());
    }

    public void restartPlay(ActionEvent actionEvent) {

        imageDisable(false);
        resetimage();
    }

    public void resetimage(){
        sizeimage=0;
        view1.setImage(new Image("Мешок.png"));
        view2.setImage(new Image("Мешок.png"));
        view3.setImage(new Image("Мешок.png"));
    }
    public void imageDisable(boolean temp){
        view1.setDisable(temp);
        view2.setDisable(temp);
        view3.setDisable(temp);
    }

    public void onStart(int number) {
        if(!init()) return;//проверка ликвидности
        switch (number){
            case 3:
                action=new ActionChain(player1,view1,view2);//запуск механизма розыгрыша
                break;
            case 4:
                action=new ActionChain(player1,view1,view3);//запуск механизма розыгрыша
                break;
            case 5:
                action=new ActionChain(player1,view2,view3);//запуск механизма розыгрыша
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Выбери разные мешки!");
                alert.show();
                sizeimage=0;
                break;
        }
    }

    public boolean init(){
        if(player1.getNumber()==0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Баланс всё!");
            alert.show();
            action=null;
            resetimage();
            return false;
        }
        else return true;
    }

}
