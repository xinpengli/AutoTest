package com.taishite.autotest.controller;

public class SuanFa {

    public static void main(String[] args) {
        int temp =0 ;
        int [] data =new int []{1,4,2,7,10,44};
        int [] data2 ={1,2};

        for (int i=0;i<data.length-1;i++){
            for (int j=0;j<data.length-1-i;j++){
                if (data[j]>data[j+1]){
                    temp=data[j+1];
                    data[j+1]=data[j];
                    data[j]=temp;

                }
            }

        }
    }
}
