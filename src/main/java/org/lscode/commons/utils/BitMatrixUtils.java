package org.lscode.commons.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码位图矩阵工具
 * v1.2
 */
public final class BitMatrixUtils {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private BitMatrixUtils() {
	}

	/**
	 * 位图矩阵转换为图像缓冲区
	 * @param matrix 矩阵
	 * @return BufferedImage
	 */
	public static BufferedImage bitMatrixToBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 写位图矩阵到图片文件
	 * @param matrix 矩阵
	 * @param format 格式串
	 * @param file 文件
	 * @throws IOException 输入输出异常
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = bitMatrixToBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	/**
	 * 写位图矩阵到输出流
	 * @param matrix 位图矩阵
	 * @param format 位图格式
	 * @param stream 输出流
	 * @throws IOException IO异常
	 */
	public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = bitMatrixToBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}
}