package com.library.util;

import java.util.Collection;

public class CollectionUtils {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean notEmpty(Collection collection) {
        return !isEmpty(collection);
    }

}
