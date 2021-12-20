package utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeException;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 二维码工具类
 *
 * @author liyongsheng
 * @date 2021/12/08 09:50
 **/
public class QRCodeUtils {

    /**
     * 自定义参数，这部分是Hutool工具封装的
     * @return
     */
    private static QrConfig initQrConfig() {
        QrConfig config = new QrConfig(300, 300);
        config.setImg("D:\\chromedownload\\8bf1cb4257ecfaefdf064264f3255d72.jpeg");
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.BLUE);
        // 设置背景色（灰色）
        config.setBackColor(Color.GRAY);
        return config;
    }

    /**
     * 生成到文件
     *
     * @param content
     * @param filepath
     */
    public static void createQRCode2File(String content, String filepath) {
        try {
            QrCodeUtil.generate(content, initQrConfig(), FileUtil.file(filepath));
            System.out.println("生成二维码成功, 位置在：{}！" +  filepath);
        } catch (QrCodeException e) {
            System.out.println("发生错误！ {}！" + e.getMessage());
        }
    }

    /**
     * 解析二维码
     * @param filePath
     * @return
     */
    public static String decodeQRCode(String filePath) {
        return QrCodeUtil.decode(FileUtil.file(filePath));
    }

    /**
     * 生成到流
     *
     * @param content
     * @param response
     */
    public void createQRCode2Stream(String content, OutputStream response) {
        try {
            QrCodeUtil.generate(content, initQrConfig(), "png", response);
            System.out.println("生成二维码成功, 位置在：{}！");
        } catch (QrCodeException e) {
            System.out.println("发生错误！ {}！" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "D:\\temp\\QRCode.PNG";
        String content = "你也就只能用表情包了，而我能用二维码！";
        System.out.println(content);
        createQRCode2File(content, filePath);
        String decodeFilePath = "D:\\temp\\clqrcode.png";
        System.out.println(decodeQRCode(decodeFilePath));
    }
}
