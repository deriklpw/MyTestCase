package com.derik.myapps.junitTest;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;

/**
 * Copyright (c) 2017-3-11, derik
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 * Neither the name of the <organization> nor the
 names of its contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

class Calculator {

    public double sum(double a, double b){
        return a+b;
    }

    public double subtract(double a, double b){
        return a-b;
    }

    public double divide(double a, double b){
        return a/b;
    }

    public double multiply(double a, double b){
        return a*b;
    }

    public void myTestFunction(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        uriMatcher.addURI("com.derik.demo","words",1);
//        uriMatcher.addURI("com.derik.demo","word/#",2);
//        uriMatcher.addURI("com.derik.demo","details",3);
//        uriMatcher.addURI("com.derik.demo","details/#/test1/#",4);
//
//        // 进来的Uri对象是标准的content://authority/path/num
//        System.out.println(uriMatcher.match(Uri.parse("content://com.derik.demo/words")));
//        System.out.println(uriMatcher.match(Uri.parse("content://com.derik.demo/word")));
//        System.out.println(uriMatcher.match(Uri.parse("content://com.derik.demo/word/2")));
//        System.out.println(uriMatcher.match(Uri.parse("content://com.derik.demo/word/5")));
//        System.out.println(uriMatcher.match(Uri.parse("content://com.derik.demo/details")));
//        System.out.println(uriMatcher.match(Uri.parse("content://com.derik.demo/details/3/test1/5")));
//        System.out.println(uriMatcher.match(Uri.parse("content://com.derik.demo/details/3/test1/7")));
//        System.out.println(uriMatcher.match(Uri.parse("content://com.derik.demo/details/4/test1/5")));
//        System.out.println(Uri.parse("content://com.derik.demo/details/4/test1/5"));
//        ContentUris.withAppendedId(Uri.parse("content://com.derik.demo/details/4/test1"), 5);
    }

}
