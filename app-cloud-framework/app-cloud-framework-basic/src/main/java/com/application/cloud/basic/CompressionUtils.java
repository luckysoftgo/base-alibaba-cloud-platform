package com.application.cloud.basic;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.*;

/**
 * @author ：孤狼
 * @description: 压缩/解压缩操作
 * @modified By：
 * @version: 1.0.0
 */
public class CompressionUtils {
	
	/**
	 * 文件操作
	 */
	public static class FileUtil {
		
		/**
		 * 获得文件字节信息.
		 *
		 * @param filePath
		 * @return
		 * @throws IOException
		 */
		public static byte[] fileToByte(String filePath) throws IOException {
			File file = new File(filePath);
			if (!file.exists()) {
				System.out.println("文件路径上的文件为空");
				return null;
			}
			Path path = file.toPath();
			byte[] data = Files.readAllBytes(path);
			//返回字节信息.
			return data;
		}
		
		/**
		 * 获得文件 base64 encode 后的字符串信息.
		 *
		 * @param filePath
		 * @return
		 * @throws IOException
		 */
		public static String base64EncodeToByte(String filePath) throws IOException {
			byte[] data = fileToByte(filePath);
			if (data == null) {
				System.out.println("得到的文件夹:" + filePath + "下的字节信息为空");
				return null;
			}
			return new BASE64Encoder().encodeBuffer(data);
		}
		
		/**
		 * 对字节数据进行 base64 encode 后的字符串信息.
		 *
		 * @param data
		 * @return
		 * @throws IOException
		 */
		public static String base64EncodeByte(byte[] data) {
			if (data == null) {
				System.out.println("给定的字节信息为空");
				return null;
			}
			return new BASE64Encoder().encodeBuffer(data);
		}
		
		/**
		 * 获得文件 base64 dencode后的字节信息.
		 *
		 * @param content
		 * @return
		 * @throws IOException
		 */
		public static byte[] base64DencodeToByte(String content) throws IOException {
			if (StringUtils.isBlank(content)) {
				System.out.println("給定的文件信息为空");
				return null;
			}
			return new BASE64Decoder().decodeBuffer(content);
		}
		
		/**
		 * 获得文件 base64 dencode后的字节信息.
		 *
		 * @param file
		 * @return
		 * @throws IOException
		 */
		public static byte[] base64DencodeToByte(File file) throws IOException {
			if (file == null) {
				System.out.println("給定的文件信息为空");
				return null;
			}
			FileInputStream inputStream = new FileInputStream(file);
			return new BASE64Decoder().decodeBuffer(inputStream);
		}
		
		/**
		 * 获得文件 base64 dencode后的字节信息.
		 *
		 * @param inputStream
		 * @return
		 * @throws IOException
		 */
		public static byte[] base64DencodeToByte(FileInputStream inputStream) throws IOException {
			if (inputStream == null) {
				System.out.println("給定的文件流信息为空");
				return null;
			}
			return new BASE64Decoder().decodeBuffer(inputStream);
		}
	}
	
	/**
	 * zip 格式的文件解压缩.
	 */
	public static class ZipModeUtil extends FileUtil {
		
		/**
		 * 压缩文件,得到zip文件的流信息
		 *
		 * @param filePath
		 * @return
		 * @throws IOException
		 */
		public static String base64EncodeCompress(String filePath) throws IOException {
			byte[] data = compress(filePath);
			if (data == null) {
				System.out.println("文件路径上的文件字节信息为空");
				return null;
			}
			//得到zip字节结果.
			return base64EncodeByte(data);
		}
		
		/**
		 * 压缩文件,得到zip文件的流信息
		 *
		 * @param data
		 * @return
		 * @throws IOException
		 */
		public static String base64EncodeCompress(byte[] data) throws IOException {
			byte[] encodeData = compress(data);
			if (data == null) {
				System.out.println("文件路径上的文件字节信息为空");
				return null;
			}
			//得到zip字节结果.
			return base64EncodeByte(encodeData);
		}
		
		/**
		 * 压缩文件,得到zip文件的流信息
		 *
		 * @param filePath
		 * @return
		 * @throws IOException
		 */
		public static byte[] compress(String filePath) throws IOException {
			byte[] data = fileToByte(filePath);
			if (data == null) {
				System.out.println("文件路径上的文件字节信息为空");
				return null;
			}
			//得到zip字节结果.
			return compress(data);
		}
		
		/**
		 * 压缩文件,得到zip文件的流信息
		 *
		 * @param data
		 * @return
		 * @throws IOException
		 */
		public static byte[] compress(byte[] data) throws IOException {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ZipOutputStream zip = new ZipOutputStream(output);
			ZipEntry zipEntry = new ZipEntry("zip");
			zipEntry.setSize(data.length);
			zip.putNextEntry(zipEntry);
			zip.write(data);
			zip.flush();
			zip.closeEntry();
			zip.close();
			byte[] compressed = output.toByteArray();
			output.close();
			return compressed;
		}
		
