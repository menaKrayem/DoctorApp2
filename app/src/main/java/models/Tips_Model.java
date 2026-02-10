package models;

public class Tips_Model {
    private String title;
    private String image;
    public static final String TABLE_NAME = "favorites";
    public static final String ID = "id";
    public static final String TITLE = "tip_title";
    public static final String IMAGE = "tip_image";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT NOT NULL, "
            + IMAGE + " TEXT" + ")";

    public Tips_Model() {
    }

    public Tips_Model(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
