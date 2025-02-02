package com.microsoft.azure.spring.chatgpt.sample.cli;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.search.documents.SearchClient;
import com.azure.search.documents.SearchClientBuilder;
import com.microsoft.azure.spring.chatgpt.sample.common.vectorstore.AzureCognitiveSearchVectorStore;
import com.microsoft.azure.spring.chatgpt.sample.common.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// This class is provided as an example of how to configure the Azure Cognitive Search vector store.
@Configuration
@ConditionalOnProperty(name = "vector-store.type", havingValue = "azure-search")
public class AzSearchConfig {

    @Value("${azure.cognitive-search.endpoint}")
    private String acsEndpoint;

    @Value("${azure.cognitive-search.api-key}")
    private String acsApiKey;

    @Value("${azure.cognitive-search.index}")
    private String acsIndexName;

    // This bean is only created if the property vector-store.type is set to azure-search.
    @Bean
    public VectorStore vectorStore() {
        final SearchClient searchClient = new SearchClientBuilder()
                .endpoint(acsEndpoint)
                .credential(new AzureKeyCredential(acsApiKey))
                .indexName(acsIndexName)
                .buildClient();
        return new AzureCognitiveSearchVectorStore(searchClient);
    }
}
