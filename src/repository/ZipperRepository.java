/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import dataacess.ZipperDAO;

/**
 *
 * @author Administrator
 */
public class ZipperRepository implements IZipperRepository{

    @Override
    public void zipFile() {
        ZipperDAO.Instance().zipFile();
    }

    @Override
    public void unzipFile() {
        ZipperDAO.Instance().unzipFile();
    }
    
}
