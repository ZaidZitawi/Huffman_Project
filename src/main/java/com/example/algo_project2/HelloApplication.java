package com.example.algo_project2;
import java.io.*;
import java.util.*;
import java.util.concurrent.TransferQueue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static FileChooser fileChooser;
    public static File file;
    String btnStyle= "-fx-background-radius: 18, 7;-fx-background-color:#eba116;";
    String labelStyle = "-fx-font-size: 18; -fx-font-weight: bold; -fx-font-family: 'YourCustomFont'; -fx-text-fill: #eba116;";
    String textFieldStyle = "-fx-text-fill: #ffffff; -fx-border-color: transparent; -fx-font-weight: bold; -fx-background-color: #2c3e50; -fx-pref-height: 30; -fx-pref-width: 900;";
    Image img0 = new Image("C:\\Users\\DELL\\IdeaProjects\\ALgo_Project2\\src\\main\\java\\Images\\img_2.png");
    Image back = new Image("C:\\Users\\DELL\\IdeaProjects\\ALgo_Project2\\src\\main\\java\\Images\\img_1.png");
    int IsClicked=0;
    TextArea textArea2;
    private Map<Character, String> huffmanCodes;
    TextArea headerTeaxtArea = new TextArea();
    int filesize=0;
    File file2;
    double compressedSize;
    int headerSize =0;

    @Override
    public void start(Stage stage) {

        //Creating grid pane

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(100,80,100,80));
        pane.setHgap(10.5);
        pane.setVgap(10.5);

        //Background Image
        Image img = new Image("C:\\Users\\DELL\\IdeaProjects\\ALgo_Project2\\src\\main\\java\\Images\\key.png");
