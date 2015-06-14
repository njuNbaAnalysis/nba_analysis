package ui.team;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import dataFactory.DataFactoryMySql;

public class ImageReader implements Runnable{

	int progress;
	
	ArrayList<BufferedImage> map_imageList = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> map_imageList_highlight = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> map_imageToDraw = new ArrayList<BufferedImage>();

	ArrayList<BufferedImage> hot_imageList = new ArrayList<BufferedImage>(); // 原图片组件
	ArrayList<BufferedImage> hot_imageList_highlight = new ArrayList<BufferedImage>(); // 高亮图片组件
	ArrayList<BufferedImage> hot_imageToDraw = new ArrayList<BufferedImage>(); // 待绘图片组件

	ArrayList<BufferedImage> hotr_imageList = new ArrayList<BufferedImage>(); // 原图片组件红
	
	ArrayList<BufferedImage> hotb_imageList = new ArrayList<BufferedImage>(); // 原图片组件蓝

	private static ImageReader reader = null;
	
	
	public ArrayList<BufferedImage> getHotr_imageList() {
		return hotr_imageList;
	}


	public ArrayList<BufferedImage> getHotb_imageList() {
		return hotb_imageList;
	}


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


	private  ImageReader(){
		
	}

	
	public  void run() {
		try {
			for (int i = 1; i <= 29; i++) {
				String path = "image" + File.separator + "z" + i + ".png";
				BufferedImage temp = ImageIO.read(new File(path));
				map_imageList.add(temp);
				map_imageToDraw.add(temp);

				path = "image" + File.separator + "x" + i + ".png";
				map_imageList_highlight.add(ImageIO.read(new File(path)));
				
				progress = i/5+1;
				
				try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
				
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
				
				path = "image" + File.separator + "b" + i + ".png";
				hotb_imageList.add(ImageIO.read(new File(path)));
				path = "image" + File.separator + "r" + i + ".png";
				hotr_imageList.add(ImageIO.read(new File(path)));
				
				progress=6+(i+1)/5;
				
				try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
