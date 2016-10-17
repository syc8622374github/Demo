package phone.demo.com.library.net.response;

/**
 * @author cyc
 * @name phone.demo.com.library.net.response
 * @description
 * @date 2016/10/17 0017
 */
public interface CommonResponse<T> {

    /**
     * restful valid data [option]
     */
    T getResult();

    /**
     * Designation type of valid data [option]
     */
    void setResult(T t);

    /**
     * check data is it effective [must]
     */
    boolean isValid();
}
