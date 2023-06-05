package ucas.edu.android.productsstoreapplication;

public class Sale_db_obj {
    String sale_product_name ;
    double sale_price ;
    String sale_date_time ;
    int sale_user_id ;

    public Sale_db_obj(String sale_product_name, double sale_price, String sale_date_time, int sale_user_id) {
        this.sale_product_name = sale_product_name;
        this.sale_price = sale_price;
        this.sale_date_time = sale_date_time;
        this.sale_user_id = sale_user_id;
    }

    public void setSale_product_name(String sale_product_name) {
        this.sale_product_name = sale_product_name;
    }

    public void setSale_price(double sale_price) {
        this.sale_price = sale_price;
    }

    public void setSale_date_time(String sale_date_time) {
        this.sale_date_time = sale_date_time;
    }

    public void setSale_user_id(int sale_user_id) {
        this.sale_user_id = sale_user_id;
    }

    public String getSale_product_name() {
        return sale_product_name;
    }

    public double getSale_price() {
        return sale_price;
    }

    public String getSale_date_time() {
        return sale_date_time;
    }

    public int getSale_user_id() {
        return sale_user_id;
    }
}
//تبدو هذه الشيفرة كفئة Java تسمى Sale_db_obj وتُستخدم لتمثيل كائن يمثل سجل بيع في قاعدة البيانات. هي عبارة عن كلاس بسيط يحتوي على الخصائص والوظائف التالية:
//
//الخصائص (المتغيرات):
//
//sale_product_name: يتم تخزين اسم المنتج الذي تم بيعه في هذا السجل.
//sale_price: يتم تخزين سعر البيع في هذا السجل.
//sale_date_time: يتم تخزين تاريخ ووقت البيع في هذا السجل.
//sale_user_id: يتم تخزين معرف المستخدم الذي قام بالبيع في هذا السجل.
//الدوال (الأساليب):
//
//Sale_db_obj(): هو البناء (Constructor) للكائن من الفئة. يستخدم لتهيئة الكائن عند إنشائه وتعيين قيم الخصائص الأساسية (اسم المنتج، سعر البيع، تاريخ البيع، معرف المستخدم).
//دوال الضبط (setters): setSale_product_name(), setSale_price(), setSale_date_time(), setSale_user_id(). تُستخدم لتعيين قيم جديدة للخصائص.
//دوال الاسترداد (getters): getSale_product_name(), getSale_price(), getSale_date_time(), getSale_user_id(). تُستخدم لاسترداد قيم الخصائص.
//تم تصميم هذه الفئة لتسهيل تمثيل سجلات المبيعات في قاعدة البيانات والوصول إلى بيانات السجل بسهولة باستخدام الدوال المناسبة.