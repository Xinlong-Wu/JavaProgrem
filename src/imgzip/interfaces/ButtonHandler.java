package imgzip.interfaces;


import javafx.scene.control.Button;

/**
 * @author 乌鑫龙
 */
@FunctionalInterface
public interface ButtonHandler<T>{
    void start(T t);
}
