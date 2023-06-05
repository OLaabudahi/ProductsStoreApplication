package ucas.edu.android.productsstoreapplication;

import java.io.Serializable;

public class Product_db_obj implements Serializable {


    String product_name ;
    double product_price ;
    String product_uri ;
    String product_description ;
    int product_paying_type ;

    public static final int TYPE_CASH = 1 ;
    public static final int TYPE_INSTALLMENT = 2 ;


    public Product_db_obj(String product_name, double product_price, int product_paying_type) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_paying_type = product_paying_type;
        product_uri = null ;
        product_description = null ;
    }

    public Product_db_obj(String product_name, double product_price, int product_paying_type , String product_uri , String product_description) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_paying_type = product_paying_type;
        this.product_uri = product_uri ;
        this.product_description = product_description ;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public String getProduct_uri() {
        return product_uri;
    }

    public String getProduct_description() {
        return product_description;
    }

    public int getProduct_paying_type() {
        return product_paying_type;
    }

    public static int getTypeCash() {
        return TYPE_CASH;
    }

    public static int getTypeInstallment() {
        return TYPE_INSTALLMENT;
    }


    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public void setProduct_uri(String product_uri) {
        this.product_uri = product_uri;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setProduct_paying_type(int product_paying_type) {
        this.product_paying_type = product_paying_type;
    }
}
//الشيفرة التي قمت بمشاركتها هي الفئة Product_db_obj في تطبيق Android. هذه الفئة تُستخدم لتمثيل كائن منتج في قاعدة البيانات.
//
//دعونا نشرح بعض الأجزاء الرئيسية في هذه الفئة:
//
//المتغيرات:
//
//product_name: يحتوي على اسم المنتج.
//product_price: يحتوي على سعر المنتج.
//product_uri: يحتوي على URI لصورة المنتج.
//product_description: يحتوي على وصف المنتج.
//product_paying_type: يحتوي على نوع الدفع للمنتج (نقدًا أو بالتقسيط).
//المستعرضات (Getters و Setters):
//
//تُستخدم للوصول إلى قيم المتغيرات وتعيينها.
//الثوابت:
//
//TYPE_CASH و TYPE_INSTALLMENT: ثوابت تمثل نوع الدفع (نقدًا أو بالتقسيط).
//تستخدم هذه الفئة لتمثيل المنتجات في تطبيقك والوصول إلى معلوماتها مثل اسم المنتج وسعره ونوع الدفع وغيرها. يمكنك استخدامها في محتوى RecyclerView الخاص بك لعرض قائمة المنتجات وتمريرها إلى Product_detailsActivity عند النقر على عنصر محدد.
//
//هذه هي الأساسيات حول الفئة Product_db_obj. إذا كان لديك أي أسئلة محددة أو تحتاج إلى شرح أوضح لجزء معين، فلا تتردد في طرح المزيد من الأسئلة.