package com.zzg.jreport.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

public class BeanUtils extends PropertyUtils {
	/**
     * Gets the property.
     * 
     * @param bean
     *            the bean
     * @param property
     *            the property
     * @return the property
     * @throws RuntimeException
     *             the runtime exception
     */
    public static Object getProperty(Object bean, String property) throws RuntimeException {
        try {
            return PropertyUtils.getProperty(bean, property);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Clone bean.
     * 
     * @param bean
     *            the bean
     * @return the object
     * @throws RuntimeException
     *             the runtime exception
     */
    public static Object cloneBean(Object bean) throws RuntimeException {
        try {
            return org.apache.commons.beanutils.BeanUtils.cloneBean(bean);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Populate.
     * 
     * @param bean
     *            the bean
     * @param map
     *            the map
     * @throws Exception
     *             the exception
     */
    public static void populate(Object bean, Map map) throws Exception {
        org.apache.commons.beanutils.BeanUtils.populate(bean, map);
    }

    /**
     * Sets the property as null.
     * 
     * @param bean
     *            the bean
     * @param name
     *            the name
     * @throws Exception
     *             the exception
     */
    public static void setPropertyAsNull(Object bean, String name) throws Exception {
        org.apache.commons.beanutils.BeanUtils.setProperty(bean, name, null);
    }

    /**
     * Compare value.
     * 
     * @param oldValue
     *            the old value
     * @param newValue
     *            the new value
     * @return true, if successful
     */
    public static boolean compareValue(Object oldValue, Object newValue) {
        if ((oldValue == null) && (newValue == null))
            return true;
        if ((oldValue == null) && (newValue != null))
            return false;
        if ((oldValue != null) && (newValue == null))
            return false;
        if (oldValue.getClass().isInstance(newValue))
            return oldValue.equals(newValue);
        return newValue.equals(oldValue);
    }

    /**
     * Equals.
     * 
     * @param bean1
     *            the bean1
     * @param bean2
     *            the bean2
     * @return true, if successful
     */
    public static boolean equals(Object bean1, Object bean2) {
        if (bean1 == null && bean2 == null) {
            return true;
        }

        try {
            if (bean1.getClass().equals(bean2.getClass())) {
                if (bean1 == bean2)
                    return true;
                Map map1 = BeanUtils.describe(bean1);
                Map map2 = BeanUtils.describe(bean2);
                return map1.equals(map2);
            }
        } catch (Exception e) {
            /*
             * catch bean1 or bean2 NullPointException catch
             * BeanUtils.describe() exception catch BeanUtils.describe()
             * returned null map exception
             */
            return false;
        }

        return false;
    }

    /**
     * To string.
     * 
     * @param bean
     *            the bean
     * @return the string
     */
    public static String toString(Object bean) {
        return toString(bean, null);
    }


    /**
     * To string.
     * 
     * @param bean
     *            the bean
     * @param travelledList
     *            the travelled list
     * @return the string
     */
    public static String toString(Object bean, List travelledList) {
        if (bean == null) {
            return "null";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<javabean class=\"" + bean.getClass().getName() + "\">" + "\n");

        PropertyDescriptor[] propDescriptors = BeanUtils.getPropertyDescriptors(bean);

        for (int i = 0; i < propDescriptors.length; i++) {
            try {
                String propName = propDescriptors[i].getName();
                Class propClass = propDescriptors[i].getPropertyType();
                Object propValue = BeanUtils.getProperty(bean, propName);
                if (propValue.getClass().isPrimitive()
                        || (propValue.getClass().getName().startsWith("java.lang"))
                        || (propValue.getClass().getName().startsWith("java.util.Date"))) {
                    if (propValue != null) {
                        propValue = propValue.toString();
                    }
                    sb.append("<property name=\"" + propName + "\" class=\"" + propClass.getName()
                            + "\">");
                    sb.append(propValue);
                    sb.append("</property>\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sb.append("</javabean>" + "\n");

        return sb.toString();
    }

    /**
     * Copy properties.
     * 
     * @param desBean
     *            the des bean
     * @param srcBean
     *            the src bean
     * @param propMap
     *            the prop map
     */
    public static void copyProperties(Object desBean, Object srcBean, HashMap propMap) {
        try {
            Object tempBean = BeanUtils.cloneBean(desBean);

            Iterator iterator = propMap.keySet().iterator();
            while (iterator.hasNext()) {
                String srcPropName = (String) iterator.next();
                String desPropName = (String) propMap.get(srcPropName);
                PropertyUtils.setProperty(tempBean, desPropName,
                        PropertyUtils.getProperty(srcBean, srcPropName));
            }

            BeanUtils.copyProperties(desBean, tempBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Populate.
     * 
     * @param beanList
     *            the bean list
     * @param beanClass
     *            the bean class
     * @param propMaps
     *            id = {id1, id2, id3} name = {name1, name2, name3} desc =
     *            {desc1, desc2, desc3} populate 3 instances of beanClass :
     *            beanClass#id, beanClass#name, beanClass#desc
     */
    public static void populate(List beanList, Class beanClass, Map propMaps) {
        try {
            int beanCount = 0;
            Iterator iterator = propMaps.values().iterator();
            while (iterator.hasNext()) {
                Object[] value = (Object[]) iterator.next();
                if (value != null && value.length > beanCount) {
                    beanCount = value.length;
                }
            }

            for (int i = 0; i < beanCount; i++) {
                Map propMap = new HashMap();
                iterator = propMaps.keySet().iterator();
                while (iterator.hasNext()) {
                    Object propName = iterator.next();
                    Object propValue = null;

                    Object[] propValues = (Object[]) propMaps.get(propName);
                    if (propValues != null && (propValues.length - 1) >= i) {
                        propValue = propValues[i];
                    }

                    propMap.put(propName, propValue);
                }

                Object bean = beanClass.newInstance();
                populate(bean, propMap);

                beanList.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Merge collections by properties.
     * 
     * @param collection1
     *            the collection1
     * @param collection2
     *            the collection2
     * @param propNames
     *            the prop names
     * @return the collection
     */
    public static Collection mergeCollectionsByProperties(Collection collection1,
            Collection collection2, String[] propNames) {
        TreeMap objectMap = new TreeMap();

        mergeCollectionsByProperties(objectMap, collection1, propNames);
        mergeCollectionsByProperties(objectMap, collection2, propNames);

        if (objectMap.size() > 0) {
            return new Vector(objectMap.values());
        } else {
            return null;
        }
    }

    /**
     * Merge collections by properties.
     * 
     * @param objectMap
     *            the object map
     * @param collection
     *            the collection
     * @param propNames
     *            the prop names
     */
    private static void mergeCollectionsByProperties(Map objectMap, Collection collection,
            String[] propNames) {
        if (objectMap != null && collection != null) {
            Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                try {
                    Object object = iterator.next();
                    // Object objectId = BeanUtils.getProperty(object, "id");
                    String objectId = "";
                    for (int i = 0; i < propNames.length; i++) {
                        objectId += BeanUtils.getProperty(object, propNames[i]) + "#";
                    }

                    if (objectId != null && !objectMap.containsKey(objectId)) {
                        objectMap.put(objectId, object);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Sets the property.
     * 
     * @param beans
     *            the beans
     * @param propName
     *            the prop name
     * @param propValue
     *            the prop value
     * @throws Exception
     *             the exception
     */
    public static void setProperty(Collection beans, String propName, Object propValue)
    {
        if (beans != null && beans.size() > 0) {
            Iterator iterator = beans.iterator();
            while (iterator.hasNext()) {
                try {
                    setProperty(iterator.next(), propName, propValue);
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Sets the property.
     * 
     * @param bean
     *            the bean
     * @param propName
     *            the prop name
     * @param propValue
     *            the prop value
     */
    public static void setProperty(Object bean, String propName, Object propValue) {
        if (bean != null) {
            try {
                PropertyUtils.setProperty(bean, propName, propValue);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Copy properties.
     * 
     * @param dest
     *            the dest
     * @param src
     *            the src
     */
    public static void copyProperties(Object dest, Object src) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, src);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the property value of every bean in beanCollection, and return them
     * as a List of objects.
     * 
     * @param beanCollection
     *            the bean collection
     * @param name
     *            the name of the property that want to get.
     * @return the property list
     * @author chenke
     */
    public static List getPropertyList(Collection beanCollection, String name) {
        if (CollectionUtils.isEmpty(beanCollection)) {
            return Collections.EMPTY_LIST;
        }
        List result = new ArrayList(beanCollection.size());
        for (Iterator it = beanCollection.iterator(); it.hasNext();) {
            Object obj = it.next();
            Object value = getProperty2(obj, name);
            if(null != value){
            	result.add(value);
            }
        }
        return result;
    }

    /**
     * Gets the property2.
     * 
     * @param bean
     *            the bean
     * @param names
     *            the names
     * @return the property2
     */
    public static Object[] getProperty2(Object bean, String[] names) {
        Object[] values = new Object[names.length];
        for (int i = 0; i < names.length; i++) {
            values[i] = getProperty2(bean, names[i]);
        }
        return values;
    }

    /**
     * Gets the property2.
     * 
     * @param bean
     *            the bean
     * @param name
     *            the name
     * @return the property2
     */
    public static Object getProperty2(Object bean, String name) {
        try {
            return PropertyUtils.getProperty(bean, name);
        } catch (IllegalAccessException e) {
            String errMsg = generateErrMsg(bean, name);
            throw new RuntimeException(errMsg, e);
        } catch (InvocationTargetException e) {
            String errMsg = generateErrMsg(bean, name);
            throw new RuntimeException(errMsg, e);
        } catch (NoSuchMethodException e) {
            String errMsg = generateErrMsg(bean, name);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     * Generate err msg.
     * 
     * @param bean
     *            the bean
     * @param name
     *            the name
     * @return the string
     */
    private static String generateErrMsg(Object bean, String name) {
        StringBuffer result = new StringBuffer();
        result.append("Can not get the value of property <" + name + "> in bean <"
                + bean.getClass().getName() + ">");
        return result.toString();
    }
}
