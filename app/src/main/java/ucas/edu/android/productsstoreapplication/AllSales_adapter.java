package ucas.edu.android.productsstoreapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllSales_adapter extends RecyclerView.Adapter<AllSales_adapter.AllSalesViewHolder> {

    ArrayList<Sale_db_obj> data ;
    int last_position = -1 ;
    ViewGroup viewGroup ;

    public AllSales_adapter(ArrayList<Sale_db_obj> data){
        this.data = data ;
    }

    public void setData(ArrayList<Sale_db_obj> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllSalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_sales_custom_adapter , null , false) ;
        AllSalesViewHolder viewHolder = new AllSalesViewHolder(view) ;
        viewGroup = parent ;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllSalesViewHolder holder, int position) {
        if (holder.getAdapterPosition()>last_position && position%2==0 ){
            Animation animation = AnimationUtils.loadAnimation(viewGroup.getContext() , R.anim.fast_animation) ;
            ((AllSalesViewHolder)holder).itemView .startAnimation(animation);
            last_position = holder.getAdapterPosition() ;
        }
        else if (holder.getAdapterPosition()>last_position && position%2!=0 ){
            Animation animation = AnimationUtils.loadAnimation(viewGroup.getContext() , R.anim.fast_animation2) ;
            ((AllSalesViewHolder)holder).itemView .startAnimation(animation);
            last_position = holder.getAdapterPosition() ;
        }

        holder.sale_name.setText(data.get(position).sale_product_name);
        holder.date_time.setText(data.get(position).sale_date_time);
        double i =  data.get(position).sale_price ;
        holder.total_price.setText(data.get(position).sale_price+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AllSalesViewHolder extends RecyclerView.ViewHolder {
        TextView sale_name , date_time , total_price ;
        public AllSalesViewHolder(@NonNull View itemView) {
            super(itemView);
            sale_name= itemView.findViewById(R.id.all_sales_tv_sale_name) ;
            date_time= itemView.findViewById(R.id.all_sales_tv_date_time) ;
            total_price= itemView.findViewById(R.id.all_sales_tv_total_sales) ;

        }
    }
}

//هذا الكود هو محول (Adapter) يتم استخدامه في صفحة All_salesActivity لعرض قائمة البيعات في RecyclerView. هنا هو شرح الكود:
//
//يتم تعريف المتغيرات والعناصر الرسومية المستخدمة في المحول مثل ArrayList ومؤشر الموضع الأخير وViewGroup.
//يتم إنشاء محول AllSales_adapter وتمرير البيانات إليه في البناء.
//يتم تعريف الدالة setData لتحديث بيانات البيع في المحول وإعادة ترتيب القائمة.
//يتم تعريف الطريقة onCreateViewHolder لإنشاء ViewHolder الخاص بكل عنصر في القائمة. يتم استخدام تمثيل العنصر all_sales_custom_adapter.xml.
//يتم تعريف الطريقة onBindViewHolder لربط بيانات البيع بعنصر ViewHolder. يتم تحديد التأثيرات البصرية لعناصر القائمة عند التمرير.
//يتم تعريف الطريقة getItemCount لإعادة عدد عناصر البيع المعروضة في القائمة.
//يتم تعريف فئة AllSalesViewHolder كفئة مدمجة (nested class) تحتوي على عناصر واجهة المستخدم لعنصر واحد في القائمة.