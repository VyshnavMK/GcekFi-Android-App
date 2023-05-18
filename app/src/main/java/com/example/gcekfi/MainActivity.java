package com.example.gcekfi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
            EditText username;
            EditText passwd;
            EditText cpasswd;
            String passWord;
            String userName;
            WebView web;

            public static final String USER_NAME="username_of_gcek_wifi";
            public static final String PASS_WORD="password_of_gcek_wifi";

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                web=findViewById(R.id.web);
                readData();
                if(userName.equals("")){
                    registrationScreen();
                }
                else{
                    connectScreen();

                }



            }
            public void load_url(){

                WebSettings webSettings = web.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                webSettings.setSupportMultipleWindows(true);
                webSettings.setSupportZoom(true);
                webSettings.setDomStorageEnabled(true);
                //Toast.makeText(getApplicationContext(),"again again",Toast.LENGTH_SHORT).show();
                //web.loadUrl("https://gcek.etlab.in/user/login");

                web.loadUrl("http://172.16.0.2:2280/");
                web.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        webWorks();

                    }
                });

            }
            public void readData(){
                SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                userName = sharedPref.getString(USER_NAME,"");
                passWord = sharedPref.getString(PASS_WORD,"");

            }
            public void registrationScreen(){
                username=findViewById(R.id.username);
                passwd=findViewById(R.id.passwd);
                cpasswd=findViewById(R.id.cpasswd);
                Group group = findViewById(R.id.group);
                group.setVisibility(View.VISIBLE);
                Group group1 = findViewById(R.id.group1);
                group1.setVisibility(View.GONE);
                web.setVisibility(View.GONE);

            }
            public void connectScreen(){
                readData();
                Group group = findViewById(R.id.group);
                group.setVisibility(View.GONE);
                Group group1 = findViewById(R.id.group1);
                group1.setVisibility(View.VISIBLE);
                web.setVisibility(View.VISIBLE);
                load_url();
            }

            public void onclkbtn(View view){
                if(validateForm()){

                    SharedPreferences sharedPref =  this.getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(USER_NAME, username.getText().toString());
                    editor.putString(PASS_WORD, passwd.getText().toString());
                    editor.apply();
                    connectScreen();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Check your inputs!!",Toast.LENGTH_SHORT).show();
                }
            }
            public boolean validateForm(){
                if(username.getText().toString().equals("")){
                    return false;
                }
                if(passwd.getText().toString().equals("")){
                    return false;
                }
                return passwd.getText().toString().equals(cpasswd.getText().toString());

            }
            public void onclkconnectbtn(View view){
                load_url();
            }
            public void webWorks(){
                //unnecessary thing

                    //Toast.makeText(getApplicationContext(),"trying to connect",Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),userName,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),passWord,Toast.LENGTH_SHORT).show();
                    //web.loadUrl("javascript:(function f(){var q=document.getElementById('update_btn');q.click();})()");
                    //web.loadUrl("javascript:(function f(){var q = document.getElementsByClassName('submit_btn');q[0].click();})()");

                //gcek thing
//                /*
                web.loadUrl("javascript:var x=window.frames['login_win'];var z=x.document.getElementById('usrname').value ='"+userName+"';");
                web.loadUrl("javascript:var y=window.frames['login_win']; var k=y.document.getElementById('newpasswd').value ='"+passWord+"';");
                web.loadUrl("javascript:var l=window.frames['login_win'];var m=l.document.getElementById('terms').checked='true';");
                web.loadUrl("javascript:(function f(){ var a=document.getElementsByName('submit_login');a[0].submit();})()");
//                */

                //etlab thing
                /*
                web.loadUrl("javascript:var x=document.getElementById('LoginForm_username').value ='"+userName+"';");
                web.loadUrl("javascript:var y=document.getElementById('LoginForm_password').value ='"+passWord+"';");
                web.loadUrl("javascript:(function f(){ var a=document.getElementById('login-form');a.submit();})()");
                */
               // finish();

            }


            public void onclkupdatebtn(View view){

                        registrationScreen();
            }
            @Override
            public void onUserLeaveHint() {
                finish();
            }


}
