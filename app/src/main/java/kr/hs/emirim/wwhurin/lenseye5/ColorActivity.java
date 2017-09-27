package kr.hs.emirim.wwhurin.lenseye5;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

public class ColorActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomActionbar();
        setContentView(R.layout.activity_color);
    }

    //액션바
    private void setCustomActionbar() {
        ActionBar actionbar = getSupportActionBar();

        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayShowTitleEnabled(false);

        View mCustomView = LayoutInflater.from(this).inflate(R.layout.activity_actionbar, null);
        actionbar.setCustomView(mCustomView);

        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        actionbar.setElevation(0);

        actionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.topback));
    }

  /*  @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.1:
                break;
            default:
                break;
        }
    }*/
}
