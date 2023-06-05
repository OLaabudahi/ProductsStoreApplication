package ucas.edu.android.productsstoreapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    ArrayList<String> data;
    public SpinnerAdapter ( ArrayList<String> data){
        this.data = data ;
    }

    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_adapter , null , false);
        }

        TextView textView1 = convertView.findViewById(R.id.spinner_text1) ;
        textView1.setText(data.get(position));

        return convertView;
    }
}

//هذا هو الكود لـ SpinnerAdapter وهو محول (adapter) مخصص يستخدم لعرض بيانات في عنصر التحكم Spinner في تطبيق Android. دعونا نشرحها:
//       المتغيرات:
//       data: ArrayList يحتوي على البيانات التي سيتم عرضها في عنصر التحكم Spinner.
//       الدوال والوظائف:

//      getCount(): تُرجع عدد العناصر في قائمة البيانات.
//       getItem(): تُرجع العنصر في الموضع المحدد بواسطة المؤشر position.
//        getItemId(): تُرجع معرّف العنصر في الموضع المحدد، ويُستخدم عادةً في حالات معينة لتحديد هوية العنصر.
//        getView(): تُرجع عرض العنصر المحدد في الموضع position في شكل View ليتم عرضه في عنصر التحكم Spinner.
//        إذا كانت convertView هي null، فإنه يتم إنشاء عنصر View جديد باستخدام LayoutInflater ويتم تعيينه على القالب المحدد (spinner_adapter).
//        يتم العثور على عنصر TextView في convertView ويتم تعيين النص المناسب من القائمة data في الموضع position.
//        يتم إرجاع convertView المعدل والذي يحتوي على البيانات المعروضة.
//        يتم استخدام SpinnerAdapter في استخدامات عديدة، حيث يُمكن استخدامه لعرض بيانات مختلفة في عنصر التحكم Spinner، مثل اختيار الخيارات أو التصنيفات أو القوائم المنسدلة الأخرى.