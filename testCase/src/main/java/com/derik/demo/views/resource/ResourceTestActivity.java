package com.derik.demo.views.resource;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.derik.demo.R;

public class ResourceTestActivity extends Activity {

    private TextView xmlResult1;
    private TextView xmlResult2;
    private TextView xmlResult3;
    private Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_test);
        xmlResult1 = (TextView) findViewById(R.id.third_resource_textView1);
        xmlResult2 = (TextView) findViewById(R.id.third_resource_textView2);
        xmlResult3 = (TextView) findViewById(R.id.third_resource_textView3);

        click= (Button) findViewById(R.id.third_resource_button1);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmlResourceParser xrp = getResources().getXml(R.xml.books);
                try{
                    StringBuilder allTags = new StringBuilder("");
                    StringBuilder bookInfo = new StringBuilder();
                    while(xrp.getEventType() != XmlResourceParser.END_DOCUMENT){
                        if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                            String tagName = xrp.getName();
                            if (tagName.equals("book")){
                                bookInfo.append(xrp.getAttributeName(0)+": "+xrp.getAttributeValue(0)+", ");
                                bookInfo.append(xrp.getAttributeName(1)+": "+xrp.getAttributeValue(1)+", ");
                                bookInfo.append("书名: "+xrp.nextText()+"\n");

                            }
                            allTags.append(tagName);
                            allTags.append("\n");
                        }

                        xrp.next();
                    }

                    xmlResult1.setText("所有标签："+allTags.toString());
                    xmlResult2.setText(bookInfo.toString());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
