package database;

import entity.*;

import java.util.ArrayList;

public class MySQL_Ordering {
    private MySQL_System connector =  MySQL_System.getInstance();
    public String lastError = "";

    public ArrayList<CategoryIEntity> getAllCategorys(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<IEntity> IEntities;
            ArrayList<CategoryIEntity>  categorys = new ArrayList<>();
            IEntities = connector.select("categorys", where, orderBy, param, isInt);

            for (int i = 0; i < IEntities.size(); i++)
                categorys.add((CategoryIEntity) IEntities.get(i));
            return categorys;
        } catch (Exception e){
            lastError = "Ошибка запроса: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }

    public ArrayList<HaircutIEntity> getAllHaircuts(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<IEntity> IEntities;
            ArrayList<HaircutIEntity>  haircuts = new ArrayList<>();
            IEntities = connector.select("haircuts", where, orderBy, param, isInt);
            for (int i = 0; i < IEntities.size(); i++)
                haircuts.add((HaircutIEntity) IEntities.get(i));
            return haircuts;
        } catch (Exception e){
            lastError = "Ошибка запроса: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }



    public ArrayList<MasterIEntity> getAllMasters(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<IEntity> IEntities;
            ArrayList<MasterIEntity>  masters = new ArrayList<>();
            IEntities = connector.select("masters", where, orderBy, param, isInt);
            for (int i = 0; i < IEntities.size(); i++)
                masters.add((MasterIEntity) IEntities.get(i));
            return masters;
        } catch (Exception e){
            lastError = "Ошибка запроса: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }

    public ArrayList<PurchaseIEntity> getAllPurchases(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<IEntity> IEntities;
            ArrayList<PurchaseIEntity>  purchases = new ArrayList<>();
            IEntities = connector.select("purchases", where, orderBy, param, isInt);
            for (int i = 0; i < IEntities.size(); i++)
                purchases.add((PurchaseIEntity) IEntities.get(i));
            return purchases;
        } catch (Exception e){
            lastError = "Ошибка запроса: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }


    public ArrayList<UserIEntity> getAllUsers(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<IEntity> IEntities;
            ArrayList<UserIEntity>  users = new ArrayList<>();
            IEntities = connector.select("users", where, orderBy, param, isInt);

            for (int i = 0; i < IEntities.size(); i++)
                users.add((UserIEntity) IEntities.get(i));
            return users;
        } catch (Exception e){
            lastError = "Ошибка запроса: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }


    public ArrayList<SalonIEntity> getAllSalons(String orderBy, String where, String param, String isInt){
        try {
            ArrayList<IEntity> IEntities;
            ArrayList<SalonIEntity>  salons = new ArrayList<>();
            IEntities = connector.select("salons", where, orderBy, param, isInt);
            for (int i = 0; i < IEntities.size(); i++)
                salons.add((SalonIEntity) IEntities.get(i));
            return salons;
        } catch (Exception e){
            lastError = "Ошибка запроса: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }

    public ArrayList<TransferIEntity> getMasterHaircuts(String where, String param, String isInt){
        try {
            ArrayList<IEntity> IEntities;
            ArrayList<TransferIEntity>  masterHaircuts = new ArrayList<>();
            String query = "select transfers.id, transfers.master_id, masters.name, transfers.haircut_id, haircuts.name " +
                    "from (transfers join masters on transfers.master_id = masters.id) join haircuts " +
                    "on transfers.haircut_id = haircuts.id ";
            if(!("".equals(where.trim()))) query += " where transfers." + where;
            query += " group by transfers.id";
            IEntities = connector.selectByQuery(query, "transfers", param, isInt);
            for (int i = 0; i < IEntities.size(); i++)
                masterHaircuts.add((TransferIEntity) IEntities.get(i));
            return masterHaircuts;
        } catch (Exception e){
            lastError = "Ошибка запроса: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }

    public ArrayList<ModeratorIEntity> getUserCategorys(String where, String param, String isInt){
        try {
            ArrayList<IEntity> IEntities;
            ArrayList<ModeratorIEntity>  userCategorys = new ArrayList<>();
            String query = "select user_categories.id, user_categories.user_id, users.login, user_categories.category_id, categorys.name " +
                    "from (user_categories join users on user_categories.user_id = users.id) join categorys " +
                    "on user_categories.category_id = categorys.id ";
            if(!("".equals(where.trim()))) query += " where user_categories." + where;
            query += " group by user_categories.id";

            IEntities = connector.selectByQuery(query, "user_categories", param, isInt);
            for (int i = 0; i < IEntities.size(); i++)
                userCategorys.add((ModeratorIEntity) IEntities.get(i));
            return userCategorys;
        } catch (Exception e){
            lastError = "Ошибка запроса: \"" + e.getClass() + "\"" + connector.lastError;
            return null;
        }
    }


    public boolean delete(String table, int id) {
        lastError = "";
        try {
            MySQL_System connector =  MySQL_System.getInstance();
            if (connector.delete(table,id)) {
                return true;
            } else {
                throw new Exception(connector.lastError);
            }
        } catch (Exception e){
            lastError = "Ошибка запроса: \"" + e.getClass() + "\"" + connector.lastError;
            return false;
        }
    }

    public boolean insert(IEntity IEntity, String table) {
        lastError = "";
        try {
            MySQL_Commander transformator = new MySQL_Commander();
            String values = prepareQueryComponents(IEntity);
            String columns = prepareQueryColumns(IEntity);
            String[] valueByQuery = transformator.newData(IEntity);

            if (connector.insert(table, columns, values, valueByQuery, isStrCurrent(IEntity))) {
                return true;
            } else {
                lastError = connector.lastError;
                return false;
            }
        } catch (Exception  e){
            if(e.getMessage().length() > 150) lastError = "Ошибка запроса: \"" + e.getClass() + "\"";
            else lastError = "Ошибка запроса: \"" + e.getMessage() + "\"";
            return false;
        }
    }

    private String prepareQueryComponents(IEntity IEntity){
        if(IEntity instanceof CategoryIEntity)  return "?,?";
        if(IEntity instanceof HaircutIEntity)   return "?,?,?,?,?,?,?";
        if(IEntity instanceof PurchaseIEntity)  return "?,?,?,?,?";
        if(IEntity instanceof MasterIEntity)  return "?,?,?";
        if(IEntity instanceof UserIEntity)      return "?,?,?";
        if(IEntity instanceof SalonIEntity) return "?,?";
        if(IEntity instanceof TransferIEntity)   return "?,?";
        if(IEntity instanceof ModeratorIEntity)      return "?,?";
        return null;
    }

    private String prepareQueryColumns(IEntity IEntity){
        if(IEntity instanceof CategoryIEntity)  return prepareColumns(CategoryIEntity.COLUMNS);
        if(IEntity instanceof HaircutIEntity)   return prepareColumns(HaircutIEntity.COLUMNS);
        if(IEntity instanceof PurchaseIEntity)  return prepareColumns(PurchaseIEntity.COLUMNS);
        if(IEntity instanceof MasterIEntity)  return prepareColumns(MasterIEntity.COLUMNS);
        if(IEntity instanceof UserIEntity)      return prepareColumns(UserIEntity.COLUMNS);
        if(IEntity instanceof SalonIEntity) return prepareColumns(SalonIEntity.COLUMNS);
        if(IEntity instanceof TransferIEntity)   return prepareColumns(TransferIEntity.COLUMNS_ABSOLUTE);
        if(IEntity instanceof ModeratorIEntity)      return prepareColumns(ModeratorIEntity.COLUMNS_ABSOLUTE);
        return null;
    }

    private boolean[] isStrCurrent(IEntity IEntity){
        if(IEntity instanceof CategoryIEntity)  return CategoryIEntity.IS_STR;
        if(IEntity instanceof HaircutIEntity)   return HaircutIEntity.IS_STR;
        if(IEntity instanceof PurchaseIEntity)  return PurchaseIEntity.IS_STR;
        if(IEntity instanceof MasterIEntity)  return MasterIEntity.IS_STR;
        if(IEntity instanceof UserIEntity)      return UserIEntity.IS_STR;
        if(IEntity instanceof SalonIEntity) return SalonIEntity.IS_STR;
        if(IEntity instanceof TransferIEntity)   return TransferIEntity.IS_STR_ABSOLUTE;
        if(IEntity instanceof ModeratorIEntity)      return ModeratorIEntity.IS_STR_ABSOLUTE;
        return null;
    }

    private String prepareColumns(String columns[]){
        String str = "";
        for (int i = 0; i < columns.length; i++) {
            str += columns[i];
            if(i != columns.length - 1) str +=",";
        }
        return str;
    }


    public boolean update(IEntity IEntity, String table, int id) {
        lastError = "";
        try {
            MySQL_Commander transformator = new MySQL_Commander();
            String[] valueByQuery = transformator.newData(IEntity);

            String values = prepareQueryValue(IEntity);
            if (connector.update(table, id, values, valueByQuery, isStrCurrent(IEntity))) {
                return true;
            } else {
                lastError = connector.lastError;
                return false;
            }
        } catch (Exception e){
            if(e.getMessage().length() > 150) lastError = "Ошибка запроса: \"" + e.getClass() + "\"";
            else lastError = "Ошибка запроса: \"" + e.getMessage() + "\"";
            return false;
        }
    }

    private String prepareQueryValue(IEntity IEntity){

        if(IEntity instanceof CategoryIEntity){
            return prepareCategoryValue();
        }
        if(IEntity instanceof HaircutIEntity){
            return prepareHaircutValue();
        }
        if(IEntity instanceof PurchaseIEntity){
            return preparePurchaseValue();
        }
        if(IEntity instanceof MasterIEntity){
            return prepareMasterValue();
        }
        if(IEntity instanceof UserIEntity){
            return prepareUserValue();
        }
        if(IEntity instanceof SalonIEntity){
            return prepareSalonValue();
        }
        if(IEntity instanceof TransferIEntity){
            return prepareMasterHaircutValue();
        }
        if(IEntity instanceof ModeratorIEntity){
            return prepareUserCategoryValue();
        }
        return "";
    }

    private String prepareCategoryValue(){
        String str;
        str = "name = ? , parent_category_id = ?";
        return str;
    }

    private String prepareHaircutValue(){
        String str;
        str = "name = ?, description = ?, price = ?, count = ? ,style_name = ? " +
                ",category_id = ?, salon_id = ?";
        return str;
    }

    private String preparePurchaseValue(){
        String str;
        str = "date = ?, address = ?, mail = ?,haircut_id = ?, count = ?";
        return str;
    }

    private String prepareMasterValue( ){
        String str;
        str = "name = ?, country = ?, date_of_cooperation = ?";
        return str;
    }

    private String prepareUserValue(){
        String str;
        str = "login = ?, password = ?, role = ?";
        return str;
    }

    private String prepareSalonValue(){
        String str;
        str = "name = ?, address = ?";
        return str;
    }

    private String prepareMasterHaircutValue(){
        String str;
        str = "master_id = ?, haircut_id = ?";
        return str;
    }

    private String prepareUserCategoryValue(){
        String str;
        str = "user_id = ?, category_id = ?";
        return str;
    }
}
