package entity;

public class TransferIEntity implements IEntity {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "ИД Мастер", "Мастер", "ИД Стрижки", "Стрижка"};
    public final static String[] COLUMNS = {"master_id", "master_name", "haircut_id", "haircut_name" };
    public final static boolean[] IS_STR = {false, true, false, true};
    public final static String[] COLUMNS_ABSOLUTE = {"master_id", "haircut_id" };
    public final static boolean[] IS_STR_ABSOLUTE = {false, false};

    private int id;
    private int master_id;
    private String master_name;
    private int haircut_id;
    private String haircut_name;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public TransferIEntity(int id, int master_id, String master_name, int haircut_id, String haircut_name){
        this.id = id;
        this.master_id = master_id;
        this.haircut_name = haircut_name;
        this.haircut_id = haircut_id;
        this.master_name = master_name;
    }

    public TransferIEntity(){}

    public int getId(){
        return id;
    }


    public int getMaster_id() {
        return master_id;
    }

    public String getMaster_name() {
        return master_name;
    }

    public int getHaircut_id() {
        return haircut_id;
    }

    public String getHaircut_name() {
        return haircut_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMaster_id(int master_id) {
        this.master_id = master_id;
    }

    public void setMaster_name(String master_name) {
        this.master_name = master_name;
    }

    public void setHaircut_id(int haircut_id) {
        this.haircut_id = haircut_id;
    }

    public void setHaircut_name(String haircut_name) {
        this.haircut_name = haircut_name;
    }
}
