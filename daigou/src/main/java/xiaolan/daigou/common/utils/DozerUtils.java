package xiaolan.daigou.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DozerUtils {
	
    public static <T, S> List<T> mapList(final Mapper mapper, List<S> sourceList, Class<T> targetObjectClass) {
        List<T> targetList = new ArrayList<T>();
        for (S s : sourceList) {
            targetList.add(mapper.map(s, targetObjectClass));
        }
        return targetList;
    }
}
