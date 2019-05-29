package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class copyFile {
	public static void main(String[] args) {
//		System.out.println((int)'A');//65
//		System.out.println((int)'Z');//90
		
		
		int k=0;
		int[] letter = new int[26];
		for(char i='A';i<='Z';i++,k++) {
			File file = new File(i+":\\");
			if(file.exists()) {
				letter[k]=1;
			}else {
				letter[k]=0;
			}
		}
		while(true) {
			for(char i='A';i<='Z';i++) {
				File target = new File(i+":\\");
				if(target.exists() && letter[(int)i-65] == 0 ) {
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
					String time = sdf.format(date);
					String dir = "E:\\"+time;
					File direction = new File(dir);
					if(!direction.exists()) {
						direction.delete();
					}else {
						direction.mkdir();
					}
					copy(dir,target);
					letter[(int)i-65] = 1;
					System.out.println("拷贝成功");
					System.out.println("拷贝成功");
					try {
						System.out.println("拷贝成功");
						Thread.sleep(3600000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			try {
				System.out.println("等待中.....");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		File file = new File("F:\\java\\java代码\\文件流");
//		String dir = "E:\\123";
//		copy(dir,file);
	}
	
	
	private static void copy(String dir,File newFile) {
		File term =new File(dir+newFile.getAbsolutePath().substring(2,newFile.getAbsolutePath().length()));
		if(newFile.isDirectory())
			if(!term.exists()) {
				term.mkdirs();
			}
		File[] f=newFile.listFiles();
		for(File ff:f)
		{
			try
			{
				if(ff.isDirectory()) {
					File direc = new File(dir+"\\"+ff.getAbsolutePath().substring(2, ff.getAbsolutePath().length()));
					if(!direc.exists()) {
						direc.mkdirs();
					}
					copy(dir,ff);
				}
				else
				{
					if(ff.isFile()) {
						String str = dir+ff.getAbsolutePath().substring(2, ff.getAbsolutePath().length());
						try {
							readAndWrite(str,ff);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
						
				}
			}
			catch(NullPointerException e)
			{}
				
		}
	}
	
	private static void readAndWrite(String dir,File target){
		try {
			File direction = new File(dir);
			if(direction.exists()) {
				direction.delete();
			}else {
				direction.createNewFile();
			}
			InputStream read = new FileInputStream(target);
			OutputStream writer = new FileOutputStream(direction);
			byte[] b=new byte[1024*1024*1];
			int n=-1;
			while((n=read.read(b,0,b.length))!=-1)
			{
			    writer.write(b,0,n);
			}
			read.close();
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();
			try {
		
				Thread.sleep(100000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
