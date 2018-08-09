package com.derik.demo.tools;

import android.content.Context;

import java.util.List;

public class PrefUtils {

    public enum Items {
        ACCOUNT_PROFILE("account_profile"),
        ADD_COMMUNITY_PROFILE("add_community_profile"),
        ADDRESS_PROFILE("address_profile"),
        CREDENTIAL("credential"),
        DEVICE_INFO_PROFILE("device_info_profile"),
        DEVICE_PROFILE("device_profile"),
        DEV_PERMISSION_PROFILE("dev_permission_profile"),
        GENDER("gender"),
        LIVE_TYPE("live_type"),
        LOGIN_PROFILE("login_profile"),
        MEMBER_PROFILE("member_profile"),
        MEMBER_STATUS_PROFILE("member_status_profile"),
        PERMISSION_PROFILE("permission_profile"),
        REGISTER_PROFILE("register_profile"),
        RELATIONSHIP("relationship"),
        SMFILE("smfile"),
        TARGET_PROFILE("target_profile"),
        UPDATE_FRIEND_REQUEST("update_friend_request"),
        USER_ATTRIBUTE("user_attribute"),
        USER_PROFILE("user_profile"),
        WORK_TYPE("work_type"),
        ROOMTYPE("roomtype"),
        PASSWORD("password");

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
