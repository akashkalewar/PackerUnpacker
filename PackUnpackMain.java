import java.util.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.InvalidKeyException;
import java.io.File;

class PackUnpackMain
{
 public static void main(String arg[])
 {
 try
 {
	File tfile;
	File encfile = new File("documented.encrypted");
	File decfile = new File("documented.decrypted");
 	String pcsum = new String();
	String unpcsum = new String();
	String fname="";
	String enc = new String("Akash Kalewar 99");
	while(true)
	{
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter the Following Option");
	System.out.println("Pack");
	System.out.println("Unpack");
	System.out.println("exit");
	String op = sc.nextLine();
	if((op.equals("Pack")) || (op.equals("pack")))
	{
		System.out.println("Enter the Directory Name: ");
		String dirname = sc.nextLine();
		File tdir = new File(dirname);
		if(tdir.isDirectory())
		{
		System.out.println("Enter the File Name: ");
		fname = sc.nextLine();
		Packer pc = new Packer();
		tfile = pc.pack(dirname,fname);
		ChkSum ck = new ChkSum();
		pcsum = ck.getMd5(tfile);
		try {
           		CryptoUtils.encrypt(enc,tfile,encfile);		
        	    }
		catch (CryptoException ex)
		{
            		System.out.println(ex.getMessage());
            		ex.printStackTrace();
        	}
		System.out.println("File Successfully Packed");
		}
		else
		{
			System.out.println("Not a Directory");
		}
	}
	else if((op.equals("Unpack")) || (op.equals("unpack")))
	{
		System.out.println("Enter the File Name: ");
		String finame = sc.nextLine();
		File funame = new File(finame);
		ChkSum ck = new ChkSum();
		unpcsum = ck.getMd5(funame);
		if(unpcsum.equals(pcsum))
		{
		try{
           		CryptoUtils.decrypt(enc,encfile,decfile);	
        	    }
		catch (CryptoException ex)
		{
            		System.out.println(ex.getMessage());
            		ex.printStackTrace();
		}
			Unpacker upc = new Unpacker();		
			upc.unpack(funame);
			System.out.println("Unpack Successfull");
			encfile.delete();
			decfile.delete();
		}
		else
		{
			System.out.println("File Damaged");	
			File fobj1 = new File(fname);
			fobj1.delete();
		}
	}
	else if((op.equals("Exit")) || (op.equals("exit")))
	{
		System.out.println("Terminating Packer Unpacker");
		System.exit(0);
	}
	}	

 }
 catch(Exception e)
 {
 	System.out.println("Exception Occured"+e);
 }
}
}
