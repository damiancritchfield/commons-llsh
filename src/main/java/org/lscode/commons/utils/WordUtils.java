package org.lscode.commons.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.docx4j.TraversalUtil;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.finders.RangeFinder;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.org.apache.poi.util.IOUtils;
import org.docx4j.wml.Body;
import org.docx4j.wml.CTBookmark;
import org.docx4j.wml.Document;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

public class WordUtils {

	/**
	 * 插入图片
	 * @param infilePath 输入文件路径
	 * @param outfilePath 输出文件路径
	 * @param replacement 待插入的图片内容
	 * @throws Exception 异常
	 */
	public static void insertImgs(String infilePath, String outfilePath, Map<String, byte[]> replacement)
			throws Exception {
		// 载入模板文件
		WordprocessingMLPackage wPackage = WordprocessingMLPackage.load(new FileInputStream(infilePath));
		// 提取正文
		MainDocumentPart mainDocumentPart = wPackage.getMainDocumentPart();
		Document wmlDoc = (Document) mainDocumentPart.getJaxbElement();
		Body body = wmlDoc.getBody();
		// 提取正文中所有段落
		List<Object> paragraphs = body.getContent();
		// 提取书签并创建书签的游标
		RangeFinder rt = new RangeFinder("CTBookmark", "CTMarkupRange");
		new TraversalUtil(paragraphs, rt);

		for (String key : replacement.keySet()) {
			byte[] bytes = replacement.get(key);
			insertImg(wPackage, rt.getStarts(), key, bytes);
		}

		wPackage.save(new FileOutputStream(outfilePath));
	}

	/**
	 * 插入图片
	 * @param wordMLPackage wordMLPackage
	 * @param bms CTBookmark集合
	 * @param bookmarkName bookmarkName
	 * @param bytes 图片字节
	 * @throws Exception 异常
	 */
	public static void insertImg(WordprocessingMLPackage wordMLPackage, List<CTBookmark> bms, String bookmarkName,
			byte[] bytes) throws Exception {
		// 遍历书签
		for (CTBookmark bm : bms) {
			// 这儿可以对单个书签进行操作，也可以用一个map对所有的书签进行处理
			if (bm.getName().equals(bookmarkName)) {
				// 读入图片并转化为字节数组，因为docx4j只能字节数组的方式插入图片
				// InputStream is = new FileInputStream(imagePath);
				// byte[] bytes = IOUtils.toByteArray(is);

				// 穿件一个行内图片
				BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

				// createImageInline函数的前四个参数我都没有找到具体啥意思，，，，
				// 最有一个是限制图片的宽度，缩放的依据
				Inline inline = imagePart.createImageInline(null, null, 0, 1, false, 0);
				// 获取该书签的父级段落
				P p = (P) (bm.getParent());

				ObjectFactory factory = new ObjectFactory();
				// R对象是匿名的复杂类型，然而我并不知道具体啥意思，估计这个要好好去看看ooxml才知道
				R run = factory.createR();
				// drawing理解为画布？
				Drawing drawing = factory.createDrawing();
				drawing.getAnchorOrInline().add(inline);
				run.getContent().add(drawing);
				p.getContent().add(run);
			}
		}
	}
}