		/**
		 * 解压缩文件,得到zip文件的流信息
		 *
		 * @param content
		 * @return
		 * @throws IOException
		 */
		public static byte[] base64DencodeCompress(String content) throws IOException {
			if (StringUtils.isBlank(content)) {
				System.out.println("字符串内容信息为空");
				return null;
			}
			//得到zip字节结果.
			return base64DencodeToByte(content);
		}
		
		/**
		 * 解压缩zip文件,得到文件的流信息
		 *
		 * @param filePath
		 * @return
		 * @throws IOException
		 */
		public static byte[] decompress(String filePath) throws IOException {
			byte[] data = fileToByte(filePath);
			if (data == null) {
				System.out.println("文件路径上的文件字节信息为空");
				return null;
			}
			//得到zip字节结果.
			return decompress(data);
		}
		
		/**
		 * 解压缩zip文件,得到文件的流信息
		 *
		 * @param data
		 * @return
		 * @throws IOException
		 */
		public static byte[] decompress(byte[] data) throws IOException {
			ByteArrayInputStream input = new ByteArrayInputStream(data);
			ZipInputStream zip = new ZipInputStream(input);
			byte[] decompressed = null;
			if (zip.getNextEntry() != null) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024 * 1];
				for (int length = zip.read(buffer); length > 0; length = zip.read(buffer)) {
					out.write(buffer, 0, length);
				}
				out.flush();
				//解压缩信息.
				decompressed = out.toByteArray();
				out.close();
			}
			zip.close();
			input.close();
			return decompressed;
		}
	}
	
	/**
	 * GZip 格式的文件解压缩.
	 */
	public static class GZipModeUtil extends FileUtil {
		
		/**
		 * 压缩文件,得到gzip文件的流信息
		 *
		 * @param filePath
		 * @return
		 * @throws IOException
		 */
		public static String base64EncodeCompress(String filePath) throws IOException {
			byte[] data = compress(filePath);
			if (data == null) {
				System.out.println("文件路径上的文件字节信息为空");
				return null;
			}
			//得到zip字节结果.
			return base64EncodeByte(data);
		}
		
		/**
		 * 压缩文件,得到gzip文件的流信息
		 *
		 * @param data
		 * @return
		 * @throws IOException
		 */
		public static String base64EncodeCompress(byte[] data) throws IOException {
			byte[] encodeData = compress(data);
			if (data == null) {
				System.out.println("文件路径上的文件字节信息为空");
				return null;
			}
			//得到zip字节结果.
			return base64EncodeByte(encodeData);
		}
		
		
		/**
		 * 压缩文件,得到gzip文件的流信息
		 *
		 * @param filePath
		 * @return
		 * @throws IOException
		 */
		public static byte[] compress(String filePath) throws IOException {
			byte[] data = fileToByte(filePath);
			if (data == null) {
				System.out.println("文件路径上的文件字节信息为空");
				return null;
			}
			//得到gzip字节结果.
			return compress(data);
		}
		
		/**
		 * 压缩文件,得到gzip文件的流信息
		 *
		 * @param data
		 * @return
		 * @throws IOException
		 */
		public static byte[] compress(byte[] data) throws IOException {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(output);
			gzip.write(data);
			gzip.flush();
			gzip.close();
			byte[] compressed = output.toByteArray();
			output.close();
			return compressed;
		}
		
		/**
		 * 解压缩文件,得到zip文件的流信息
		 *
		 * @param content
		 * @return
		 * @throws IOException
		 */
		public static byte[] base64DencodeCompress(String content) throws IOException {
			if (StringUtils.isBlank(content)) {
				System.out.println("字符串内容信息为空");
				return null;
			}
			//得到zip字节结果.
			return base64DencodeToByte(content);
		}
		
		/**
		 * 解压缩 gzip文件,得到文件的流信息
		 *
		 * @param filePath
		 * @return
		 * @throws IOException
		 */
		public static byte[] decompress(String filePath) throws IOException {
			byte[] data = fileToByte(filePath);
			if (data == null) {
				System.out.println("文件路径上的文件字节信息为空");
				return null;
			}
			//得到gzip字节结果.
			return decompress(data);
		}
		
		/**
		 * 解压缩 gzip文件,得到文件的流信息
		 *
		 * @param data
		 * @return
		 * @throws IOException
		 */
		public static byte[] decompress(byte[] data) throws IOException {
			ByteArrayInputStream input = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(input);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024 * 1];
			for (int length = gzip.read(buffer); length > 0; length = gzip.read(buffer)) {
				output.write(buffer, 0, length);
			}
			output.flush();
			byte[] decompressed = output.toByteArray();
			output.close();
			gzip.close();
			input.close();
			return decompressed;
		}
	}
}
