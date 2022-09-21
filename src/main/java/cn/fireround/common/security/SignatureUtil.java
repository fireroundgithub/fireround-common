package cn.fireround.common.security;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.fireround.common.StringUtils;

public class SignatureUtil {

	public static String sign(TreeMap<String, String> content, PrivateKey privateKey, String algo) {
		return sign(generateSign(content), privateKey, algo);
	}

	public static String sign(String content, PrivateKey privateKey, String algo) {
		try {
			Signature signature = Signature.getInstance(algo);
			signature.initSign(privateKey);
			signature.update(content.getBytes());
			byte[] signed = signature.sign();
			return new String(Base64.getEncoder().encode(signed));
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
	}

	private static String generateSign(TreeMap<String, String> treeMap) {
		//使用for循环连接签名
		List<String> strings = new ArrayList<>();
		for (Map.Entry<String, String> entry : treeMap.entrySet()) {
			//sign字段和
			if (!"sign".equals(strings) && !StringUtils.isEmpty(entry.getValue())) {
				strings.add(entry.getKey() + "=" + entry.getValue());
			}
		}
		//将内容用‘&'符号连接
		String contentNeedSign = StringUtils.join(strings.iterator(), '&');
		return contentNeedSign;
	}
}
