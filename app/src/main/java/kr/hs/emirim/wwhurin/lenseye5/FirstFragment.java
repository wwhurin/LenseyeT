package kr.hs.emirim.wwhurin.lenseye5;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.app.Activity.RESULT_OK;
import static java.lang.Integer.parseInt;


public class  FirstFragment extends Fragment
{
    static boolean check=false;

    private TimerTask second;
    private TextView timer_text;
    private final Handler handler = new Handler();

    Button button;
    static long now; //현재 시간 msec로 구함
    static Date date; //현재 시간 date에 저장
    static SimpleDateFormat sdfnow; //24시간으로 형태 포맷
    static String formatDate; //nowDate에 값 저장
    static ImageView imageView;

    TextView lens_time_when;
    TextView tname;

    TextView dateNow;

    String name;
    int sdate[]=new int[4];

   // public var user;

    public FirstFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
      //  super.onCreateView(inflater, container, savedInstanceState);
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_first, container, false);
        imageView=(ImageView) layout.findViewById(R.id.imageView3);
        lens_time_when=(TextView)layout.findViewById(R.id.lens_time_when);
        imageView.setOnClickListener(m);
        tname=(TextView)layout.findViewById(R.id.name);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
               name= profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();

            };


        }
        tname.setText(name+"님");
        return layout;

    }




//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        View view = inflater.inflate(R.layout.activity_tab1, null);
//
//        출처: http://itmir.tistory.com/283 [미르의 IT 정복기]
//        getActivity().setContentView(R.layout.fragment_first);
//        //RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_first, container, false);
//        imageView=(ImageView) View.findViewById(R.id.imageView3);
//        imageView.setOnClickListener(m);
//    }
 /*   public void eyebtn(){
        Toast.makeText(getActivity().getApplication(), "버튼눌림", Toast.LENGTH_SHORT);
        mOnPopupClick();
            *//*if(check)
                new AlarmHATT(getActivity().getApplicationContext()).Alarm();*//*
    }*/


    public static class MyAlertDialogFragment extends DialogFragment {

        public static MyAlertDialogFragment newInstance(String title) {
            MyAlertDialogFragment frag = new MyAlertDialogFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //return super.onCreateDialog(savedInstanceState);

            String title = getArguments().getString("title");
            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.icon)
                    .setTitle(title)
                    .setMessage("렌즈 착용을 시작합니다.")
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i("MyLog", "확인 버튼이 눌림");
                        }
                    })
                   /*.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i("MyLog", "취소 버튼이 눌림");
                        }
                    })*/
                    .create();
        }

    }

    public class AlarmHATT{
        private Context context;
        public AlarmHATT(Context context){
            this.context=context;
        }

        public void Alarm(){
            Toast.makeText(context, "알람 시작", Toast.LENGTH_SHORT);
            Log.i("MyLog", "알람한다 눌림");
            now = System.currentTimeMillis();
            date = new Date(now);
            sdfnow = new SimpleDateFormat("MM-dd-HH-mm");
            formatDate = sdfnow.format(date);

            String[] startTime = formatDate.split("-");

            for (int i = 0; i < 4; i++) {
                sdate[i] = parseInt(startTime[i]);
            }


            AlarmManager am=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getActivity().getApplicationContext(), BroadcastD.class);

            PendingIntent sender = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, intent, 0);

            Calendar calendar=Calendar.getInstance();//알람시간 calendar에 set

           // calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), sdate[2]+1, sdate[3], calendar.get(Calendar.SECOND));
           // calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR, 1), sdate[3], calendar.get(Calendar.SECOND));
          //  calendar.set(calendar.HOUR, +1);
            calendar.set(calendar.MINUTE, 8+sdate[2]); testStart();
            //알람 예약
            //am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender); boolean stopchk = in
            // tent.getExtras().getBoolean("stopchk");
           // if(stopchk){

           // }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                String result = data.getStringExtra("result");
                //Toast.makeText(getContext(), "되라", Toast.LENGTH_SHORT);
                check=data.getExtras().getBoolean("check");  //Log.i("MyLog", String.valueOf(check));
                new AlarmHATT(getActivity().getApplicationContext()).Alarm();
            }
        }
    }

    public void mOnPopupClick(){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(getActivity().getApplicationContext(), lensPopActivity.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);
        //startActivity(intent);
    }



    ImageView.OnClickListener m = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Log.i("MyLog", "눈 버튼이 눌림");
           // Toast.makeText(getActivity().getApplication(), "버튼눌림", Toast.LENGTH_SHORT);

/*  MyAlertDialogFragment newDialogFragment =
                    MyAlertDialogFragment.newInstance("LENSEYE");
            newDialogFragment.show(getFragmentManager(), "dialog");*/

            mOnPopupClick();
            //intent.getExtras().getInt("itpangpang");
          /*  if(check) {
                new AlarmHATT(getActivity().getApplicationContext()).Alarm();
            }*/



            String[] showtime;

            //textView.append("ehla");


            //testStart();
            ///lens_time_when.setText(sdate[3]+"분");

        }
    };

    public void testStart() {
       // timer_text = (TextView)findViewById(R.id.lens_time_when);
        second = new TimerTask() {
            int timer_sec = 0;
            int count = 0;
            boolean mIsRun = true;

            @Override
            public void run() {
                Log.i("Test", "Timer start");

                // timer_sec 체크

                if(mIsRun) {
                    Update();
                    timer_sec++;
                }else{
                    // 정리코드

                    lens_time_when.setText("렌즈를 착용하고 있지 않습니다.");
                }
            }
        };
       Timer timer = new Timer();timer.schedule(second, 0, 1000);
    }
    Runnable updater;
    public void Update() {
       updater = new Runnable() {
            public void run() {
                now = System.currentTimeMillis();
                date = new Date(now);
                SimpleDateFormat sdfnow = new SimpleDateFormat("mm");
                String strNow = sdfnow.format(date);
                int snow = Integer.parseInt(strNow);
                Integer t=(snow-sdate[3])+1;
                String ttext=t.toString();

                lens_time_when.setText(ttext+ "분");
            }
        };
        handler.post(updater);

    }


}

