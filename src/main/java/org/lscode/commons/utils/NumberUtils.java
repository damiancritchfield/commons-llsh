package org.lscode.commons.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {
	
	/**
	 * 对比所有数字是否互相相等
	 * @param number 数字
	 * @return true相等，false至少有一对不相等
	 */
	public static boolean equal(Number ...number) {
		for (Number num : number) {
			if(!equalNumberArray(number, num)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 对比Number是否与数组中的每一个数字相等
	 * @param numbers 数字数组
	 * @param number 数字
	 * @return 数字是否与数字数组全部元素相等
	 */
	public static boolean equalNumberArray(Number[] numbers, Number number) {
		for (Number num : numbers) {
			if(num == null) {
				if(number != null) {
					return false;
				}
			} else {
				if(number == null) {
					return false;
				}
				if(num.doubleValue() != number.doubleValue()) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 判断数字是否是空或者0
	 * @param number 数字
	 * @return 数字是否为0
	 */
	public static boolean nullOrZero(Number number) {
		if(number == null || number.doubleValue()==0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 格式化数字
	 * @param number 数字
	 * @param pattern #.##
	 * @return 返回格式化后的数字字符串
	 */
	public static String format(Number number, String pattern) {
		if(number == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(number);
	}
	
	/**
	 * 小数位四舍五入法保留指定位数
	 * @param number 数字
	 * @param decimal 保留小数位数
	 * @return 保留指定小数位数后的数字
	 */
	public static Double decimalRound(Double number, Integer decimal) {
		return new BigDecimal(number).setScale(decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 小数位四舍五入法保留指定位数
	 * @param number 数字
	 * @param decimal 保留小数位数
	 * @return 保留指定小数位数后的数字
	 */
	public static Float decimalRound(Float number, Integer decimal) {
		return new BigDecimal(number).setScale(decimal, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 小数位四舍五入法保留指定位数
	 * @param number 数字
	 * @param decimal 保留小数位数
	 * @return 保留指定小数位数后的数字
	 */
	public static BigDecimal decimalRound(BigDecimal number, Integer decimal) {
		return number.setScale(decimal, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 两个数相加，如果任意一个数为null，直接返回a
	 * @param a
	 * @param b
	 * @return
	 */
	public static BigDecimal add(BigDecimal a, BigDecimal b) {
		if(a == null || b == null) {
			return a;
		}
		return a.add(b);
	}
}
