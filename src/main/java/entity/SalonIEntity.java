package entity;

public class SalonIEntity implements IEntity {
    public final static String[] COLUMNS = {"name", "address"};
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Название", "Метоположение"};
    public final static boolean[] IS_STR = {true, true};

    private int id;
    private String name;
    private String address;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public SalonIEntity(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public SalonIEntity(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
