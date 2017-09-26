package kr.hs.emirim.wwhurin.lenseye5;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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

    TextView dateNow;


    int sdate[]=new int[4];



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

            now = System.currentTimeMillis();
            date = new Date(now);
            sdfnow = new SimpleDateFormat("MM-dd-HH-mm");
            formatDate = sdfnow.format(date);

            AlarmManager am=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getActivity().getApplicationContext(), BroadcastD.class);

            PendingIntent sender = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, intent, 0);

            Calendar calendar=Calendar.getInstance();//알람시간 calendar에 set

           // calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), sdate[2]+1, sdate[3], calendar.get(Calendar.SECOND));
           // calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR, 1), sdate[3], calendar.get(Calendar.SECOND));
          //  calendar.set(calendar.HOUR, +1);
            calendar.set(calendar.MINUTE, 2+ sdate[3]);
            //알람 예약
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);

        }
    }

    ImageView.OnClickListener m = new View.OnClickListener(){
        @Override
        public void onClick(View view) {

            MyAlertDialogFragment newDialogFragment =
                    MyAlertDialogFragment.newInstance("LENSEYE");
            newDialogFragment.show(getFragmentManager(), "dialog");


            new AlarmHATT(getActivity().getApplicationContext()).Alarm();



            String[] showtime;

            //textView.append("ehla");

            String[] startTime = formatDate.split("-");

            for (int i = 0; i < 4; i++) {
                sdate[i] = parseInt(startTime[i]);
            }

            testStart();
            ///lens_time_when.setText(sdate[3]+"분");

        }
    };

    public void testStart() {
       // timer_text = (TextView)findViewById(R.id.lens_time_when);
        second = new TimerTask() {
            int timer_sec = 0;
            int count = 0;

            @Override
            public void run() {
                Log.i("Test", "Timer start");
                Update();
                timer_sec++;
            }
        };
       Timer timer = new Timer();timer.schedule(second, 0, 1000);
    }

    protected void Update() {
        Runnable updater = new Runnable() {
            public void run() {
                now = System.currentTimeMillis();
                date = new Date(now);
                SimpleDateFormat sdfnow = new SimpleDateFormat("mm");
                String strNow = sdfnow.format(date);
                int snow = Integer.parseInt(strNow);
                Integer t=(snow-sdate[3]);
                String ttext=t.toString();

                lens_time_when.setText(ttext+ "분");
            }
        };
        handler.post(updater);
    }


}

