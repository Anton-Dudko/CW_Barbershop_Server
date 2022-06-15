package entity;

public class MasterIEntity implements IEntity {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "ФИО", "Страна", "Устроен"};
    public final static String[] COLUMNS = {"name", "country", "date_of_cooperation"};
    public final static boolean[] IS_STR = {true, true, true};

    private int id;
    private String name;
    private String country;
    private String date_of_cooperation;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public MasterIEntity(int id, String name, String country, String date_of_cooperation){
        this.id = id;
        this.name = name;
        this.country = country;
        this.date_of_cooperation = date_of_cooperation;
    }

    public MasterIEntity(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDate_of_cooperation() {
        return date_of_cooperation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate_of_cooperation(String date_of_cooperation) {
        this.date_of_cooperation = date_of_cooperation;
    }
}
