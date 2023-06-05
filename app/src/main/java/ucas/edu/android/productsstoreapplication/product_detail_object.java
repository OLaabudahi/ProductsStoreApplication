package ucas.edu.android.productsstoreapplication;

public class product_detail_object {
    double pruduct_price ;
    String description ;



    public product_detail_object(double pruduct_price, String description) {
        this.pruduct_price = pruduct_price;
        this.description = description;
    }
}
//الشيفرة التي قمت بمشاركتها تعرف كلاسًا بإسم product_detail_object في تطبيق Android. هذا الكلاس يُمثل كائن تفصيل المنتج ويحتوي على بيانات المنتج المحددة لتعرض في قائمة تفاصيل المنتجات.
//
//المتغيرات:
//
//pruduct_price: double، يُحدد سعر المنتج.
//description: String، يحتوي على وصف المنتج.
//البناء:
//
//يتلقى البناء قيمتين، pruduct_price و description، ويقوم بتعيينهما للمتغيرات المناسبة في الكلاس.
//يُستخدم product_detail_object ككائن بيانات لتمثيل تفاصيل المنتج في تطبيقك. يمكنك إنشاء قائمة ArrayList من كائنات product_detail_object وتمريرها إلى product_detail_adapter لعرض تفاصيل المنتجات في RecyclerView.
//
//عادةً، ستقوم بإنشاء كائن product_detail_object لكل منتج وتعيين القيم الخاصة به، مثل السعر والوصف. ثم ستمرر قائمة هذه الكائنات إلى محول product_detail_adapter لعرضها في واجهة المستخدم.