
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class Hello {
    public static void main(String[] args) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        String line = "";
        String result = "";

        String authKey = "GTwtSLVSNTgtAXoOO18wRlTRl6s2ZnbH";
        String searchDate = "20220704";
        String dataType = "AP01";
        String apiURL =  "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey="
                +authKey+ "&searchdate="+searchDate+"&data="+dataType;

        try{
            URL url = new URL(apiURL);
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
            while((line = bf.readLine())!=null){
                result = result.concat(line);
            }

            JSONArray parseArray = (JSONArray)parser.parse(result);
            for (Object o : parseArray) {
                JSONObject obj = (JSONObject) o;
                String cur_unit = (String) obj.get("cur_unit");    //통화코드
                String cur_nm = (String) obj.get("cur_nm");    //국가/통화명
                String ttb = (String) obj.get("ttb");    //송금 받으실때
                String tts = (String) obj.get("tts");    //송금 보내실때
                String deal_bas_r = (String) obj.get("deal_bas_r");    //매매 기준율
                String bkpr = (String) obj.get("bkpr");    //장부가격
                String yy_efee_r = (String) obj.get("yy_efee_r");    //년환가료율
                String ten_dd_efee_R = (String) obj.get("ten_dd_efee_R");    //10일환가료율
                String kftc_deal_bas_r = (String) obj.get("kftc_deal_bas_r");    //서울외국환중계 매매기준율
                String kftc_bkpr = (String) obj.get("kftc_bkpr");    //서울외국환중계 장부가격

                StringBuffer sb = new StringBuffer();
                sb.append("통화코드: ").append(cur_unit)
                        .append(" 국가: ").append(cur_nm)
                        .append(", 송금 받을때: ").append(ttb)
                        .append(" 송금 보낼때: ").append(tts)
                        .append(" 매매 기준율: ").append(deal_bas_r)
                        .append(" 장부가격: ").append(bkpr)
                        .append(" 년환가료율: ").append(yy_efee_r)
                        .append(" 10일환가료율: ").append(ten_dd_efee_R)
                        .append(" 서울외국환중개매매기준율: ").append(kftc_deal_bas_r)
                        .append(" 서울외국환중개장부가격: ").append(kftc_bkpr)
                        .append(" ");
                System.out.println(sb.toString());
            }
            bf.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
