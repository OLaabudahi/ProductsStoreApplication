package ucas.edu.android.productsstoreapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class SittingActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    Button sitting_btn_sign_out ;
    ImageButton btn_go_home ;

    ImageView account_img ;

    TextView tv_user_fullname ;
    TextView tv_user_isAdmin ;

    String user_img_uri = "" ;
    int user_gender = 1 ;

    CheckBox cb_soundeffect ;

    int recycler_position = 0 ;
    LayoutAnimationController layoutAnimationController ;

    public static final String APP_SOUNDS_KEY = "app_sound_key" ;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitting);

        SharedPreferences DefultPrefernce = PreferenceManager.getDefaultSharedPreferences(this) ;
        SharedPreferences.Editor DF_Editor = DefultPrefernce.edit() ;

        recyclerView = findViewById(R.id.sitting_recyclerview) ;
        sitting_btn_sign_out = findViewById(R.id.sitting_btn_sign_out) ;
        tv_user_fullname=findViewById(R.id.sitting_tv_account_name) ;
        tv_user_isAdmin=findViewById(R.id.sitting_tv_account_type) ;
        account_img = findViewById(R.id.sitting_account_img) ;
        cb_soundeffect = findViewById(R.id.sitting_appsound_checkbox) ;

        SharedPreferences UsersPreferences = getSharedPreferences(Create_accountActivity.USERS_PREFERANCES , MODE_PRIVATE) ;

        ArrayList<Sitting_objects>data = new ArrayList<>() ;

        data.add(new Sitting_objects( "عرض جميع عمليات الشراء" , R.drawable.ic_card)) ;
        data.add(new Sitting_objects( "عرض مجموع المشتريات " , R.drawable.ic_last_will)) ;
        data.add(new Sitting_objects( "تغيير كلمة السر" , R.drawable.ic_lock)) ;
        data.add(new Sitting_objects( "حذف كل عمليات الشراء" , R.drawable.ic_clear)) ;

        if (UsersPreferences.getBoolean(Sign_inActivity.SIGNED_USER_IS_ADMIN , false)){
            data.add(new Sitting_objects( "إضافة صنف جديد" , R.drawable.ic_add)) ;
            tv_user_isAdmin.setText(R.string.account_type_admin);
        }
        else {
            tv_user_isAdmin.setText(R.string.account_type_normal);
        }


        if (DefultPrefernce.getBoolean(APP_SOUNDS_KEY , false)){
            cb_soundeffect.setChecked(true);
        }
        else {
            cb_soundeffect.setChecked(false);
        }

        tv_user_fullname.setText(UsersPreferences.getString(Sign_inActivity.SIGNED_USER_FULLNAME , "اسم المستخدم"));

        user_img_uri = UsersPreferences.getString(Sign_inActivity.SIGNED_USER_IMG_URI , "" );
        user_gender = UsersPreferences.getInt(Sign_inActivity.SIGNED_USER_GENDER , 1 );

        if(!TextUtils.isEmpty(user_img_uri)){
            File img_file = new File(user_img_uri) ;
            if (img_file.exists()){
                Bitmap user_img_bitmap = BitmapFactory.decodeFile(img_file.getAbsolutePath()) ;
                account_img.setImageBitmap(user_img_bitmap);
            }
        }
        else {
            if (user_gender == Create_accountActivity.USERS_MALE) {
                account_img.setImageResource(R.drawable.ic_man);
            }
            else if (user_gender == Create_accountActivity.USERS_FEMALE){
                account_img.setImageResource(R.drawable.ic_woman);
            }
        }

        Sitting_Adapter adapter = new Sitting_Adapter(data) ;

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this , 1 ) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        layoutAnimationController =
                AnimationUtils.loadLayoutAnimation(getBaseContext() , R.anim.delay_animation);

        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

        sitting_btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignin = new Intent(getBaseContext() , Sign_inActivity.class) ;
                startActivity(goSignin);

                DF_Editor.putBoolean(Splash_screen.IS_SIGNED , false) ;
                DF_Editor.apply();
                finish();
            }
        });

        btn_go_home = findViewById(R.id.btn_go_home) ;

        btn_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_home = new Intent(getBaseContext() , MainActivity.class) ;
                startActivity(go_home);
                finish();
            }
        });

        cb_soundeffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (DefultPrefernce.getBoolean(APP_SOUNDS_KEY, false)) {
                    DF_Editor.putBoolean(APP_SOUNDS_KEY, false);
                    MainActivity.mp.pause();
                } else {
                    DF_Editor.putBoolean(APP_SOUNDS_KEY, true);
                    MainActivity.mp.start();
                }
                DF_Editor.apply();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent go_home = new Intent(getBaseContext() , MainActivity.class) ;
        startActivity(go_home);
        finish();
    }
}
//هذا هو الكود لنشاط SittingActivity الذي يمثل شاشة إعدادات التطبيق. دعونا نشرحها:
//
//المتغيرات:
//
//recyclerView: عنصر RecyclerView المستخدم لعرض قائمة الإعدادات.
//sitting_btn_sign_out: زر تسجيل الخروج.
//btn_go_home: زر العودة إلى الصفحة الرئيسية.
//account_img: صورة حساب المستخدم.
//tv_user_fullname: عنصر TextView لعرض اسم المستخدم.
//tv_user_isAdmin: عنصر TextView لعرض نوع الحساب (مسؤول أو عادي).
//cb_soundeffect: مربع اختيار لتمكين أو تعطيل تأثيرات الصوت في التطبيق.
//recycler_position: متغير لتتبع موضع العنصر المحدد في RecyclerView.
//layoutAnimationController: كائن لتحديد تأثيرات الرسوم المتحركة عند عرض قائمة الإعدادات.
//دالة onCreate():
//
//تقوم بتهيئة الواجهة الخاصة بالنشاط وتعيين المستخدمين الافتراضيين.
//تقوم بتهيئة RecyclerView وتعيين محول العرض (adapter) ومدير العرض (layout manager).
//تعيين البيانات في RecyclerView وتطبيق تأثيرات الرسوم المتحركة.
//تعيين مستمعي النقر لزر تسجيل الخروج وزر العودة إلى الصفحة الرئيسية ومربع الاختيار لتأثيرات الصوت.
//دالة onBackPressed():
//
//تعيين السلوك عند النقر على زر العودة للعودة إلى الصفحة الرئيسية.
//يتضمن هذا النشاط أيضًا استخدام SharedPreferences للوصول إلى إعدادات التطبيق المحفوظة، مثل حالة صوت التطبيق وصورة حساب المستخدم.