package entity;

public class PurchaseIEntity implements IEntity {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Дата", "Метоположение", "Почта", "ИД Стрижки", "Кол-во часов"};
    public final static String[] COLUMNS = {"date", "address", "mail", "haircut_id", "count"};
    public final static boolean[] IS_STR = {true, true, true, false, false};

    private int id;
    private String date;
    private String address;
    private String mail;
    private int haircut_id;
    private int count;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public PurchaseIEntity(int id, String date, String address, String mail, int haircut_id, int count){
        this.id = id;
        this.date = date;
        this.address = address;
        this.mail = mail;
        this.haircut_id = haircut_id;
        this.count = count;

    }

    public PurchaseIEntity(){}

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public String getMail() {
        return mail;
    }

    public int getHaircut_id() {
        return haircut_id;
    }

    public int getCount() {
        return count;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setHaircut_id(int haircut_id) {
        this.haircut_id = haircut_id;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
