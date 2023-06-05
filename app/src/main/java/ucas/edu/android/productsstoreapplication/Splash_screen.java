package ucas.edu.android.productsstoreapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class Splash_screen extends AppCompatActivity {

    private static int splash_time = 4000 ;
    public static final String IS_SIGNED = "isSigned" ;
    Intent isSigned_intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences DefultPrefernce = PreferenceManager.getDefaultSharedPreferences(this) ;

        isSigned_intent = new Intent() ;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (DefultPrefernce.getBoolean(IS_SIGNED, false)){
                    isSigned_intent.setClass(Splash_screen.this , MainActivity.class) ; }
                else {
                    isSigned_intent.setClass(Splash_screen.this , Sign_inActivity.class) ; }

                startActivity(isSigned_intent);
                finish();
            }
        },splash_time) ;
    }
}
//هذا هو الكود لـ Splash_screen وهو Activity في تطبيق Android يعرض شاشة البداية لفترة محددة قبل تحويل المستخدم إلى النشاط الرئيسي أو شاشة تسجيل الدخول. دعونا نشرحها:
//
//المتغيرات:
//
//splash_time: المتغير الذي يحدد مدة عرض شاشة البداية بالمللي ثانية (هنا هو 4000 مللي ثانية أي 4 ثوانٍ).
//IS_SIGNED: ثابت يحدد مفتاح حالة تسجيل الدخول في SharedPreferences.
//الدوال والوظائف:
//
//onCreate(): يتم استدعاؤها عند إنشاء النشاط. تُعين تخطيط الشاشة باستخدام setContentView() وتستعد لتنفيذ المهمة الرئيسية للنشاط.
//يتم إنشاء كائن من نوع SharedPreferences باستخدام PreferenceManager.getDefaultSharedPreferences(this) للوصول إلى SharedPreferences الافتراضي.
//يتم إنشاء كائن isSigned_intent من النوع Intent للاستخدام في تحويل المستخدم إلى النشاط المناسب بناءً على حالة تسجيل الدخول.
//يتم استخدام Handler لتأخير التنفيذ لفترة زمنية محددة (هنا 4 ثوانٍ) باستخدام postDelayed().
//في الدالة run() المحددة بواسطة postDelayed()، يتم فحص حالة تسجيل الدخول في SharedPreferences.
//إذا كان المستخدم قد سجل الدخول (بناءً على القيمة المخزنة في IS_SIGNED)، يتم تعيين النشاط المقصود إلى MainActivity باستخدام setClass().
//إلا إذا كان المستخدم لم يسجل الدخول، يتم تعيين النشاط المقصود إلى Sign_inActivity باستخدام setClass().
//يتم بدء النشاط المقصود باستخدام startActivity() ومن ثم يتم إغلاق شاشة البداية الحالية باستخدام finish().
//تُستخدم شاشة البداية (Splash Screen) لعرض شعار التطبيق أو شاشة تحميل لفترة قصيرة قبل بدء التطبيق الفعلي. هذا يمنح المستخدم تجربة مرئية قبل بدء التطبيق ويعطيهم انطباعًا بأن التطبيق يعمل ويتحمل.