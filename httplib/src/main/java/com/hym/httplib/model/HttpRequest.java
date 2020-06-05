package com.hym.httplib.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 胡彦明 on 2017/6/29.
 * <p>
 * Description Http请求封装类
 * <p>
 * otherTips
 */

public class HttpRequest<K, V> {
    int mark;//标记类型
    String url;//请求地址
    Map<K, V> params;//请求参数
    String filesKey;//file数组对应key
    File[] files;//文件数组
    String fileName;//单个文件key
    File file;//单个文件

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<K, V> getParams() {
        return params;
    }

    public void setParams(Map<K, V> params) {
        this.params = params;
    }

    public String getFilesKey() {
        return filesKey;
    }

    public void setFilesKey(String filesKey) {
        this.filesKey = filesKey;
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Map<K, V> AddParems(K key, V value) {
        if (params == null) {
            params = new HashMap<K, V>();
        }
        params.put(key, value);
        return params;
    }

}
