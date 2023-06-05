package ucas.edu.android.productsstoreapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Sign_inActivity extends AppCompatActivity {

    Button btn_create_account ;
    Button btn_signin ;

    CheckBox checkbok_remember ;

    Intent from_create_account ;

    EditText et_username ;
    EditText et_password ;

    String entered_username ;
    String entered_password ;

    boolean info_isture = false;

    Toast tst_sign_btn_pressed ;
    Toast tst_back_pressed ;


    public static final String SIGNED_USER_USERNAME = "signed_user_username" ;
    public static final String SIGNED_USER_FULLNAME = "signed_user_fullname" ;
    public static final String SIGNED_USER_PASSWORD = "signed_user_password" ;
    public static final String SIGNED_USER_ID = "signed_user_id" ;
    public static final String SIGNED_USER_GENDER = "signed_user_gender" ;
    public static final String SIGNED_USER_IS_ADMIN = "signed_user_isadmin" ;
    public static final String SIGNED_USER_IMG_URI = "signed_user_imguri" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SharedPreferences DefultPrefernce = PreferenceManager.getDefaultSharedPreferences(this) ;
        SharedPreferences.Editor DF_Editor = DefultPrefernce.edit() ;

        btn_create_account = findViewById(R.id.sign_in_btn_create_account) ;
        btn_signin = findViewById(R.id.sign_in_btn_signin) ;
        checkbok_remember = findViewById(R.id.sign_in_checkbok_remember) ;

        et_username =findViewById(R.id.sign_in_user_name) ;
        et_password = findViewById(R.id.sign_in_password) ;

        tst_back_pressed = new Toast(getBaseContext());
        tst_sign_btn_pressed = new Toast(getBaseContext()) ;

        SharedPreferences UsersPreferences = getSharedPreferences(Create_accountActivity.USERS_PREFERANCES , MODE_PRIVATE) ;
        SharedPreferences.Editor UsersEditor  = UsersPreferences.edit() ;


        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCreateAccount = new Intent(getBaseContext() , Create_accountActivity.class);
                startActivity(goCreateAccount);
            }
        });

        from_create_account = getIntent() ;

        et_username.setText(from_create_account.getStringExtra(Create_accountActivity.ADDED_USER_USERNAME));
        et_password.setText(from_create_account.getStringExtra(Create_accountActivity.ADDED_USER_PASSWORD));

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbok_remember.isChecked()){
                    DF_Editor.putBoolean(Splash_screen.IS_SIGNED , true) ;
                }
                else {
                    DF_Editor.putBoolean(Splash_screen.IS_SIGNED , false) ;
                }
                DF_Editor.apply();

                info_isture =false ;

                entered_username = et_username.getText().toString() ;
                entered_password = et_password.getText().toString() ;

                if (entered_username.equals("") || entered_password.equals(""))
                { tst_sign_btn_pressed.cancel();
                    tst_sign_btn_pressed =  Toast.makeText(Sign_inActivity.this, "الرجاء إدخال اسم المستخدم و كلمة المرور", Toast.LENGTH_SHORT) ;
                    tst_sign_btn_pressed.show();
                }
                else {

                    for (int i = 0 ; i<=UsersPreferences.getInt(Create_accountActivity.USERS_NUM , 0) ; i++)
                    {
                        if
                        (UsersPreferences.getString("u"+ i +"_username"  , "No_User").equals(entered_username))
                        {
                            if (UsersPreferences.getString("u"+ i +"_password"  , "No_User").equals(entered_password)){
                                Intent goHome = new Intent(getBaseContext() , MainActivity.class) ;

                                UsersEditor.putString(SIGNED_USER_USERNAME , UsersPreferences.getString("u"+ i +"_username"  , "no_username")) ;
                                UsersEditor.putString(SIGNED_USER_FULLNAME , UsersPreferences.getString("u"+ i +"_fullname"  , "بدون اسم")) ;
                                UsersEditor.putString(SIGNED_USER_PASSWORD , UsersPreferences.getString("u"+ i +"_password"  , "no_password")) ;
                                UsersEditor.putBoolean(SIGNED_USER_IS_ADMIN , UsersPreferences.getBoolean("u"+ i +"_isAdmin"  , false)) ;
                                UsersEditor.putString(SIGNED_USER_IMG_URI , UsersPreferences.getString("u"+ i +"_imgUri"  , "")) ;
                                UsersEditor.putInt(SIGNED_USER_ID , UsersPreferences.getInt("u"+ i +"_id"  , 0)) ;
                                UsersEditor.putInt(SIGNED_USER_GENDER , UsersPreferences.getInt("u"+ i +"_gender"  , 1)) ;
                                UsersEditor.apply();

                                startActivity(goHome);
                                finish();
                            }
                            else
                            {
                                tst_sign_btn_pressed.cancel();
                                tst_sign_btn_pressed =  Toast.makeText(Sign_inActivity.this, "كلمة المرور خاطئة", Toast.LENGTH_SHORT);
                                tst_sign_btn_pressed.show();
                            }
                            info_isture =true ;
                        }
                        else if (i==UsersPreferences.getInt(Create_accountActivity.USERS_NUM , 0)
                                &&!info_isture){
                            tst_sign_btn_pressed.cancel();
                            tst_sign_btn_pressed = Toast.makeText(Sign_inActivity.this, "اسم المستخدم الذي أدخلته خاطئ ", Toast.LENGTH_LONG);
                            tst_sign_btn_pressed.show();
                        }

                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        tst_back_pressed.cancel();
        tst_back_pressed = Toast.makeText(this, "لا يمكنك العودة للوراء", Toast.LENGTH_SHORT) ;
        tst_back_pressed.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        tst_sign_btn_pressed.cancel();
        tst_back_pressed.cancel();
    }
}

