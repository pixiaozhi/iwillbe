package view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/12/15.
 * 不可滑动的ListView,用于情况：
 * 社交，社区APP ，例如 QQ空间，一条说说，说说下面的评论用不可滑动的ListView来展示
 */
public class NoScrollListView extends ListView {
    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }
}
