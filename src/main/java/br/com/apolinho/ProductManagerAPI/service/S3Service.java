package br.com.apolinho.ProductManagerAPI.service;

import br.com.apolinho.ProductManagerAPI.model.Product;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class S3Service {

    private final AmazonS3 s3Client;
    private final String bucketName;

    public S3Service(@Value("${aws.access.key}") String accessKey,
                     @Value("${aws.secret.key}") String secretKey,
                     @Value("${aws.region}") String region,
                     @Value("${s3.bucket.name}") String bucketName) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
        this.bucketName = bucketName;
    }

    public void uploadProductData(Product product) throws IOException {
        File file = new File(product.getId() + ".json");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(product.toString());
        }
        s3Client.putObject(new PutObjectRequest(bucketName, file.getName(), file));
    }
}
