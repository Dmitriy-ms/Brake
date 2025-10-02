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
        ByteBuffer buf = ByteBuffer.allocate(25)
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



//    // 0x01: Сервисный режим стенда
//    public static void sendServiceModeCommand(
//            float leftForce, float leftPos,
//            float rightForce, float rightPos,
//            float angle, float torque,
//            float speed, byte controlType
//    ) {
//        ByteBuffer buf = ByteBuffer.allocate(30) // 7*float + 1*byte
//                .order(BYTE_ORDER)
//                .put(SERVICE_MODE_CMD)
//                .putFloat(leftForce)
//                .putFloat(leftPos)
//                .putFloat(rightForce)
//                .putFloat(rightPos)
//                .putFloat(angle)
//                .putFloat(torque)
//                .putFloat(speed)
//                .put(controlType);
//        sendBuffer(buf);
//    }
//
//    // 0x02: Остановка стенда
//    public static void sendStopBench() {
//        sendSimpleCommand(STOP_BENCH_CMD);
//    }
//
//    // 0x11: Сервисный режим, задание гидростанции
//    public static void sendHydroStationModeCommand(float tempCoolant, float tempHydraulic, float pressureHydro, boolean highPressureValve, boolean lowPressureValve) {
//        ByteBuffer buf = ByteBuffer.allocate(21) // 5*float
//                .order(BYTE_ORDER)
//                .put(HYDRO_STATION_MODE_CMD)
//                .putFloat(tempCoolant)
//                .putFloat(tempHydraulic)
//                .putFloat(pressureHydro)
//                .put((byte) (highPressureValve ? 1 : 0))
//                .put((byte) (lowPressureValve ? 1 : 0));
//        sendBuffer(buf);
//    }
//
//    // 0x12: Остановка гидростанции
//    public static void sendStopHydro() {
//        sendSimpleCommand(STOP_HYDRO_CMD);
//    }
//
//    // 0x21: Запуск автокалибровки
//    public static void startCalibration() {
//        sendSimpleCommand(START_CALIBRATION_CMD);
//    }
//
//    // 0x22: Остановка автокалибровки
//    public static void stopCalibration() {
//        sendSimpleCommand(STOP_CALIBRATION_CMD);
//    }
//
//    //0x23: Загрузить данные автокалибровки
//    public static void loadCalibration(
//            float rackStrokeLength,
//            float measuredTransmissionRatio,
//            float fullRotations,
//            float peakFreeMovementTorque,
//
//            float leftCylinderProtrusion,
//            float rightCylinderProtrusion,
//
//            float pinionBacklash,
//            float leftCylinderBacklash,
//            float rightCylinderBacklash,
//            float middlePoint
//    ) {
//        sendSimpleCommand(LOAD_CALIBRATION_CMD);
//        ByteBuffer buffer = ByteBuffer.allocate(42)
//                .order(BYTE_ORDER)
//                .put(LOAD_CALIBRATION_CMD)
//                .putFloat(rackStrokeLength)
//                .putFloat(measuredTransmissionRatio)
//                .putFloat(fullRotations)
//                .putFloat(peakFreeMovementTorque)
//                .putFloat(leftCylinderProtrusion)
//                .putFloat(rightCylinderProtrusion)
//                .putFloat(pinionBacklash)
//                .putFloat(leftCylinderBacklash)
//                .putFloat(rightCylinderBacklash)
//                .putFloat(middlePoint);
//
//        sendBuffer(buffer);
//
//    }
//
//    // 0x31: Начать установки
//    public static void startInstallation() {
//        ByteBuffer buf = ByteBuffer.allocate(9) // 1 + 8
//                .order(BYTE_ORDER)
//                .put(START_INSTALL_CMD);
//        sendBuffer(buf);
//    }
//
//    // 0x32: Подтвердить установку
//    public static void confirmInstallation(
//            UUID uuid,
//            float gearRatio,
//            float maxTorqueDriveShaft,
//            float angleRotationsLimit,
//            float angleSpeedLimit,
//            float maxForceLeftHydraulicCylinder,
//            float maxMovementLeftHydraulicCylinder,
//            float maxForceRightHydraulicCylinder,
//            float maxMovementRightHydraulicCylinder
//    ) {
//
//        ByteBuffer buf = ByteBuffer.allocate(33) // 1 + 8 + 7*4
//                .order(BYTE_ORDER)
//                .put(CONFIRM_INSTALL_CMD)
//                .putLong(uuid.getLeastSignificantBits())
//                .putLong(uuid.getMostSignificantBits())
//                .putFloat(gearRatio)
//                .putFloat(maxTorqueDriveShaft)
//                .putFloat(angleRotationsLimit)
//                .putFloat(angleSpeedLimit)
//                .putFloat(maxForceLeftHydraulicCylinder)
//                .putFloat(maxMovementLeftHydraulicCylinder)
//                .putFloat(maxForceRightHydraulicCylinder)
//                .putFloat(maxMovementRightHydraulicCylinder);
////        MainWorkPane.CONNECTOR.sendPLCmessage("buf");
//        sendBuffer(buf);
//    }
//
//    //0x41: Запуск автоматического режима
//    public static void startAutoMode(ByteBuffer buffer) {
//        ByteBuffer buf = ByteBuffer.allocate(buffer.capacity() + 1);
//        buf.put(START_AUTO_MODE_CMD);
//        buf.put(buffer);
//        sendBuffer(buf);
//
////        sendSimpleCommand(START_AUTO_MODE_CMD);
//    }
//
//    // 0x42: Пауза автоматического режима
//    public static void pauseAutoMode() {
//        sendSimpleCommand(PAUSE_AUTO_MODE_CMD);
//    }
//
//
//    // 0x50: Ручное управление гидростанцией
//    public static void sendManualHydroControl(
//            byte k1, byte k2, byte k3, byte k4,
//            byte ka, byte kb, byte oilPump, byte circPump,
//            float tcv, float pcv
//    ) {
//        ByteBuffer buf = ByteBuffer.allocate(17)
//                .order(BYTE_ORDER)
//                .put(MANUAL_HYDRO_CONTROL_CMD)
//                .put(k1).put(k2).put(k3).put(k4)
//                .put(ka).put(kb)
//                .put(oilPump).put(circPump)
//                .putFloat(tcv)
//                .putFloat(pcv);
//        sendBuffer(buf);
//    }
//
//    // 0x51: Остановка ручного управления гидростанцией
//    public static void sendStopManualHydroControl() {
//        sendSimpleCommand(STOP_MANUAL_HYDRO_CMD);
//    }
//
//    // 0xB0: Загрузить настройки стенда
//    /*public void sendSettingsLoaded(float[] settings) {
//        ByteBuffer buf = ByteBuffer.allocate(settings.length * 4+1)
//                .order(BYTE_ORDER)
//                .put(LOAD_SETTINGS_PLC);
//        for (int i = 0; i < settings.length; i++) {
//            buf.putFloat(settings[i]);
//        }
//        sendBuffer(buf);
//    }*/
//
//    public static void sendSettingsLoaded(List<Float> floats) {
//        ByteBuffer buf = ByteBuffer.allocate(floats.size() * 4 + 2)
//                .order(BYTE_ORDER)
//                .put(LOAD_SETTINGS_PLC)
//                .putInt(floats.size());
//        floats.forEach(buf::putFloat);
//        sendBuffer(buf);
//    }
//
//    // endregion
//
//
//    // region Из ПЛК в UI
//
//    // 0xA0: Сообщение оператору
//    public static void processOperatorMessage(ByteBuffer buffer) {
//        buffer.order(BYTE_ORDER);
//        OperatorMessage msg = new OperatorMessage();
//        msg.msgId = buffer.getInt();
//        msg.msgType = buffer.get();
//        msg.destination = buffer.get();
//
//        while (buffer.remaining() > 0) {
//            short length = buffer.getShort();
//            byte[] textData = new byte[length];
//            buffer.get(textData);
//            msg.messages.add(new String(textData, StandardCharsets.UTF_8));
//        }
//        // Отображение сообщения в UI
//    }
//
//    // 0xA2: Данные автокалибровки
//    public static AutocalibrationData parseAutocalibrationData(ByteBuffer buffer) {
//        buffer.order(BYTE_ORDER);
//        AutocalibrationData data = new AutocalibrationData();
//        RackSpecimen rackSpecimen = data.rackSpecimen;
//        data.step = buffer.getInt();
//        rackSpecimen.rackStrokeLength = buffer.getFloat();
//        rackSpecimen.userTransmissionRatio = buffer.getFloat();
//        rackSpecimen.fullRotations = buffer.getFloat();
//        rackSpecimen.peakFreeMovementTorque = buffer.getFloat();
//        rackSpecimen.leftCylinderProtrusion = buffer.getFloat();
//        rackSpecimen.rightCylinderProtrusion = buffer.getFloat();
//        rackSpecimen.pinionBacklash = buffer.getFloat();
//        rackSpecimen.leftCylinderBacklash = buffer.getFloat();
//        rackSpecimen.rightCylinderBacklash = buffer.getFloat();
//        rackSpecimen.middlePoint = buffer.getFloat();
//        return data;
//    }
//
//    //(0xA3) Настройки АКП загружены
//    public static IdRack settingsLoaded(ByteBuffer buffer) {
//        buffer.order(BYTE_ORDER);
//        IdRack msg = new IdRack();
//        msg.idRack = buffer.getLong();
//        return msg;
//    }
//
//    // endregion
//
//    private static void sendSimpleCommand(byte commandId) {
//        ByteBuffer buf = ByteBuffer.allocate(1).put(commandId);
//        sendBuffer(buf);
//    }
//
    private static void sendBuffer(ByteBuffer buffer) {
        MainPanel.CONNECTOR.sendControlMsg(buffer);
//        buffer.flip();
//        connector.sendEvent(buffer);
    }
//
//
//    public static class OperatorMessage {
//        public int msgId;
//        public byte msgType;
//        public byte destination;
//        public List<String> messages = new ArrayList<>();
//    }
//
//    public static class AutocalibrationData {
//        public int step;
//        public RackSpecimen rackSpecimen = new RackSpecimen();
//
//    }
//
//    public static class IdRack {
//        public long idRack;
//    }
}
