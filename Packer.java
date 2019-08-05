import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

class Packer
{
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Name :          Pack
    //  Input :         String String
    //  Output :        File
    //  Description :   This function is used to pack multiple files into one file
    //  Date :          6 AUG 2019
    //  Author :        Akash Nandkumar Kalewar
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
 int k,len,a,m;
 FileOutputStream fos = null;
 FileInputStream fis = null;
 byte buffer[] = new byte[1024];
 byte temp[] = new byte[100];
 ArrayList<String> FName = new ArrayList<String>();
 ArrayList<Long> Size = new ArrayList<Long>();
 String fobjname;
 long fobjsize;
 
 public File pack(String src, String Dest)throws Exception
	{
		File dir = new File(src);
 		File tempfile = new File(Dest);
		File[] files = dir.listFiles();
 			for (File file : files)
   			{
				if(!file.isDirectory())
				{
					FName.add(file.getName());
					Size.add(file.length());
					len++;
				}
        		}
		tempfile.createNewFile();
		fos = new FileOutputStream(tempfile);
		for(k=0;k<len;k++)
		{
			fobjname = FName.get(k);
			fobjsize = Size.get(k);
			fis = new FileInputStream("./"+dir+"/"+fobjname);
			String Header = fobjname+" "+fobjsize;
			for(a=Header.length();a<100;a++)
			{
				Header+=" ";
			}
			temp = Header.getBytes();
 			fos.write(temp, 0, temp.length);

			while((m=fis.read(buffer))>0)
			{	
				fos.write(buffer,0,m);
			}
			fis.close();
		}
		fos.close();
		return tempfile;
	}
}

