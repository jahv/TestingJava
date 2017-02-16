package jahv.java.testing.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

/**
 * Created by ahernandez on 12/14/16.
 */
public class HttpURLConnectionExample {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty("hostURL", "http://int04-edi.int.skytouch.io:8989/pm");
//        properties.setProperty("hostURL", "http://localhost:8989/pm");
        properties.setProperty("userName", "Skytouch");
        properties.setProperty("password", "100webNOapps");
        HttpURLConnection connection = createHttpConnection(properties);
        sendHttpRequest(connection, "request");
        String response = getHttpResponse(connection);

    }

    private static HttpURLConnection createHttpConnection(Properties properties) {
        HttpURLConnection connection = null;
        try {
            String hostUrl = properties.getProperty("hostURL");
            int connectionTimeout = 3000;
            if(properties.getProperty("connectionTimeout") != null){
                connectionTimeout = Integer.parseInt(properties.getProperty("connectionTimeout"));
            }
            String userName = properties.getProperty("userName");
            String password = properties.getProperty("password");

            Proxy proxy = Proxy.NO_PROXY;

            URL url = new URL(hostUrl);
            connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setConnectTimeout(connectionTimeout);
            if(password!=null && password.length()>1 && userName!=null && userName.length() > 1){
                String userPassword = userName + ":" + password;
                @SuppressWarnings("restriction")
                String userPasswordEncoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
                connection.setRequestProperty("Authorization", "Basic " + userPasswordEncoding);
            }
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
        } catch (ConnectException e) {
            System.out.println("ConnectException : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }
        return connection;
    }

    private static void sendHttpRequest(HttpURLConnection httpURLConnection, String requestString) {
        boolean err = false;
        OutputStreamWriter outStream = null;
        OutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            outputStream = httpURLConnection.getOutputStream();
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            outStream = new OutputStreamWriter(bufferedOutputStream);
            outStream.write(requestString);
            outStream.flush();
        } catch (IOException e) {
            err = true;
            System.out.println("error while writing request to URL: " + e);
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    if (!err)
                        System.out.println("IO Exception - Error while closing request writer to URL " + e);
                }
            }
        }
    }

    private static String getHttpResponse(HttpURLConnection connection) {
        String response = "";
        boolean err = false;
        StringBuilder responseBuilder = new StringBuilder();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader inputReader = null;
        try {
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            inputReader = new BufferedReader(inputStreamReader);
            String inputline = inputReader.readLine();

            while (inputline != null) {
                responseBuilder.append(inputline);
                inputline = inputReader.readLine();
            }
            response = responseBuilder.toString();
        } catch (IOException ioe) {
            System.out.println("IOException : " + ioe);
            err = true;
            try {
                InputStream errStream = connection.getErrorStream();
                if (errStream != null) {
                    inputReader = new BufferedReader(new InputStreamReader(errStream));
                    String inputline = inputReader.readLine();
                    while (inputline != null) {
                        responseBuilder.append(inputline);
                        inputline = inputReader.readLine();
                    }
                    response = responseBuilder.toString();
                }
            } catch (IOException e) {
                System.out.println("IO Exception - Error while reading err stream of response from URL:");
                response = "Error while reading err stream of response from URL: " + connection.toString();
                System.out.println(response);
                System.out.println(e);
            }
            System.out.println("IO Exception - Error while getting response from domain at URL:" + ioe);
        } finally {
            try {
                if(inputReader != null) {
                    inputReader.close();
                }
            } catch (IOException ioe) {
                if (!err) {
                    System.out.println("IO Exception - Error while closing stream of response from domain at URL:");
                    response = new StringBuilder(".Error while closing stream of response from domain at URL: ").append(connection.toString()).toString();
                }
            }
        }
        return response;
    }


}
