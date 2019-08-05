import java.io.File;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

class ChkSum
{
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //  Name :          GetMd5
    //  Input :         File
    //  Output :        String
    //  Description :   This function is used to provide Checksum of a file
    //  Date :          6 AUG 2019
    //  Author :        Akash Nandkumar Kalewar
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static String getMd5(File input) 
	{ 
		try { 

			// Static getInstance method is called with hashing MD5 
			MessageDigest md = MessageDigest.getInstance("MD5"); 

			// digest() method is called to calculate message digest 
			// of an input digest() return array of byte 
			byte[] messageDigest = md.digest((input.toString()).getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		} 

		// For specifying wrong message digest algorithms 
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 

}
}

