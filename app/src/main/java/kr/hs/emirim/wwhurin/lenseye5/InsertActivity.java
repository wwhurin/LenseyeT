package kr.hs.emirim.wwhurin.lenseye5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class InsertActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    EditText edit1, edit2, edit3, edit4,edit5;
    TextView text1, text2, text3, text4,text5,text6;
    Button button;
    ImageButton imbutton;
    String userId="Test03";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("User");

        edit1 = (EditText)findViewById(R.id.userName1);
        edit2 = (EditText)findViewById(R.id.leftEye1);
        edit3 = (EditText)findViewById(R.id.rightEye1);
        edit4 = (EditText)findViewById(R.id.userAge1);

        text1 = (TextView)findViewById(R.id.userName);
        text2 = (TextView)findViewById(R.id.userEye);
        // text3 = (TextView)findViewById(R.id.rightEye);
        text4 = (TextView)findViewById(R.id.userAge);
        text5 = (TextView)findViewById(R.id.userDate);
        text6 = (TextView)findViewById(R.id.textview1);

        imbutton=(ImageButton)findViewById(R.id.button);
        button=(Button)findViewById(R.id.btn1);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(InsertActivity.this, edit4.getText().toString(), Toast.LENGTH_SHORT).show();

                writeUserInfo(userId, edit1.getText().toString(), edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString(),text6.getText().toString());


                // readUserInfo(userId);



            }
        });

    }

//    private void readUserInfo(String userId) {
//
//        DatabaseReference userInfoRef = mDatabase.child("Test11");
//        ValueEventListener userInfoListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                text1.setText(user.getUserName());
//                text2.setText(user.getLeftEye());
//                text3.setText(user.getRightEye());
//                text4.setText(user.getUserAge());
//
//                Toast.makeText(MainActivity.this, user.getUserAge(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        userInfoRef.addValueEventListener(userInfoListener);
//
//    }

    private void writeUserInfo(String userId,String userName, String leftEye, String rightEye, String userAge, String startCase) {

        //String key = mDatabase.push().getKey();
        User user = new User(userName,leftEye,rightEye,userAge,startCase);

        Map<String, Object> userInfo = new HashMap<>();

        userInfo.put("userName",user.getUserName());
        userInfo.put("leftEye",user.getLeftEye());
        userInfo.put("rightEye",user.getRightEye());
        userInfo.put("userAge",user.getUserAge());
        userInfo.put("startDate",user.getStartCase());

        DatabaseReference keyRef = mDatabase.child(userId);
        keyRef.setValue(userInfo);

        Toast.makeText(this, user.getUserAge(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(InsertActivity.this, MainActivity.class);
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
