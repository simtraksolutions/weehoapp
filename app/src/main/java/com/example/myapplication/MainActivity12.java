    package com.example.myapplication;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.cardview.widget.CardView;
    import androidx.constraintlayout.widget.ConstraintLayout;

    import android.annotation.SuppressLint;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.Gravity;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.LinearLayout;
    import android.widget.RelativeLayout;
    import android.widget.TextView;

    public class MainActivity12 extends AppCompatActivity {
        LinearLayout layout;
        private SharedPreferences sharedPreferences;
        private SharedPreferences.Editor editor;
        CardView bookanevent, logout, contact, performers,history;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main12);
            sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
            editor = sharedPreferences.edit();
            Intent intent = getIntent();
            String useremail = intent.getStringExtra("keymail");
            String usermail1 = intent.getStringExtra("keymail1");
            String usercode = intent.getStringExtra("keycode");
            String userphone = intent.getStringExtra("keyphone");
            editor.putString("fmail", useremail);
            editor.putString("fphone", userphone);
            editor.commit();
            if(sharedPreferences.getString("islogin","false").equals("false")){
                editor.putString("islogin","yes");
                editor.commit();
                startActivity(new Intent(MainActivity12.this,MainActivity2.class));
            }
            Intent intentE = getIntent();
            String retrievedemail = intentE.getStringExtra("mail1");
            String retrievedphone = intentE.getStringExtra("phone1");
            layout = findViewById(R.id.dashboard);

            layout.setBackgroundResource(R.drawable.background1);

            String savedEmail = sharedPreferences.getString("fmail", "");
            String savedPhone = sharedPreferences.getString("fphone", "");


            Intent intent1 = getIntent();
            String rname = intent1.getStringExtra("nam");
            bookanevent = findViewById(R.id.dasboardbookanevent);
            performers = findViewById(R.id.cardView15);
            logout = findViewById(R.id.cardView14);

            history = findViewById(R.id.cardView12);
            contact = findViewById(R.id.cardView13);

            bookanevent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity12.this, MainActivity3.class);
                    intent.putExtra("fmail",savedEmail);
                    intent.putExtra("fphone",savedPhone);
                    intent.putExtra("keymail", useremail);
                    intent.putExtra("keymail1", usermail1);
                    intent.putExtra("keyphone", userphone);
                    intent.putExtra("keycode", usercode);
                    intent.putExtra("mail1",retrievedemail);
                    intent.putExtra("phone1",retrievedphone);
                    startActivity(intent);
                }
            });

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editor.putString("islogin","false");
                    editor.commit();
                    Intent intent = new Intent(MainActivity12.this, MainActivity2.class);
                    startActivity(intent);
                }
            });
            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity12.this,MainActivity17.class);
                    intent.putExtra("nam",rname);
                    startActivity(intent);
                }
            });

            performers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity12.this, MainActivity15.class);
                    startActivity(intent);
                }
            });

            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity12.this, MainActivity14.class);
                    intent.putExtra("keymail", useremail);
                    intent.putExtra("keymail1", usermail1);
                    intent.putExtra("keyphone", userphone);
                    startActivity(intent);
                }
            });
        }


        }