//تبدو هذه الشيفرة كفئة Java تابعة لتطبيق Android تسمى Sign_inActivity. تستخدم هذه الفئة لتنفيذ شاشة تسجيل الدخول في التطبيق. إليك شرح للعناصر الرئيسية في هذه الفئة:

//        الخصائص:

//        btn_create_account و btn_signin: أزرار تمثل زر إنشاء حساب جديد وزر تسجيل الدخول على التوالي.
//        checkbok_remember: خانة اختيار لتمكين المستخدم من تذكر تفاصيل تسجيل الدخول.
//        from_create_account: كائن Intent يُستخدم لتلقي البيانات المرسلة من شاشة إنشاء حساب جديد.
//       et_username و et_password: حقول إدخال لاسم المستخدم وكلمة المرور على التوالي.
//        entered_username و entered_password: متغيرات تستخدم لتخزين القيم المدخلة في حقول إدخال اسم المستخدم وكلمة المرور.
//        info_isture: متغير منطقي يشير إلى صحة معلومات تسجيل الدخول (إذا كانت صحيحة أم لا).
//        tst_sign_btn_pressed و tst_back_pressed: كائنات Toast تُستخدم لعرض رسائل تنبيه للمستخدم.
//        الثوابت:

//        ثوابت السلسلة المستخدمة لتعريف مفاتيح SharedPreferences لتخزين بيانات المستخدم المسجل دخوله.
//        دوال الحياة الداخلية:

//        onCreate(): يتم استدعاءها عند إنشاء النشاط (Activity) وتهيئة الواجهة الرسومية وإعداد المستمعين للأحداث.
//        onBackPressed(): يتم استدعاؤها عند الضغط على زر العودة في الجهاز، وهي تعرض رسالة تنبيه للمستخدم.
//        onPause(): يتم استدعاؤها عندما يتم إخفاء النشاط، وتلغي رسائل التنبيه التي قد تكون قيد العرض.
//       المستمعين والعمليات:

//       تم تعيين مستمع لزر "إنشاء حساب" (btn_create_account) يقوم بتوجيه المستخدم إلى شاشة إنشاء حساب جديد.
//        تم تعيين مستمع لزر "تسجيل الدخول" (btn_signin) يقوم بفحص معلومات تسجيل الدخول المدخلة والتحقق من صحتها، وإذا كانت صحيحة يقوم بتوجيه المستخدم إلى الشاشة الرئيسية.
//        تم استخدام SharedPreferences (DefultPrefernce و DF_Editor) لتخزين إعدادات تذكر تفاصيل تسجيل الدخول وتطبيقها عند الضغط على زر "تسجيل الدخول".
//        تم استخدام SharedPreferences (UsersPreferences و UsersEditor) للوصول إلى بيانات المستخدمين المسجلين وتخزين معلومات المستخدم المسجل دخوله.
//        إذا تم تحديد خانة الاختيار "تذكرني" (checkbok_remember): تتم تخزين قيمة منطقية معينة في SharedPreferences للاستفادة منها لاحقًا.
//        تهدف هذه الفئة إلى تنفيذ واجهة تسجيل الدخول والتحقق من صحة معلومات المستخدم وتوجيهه إلى الشاشة الرئيسية في حالة النجاح.