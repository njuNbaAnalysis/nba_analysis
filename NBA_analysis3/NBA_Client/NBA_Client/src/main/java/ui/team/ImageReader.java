package ui.team;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dataFactory.DataFactoryMySql;

public class ImageReader {

	int progress;
	
	ArrayList<BufferedImage> map_imageList = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> map_imageList_highlight = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> map_imageToDraw = new ArrayList<BufferedImage>();

	ArrayList<BufferedImage> hot_imageList = new ArrayList<BufferedImage>(); // 原图片组件
	ArrayList<BufferedImage> hot_imageList_highlight = new ArrayList<BufferedImage>(); // 高亮图片组件
	ArrayList<BufferedImage> hot_imageToDraw = new ArrayList<BufferedImage>(); // 待绘图片组件

	private static ImageReader reader = null;
	
	
	public static ImageReader getInstance(){
        if(reader == null){
        	reader = new ImageReader();
        }
        return reader;
    }
	
	
	
	public int getProgress() {
		return progress;
	}



	public ArrayList<BufferedImage> getMap_imageList() {
		return map_imageList;
	}


	public ArrayList<BufferedImage> getMap_imageList_highlight() {
		return map_imageList_highlight;
	}


	public ArrayList<BufferedImage> getMap_imageToDraw() {
		return map_imageToDraw;
	}


	public ArrayList<BufferedImage> getHot_imageList() {
		return hot_imageList;
	}


	public ArrayList<BufferedImage> getHot_imageList_highlight() {
		return hot_imageList_highlight;
	}


	public ArrayList<BufferedImage> getHot_imageToDraw() {
		return hot_imageToDraw;
	}




	private  ImageReader() {
		try {
			for (int i = 1; i <= 29; i++) {
				String path = "image" + File.separator + "z" + i + ".png";
				BufferedImage temp = ImageIO.read(new File(path));
				map_imageList.add(temp);
				map_imageToDraw.add(temp);

				path = "image" + File.separator + "x" + i + ".png";
				map_imageList_highlight.add(ImageIO.read(new File(path)));
				
				progress = i/5+1;
			}
			
			for (int i = 1; i <= 14; i++) {
				// 读取原始图片，并且存入待绘组件列表
				String path = "image" + File.separator + "q" + i + ".png";
				BufferedImage temp = ImageIO.read(new File(path));
				hot_imageList.add(temp);
				hot_imageToDraw.add(temp);

				// 读取高亮图片
				path = "image" + File.separator + "p" + i + ".png";
				hot_imageList_highlight.add(ImageIO.read(new File(path)));
				
				progress=6+(i+1)/5;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
