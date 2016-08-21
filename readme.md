### 模仿建设银行Android 版本圆形菜单:

####  1. 覆写view的onlayout的函数来实现初始化的圆形菜单按钮上的布局（确定每个Imageview的坐标位置）
  

####  2. 覆写dispatchTouchEvnet 函数来实现根据手势判定顺时针滑动还是逆时针滑动:
   * 2.1 ACTION_DOWN 事件中记录下手指的坐标
   * 2.2 ACTION_MOVE 事件中计算角度差，然后调用requestLayout()方法（通过改变布局来实现View的滑动）
   * 2.3 ACTION_UP   手指抬起后，通过Hander机制来实现来实现渐变的动画的滑动   



