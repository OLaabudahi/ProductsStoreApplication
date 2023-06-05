package ucas.edu.android.productsstoreapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Product_detailsActivity extends AppCompatActivity {

    ImageButton btn_go_home;
    ImageButton btn_plus;
    ImageButton btn_minus;

    TextView tv_quantity_num, tv_product_name, tv_single_price, tv_total_price, tv_product_description, tv_product_payingType;

    ImageView img_productImage;

    Button btn_buy;

    ProjectDatabase db;

    Toast tst_buy_pressed;

    public static MediaPlayer mp_cash;
    public static final String SERIALIZABLE_KEY = "serializable_key";

    private static final int MAX_ORDER_QUANTITY = 30;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        btn_go_home = findViewById(R.id.btn_go_home);
        btn_minus = findViewById(R.id.product_adapter_btn_minus);
        btn_plus = findViewById(R.id.product_adapter_btn_plus);
        tv_quantity_num = findViewById(R.id.product_adapter_tv_num);
        tv_product_name = findViewById(R.id.product_detail_product_name);
        tv_single_price = findViewById(R.id.product_adapter_single_price);
        tv_total_price = findViewById(R.id.product_adapter_total_price);
        tv_product_description = findViewById(R.id.product_adapter_description);
        img_productImage = findViewById(R.id.product_apadter_img);
        tv_product_payingType = findViewById(R.id.product_adapter_paying_type);
        btn_buy = findViewById(R.id.product_adapter_btn_buy);

        tv_quantity_num.setText(String.valueOf(1));

        tst_buy_pressed = new Toast(getBaseContext());

        mp_cash = MediaPlayer.create(this, R.raw.cashnew);

        SharedPreferences UsersPreferences = getSharedPreferences(Create_accountActivity.USERS_PREFERANCES, MODE_PRIVATE);
        SharedPreferences DefultPrefrences = PreferenceManager.getDefaultSharedPreferences(this);

        db = new ProjectDatabase(getBaseContext());

        Intent ProductIntent = getIntent();

        Product_db_obj obj = (Product_db_obj) ProductIntent.getSerializableExtra(SERIALIZABLE_KEY);

        String product_name = obj.getProduct_name();
        double product_price = obj.getProduct_price();
        String product_uri = obj.getProduct_uri();
        String product_description = obj.getProduct_description();
        int product_payingType_int = obj.getProduct_paying_type();

        String payingType_string = null;
        if (product_payingType_int == AddNew_ItemActivity.PAYING_CASH)
            payingType_string = "كاش";
        else if (product_payingType_int == AddNew_ItemActivity.PAYING_INSTALLMENT)
            payingType_string = "تقسيط";

        tv_product_name.setText(product_name);
        tv_single_price.setText("سعر الوحدة  : " + product_price);
        tv_total_price.setText("المجموع  : " + product_price);
        tv_product_payingType.setText("نوع الدفع : " + payingType_string);
        if (!product_description.equals(""))
            tv_product_description.setText("الوصف : " + product_description);
        else
            tv_product_description.setText(R.string.no_description);

        if (!TextUtils.isEmpty(product_uri)) {
            File f = new File(obj.getProduct_uri());
            if (f.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
                img_productImage.setImageBitmap(bitmap);
            }
        } else {
            img_productImage.setImageResource(R.drawable.ic_cloche);
        }

        btn_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_home = new Intent(getBaseContext(), MainActivity.class);
                startActivity(go_home);
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(tv_quantity_num.getText().toString());
                if (num < MAX_ORDER_QUANTITY) {
                    num++;
                    updateQuantityAndTotalPrice(num, product_price);
                } else {
                    Toast.makeText(getBaseContext(), "تم الوصول إلى الحد الأقصى للكمية", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(tv_quantity_num.getText().toString());
                if (num > 1) {
                    num--;
                    updateQuantityAndTotalPrice(num, product_price);
                }
            }
        });

        btn_plus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int num = Integer.parseInt(tv_quantity_num.getText().toString());
                if (num < MAX_ORDER_QUANTITY - 10) {
                    num += 10;
                } else {
                    num = MAX_ORDER_QUANTITY;
                    Toast.makeText(getBaseContext(), "تم الوصول إلى الحد الأقصى للكمية", Toast.LENGTH_SHORT).show();
                }
                updateQuantityAndTotalPrice(num, product_price);
                return true;
            }
        });

        btn_minus.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int num = Integer.parseInt(tv_quantity_num.getText().toString());
                if (num > 10) {
                    num -= 10;
                } else {
                    num = 1;
                }
                updateQuantityAndTotalPrice(num, product_price);
                return true;
            }
        });

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int orderCount = Integer.parseInt(tv_quantity_num.getText().toString());
                if (orderCount > MAX_ORDER_QUANTITY) {
                    Toast.makeText(getBaseContext(), "لقد تجاوزت الحد الأقصى للطلبات", Toast.LENGTH_SHORT).show();
                    return;
                }

                String sale_product_name = tv_product_name.getText().toString();
                double sale_total_price = Double.parseDouble(tv_total_price.getText().toString().substring(10));

                String time_minute = LocalTime.now().getMinute() + "";
                if (time_minute.length() == 1)
                    time_minute = "0" + time_minute;

                String sale_date_time = LocalDate.now() + " , " + LocalTime.now().getHour() + ":" + time_minute;

                int sale_user_id = UsersPreferences.getInt(Sign_inActivity.SIGNED_USER_ID, 0);

                Sale_db_obj sale_obj = new Sale_db_obj(sale_product_name, sale_total_price, sale_date_time, sale_user_id);

                long i = db.insertSale(sale_obj);

                tst_buy_pressed.cancel();

                if (i > -1) {
                    tst_buy_pressed = Toast.makeText(getBaseContext(), "تمت عملية الشراء بنجاح", Toast.LENGTH_LONG);

                    if (DefultPrefrences.getBoolean(SittingActivity.APP_SOUNDS_KEY, false)) {
                        //  mp_cash.pause();
                        mp_cash.setVolume(0.15f, 0.15f);
                        mp_cash.start();
                    }
                } else
                    tst_buy_pressed = Toast.makeText(getBaseContext(), "للأسف ،، فشلت عملية الشراء", Toast.LENGTH_LONG);

                tst_buy_pressed.show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        tst_buy_pressed.cancel();
    }

    private void updateQuantityAndTotalPrice(int quantity, double unitPrice) {
        double totalPrice = quantity * unitPrice;
        tv_quantity_num.setText(String.valueOf(quantity));
        tv_total_price.setText("المجموع  : " + (((int) (totalPrice * 100.0)) / 100.0));
    }
}


