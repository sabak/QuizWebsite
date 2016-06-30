package ge.freeuni.quizwebsite.util;

import java.util.Map;
import java.util.Objects;

/**
 * Created by Saba on 30-06-2016.
 */
public final class MapUtils {

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
