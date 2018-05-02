package logger.test;

import logger.Utils;

/**
 * Created by Lynnsion on 2018/5/2.
 */

 public class Utils_test{

    public static void main(String[] args) {
        Utils utils  = new Utils();
        utils.convertToUnits(123423213133L);

        System.out.println("result = " +utils.convertToUnits(123423213133L));

        System.out.println("result = " +utils.formatDiffString(123423213133L,12));
    }
 }