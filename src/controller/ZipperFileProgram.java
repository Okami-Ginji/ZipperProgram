/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.Menu;
import repository.ZipperRepository;

/**
 *
 * @author Administrator
 */
public class ZipperFileProgram extends Menu<String>{
    static String[] mc = {"Compression", "Extraction", "Exit"};
    ZipperRepository program;
    
    public ZipperFileProgram() {
        super("\t Zipper program", mc);
        program = new ZipperRepository();
    }

    @Override
    public void execute(int n) {
        switch(n) {
            case 1:
                program.zipFile();
                break;
            case 2:
                program.unzipFile();
                break;
            case 3:
                System.exit(0);
        }
    }

}
