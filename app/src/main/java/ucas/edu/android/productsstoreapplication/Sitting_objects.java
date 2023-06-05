package ucas.edu.android.productsstoreapplication;

public class Sitting_objects {

    String sittin_type ;
    int img_res ;

    public Sitting_objects(String sittin_type , int img_res){
        this.sittin_type=sittin_type ;
        this.img_res = img_res ;
    }
}
//هذا هو الكود للفئة Sitting_objects والتي تعرف بيانات العناصر المستخدمة في قائمة الإعدادات (Settings). دعونا نشرحها:
//
//الخصائص:
//
//sittin_type: سلسلة (String) تمثل نوع الإعداد.
//img_res: متغير صحيح (int) يحتوي على مصدر الصورة المرتبطة بالإعداد.
//البناء:
//
//يتلقى البناء سلسلة sittin_type ومصدر الصورة img_res.
//يستخدم لتعيين قيم الخصائص الداخلية للفئة.
//يتم استخدام الكائنات من هذه الفئة في محول العرض (Adapter) لتعريف بيانات العناصر المعروضة في قائمة الإعدادات. تحتوي كل كائن على نوع الإعداد ومصدر الصورة المرتبطة به. يمكن تمرير قائمة من كائنات Sitting_objects إلى محول العرض لعرض البيانات في RecyclerView بشكل صحيح.
