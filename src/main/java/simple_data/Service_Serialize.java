package simple_data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.IEntity;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Service_Serialize {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    public static String ObjectToJSON(IEntity IEntity) {
        return gson.toJson(IEntity);
    }

    public static IEntity JSONToObject(String string, Class<? extends IEntity> modelType) {
        return gson.fromJson(string, modelType);
    }

    public static String ObjectsToJSON(ArrayList<? extends IEntity> models) {
        return gson.toJson(models);
    }


    public static ArrayList<IEntity> JSONToObjects(String string, Type type) {
        return gson.fromJson(string, type);
    }
}
