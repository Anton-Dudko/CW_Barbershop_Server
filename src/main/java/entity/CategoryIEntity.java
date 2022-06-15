package entity;

public class CategoryIEntity implements IEntity {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Название", "База"};
    public final static String[] COLUMNS = {"name", "parent_category_id"};
    public final static boolean[] IS_STR = {true, false};
    public final static String[] REQUIRED_COLUMN = {"name"};

    private int id;
    private String name;
    private int parent_category_id;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public CategoryIEntity(int id, String name, int parent_category_id){
        this.id = id;
        this.name = name;
        this.parent_category_id = parent_category_id;
    }

    public CategoryIEntity(int id, String name){
        this.id = id;
        this.name = name;
    }
    public CategoryIEntity(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParent_category_id() {
        return parent_category_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent_category_id(int parent_category_id) {
        this.parent_category_id = parent_category_id;
    }

}
