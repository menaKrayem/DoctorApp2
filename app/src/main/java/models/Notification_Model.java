package models;

public class Notification_Model {
    private int id;
    private String title;
    private String description;
    private String time;
    private String date;
    private int image;
    public static final String TABLE_NAME = "notifications_table";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESC = "description";
    public static final String TIME = "time";
    public static final String DATE = "date";
    public static final String IMAGE = "image";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT, "
            + DESC + " TEXT, "
            + TIME + " TEXT, "
            + DATE + " TEXT, "
            + IMAGE + " INTEGER" + ")";

    public Notification_Model(int id, int image, String date, String time, String description, String title) {
        this.id = id;
        this.image = image;
        this.date = date;
        this.time = time;
        this.description = description;
        this.title = title;
    }

    public Notification_Model() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public int getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
