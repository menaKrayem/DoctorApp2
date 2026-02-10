package models;

public class Appoint_Model {
    private int image;
    private int id;
    private String name;
    private String specialize;
    private String date;
    private String time;
    private  String state;
    public static final String TABLE_NAME = "appointments_table";
    public static final String IMAGE = "image";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SPEC = "specialize";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String STATE = "state";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT, "
            + SPEC + " TEXT, "
            + TIME + " TEXT, "
            + DATE + " TEXT, "
            + STATE + " TEXT, "
            + IMAGE + " INTEGER" + ")";

    public Appoint_Model() {
    }

    public Appoint_Model(int image, int id, String name, String specialize, String date, String time, String state) {
        this.image = image;
        this.id = id;
        this.name = name;
        this.specialize = specialize;
        this.date = date;
        this.time = time;
        this.state = state;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialize() {
        return specialize;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setState(String state) {
        this.state = state;
    }
}
