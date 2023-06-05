package ucas.edu.android.productsstoreapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class All_salesActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    ImageButton btn_go_home ;
    Button btn_arrange ;

    ProjectDatabase db ;

    public static final int ORDER_BY_MOST_PRICE = 1 ;
    public static final int ORDER_BY_LOWEST_PRICE = 2 ;
    public static final int ORDER_BY_NEWEST = 3 ;
    public static final int ORDER_BY_OLDEST = 4 ;

    ArrayList<Sale_db_obj> data ;

    int signed_user_id ;

    LinearLayout linearLayout ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sales);

        recyclerView= findViewById(R.id.all_sales_recyclerview) ;
        btn_go_home = findViewById(R.id.btn_go_home) ;
        btn_arrange = findViewById(R.id.all_sales_btn_arrange) ;
        linearLayout = findViewById(R.id.all_sales_linearlayout) ;

        linearLayout.setVisibility(View.GONE);

        db = new ProjectDatabase(getBaseContext());
        SharedPreferences UsersPreferences = getSharedPreferences(Create_accountActivity.USERS_PREFERANCES , MODE_PRIVATE) ;

        signed_user_id = UsersPreferences.getInt(Sign_inActivity.SIGNED_USER_ID , 0 ) ;

        data = new ArrayList<>() ;

        data = db.getAllSalesForUser(signed_user_id , ORDER_BY_NEWEST) ;

        AllSales_adapter adapter = new AllSales_adapter(data) ;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this , 1) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btn_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_home = new Intent(getBaseContext() , MainActivity.class) ;
                startActivity(go_home);
            }
        });

        btn_arrange.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                PopupMenu arrange_menu = new PopupMenu(getBaseContext() , btn_arrange) ;
                arrange_menu.inflate(R.menu.arragement_popub_menu);
                arrange_menu.setForceShowIcon(true);
                arrange_menu.show();

                arrange_menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        AdapterView.AdapterContextMenuInfo info
                                = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo() ;
                        switch (item.getItemId()){
                            case R.id.all_sales_menu_most_price :
                                btn_arrange.setText("الأكثر تكلفة");
                                data = db.getAllSalesForUser(signed_user_id , ORDER_BY_MOST_PRICE) ;
                                break;

                            case R.id.all_sales_menu_lowest_price :
                                btn_arrange.setText("الأقل تكلفة");
                                data = db.getAllSalesForUser(signed_user_id , ORDER_BY_LOWEST_PRICE) ;
                                break;

                            case R.id.all_sales_menu_new :
                                btn_arrange.setText("الأحدث");
                                data = db.getAllSalesForUser(signed_user_id , ORDER_BY_NEWEST) ;
                                break;

                            case R.id.all_sales_menu_oldest :
                                btn_arrange.setText("الأقدم");
                                data = db.getAllSalesForUser(signed_user_id , ORDER_BY_OLDEST) ;
                                break;
                        }
                        adapter.setData(data);
                        return true;
                    }
                });

            }
        });



    }
}
//هذا الكود يعرض قائمة بجميع البيعات في التطبيق. يتم استخدامه في صفحة All_salesActivity. هنا هو شرح الكود:
//
//يتم تعريف متغيرات وعناصر الواجهة الرسومية المستخدمة في الصفحة مثل RecyclerView والأزرار والخط الأفقي.
//يتم إنشاء كائن من قاعدة البيانات ProjectDatabase.
//يتم استخدام SharedPreferences للحصول على معلومات المستخدم المسجل الحالي.
//يتم إنشاء ArrayList لتخزين بيانات البيعات.
//يتم استرداد جميع بيعات المستخدم المسجل الحالي من قاعدة البيانات وتخزينها في ArrayList.
//يتم إنشاء وتهيئة محول AllSales_adapter وتعيينه في RecyclerView.
//يتم تعيين مستمع للنقر على زر "العودة إلى الصفحة الرئيسية" للانتقال إلى صفحة MainActivity.
//يتم تعيين مستمع للنقر على زر "ترتيب" لعرض قائمة منبثقة (PopupMenu) تحتوي على خيارات الترتيب المختلفة.
//عند اختيار أحد الخيارات في قائمة الترتيب، يتم استدعاء الدالة المناسبة في قاعدة البيانات لاسترداد البيعات بترتيب محدد وتحديث ArrayList والمحول.
//يتم تعيين المحول الجديد للـ RecyclerView لعرض البيعات المعدلة.
