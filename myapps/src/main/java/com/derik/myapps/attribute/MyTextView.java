package com.derik.myapps.attribute;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.derik.myapps.R;

public class MyTextView extends View {

    private int textColor;
    private float textSize;
    private int background;
    private String text;
    private final Paint paint = new Paint();

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 注意事项一：AttributeSet，代表.xml布局文件中组件使用的属性集合
        // attrs 虽然可以直接读取，但取引用值时，只能读取到资源id
        // for(int i =0; i< attrs.getAttributeCount(); i++){
               // 打印每一个使用的属性名和设置的值
        //     System.out.println("attrs:"+attrs.getAttributeCount()+ "; name:"+attrs.getAttributeName(i)+"; value4:"+attrs.getAttributeValue(i));
        // }

        // 注意事项二：TypedArray，styleable
        // TypedArray，可以帮助取attrs属性对应的最终值(即引用资源，解决直接取值时，引用取值为id的情况)
        // styleable，则帮助将所有自定义的资源的id组合成数组(非styleable组织的属性，需要手动初始化为数组)

        // 注意事项三：四种obtainStyledAttributes方法
        // 1. obtainStyledAttributes (int[] attrs)， 此attrs非构造函数中的attrs，构造函数中attrs未使用
        // 取值来源：从Resources.Theme中的style中读取R.styleable.MyTextView数组中各项对应的值
        // TypedArray typedArray = context.getTheme().obtainStyledAttributes(R.styleable.MyTextView);

        // 2. obtainStyledAttributes (int resid, int[] attrs), resid：样式id
        // 取值来源：从样式文件style中读取R.styleable.MyTextView数组中各项对应的值
        // TypedArray typedArray = context.obtainStyledAttributes(R.style.myAttrStyle, R.styleable.MyTextView);

        // 3. obtainAttributes (AttributeSet set, int[] attrs)
        // 取值来源：从set集合（即.xml布局文件中设置属性集合）中读取R.styleable.MyTextView数组中各项对应的值
        // TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);

        // 4. obtainStyledAttributes (AttributeSet set, int[] attrs, int defStyleAttr, int defStyleRes)
        // 取值来源：优先级，高-->低
        // .xml直接设置的属性--> .xml中style属性定义的style--> 一个引用attr，在theme中为该引用指定了style--> 一个指定的styleRes
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView, R.attr.defStyleAttr, R.style.defStyleRes);
        textColor = typedArray.getColor(R.styleable.MyTextView_textColor, 0);
        textSize = typedArray.getDimension(R.styleable.MyTextView_textSize, 16);
        background = typedArray.getColor(R.styleable.MyTextView_backColor, 0);
        text = typedArray.getString(R.styleable.MyTextView_text);

        typedArray.recycle();

    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        canvas.drawColor(background);
        canvas.drawText(text == null ? "123123123" : text, 100, 300, paint);
    }

}
