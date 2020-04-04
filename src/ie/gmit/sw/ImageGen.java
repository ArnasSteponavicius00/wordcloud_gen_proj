package ie.gmit.sw;

/* 
 * Arnas Steponavicius - G00361891
 * Time Complexity Table:
 * https://gist.github.com/psayre23/c30a821239f4818b0709
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import java.util.*;

public class ImageGen {
	
	//4:3 Aspect ratio
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;

	private Scanner scan = new Scanner(System.in);
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
	private Random rand = new Random();
	private String imgName;
	
	public void createImage(Queue<WordFreq> wordQueue, int maxWords) throws IOException {
		
		int count = 0, randomWidth = 0, randomHeight = 0;
		int max = 0, min = 0;
		
		Graphics graphics = image.getGraphics();
		Font font = new Font(Font.SANS_SERIF, Font.ITALIC, 62);
		
		//set the backgrounf colour of the image and fill it fully
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		
		WordFreq frequency = new WordFreq(null, 0);
		
		/*
		 * loops over the Queue and increments the count at the end of the method and draws the string
		 * onto the image. loop stops when the count is larger than maxWords which was specified by user
		 * 
		 */
		while((!wordQueue.isEmpty()) && count < maxWords) {
				
			// O (log n)
			frequency = wordQueue.poll(); // removes word from the top of the queue each loop
			
			/*
			 * use Random() to create a random gap between each word.
			 * 
			 * Ref: https://www.geneseo.edu/~baldwin/reference/random.html
			 */
			
			randomWidth = rand.nextInt(1024);
			randomHeight = rand.nextInt(768);
			
			randomWidth += rand.nextInt((max - min) + 10) + min;
			randomHeight += rand.nextInt((max - min) + 1) + min;
					
			/*
			 * set the properties of the image 
			 * and take the width and height of image away from a random value to make sure there is a word 
			 * within the specified area of the image.
			 */
			
			graphics.setFont(font);
			graphics.setColor(Color.green);
			graphics.drawString(frequency.getWord(), WIDTH - randomWidth, HEIGHT - randomHeight);
		
			//increment count
			count++;
		}
		
		System.out.println("Please enter the name of the file you wish to output");
		imgName = scan.next();

		//create the image
		graphics.dispose();		
		ImageIO.write(image, "png", new File(imgName + ".png"));

	}	
}