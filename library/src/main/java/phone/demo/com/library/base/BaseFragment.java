package phone.demo.com.library.base;

import phone.demo.com.library.presenter.FragmentPresenter;
import phone.demo.com.library.view.IDelegate;

/**
 * Created by cyc on 2016/10/16.
 */

public abstract class BaseFragment<T extends IDelegate> extends FragmentPresenter {
}
