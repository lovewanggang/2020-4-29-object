package com.nature.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageCompressUtil {

	 /** 
     * * 图片文件读取 
     *  
     * @param srcImgPath 
     * @return 
     */  
    private static BufferedImage InputImage(String srcImgPath) {  
  
        BufferedImage srcImage = null;  
        try {  
            // 构造BufferedImage对象  
            File file = new File(srcImgPath);  
//            FileInputStream in = new FileInputStream(file);  
//            byte[] b = new byte[5];  
//            in.read(b);  
            srcImage = javax.imageio.ImageIO.read(file);  
        } catch (IOException e) {  
            System.out.println("读取图片文件出错！" + e.getMessage());  
            e.printStackTrace();  
        }  
        return srcImage;  
    }  
    /**
     * 将图片压缩，尺寸不变
     * @param srcImgPath
     * @param outImgPath
     * @param outImgSize
     */
    public static void TosmallerpicMaxSize(String srcImgPath,String outImgPath,long outImgSize){
    	File srcImgFile = new File(srcImgPath);
    	TosmallerpicMaxSize(srcImgFile, outImgPath, outImgSize);
    }
    
    public static void TosmallerpicMaxSize(File srcImgFile,String outImgPath,long outImgSize){
    	float per = (float)outImgSize/(float)srcImgFile.length();
    	TosmallerpicMaxSize(srcImgFile, outImgPath, per);
    }
    
	public static void TosmallerpicMaxSize(File srcImgFile, String outImgPath,float per) {
		try {

			OutImage(outImgPath, ImageIO.read(srcImgFile), per);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
    /** 
     * * 将图片按照指定的图片尺寸、源图片质量压缩(默认质量为1) 
     *  
     * @param srcImgPath 
     *            :源图片路径 
     * @param outImgPath 
     *            :输出的压缩图片的路径 
     * @param new_w 
     *            :压缩后的图片宽 
     * @param new_h 
     *            :压缩后的图片高 
     */  
    public static void Tosmallerpic(String srcImgPath, String outImgPath,  
            int new_w, int new_h) {  
        Tosmallerpic(srcImgPath, outImgPath, new_w, new_h, 1F);  
    }  
  
    /** 
     * 将图片按照指定的尺寸比例、源图片质量压缩(默认质量为1) 
     *  
     * @param srcImgPath 
     *            :源图片路径 
     * @param outImgPath 
     *            :输出的压缩图片的路径 
     * @param ratio 
     *            :压缩后的图片尺寸比例 
     * @param per 
     *            :百分比 
     */  
  
  
    /** 
     * 将图片按照指定长或者宽的最大值来压缩图片(默认质量为1) 
     *  
     * @param srcImgPath 
     *            :源图片路径 
     * @param outImgPath 
     *            :输出的压缩图片的路径 
     * @param maxLength 
     *            :长或者宽的最大值 
     * @param per 
     *            :图片质量 
     */  
    public static void Tosmallerpic(String srcImgPath, String outImgPath,  
            int maxLength) {  
        Tosmallerpic(srcImgPath, outImgPath, maxLength, 1F);  
    }  
  
    /** 
     * * 将图片按照指定的图片尺寸、图片质量压缩 
     *  
     * @param srcImgPath 
     *            :源图片路径 
     * @param outImgPath 
     *            :输出的压缩图片的路径 
     * @param new_w 
     *            :压缩后的图片宽 
     * @param new_h 
     *            :压缩后的图片高 
     * @param per 
     *            :百分比 
     */  
    public static void Tosmallerpic(String srcImgPath, String outImgPath,  
            int new_w, int new_h, float per) {  
        // 得到图片  
        BufferedImage src = InputImage(srcImgPath);  
        int old_w = src.getWidth();  
        // 得到源图宽  
        int old_h = src.getHeight();  
        // 得到源图长  
        // 根据原图的大小生成空白画布  
        BufferedImage tempImg = new BufferedImage(old_w, old_h,  
                BufferedImage.TYPE_INT_RGB);  
        // 在新的画布上生成原图的缩略图  
        Graphics2D g = tempImg.createGraphics();  
        g.setColor(Color.white);  
        g.fillRect(0, 0, old_w, old_h);  
        g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);  
        g.dispose();  
        BufferedImage newImg = new BufferedImage(new_w, new_h,  
                BufferedImage.TYPE_INT_RGB);  
        newImg.getGraphics().drawImage(  
                tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,  
                0, null);  
        // 调用方法输出图片文件  
        OutImage(outImgPath, newImg, per);  
    }  
  
    /** 
     * * 将图片按照指定的尺寸比例、图片质量压缩 
     *  
     * @param srcImgPath 
     *            :源图片路径 
     * @param outImgPath 
     *            :输出的压缩图片的路径 
     * @param ratio 
     *            :压缩后的图片尺寸比例 
     * @param per 
     *            :百分比 
     */  
    public static void Tosmallerpic1(BufferedImage src, String outImgPath,  
            float ratio, float per) {  
        // 得到图片  
       
        int old_w = src.getWidth();  
        // 得到源图宽  
        int old_h = src.getHeight();  
        // 得到源图长  
        int new_w = 0;  
        // 新图的宽  
        int new_h = 0;  
        // 新图的长  
        BufferedImage tempImg = new BufferedImage(old_w, old_h,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = tempImg.createGraphics();  
        g.setColor(Color.white);  
        // 从原图上取颜色绘制新图g.fillRect(0, 0, old_w, old_h);  
        g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);  
        g.dispose();  
        // 根据图片尺寸压缩比得到新图的尺寸new_w = (int) Math.round(old_w * ratio);  
        new_h = (int) Math.round(old_h * ratio); 
        new_w = (int) Math.round(old_w * ratio); 
        BufferedImage newImg = new BufferedImage(new_w, new_h,  
                BufferedImage.TYPE_INT_RGB);  
        newImg.getGraphics().drawImage(  
                tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,  
                0, null);  
        // 调用方法输出图片文件
        OutImage(outImgPath, newImg, per);  
    }  
  
    /** 
     * * 指定长或者宽的最大值来压缩图片 
     *  
     * @param srcImgPath 
     *            :源图片路径 
     * @param outImgPath 
     *            :输出的压缩图片的路径 
     * @param maxLength 
     *            :长或者宽的最大值 
     * @param per 
     *            :图片质量 
     */  
    public static void Tosmallerpic(String srcImgPath, String outImgPath,  
            int maxLength, float per) {  
        // 得到图片  
        BufferedImage src = InputImage(srcImgPath);  
        int old_w = src.getWidth();  
        // 得到源图宽  
        int old_h = src.getHeight();  
        // 得到源图长  
        int new_w = 0;  
        // 新图的宽  
        int new_h = 0;  
        // 新图的长  
        BufferedImage tempImg = new BufferedImage(old_w, old_h,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = tempImg.createGraphics();  
        g.setColor(Color.white);  
        // 从原图上取颜色绘制新图  
        g.fillRect(0, 0, old_w, old_h);  
        g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);  
        g.dispose();  
        // 根据图片尺寸压缩比得到新图的尺寸  
        if (old_w > old_h) {  
            // 图片要缩放的比例  
            new_w = maxLength;  
            new_h = (int) Math.round(old_h * ((float) maxLength / old_w));  
        } else {  
            new_w = (int) Math.round(old_w * ((float) maxLength / old_h));  
            new_h = maxLength;  
        }  
        BufferedImage newImg = new BufferedImage(new_w, new_h,  
                BufferedImage.TYPE_INT_RGB);  
        newImg.getGraphics().drawImage(  
                tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,  
                0, null);  
        // 调用方法输出图片文件  
        OutImage(outImgPath, newImg, per);  
    } 
    
    /** 
     * * 指定长或者宽的最大值来压缩图片 
     *  
     * @param src 
     *            :源图片
     * @param outImgPath 
     *            :输出的压缩图片的路径 
     * @param maxLength 
     *            :长或者宽的最大值 
     * @param per 
     *            :图片质量 
     */  
    public static void Tosmallerpic(BufferedImage src, String outImgPath,  
            int maxLength, float per) {  
        // 得到图片  
        int old_w = src.getWidth();  
        // 得到源图宽  
        int old_h = src.getHeight();  
        // 得到源图长  
        int new_w = maxLength;  
        // 新图的宽  
        int new_h = (int)((float)old_h/((float)old_w/(float)new_w));  
        // 新图的长  
        BufferedImage tempImg = new BufferedImage(old_w, old_h,  
                BufferedImage.TYPE_INT_RGB);  
        Graphics2D g = tempImg.createGraphics();  
        g.setColor(Color.white);  
        // 从原图上取颜色绘制新图  
        g.fillRect(0, 0, old_w, old_h);  
        g.drawImage(src, 0, 0, old_w, old_h, Color.white, null);  
        g.dispose();  
        // 根据图片尺寸压缩比得到新图的尺寸  
//        if (old_w > old_h) {  
            // 图片要缩放的比例  
//            new_w = maxLength;  
//            new_h = (int) Math.round(old_h * ((float) maxLength / old_w));  
//        } else {  
//            new_w = (int) Math.round(old_w * ((float) maxLength / old_h));  
//            new_h = maxLength;  
//        }  
        
        BufferedImage newImg = new BufferedImage(new_w, new_h,  
                BufferedImage.TYPE_INT_RGB);  
        newImg.getGraphics().drawImage(  
                tempImg.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,  
                0, null); 
//       newImg =  rotateImage(newImg,90);
        // 调用方法输出图片文件  
        OutImage(outImgPath, newImg, per);  
    }
    public static BufferedImage rotateImage(BufferedImage bufferedimage,
            int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
    
    /** 
     * * 指定长或者宽的最大值来压缩图片 
     *  
     * @param srcImgFile 
     *            :源图片
     * @param outImgPath 
     *            :输出的压缩图片的路径 
     * @param maxLength 
     *            :长或者宽的最大值 
     * @param per 
     *            :图片质量 
     */  
    public static void Tosmallerpic(File srcImgFile, String outImgPath,  
            int maxLength, float per) {  
    	try {
			Tosmallerpic(ImageIO.read(srcImgFile), outImgPath, maxLength, per);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  
    /** 
     * * 将图片文件输出到指定的路径，并可设定压缩质量 
     *  
     * @param outImgPath 
     * @param newImg 
     * @param per 
     */  
    private static void OutImage(String outImgPath, BufferedImage newImg,  
            float per) {  
        // 判断输出的文件夹路径是否存在，不存在则创建  
        File file = new File(outImgPath);  
        if (!file.getParentFile().exists()) {  
            file.getParentFile().mkdirs();  
        }// 输出到文件流  
        try {  
            FileOutputStream newimage = new FileOutputStream(outImgPath);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);  
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(newImg);  
            // 压缩质量  
            jep.setQuality(per, true);  
            encoder.encode(newImg, jep);  
            newimage.close();  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch blocke.printStackTrace();  
        } catch (ImageFormatException e) {  
            // TODO Auto-generated catch blocke.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch blocke.printStackTrace();  
        }  
    } 
    
    private float getPer(float per){
//    	if(per>1){
//    		return 1f;
//    	}else if(per >= 0.9){
//    		return per;
//    	}else if(per >= 0.7){
//    		return 0.9f;
//    	}else if(per >= 0.5){
//    		return 0.75f;
//    	}else if(per >= 0.4){
//    		return 0.5f;
//    	}else if(per >= 0.1&&per <= 0.2){
//    		return 0.1f;
//    	}else{
//    		return per;
//    	}
    	float reper = per;
    	switch ((int)(per*10)) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		default:
			reper = 1f;
			break;
		}
    	
    	return reper;
    }
  
    public static void main(String args[]) {  
    	File imageFile = new File("F://111.jpg"); 
    	
    	float per = 600*1024/(float)imageFile.length();
    	//per = (float)Math.round(per*100)/100;
    	System.out.println(per);
    	
    	try {
			OutImage("F://11111.jpg", ImageIO.read(imageFile), per);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
}
