package ru.volgatest.brake.MainPane.Widgets.Controller;

import ru.volgatest.brake.InsideWindows.Breakaway.BreakawayTestWindow;
import ru.volgatest.brake.InsideWindows.ContinuousBraking.ContinuousBrakingWindow;
import ru.volgatest.brake.InsideWindows.IntermittentBraking.IntermittentBrakingWindow;
import ru.volgatest.brake.InsideWindows.Limitations.Limits;
import ru.volgatest.brake.InsideWindows.Metrology;
import ru.volgatest.brake.MainPane.Widgets.FooterPanel;

public class FooterConroller {
    private final FooterPanel footerPanel = new FooterPanel();

    //Обработка нажатия кнопок, которые находятся на панели FooterPanel
    public FooterConroller() {
        footerPanel.metrologyBtn.setOnAction(event -> {
            Metrology metrology = new Metrology();
        });

        footerPanel.intermittentBrakingBtn.setOnAction(event -> {
            IntermittentBrakingWindow intermittentBrakingWindow = new IntermittentBrakingWindow();
        });

        footerPanel.continuousBrakingBtn.setOnAction(event -> {
            ContinuousBrakingWindow continuousBrakingWindow = new ContinuousBrakingWindow();
        });

        footerPanel.breakawayBtn.setOnAction(event -> {
            BreakawayTestWindow breakawayTestWindow = new BreakawayTestWindow();
        });

        footerPanel.limitsBtn.setOnAction(event -> {
            Limits limits = new Limits();
        });
    }

    public FooterPanel getFooterPanel() {
        return footerPanel;
    }
}
