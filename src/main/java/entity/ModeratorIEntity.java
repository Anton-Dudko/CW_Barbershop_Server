package entity;

public class ModeratorIEntity implements IEntity {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "ИД Пользователя", "Пользователь", "ИД Категории", "Категория"};
    public final static String[] COLUMNS = {"user_id", "user_name", "category_id", "category_name" };
    public final static boolean[] IS_STR = {false, true, false, true};
    public final static String[] COLUMNS_ABSOLUTE = {"user_id", "category_id" };
    public final static boolean[] IS_STR_ABSOLUTE = {false, false};

    private int id;
    private int user_id;
    private String user_name;
    private int category_id;
    private String category_name;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public ModeratorIEntity(int id, int user_id, String user_name, int category_id, String category_name){
        this.id = id;
        this.user_id = user_id;
        this.category_name = category_name;
        this.category_id = category_id;
        this.user_name = user_name;
    }

    public ModeratorIEntity(){}

    public int getId(){
        return id;
    }

    public int getuser_id() {
        return user_id;
    }

    public String getuser_name() {
        return user_name;
    }

    public int getcategory_id() {
        return category_id;
    }

    public String getcategory_name() {
        return category_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setuser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setcategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setcategory_name(String category_name) {
        this.category_name = category_name;
    }
}
