package ucas.edu.android.productsstoreapplication;

import android.net.Uri;

public class Custom_adp_obj {
    String name ;

    public Uri getImg_uri() {
        return img_uri;
    }

    public void setImg_uri(Uri img_uri) {
        this.img_uri = img_uri;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    String paying_type ;
    double price ;
    Uri img_uri ;

    public String getPaying_type() {
        return paying_type;
    }

    public void setPaying_type(String paying_type) {
        this.paying_type = paying_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
//الشيفرة التي قدمتها هي تعريف لصنف (class) يُسمى Custom_adp_obj في تطبيق Android. يُستخدم هذا الصنف لتعريف كائنات مخصصة تستخدم في تكوين عناصر واجهة المستخدم أو البيانات المرتبطة بها.
//
//دعونا نشرح الأجزاء الرئيسية في هذا الصنف:
//
//المتغيرات:
//
//name: يُستخدم لتخزين اسم العنصر أو الكائن.
//paying_type: يُستخدم لتخزين نوع الدفع المرتبط بالكائن.
//price: يُستخدم لتخزين قيمة السعر المرتبطة بالكائن.
//img_uri: يُستخدم لتخزين URI لصورة الكائن.
//الوظائف (الأساليب):
//
//getName(): تُستخدم للحصول على قيمة المتغير name.
//setName(): تُستخدم لتعيين قيمة المتغير name.
//getPaying_type(): تُستخدم للحصول على قيمة المتغير paying_type.
//setPaying_type(): تُستخدم لتعيين قيمة المتغير paying_type.
//getPrice(): تُستخدم للحصول على قيمة المتغير price.
//setPrice(): تُستخدم لتعيين قيمة المتغير price.
//getImg_uri(): تُستخدم للحصول على قيمة المتغير img_uri.
//setImg_uri(): تُستخدم لتعيين قيمة المتغير img_uri.
//يتيح هذا الصنف إمكانية تخزين البيانات ذات الصلة بكائن مخصص، مثل اسم ونوع الدفع والسعر وURI للصورة. يمكن استخدامه في سياقات مختلفة حسب احتياجات تطبيقك مثل عرض عناصر في قائمة أو استخدامها في محول (adapter) لعرض بيانات في واجهة المستخدم.