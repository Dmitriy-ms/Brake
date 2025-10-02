package ru.volgatest.brake.ObjectsLibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import ru.volgatest.brake.InsideWindows.BrakingMechanisms.BrakeModel;
import ru.volgatest.brake.InsideWindows.ContinuousBraking.ContinuousBrakingModel;
import ru.volgatest.brake.InsideWindows.IntermittentBraking.Cycle.CycleModel;
import ru.volgatest.brake.InsideWindows.IntermittentBraking.SubCycle.SubCycleModel;
import ru.volgatest.brake.SimpleLogger.MsgType;
import ru.volgatest.brake.SimpleLogger.SimpleLogger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private static final String BRAKE_LIBRARY_PATH = "Brake.json";
    private static final String CYCLE_LIBRARY_PATH = "Cycle.json";
    private static final String SUBCYCLE_LIBRARY_PATH = "SubCycle.json";
    private static final String CONTINUOUS_LIBRARY_PATH = "Continuous.json";

    public static final Library LOADED_LIBRARY = new Library();

    public List<BrakeModel> brakeModelList = new ArrayList<>(); // Тормозные механизмы
    public List<CycleModel> cycleModelList = new ArrayList<>(); //Циклы
    public List<SubCycleModel> subCycleModelList = new ArrayList<>(); //Подциклы
    public List<ContinuousBrakingModel> continuousCycle = new ArrayList<>(); //

//    public boolean isChangedBrakeModelList = false;

    public BooleanProperty isLoaded = new SimpleBooleanProperty(false);

//    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Gson gson = new GsonBuilder().create();

    // Загрузить все данные из всех файлов
    public static void loadAll() {
        LOADED_LIBRARY.brakeModelList = loadListFromFile(BRAKE_LIBRARY_PATH, new TypeToken<List<BrakeModel>>(){}.getType());
        LOADED_LIBRARY.cycleModelList = loadListFromFile(CYCLE_LIBRARY_PATH, new TypeToken<List<CycleModel>>(){}.getType());
        LOADED_LIBRARY.subCycleModelList = loadListFromFile(SUBCYCLE_LIBRARY_PATH, new TypeToken<List<SubCycleModel>>(){}.getType());
        LOADED_LIBRARY.continuousCycle = loadListFromFile(CONTINUOUS_LIBRARY_PATH, new TypeToken<List<ContinuousBrakingModel>>(){}.getType());
    }

    // Сохраняет по типу
    public static void saveData(Object modelList) {
        if (modelList instanceof List<?>) {
            List<?> list = (List<?>) modelList;
            if (list.isEmpty()) return;

            Object first = list.get(0);

            if (first instanceof BrakeModel) {
                Library.LOADED_LIBRARY.brakeModelList = (List<BrakeModel>) list;
                saveListToFile(list, BRAKE_LIBRARY_PATH);
//                Library.LOADED_LIBRARY.isLoaded.setValue(true);
//                BreakawayTestWindow.getBrakeList();
            } else if (first instanceof CycleModel) {
                Library.LOADED_LIBRARY.cycleModelList = (List<CycleModel>) list;
                saveListToFile(list, CYCLE_LIBRARY_PATH);
            } else if (first instanceof SubCycleModel) {
                Library.LOADED_LIBRARY.subCycleModelList = (List<SubCycleModel>) list;
                saveListToFile(list, SUBCYCLE_LIBRARY_PATH);
            } else if (first instanceof ContinuousBrakingModel) {
                Library.LOADED_LIBRARY.continuousCycle = (List<ContinuousBrakingModel>) list;
                saveListToFile(list, CONTINUOUS_LIBRARY_PATH);
            } else {
                System.err.println("Unknown model type: " + first.getClass());
            }
        }
    }


    private static <T> List<T> loadListFromFile(String path, java.lang.reflect.Type type) {
        File file = new File(path);
        if (!file.exists()) return new ArrayList<>();
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, type);
        } catch (IOException | JsonSyntaxException e) {
            SimpleLogger.logText("Error loading " + path + ": " + e.getMessage(), MsgType.FAULT, true);
            return new ArrayList<>();
        }
    }

    private static void saveListToFile(Object list, String path) {
        Gson gson = new Gson();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            SimpleLogger.logText("Error saving to " + path + ": " + e.getMessage(), MsgType.FAULT, true);
        }
    }
}

