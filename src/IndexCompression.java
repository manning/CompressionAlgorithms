import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Christopher Manning
 */
public class IndexCompression {

  /**
   * Gap encodes a postings list.  The DocIds in the postings list are provided
   * in the array inputDocIdsOutputGaps.  The output gaps are placed right back
   * into this array, replacing each docId with the corresponding gap.
   *
   * Example:
   * If inputDocIdsOutputGaps is initially {5, 1000, 1005, 1100}
   * then at the end inputDocIdsOutputGaps is set to {5, 995, 5, 95}
   *
   * @param inputDocIdsOutputGaps The array of input docIds.
   *                              The output gaps are placed back into this array!
   */
  public static void gapEncode(int[] inputDocIdsOutputGaps) {
    // TODO: Fill in your code here
  }


  /**
   * Decodes a gap encoded postings list into the corresponding docIds.  The input
   * gaps are provided in inputGapsOutputDocIds.  The output docIds are placed
   * right back into this array, replacing each gap with the corresponding docId.
   *
   * Example:
   * If inputGapsOutputDocIds is initially {5, 905, 5, 95}
   * then at the end inputGapsOutputDocIds is set to {5, 1000, 1005, 1100}
   *
   * @param inputGapsOutputDocIds The array of input gaps.
   *                              The output docIds are placed back into this array.
   */
  public static void gapDecode(int[] inputGapsOutputDocIds) {
    // TODO: Fill in your code here
  }


  /**
   * Encodes gap using a VB code.  The encoded bytes are placed in outputVBCode.
   * Returns the number bytes placed in outputVBCode.
   *
   * @param gap          gap to be encoded.  Assumed to be greater than or equal to 0.
   * @param outputVBCode VB encoded bytes are placed here.  This byte array is assumed to be large
   *                     enough to hold the VB code for gap (e.g., Integer.SIZE/7 + 1).
   * @return Number of bytes placed in outputVBCode.
   */
  public static int VBEncodeInteger(int gap, byte[] outputVBCode) {
    int numBytes = 0;
    // TODO: Fill in your code here
    return numBytes;
  }


  /**
   * Decodes the first integer encoded in inputVBCode starting at index startIndex.  The decoded
   * number is placed in the element zero of the numberEndIndex array and the index position
   * immediately after the encoded value is placed in element one of the numberEndIndex array.
   *
   * @param inputVBCode    Byte array containing the VB encoded number starting at index startIndex.
   * @param startIndex     Index in inputVBCode where the VB encoded number starts
   * @param numberEndIndex Outputs are placed in this array.  The first element is set to the
   *                       decoded number and the second element is set to the index of inputVBCode
   *                       immediately after the end of the VB encoded number.
   * @throws IllegalArgumentException If not a valid variable byte code
   */
  public static void VBDecodeInteger(byte[] inputVBCode, int startIndex, int[] numberEndIndex) {
    // TODO: Fill in your code here
  }


  /**
   * Encodes a number using unary code.  The unary code for the number is placed in the BitSet
   * outputUnaryCode starting at index startIndex.  The method returns the BitSet index that
   * immediately follows the end of the unary encoding.  Use startIndex = 0 to place the unary
   * encoding at the beginning of the outputUnaryCode.
   *
   * Examples:
   * If number = 5, startIndex = 3, then unary code 111110 is placed in outputUnaryCode starting
   * at the 4th bit position and the return value 9.
   *
   * @param number          The number to be unary encoded
   * @param outputUnaryCode The unary code for number is placed into this BitSet
   * @param startIndex      The unary code for number starts at this index position in outputUnaryCode
   * @return The next index position in outputUnaryCode immediately following the unary code for number
   */
  public static int unaryEncodeInteger(int number, BitSet outputUnaryCode, int startIndex) {
    int nextIndex = startIndex;
    // TODO: Fill in your code here
    return nextIndex;
  }


  /**
   * Decodes the unary coded number in BitSet inputUnaryCode starting at (0-based) index startIndex.
   * The decoded number is returned in numberEndIndex[0] and the index position immediately following
   * the encoded value in inputUnaryCode is returned in numberEndIndex[1].
   *
   * @param inputUnaryCode BitSet containing the unary code
   * @param startIndex     Unary code starts at this index position
   * @param numberEndIndex Return values: index 0 holds the decoded number; index 1 holds the index
   *                       position in inputUnaryCode immediately following the unary code.
   */
  public static void unaryDecodeInteger(BitSet inputUnaryCode, int startIndex, int[] numberEndIndex) {
    // TODO: Fill in your code here
  }


