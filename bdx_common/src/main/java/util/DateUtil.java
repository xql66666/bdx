package util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 磊大大
 * @date: 2019/10/31 10:36
 */
public class DateUtil {

    /**
     * 2019-10-06T16:00:00.000Z 转Date
     * @param time
     * @return
     */
    public Date formatDate(String time) {
        if (!"".equals(time) && time != null) {
            time = time.replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            try {
                Date date = format.parse(time);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
