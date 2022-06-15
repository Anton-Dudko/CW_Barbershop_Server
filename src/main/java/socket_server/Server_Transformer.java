package socket_server;

import database.MySQL_Ordering;
import entity.*;
import simple_data.Service_Serialize;
import simple_data.Valid_Session;

import java.util.ArrayList;

public class Server_Transformer {
    private static MySQL_Ordering queryer = new MySQL_Ordering();

    public static String doAuthorization(String data){
        String answer;
        UserIEntity user = (UserIEntity) Service_Serialize.JSONToObject(data, UserIEntity.class);
        ArrayList<UserIEntity> users = queryer.getAllUsers("", "`login` = ?", user.getLogin(), "false");
        return (answer = userSeaech(users, user)) != "" ? answer : "";

    }

    private static String userSeaech(ArrayList<UserIEntity> users, UserIEntity user){
        for (int i = 0; i < users.size(); i++) {
            UserIEntity iUser = users.get(i);
            if(user.getLogin().equals(iUser.getLogin()) && user.getPassword().equals(iUser.getPassword())){
                return Service_Serialize.ObjectToJSON(iUser);
            }
        }
        return "";
    }


    public static String getAll(String orderByAndWhere, Class<? extends IEntity> currentClass){
        String params[] = new String[]{"", ""};
        if(!(orderByAndWhere.equals("")))
            params = orderByAndWhere.split("@@@");

        if(params.length <= 2)
            params = new String[]{params[0], params[1], "", ""};

        return getAnswerFromBD(params,currentClass);
    }

    private static String getAnswerFromBD(String params[], Class<? extends IEntity> currentClass){
        switch (currentClass.getName()) {
            default:
            case "entity.CategoryIEntity":     return Service_Serialize.ObjectsToJSON(queryer.getAllCategorys(params[0], params[1], params[2], params[3]));
            case "entity.HaircutIEntity":      return Service_Serialize.ObjectsToJSON(queryer.getAllHaircuts(params[0], params[1], params[2], params[3]));
            case "entity.PurchaseIEntity":     return Service_Serialize.ObjectsToJSON(queryer.getAllPurchases(params[0], params[1] , params[2], params[3]));
            case "entity.MasterIEntity":     return Service_Serialize.ObjectsToJSON(queryer.getAllMasters(params[0], params[1] , params[2], params[3]));
            case "entity.UserIEntity":         return Service_Serialize.ObjectsToJSON(queryer.getAllUsers(params[0], params[1], params[2], params[3]));
            case "entity.SalonIEntity":    return Service_Serialize.ObjectsToJSON(queryer.getAllSalons(params[0], params[1], params[2], params[3]));
            case "entity.TransferIEntity":      return Service_Serialize.ObjectsToJSON(queryer.getMasterHaircuts(params[1], params[2], params[3]));
            case "entity.ModeratorIEntity":         return Service_Serialize.ObjectsToJSON(queryer.getUserCategorys(params[1], params[2], params[3]));
        }
    }


    public static String delete(String data){
        String params[] = data.split("@@@");
        if(!Valid_Session.isInt(params[1])) return "";
        boolean isOk = queryer.delete(params[0], Integer.valueOf(params[1]));
        return isOk ? "@ok@" : queryer.lastError;
    }

    public static String insert(String data, String table, Class<? extends IEntity> modelType){
        boolean isOk = queryer.insert(Service_Serialize.JSONToObject(data, modelType), table);
        return isOk ? "@ok@" : queryer.lastError;
    }

    public static String update(String data, String table, Class<? extends IEntity> modelType){
        String params[] = data.split("@@@");
        if(!Valid_Session.isInt(params[1])) return "";
        boolean isOk = queryer.update(Service_Serialize.JSONToObject(params[0], modelType), table, Integer.valueOf(params[1]));// ? "ok" : "";
        return isOk ? "@ok@" : queryer.lastError;
    }

}