  /**
   * Gamma encodes number.  The encoded bits are placed in BitSet outputGammaCode starting at
   * (0-based) index position startIndex.  Returns the index position immediately following the
   * encoded bits.  If you try to gamma encode 0, then the return value should be startIndex (i.e.,
   * it does nothing).
   *
   * @param number          Number to be gamma encoded
   * @param outputGammaCode Gamma encoded bits are placed in this BitSet starting at startIndex
   * @param startIndex      Encoded bits start at this index position in outputGammaCode
   * @return Index position in outputGammaCode immediately following the encoded bits
   */
  public static int gammaEncodeInteger(int number, BitSet outputGammaCode, int startIndex) {
    int nextIndex = startIndex;
    // TODO: Fill in your code here
    return nextIndex;
  }


  /**
   * Decodes the Gamma encoded number in BitSet inputGammaCode starting at (0-based) index startIndex.
   * The decoded number is returned in numberEndIndex[0] and the index position immediately following
   * the encoded value in inputGammaCode is returned in numberEndIndex[1].
   *
   * @param inputGammaCode BitSet containing the gamma code
   * @param startIndex     Gamma code starts at this index position
   * @param numberEndIndex Return values: index 0 holds the decoded number; index 1 holds the index
   *                       position in inputGammaCode immediately following the gamma code.
   */
  public static void gammaDecodeInteger(BitSet inputGammaCode, int startIndex, int[] numberEndIndex) {
    // TODO: Fill in your code here
  }

  ////////////////////////////////////////////////////////////////////////////
  //  Methods to VB encode and Gamma encode (a gap encoded) postings list
  ////////////////////////////////////////////////////////////////////////////


  /**
   * VB encodes all the first numGaps integers in inputGaps and writes the resulting bytes to
   * OutputStream str.
   *
   * @param inputGaps Array of gaps to be encoded.  The first numGaps are encoded.
   * @param numGaps   The number of gaps to be encoded
   * @return The ByteArrayOutputStream to which the encoded bytes are written
   * @throws java.io.IOException
   */
  public static ByteArrayOutputStream VBEncode(int[] inputGaps, int numGaps) throws IOException {
    ByteArrayOutputStream vBOutputStream = new ByteArrayOutputStream();
    // byte array that's large enough to hold the VB code for an integer
    byte[] encodedInt = new byte[Integer.SIZE / 7 + 1];
    for (int i = 0; i < numGaps; ++i) {
      int numBytes = VBEncodeInteger(inputGaps[i], encodedInt);
      vBOutputStream.write(encodedInt, 0, numBytes);
    }
    return vBOutputStream;
  }


  /**
   * Gamma encodes all the first numGaps integers in inputGaps and writes the resulting bits to
   * the BitSet outputGammaCodes.
   *
   * @param inputGaps        Array of gaps to be encoded.  The first numGaps are encoded.
   * @param numGaps          The number of gaps to be encoded.
   * @param outputGammaCodes The BitSet to which the encoded bits are written
   * @return Returns the number of bits in the BitSet
   */
  public static int gammaEncode(int[] inputGaps, int numGaps, BitSet outputGammaCodes) {
    outputGammaCodes.clear();
    int nextIndex = 0;
    for (int i = 0; i < numGaps; ++i) {
      nextIndex = gammaEncodeInteger(inputGaps[i], outputGammaCodes, nextIndex);
    }
    return nextIndex;
  }

  /**
   * Gamma encodes all the first numGaps integers in inputGaps and writes the result
   * to the ByteArrayOutputStream.
   *
   * @param inputGaps        Array of gaps to be encoded.  The first numGaps are encoded.
   * @param numGaps          The number of gaps to be encoded.
   * @return A ByteArrayOutputStream to which the encoded bits are written
   */
  public static ByteArrayOutputStream gammaEncodedOutputStream(int[] inputGaps, int numGaps)
          throws IOException {
    BitSet bits = new BitSet();
    int numBits = gammaEncode(inputGaps, numGaps, bits);
    // Add an extra 1 bit (really there should be a zero terminator, we add a 1 so it's not lost in counting)
    bits.set(numBits);
    numBits++;
    byte[] bytes = bits.toByteArray();
    ByteArrayOutputStream gammaOutputStream = new ByteArrayOutputStream();
    gammaOutputStream.write(bytes);
    return gammaOutputStream;
  }


  /**
   * Unary encodes all the first numGaps integers in inputGaps and writes the resulting bits to
   * the BitSet outputUnaryCodes.
   *
   * @param inputGaps        Array of gaps to be encoded.  The first numGaps are encoded.
   * @param numGaps          The number of gaps to be encoded.
   * @param outputUnaryCodes The BitSet to which the encoded bits are written
   * @return Returns the number of bits in the BitSet
   */
  public static int unaryEncode(int[] inputGaps, int numGaps, BitSet outputUnaryCodes) {
    outputUnaryCodes.clear();
    int nextIndex = 0;
    for (int i = 0; i < numGaps; ++i) {
      nextIndex = unaryEncodeInteger(inputGaps[i], outputUnaryCodes, nextIndex);
    }
    return nextIndex;
  }

