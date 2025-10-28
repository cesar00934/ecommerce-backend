package com.ecommerce.backend.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.net.URI;
import java.net.http.*;
import java.util.UUID;
import java.io.InputStream;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
@Service
public class SupabaseStorageService {

  @Value("${supabase.url}")
  private String supabaseUrl;

  @Value("${supabase.service-role-key}")
  private String serviceRoleKey;

  @Value("${supabase.storage.bucket}")
  private String bucket;

  private final HttpClient httpClient = HttpClient.newHttpClient();

  /**
   * Sube un archivo y retorna la URL pública (o ruta pública).
   * Requiere SERVICE_ROLE_KEY para operaciones seguras.
   */


// ...

public String uploadFile(String filename, InputStream content, String contentType, long contentLength) throws Exception {
  // safe filename: UUID + '-' + original filename, pero URL-encoded para evitar espacios
  String rawObjectPath = UUID.randomUUID() + "-" + filename;
  String encodedObjectPath = URLEncoder.encode(rawObjectPath, StandardCharsets.UTF_8.toString());

  // subir a la ruta "object/{bucket}/{rawObjectPath}" (Supabase acepta el cuerpo PUT)
  String url = supabaseUrl + "/storage/v1/object/" + bucket + "/" + encodedObjectPath;

  HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(url))
      .header("Authorization", "Bearer " + serviceRoleKey)
      .header("Content-Type", contentType)
      .method("PUT", BodyPublishers.ofInputStream(() -> content))
      .build();

  HttpResponse<String> res = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
  System.out.println("Supabase upload response code: " + res.statusCode());
  System.out.println("Supabase upload response body: " + res.body());
  if (res.statusCode() >= 200 && res.statusCode() < 300) {
    // Public URL: CODIFICAR el path para la URL pública también
    String publicPath = URLEncoder.encode(rawObjectPath, StandardCharsets.UTF_8.toString());
    return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + publicPath;
  } else {
    throw new RuntimeException("Error al subir a Supabase: " + res.body());
  }
}

}
