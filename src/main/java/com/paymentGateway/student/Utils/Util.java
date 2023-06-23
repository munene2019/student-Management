package com.paymentGateway.student.Utils;
import com.paymentGateway.student.DTO.CustomResponse;
import com.paymentGateway.student.DTO.CustomStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import static org.springframework.data.domain.Sort.by;

public class Util<T extends Object> {


    static Logger logger = LoggerFactory.getLogger(Util.class.getName());

    public static String getTransactionTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }
    public static String getStkPushPassword(String shortCode, String passKey, String timestamp) {
        String concatenatedString = String.format("%s%s%s", shortCode, passKey, timestamp);
        return toBase64String(concatenatedString);
    }
    public static String toBase64String(String value) {
        byte[] data = value.getBytes(StandardCharsets.ISO_8859_1);
        return Base64.getEncoder().encodeToString(data);
    }
//    public static ResponseEntity<Object> getImageErrorResponse() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//        byte[] errorResponse = Util.toJson(CustomStatus.strip(Translator.toLocale(ResponseMessage.IMAGE_NOT_FOUND))).getBytes();
//
//        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(Util.convertingByteArrayToByteArrayInputStream(errorResponse)));
//
//
//    }

    public static HttpStatus httpCodeMapping(HttpStatus httpStatus, int httpCode) {

        if (httpCode == 200) {
            httpStatus = HttpStatus.OK;
        } else if (httpCode == 202) {
            httpStatus = HttpStatus.ACCEPTED;
        } else if (httpCode == 401) {
            httpStatus = HttpStatus.UNAUTHORIZED;
        } else if (httpCode == 403 || httpCode == 411) {
            httpStatus = HttpStatus.FORBIDDEN;
        } else if (httpCode == 500) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (httpCode == 404) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (httpCode == 400) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return httpStatus;

    }

    public static ResponseEntity<?> getResponse(CustomResponse response) {
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.resolve(response.getCode()) != null ? HttpStatus.valueOf(response.getCode()) : HttpStatus.BAD_REQUEST);
        return builder.body(response.getData() != null ? response : CustomStatus.map(response.isStatus(), response.getCode(), response.getMessage()));
    }


    public static Pageable getPageable(Integer page, Integer size, String direction, String orderBy) {
        Sort sort;
        if (direction.equalsIgnoreCase("ASC")) {
            sort = by(Direction.ASC, orderBy);
        } else {
            sort = by(Direction.DESC, orderBy);
        }
        return PageRequest.of(page, size, sort);
    }



       public static String decodeString(String str) {
        String encodedContent = "";
        try {
            encodedContent = URLDecoder.decode(str, "UTF-8");
        } catch (Exception exception) {
            exception.getMessage();
        }
        return encodedContent;
    }

    public static String encodeString(String str) {
        String encodedContent = "";
        try {
            encodedContent = URLEncoder.encode(str, "UTF-8");
        } catch (Exception exception) {
            exception.getMessage();
        }
        return encodedContent;
    }



    public static boolean checkRequest(String request) {
        if (request == null) {
            return false;
        } else if (request.isEmpty()) {
            return false;
        } else if (request.equalsIgnoreCase("string")) {
            return false;
        } else {
            return true;
        }
    }

    public static HashMap<String, Date> getLastMonths(int mothCount) {
        HashMap<String, Date> dateHashMap = new HashMap<>();

        LocalDate localDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        Date date = convertToDateViaInstant(localDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -mothCount + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date prevDate = calendar.getTime();
        dateHashMap.put("today", date);
        dateHashMap.put("prevDate", prevDate);

        return dateHashMap;

    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }




    public static byte[] convertBase64ByteArray(String rawString) {
        String[] base64String = convertStringToArray(rawString);
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(base64String[1]);
        return data;
    }

    public static String[] convertStringToArray(String base64String) {
        String[] stringArr = base64String.split(",");
        return stringArr;
    }

    public static String convertBytesToBase64(byte[] fileBytes) {
        return Base64.getEncoder().encodeToString(fileBytes);
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        Date date = null;
        if (localDateTime != null) {
            Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            date = Date.from(instant);
        }
        return date;
    }

    public static String getFileName(String base64String) {
        String[] strings = convertStringToArray(base64String);
        String extension;
        String fileName = "";
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            case "data:image/gif;base64":
                extension = "gif";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        fileName = getTimestamp() + "." + extension;
        return fileName;
    }

    //Create image URL
    public static String getImageURL(String baseURL, String imageName) {
        return baseURL + "/api/v1/documents/image/" + imageName;
    }

    private static boolean isMatch(byte[] pattern, byte[] data) {
        if (pattern.length <= data.length) {
            for (int idx = 0; idx < pattern.length; ++idx) {
                if (pattern[idx] != data[idx])
                    return false;
            }
            return true;
        }

        return false;
    }



    public static Boolean checkAllowedMimeTypes(String mimeType) {

        Set<String> allowedMimeTypes = new HashSet<>();
        allowedMimeTypes.add("image/png");
        allowedMimeTypes.add("image/jpg");
        allowedMimeTypes.add("image/jpeg");
        allowedMimeTypes.add("image/gif");
        allowedMimeTypes.add("image/bmp");
        allowedMimeTypes.add("image/tif");
        allowedMimeTypes.add("application/pdf");
        allowedMimeTypes.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document"); //.docx
        allowedMimeTypes.add("application/msword"); //.doc
        allowedMimeTypes.add("application/vnd.openxmlformats-officedocument.wordprocessingml.template"); //.dotx
        allowedMimeTypes.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //.xlsx
        allowedMimeTypes.add("application/vnd.ms-excel"); //.xls
        allowedMimeTypes.add("application/vnd.ms-powerpoint"); //.ppt
        allowedMimeTypes.add("application/vnd.openxmlformats-officedocument.presentationml.presentation"); //.pptx
        return allowedMimeTypes.contains(mimeType);
    }


    public static String getFileExtension(String mimeType) {
        String extension = "";
        switch (mimeType) {
            case "image/png":
                extension = "png";
                break;
            case "image/jpg":
                extension = "jpg";
                break;
            case "image/jpeg":
                extension = "jpeg";
                break;
            case "image/gif":
                extension = "gif";
                break;
            case "image/bmp":
                extension = "bmp";
                break;
            case "image/tif":
                extension = "tif";
                break;
            case "application/pdf":
                extension = "pdf";
                break;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                extension = "docx";
                break;
            case "application/msword":
                extension = "doc";
                break;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.template":
                extension = "dotx";
                break;
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                extension = "xls";
                break;

            case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
                extension = "pptx";
                break;
            case "application/vnd.ms-powerpoint":
                extension = "ppt";
                break;

            case "application/vnd.ms-excel":
                extension = "xlsx";
                break;
            default:
                break;
        }

        return extension;
    }

    public static int getFileSize(byte[] b) {
        int n = b.length;
        return (n * 4 + 2) / 3;
    }

    public static String getFileExtensionCategory(String mimeType) {
        String category = "";
        switch (mimeType) {
            case "image/png":
            case "image/jpg":
            case "image/jpeg":
            case "image/gif":
            case "image/bmp":
            case "image/tif":
                category = "image";
                break;

            case "application/pdf":
                category = "pdf";
                break;
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
            case "application/msword":
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.template":
                category = "doc";
                break;
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
            case "application/vnd.ms-excel":
                category = "excel";
                break;
            case "application/vnd.ms-powerpoint":
            case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
                category = "ppt";

            default:
                break;
        }

        return category;
    }


    public static String getImageType(byte[] data) {

        final byte[] pngPattern = new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};
        final byte[] jpgPattern = new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF};
        final byte[] gifPattern = new byte[]{0x47, 0x49, 0x46, 0x38};
        final byte[] bmpPattern = new byte[]{0x42, 0x4D};
        final byte[] tiffLEPattern = new byte[]{0x49, 0x49, 0x2A, 0x00};
        final byte[] tiffBEPattern = new byte[]{0x4D, 0x4D, 0x00, 0x2A};
        if (isMatch(pngPattern, data))
            return "image/png";

        if (isMatch(jpgPattern, data))
            return "image/jpg";

        if (isMatch(gifPattern, data))
            return "image/gif";

        if (isMatch(bmpPattern, data))
            return "image/bmp";

        if (isMatch(tiffLEPattern, data))
            return "image/tif";

        if (isMatch(tiffBEPattern, data))
            return "image/tif";

        return "image/png";
    }


    public static CustomStatus stripMessage(String message) {
        System.out.println("Results "+message.toString());

        String[] codeMessage = message.split(":", 2);
        if(codeMessage.length==2){
            String code = codeMessage[0];
            String msg = codeMessage[1];
            return new CustomStatus(Integer.parseInt(code), msg);
        }

        return new CustomStatus(200, message);
    }



    public static String appendTimestampToFileName(String name) {
        return String.format("%s_%s", name.replaceAll(" ", "_"), getTimestamp());
    }

    public static String getTimestamp() {
        final String TIMESTAMP_PATTERN = "yyyyMMddHHmmss";
        SimpleDateFormat formatter = new SimpleDateFormat(TIMESTAMP_PATTERN);
        return formatter.format(new Date());
    }


    public static String getDataString(HashMap<String, Object> params) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("\n");
            }
            result.append(entry.getKey());
            result.append(":");
            result.append(entry.getValue());
        }

        logger.info("Data String :" + result);
        return result.toString();

    }





    public String getDisplayDateTime(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh.mm.ss aa");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EAT"));
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String getReportDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("EAT"));
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String getIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

      public static Date getStartDate(Date startDate) {
        if (startDate != null) {
            return startDate;
        } else {
            return new Date(0);  //1970
        }

    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        if (localDate != null) {
            //default time zone
            ZoneId defaultZoneId = ZoneId.systemDefault();
            //local date + atStartOfDay() + default time zone + toInstant() = Date
            return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        }
        return null;

    }

    public static String getLastWordsOfString(int charCount, String myString) {
        if (myString.length() > charCount)
            return myString.substring(myString.length() - charCount);
        else
            return myString;
    }


    public static String codeLogic(String str) {
        String workedString = str.replaceAll("\\s", "");
        return workedString.toUpperCase();
    }

    public static ByteArrayInputStream convertingByteArrayToByteArrayInputStream(byte[] fileContent) {
        return new ByteArrayInputStream(fileContent);
    }

    public static synchronized String getUniqueStringEveryTime() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String datetime = ft.format(dNow);
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datetime;
    }



	public static String createPageableStringUrl(Integer page, Integer size, String direction, String orderBy) {

		StringBuilder sb = new StringBuilder();
		if (page != null) {
			sb.append("page=").append(page);
		}

		if (size != null) {
			if (sb.toString().length() > 0) {
				sb.append("&").append("size=").append(size);
			} else {
				sb.append("size=").append(size);
			}
		}

		if (direction != null && !direction.isEmpty()) {
			if (sb.toString().length() > 0) {
				sb.append("&").append("direction=").append(direction);
			} else {
				sb.append("direction=").append(direction);
			}
		}

		if (orderBy != null && !orderBy.isEmpty()) {
			if (sb.toString().length() > 0) {
				sb.append("&").append("orderBy=").append(orderBy);
			} else {
				sb.append("orderBy=").append(orderBy);
			}
		}

		return sb.toString().length() > 0 ? "?".concat(sb.toString()) : sb.toString();
	}

    public static byte[] getByteFromFile(File file)
            throws IOException
    {

        // Creating an object of FileInputStream to
        // read from a file
        FileInputStream fl = new FileInputStream(file);

        // Now creating byte array of same length as file
        byte[] arr = new byte[(int)file.length()];

        // Reading file content to byte array
        // using standard read() method
        fl.read(arr);

        // lastly closing an instance of file input stream
        // to avoid memory leakage
        fl.close();

        // Returning above byte array
        return arr;
    }
}
