package org.dreamfly.circlemenu;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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

       private int  mCenterX;
       private int  mCenterY;
       private int  mRadius;

       private float mLastX;
       private float mLastY;

       private float mstartAngle;

       private float mDisAnlge;

       private final static int  ONE_QUADRANT=1;
       private final static int  TWO_QUADRANT=2;
       private final static int  THREE_QUADRANT=3;
       private final static int  FOUR_QUADRANT=4;



       public CirCleLayout(Context mContext,AttributeSet attrs){
           super(mContext,attrs);
           isOnce=false;
           this.imgArr=new ImageView[6];
           mDisAnlge=60.0f;
       }

      @Override
      public void onLayout(boolean changed, int l, int t, int r, int b){
        super.onLayout(changed, l, t, r, b);
        //找到圆心坐标
        int piontX=this.getMeasuredWidth()/2;
        int piontY=this.getMeasuredHeight()/2;

        //设置半径
        int raduis=this.getMeasuredWidth()/3;

        mCenterX=piontX;
        mCenterY=piontY;
        mRadius=raduis;

        for(int index=0;index<=5;index++){

              //根据sin,cos函数去计算每个ImageView应该有的中心点
              double angle=(mstartAngle+mDisAnlge*(index+1))/180.0;
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

    /**
     * 每个ImageView中心点布局
     * @param imageView
     * @param centerX
     * @param centerY
     */
      private void imgLayout(ImageView imageView,int centerX,int centerY){
            int width=imageView.getMeasuredWidth();
            int height=imageView.getMeasuredHeight();
            int leftPiont=centerX-width/2;
            int topPiont=centerY-height/2;

            int rightPiont=leftPiont+width;
            int botoomPiont=topPiont+height;
            imageView.layout(leftPiont,topPiont,rightPiont,botoomPiont);

      }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
          switch (event.getAction()){
              case MotionEvent.ACTION_DOWN:
                  //touchX,touchY 是针对一个组件内部左边值
                  this.mLastX=event.getX();
                  this.mLastY=event.getY();
                  return(true);
              case MotionEvent.ACTION_UP:

                  break;
              case MotionEvent.ACTION_MOVE:
                  float endX=event.getX();
                  float endY=event.getY();
                  float startAngle=this.calAngle((int)mLastX,(int)mLastY);
                  float endAngle=this.calAngle((int)endX,(int)endY);
                  this.mLastX=endX;
                  this.mLastY=endY;
                  float disAngle=endAngle-startAngle;

                  int quart=this.judgeQuadrant((int)endX,(int)endY);
                  //如果是1，4象限，则起始角度可以按照普通三角函数的角度象限法（顺时针减，逆时针增）来递增，递减
                  if(quart==ONE_QUADRANT || quart==FOUR_QUADRANT) {
                         this.mstartAngle -= disAngle;
                  //如果为2,3 象限，则其实角度的递增和递减是反着的.
                  }else if(quart==TWO_QUADRANT || quart==THREE_QUADRANT){
                          this.mstartAngle+=disAngle;
                  }
                  requestLayout();
                  break;
          }
          return(super.dispatchTouchEvent(event));
    }

    /**
     *
     * @param touchX
     * @param touchY
     */
    private float calAngle(int touchX,int touchY){
          double disX=touchX-mCenterX;
          double disY=mCenterY-touchY;
          double radiuTmp=Math.sqrt(Math.abs(disX*disX)+Math.abs(disY*disY));
          double sinValue=disY/radiuTmp;

          if(sinValue>=1.0){
              sinValue=1.0d;
          }else if(sinValue<=-1.0){
              sinValue=-1.0d;
          }
          return (float)(Math.asin(sinValue)/Math.PI*180.0);
    }


    /**
     * 判断象限
     * @param touchX
     * @param touchY
     */
    private int judgeQuadrant(int touchX,int touchY){
          if(touchX>=this.mCenterX && touchY<=this.mCenterY){
               return(ONE_QUADRANT);
          }else if(touchX<=this.mCenterX && touchY<=this.mCenterY){
               return(TWO_QUADRANT);
          }else if(touchX<=this.mCenterX && touchY>=this.mCenterY){
              return(THREE_QUADRANT);
          }else if(touchX>=this.mCenterX && touchY>=this.mCenterY){
               return(FOUR_QUADRANT);
          }else{
              return (-1);
          }

    }


}
