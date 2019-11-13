package imgzip;

import javafx.scene.control.MenuItem;

/**
 * @author 乌鑫龙
 */
class ImgMenuItem extends MenuItem {

    ImgMenuItem(String str){
        super(str);
    }

    @Override
    public String toString(){
        return this.getText();
    }
}
