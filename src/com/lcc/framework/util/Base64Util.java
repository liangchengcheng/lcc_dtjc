package com.lcc.framework.util;

/**
 * Created by lcc on 2016/11/29.
 */
public class Base64Util {

    private final static int BASELENGTH = 255;

    private final static int EIGHTBIT = 8;

    private final static int FOURBYTE = 4;

    private final static int LOOKUPLENGTH = 64;

    private final static byte PAD = (byte) '=';

    private final static int SIGN = -128;

    private final static int SIXBIT = 6;

    private final static int SIXTEENBIT = 16;

    private final static int TWENTYFOURBITGROUP = 24;

    private static byte[] base64Alphabet = new byte[BASELENGTH];

    private static byte[] lookUpBase64Alphabet = new byte[LOOKUPLENGTH];

    /**
     * Gets the ArrayByteBase64 attribute of the Base64Util class
     *
     * @param arrayOctect
     *            Description of Parameter
     * @return The ArrayByteBase64 value
     */
    public static boolean isArrayByteBase64(byte[] arrayOctect) {
        int length = arrayOctect.length;
        if (length == 0) {
            // shouldn't a 0 length array be valid base64 data?
            // return false;
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!Base64Util.isBase64(arrayOctect[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the Base64 attribute of the Base64Util class
     *
     * @param isValidString
     *            Description of Parameter
     * @return The Base64 value
     */
    public static boolean isBase64(String isValidString) {
        return isArrayByteBase64(isValidString.getBytes());
    }

    /**
     * Gets the Base64 attribute of the Base64Util class
     *
     * @param octect
     *            Description of Parameter
     * @return The Base64 value
     */
    public static boolean isBase64(byte octect) {
        // shall we ignore white space? JEFF??
        return (octect == PAD || base64Alphabet[octect] != -1);
    }

    /**
     * Description of the Method
     *
     * @param str
     *            Description of Parameter
     * @return Description of the Returned Value
     * @throws Exception
     */
    public static byte[] Base64ToBytes(String str) throws RuntimeException {
        try {

            byte[] b1 = str.getBytes();
            return decode(b1);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    /**
     * Description of the Method
     *
     * @param bts
     *            Description of Parameter
     * @return Description of the Returned Value
     * @throws Exception
     */
    public static String BytesToBase64(byte[] bts) throws RuntimeException {
        try {
            byte[] b1 = encode(bts);
            return new String(b1);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    /**
     * Decodes Base64 data into octects
     *
     * @param base64Data
     *            Description of Parameter
     * @return Array containing decoded data.
     */
    public static byte[] decode(byte[] base64Data) {
        // handle the edge case, so we don't have to worry about it later
        if (base64Data.length == 0) {
            return new byte[0];
        }

        int numberQuadruple = base64Data.length / FOURBYTE;
        byte decodedData[] = null;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        byte marker0 = 0;
        byte marker1 = 0;

        // Throw away anything not in base64Data

        int encodedIndex = 0;
        int dataIndex = 0;
        {
            // this sizes the output array properly - rlw
            int lastData = base64Data.length;
            // ignore the '=' padding
            while (base64Data[lastData - 1] == PAD) {
                if (--lastData == 0) {
                    return new byte[0];
                }
            }
            decodedData = new byte[lastData - numberQuadruple];
        }

        for (int i = 0; i < numberQuadruple; i++) {
            dataIndex = i * 4;
            marker0 = base64Data[dataIndex + 2];
            marker1 = base64Data[dataIndex + 3];

            b1 = base64Alphabet[base64Data[dataIndex]];
            b2 = base64Alphabet[base64Data[dataIndex + 1]];

            if (marker0 != PAD && marker1 != PAD) {
                // No PAD e.g 3cQl
                b3 = base64Alphabet[marker0];
                b4 = base64Alphabet[marker1];

                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
                decodedData[encodedIndex + 2] = (byte) (b3 << 6 | b4);
            } else if (marker0 == PAD) {
                // Two PAD e.g. 3c[Pad][Pad]
                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
            } else if (marker1 == PAD) {
                // One PAD e.g. 3cQ[Pad]
                b3 = base64Alphabet[marker0];

                decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
                decodedData[encodedIndex + 1] = (byte) (((b2 & 0xf) << 4) | ((b3 >> 2) & 0xf));
            }
            encodedIndex += 3;
        }
        return decodedData;
    }

    /**
     * Encodes hex octects into Base64.
     *
     * @param binaryData
     *            Array containing binary data to encode.
     * @return Base64-encoded data.
     */
    public static byte[] encode(byte[] binaryData) {
        int lengthDataBits = binaryData.length * EIGHTBIT;
        int fewerThan24bits = lengthDataBits % TWENTYFOURBITGROUP;
        int numberTriplets = lengthDataBits / TWENTYFOURBITGROUP;
        byte encodedData[] = null;

        if (fewerThan24bits != 0) {
            // data not divisible by 24 bit
            encodedData = new byte[(numberTriplets + 1) * 4];
        } else {
            // 16 or 8 bit
            encodedData = new byte[numberTriplets * 4];
        }

        byte k = 0;

        byte l = 0;

        byte b1 = 0;

        byte b2 = 0;

        byte b3 = 0;

        int encodedIndex = 0;
        int dataIndex = 0;
        int i = 0;
        for (i = 0; i < numberTriplets; i++) {
            dataIndex = i * 3;
            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            b3 = binaryData[dataIndex + 2];

            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);

            encodedIndex = i * 4;
            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);
            byte val3 = ((b3 & SIGN) == 0) ? (byte) (b3 >> 6) : (byte) ((b3) >> 6 ^ 0xfc);

            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[(l << 2) | val3];
            encodedData[encodedIndex + 3] = lookUpBase64Alphabet[b3 & 0x3f];
        }

        // form integral number of 6-bit groups
        dataIndex = i * 3;
        encodedIndex = i * 4;
        if (fewerThan24bits == EIGHTBIT) {
            b1 = binaryData[dataIndex];
            k = (byte) (b1 & 0x03);
            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[k << 4];
            encodedData[encodedIndex + 2] = PAD;
            encodedData[encodedIndex + 3] = PAD;
        } else if (fewerThan24bits == SIXTEENBIT) {

            b1 = binaryData[dataIndex];
            b2 = binaryData[dataIndex + 1];
            l = (byte) (b2 & 0x0f);
            k = (byte) (b1 & 0x03);

            byte val1 = ((b1 & SIGN) == 0) ? (byte) (b1 >> 2) : (byte) ((b1) >> 2 ^ 0xc0);
            byte val2 = ((b2 & SIGN) == 0) ? (byte) (b2 >> 4) : (byte) ((b2) >> 4 ^ 0xf0);

            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[val2 | (k << 4)];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l << 2];
            encodedData[encodedIndex + 3] = PAD;
        }

        return encodedData;
    }

    /**
     * The main program for the Base64Util class
     *
     * @param args
     *            The command line arguments
     * @exception Exception
     *                Description of the Exception
     */
    public static void main(String[] args) throws Exception {
//        String p = "110108123456788";
//        byte[] a1 = { -65, -111, -29 };
//        a1 = p.getBytes();
//        System.err.println("a1=" + zght.pub.util.DataTypeUtil.bytes2Hexs(a1));
//        // zxt.pub.util.LogUtil.getLogger().debug(a1);
//        String s = BytesToBase64(a1);
//        System.err.println("base64 string=" + s);
//        byte[] b1 = Base64ToBytes(s);
//        System.err.println("b1=" + zght.pub.util.DataTypeUtil.bytes2Hexs(b1));
//        System.err.println(new String(b1));
        System.out.println(Base64Util.BytesToBase64(ManageFile.loadFromFile("D:/erweima.jpg")));
    }

    static {
        for (int i = 0; i < BASELENGTH; i++) {
            base64Alphabet[i] = -1;
        }
        for (int i = 'Z'; i >= 'A'; i--) {
            base64Alphabet[i] = (byte) (i - 'A');
        }
        for (int i = 'z'; i >= 'a'; i--) {
            base64Alphabet[i] = (byte) (i - 'a' + 26);
        }
        for (int i = '9'; i >= '0'; i--) {
            base64Alphabet[i] = (byte) (i - '0' + 52);
        }

        base64Alphabet['+'] = 62;
        base64Alphabet['/'] = 63;

        for (int i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = (byte) ('A' + i);
        }

        for (int i = 26, j = 0; i <= 51; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('a' + j);
        }

        for (int i = 52, j = 0; i <= 61; i++, j++) {
            lookUpBase64Alphabet[i] = (byte) ('0' + j);
        }

        lookUpBase64Alphabet[62] = (byte) '+';
        lookUpBase64Alphabet[63] = (byte) '/';
    }
}
