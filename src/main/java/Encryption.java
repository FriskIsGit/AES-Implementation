import java.util.*;

/**
 * #1 Initial addRoundKey;
 * #2 Main rounds:
 *      - subBytes (S_BOX)
 *      - shiftRows
 *      - addRoundKey
 *      - mixColumns (Rijndael's Galois Field) matrix multiplication
 *      - addRoundKey
 * #3 Final round
 *      - subBytes
 *      - shiftRows
 *      - addRoundKey
 */
class Encryption{
    final static String[][] HEX_S_BOX = new String[][]{
            {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "1", "67", "2b", "fe", "d7", "ab", "76" },
            {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0" },
            {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15" },
            {"4",  "c7", "23", "c3", "18", "96",  "5", "9a",  "7", "12", "80", "e2", "eb", "27", "b2", "75" },
            {"9", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84" },
            {"53", "d1", "0", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf" },
            {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "2", "7f", "50", "3c", "9f", "a8" },
            {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2" },
            {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
            {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
            {"e0", "32", "3a", "0a", "49", "6", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
            {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
            {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
            {"70", "3e", "b5", "66", "48", "3", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
            {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
            {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}
    };
    final static int[][] INT_S_BOX_TWO_DIM = new int[][]{
            {0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30,  0x1, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
            {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
            {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
            {0x4,  0xc7, 0x23, 0xc3, 0x18, 0x96, 0x5,  0x9a,  0x7, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
            {0x9,  0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
            {0x53, 0xd1,  0x0, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
            {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9,  0x2, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
            {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
            {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
            {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
            {0xe0, 0x32, 0x3a, 0x0a, 0x49,  0x6, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
            {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
            {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
            {0x70, 0x3e, 0xb5, 0x66, 0x48,  0x3, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
            {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
            {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16},
    };
    final static int[] INT_S_BOX = new int[]{
            0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30,  0x1, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
            0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
            0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
            0x4,  0xc7, 0x23, 0xc3, 0x18, 0x96, 0x5,  0x9a,  0x7, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
            0x9,  0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
            0x53, 0xd1,  0x0, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
            0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9,  0x2, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
            0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
            0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
            0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
            0xe0, 0x32, 0x3a, 0x0a, 0x49,  0x6, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
            0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
            0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
            0x70, 0x3e, 0xb5, 0x66, 0x48,  0x3, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
            0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
            0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16,
    };

    final static byte[] BYTE_S_BOX = new byte[]{
            99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118,
            -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64,
            -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21,
            4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117,
            9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124,
            83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49,
            -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88,
            81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46,
            -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115,
            96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37,
            -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121,
            -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8,
            -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118,
            112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98,
            -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33,
            -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    final static byte[][] BYTE_S_BOX_TWO_DIM = new byte[][]{
            {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118},
            {-54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64},
            {-73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21},
            {4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117},
            {9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124},
            {83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49},
            {-48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88},
            {81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46},
            {-51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115},
            {96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37},
            {-32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121},
            {-25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8},
            {-70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118},
            {112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98},
            {-31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33},
            {-116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22}};
    final static int[] RCON_256 = new int[]{
            0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a,
            0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39,
            0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a,
            0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8,
            0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef,
            0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc,
            0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b,
            0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3,
            0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94,
            0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20,
            0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35,
            0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f,
            0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04,
            0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63,
            0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd,
            0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d};
    final static int[] RCON_10 = new int[]{
            0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    final static int[] GALOIS = new int[]{2,3,1,1, 1,2,3,1, 1,1,2,3, 3,1,1,2};
    final static int BIT_128 = 128;
    final static int ROUNDS = 10;
    protected List<byte[]> ciphersList = new LinkedList<>();
    static final int[] gfp2 = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100, 102, 104, 106, 108, 110, 112, 114, 116, 118, 120, 122, 124, 126, 128, 130, 132, 134, 136, 138, 140, 142, 144, 146, 148, 150, 152, 154, 156, 158, 160, 162, 164, 166, 168, 170, 172, 174, 176, 178, 180, 182, 184, 186, 188, 190, 192, 194, 196, 198, 200, 202, 204, 206, 208, 210, 212, 214, 216, 218, 220, 222, 224, 226, 228, 230, 232, 234, 236, 238, 240, 242, 244, 246, 248, 250, 252, 254, 27, 25, 31, 29, 19, 17, 23, 21, 11, 9, 15, 13, 3, 1, 7, 5, 59, 57, 63, 61, 51, 49, 55, 53, 43, 41, 47, 45, 35, 33, 39, 37, 91, 89, 95, 93, 83, 81, 87, 85, 75, 73, 79, 77, 67, 65, 71, 69, 123, 121, 127, 125, 115, 113, 119, 117, 107, 105, 111, 109, 99, 97, 103, 101, 155, 153, 159, 157, 147, 145, 151, 149, 139, 137, 143, 141, 131, 129, 135, 133, 187, 185, 191, 189, 179, 177, 183, 181, 171, 169, 175, 173, 163, 161, 167, 165, 219, 217, 223, 221, 211, 209, 215, 213, 203, 201, 207, 205, 195, 193, 199, 197, 251, 249, 255, 253, 243, 241, 247, 245, 235, 233, 239, 237, 227, 225, 231, 229};
    static final int[] gfp3 = {0, 3, 6, 5, 12, 15, 10, 9, 24, 27, 30, 29, 20, 23, 18, 17, 48, 51, 54, 53, 60, 63, 58, 57, 40, 43, 46, 45, 36, 39, 34, 33, 96, 99, 102, 101, 108, 111, 106, 105, 120, 123, 126, 125, 116, 119, 114, 113, 80, 83, 86, 85, 92, 95, 90, 89, 72, 75, 78, 77, 68, 71, 66, 65, 192, 195, 198, 197, 204, 207, 202, 201, 216, 219, 222, 221, 212, 215, 210, 209, 240, 243, 246, 245, 252, 255, 250, 249, 232, 235, 238, 237, 228, 231, 226, 225, 160, 163, 166, 165, 172, 175, 170, 169, 184, 187, 190, 189, 180, 183, 178, 177, 144, 147, 150, 149, 156, 159, 154, 153, 136, 139, 142, 141, 132, 135, 130, 129, 155, 152, 157, 158, 151, 148, 145, 146, 131, 128, 133, 134, 143, 140, 137, 138, 171, 168, 173, 174, 167, 164, 161, 162, 179, 176, 181, 182, 191, 188, 185, 186, 251, 248, 253, 254, 247, 244, 241, 242, 227, 224, 229, 230, 239, 236, 233, 234, 203, 200, 205, 206, 199, 196, 193, 194, 211, 208, 213, 214, 223, 220, 217, 218, 91, 88, 93, 94, 87, 84, 81, 82, 67, 64, 69, 70, 79, 76, 73, 74, 107, 104, 109, 110, 103, 100, 97, 98, 115, 112, 117, 118, 127, 124, 121, 122, 59, 56, 61, 62, 55, 52, 49, 50, 35, 32, 37, 38, 47, 44, 41, 42, 11, 8, 13, 14, 7, 4, 1, 2, 19, 16, 21, 22, 31, 28, 25, 26};

    protected static void xorWithRcon(byte[] arr, final int rconCol, final int col1, final int col2){
        int diff = col2-col1;
        for(int i = col1, ri=rconCol; i+diff<BIT_128; i+=32, ri+=10){
            arr[i+diff] = (byte) (arr[i]^arr[i+diff]^RCON_10[ri]);
        }
    }

    protected static void xorWithRcon(byte[] initialState, byte[] nextState, int rconInd){
        for(int i = 0; i<4; i++, rconInd+=10){
            nextState[4*i] = (byte) (initialState[4*i]^nextState[4*i]^RCON_10[rconInd]);
        }
    }

    protected static void xor(byte[] arr, int col1, int col2, int targetCol){
        for(;targetCol<BIT_128; targetCol+=32,col1+=32, col2+=32){
            arr[targetCol] = (byte) (arr[col1]^arr[col2]);
        }
    }
    protected static void xor(byte[] arrL, byte[] arrR, int targetCol){
        for(int i = 0; i<4; i++){
            arrR[4*i + targetCol] = (byte)(arrL[4*i + targetCol]^arrR[4*i + targetCol-1]);
        }
    }

    protected static void fullKeySchedule(byte[] arr){
        for(int block = 0; block < 7; block++){
            final int leadingCol = 4*(block+1);
            rotateVertically(arr,leadingCol-1,leadingCol);
            subBytes(arr, leadingCol);
            xorWithRcon(arr, block, leadingCol-4, leadingCol);
            for(int i = leadingCol+1; i<leadingCol+4; i++){
                xor(arr,i-4,i-1, i);
            }
        }
    }
    protected static byte[] keySchedule(byte[] arr, final int RCON_COL){
        byte[] resultArr = new byte[16];
        arr = ensureSize(arr, 16);
        byte[] columnArr = new byte[]{arr[3], arr[7], arr[11], arr[15]};
        rotateVertically(columnArr);
        subBytes(columnArr);
        for(int i=0; i<4;i++){
            resultArr[4*i] = columnArr[i];
        }
        xorWithRcon(arr,resultArr,RCON_COL);
        for(int remainingCols = 1; remainingCols<4; remainingCols++){
            xor(arr, resultArr, remainingCols);
        }
        return resultArr;
    }

    protected static byte[] ensureSize(byte[] arr, int minSize){
        byte[] newArr = new byte[16];
        if(arr.length >= minSize){
            return arr;
        }
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    //128bit?
    protected static void rotateVertically(byte[] state, final int accordingTo, final int column){
        //4 rows, 32 columns, in total: 128 elements, single dimensional arr
        if(state.length<BIT_128 || column>31) return;
        state[column] = state[accordingTo+32];
        state[column+32] = state[accordingTo+64];
        state[column+64] = state[accordingTo+96];
        state[column+96] = state[accordingTo];
    }
    protected static void rotateVertically(byte[] columnArr){
        if(columnArr.length!=4) return;
        byte temp = columnArr[0];
        columnArr[0] = columnArr[1];
        columnArr[1] = columnArr[2];
        columnArr[2] = columnArr[3];
        columnArr[3] = temp;
    }


    //block cipher      1) append a byte with value 128 (hex 80), followed by as many zero bytes as needed to fill the last block
    //padding concepts  2) pad the last block with n bytes all with value n.
    protected static byte[] padding(byte[] arr){
        byte[] paddedArr = new byte[BIT_128];
        for(int i = 0; i<arr.length; i++){
            if(arr[i]==0){
                System.arraycopy(arr,0,paddedArr,0,i);
                Arrays.fill(paddedArr,i,paddedArr.length, (byte) 0);
            }
        }
        return paddedArr;
    }

    protected static void subBytesTwoDimensional(byte[] state){
        for(int i = 0;i<state.length; i++){
            int[] indexes = Convert.unsignedByteToIndices(Convert.byteToUnsigned(state[i]));
            state[i] = BYTE_S_BOX_TWO_DIM[indexes[0]][indexes[1]];
        }
    }
    protected static void subBytesTwoDimensional(byte[] arr, int column){
        for(int i = column;i<arr.length; i+=32){
            int[] indexes = Convert.unsignedByteToIndices(Convert.byteToUnsigned(arr[i]));
            arr[i] = BYTE_S_BOX_TWO_DIM[indexes[0]][indexes[1]];
        }
    }
    protected static void subBytes(byte[] state){
        for(int i = 0;i<state.length; i++){
            state[i] = BYTE_S_BOX[Byte.toUnsignedInt(state[i])];
        }
    }
    protected static void subBytes(byte[] arr, int column){
        for(int i = column;i<arr.length; i+=32){
            arr[i] = BYTE_S_BOX[Byte.toUnsignedInt(arr[i])];
        }
    }

    //XOR values at respective indices, pass by reference
    protected static void addRoundKey(byte[] state, byte[] roundKey){
        for(int i = 0; i<state.length; i++){
            state[i] = (byte) (state[i]^roundKey[i]);
        }
    }

    //traverses galois - horizontally, arr - vertically
    protected static byte[] mixColumns(byte[] arr){
        byte[] computedArr = new byte[arr.length];
        for(int col = 0; col<4; col++){
            //galois field is traversed in total 4 times
            int galoisCell = 0;
            for(int row = 0; row<4; row++){
                boolean firstEntry = true;
                byte singleCellResult = -1;
                for(int i = col; i<16; i+=4, galoisCell++){
                    if(firstEntry){
                        singleCellResult = multiplyByLookup(arr[i], GALOIS[galoisCell]);
                        firstEntry = false;
                    }
                    else{
                        singleCellResult ^= multiplyByLookup(arr[i],GALOIS[galoisCell]);
                    }
                }
                computedArr[row*4 + col] = singleCellResult;
            }
        }
        return computedArr;
    }

    private static byte multiplyByLookup(byte b, int times){
        switch (times){
            case 1:
                return b;
            case 2:
                return (byte) gfp2[Byte.toUnsignedInt(b)];
            case 3:
                return (byte) gfp3[Byte.toUnsignedInt(b)];
        }
        return -1;
    }

    //performs hex multiplication (MixColumns) while ensuring bits don't overflow
    protected static byte multiply(byte myByte, final int times){
        if(times>1){
            //0x1b corresponds to the irreducible polynomial with the high term eliminated
            byte special=(byte)0x1b;
            byte original = myByte;
            myByte<<=1;
            //if overflows (unsigned <= 2^7)
            if(original<0){
                myByte^=special;
            }
            //multiplying by 3 has one additional step
            if(times==3){
                myByte^=original;
            }
        }
        return myByte;
    }
    public static byte bt(int x){ return (byte)x; }

    protected static void shiftRows(byte[] state){
        for(int shifts = 1; shifts<4;shifts++){
            shiftLeft(state,shifts,shifts);
        }
    }
    private static void shiftLeft(byte[] arr, int shifts, int row){
        row*=4;
        byte temp;
        switch (shifts){
            case 1:
                temp = arr[row];
                arr[row] = arr[row+1];
                arr[row+1] = arr[row+2];
                arr[row+2] = arr[row+3];
                arr[row+3] = temp;
                break;
            case 2:
                temp = arr[row];
                arr[row] = arr[row+2];
                arr[row+2] = temp;
                temp = arr[row+1];
                arr[row+1] = arr[row+3];
                arr[row+3] = temp;
                break;
            case 3:
                //actually one shift to the right
                temp = arr[row+3];
                arr[row+3] = arr[row+2];
                arr[row+2] = arr[row+1];
                arr[row+1] = arr[row];
                arr[row] = temp;
                break;
        }
    }

    protected static byte[] encryptData(byte[] state, byte[] roundKey){
        state = ensureSize(state, 16);
        roundKey = ensureSize(roundKey, 16);
        addRoundKey(state, roundKey);
        for(int round = 1; round<ROUNDS; round++){
            subBytes(state);
            shiftRows(state);
            state = mixColumns(state);
            roundKey = keySchedule(roundKey,round-1);
            addRoundKey(state,roundKey);
        }
        subBytes(state);
        shiftRows(state);
        roundKey = keySchedule(roundKey,ROUNDS-1);
        addRoundKey(state,roundKey);
        return state;
    }

}
//256^16 = (2^8)^16 = 2^128
//256^24 = (2^8)^24 = 2^192
//256^32 = (2^8)^32 = 2^256