package entity;

public class HaircutIEntity implements IEntity {
    public final static String[] COLUMNS_BY_RUS = {"ИД", "Название", "Описание", "Цена", "Кол-во часов",
            "Стиль", "Категория", "Солон красоты"};
    public final static String[] COLUMNS = {"name", "description", "price", "count", "style_name",
            "category_id", "salon_id"};
    public final static boolean[] IS_STR = {true, true, false, false, true, false, false};

    private int id;
    private String name;
    private String description;
    private double price;
    private int count;
    private String style_name;
    private int category_id;
    private int salon_id;

    public String[] getConstColumnsRus(){return COLUMNS_BY_RUS;}
    public String[] getConstColumns(){return COLUMNS;}
    public boolean[] getConstIsSTR(){return IS_STR;}

    public HaircutIEntity(int id, String name, String description, double pricce, int count, String style_name,
                          int category_id, int salon_id){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = pricce;
        this.count = count;
        this.style_name = style_name;
        this.category_id = category_id;
        this.salon_id = salon_id;
    }

    public HaircutIEntity(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public String getStyle_name() {
        return style_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public int getSalon_id() {
        return salon_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setSalon_id(int salon_id) {
        this.salon_id = salon_id;
    }
}