  /**
   * Unary encodes all the first numGaps integers in inputGaps and writes the result
   * to the ByteArrayOutputStream.
   *
   * @param inputGaps        Array of gaps to be encoded.  The first numGaps are encoded.
   * @param numGaps          The number of gaps to be encoded.
   * @return The ByteArrayOutputStream to which the encoded bits are written
   */
  public static ByteArrayOutputStream unaryEncodedOutputStream(int[] inputGaps, int numGaps)
          throws IOException {
    BitSet bits = new BitSet();
    int numBits = unaryEncode(inputGaps, numGaps, bits);
    // Add an extra 1 bit (really there should be a zero terminator, we add a 1 so it's not lost in counting)
    bits.set(numBits);
    numBits++;
    byte[] bytes = bits.toByteArray();
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    output.write(bytes);
    return output;
  }

  /**
   * Null encodes all the first num integers in inputs and writes the result
   * to the ByteArrayOutputStream.
   *
   * @param inputs         Array of gaps to be encoded.  The first numGaps are encoded.
   * @param num           The number of gaps to be encoded.
   */
  public static ByteArrayOutputStream nullEncodedOutputStream(int[] inputs, int num)
          throws IOException {

    ByteBuffer buffer = ByteBuffer.allocate(num * Integer.SIZE / Byte.SIZE);
    IntBuffer intBuffer = buffer.asIntBuffer();
    intBuffer.put(inputs, 0, num);
    byte[] bytes = buffer.array();
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    stream.write(bytes);
    return stream;
  }

  /**
   * Extra blank routine for your own custom encoding of the
   * first num integers in inputs and writes the result to the ByteArrayOutputStream.
   *
   * @param inputs         Array of gaps to be encoded.  The first numGaps are encoded.
   * @param num           The number of gaps to be encoded.
   * @return ByteArrayOutputStream with the inputs encoded
   */
  public static ByteArrayOutputStream customEncodeOutputStream(int[] inputs, int num)
          throws IOException {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    return stream;
  }

  ////////////////////////////////////////////////////////////////////////////
  //  Unit test methods
  ////////////////////////////////////////////////////////////////////////////


  public static void runAllUnitTests() {
    testGapEncode(false);
    testGapDecode(false);
    testVBEncodeInteger(false);
    testVBDecodeInteger(true);
    testUnaryEncodeInteger(false);
    testUnaryDecodeInteger(false);
    testGammaEncodeInteger(true);
    testGammaDecodeInteger(false);
  }


  public static boolean testGapEncode(boolean debugPrint) {
    boolean success = true;

    int[] inputDocIdsOutputGaps = {824, 829, 215406};
    int[] expectedGaps = {824, 5, 214577};
    gapEncode(inputDocIdsOutputGaps);
    if (!equalIntArrays(inputDocIdsOutputGaps, expectedGaps, 3, debugPrint, "TestGapEncode")) {
      success = false;
    }

    printStatus(success, "TestGapEncode");
    return success;
  }


  public static boolean testGapDecode(boolean debugPrint) {
    boolean success = true;

    int[] inputGapsOutputDocIds = {824, 5, 214577};
    int[] expectedDocIds = {824, 829, 215406};
    gapDecode(inputGapsOutputDocIds);
    if (!equalIntArrays(inputGapsOutputDocIds, expectedDocIds, 3, debugPrint, "TestGapDecode")) {
      success = false;
    }

    printStatus(success, "TestGapDecode");
    return success;
  }


