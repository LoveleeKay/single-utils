package com.llys.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * OCR文字识别
 *
 * @author liyongsheng
 * @date 2021/12/09 14:24
 **/
public class OCRUtils {

    public static void main(String[] args) throws TesseractException {
        ITesseract instance = new Tesseract();
        // 指定训练数据集合的路径
        instance.setDatapath("D:\\data\\tesseract");
//        // 指定为中文识别
        instance.setLanguage("chi_sim");

        // 指定识别图片
        File imgDir = new File("D:\\temp\\微信截图_20211209152717.png");
        long startTime = System.currentTimeMillis();
        String ocrResult = instance.doOCR(imgDir);

        // 输出识别结果
        System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

}
