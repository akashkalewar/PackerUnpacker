import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

class Unpacker
{
    /////////////////////////////////////////////////////////////////////////////////////////////////////////	
    //
    //  Name :          UnPack
    //  Input :         String File
    //  Output :        void
    //  Description :   This function is used to unpack one file to multiple files
    //  Date :          6 AUG 2019
    //  Author :        Akash Nandkumar Kalewar
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
	
  	public void unpack(File fname) throws Exception
	{
		FileInputStream fis;
		FileOutputStream fos;
		int len =100;
		byte header[] = new byte[len];
		int length;
		fis = new FileInputStream(fname);
		while((length = fis.read(header,0,100)) > 0)
		{
			String str = new String(header);

			String[] words=str.split(" ");
			String filename = words[0];			
			int size = Integer.parseInt(words[1]);

			byte arr[] = new byte[size];
			fis.read(arr,0,size);

			fos=new FileOutputStream(filename);
			fos.write(arr,0,size);
		}
		
		fname.delete();
	}

}

