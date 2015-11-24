package org.dreamfly.circlemenu;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by asus on 2015/11/24.
 */
public class CirCleLayout extends RelativeLayout{

       private ImageView imgCircleLayoutCenter;
       private boolean isOnce;

       private ImageView[] imgArr;

       public CirCleLayout(Context mContext,AttributeSet attrs){
           super(mContext,attrs);
           isOnce=false;
           this.imgArr=new ImageView[6];
       }

       @Override
       public void onLayout(boolean changed, int l, int t, int r, int b){
               super.onLayout(changed, l, t, r, b);
               int piontX=this.getMeasuredWidth()/2;
               int piontY=this.getMeasuredHeight()/2;
               int raduis=this.getMeasuredWidth()/3;
               for(int index=0;index<=5;index++){
                 double angle=(index+1)/3.0;
                 double radioY=Math.sin(angle*Math.PI);
                 double radioX=Math.cos(angle*Math.PI);
                 double paramX=radioX*raduis+piontX;
                 double paramY=radioY*raduis+piontY;
                 this.imgLayout(imgArr[index],(int)paramX,(int)paramY);
               }

       }

       @Override
       public void onFinishInflate(){
         super.onFinishInflate();
         this.imgCircleLayoutCenter=(ImageView)this.findViewById(R.id.img_mainactivity_center);
         this.imgArr[0]=(ImageView)this.findViewById(R.id.img_mainactivity_mbank_1);
         this.imgArr[1]=(ImageView)this.findViewById(R.id.img_mainactivity_mbank_2);
         this.imgArr[2]=(ImageView)this.findViewById(R.id.img_mainactivity_mbank_3);
         this.imgArr[3]=(ImageView)this.findViewById(R.id.img_mainactivity_mbank_4);
         this.imgArr[4]=(ImageView)this.findViewById(R.id.img_mainactivity_mbank_5);
         this.imgArr[5]=(ImageView)this.findViewById(R.id.img_mainactivity_mbank_6);

      }

      @Override
      public void onMeasure(int w,int h){
          super.onMeasure(w,h);
      }

       @Override
      public void onDraw(Canvas canvas){
           super.onDraw(canvas);
           this.setPadding(0,0,0,0);
      }

      private void imgLayout(ImageView imageView,int centerX,int centerY){
            int width=imageView.getMeasuredWidth();
            int height=imageView.getMeasuredHeight();
            int leftPiont=centerX-width/2;
            int topPiont=centerY-height/2;
            int rightPiont=leftPiont+width;
            int botoomPiont=topPiont+height;
            imageView.layout(leftPiont,topPiont,rightPiont,botoomPiont);
      }


}
