//package ru.volgatest.brake.ObjectsLibrary;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonSyntaxException;
//import com.google.gson.reflect.TypeToken;
//import ru.volgatest.brake.InsideWindows.BrakingMechanisms.BrakeModel;
//import ru.volgatest.brake.InsideWindows.ContinuousBraking.ContinuousBrakingModel;
//import ru.volgatest.brake.InsideWindows.Cycle.CycleModel;
//import ru.volgatest.brake.InsideWindows.SubCycle.SubCycleModel;
//import ru.volgatest.brake.SimpleLogger.MsgType;
//import ru.volgatest.brake.SimpleLogger.SimpleLogger;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//public class LibraryOld {
//    static String BRAKE_LIBRARY_PATH = "Brake.json";
//    static String CYCLE_LIBRARY_PATH = "Cycle.json";
//    static String SUBCYCLE_LIBRARY_PATH = "SubCycle.json";
//    static String CONTINUOUS_LIBRARY_PATH = "Continuous.json";
//
//    public static LibraryOld loadedLibraryOld = new LibraryOld();
//
//    public List<BrakeModel> brakeModelList = new ArrayList<>();
//    public List<CycleModel> cycleModelList = new ArrayList<>();
//    public List<SubCycleModel> subCycleModelList = new ArrayList<>();
//    public List<ContinuousBrakingModel> continuousBrakingModelList = new ArrayList<>();
//
//    public static void saveDataToFile(LibraryOld rackLibraryOld, String LIBRARY_PATH) {
//        LibraryOld.LIBRARY_PATH = LIBRARY_PATH;
////        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        Gson gson = new Gson();
////        try (FileWriter writer = new FileWriter(LIBRARY_PATH)) {
//        try (Writer writer = new OutputStreamWriter(new FileOutputStream(LIBRARY_PATH), StandardCharsets.UTF_8)) {
//            gson.toJson(rackLibraryOld, writer);
//        } catch (IOException e) {
//            SimpleLogger.logText("Can not save rack library:" + e.getMessage(), MsgType.FAULT, true);
//        }
//    }
//
//    public static void loadDataFromFile(String LIBRARY_PATH) throws FileNotFoundException, JsonSyntaxException {
//        LibraryOld.LIBRARY_PATH = LIBRARY_PATH;
//        Gson gson = new GsonBuilder().create();
//        Reader reader = new InputStreamReader(new FileInputStream(LIBRARY_PATH), StandardCharsets.UTF_8);
////        List<RackModel> lst = gson.fromJson(reader, new TypeToken<List<RackModel>>() {}.getType());
//        loadedLibraryOld = gson.fromJson(reader, new TypeToken<LibraryOld>() {
//        }.getType());
//    }
//}
