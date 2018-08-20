package com.derik.demo.tools;

import android.content.Context;

import java.util.List;

public class PrefUtils {

    public enum Items {
        ACCOUNT_PROFILE("account_profile");

        private String desc;

        Items(String desc){
            this.desc = desc;
        }

        public String getDesc(){
            return desc;
        }

    }

    public static void setCurrentObject(Items name, Object object, Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, name.getDesc(), 0);
        complexPreferences.putObject(name.getDesc(), object);
        complexPreferences.commit();
    }

    public static <T> void getCurrentObject(Items name, Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, name.getDesc(), 0);
//        T t = complexPreferences.getObject(name.getDesc(), a);
        return;
    }

    public static void clearCurrentObject(Context ctx, Items name) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, name.getDesc(), 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }

}
