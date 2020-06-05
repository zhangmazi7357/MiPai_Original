package com.hym.eshoplib.fragment.shop;

public class Test {
    public static void main(String[] args){
        String s = "http://mpic.liandao.mobi/ASE0JUPsTBkBS1ag-VY5jmPChco=/FuFd5AytBUUenh_5LV8TJvkoF8-u";
        String substring = s.substring(s.indexOf("//")+2);
        String substring1 = substring.substring(substring.indexOf("/")+1);
        System.out.println(substring1);
    }
}
