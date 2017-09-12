package kr.hs.emirim.wwhurin.lenseye5;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

public class actionbar extends ActionBarActivity {
    ImageView imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actionbar);

        getSupportActionBar().setTitle("TEST");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(00000000));

//        imageButton = (ImageView) findViewById(R.id.bt_hamber);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MainActivity m = new MainActivity();
//                m.onGo();
//            }
//        });

    }

}
