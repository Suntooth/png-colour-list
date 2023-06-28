import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import java.util.*;

public class listMain {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		fileWrite(allColours());
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		System.out.println("Run time: " + duration + "ms");
	}
	
	public static ArrayList<String> allColours() {
		int i, j;
		String hex;
		ArrayList<String> colours = new ArrayList<String>();
		try {
			
			BufferedImage img = ImageIO.read(new File("image.png")); 
			int height = img.getHeight(), width = img.getWidth();
			
			
			for(i=0;i<height;i++) {
				for(j=0;j<width;j++) {
					hex = "#"+Integer.toHexString(img.getRGB(j,i)).substring(2);
					colours.add(hex);
				}
			}
			
			Set<String> s = new HashSet<>(colours);
			colours.clear();
			colours.addAll(s);
			
			colours.sort(String::compareToIgnoreCase);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return colours;
	}
	
	public static void fileWrite(ArrayList<String> writeList) {
		try {
			File listFile = new File("list.txt");
			listFile.createNewFile();
			int size = writeList.size();
			int i;
			
			try (FileWriter writeFile = new FileWriter("list.txt")) {
				for(i=0;i<size;i++) {
					writeFile.write(writeList.get(i) + System.getProperty("line.separator"));
				}
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
