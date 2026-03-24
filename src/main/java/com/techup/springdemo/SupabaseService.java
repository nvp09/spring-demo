package com.techup.springdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class SupabaseService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.bucket}")
    private String bucket;

    @Value("${supabase.apiKey}")
    private String apiKey;

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucket + "/" + fileName;

            URL url = new URL(uploadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setRequestProperty("Content-Type", file.getContentType());

            try (OutputStream os = conn.getOutputStream()) {
                os.write(file.getBytes());
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + fileName;
            } else {
                throw new RuntimeException("Upload failed: " + responseCode);
            }

        } catch (Exception e) {
            throw new RuntimeException("Upload error: " + e.getMessage());
        }
    }
}