package main.java;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*; //This class offers a rich set of APIs for reading, writing, and manipulating files and directories.
import java.nio.file.attribute.BasicFileAttributes;

//The Files class methods work on instances of Path objects.

public class Demo {

    public static void main(String[] args) throws IOException {

        createFileAndWriteText();
        readFile();
        readFileAndShowByteSize();
       // existFile();
       // createDirectory();
        //moveFile();
        //copyFile();
        //Path path = Paths.get("/Users/temporaryadmin/Documents/Java/IO-16Dec/newFolder/myFile.txt");
        //traversingDirectory(path);
        //deleteFile();

    }

    public static FileInputStream readFile() throws IOException {
        // This will create a variable with our file data
        return new FileInputStream("/Users/temporaryadmin/Documents/Java/IO-16Dec/myFile.txt");
    }

    public static void readFileAndShowByteSize() throws IOException {
        // Now we will read the file we created


        // Creating a channel for reading the data from our file
        FileChannel readChannel = readFile().getChannel();

        // The buffer will allocate the space in memory for our file data
        ByteBuffer readBuffer = ByteBuffer.allocate(1024); // the size of 1Mb

        // This read() will get the byte information ( int ) of our allocated data file
        int result = readChannel.read(readBuffer);

        // Information about how much space this data have in our Buffer
        System.out.println("File read successfully and have " + result + " bytes");
    }

    public static void createFileAndWriteText() throws IOException {
        // First we will create a file and write something.

        // Specify the name of our file as well the path where our file will be...
        // surrounding with the try and catch
        FileOutputStream mainFile = null;
        try {
            mainFile = new FileOutputStream("/Users/temporaryadmin/Documents/Java/IO-16Dec/myFile.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create a channel to the write function
        FileChannel writeChannel = mainFile.getChannel();

        // The bytebuffer will establish the amount of bytes that is possible to allocate
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024); // this is a size of 1Mb

        // Now we will write our message in a String variable
        String message = "This was successfully done!";

        // Now we will put this message into the Buffer... since ByteBuffer wants bytes as parameter
        // we have to convert into bytes.
        writeBuffer.put(message.getBytes());

        // This will turn our index to the initial position for a successful reading from the buffer
        // Without this flip() the file will be empty
        writeBuffer.flip();

        // Now it will transport our data to the location of our channel and write it with the write method.
        writeChannel.write(writeBuffer);

        System.out.println("Success");
    }

    public static void existFile() throws  IOException{
        //File.exists(): Checks whether the file (path of the file) exists in the System or not.
        Path path = Paths.get("/Users/temporaryadmin/Documents/Java/IO-16Dec/myFile.txt"); //initialize the path object
        boolean pathExists = Files.exists(path);
        System.out.println("File exists: " + pathExists);

    }

    public static void createDirectory() throws IOException {
        //File.createDirectory() : Creates a new directory from the file path instance.
        //creates the Path instance that represents the directory to create
        try {
            Path path3 = Paths.get("/Users/temporaryadmin/Documents/Java/IO-16Dec/newFolder");
            //If creating the directory succeeds, a Path instance is returned which points to the newly created path
            Path newDir = Files.createDirectory(path3);
        } catch (IOException e) { //if something goes wrong an exception will be thrown
            e.printStackTrace();
        }
    }

    public static void moveFile() throws IOException {

        //Files.move() : Moves the file from one path to another
        Path originalPath2 = Paths.get("/Users/temporaryadmin/Documents/Java/IO-16Dec/myFile.txt");
        Path destinationPath2 = Paths.get("/Users/temporaryadmin/Documents/Java/IO-16Dec/newFolder/myFile.txt");

        try {
            Files.move(originalPath2, destinationPath2);
        } catch (IOException e) {
            e.printStackTrace();  //moving file failed.
        }
    }

    public static void copyFile() throws IOException {
        Path originalPath = Paths.get("/Users/temporaryadmin/Documents/Java/IO-16Dec/newFolder/myFile.txt");
        Path destinationPath = Paths.get("/Users/temporaryadmin/Documents/Java/IO-16Dec/myFile-copy.txt");

        //will copy the file from the originalPath into the destinationPath
        try {
            Files.copy(originalPath, destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void traversingDirectory(Path path) throws IOException {
        Files.walkFileTree(path, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("pre visit dir:" + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("visit file: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("visit file failed: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("post visit directory: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void deleteFile() throws IOException {
        Files.delete(Path.of("/Users/temporaryadmin/Documents/Java/IO-16Dec/myFile-copy.txt"));
    }
}




