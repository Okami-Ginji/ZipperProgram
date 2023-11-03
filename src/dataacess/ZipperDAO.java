package dataacess;

import common.Library;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;



public class ZipperDAO {
    private static ZipperDAO instance = null;
    Library l;
    
    
    public ZipperDAO() {
        l = new Library();        
    }

    public static ZipperDAO Instance() {
        if (instance == null) {
            synchronized (ZipperDAO.class) {
                if (instance == null) {
                    instance = new ZipperDAO();
                }
            }
        }
        return instance;
    }
    
      public void zipFile() {
        String pathSrc = l.checkInputPathFile("Enter Source Folder: ");
        String pathCompress = l.CheckDirectoryExistence("Enter Destination Folder: ");
        String fileZipName = l.inputString("Enter Name: ");
        try {
            boolean check = compressTo(pathSrc, fileZipName, pathCompress);
            if (check == true) {
                System.out.println("Successfully");
            } else {
                System.out.println("Fail");
            }
        } catch(Exception e) {
            System.out.println("=");
        }
       
    }

   public boolean compressTo(String pathSrc, String fileZipName, String pathCompress) throws IOException {
        File sourceFile = new File(pathSrc);
        String nameFos = pathCompress + "/" + fileZipName + ".zip";
        FileOutputStream fos = new FileOutputStream(nameFos);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        addFileToZip(zipOut, sourceFile, sourceFile.getName());
        zipOut.close();
        fos.close();
        return true;
    }

    private void addFileToZip(ZipOutputStream zipOut, File sourceFile, String parentPath) throws IOException {
        if (sourceFile.isDirectory()) {
            String[] files = sourceFile.list();
            for (String file : files) {
                addFileToZip(zipOut, new File(sourceFile, file), parentPath + "/" + sourceFile.getName());
            }
        } else {
            FileInputStream fis = new FileInputStream(sourceFile);
            ZipEntry zipEntry = new ZipEntry(parentPath + "/" + sourceFile.getName());
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
    }    

    public void unzipFile() {
        String pathZipFile = l.checkInputPathFile("Enter Source file: ");
        String pathExtract = l.CheckDirectoryExistence("Enter Destination Folder: ");
        try {
            boolean check = extractTo(pathZipFile, pathExtract);
            if (check == true) {
                System.out.println("Successfully");
            } else {
                System.out.println("Fail");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }    
    }

    public boolean extractTo(String pathZipFile, String pathExtract) throws IOException {
        File zipFile = new File(pathZipFile);
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile)) ) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                String fileName = zipEntry.getName();
                File newFile = new File(pathExtract, fileName);
                if (zipEntry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    newFile.getParentFile().mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
        return true;
    }
    
}