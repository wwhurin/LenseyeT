package kr.hs.emirim.wwhurin.lenseye5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class SecondFragment extends Fragment implements View.OnClickListener
{
    public SecondFragment()
    {
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    Button btn ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_second, container, false);
        /*btn = (Button)getActivity().findViewById(R.id.colorbtn);
        btn.setOnClickListener(this);*/
        ((Button)layout.findViewById(R.id.colorbtn)).setOnClickListener(this);
        ((Button) layout.findViewById(R.id.noseebtn)).setOnClickListener(this);
        ((Button) layout.findViewById(R.id.onedaybtn)).setOnClickListener(this);
        ((Button) layout.findViewById(R.id.monthbtn)).setOnClickListener(this);
        ((Button) layout.findViewById(R.id.ALL)).setOnClickListener(this);
        ((Button) layout.findViewById(R.id.twoweekbtn)).setOnClickListener(this);

        return layout;
    }




    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.colorbtn:
                intent = new Intent(getActivity().getApplication(), ColorActivity.class);
                startActivity(intent);
                break;
            case R.id.noseebtn:
                intent = new Intent(getActivity().getApplication(), NoseeActivity.class);
                startActivity(intent);
                break;
            case R.id.onedaybtn:
                intent = new Intent(getActivity().getApplication(), OnedayActivity.class);
                startActivity(intent);
                break;
            case R.id.twoweekbtn:
                intent = new Intent(getActivity().getApplication(), TwoweekActivity.class);
                startActivity(intent);
                break;
            case R.id.monthbtn:
                intent = new Intent(getActivity().getApplication(), MonthActivity.class);
                startActivity(intent);
                break;
            case R.id.ALL:
                intent = new Intent(getActivity().getApplication(), AllActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}


