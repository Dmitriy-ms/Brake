package ru.volgatest.brake.PLC;

//import com.example.LibrarySteeringRack.RackSpecimen;
//import com.example.MainPanel.MainWorkPane;
import ru.volgatest.brake.MainPane.MainPanel;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PLCCommandController {
    // Из UI в ПЛК
    public static final byte SERVICE_MODE_CMD = 0x01; // (0x01) Ручной режим, задание
    public static final byte STOP_BENCH_CMD = 0x02; // (0x02) Остановка стенда
    public static final byte HYDRO_STATION_MODE_CMD = 0x11; // (0x11) Статическое испытание
    public static final byte START_CALIBRATION_CMD = 0x21; // (0x21) Автоматическое испытание
    public static final byte STOP_CALIBRATION_CMD = 0x22;// (0x22) Пауза автоматического испытания
    public static final byte LOAD_SETTINGS_PLC = (byte) 0xB0; //(0xB0) Настройки

//    public static final byte STOP_HYDRO_CMD = 0x12; // (0x12) Остановка гидростанции
//    public static final byte LOAD_CALIBRATION_CMD = 0x23; //(0x23) Загрузить данные автокалибровки
//    public static final byte START_INSTALL_CMD = 0x31;// (0x31) Начать установки
//    public static final byte CONFIRM_INSTALL_CMD = 0x32;// (0x32) Подтвердить установку
//    public static final byte START_AUTO_MODE_CMD = 0x41; //(0x41) Запуск автоматического режима
//    public static final byte PAUSE_AUTO_MODE_CMD = 0x42;// (0x42) Пауза автоматического режима
//    public static final byte MANUAL_HYDRO_CONTROL_CMD = 0x50;// (0x50) Ручное управление гидростанцией
//    public static final byte STOP_MANUAL_HYDRO_CMD = 0x51;// (0x51) Остановка ручного управление гидростанцией



    // Из ПЛК в UI
    public static final byte OPERATOR_MSG_CMD = (byte) 0xE0; // (0xA0) Сообщение для оператора
//    public static final byte AUTOCALIBRATION_UPDATE_CMD = (byte) 0xA2;// (0xA2) Обновление данных автокалибровки
//    public static final byte SETTINGS_LOADED_CMD = (byte) 0xA3;// (0xA3) Настройки АКП загружены

    private static final ByteOrder BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;


    // region Из UI в ПЛК


    // 0x01: Ручной режим, задание
    public static void manualMode(ByteBuffer buffer) {
        ByteBuffer buf = ByteBuffer.allocate(buffer.capacity()+5)
                .order(BYTE_ORDER)
                .put(SERVICE_MODE_CMD)
                .put(buffer);
        sendBuffer(buf);
    }
    // 0x02: Остановка стенда
    public static void stopManualMode() {
        ByteBuffer buf = ByteBuffer.allocate(25)
                .order(BYTE_ORDER)
                .put(STOP_BENCH_CMD);
        sendBuffer(buf);
    }
    // (0x11) Статическое испытание
    public static void staticMode(ByteBuffer buffer) {
        ByteBuffer buf = ByteBuffer.allocate(buffer.capacity()+5)
                .order(BYTE_ORDER)
                .put(SERVICE_MODE_CMD)
                .put(buffer);
        sendBuffer(buf);
    }



    private static void sendBuffer(ByteBuffer buffer) {
        MainPanel.CONNECTOR.sendControlMsg(buffer);
//        buffer.flip();
//        connector.sendEvent(buffer);
    }
}
