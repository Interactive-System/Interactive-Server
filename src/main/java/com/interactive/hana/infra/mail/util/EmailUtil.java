package com.interactive.hana.infra.mail.util;

import com.interactive.hana.infra.mail.domain.EmailSubject;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String toAddress, EmailSubject subject, String body) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(toAddress);
            helper.setSubject(subject.getSubject());
            helper.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

    public String getEmailAuthMessage(String email, String authCode) {
        return "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <div style=\"max-width:520px;margin:0 auto\">\n" +
                "                        <div\n" +
                "                            style=\"vertical-align:top;text-align:left;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px\">\n" +
                "                            <div style=\"padding-top:0px;padding-bottom:0px;vertical-align:top;text-align:center\"><a\n" +
                "                                    href=\"https://www.google.com\" target=\"_blank\"\n" +
                "                                    data-saferedirecturl=\"https://www.google.com/\"><img\n" +
                "                                        src=\"https://lh3.googleusercontent.com/d/10KqwpIA0I84HChT31fzbaKR6UEMDb-40\"\n" +
                "                                        height=\"150\" alt=\"InteractiveHana logo\" style=\"border:0\" class=\"CToWUd\"></a></div>\n" +
                "                            <hr style=\"margin-top:0px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <h2\n" +
                "                                style=\"margin-bottom:0;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:20px;font-weight:500;color:#172b4d;line-height:24px;margin-top:28px\">\n" +
                "                                <span>Hana</span> 회원가입 인증 메일</h2>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                안녕하세요.</p>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                회원가입을 완료하시려면 아래의 <b>이메일 인증</b>을 클릭하세요.</p>\n" +
                "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"center\">\n" +
                "                                            <a style=\"text-decoration: none; font-size: 20px; color: #011627\"\n" +
                "                                                href='http://13.124.207.110:8080/api/v1/email-auth?email=" + email + "&authCode=" + authCode + "'>이메일 인증</a>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <hr style=\"margin-top:24px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <div style=\"color:#707070;font-size:13px;line-height:19px;text-align:center;margin-top:10px\">\n" +
                "                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#ffffff\"\n" +
                "                                    align=\"center\">\n" +
                "                                    <tbody>\n" +
                "                                        <tr>\n" +
                "                                            <td valign=\"top\" align=\"center\"\n" +
                "                                                style=\"padding-top:10px;line-height:18px;text-align:center;font-weight:none;font-size:12px;color:#505f79\">\n" +
                "                                                <span>이 메시지는 Hana에서 전송되었습니다.</span><br></td>\n" +
                "                                        </tr>\n" +
                "                                        <tr valign=\"top\">\n" +
                "                                            <td align=\"center\" style=\"padding-top:15px;padding-bottom:30px\"><a\n" +
                "                                                    href=\"https://www.dogfoot.com\" target=\"_blank\"\n" +
                "                                                    data-saferedirecturl=\"https://www.google.com\"><img\n" +
                "                                                        src=\"https://lh3.googleusercontent.com/d/10KqwpIA0I84HChT31fzbaKR6UEMDb-40\"\n" +
                "                                                        width=\"50\" border=\"0\" alt=\"Atlassian\"\n" +
                "                                                        style=\"display:block;color:#4c9ac9\" class=\"CToWUd\"></a></td>\n" +
                "                                        </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>";
    }


    public String getFindEmailMessage(String email) {
        return "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <div style=\"max-width:520px;margin:0 auto\">\n" +
                "                        <div\n" +
                "                            style=\"vertical-align:top;text-align:left;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px\">\n" +
                "                            <div style=\"padding-top:0px;padding-bottom:0px;vertical-align:top;text-align:center\"><a\n" +
                "                                    href=\"https://www.google.com\" target=\"_blank\"\n" +
                "                                    data-saferedirecturl=\"https://www.google.com/\"><img\n" +
                "                                        src=\"https://lh3.googleusercontent.com/pw/ACtC-3c04Gv-k45cMqMbwSpHLtBOXanwyyU4NJ7CCEImXO7Ea5W27F0KzqWm7ClWu96vPF1F9e9VWs4VfS-zqMY5EOwvi1hySnsMPuDA7I-bmfO4k9wb0o3CrCWE0uzCTaHVW7H6nW2oXvo5Irn8J9Acn98=w1280-h720-no?authuser=4\"\n" +
                "                                        height=\"150\" alt=\"InteractiveHana logo\" style=\"border:0\" class=\"CToWUd\"></a></div>\n" +
                "                            <hr style=\"margin-top:0px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <h2\n" +
                "                                style=\"margin-bottom:0;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:20px;font-weight:500;color:#172b4d;line-height:24px;margin-top:28px\">\n" +
                "                                <span>InteractiveHana</span> 이메일 찾기 안내 메일</h2>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                안녕하세요.</p>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                이메일 찾기 안내 드립니다. 아래의 <b>이메일</b>을 통해 로그인해주세요.</p>\n" +
                "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"center\">\n" +
                "                                            <span style=font-size: 20px; color: #011627>" + email + "</span>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <hr style=\"margin-top:24px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <div style=\"color:#707070;font-size:13px;line-height:19px;text-align:center;margin-top:10px\">\n" +
                "                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#ffffff\"\n" +
                "                                    align=\"center\">\n" +
                "                                    <tbody>\n" +
                "                                        <tr>\n" +
                "                                            <td valign=\"top\" align=\"center\"\n" +
                "                                                style=\"padding-top:10px;line-height:18px;text-align:center;font-weight:none;font-size:12px;color:#505f79\">\n" +
                "                                                <span>이 메시지는 InteractiveHana에서 전송되었습니다.</span><br></td>\n" +
                "                                        </tr>\n" +
                "                                        <tr valign=\"top\">\n" +
                "                                            <td align=\"center\" style=\"padding-top:15px;padding-bottom:30px\"><a\n" +
                "                                                    href=\"https://www.DogFoot.com\" target=\"_blank\"\n" +
                "                                                    data-saferedirecturl=\"https://www.google.com\"><img\n" +
                "                                                        src=\"https://lh3.googleusercontent.com/pw/ACtC-3e3OsSdKKOWeKcrnQOrArwgoAGuAVg5ZUukTz-h7wXQTucQZDxKJPOuRRxPGZeV7XeQsljC9nJ7I9SlKaSmpKM5jmG1f-WKBEmbPbhauwheNbKVUaEPcRnLu0MWY--WgMZZbxjz2m3wO9WMO28UfT4=s1182-no?authuser=0\"\n" +
                "                                                        width=\"40\" border=\"0\" alt=\"Atlassian\"\n" +
                "                                                        style=\"display:block;color:#4c9ac9\" class=\"CToWUd\"></a></td>\n" +
                "                                        </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>";
    }

    public String getFindPasswordMessage(String email, String authCode) {
        return "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td align=\"center\">\n" +
                "                    <div style=\"max-width:520px;margin:0 auto\">\n" +
                "                        <div\n" +
                "                            style=\"vertical-align:top;text-align:left;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px\">\n" +
                "                            <div style=\"padding-top:0px;padding-bottom:0px;vertical-align:top;text-align:center\"><a\n" +
                "                                    href=\"https://www.google.com\" target=\"_blank\"\n" +
                "                                    data-saferedirecturl=\"https://www.google.com/\"><img\n" +
                "                                        src=\"https://lh3.googleusercontent.com/pw/ACtC-3c04Gv-k45cMqMbwSpHLtBOXanwyyU4NJ7CCEImXO7Ea5W27F0KzqWm7ClWu96vPF1F9e9VWs4VfS-zqMY5EOwvi1hySnsMPuDA7I-bmfO4k9wb0o3CrCWE0uzCTaHVW7H6nW2oXvo5Irn8J9Acn98=w1280-h720-no?authuser=4\"\n" +
                "                                        height=\"150\" alt=\"DogFoot logo\" style=\"border:0\" class=\"CToWUd\"></a></div>\n" +
                "                            <hr style=\"margin-top:0px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <h2\n" +
                "                                style=\"margin-bottom:0;font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:20px;font-weight:500;color:#172b4d;line-height:24px;margin-top:28px\">\n" +
                "                                <span>InteractiveHana</span> 임시 비밀번호 안내 메일</h2>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                안녕하세요.</p>\n" +
                "                            <p\n" +
                "                                style=\"font-family:-apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Fira Sans,Droid Sans,Helvetica Neue,sans-serif;font-size:14px;font-weight:400;color:#091e42;line-height:20px;margin-top:12px\">\n" +
                "                                임시 비밀번호 안내 드립니다. 아래의 <b>임시 비밀번호</b>를 통해 로그인해주세요.</p>\n" +
                "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                <tbody>\n" +
                "                                    <tr>\n" +
                "                                        <td align=\"center\">\n" +
                "                                            <span style=font-size: 20px; color: #011627>" + authCode + "</span>\n" +
                "                                        </td>\n" +
                "                                    </tr>\n" +
                "                                </tbody>\n" +
                "                            </table>\n" +
                "                            <hr style=\"margin-top:24px;margin-bottom:24px;border:0;border-bottom:1px solid #c1c7d0\">\n" +
                "                            <div style=\"color:#707070;font-size:13px;line-height:19px;text-align:center;margin-top:10px\">\n" +
                "                                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" bgcolor=\"#ffffff\"\n" +
                "                                    align=\"center\">\n" +
                "                                    <tbody>\n" +
                "                                        <tr>\n" +
                "                                            <td valign=\"top\" align=\"center\"\n" +
                "                                                style=\"padding-top:10px;line-height:18px;text-align:center;font-weight:none;font-size:12px;color:#505f79\">\n" +
                "                                                <span>이 메시지는 InteractiveHana에서 전송되었습니다.</span><br></td>\n" +
                "                                        </tr>\n" +
                "                                        <tr valign=\"top\">\n" +
                "                                            <td align=\"center\" style=\"padding-top:15px;padding-bottom:30px\"><a\n" +
                "                                                    href=\"https://www.DogFoot.com\" target=\"_blank\"\n" +
                "                                                    data-saferedirecturl=\"https://www.google.com\"><img\n" +
                "                                                        src=\"https://lh3.googleusercontent.com/pw/ACtC-3e3OsSdKKOWeKcrnQOrArwgoAGuAVg5ZUukTz-h7wXQTucQZDxKJPOuRRxPGZeV7XeQsljC9nJ7I9SlKaSmpKM5jmG1f-WKBEmbPbhauwheNbKVUaEPcRnLu0MWY--WgMZZbxjz2m3wO9WMO28UfT4=s1182-no?authuser=0\"\n" +
                "                                                        width=\"40\" border=\"0\" alt=\"Atlassian\"\n" +
                "                                                        style=\"display:block;color:#4c9ac9\" class=\"CToWUd\"></a></td>\n" +
                "                                        </tr>\n" +
                "                                    </tbody>\n" +
                "                                </table>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>";
    }

}
