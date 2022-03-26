package com.psq.learn.learn;

import java.io.Serializable;
import java.util.Arrays;

/**
 * auth：psq
 * date：2022-03-23 23:45:36
 * 手写简单版ArrayList
 */
public class MyArrayList implements Serializable {

    /**
     * 第一次扩容的容量
     */
    private static final int DEFAULT_CAPACITY  = 10;

    /**
     * 用于初始化空的list
     */
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    /**
     * 实际存储的数据
     * transient防止序列化
     */
    transient Object[] elementData;

    /**
     * 实际list集合大小，从0开始
     */
    private int size;

    public MyArrayList(){
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    public MyArrayList(int length){
        if(length > 0){
            this.elementData = new Object[length];
        }else if(length == 0){
            this.elementData = EMPTY_ELEMENT_DATA;
        }else {
            throw new IllegalArgumentException("非法长度：" + length);
        }
    }

    /**
     * 添加元素
     */
    public boolean add(Object e){
        //确认添加数据后的容量
        ensureCapacity(size + 1);
        //使用下标赋值
        elementData[size++] = e;
        return true;
    }

    /**
     * 计算容量 + 扩容
     */
    private void ensureCapacity(int minCapacity) {
        //如果是初次扩容，则使用默认的容量
        if(elementData == EMPTY_ELEMENT_DATA){
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        //是否需要扩容，需要的最小容量若大于现在的数组长度则要扩容
        if(minCapacity > elementData.length){
            int oldCapacity = elementData.length;
            //新的长度扩容 = 旧容量 + 旧容量/2
            int newCapacity = oldCapacity + (oldCapacity>>1);
            if(minCapacity > newCapacity){
                //若最小所需的扩容量大于新的扩容量，则取最小所需的扩容量
                newCapacity = minCapacity;
            }
            //创建新数组，将旧的数组拷贝到新的数组里面；再将对象指向新的数组
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * 修改对应位置的值，并返回旧的值
     */
    public Object set(int index, Object e){
        rangeCheck(index);
        Object oldValue = elementData[index];
        elementData[index] = e;
        return oldValue;
    }

    /**
     * 根据索引删除
     */
    public Object remove(int index){
        rangeCheck(index);
        Object oldValue = elementData[index];
        //计算要删除的位置后有几个元素
        int numMoved = size - index - 1;
        if(numMoved > 0){
            /**
             * 第一个参数 src    :原数组
             * 第二个参数 srcPos :从原数据的起始位置开始
             * 第三个参数 dest   :目标数组
             * 第四个参数 destPos:目标数组的开始起始位置
             * 第五个参数 length :要copy的数组的长度
             */
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        //将多出的位置置空，使其没有引用对象，才能被垃圾收集器回收；否则可能造成内存泄露
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * 获取对应位置的值
     */
    public Object get(int index){
        rangeCheck(index);
        return elementData[index];
    }

    /**
     * 判断对象所在位置
     */
    public int indexOf(Object e){
        if(null == e){
            for(int i=0; i<size; i++){
                if(null == elementData[i]){
                    return i;
                }
            }
        }else{
            for(int i=0; i < size; i++){
                if(e.equals(elementData[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 获取数组实际大小
     */
    public int size(){
        return this.size;
    }
    /**
     * 校验是否越界
     * @param index
     */
    private void rangeCheck(int index){
        if(index > size || size < 0){
            throw new IndexOutOfBoundsException("越界");
        }
    }
}