//        ImageView imgView = new ImageView(img);
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        pane.setBackground(bGround);

        //Greeting User
        Label label = new Label("                                          Hello user, Select what you want to do to your file...");
        label.setFont(Font.font(16));
        pane.add(label, 1,0);
        label.setStyle(labelStyle);

        //This label will be updated by process of file importing
        Label process = new Label("                                                                    Process");
        process.setFont(Font.font(12));
        process.setTextFill(Color.RED );
        pane.add(process, 1, 1);

        //User button to run the program
        Button run = new Button("Run");
        run.setFont(Font.font(14));
        run.setTextFill(Color.BLACK);
        run.setPrefSize(100, 30);
        run.setStyle(btnStyle);

        //User Button to browse file
        Button browse = new Button("Browse");
        browse.setFont(Font.font(14));
        browse.setStyle(btnStyle);
        browse.setTextFill(Color.BLACK);
        browse.setPrefSize(100, 30);
        pane.add(browse , 2,2);

        //Scond Scene When the Run is clicked//
        AnchorPane mainAnchorPane = new AnchorPane();
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefWidth(600);
        anchorPane.setPrefHeight(100);
        anchorPane.setStyle("-fx-background-color: #3b4a8a");
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 450.0);





        //Add a LED Image to the scene
        ImageView v0=new ImageView(img0);
        v0.setFitWidth(50);
        v0.setFitHeight(50);

        //User Button to browse file
        Button compress = new Button("Compress");
        compress.setStyle(btnStyle);
        compress.setPrefSize(100, 80);
        compress.setGraphic(v0);
        compress.setContentDisplay(ContentDisplay.TOP);

        //Add a LED Image to the scene
        Image img1 = new Image("C:\\Users\\DELL\\IdeaProjects\\ALgo_Project2\\src\\main\\java\\Images\\img.png");
        ImageView v1=new ImageView(img1);
        v1.setFitWidth(50);
        v1.setFitHeight(50);

        //User Button to browse file
        Button decompress = new Button("Extract");
        decompress.setGraphic(v1);
        decompress.setStyle(btnStyle);
        decompress.setPrefSize(100, 80);
        decompress.setContentDisplay(ContentDisplay.TOP);

        //User button to exit
        ImageView backView = new ImageView(back);
        backView.setFitHeight(50);
        backView.setFitWidth(50);
        Button cancel = new Button("Back", backView);
        cancel.setPrefSize(100, 80);
        cancel.setStyle(btnStyle);
        cancel.setContentDisplay(ContentDisplay.TOP);
        cancel.setOnAction(event -> stage.setScene(scene));

        //Text field which will hold data of the file compressed, this txtfield is in the second Hbox
        TextField FileInfo = new TextField();
        FileInfo.setStyle(textFieldStyle);

        HBox hBox = new HBox(compress, decompress, cancel);
        HBox hBox1 = new HBox(FileInfo);
        hBox.setSpacing(50);
        VBox vBox= new VBox(hBox, hBox1);
        vBox.setSpacing(20);
        AnchorPane.setRightAnchor(vBox, 50.0);
        AnchorPane.setLeftAnchor(vBox, 50.0);
        AnchorPane.setTopAnchor(vBox, 15.0);
        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);


        //Sconed AnchorPane under anchorPane
        TextArea textArea = new TextArea();
        textArea2 = new TextArea();
        textArea2.setText("Huffman Code For Each Character in The File: \n");
        HBox stateHbox= new HBox(textArea, textArea2);
        stateHbox.setSpacing(50);
        AnchorPane anchorPane1 = new AnchorPane(stateHbox);
        anchorPane1.setStyle("-fx-background-color: #021153");
        anchorPane1.setPadding(new Insets(10));
        AnchorPane.setRightAnchor(anchorPane1, 0.0);
        AnchorPane.setLeftAnchor(anchorPane1, 0.0);
        AnchorPane.setTopAnchor(anchorPane1, 150.0);
        AnchorPane.setBottomAnchor(anchorPane1, 0.0);

        AnchorPane.setRightAnchor(stateHbox, 0.0);
        AnchorPane.setLeftAnchor(stateHbox, 0.0);
        AnchorPane.setTopAnchor(stateHbox, 0.0);
        AnchorPane.setBottomAnchor(stateHbox, 0.0);

        //Scene setting
        anchorPane.getChildren().add(vBox);
        mainAnchorPane.getChildren().addAll(anchorPane, anchorPane1);
        Scene scene1 = new Scene(mainAnchorPane, 1000, 600);
        stage.setTitle("Choices of Huffman");
        stage.getIcons().add(new Image("C:\\Users\\DELL\\IdeaProjects\\ALgo_Project2\\src\\main\\java\\Images\\img_6.png"));
        stage.setScene(scene1);
        stage.show();

        browse.setOnAction(d-> {
            IsClicked++;
            Stage stage3 = new Stage();
            fileChooser = new FileChooser();
            file=fileChooser.showOpenDialog(stage3);
//            Heap h = new Heap(256);
            if(file==(null)) {
                process.setText("You haven't chose a file yet!");
                System.out.println("null pointer");
            } else {
                if(readFile(file)==-1) {
                    process.setText("----");
                }else {
                    if (IsClicked<=1){
                        GridPane.setHalignment(run,HPos.CENTER);
                        pane.add(run, 1, 2);
                    }
                    process.setText(" ");
                    label.setText("             Reday to Process...");
                    int[] byteFrequencies = calculateByteFrequencies(file);
                    String string = displayByteFrequencies(byteFrequencies);
                    textArea.setText("Frequencies For Each Char in The File:" + " \n" + string);
                    for (int i=0 ; i< byteFrequencies.length ; i++){
                        filesize+=byteFrequencies[i];
                    }
                    Node node = buildHuffmanTree(byteFrequencies);
                    System.out.println(node.toString()+ "\n======================================================================\n");
                    huffmanCodes=buildCodedTable(node);

                    compressedSize = calculateCompressedSize(node);
                    double percentage = (1 - (compressedSize / filesize)) * 100;
                    String Percentage = String.format("%.3f", percentage);
                    FileInfo.setText("File Name: " + file.getName() + " || " +
                            "Original File Size: " + filesize + " Bytes" + " || " +
                            "Compressed File Size: " + compressedSize +" Bytes"+ " || " +
                            "Compression Percentage: " + Percentage);


                }
            }
        });

        run.setOnAction(e->{
            stage.setScene(scene1);
        });

        compress.setOnAction(o->{
            Stage stage2 = new Stage();
            GridPane pane2 = new GridPane();
            pane2.setPadding( new Insets( 110,130, 110,130 ));
            pane2.setAlignment(Pos.CENTER);
            pane2.setHgap(10);
            pane2.setVgap(10);

            //Greeting User
            Label label2 = new Label("Your compressed file is ready to download...");
            label2.setStyle(labelStyle);
            label2.setFont(Font.font(16));
            pane2.add(label2, 0,0);
            pane2.add(headerTeaxtArea, 0, 3);


            // Create a FileChooser to get the output file path

            //User button to download file
            Button btdown = new Button("Download");
            btdown.setPrefSize(100, 30);
            btdown.setStyle(btnStyle);
            btdown.setFont(Font.font(16));
            pane2.add(btdown, 1, 1);
            btdown.setOnAction(d-> {
                FileChooser fileChooser = new FileChooser();
                // Set extension filter
                FileChooser.ExtensionFilter huffExtension = new FileChooser.ExtensionFilter("Huffman files (*.huff)",
                        "*.huff");
                fileChooser.getExtensionFilters().add(huffExtension);
                file2 = fileChooser.showSaveDialog(null);
                // Check if the user selected a file
                if (file != null) {
                    // Get the selected file path with the .huff extension
                    String outputFilePath = file2.getAbsolutePath();
                    if (!outputFilePath.toLowerCase().endsWith(".huff")) {
                        outputFilePath += ".huff";
                    }

                    headerTeaxtArea.setText("This is the Header for File : " + outputFilePath +"\n" + "============================\n"
                            +createHeader(getExtension(file.getPath()), compressedSize+"" , huffmanCodes));
                    headerSize=createHeader(getExtension(file.getPath()), compressedSize+"" , huffmanCodes).toString().getBytes().length;;

                    // Call the encodeFile method with the updated output file path
                    try {
                        encodeFile(file.getPath(), huffmanCodes, outputFilePath);//With the header
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            //User button to exit
            Button btCancel = new Button("Cancel");
            btCancel.setPrefSize(100, 30);
            btCancel.setStyle(btnStyle);
            btCancel.setFont(Font.font(16));
            pane2.add(btCancel, 2, 1);
            btCancel.setOnAction(d-> stage2.close());

            //Scene setting
            Scene scene2 = new Scene(pane2);
            stage2.setTitle("Wallah 30/30");
            stage2.getIcons().add(new Image("C:\\Users\\DELL\\IdeaProjects\\ALgo_Project2\\src\\main\\java\\Images\\img_4.png"));
            stage2.setScene(scene2);
            stage2.show();
        });

        decompress.setOnAction(o->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Huffman Compressed Files", "*.huff"));
            File selectedCompressedFile = fileChooser.showOpenDialog(stage);


            Stage stage2 = new Stage();
            GridPane pane2 = new GridPane();
            pane2.setPadding( new Insets( 110,130, 110,130 ));
            pane2.setAlignment(Pos.CENTER);
            pane2.setHgap(10);
            pane2.setVgap(10);

            //Greeting User
            Label label2 = new Label("Your decompressed file is ready to download...");
            label2.setStyle(labelStyle);
            label2.setFont(Font.font(16));
            pane2.add(label2, 0,0);

            //User button to download file
            Button btdown = new Button("Download");
            //btCancel.setTextFill(c4);
            btdown.setPrefSize(100, 30);
            btdown.setStyle("");
            btdown.setFont(Font.font(16));
            pane2.add(btdown, 1, 1);
            btdown.setOnAction(d -> {
                if (selectedCompressedFile != null) {
                // Choose the directory to save the decompressed file
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(stage);
                 //header.toString().getBytes().length;
                if (selectedDirectory != null) {
                    try {
                        // Call the decompression method
                        decompressFile(selectedCompressedFile.getAbsolutePath(), selectedDirectory.getAbsolutePath(), headerSize, huffmanCodes, getExtension(file.getAbsolutePath()));
                        // Update the UI or notify the user that the decompression is complete
                        // ...
                    } catch (IOException e) {
                        e.printStackTrace();
                        // Handle the error, maybe show an error message to the user
                    }
                }
            }
            });


            //User button to exit
            Button btCancel = new Button("Cancel");
            //btCancel.setTextFill(c4);
            btCancel.setPrefSize(100, 30);
            btCancel.setStyle(btnStyle);
            btCancel.setFont(Font.font(16));
            pane2.add(btCancel, 2, 1);
            btCancel.setOnAction(d-> stage2.close());

            //Scene setting
            Scene scene2 = new Scene(pane2);
            stage2.setTitle("Power source supplied");
            stage2.getIcons().add(new Image("C:\\Users\\DELL\\IdeaProjects\\ALgo_Project2\\src\\main\\java\\Images\\img_5.png"));
            stage2.setScene(scene2);
            stage2.show();
        });
        //User button to exit
        Button cancel2 = new Button("Cancel");
        cancel2.setFont(Font.font(14));
        cancel2.setPrefSize(100, 30);
        cancel2.setStyle(btnStyle);
        cancel2.setTextFill(Color.BLACK);
        pane.add(cancel2,3,2);
        cancel2.setOnAction(e -> Platform.exit());




        //Create Scene

        stage.setScene(scene);
        stage.setTitle("Huffman Encoding program");
        stage.getIcons().add(new Image("C:\\Users\\DELL\\IdeaProjects\\ALgo_Project2\\src\\main\\java\\Images\\img.png"));
        stage.show();
    }

    public static int readFile(File input) {
        try {
            FileReader fileR = new FileReader(input);
            BufferedReader buffer = new BufferedReader(fileR);
            buffer.close();
        }catch(NumberFormatException t) {
            System.out.println(t);
        }catch (FileNotFoundException e1) {
            System.out.println(e1);
        }catch (IOException e1) {
            System.out.println(e1);
        } catch (InputMismatchException e) {
            System.out.println(e);
        }
        return 0;
    }
    public static int[] calculateByteFrequencies(File filePath) {
        int[] byteFrequencies = new int[256];
        byte[] buffer = new byte[8]; // Set the buffer size to 8 bytes

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            int bytesRead;

            // Read 8 bytes at a time
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                // Process the bytes in the buffer
                for (int i = 0; i < bytesRead; i++) {
                    byteFrequencies[buffer[i] & 0xFF]++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteFrequencies;
    }


    public static String displayByteFrequencies(int[] byteFrequencies) {
        String str ="";
        for (int i = 0; i < 256; i++) {
            if (byteFrequencies[i] > 0) {
                char character = (char) i;
                str += "Byte: " + character+ " Frequency: " +byteFrequencies[i] + "\n";
                System.out.println("Byte: " + character+ " Frequency: " +byteFrequencies[i] + "\n");
            }
        }
        return str;
    }

    
    ////////////LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAzm Ne3mal SOOOO//////////////////////////////////////
    public Node buildHuffmanTree(int[] freq) {
        int size_of_ascii_char = 256;
        Heap heap = new Heap();

        for (char i = 0; i < size_of_ascii_char; i++) {
            if (freq[i] > 0) {
                //build the مربعات nodes
                heap.add(new Node(i, freq[i], null, null));
            }
        }

        char nullChar = '\0';
        if (heap.getSize() == 1) {
            //build the دوائر nodes
            heap.add(new Node(nullChar, 1, null, null));
        }

        //build the دوائر nodes
        while (heap.getSize() > 1) {
            Node left = heap.poll();
            Node right = heap.poll();
            Node parent = new Node(nullChar, left.getFreq() + right.getFreq(), left, right);
            heap.add(parent);
        }

        Node root = heap.poll();
        assignHuffmanCodes(root);

        return root;
    }

    // This method is now used to start the recursive process of building the codes
    private void assignHuffmanCodes(Node root) {
        if (root != null) {
            generateHuffmanCodes(root, "", textArea2);
        }
    }

    // method to generate Huffman codes
    private static void generateHuffmanCodes(Node node, String code, TextArea textArea) {
        if (node != null) {
            // When a leaf node is reached, append the character and its code to the text area
            if (node.getLeft() == null && node.getRight() == null) {
                textArea.appendText("Character: " + node.getData() + "                Code: " + code + "\n");
            }
            // Traverse left
            generateHuffmanCodes(node.getLeft(), code + "0", textArea);
            // Traverse right
            generateHuffmanCodes(node.getRight(), code + "1", textArea);
        }
    }


    private double calculateCompressedSize(Node root) {
        if (root == null) {
            return 0;
        }

        // Start the recursive process to calculate the compressed size
        return calculateCompressedSize(root, 0)/8;
    }

    // Recursive method to calculate the compressed size
    private double calculateCompressedSize(Node node, int depth) {
        if (node == null) {
            return 0;
        }

        // When a leaf node is reached, return the product of frequency and code length ليييف
        if (node.getLeft() == null && node.getRight() == null) {
            return node.getFreq() * depth;
        }

        // Traverse left and right, accumulating the compressed size
        return calculateCompressedSize(node.getLeft(), depth + 1) + calculateCompressedSize(node.getRight(), depth + 1);
    }




    public void encodeFile(String inputFilePath, Map<Character, String> huffmanCodes, String outputFilePath) throws IOException {
            StringBuilder encodedContent = new StringBuilder();

            try (FileInputStream fileInputStream = new FileInputStream(inputFilePath)) {
                int character;
                while ((character = fileInputStream.read()) != -1) {
                    encodedContent.append(huffmanCodes.get((char) character));
                }
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                writeStringAsBytes(encodedContent.toString(), bufferedOutputStream);
            }

        // Obtain original file extension and size
        String originalExtension = getExtension(inputFilePath);
        long DataSize = new File(inputFilePath).length();

        // Create the header
        String header = createHeader(originalExtension, DataSize +"", huffmanCodes);

        // Combine the header and encoded content
        String finalEncodedContent = header + encodedContent.toString();

        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            // Write the final encoded content (including the header) to the output file
            writeStringAsBytes(finalEncodedContent, bufferedOutputStream);
        }
    }

    //method to get extension
    private String getExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex + 1);
        }
        return "";
    }

    //method to create header
    private String createHeader(String originalExtension, String DataSize, Map<Character, String> huffmanCodes) {
        StringBuilder header = new StringBuilder();

        // Add original file extension
        header.append("Ext: ").append(originalExtension).append("\n");

        // Add original file size
        header.append("Size: ").append(DataSize).append("\n");
        header.append("Huffman Codes:\n");
        // Add Huffman codes
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            header.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }

        // Add header size
        int headerSize = header.toString().getBytes().length;
        header.insert(0,"HeaderSize: "+ headerSize + "\n");
        header.append("----HEADER-END----");

        return header.toString();
    }

        //methode that gets the encoded content and output file and write the content inside it
        private void writeStringAsBytes(String s, OutputStream outputStream) throws IOException {
            int byteValue = 0;
            int bitPosition = 7;

            for (char bit : s.toCharArray()) {
                if (bit == '1') {
                    byteValue |= (1 << bitPosition);
                }
                bitPosition--;

                if (bitPosition < 0) { // write byte and reset
                    outputStream.write(byteValue);
                    byteValue = 0;
                    bitPosition = 7;
                }
            }

            if (bitPosition != 7) { // write the last byte
                outputStream.write(byteValue);
            }
        }

        //method to connect the frequencies of each byte with its character and returns hashmap
    public HashMap<Character, String> buildCodedTable(Node root){
        HashMap<Character, String> codedTable = new HashMap<>();
        createCodedTable(root, "", codedTable);
        return codedTable;
    }

    public void createCodedTable(Node node, String s, HashMap<Character, String> codedTable) {

        if (!node.isLeaf()){
            createCodedTable(node.getLeft(), s + "0", codedTable);
            createCodedTable(node.getRight(), s + "1", codedTable);
        }else{
            codedTable.put(node.getData(),s);
        }

    }

    //read the encoded content of a file and returns the original file
    public static void decompressFile(String compressedFilePath, String outputDir, int headerSize, Map<Character, String> huffmanCodes, String originalExtension) throws IOException {
        File inputFile = new File(compressedFilePath);
        try (FileInputStream fileInputStream = new FileInputStream(inputFile);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            // Read the header
            byte[] headerBytes = new byte[headerSize];
            bufferedInputStream.read(headerBytes, 0, headerSize);
            // The header is read, but not used since originalExtension is directly passed

            // Read the rest of the file as compressed data
            ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            int b;
            while ((b = bufferedInputStream.read()) != -1) {
                dataStream.write(b);
            }
            byte[] compressedData = dataStream.toByteArray();

            // Decompress and decode data
            String decodedData = decodeData(compressedData, huffmanCodes);

            // Write the decoded data to a new file with the original extension
            File outputFile = new File(outputDir + File.separator + "decompressed." + originalExtension);
            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                fileOutputStream.write(decodedData.getBytes());
            }
        }
    }


    private static String decodeData(byte[] data, Map<Character, String> huffmanCodes) {
        // Convert the bytes to a binary string
        StringBuilder binaryStringBuilder = new StringBuilder();
        for (byte b : data) {
            binaryStringBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }

        // Decode the binary string using Huffman codes
        StringBuilder decodedData = new StringBuilder();
        String tempCode = "";
        Map<String, Character> reverseHuffmanCodes = new HashMap<>(); // Reverse the map for decoding
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            reverseHuffmanCodes.put(entry.getValue(), entry.getKey());
        }
        for (int i = 0; i < binaryStringBuilder.length(); i++) {
            tempCode += binaryStringBuilder.charAt(i);
            if (reverseHuffmanCodes.containsKey(tempCode)) {
                decodedData.append(reverseHuffmanCodes.get(tempCode));
                tempCode = "";
            }
        }
        return decodedData.toString();
    }




    public static void main(String[] args) {
        launch(args);
    }

}

