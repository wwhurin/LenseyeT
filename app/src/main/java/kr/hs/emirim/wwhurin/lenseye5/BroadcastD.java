package kr.hs.emirim.wwhurin.lenseye5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

/**
 * Created by LG on 2017-09-15.
 */

public class BroadcastD extends BroadcastReceiver {
    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;
    BroadcastD(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {//알람 시간이 되면 onReceive 호출
        // NotificationManager 안드로이드 상태바에 메세지를 던지기위한 서비스 불러오고

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, FirstFragment.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.eye).setTicker("렌즈를 빼주세요!").setWhen(System.currentTimeMillis())
                .setNumber(1).setContentTitle("LENSEYE").setContentText("렌즈를 빼주세요!")
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent).setAutoCancel(true);

        notificationManager.notify(1, builder.build());

        //팝업창
        AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(context);

        //제목세팅
        alerDialogBuilder.setTitle("LensEYE");

        //AlerDialog 세팅
        alerDialogBuilder.setMessage("렌즈 권장 착용 시간이 지났어요! \n 서둘러 빼주세요! ");

    }
}
