package ucas.edu.android.productsstoreapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

public class DeleteAllSalesActivity extends AppCompatActivity {
    Window window ;
    ImageButton btn_go_home ;

    Button btn_delete ;

    ProjectDatabase db ;

    CheckBox cb_iam_sure ;
    Toast tst_delete_pressed ;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_all_sales);

        btn_go_home = findViewById(R.id.btn_go_home) ;
        btn_delete = findViewById(R.id.delete_sales_btn_delete) ;
        cb_iam_sure = findViewById(R.id.delete_sales_checkbox) ;

        SharedPreferences UsersPreferences = getSharedPreferences(Create_accountActivity.USERS_PREFERANCES , MODE_PRIVATE) ;

        db = new ProjectDatabase(getBaseContext()) ;

        tst_delete_pressed = new Toast(getBaseContext()) ;

        window = this.getWindow() ;
        window.setStatusBarColor(Color.parseColor("#F82626"));

        btn_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_home = new Intent(getBaseContext() , MainActivity.class) ;
                startActivity(go_home);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user_id = UsersPreferences.getInt(Sign_inActivity.SIGNED_USER_ID  , 0) ;
                long i = 0 ;
                tst_delete_pressed.cancel();

                if (cb_iam_sure.isChecked()){

                    i = db.deleteAllSalesForUser(user_id) ;
                    if (i>2 && i<=10)
                        tst_delete_pressed = Toast.makeText(getBaseContext() ,"تم حذف " + i + " عمليات شراء" , Toast.LENGTH_LONG) ;
                    else if (i>10)
                        tst_delete_pressed = Toast.makeText(getBaseContext() ,"تم حذف " + i + " عمليات شراء" , Toast.LENGTH_LONG) ;
                    else if (i==1)
                        tst_delete_pressed = Toast.makeText(getBaseContext() ,"تم حذف عملية شراء واحدة"  , Toast.LENGTH_LONG) ;
                    else if (i==2)
                        tst_delete_pressed = Toast.makeText(getBaseContext() ,"تم حذف عمليتي شراء"  , Toast.LENGTH_LONG) ;
                    else if (i==0)
                        tst_delete_pressed = Toast.makeText(getBaseContext() ,"لم يتم حذف أي عملية شراء"  , Toast.LENGTH_LONG) ;
                }
                else {
                    tst_delete_pressed = Toast.makeText(getBaseContext() ,"لتأكيد عملية الحذف اضغط على علامة ✔ من فضلك" , Toast.LENGTH_LONG) ;
                }
                tst_delete_pressed.show();
            }
        });





    }

    @Override
    protected void onPause() {
        super.onPause();
        tst_delete_pressed.cancel();
    }
}
//الشيفرة التي قدمتها هي نشاط (Activity) في تطبيق Android يُسمى DeleteAllSalesActivity. يُستخدم هذا النشاط لعرض واجهة المستخدم لحذف جميع عمليات البيع.
//
//دعونا نشرح الأجزاء الرئيسية في هذا النشاط:
//
//المتغيرات:
//
//window: تُستخدم للوصول إلى نافذة النشاط وتغيير لون شريط الحالة.
//btn_go_home: تُستخدم للربط بزر العودة إلى الصفحة الرئيسية.
//btn_delete: تُستخدم للربط بزر الحذف.
//cb_iam_sure: تُستخدم للربط بخانة الاختيار "أنا متأكد".
//db: تُستخدم للوصول إلى قاعدة البيانات.
//tst_delete_pressed: تُستخدم لإظهار رسالة منبثقة (Toast) بنتيجة عملية الحذف.
//دالات الحدث:
//
//onCreate(): تُستدعى عند إنشاء النشاط وتُستخدم لتهيئة واجهة المستخدم وإعدادات أخرى. يتم ربط عناصر واجهة المستخدم مثل الأزرار وخانات الاختيار باستخدام findViewById(). يتم تعيين قيم للمتغيرات وتهيئة قاعدة البيانات والتلاعب بألوان شريط الحالة.
//onPause(): تُستدعى عندما يتوقف النشاط عن الظهور للمستخدم. في هذا النشاط، يتم إلغاء عرض رسالة الحذف (إذا كانت قيد العرض) عندما يتم وقف النشاط.
//معالجات الحدث:
//
//btn_go_home.setOnClickListener(): تُستدعى عند النقر على زر "العودة إلى الصفحة الرئيسية" وتُستخدم للانتقال إلى النشاط الرئيسي (MainActivity).
//btn_delete.setOnClickListener(): تُستدعى عند النقر على زر "حذف" وتُستخدم للتحقق من تحديد خانة الاختيار "أنا متأكد". إذا تم تحديدها، يتم استدعاء دالة deleteAllSalesForUser() من قاعدة البيانات لحذف جميع عمليات البيع للمستخدم الموقع الحالي. يتم عرض رسالة منبثقة (Toast) بنتيجة الحذف.
//إذا لم يتم تحديد خانة الاختيار، يتم عرض رسالة منبثقة تطلب تأكيد الحذف.
//هذا النشاط يُعرض واجهة المستخدم لحذف جميع عمليات البيع، ويستخدم قاعدة البيانات للقيام بالعملية الفعلية للحذف.