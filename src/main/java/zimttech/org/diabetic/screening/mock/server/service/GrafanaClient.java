package zimttech.org.diabetic.screening.mock.server.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class GrafanaClient {
    private static final String GRAFANA_URL = "http://grafana:3000/api/datasources/proxy/1/write";
    private static final String GRAFANA_API_KEY = "eyJrIjoiOGgzZHV4UnQyd0l4NDBKZ3FjaE91MGM5bFFGQ2JTV0EiLCJuIjoiemltdHRlY2giLCJpZCI6MX0=";

    public void pushDataToGrafana(String metricName, List<Map<String, Object>> data) {
        // Create the HTTP client and request
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(GRAFANA_URL);

        // Set the headers and body of the request
        httpPost.setHeader("Authorization", "Bearer " + GRAFANA_API_KEY);
        httpPost.setHeader("Content-Type", "application/json");
        String requestBody = createRequestBody(metricName, data);
        httpPost.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

        // Execute the request and handle the response
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode >= 300) {
                throw new RuntimeException("Failed to push data to Grafana: " + response.getStatusLine().getReasonPhrase());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to push data to Grafana", e);
        }
    }

    private String createRequestBody(String metricName, List<Map<String, Object>> data) {
        StringBuilder requestBodyBuilder = new StringBuilder();
        for (Map<String, Object> dataMap : data) {
            long timestamp = ((LocalDateTime) dataMap.get("date")).toEpochSecond(ZoneOffset.UTC);
            String vitalSignType = dataMap.get("vital_sign_type").toString();
            String value = (String) dataMap.get("value");
            requestBodyBuilder.append(metricName).append(" ").append(vitalSignType).append(" ").append(value).append(" ").append(timestamp).append("\n");
        }
        return requestBodyBuilder.toString();
    }
}
