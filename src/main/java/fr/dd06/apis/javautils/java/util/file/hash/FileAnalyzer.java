package fr.dd06.apis.javautils.java.util.file.hash;



import fr.dd06.apis.javautils.java.configuration.JSONConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FileAnalyzer {
    public FileAnalyzer() {

    }


    public String getFileMD5(File file) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            String fileMD5 = getFileChecksum(messageDigest, file);
            return fileMD5;
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean checkMD5Files(File file, File fileToCompare) {
        String file1MD5 = getFileMD5(file);
        String fileToCompareMD5 = getFileMD5(fileToCompare);
        if (file1MD5.equals(fileToCompareMD5)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void analyzeFolder(File folder, File checkList) {
        List<File> filesInFolder = getAllFilesFromFolder(folder);

        JSONConfiguration checkConfig = new JSONConfiguration(checkList);
        checkConfig.reloadConfiguration();
        for(File file : filesInFolder) {
            if(checkConfig.getConfiguration().containsKey(file.getName())) {
                if(!getFileMD5(file).equals(checkConfig.getConfiguration().get(file.getName()))) {
                    file.delete();
                }
            }
            else {
                file.delete();
            }
        }


    }


    private List<File> getAllFilesFromFolder(File folder) {

        File fileList[] = folder.listFiles();
        List<File> allFiles = new ArrayList<>();
        for (File file: fileList) {
            if (file.isFile()) {
                allFiles.add(file);
            }
        }
        return allFiles;
    }

    private String getFileChecksum(MessageDigest digest, File file) throws IOException
    {

        FileInputStream fis = new FileInputStream(file);


        byte[] byteArray = new byte[1024];
        int bytesCount = 0;


        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }


        fis.close();


        byte[] bytes = digest.digest();


        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }


        return sb.toString();
    }
}
