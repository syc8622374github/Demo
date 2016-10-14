package phone.demo.com.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import phone.demo.com.demo.R;

/**
 * @author 80986
 * @name phone.demo.com.demo.fragment
 * @description
 * @date 2016/10/14 0014
 */
public class TabFragment extends Fragment {

    private View view;

    public static TabFragment newInstance(String data) {

        Bundle args = new Bundle();
        args.putString("title",data);

        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.base_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((TextView)view.findViewById(R.id.textView)).setText(getArguments().getString("title"));
    }
}
