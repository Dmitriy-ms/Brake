//package ru.volgatest.brake;
//
//import com.example.AutomaticCycle.Cycle.Cycle;
//import com.example.AutomaticCycle.SubCycleTable.SubCycle;
//import com.example.LibrarySteeringRack.LocalDateJsonSerDes;
//import com.example.UI.ru.volgatest.brake.SimpleLogger.MsgType;
//import com.example.UI.ru.volgatest.brake.SimpleLogger.ru.volgatest.brake.SimpleLogger;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonSyntaxException;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProgramLibrary {
//    static String LIBRARY_PATH = "AutomaticCycleLibrary.json";
//    public static ProgramLibrary LOADED_LIBRARY = new ProgramLibrary();
//
//    public List<Cycle> cycleList = new ArrayList<>();
//    public List<SubCycle> subCycleList = new ArrayList<>();
//
//
////    public Map<UUID, Integer> subCycleList = new LinkedHashMap<>();
//
//    public static void saveDataToFile(ProgramLibrary automaticCycleLibrary) {
//        Gson gson = new Gson();
////        try (FileWriter writer = new FileWriter(LIBRARY_PATH)) {
//        try (Writer writer = new OutputStreamWriter(new FileOutputStream(LIBRARY_PATH), StandardCharsets.UTF_8)) {
//            gson.toJson(automaticCycleLibrary, writer);
//        } catch (IOException e) {
//            ru.volgatest.brake.SimpleLogger.logText("Can not save program library /n" + e.getMessage(), MsgType.FAULT, true);
//        }
//    }
//
//    public static void loadDataFromFile() throws FileNotFoundException, JsonSyntaxException {
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateJsonSerDes())
//                .create();
//        Reader reader = new InputStreamReader(new FileInputStream(LIBRARY_PATH), StandardCharsets.UTF_8);
//        LOADED_LIBRARY = gson.fromJson(reader, new TypeToken<ProgramLibrary>() {}.getType());
//    }
//}