  public static boolean testVBEncodeInteger(boolean debugPrint) {
    boolean success = true;
    byte[] outputVBCode = new byte[Integer.SIZE / 7 + 1];  // Enough to encode an integer in VBCode

    byte[] expectedBytes = {(byte) 0x85, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
    int numBytes = VBEncodeInteger(5, outputVBCode);
    if (!equalByteArrays(outputVBCode, expectedBytes, 1, debugPrint, "Encoding 5") ||
            !equalInts(numBytes, 1, debugPrint, "numBytes from encoding 5")) {
      success = false;
    }

    expectedBytes[0] = (byte) 0x80;
    numBytes = VBEncodeInteger(0, outputVBCode);
    if (!equalByteArrays(outputVBCode, expectedBytes, 1, debugPrint, "Encoding 0") ||
            !equalInts(numBytes, 1, debugPrint, "NumBytes from encoding 0")) {
      success = false;
    }

    expectedBytes[0] = (byte) 0x06;
    expectedBytes[1] = (byte) 0xB8;
    numBytes = VBEncodeInteger(824, outputVBCode);
    if (!equalByteArrays(outputVBCode, expectedBytes, 2, debugPrint, "Encoding 824") ||
            !equalInts(numBytes, 2, debugPrint, "NumBytes from encoding 824")) {
      success = false;
    }

    expectedBytes[0] = (byte) 0x0D;
    expectedBytes[1] = (byte) 0x0C;
    expectedBytes[2] = (byte) 0xB1;
    numBytes = VBEncodeInteger(214577, outputVBCode);
    if (!equalByteArrays(outputVBCode, expectedBytes, 3, debugPrint, "Encoding 214577") ||
            !equalInts(numBytes, 3, debugPrint, "NumBytes from encoding 214577")) {
      success = false;
    }

    printStatus(success, "VBEncodeInteger");
    return success;
  }


  public static boolean testVBDecodeInteger(boolean debugPrint) {
    boolean success = true;
    byte[] encodedInt = new byte[5];
    int[] numberEndIndex = new int[2];

    encodedInt[0] = (byte) 0x85;
    VBDecodeInteger(encodedInt, 0, numberEndIndex);
    if (!equalInts(numberEndIndex[0], 5, debugPrint, "Decoding 0x85") ||
            !equalInts(numberEndIndex[1], 1, debugPrint, "nextIndex when decoding 0x85")) {
      success = false;
    }

    encodedInt[0] = (byte) 0x80;
    VBDecodeInteger(encodedInt, 0, numberEndIndex);
    if (!equalInts(numberEndIndex[0], 0, debugPrint, "Decoding 0x80") ||
            !equalInts(numberEndIndex[1], 1, debugPrint, "nextIndex when decoding 0x80")) {
      success = false;
    }

    encodedInt[0] = (byte) 0x06;
    encodedInt[1] = (byte) 0xB8;
    VBDecodeInteger(encodedInt, 0, numberEndIndex);
    if (!equalInts(numberEndIndex[0], 824, debugPrint, "Decoding 0x6B8") ||
            !equalInts(numberEndIndex[1], 2, debugPrint, "nextIndex when decoding 0x06B8")) {
      success = false;
    }

    encodedInt[0] = (byte) 0x0D;
    encodedInt[1] = (byte) 0x0C;
    encodedInt[2] = (byte) 0xB1;
    VBDecodeInteger(encodedInt, 0, numberEndIndex);
    if (!equalInts(numberEndIndex[0], 214577, debugPrint, "Decoding 0x0D0CB1") ||
            !equalInts(numberEndIndex[1], 3, debugPrint, "nextIndex when decoding 0x0D0CB1")) {
      success = false;
    }

    encodedInt[0] = (byte) 0x0D;
    encodedInt[1] = (byte) 0x0C;
    encodedInt[2] = (byte) 0x0B;
    try {
      VBDecodeInteger(encodedInt, 0, numberEndIndex); // should exception
      success = false;
      if (debugPrint) {
        System.out.println("Actual:   " + Arrays.toString(numberEndIndex));
        System.out.println("Expected: IllegalArgumentException");
        System.out.println("Test failed: " + "VBDecodeInteger");
      }
    } catch (IllegalArgumentException iae) {
      // that's correct
    }

    printStatus(success, "VBDecodeInteger");
    return success;
  }


  public static boolean testUnaryEncodeInteger(boolean debugPrint) {
    boolean success = true;
    BitSet outputUnaryCode = new BitSet();

    int nextIndex = unaryEncodeInteger(5, outputUnaryCode, 0);
    if (!equalBitSet(outputUnaryCode, "111110", debugPrint, "Encoding 5") ||
            !equalInts(nextIndex, 6, debugPrint, "nextIndex for 5")) {
      success = false;
    }

    outputUnaryCode.clear();
    nextIndex = unaryEncodeInteger(0, outputUnaryCode, 0);
    if (!equalBitSet(outputUnaryCode, "0", debugPrint, "Encoding 0") ||
            !equalInts(nextIndex, 1, debugPrint, "nextIndex for 0")) {
      success = false;
    }

    printStatus(success, "UnaryEncodeInteger");
    return success;
  }


  public static boolean testUnaryDecodeInteger(boolean debugPrint) {
    boolean success = true;
    int[] numberNextIndex = new int[2];
    BitSet inputUnaryCode = createBitSet("111110");
    unaryDecodeInteger(inputUnaryCode, 0, numberNextIndex);
    if (!equalInts(numberNextIndex[0], 5, debugPrint, "Decoding 111110") ||
            !equalInts(numberNextIndex[1], 6, debugPrint, "NextIndex for 111110")) {
      success = false;
    }

    inputUnaryCode = createBitSet("0");
    unaryDecodeInteger(inputUnaryCode, 0, numberNextIndex);
    if (!equalInts(numberNextIndex[0], 0, debugPrint, "Decoding 0") ||
            !equalInts(numberNextIndex[1], 1, debugPrint, "NextIndex for 0")) {
      success = false;
    }

    printStatus(success, "UnaryDecodeInteger");
    return success;
  }


  public static boolean testGammaEncodeInteger(boolean debugPrint) {
    boolean success = true;
    BitSet outputGammaCode = new BitSet();

    int nextIndex = gammaEncodeInteger(1, outputGammaCode, 0);
    if (!equalBitSet(outputGammaCode, "0", debugPrint, "Encoding 1") ||
            !equalInts(nextIndex, 1, debugPrint, "nextIndex for 1")) {
      success = false;
    }

    nextIndex = gammaEncodeInteger(0x05, outputGammaCode, 0);
    if (!equalBitSet(outputGammaCode, "11001", debugPrint, "Encoding 0x05") ||
            !equalInts(nextIndex, 5, debugPrint, "nextIndex for 5")) {
      success = false;
    }

    nextIndex = gammaEncodeInteger(1025, outputGammaCode, 0);
    if (!equalBitSet(outputGammaCode, "111111111100000000001", debugPrint, "Encoding 1025") ||
            !equalInts(nextIndex, 21, debugPrint, "nextIndex for 1025")) {
      success = false;
    }

//    // To get this next test to succeed, you have to be careful to treat the first argument
//    // as if unsigned.  Perhaps see the function Integer.toUnsignedLong(int)
//    nextIndex = gammaEncodeInteger(-1, outputGammaCode, 0);
//    if (!equalBitSet(outputGammaCode, "111111111111111111111111111111101111111111111111111111111111111",
//            debugPrint, "Encoding -1") ||
//            !equalInts(nextIndex, 63, debugPrint, "nextIndex for -1")) {
//      success = false;
//    }

    outputGammaCode.clear();
    nextIndex = gammaEncodeInteger(0, outputGammaCode, 0);
    if (!equalBitSet(outputGammaCode, "", debugPrint, "Encoding 0") ||
            !equalInts(nextIndex, 0, debugPrint, "nextIndex for 0")) {
      success = false;
    }

    printStatus(success, "GammaEncodeInteger");
    return success;
  }


  public static boolean testGammaDecodeInteger(boolean debugPrint) {
    boolean success = true;
    int[] numberNextIndex = new int[2];
    BitSet inputGammaCode = createBitSet("11001");
    gammaDecodeInteger(inputGammaCode, 0, numberNextIndex);
    if (!equalInts(numberNextIndex[0], 5, debugPrint, "Decoding 11001")) {
      success = false;
    }

    inputGammaCode = createBitSet("1111111111111110111111100000000");
    gammaDecodeInteger(inputGammaCode, 0, numberNextIndex);
    if (!equalInts(numberNextIndex[0], 0xFF00, debugPrint, "Decoding 1111111111111110111111100000000")) {
      success = false;
    }

    printStatus(success, "GammaDecodeInteger");
    return success;
  }

  ////////////////////////////////////////////////////////////////////////////
  //  Utility methods for unit tests
  ////////////////////////////////////////////////////////////////////////////


  public static void printStatus(boolean success, String testName) {
    if (success) {
      System.out.println(testName + " test succeeded");
    } else {
      System.out.println(testName + " test failed");
    }
  }


  public static boolean equalByteArrays(byte[] bytes1, byte[] bytes2, int numBytes, boolean debugPrint, String testName) {
    boolean success = true;
    if (bytes1.length >= numBytes && bytes2.length >= numBytes) {
      for (int i = 0; i < numBytes; ++i) {
        if (bytes1[i] != bytes2[i]) {
          if (debugPrint) {
            System.out.println("Actual:   Bytes1[" + i + "] = " + bytes1[i]);
            System.out.println("Expected: Bytes2[" + i + "] = " + bytes2[i]);
          }
          success = false;
        }
      }
    } else {
      success = false;
    }
    if (debugPrint && !success) {
      System.out.println("Test failed: " + testName);
    }
    return success;
  }


  public static boolean equalIntArrays(int[] ints1, int[] ints2, int numInts, boolean debugPrint, String testName) {
    boolean success = true;
    if (ints1.length >= numInts && ints2.length >= numInts) {
      for (int i = 0; i < numInts; ++i) {
        if (ints1[i] != ints2[i]) {
          if (debugPrint) {
            System.out.println("Actual:   Ints1[" + i + "]" + ints1[i]);
            System.out.println("Expected: Ints2[" + i + "]" + ints2[i]);
          }
          success = false;
        }
      }
    } else {
      success = false;
    }
    if (debugPrint && !success) {
      System.out.println("Test failed: " + testName);
    }
    return success;
  }


  public static boolean equalInts(int number, int expected,
                                  boolean debugPrint, String testName) {
    boolean success = (number == expected);
    if (!success && debugPrint) {
      System.out.println("Actual:   " + number);
      System.out.println("Expected: " + expected);
      System.out.println("Test failed: " + testName);
    }
    return success;
  }


  public static boolean equalBitSet(BitSet bitSet, String bits, boolean debugPrint, String testName) {
    boolean success = (bitSet.equals(createBitSet(bits)));
    if (!success && debugPrint) {
      System.out.println("Test failed: " + testName);
      System.out.println("Encoded: " + createString(bitSet));
      System.out.println("Expected: " + bits);
    }
    return success;
  }


  public static BitSet createBitSet(String bits) {
    BitSet outputBitSet = new BitSet();
    int bitIndex = 0;
    for (int i = 0; i < bits.length(); ++i) {
      if (bits.charAt(i) == '1') {
        outputBitSet.set(bitIndex++, true);
      } else if (bits.charAt(i) == '0') {
        outputBitSet.set(bitIndex++, false);
      }
    }
    return outputBitSet;
  }


  public static String createString(BitSet bitSet) {
    char[] bits = new char[bitSet.length()];
    for (int i = 0; i < bitSet.length(); ++i) {
      if (bitSet.get(i)) {
        bits[i] = '1';
      } else {
        bits[i] = '0';
      }
    }
    return new String(bits);
  }

  ////////////////////////////////////////////////////////////////////////////
  //  Postings lists
  ////////////////////////////////////////////////////////////////////////////


  /** Postings list for "the" */
  public static int[] createThePostingsList() {
    int[] thePostingsList = {
            1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 14, 16, 17, 19, 21, 22, 23, 24, 25, 26, 27, 29, 30, 31,
            32, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55,
            57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
            80, 81, 82, 83, 84, 85, 86, 87, 89, 90, 91, 92, 93, 95, 96, 97, 98, 99, 100, 101, 102, 103,
            104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 122,
            123, 124, 125, 126, 127, 129, 130, 131, 132, 134, 135, 137, 138, 140, 141, 142, 143, 145,
            146, 147, 148, 149, 150, 151, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165,
            166, 168, 169, 170, 173, 174, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187,
            188, 189, 190, 191, 192, 193, 195, 196, 197, 198, 199, 201, 202, 203, 204, 205, 207, 208,
            209, 210, 211, 212, 214, 215, 216, 217, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228,
            229, 231, 232, 234, 235, 236, 237, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 250,
            251, 252, 253, 254, 255, 256, 257, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269,
            271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 283, 285, 286, 287, 289, 290, 291,
            292, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 306, 307, 308, 309, 310, 311,
            312, 313, 316, 317, 318, 319, 320, 321, 322, 324, 325, 326, 327, 328, 329, 330, 331, 332,
            334, 335, 336, 337, 338, 340, 341, 342, 343, 344, 345, 346, 348, 349, 351, 352, 353, 354,
            355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 370, 371, 372, 373,
            374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 385, 386, 387, 388, 389, 390, 391, 392,
            393, 394, 395, 396, 399, 400, 402, 403, 404, 405, 407, 408, 409, 410, 411, 412, 413, 414,
            415, 416, 417, 418, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433,
            434, 435, 436, 437, 438, 439, 440, 441, 443, 444, 445, 446, 447, 449, 450, 451, 453, 454,
            455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 466, 467, 468, 469, 470, 471, 472, 474,
            475, 477, 478, 479, 480, 481, 483, 484, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495,
            496, 497, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514,
            515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 528, 529, 530, 531, 532, 533,
            534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551,
            552, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 567, 569, 570, 571, 572,
            573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 586, 587, 588, 589, 590, 591,
            592, 594, 595, 596, 597, 598, 599, 600, 601, 602, 604, 605, 606, 607, 608, 609, 610, 611,
            612, 613, 614, 615, 616, 617, 618, 620, 621, 622, 623, 624, 625, 627, 628, 629, 630, 631,
            632, 633, 634, 635, 636, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651,
            652, 653, 655, 656, 657, 658, 659, 660, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671,
            672, 673, 674, 675, 676, 677, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690,
            691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 702, 703, 704, 705, 706, 709, 710, 711,
            712, 713, 714, 715, 716, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 729, 730, 732,
            733, 734, 735, 736, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 750, 751,
            752, 754, 755, 756, 758, 759, 760, 762, 763, 764, 765, 767, 768, 769, 770, 771, 772, 773,
            774, 776, 777, 778, 779, 780, 781, 782, 783, 784, 785, 786, 787, 788, 789, 790, 791, 792,
            793, 794, 795, 796, 797, 798, 799, 800, 801, 802, 803, 804, 805, 806, 807, 808, 809, 810,
            811, 812, 813, 814, 815, 816, 817, 818, 820, 821, 822, 823, 824, 825, 826, 827, 828, 829,
            830, 831, 832, 833, 834, 835, 836, 837, 838, 839, 840, 841, 842, 843, 844, 845, 846, 847,
            848, 849, 850, 852, 853, 854, 855, 856, 857, 859, 860, 861, 862, 863, 864, 865, 866, 867,
            868, 869, 870, 871, 872, 873, 874, 875, 876, 877, 878, 879, 880, 881, 882, 883, 884, 886,
            887, 888, 889, 890, 891, 892, 893, 895, 896, 897, 898, 899, 900, 901, 902, 903, 905, 906,
            907, 908, 909, 912, 913, 914, 915, 916, 917, 918, 919, 921, 923, 925, 926, 927, 928, 929,
            930, 932, 933, 934, 935, 936, 938, 939, 940, 941, 942, 943, 944, 945, 946, 947, 948, 950,
            952, 953, 954, 955, 956, 958, 959, 960, 961, 962, 963, 965, 966, 967, 968, 969, 970, 971,
            972, 973, 974, 976, 977, 978, 979, 980, 981, 982, 983, 984, 985, 986, 987, 988, 989, 990,
            991, 992, 993, 995, 996, 997, 998, 999, 1000};
    return thePostingsList;
  }


  public static int[] createFacultyPostingsList() {
    int[] facultyPostingsList = {
            3, 4, 9, 16, 19, 24, 25, 27, 28, 30, 31, 32, 33, 35, 36, 43, 46, 47, 52, 55, 57, 60, 61, 62,
            64, 65, 66, 77, 78, 80, 83, 86, 91, 98, 99, 100, 101, 102, 103, 104, 106, 108, 112, 113, 116,
            117, 119, 120, 127, 141, 147, 151, 156, 158, 168, 170, 172, 175, 179, 182, 184, 185, 187, 195,
            197, 199, 202, 206, 207, 208, 209, 210, 213, 221, 225, 227, 228, 233, 238, 249, 252, 255, 256,
            266, 267, 268, 270, 271, 273, 274, 281, 284, 285, 289, 290, 292, 294, 299, 301, 302, 303, 306,
            308, 312, 320, 321, 322, 325, 326, 328, 329, 332, 334, 335, 337, 341, 342, 344, 345, 347, 349,
            356, 357, 358, 360, 364, 376, 377, 379, 382, 383, 385, 395, 397, 403, 404, 405, 406, 410, 412,
            417, 418, 423, 430, 431, 432, 433, 434, 437, 440, 441, 445, 446, 452, 453, 454, 461, 464, 466,
            469, 477, 480, 486, 487, 488, 495, 496, 506, 507, 511, 512, 517, 518, 520, 522, 524, 526, 532,
            535, 540, 543, 549, 550, 558, 562, 563, 564, 571, 574, 581, 586, 587, 592, 597, 598, 604, 607,
            608, 615, 620, 621, 622, 625, 633, 634, 635, 636, 639, 640, 642, 653, 654, 656, 658, 660, 668,
            671, 676, 680, 681, 683, 686, 687, 689, 694, 697, 702, 703, 708, 710, 711, 714, 722, 723, 729,
            730, 737, 739, 742, 746, 747, 750, 756, 757, 758, 759, 764, 765, 766, 769, 770, 772, 777, 780,
            782, 783, 784, 791, 795, 798, 801, 807, 812, 815, 816, 822, 823, 824, 825, 828, 830, 833, 835,
            836, 837, 841, 852, 854, 863, 864, 865, 868, 870, 873, 880, 882, 884, 887, 888, 889, 897, 902,
            906, 912, 914, 918, 922, 924, 925, 928, 929, 932, 933, 934, 938, 939, 941, 943, 944, 947, 948,
            952, 955, 961, 962, 963, 968, 971, 973, 975, 979, 980, 983, 984, 987, 989, 993, 995, 996,
            999
    };
    return facultyPostingsList;
  }


  public static int[] createStudentPostingsList() {
    int[] studentPostingsList = {
            3, 8, 16, 19, 23, 29, 31, 40, 47, 52, 57, 61, 83, 86, 87, 100, 101, 103, 110, 113, 117, 119,
            125, 127, 145, 148, 154, 156, 159, 182, 185, 202, 208, 228, 231, 233, 240, 244, 248, 255,
            264, 271, 278, 283, 289, 297, 303, 306, 307, 308, 312, 323, 325, 332, 335, 337, 341, 344,
            356, 357, 379, 402, 430, 435, 437, 441, 453, 457, 461, 462, 463, 466, 480, 486, 487, 502,
            507, 511, 520, 532, 535, 542, 548, 550, 551, 552, 557, 559, 584, 589, 592, 604, 605, 619,
            621, 625, 629, 635, 640, 648, 653, 658, 663, 668, 670, 686, 702, 709, 719, 722, 729, 747,
            750, 751, 759, 769, 770, 782, 785, 788, 796, 798, 807, 811, 813, 815, 822, 824, 825, 828,
            840, 849, 857, 860, 880, 882, 889, 906, 918, 922, 925, 932, 933, 934, 938, 939, 941, 942,
            946, 947, 955, 962, 963, 967, 968, 971, 978, 979, 983, 984, 989, 996
    };
    return studentPostingsList;
  }


  public static int[] createBiologyPostingsList() {
    int[] biologyPostingsList = {
            9, 80, 116, 124, 137, 146, 170, 182, 197, 209, 212, 213, 237, 245, 250, 256, 290, 303, 307,
            311, 321, 333, 338, 358, 364, 377, 463, 486, 496, 512, 524, 550, 558, 572, 581, 586, 588,
            615, 622, 654, 665, 675, 683, 711, 714, 758, 791, 795, 798, 812, 840, 875, 901, 907, 937,
            938, 943, 950, 979, 980, 981, 995
    };
    return biologyPostingsList;
  }


  public static int[] createAdvancedPostingsList() {
    int[] advancedPostingsList = {
            27, 46, 55, 62, 99, 117, 122, 127, 130, 159, 212, 225, 236, 255, 256, 335, 341, 357, 413,
            437, 446, 466, 510, 535, 550, 622, 664, 666, 680, 745, 792, 798, 826, 845, 866, 874, 888,
            947, 980
    };
    return advancedPostingsList;
  }


  public static int[] createAnthropologyPostingsList() {
    int[] anthropologyPostingsList = {
            324, 335, 418, 466, 505, 686
    };
    return anthropologyPostingsList;
  }


  public static int[] CreateClassicsPostingsList() {
    int[] classicsPostingsList = {
            19, 190, 435, 519, 590
    };
    return classicsPostingsList;
  }


  public static int[] createLinguisticsPostingsList() {
    int[] linguisticsPostingsList = {
            207, 756
    };
    return linguisticsPostingsList;
  }

  ////////////////////////////////////////////////////////////////////////////
  //  Compression tests
  ////////////////////////////////////////////////////////////////////////////


  public static void runCompressionTests() throws IOException {
    System.out.println();
    Map<String,Integer> totals = new HashMap<>();
    compressionTestForPostingsList(createThePostingsList(), "the", totals);
    compressionTestForPostingsList(createFacultyPostingsList(), "faculty", totals);
    compressionTestForPostingsList(createStudentPostingsList(), "student", totals);
    compressionTestForPostingsList(createBiologyPostingsList(), "biology", totals);
    compressionTestForPostingsList(createAdvancedPostingsList(), "advanced", totals);
    compressionTestForPostingsList(createAnthropologyPostingsList(), "anthropology", totals);
    compressionTestForPostingsList(CreateClassicsPostingsList(), "classics", totals);
    compressionTestForPostingsList(createLinguisticsPostingsList(), "linguistics", totals);
    System.out.println();
    System.out.println("Totals: " + totals);
  }


  public static void compressionTestForPostingsList(int[] postingsList, String termName,
          Map<String,Integer> totals) throws IOException {

    gapEncode(postingsList);

    ByteArrayOutputStream nullEncoderOutputStream =
            nullEncodedOutputStream(postingsList, postingsList.length);
    ByteArrayOutputStream vBOutputStream =
            VBEncode(postingsList, postingsList.length);
    ByteArrayOutputStream gammaOutputStream =
            gammaEncodedOutputStream(postingsList, postingsList.length);
    ByteArrayOutputStream unaryOutputStream =
            unaryEncodedOutputStream(postingsList, postingsList.length);
    ByteArrayOutputStream customOutputStream =
            customEncodeOutputStream(postingsList, postingsList.length);

    System.out.print("For term \"" + termName + "\": ");
    System.out.print("null encode = " + nullEncoderOutputStream.size() + " bytes; ");
    incrementKey(totals, "uncompressed", nullEncoderOutputStream.size());
    System.out.print("VB encode = " + vBOutputStream.size() + " bytes; ");
    incrementKey(totals, "VB", vBOutputStream.size());
    System.out.print("Gamma code = " + gammaOutputStream.size() + " bytes; ");
    incrementKey(totals, "gamma", gammaOutputStream.size());
    System.out.print("Unary code = " + unaryOutputStream.size() + " bytes; ");
    incrementKey(totals, "unary", unaryOutputStream.size());
    System.out.print("Bit map = " + (1000 / Byte.SIZE) + " bytes; ");
    incrementKey(totals, "bitmap", (1000 / Byte.SIZE));
    System.out.print("Custom = " + customOutputStream.size() + " bytes; ");
    incrementKey(totals, "custom", customOutputStream.size());
    System.out.println();
  }

  private static <K> void incrementKey(Map<K,Integer> map, K key, int num) {
    Integer old = map.get(key);
    if (old == null) {
      map.put(key, num);
    } else {
      map.put(key, num + old);
    }
  }

  /** Main method. Runs all tests. */
  public static void main(String[] args) throws IOException {
    runAllUnitTests();
    runCompressionTests();
  }

}
