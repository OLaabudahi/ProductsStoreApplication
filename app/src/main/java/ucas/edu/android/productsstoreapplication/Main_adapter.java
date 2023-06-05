package ucas.edu.android.productsstoreapplication;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class Main_adapter extends RecyclerView.Adapter<Main_adapter.Adapter_views_inflater>{

    private ViewGroup viewGroup ;
    private Context context ;
    private int layout ;
    private ArrayList<Product_db_obj> data ;
    int last_position = -1 ;

    public Main_adapter(Context context, int layout, ArrayList<Product_db_obj> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    public ArrayList<Product_db_obj> getData() {
        return data;
    }

    public void setData(ArrayList<Product_db_obj> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public Main_adapter(ArrayList<Product_db_obj> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public Main_adapter.Adapter_views_inflater onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_custom_adapter , null , false) ;
        Adapter_views_inflater viewHolder = new Adapter_views_inflater(view);

        this.viewGroup = parent ;

        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull Main_adapter.Adapter_views_inflater holder, int position) {
        if (holder.getAdapterPosition()>last_position && position%2!=0 ){
            Animation animation = AnimationUtils.loadAnimation(viewGroup.getContext() , R.anim.fast_animation) ;
            ((Adapter_views_inflater)holder).itemView .startAnimation(animation);
            last_position = holder.getAdapterPosition() ;
        }
        else if (holder.getAdapterPosition()>last_position && position%2==0 ){
            Animation animation = AnimationUtils.loadAnimation(viewGroup.getContext() , R.anim.fast_animation2) ;
            ((Adapter_views_inflater)holder).itemView.startAnimation(animation);
            last_position = holder.getAdapterPosition() ;
        }

        Product_db_obj obj = data.get(position) ;
        holder.name.setText(obj.product_name);
        holder.price.setText(obj.product_price+"");
        int paying_type  = obj.getProduct_paying_type() ;
        if(paying_type == AddNew_ItemActivity.PAYING_CASH){
            holder.paying_type.setText(R.string.cash);}
        else if (paying_type == AddNew_ItemActivity.PAYING_INSTALLMENT){
            holder.paying_type.setText(R.string.installments);
        }

        holder.image.setImageResource(R.drawable.ic_fish);

        if(!TextUtils.isEmpty(obj.getProduct_uri())){
            File f = new File(obj.getProduct_uri()) ;
            if (f.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath())  ;
                holder.image.setImageBitmap(bitmap);
            }
        }
        else {
            if (position%7==0)
                holder.image.setImageResource(R.drawable.ic_fruits);
            else if (position%6==0)
                holder.image.setImageResource(R.drawable.ic_boiled);
            else if (position%5==0)
                holder.image.setImageResource(R.drawable.ic_salad);
            else if (position%4==0)
                holder.image.setImageResource(R.drawable.ic_burger);
            else if (position%3==0)
                holder.image.setImageResource(R.drawable.ic_pizza);
            else if (position%2==0)
                holder.image.setImageResource(R.drawable.ic_soup);
            else
                holder.image.setImageResource(R.drawable.ic_vegetable);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Adapter_views_inflater extends RecyclerView.ViewHolder{
        TextView name ;
        TextView paying_type ;
        TextView price ;
        ImageView image ;

        public Adapter_views_inflater(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_text1) ;
            paying_type = itemView.findViewById(R.id.tv_paying_type) ;
            price  = itemView.findViewById(R.id.tv_price) ;
            image = itemView.findViewById(R.id.img_adp) ;

            Intent go_details = new Intent() ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    go_details.setClass(itemView.getContext() , Product_detailsActivity.class) ;
                    go_details.putExtra(Product_detailsActivity.SERIALIZABLE_KEY, data.get(getLayoutPosition())) ;
                    viewGroup.getContext().startActivity(go_details);
                }
            });

        }
    }
}
//هذا هو محول العرض Main_adapter الذي يُستخدم في RecyclerView لعرض قائمة المنتجات. دعونا نشرح بعض الأجزاء الرئيسية في هذا المحول:
//
//المتغيرات:
//
//context: يستخدم للوصول إلى سياق التطبيق.
//layout: يحدد تخطيط عنصر واجهة المستخدم لكل عنصر في RecyclerView.
//data: يحتوي على بيانات المنتجات التي يتم عرضها في RecyclerView.
//last_position: يستخدم لتتبع آخر موضع تم تحميله في RecyclerView لتطبيق الرسوم المتحركة.
//دالة onCreateViewHolder():
//
//تُستدعى عند إنشاء ViewHolder جديد لعنصر واجهة المستخدم.
//يتم إنشاء عنصر واجهة المستخدم باستخدام LayoutInflater ويتم ربطه بـ ViewHolder.
//دالة onBindViewHolder():
//
//تُستدعى عند ربط بيانات العنصر بـ ViewHolder.
//تُطبق الرسوم المتحركة على العناصر المظهرة.
//يتم الوصول إلى بيانات المنتجات من قائمة البيانات ويتم تعيينها في عناصر واجهة المستخدم في ViewHolder.
//إذا كانت هناك صورة محددة للمنتج، يتم تحميلها وعرضها باستخدام BitmapFactory و ImageView. إلا، يتم تعيين صورة افتراضية بناءً على الموقع في القائمة.
//دالة getItemCount():
//
//تُستدعى لإرجاع عدد العناصر في قائمة البيانات.
//فئة Adapter_views_inflater:
//
//تُمثل ViewHolder لعناصر واجهة المستخدم في RecyclerView.
//يتم ربط عناصر واجهة المستخدم باستخدام findViewById() في دالة البناء الخاصة به.
//يتم تعيين معالج النقر (OnClickListener) لعنصر واجهة المستخدم لفتح تفاصيل المنتج عند النقر عليه.
