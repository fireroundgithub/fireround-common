package cn.fireround.common;

import java.util.Iterator;
import java.util.List;

public class StringUtils {
    
    public static boolean isEmpty(String string) {
        return string != null && string.length() > 0;
    }

    public static String join(final Iterator<?> iterator, char separator) {
        // two or more elements
        final StringBuilder buf = new StringBuilder(256);
        while (iterator.hasNext()) {
            buf.append(separator);
            final Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }

        return buf.toString();
    }

}
