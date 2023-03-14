package Util;

import Classes.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//import static Classes.ClassesForInAndOut.getString;
import static Classes.MainLogic.*;


public class FrameMain extends JFrame {
    private JButton readFileBtn;
    private JButton writeFileBtn;


    private JPanel panelMain;
    private JTextField textField1;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextField textField2;


    static InputArgs inputArgs = new InputArgs();

    public static void runTest() throws IOException {
        Tests test = new Tests();
        //первый тест
        String[] pathsTest1 = {test.testPath1In, test.testPath1Out};
        runSolutionTest(pathsTest1, 1);

        //второй тест
        String[] pathsTest2 = {test.testPath2In, test.testPath2Out};
        runSolutionTest(pathsTest2, 2);

        //третий тест
        String[] pathsTest3 = {test.testPath3In, test.testPath3Out};
        runSolutionTest(pathsTest3, 3);

        //четвёртый тест
        String[] pathsTest4 = {test.testPath4In, test.testPath4Out};
        runSolutionTest(pathsTest4, 4);

        //пятый тест
        String[] pathsTest5 = {test.testPath5In, test.testPath5Out};
        runSolutionTest(pathsTest5, 5);
    }

    public static void runSolutionTest(String[] pathArgs, int num) throws IOException {
        inputArgs.setInputFile(pathArgs[0]);
        inputArgs.setOutputFile(pathArgs[1]);
        readAndWriteMethod(inputArgs);
        printSuccessMessage(num);
    }

    public FrameMain() throws IOException {

        runTest();

        this.setTitle("Основная программа");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        JFileChooser fileChooserOpen;
        JFileChooser fileChooserSave;

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");


        JFileChooser finalFileChooserOpen = fileChooserOpen;
        readFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (finalFileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        SimpleLinkedList<Integer> ans = ClassesForInAndOut.readFile(finalFileChooserOpen.getSelectedFile().getPath());
                        textArea2.setText(massivVStroki(ans));
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        JFileChooser finalFileChooserSave = fileChooserOpen;
        writeFileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (finalFileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String str = textArea2.getText();
                        String[] strNew = str.split(" ");
                        SimpleLinkedList<Integer> ans = new SimpleLinkedList<>();
                        for (int i = strNew.length - 1;i >= 0; i--){
                            ans.addFirst(Integer.parseInt(strNew[i]));
                        }
                        ans.getAnswer();
                        textArea3.setText(massivVStroki(ans));
                        textField1.setText("Ответ:");
                        String path = finalFileChooserSave.getSelectedFile().getPath();
                        File file = new File(path);
                        PrintWriter pw = new PrintWriter(file);
                        pw.println(textArea3.getText());
                        pw.close();
                    }
                    } catch(Exception ex){
                        SwingUtils.showErrorMessageBox(ex);
                    }
            }
        });
        textArea2.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        textArea3.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}