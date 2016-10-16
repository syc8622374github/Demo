package phone.demo.com.demo.delegate;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import phone.demo.com.demo.R;
import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/10/16.
 */
public class TabDelegate extends AppDelegate {

    public TabDelegate(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.base_fragment;
    }

    @Override
    public void initWidget() {
        super.initWidget();
    }

    @Override
    public void initData() {
        super.initData();
        ((TextView)rootView.findViewById(R.id.textView)).setText(fragment.getArguments().getString("title"));
    }
}
