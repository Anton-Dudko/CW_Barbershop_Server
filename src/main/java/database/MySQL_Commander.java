package database;

import entity.*;

public class MySQL_Commander {


    public String[] newData(IEntity IEntity){
        switch (IEntity.getClass().getName()){
            case "entity.CategoryIEntity":               return newDataCategory(IEntity);
            case "entity.HaircutIEntity":                return newDataHaircut(IEntity);
            case "entity.PurchaseIEntity":               return newDataPurchase(IEntity);
            case "entity.MasterIEntity":               return newDataMaster(IEntity);
            case "entity.UserIEntity":                   return newDataUser(IEntity);
            default: case "entity.SalonIEntity":     return newDataSalon(IEntity);
            case "entity.TransferIEntity":        return newDataMasterHaircut(IEntity);
            case "entity.ModeratorIEntity":           return newDataUserCategory(IEntity);
        }
    }

    private String[] newDataCategory(IEntity IEntity){
        CategoryIEntity category = (CategoryIEntity) IEntity;
        String categor;
        categor = category.getParent_category_id() != 0 ? String.valueOf(category.getParent_category_id()) : "NULL";
        String answer[] = new String[]{
                category.getName(),
                categor
        };
        return answer;
    }

    private String[] newDataHaircut(IEntity IEntity){
        HaircutIEntity haircut = (HaircutIEntity) IEntity;
        String answer[] = new String[]{
                haircut.getName(),
                haircut.getDescription(),
                String.valueOf(haircut.getPrice()),
                String.valueOf(haircut.getCount()),
                haircut.getStyle_name(),
                String.valueOf(haircut.getCategory_id()),
                String.valueOf(haircut.getSalon_id())
        };
        return answer;
    }

    private String[] newDataPurchase(IEntity IEntity){
        PurchaseIEntity purchase = (PurchaseIEntity) IEntity;
        String answer[] = new String[]{
                purchase.getDate(),
                purchase.getAddress(),
                purchase.getMail(),
                String.valueOf(purchase.getHaircut_id()),
                String.valueOf(purchase.getCount())
        };
        return answer;
    }

    private String[] newDataMaster(IEntity IEntity){
        MasterIEntity master = (MasterIEntity) IEntity;
        String answer[] = new String[]{
                master.getName(),
                master.getCountry(),
                master.getDate_of_cooperation()
        };
        return answer;
    }

    private String[] newDataUser(IEntity IEntity){
        UserIEntity user = (UserIEntity) IEntity;
        String answer[] = new String[]{
                user.getLogin(),
                user.getPassword(),
                String.valueOf( user.getRole())
        };
        return answer;
    }

    private String[] newDataSalon(IEntity IEntity){
        SalonIEntity salon = (SalonIEntity) IEntity;
        String answer[] = new String[]{
                salon.getName(),
                salon.getAddress()
        };
        return answer;
    }

    private String[] newDataMasterHaircut(IEntity IEntity){
        TransferIEntity masterHaircut = (TransferIEntity) IEntity;
        String answer[] = new String[]{
                String.valueOf(masterHaircut.getMaster_id()),
                String.valueOf(masterHaircut.getHaircut_id())
        };
        return answer;
    }

    private String[] newDataUserCategory(IEntity IEntity){
        ModeratorIEntity userCategory = (ModeratorIEntity) IEntity;
        String answer[] = new String[]{
                String.valueOf(userCategory.getuser_id()),
                String.valueOf(userCategory.getcategory_id())
        };
        return answer;
    }

}
