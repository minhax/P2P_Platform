package com.lo23.data.client;

import com.lo23.common.filehandler.FileHandler;
import com.lo23.common.filehandler.FileHandlerStats;
import com.lo23.data.Const;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class UploadManager
{
    void prepareToShare (String path, String title, String desc) {
        try
        {
            File fileToShare = new File(path);
            String hash = hashFile(fileToShare);
            FileHandlerStats handler = new FileHandlerStats(hash, title, fileToShare.length(),
                    Files.probeContentType(Paths.get(fileToShare.getPath())),
                    (int) (fileToShare.length() / Const.FILEPART_SIZE + fileToShare.length() % Const.FILEPART_SIZE),
                    desc);
            segmentFile(path, handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void segmentFile (String path, FileHandler handler)
    {
        try
        {
            FileInputStream toSplit = new FileInputStream(path);
            byte[] segment = new byte[Const.FILEPART_SIZE];
            int part = 0;
            while (toSplit.read(segment) != -1) {
                FileOutputStream filepart = new FileOutputStream("files/fileparts/" + handler.getHash() + ".part" + part);
                filepart.write(segment);
                filepart.close();
                part++;
            }
            toSplit.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    String hashFile (File fileToHash)
    {
        try
        {
            FileInputStream inputStream = new FileInputStream(fileToHash);
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Create byte array to read data in chunks
            byte[] byteArray = new byte[1024];
            int bytesCount = 0;

            //Read file data and update in message digest
            while ((bytesCount = inputStream.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }

            //close the stream; We don't need it now.
            inputStream.close();

            //Get the hash's bytes
            byte[] bytes = digest.digest();

            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            //return complete hash
            return sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
