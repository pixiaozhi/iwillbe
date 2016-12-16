package uitl;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.iwillbe.R;

/**
 * Created by Administrator on 2016/12/15.
 */
public class Setdata {
    /* --------- 数据源----------- */
    //记录回复说说用户
    private String name;
    //记录被回复说说用户
    private String toName;
    //记录评论内容
    private String content;
    //上下文
    Context mContext;
    //创建的textView
    TextView textView;

    /**
     *
     * @param test  上下文
     * @param textView  动态创建的textView
     * @param s  回复说说用户
     * @param s1  被回复说说用户
     * @param s2  评论内容
     */
    public Setdata(Context test, TextView textView, String s, String s1, String s2) {
        this.mContext = test;
        this.textView = textView;
        this.name = s;
        this.toName = s1;
        this.content = s2;
    }

    //抽象类
    public abstract class NolineColorSpan extends ClickableSpan{
        public abstract void onClick(View widget);

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false);
        }
    }

    //主要方法
    public void mSetdata(){
        if (name != null) {
            StringBuilder actionText = new StringBuilder();

            //谁回复
            actionText.append("<a style=\"text-decoration:none;\" href='name' ><font color='#1468a3'>"
                    + name  + "</font> </a>");

            // 回复谁，被回复的人可能不存在。
            if(toName!=null) {
                actionText.append("回复");
                actionText.append("<font color='#1468a3'><a style=\"text-decoration:none;\" href='toName'>"
                        + toName + " " + " </a></font>");
            }
            // 内容
            actionText.append("<font color='#484848'><a style=\"text-decoration:none;\" href='content'>"
                    + ":" + content + " " + " </a></font>");

            textView.setText(Html.fromHtml(actionText.toString()));
            textView.setMovementMethod(LinkMovementMethod
                    .getInstance());
            CharSequence text = textView.getText();
            int ends = text.length();
            Spannable spannable = (Spannable) textView.getText();
            URLSpan[] urlspan = spannable.getSpans(0, ends, URLSpan.class);
            SpannableStringBuilder stylesBuilder = new SpannableStringBuilder(text);
            stylesBuilder.clearSpans();

            for (URLSpan url : urlspan) {
                FeedTextViewURLSpan myURLSpan = new FeedTextViewURLSpan(url.getURL(),
                        mContext,name,toName,content);
                stylesBuilder.setSpan(myURLSpan, spannable.getSpanStart(url),
                        spannable.getSpanEnd(url), spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.setText(stylesBuilder);
            textView.setFocusable(false);
            textView.setClickable(false);
            textView.setLongClickable(false);
        }
    }

    //自定义Spannable分开点击事件类以及文字不同色
    static class FeedTextViewURLSpan extends ClickableSpan {
        private String clickString;
        private Context context;
        // 回复人的名字
        private String name;
        // 被回复人的名字
        private String toName;
        // 评论内容
        private String content;

        public FeedTextViewURLSpan(String clickString, Context context, String name, String toName, String content) {
            this.clickString = clickString;
            this.context = context;
            this.name = name;
            this.toName = toName;
            this.content = content;
        }

        //给标记的部分 的文字 添加颜色
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
            if(clickString.equals("toName")){
                ds.setColor(context.getResources().getColor(R.color.colorPrimary));
            }else if(clickString.equals("name")){
                ds.setColor(context.getResources().getColor(R.color.colorPrimary));
            }
        }

        // 根据文字的标记 来进行相应的 响应事件
        @Override
        public void onClick(View widget) {
            if (clickString.equals("toName")) {
                //可以再次进行跳转activity的操作
                Toast.makeText(context,"点击了"+toName,Toast.LENGTH_SHORT).show();
            } else if (clickString.equals("name")) {
                //可以再次进行跳转activity的操作
                Toast.makeText(context,"点击了"+name,Toast.LENGTH_SHORT).show();
            } else if(clickString.equals("content")){
                //可以再次进去回复评论的操作
                Toast.makeText(context,"点击了"+content,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
