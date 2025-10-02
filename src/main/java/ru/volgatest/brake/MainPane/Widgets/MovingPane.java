package ru.volgatest.brake.MainPane.Widgets;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.volgatest.brake.Widgets.Coordinate;

abstract class MovingPane extends VBox {
    Label title = new Label();
    Coordinate dragDelta = new Coordinate(0, 0);
    Label dragLb = new Label();
    public boolean isDeveloperMode = false;

    public MovingPane() {

        this.setAlignment(Pos.CENTER);
        dragLb.setLayoutX(0);
        dragLb.setLayoutY(0);

        this.getChildren().addAll(dragLb);
        this.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            if (mouseEvent.getTarget() instanceof HBox) {
                return; // не перетаскиваем, если тянем поле ввода
            }
            if (mouseEvent.getTarget() instanceof MovingPane) {
                dragDelta.x = this.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = this.getLayoutY() - mouseEvent.getSceneY();
                mouseEvent.consume();
            }

        });

        this.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseEvent -> {
            if (mouseEvent.getTarget() instanceof MovingPane) {

                // Получаем текущие размеры AnchorPane
                double paneWidth = this.getWidth();
                double paneHeight = this.getHeight();

                // Рассчитываем абсолютные координаты
                double absX = mouseEvent.getSceneX() + dragDelta.x;
                double absY = mouseEvent.getSceneY() + dragDelta.y;

                // Преобразуем в относительные (0.0-1.0)
                double relX = absX / paneWidth;
                double relY = absY / paneHeight;

                // Устанавливаем новую позицию
                this.setLayoutX(absX);
                this.setLayoutY(absY);

                // Выводим относительные координаты (форматированные)
//                dragLb.setText(String.format("Относительные координаты: X: %.3f, Y: %.3f", relX, relY));
                dragLb.setText(String.format("Координаты: X: %.3f, Y: %.3f", absX, absY));

                mouseEvent.consume();

            }
        });

    }

//    public void setLayouts(double x, double y) {
//        this.setLayoutX(x);
//        this.setLayoutY(y);
//    }

    public void setTitle(String title) {
        this.title.setText(title);
        this.getChildren().add(1, this.title);
    }
}
