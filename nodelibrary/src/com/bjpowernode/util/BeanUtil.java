package com.bjpowernode.util;

import java.lang.reflect.Field;

public class BeanUtil {
    /**
     * 对象属性值的拷贝
     *
     * @param origin
     * @param dest
     */
    public static void populate(Object origin, Object dest) {
        try {
            //使用反射解决这个问题，传递的数目不同
            //判断两个对象是否是同一类型
            if (origin.getClass() != dest.getClass()) {
                throw new RuntimeException("两个对象必须是同一类型");
            }

            Class<?> clazz = origin.getClass();
            //获取origin中的属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if("serialVersionUID".equals(f.getName())){
                    continue;
                }
                //打破封装
                f.setAccessible(true);
                //从dest对象中找到对应的值赋值到origin中
                f.set(origin, f.get(dest));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
