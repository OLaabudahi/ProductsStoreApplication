package ucas.edu.android.productsstoreapplication;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Last_saleActivity extends AppCompatActivity {

    TextView all_sales_Title ;
    RecyclerView recyclerView ;
    ImageButton btn_go_home ;

    ProjectDatabase db ;

    Button btn_arrange ;

    TextView tv_userTotalSales ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sales);

        all_sales_Title = findViewById(R.id.all_sales_Title) ;
        recyclerView= findViewById(R.id.all_sales_recyclerview) ;
        btn_go_home = findViewById(R.id.btn_go_home) ;
        btn_arrange = findViewById(R.id.all_sales_btn_arrange) ;
        tv_userTotalSales = findViewById(R.id.all_sales_tv_user_total_sales) ;

        btn_arrange.setVisibility(View.GONE);

        db = new ProjectDatabase(getBaseContext());
        SharedPreferences UsersPreferences = getSharedPreferences(Create_accountActivity.USERS_PREFERANCES , MODE_PRIVATE) ;


        all_sales_Title.setText("آخر عملية شراء قمت بها \n و مجموع المشتريات");

        ArrayList<Sale_db_obj> data = new ArrayList<>() ;

        int user_id = UsersPreferences.getInt(Sign_inActivity.SIGNED_USER_ID , 0 ) ;

        data = db.getLastSaleForUser(user_id) ;
        AllSales_adapter adapter = new AllSales_adapter(data) ;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this , 1) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        double user_total_sales = db.getSumOfSaleForUser(user_id) ;

        tv_userTotalSales.setText(String.valueOf(((int)(user_total_sales*100.0))/100.0));

        btn_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_home = new Intent(getBaseContext() , MainActivity.class) ;
                startActivity(go_home);
            }
        });

    }
}
//الشيفرة التي قدمتها هي نشاط (Activity) في تطبيق Android يُسمى Last_saleActivity. يُستخدم هذا النشاط لعرض آخر عملية شراء قام بها المستخدم ومجموع المشتريات الكلي.
//
//دعونا نشرح الأجزاء الرئيسية في هذا النشاط:
//
//المتغيرات:
//
//all_sales_Title: تُستخدم للربط بعنصر TextView المستخدم لعنوان العرض.
//recyclerView: تُستخدم للربط بعنصر RecyclerView المستخدم لعرض البيانات.
//btn_go_home: تُستخدم للربط بزر العودة إلى الصفحة الرئيسية.
//btn_arrange: تُستخدم للربط بزر الترتيب (الذي يبدو أنه لم يتم استخدامه في هذا النشاط).
//tv_userTotalSales: تُستخدم للربط بعنصر TextView المستخدم لعرض إجمالي المشتريات الكلي للمستخدم.
//دالة onCreate():
//
//تُستدعى عند إنشاء النشاط وتُستخدم لتهيئة واجهة المستخدم وإعدادات أخرى.
//يتم ربط عناصر واجهة المستخدم مثل TextView وRecyclerView وButton باستخدام findViewById().
//يتم إعداد عنوان العرض وتهيئة RecyclerView لعرض البيانات.
//يتم استخدام قاعدة البيانات (ProjectDatabase) للوصول إلى بيانات العمليات وإجمالي المشتريات للمستخدم.
//يتم ربط البيانات بمحول العرض (AllSales_adapter) وتعيينه في RecyclerView.
//يتم تعيين قيمة إجمالي المشتريات لعنصر TextView.
//معالجة الحدث:
//
//btn_go_home.setOnClickListener(): تُستدعى عند النقر على زر "العودة إلى الصفحة الرئيسية" وتُستخدم للانتقال إلى النشاط الرئيسي (MainActivity).
//هذا النشاط يُعرض آخر عملية شراء قام بها المستخدم ومجموع المشتريات الكلي. يستخدم قاعدة البيانات للوصول إلى البيانات وعرضها في واجهة المستخدم.