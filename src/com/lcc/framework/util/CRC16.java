package com.lcc.framework.util;

/**
 * Created by lcc on 2016/11/29.
 */
public class CRC16 {

    public static void main(String[] args) {
        byte[] b = CRC16.crc16(new byte[]{(byte)0x01, (byte)0x02, (byte)0x03, (byte)0x04, (byte)0x05, (byte)0x06});
        System.out.println(DataTypeUtil.bytes2Hexs(b));
    }

    /**
     * 计算CRC-16
     */
    public static byte[] crc16(byte[] data) {
        byte[] temdata = new byte[2];
        int crc = 0;
        int ii = 8;
        for (int i = 0; i < data.length; i++) {
            crc = crc ^ data[i] << 8;
            ii = 8;
            do{
                if((crc & 0x8000) != 0){
                    crc = crc << 1^0x1021;
                } else {
                    crc = crc <<1;
                }
            }while(--ii != 0);
        }
        temdata[1] = (byte) (crc & 0xFF);
        temdata[0] = (byte) (crc >> 8);
        return temdata;
    }
}
