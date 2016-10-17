package phone.demo.com.demo.delegate;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import phone.demo.com.demo.R;
import phone.demo.com.library.view.AppDelegate;

/**
 * @author cyc
 * @name phone.demo.com.demo.delegate
 * @description
 * @date 2016/10/17 0017
 */
public class ImageListDelegate extends AppDelegate {

    private RecyclerView recyclerView;

    public ImageListDelegate(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_image_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView = get(R.id.feed_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
