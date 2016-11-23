package phone.demo.com.demo.module.Cartoon;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import phone.demo.com.demo.R;
import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/11/23 0023.
 */

public class CartoonListDelegate extends AppDelegate {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    protected CartoonListDelegate(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_image_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.fragment_images_list_swipe_layout);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
    }

    @Override
    public void initData() {
        super.initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
    }
}
