package ucas.edu.android.productsstoreapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class product_detail_adapter extends RecyclerView.Adapter<product_detail_adapter.Detail_viewHolder> {

    ArrayList<product_detail_object> data ;
    ViewGroup viewGroup ;
    int last_position = -1 ;

    public product_detail_adapter(ArrayList<product_detail_object> data){
        this.data = data ;
    }

    @NonNull
    @Override
    public Detail_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_details , null , false) ;
        Detail_viewHolder viewHolder = new Detail_viewHolder(view) ;
        viewGroup = parent ;
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull Detail_viewHolder holder, int position) {

        product_detail_object obj = data.get(position) ;
        holder.unit_price.setText("سعر الوحدة : "+obj.pruduct_price+" $");
        holder.description.setText(obj.description);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Detail_viewHolder extends RecyclerView.ViewHolder{
        TextView unit_price ;
        TextView description ;
        public Detail_viewHolder(@NonNull View itemView) {
            super(itemView);
            unit_price = itemView.findViewById(R.id.product_adapter_single_price) ;
            description = itemView.findViewById(R.id.product_adapter_description) ;
        }
    }
}
//الشيفرة التي قمت بمشاركتها هي محول RecyclerView بإسم product_detail_adapter في تطبيق Android. يتم استخدام هذا المحول لعرض تفاصيل المنتج في قائمة RecyclerView.
//
//دعونا نشرح بعض الأجزاء الرئيسية في هذا المحول:
//
//المتغيرات:
//
//data: ArrayList من product_detail_object، يحتوي على بيانات تفاصيل المنتجات التي ستعرض في القائمة.
//viewGroup: ViewGroup تستخدم لتحديد العنصر الأب الذي سيتم إنشاء عناصر القائمة فيه.
//onCreateViewHolder:
//
//يقوم بإنشاء ViewHolder عند الحاجة. يتم تكوين العرض الذي يُعرض فيه تفاصيل المنتج باستخدام LayoutInflater و R.layout.adapter_product_details.
//يتم إنشاء وإرجاع Detail_viewHolder لتمثيل العنصر الفردي في القائمة.
//onBindViewHolder:
//
//يقوم بتعيين البيانات في ViewHolder. يتم الوصول إلى عنصر المصفوفة المناسب في data باستخدام position وتعيين القيم المناسبة لعناصر واجهة المستخدم في holder.
//getItemCount:
//
//يُرجع عدد عناصر القائمة. في هذه الحالة، يُرجع حجم data.
//Detail_viewHolder:
//
//يمثل ViewHolder لعنصر فردي في قائمة التفاصيل.
//يتم تعيين العناصر واجهة المستخدم المناسبة باستخدام findViewById في itemView الممررة إلى البناء الأساسي لـ ViewHolder.
//يُستخدم هذا المحول لعرض تفاصيل المنتجات في قائمة RecyclerView. يمكنك استخدامه مع product_detail_object لتعريف بيانات التفاصيل وعرضها في واجهة المستخدم باستخدام TextViews أو أي عناصر واجهة مخصصة أخرى.
//
//إذا كان لديك أي أسئلة أخرى أو تحتاج إلى شرح أوضح لجزء معين، فلا تتردد في طرح المزيد من الأسئلة.
