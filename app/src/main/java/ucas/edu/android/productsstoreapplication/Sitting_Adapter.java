package ucas.edu.android.productsstoreapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Sitting_Adapter extends RecyclerView.Adapter<Sitting_Adapter.Sitting_viewhalder> {

    ArrayList<Sitting_objects> data ;
    ViewGroup viewGroup ;
    int position ;
    Intent intent ;
    View view ;
    int last_position = -1 ;

    public Sitting_Adapter(ArrayList<Sitting_objects> data){
        this.data = data ;
    }

    @NonNull
    @Override
    public Sitting_viewhalder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sitting_custom_adapter  , null ) ;
        Sitting_viewhalder viewhalder = new Sitting_viewhalder(view) ;
        viewGroup = parent ;
        return viewhalder;
    }

    @Override
    public void onBindViewHolder(@NonNull Sitting_viewhalder holder, int position) {
        int adapterPosition = holder.getAdapterPosition(); // Retrieve the adapter position
        if (adapterPosition > last_position && position % 2 == 0) {
            Animation animation = AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.animation);
            ((Sitting_viewhalder) holder).itemView.startAnimation(animation);
            last_position = adapterPosition;
        } else if (adapterPosition > last_position && position % 2 != 0) {
            Animation animation = AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.animation2);
            ((Sitting_viewhalder) holder).itemView.startAnimation(animation);
            last_position = adapterPosition;
        }
        Sitting_objects obj = data.get(adapterPosition);
        holder.textView.setText(obj.sittin_type);
        holder.imageView.setImageResource(obj.img_res);

        this.position = adapterPosition;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Sitting_viewhalder extends RecyclerView.ViewHolder{

        TextView textView ;
        ImageView imageView ;

        public Sitting_viewhalder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.sittig_recycler_tv1) ;
            imageView = itemView.findViewById(R.id.sittig_recycler_img1) ;
            view = itemView ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition() ;
                    intent = new Intent() ;
                    switch (position){
                        case 0 :
                            intent.setClass(viewGroup.getContext() , All_salesActivity.class) ;
                            break;
                        case 1 :
                            intent.setClass(viewGroup.getContext() , Last_saleActivity.class) ;
                            break;
                        case 2 :
                            intent.setClass(viewGroup.getContext() , ChangePasswordActivity.class) ;
                            break;
                        case 3 :
                            intent.setClass(viewGroup.getContext() , DeleteAllSalesActivity.class) ;
                            break;
                        case 4 :
                            intent.setClass(viewGroup.getContext() , AddNew_ItemActivity.class) ;
                            break;
                    }
                    viewGroup.getContext().startActivity(intent);
                }
            });
        }
    }

    public int getPosition() {
        return position;
    }

    public Intent getIntent() {
        return intent;
    }

    public View getView() {
        return view;
    }
}
//هذا هو محول العرض (Adapter) المستخدم في RecyclerView لعرض بيانات قائمة الإعدادات (Settings). دعونا نشرح هذه الفئة:
//
//الخصائص:
//
//data: قائمة (ArrayList) تحتوي على كائنات Sitting_objects التي تمثل بيانات الإعدادات.
//viewGroup: عنصر ViewGroup يتم استخدامه للوصول إلى Context وتمريره إلى النشاط (Activity) الجديد.
//position: متغير يخزن موضع العنصر المنقرض.
//intent: Intent يستخدم لتوجيه المستخدم إلى النشاط (Activity) المناسب بناءً على العنصر الذي تم النقر عليه.
//view: العنصر View الذي يتم النقر عليه.
//دوال الحياة:
//
//onCreateViewHolder(): تُستدعى عند إنشاء ViewHolder جديد لعرض عنصر في RecyclerView. يتم تهيئة العنصر الرسومي وإعداد المستمعين للأحداث.
//onBindViewHolder(): يتم استدعاؤها عند ربط بيانات العنصر بـ ViewHolder. تُعيّن النص والصورة المناسبة لعنصر القائمة وتُعدل العرض بناءً على الموضع.
//getItemCount(): تُرجع عدد العناصر في القائمة.
//الفئة الداخلية:
//
//Sitting_viewhalder: فئة تمثل ViewHolder المستخدم في RecyclerView. تحتوي على TextView و ImageView لعرض بيانات العنصر. يتم تعيين المستمع لعنصر العرض للاستجابة للنقر وتوجيه المستخدم إلى النشاط المناسب بناءً على الموضع.
//يتم استخدام هذا المحول لعرض عناصر الإعدادات في RecyclerView، ويعتمد على بيانات Sitting_objects لعرض النص والصورة المناسبة لكل عنصر في القائمة. عند النقر على عنصر في القائمة، يتم تحديد النشاط المناسب وتوجيه المستخدم إليه باستخدام Intent.