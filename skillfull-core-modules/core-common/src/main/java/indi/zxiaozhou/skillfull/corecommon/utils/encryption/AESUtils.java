// +----------------------------------------------------------------------
// | SkillFull快速开发平台 [ SkillFull ]
// +----------------------------------------------------------------------
// | 版权所有 2020~2021 zxiaozhou
// +----------------------------------------------------------------------
// | 官方网站: https://www.divisu.com
// +----------------------------------------------------------------------
// | 作者: zxiaozhou <z7630853@163.com>
// +----------------------------------------------------------------------
package indi.zxiaozhou.skillfull.corecommon.utils.encryption;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


/**
 * AES Utils
 *
 * @author zhouxuanhong
 * @date 2019-10-23 14:07
 * @since JDK11
 */
@Slf4j
public class AESUtils {
    private static final Integer KEY_LENGTH = 128;

    /**
     * 获取256位AES加密密钥
     *
     * @return String {@link String} 256位加密密钥
     * @author zhouxuanhong
     * @date 2019-10-23 14:07
     */
    public static String getKey() {
        return getUUID();
    }


    /**
     * 明文加密
     *
     * @param key       {@link String} 密钥
     * @param plaintext {@link String} 明文
     * @return String {@link String} 密文
     * @author zhouxuanhong
     * @date 2019-10-23 14:07
     */
    public static String encrypt(String key, String plaintext) {
        return Base64.encodeBase64String(encryptToBytes(key, plaintext));
    }


    /**
     * 明文加密为byte数组
     *
     * @param key       {@link String} 密钥
     * @param plaintext {@link String} 明文
     * @return byte[]-密文数组
     * @author zhouxuanhong
     * @date 2019-10-23 14:07
     */
    private static byte[] encryptToBytes(String key, String plaintext) {
        byte[] ciphertextByte = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(KEY_LENGTH);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            ciphertextByte = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("----------AESUtils---------->明文加密为byte数组异常:{}", e.getMessage());
        }
        return ciphertextByte;
    }


    /**
     * 密文解密
     *
     * @param key        {@link String} 密文
     * @param ciphertext {@link String} 密文
     * @return String {@link String} 明文
     * @author zhouxuanhong
     * @date 2019-10-23 14:09
     */
    public static String decrypt(String key, String ciphertext) {
        return decryptByBytes(key, Base64.decodeBase64(ciphertext));
    }


    /**
     * 密文数组解密为明文字符串
     *
     * @param key                 {@link String} 密钥
     * @param ciphertextByte-密文数组
     * @return String {@link String} 明文
     * @author zhouxuanhong
     * @date 2019-10-23 14:07
     */
    private static String decryptByBytes(String key, byte[] ciphertextByte) {
        String plaintext = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(KEY_LENGTH);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            byte[] decryptBytes = cipher.doFinal(ciphertextByte);
            plaintext = new String(decryptBytes);
        } catch (Exception e) {
            log.error("----------AESUtils---------->密文byte数组解密异常:{}", e.getMessage());
        }
        return plaintext;
    }


    /**
     * 获取16位随机字符串
     *
     * @return String
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        char[] cs = new char[32];
        char c = 0;
        for (int i = uuid.length() / 2, j = 1; i-- > 0; ) {
            if ((c = uuid.charAt(i)) != '-') {
                cs[j++] = c;
            }
        }
        String uid = String.valueOf(cs);
        return uid.trim();
    }
}