//هذه هي الشيفرة الخاصة بـ Product_detailsActivity في تطبيق Android. يقوم هذا النشاط بعرض تفاصيل المنتج المحدد، بما في ذلك اسم المنتج والسعر والوصف وطريقة الدفع وصورة المنتج. يمكن للمستخدم تحديد كمية المنتج وشراء المنتج.
//        دعنا نلقي نظرة على أبرز الأشياء في هذه الشيفرة:

//        في onCreate()، يتم تعيين قيم المتغيرات والاستعلام عن الكائن Product_db_obj المرسل من النشاط السابق باستخدام getSerializableExtra().
//        ثم يتم تحديث وعرض تفاصيل المنتج في الواجهة باستخدام القيم المسترجعة من Product_db_obj.
//        يتم التحكم في زر الشراء (btn_buy) عند النقر عليه، حيث يتم إنشاء كائن Sale_db_obj وإدراجه في قاعدة البيانات باستخدام insertSale() من كائن قاعدة البيانات db.
//        إذا تمت عملية الشراء بنجاح، يتم عرض رسالة توست (Toast) برسالة "تمت عملية الشراء بنجاح". كما يتم تشغيل صوت نقدي (إذا كانت الإعدادات تسمح بذلك) باستخدام MediaPlayer.
//        تم تنفيذ أحداث النقر والاستماع لأزرار زيادة/نقصان الكمية (btn_plus و btn_minus) بالإضافة إلى النقر الطويل عليها لزيادة/نقصان الكمية بقيمة محددة.
//        إن الهدف الرئيسي لـ Product_detailsActivity هو عرض تفاصيل المنتج المحدد وتمكين المستخدم من تحديد كمية المنتج وشراءه.