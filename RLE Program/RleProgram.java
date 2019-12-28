import java.util.*;

public class RleProgram {
    private static Scanner scnr = new Scanner(System.in);
    private static byte[] fileInfo = new byte[0];
    private static boolean loaded = false;

    public static void main(String[] args) {
        // Displays welcome info once
        System.out.println("Welcome to the RLE image encoder!\n");
        System.out.println("Displaying Spectrum Image:");
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow);
        System.out.println();

        // runs the menu for selections
        menuSelect();

    }

    private static void menuSelect() {
        // Menu is printed
        System.out.println("\nRLE Menu\n" +
                "--------\n" +
                "0. Exit\n" +
                "1. Load File\n" +
                "2. Load Test Image\n" +
                "3. Read RLE String\n" +
                "4. Read RLE Hex String\n" +
                "5. Read Data Hex String\n" +
                "6. Display Image\n" +
                "7. Display RLE String\n" +
                "8. Display Hex RLE Data\n" +
                "9. Display Hex Flat Data\n");
        System.out.print("Select a Menu Option: ");
        int userInput = scnr.nextInt();

        // Depending on what the user does, it will select and option and run it over.
        switch (userInput) {
            case 0:
                System.exit(1);
                break;
            case 1:
                // Load file by name
                System.out.print("Enter name of file to load: ");
                String fileName = scnr.next();
                fileInfo = ConsoleGfx.loadFile(fileName);
                loaded = true;
                menuSelect();
                break;
            case 2:
                // Load test Image
                fileInfo = ConsoleGfx.testImage;
                loaded = true;
                System.out.println("Test image data loaded.");
                menuSelect();
                break;
            case 3:
                // Read RLE String
                System.out.print("Enter an RLE string to be decoded: ");
                String userRleString = scnr.next();
                byte[] rleByteData = stringToRle(userRleString);
                fileInfo = decodeRle(rleByteData);
                loaded = true;
                menuSelect();
                break;
            case 4:
                // Read RLE Hex String
                System.out.print("Enter the hex string holding RLE data: ");
                String userRleHexString = scnr.next();
                byte[] hexByteData = stringToData(userRleHexString);
                fileInfo = decodeRle(hexByteData);
                loaded = true;
                menuSelect();
                break;
            case 5:
                // Read Data Hex String
                System.out.print("Enter the hex string holding flat data: ");
                String userDataHexString = scnr.next();
                fileInfo = stringToData(userDataHexString);
                loaded = true;
                System.out.println();
                menuSelect();
                break;
            case 6:
                // Display Image
                System.out.println("Displaying image...");
                if (loaded) {
                    ConsoleGfx.displayImage(fileInfo);
                } else {
                    System.out.println("(no data)\n");
                }
                menuSelect();
                break;
            case 7:
                // Display RLE String
                System.out.print("RLE representation: ");
                if (loaded) {
                    System.out.print(toRleString(encodeRle(fileInfo)) + "\n");
                } else {
                    System.out.print("(no data)\n");
                }
                menuSelect();
                break;
            case 8:
                // Display HEX RLE Data
                System.out.print("RLE hex values: ");
                if (loaded) {
                    System.out.print(toHexString(encodeRle(fileInfo)) + "\n");
                } else {
                    System.out.print("(no data)\n");
                }
                menuSelect();
                break;
            case 9:
                // Display Hex Flat Data
                System.out.print("Flat hex values: ");
                if (loaded) {
                    System.out.print(toHexString(fileInfo) + "\n");
                } else {
                    System.out.print("(no data)\n");
                }
                menuSelect();
                break;
            default:
                //gives error if wrong number is put in.
                System.out.println("Error! Invalid input.");
                menuSelect();
                break;
        }
    }

    // convert byte array into String
    public static String toHexString(byte[] data) {
        byte currentByte;
        StringBuilder hexString = new StringBuilder();
        // changes the number from the array into the string builder.
        for (byte datum : data) {
            currentByte = datum;
            if (currentByte == 15) {
                hexString.append("f");
            } else if (currentByte == 14) {
                hexString.append("e");
            } else if (currentByte == 13) {
                hexString.append("d");
            } else if (currentByte == 12) {
                hexString.append("c");
            } else if (currentByte == 11) {
                hexString.append("b");
            } else if (currentByte == 10) {
                hexString.append("a");
            } else {
                hexString.append(currentByte);
            }
        }
        // turns the string builder into a string.
        return hexString.toString();
    }

    // count number of runs of different digits from the Raw data.
    public static int countRuns(byte[] flatData) {
        int runsCount = 1, count = 0;

        byte currentByte = flatData[0];
        for (byte flatDatum : flatData) {
            if (currentByte != flatDatum) {
                currentByte = flatDatum;
                runsCount++;
                count = 1;
            } else if (count == 15) {
                runsCount++;
                count = 1;
            } else {
                count++;
            }
        }
        return runsCount;
    }

    // Turns the raw data into encoded data
    public static byte[] encodeRle(byte[] flatdata) {
        int runCount = 1;
        byte[] encodedData = new byte[countRuns(flatdata) * 2];
        byte currentByte = flatdata[0];
        int numSameBytes = 0;
        // loops for length of flat data
        for (int i = 0; i < flatdata.length; i++) {
            if (currentByte != flatdata[i]) {
                // if the byte is different it changes the currentbyte into the new byte and resets number of same bytes.
                encodedData[runCount - 1] = (byte) numSameBytes;
                encodedData[runCount] = currentByte;
                currentByte = flatdata[i];
                numSameBytes = 1;
                runCount += 2;
            } else if (numSameBytes == 15) {
                encodedData[runCount - 1] = (byte) numSameBytes;
                encodedData[runCount] = currentByte;
                currentByte = flatdata[i];
                numSameBytes = 1;
                runCount += 2;
            } else {
                numSameBytes++;
            }
            if (i == flatdata.length - 1) {
                encodedData[runCount - 1] = (byte) numSameBytes;
                encodedData[runCount] = currentByte;
            }
        }
        return encodedData;
    }

    // Gets size of the decoded data
    public static int getDecodedLength(byte[] rleData) {
        int decodedLength = 0;
        for (int i = 0; i < rleData.length; i += 2) {
            decodedLength += rleData[i];
        }

        return decodedLength;
    }

    // Decode the data into raw form.
    public static byte[] decodeRle(byte[] rleData) {
        byte[] decodedRle = new byte[getDecodedLength(rleData)];
        int digit = 0;
        for (int i = 0; i < rleData.length; i += 2) {
            byte currentByte = rleData[i];
            for (int j = 0; j < currentByte; j++) {
                decodedRle[j + digit] = rleData[i + 1];
            }
            digit += currentByte;
        }
        return decodedRle;
    }

    // turns hex string into byte data, Either raw or RLE
    public static byte[] stringToData(String dataString) {
        int len = dataString.length();
        byte[] data = new byte[len];
        for (int i = 0; i < len; i++) {
            data[i] = (byte) Integer.parseInt(dataString.substring(i, i + 1), 16);
        }
        return data;
    }

    // turn byte data into RLE String that is readable.
    public static String toRleString(byte[] rleData) {
        StringBuilder RleString = new StringBuilder();
        for (int i = 0; i < rleData.length; i += 2) {
            byte[] hex = {
                    rleData[i + 1]
            };
            RleString.append(rleData[i]).append(toHexString(hex)).append(":");
        }
        RleString.deleteCharAt(RleString.length() - 1);
        return RleString.toString();
    }

    public static byte[] stringToRle(String rleString) {
        // counts the number of delimiters
        int delimit = (int) rleString.chars().filter(ch -> ch == ':').count();
        byte[] RleData = new byte[(delimit + 1) * 2];
        int a = 0, b = 0;

        // add values to the byte array
        for (int i = 0; i < rleString.length(); i += a) {
            String data = rleString.substring(i);
            if (data.contains(":")) {
                String little = data.substring(0, data.indexOf(":"));
                // checks each section and changes it
                if (little.length() > 2) {
                    // first two characters are the dec number and the last character is the hex.
                    RleData[b] = (byte) Integer.parseInt(little.substring(0, 2));
                    byte[] temp = stringToData(little.substring(2));
                    RleData[b + 1] = temp[0];
                } else {
                    RleData[b] = (byte) Integer.parseInt(little.substring(0, 1));
                    byte[] temp = stringToData(little.substring(1));
                    RleData[b + 1] = temp[0];
                }
                a = little.length() + 1;
                b += 2;
            } else {
                // this is for the very last line of the RlE String
                if (data.length() > 2) {
                    // first two characters are the dec number and the last character is the hex.
                    RleData[b] = (byte) Integer.parseInt(data.substring(0, 2));
                    byte[] temp = stringToData(data.substring(2));
                    RleData[b + 1] = temp[0];
                } else {
                    RleData[b] = (byte) Integer.parseInt(data.substring(0, 1));
                    byte[] temp = stringToData(data.substring(1));
                    RleData[b + 1] = temp[0];
                }
            }

        }

        return RleData;
    }
}