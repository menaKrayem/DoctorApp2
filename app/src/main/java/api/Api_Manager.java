package api;
import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import DataBase.Notification_Favorite_Appoint_DB;

public class Api_Manager {
         Context context;
         Notification_Favorite_Appoint_DB db;
        public Api_Manager(Context context) {
            this.context = context;
            db = new Notification_Favorite_Appoint_DB(context);
        }
        public void getHealthTips() {
            String url = "https://dummyjson.com/posts";

            JsonObjectRequest getPostsRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray list = response.getJSONArray("posts");
                                for (int i = 0; i < list.length(); i++) {
                                    JSONObject item = list.getJSONObject(i);

                                    String title = item.getString("title");
                                    String image = "https://loremflickr.com/320/240/health?random=" + i;
                                    if (!db.checkIfTitle(title, image)) {
                                        db.insertFavorite(title, image);
                                        Log.d("MY_APP", "تمت إضافة: " + title);
                                    } else {
                                        Log.d("MY_APP", "العنصر موجود مسبقاً: " + title);
                                    }
                                }
                            }catch(JSONException e){
                                    Log.e("MY_APP_ERROR", "مشكلة في البيانات: " +e.getMessage());
                                }
                            }
                        },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("VOLLEY_ERROR", "Failed to get data");
                        }
                    }
                    );
                Volley.newRequestQueue(context).add(getPostsRequest);
            }
    }



