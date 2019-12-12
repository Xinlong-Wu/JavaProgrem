package imgzip.alertwindow;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author 乌鑫龙
 */
public class AlertButton extends Button {
    public AlertButton(String Text){
        ImageView tar3 = new ImageView(new Image("res/icon/target.png"));
        tar3.setFitHeight(20);
        tar3.setFitWidth(20);
        this.setGraphic(tar3);
        this.setGraphicTextGap(10);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getStyleClass().addAll("button");
        this.setVisible(true);
        this.setText(Text);
    }
}
