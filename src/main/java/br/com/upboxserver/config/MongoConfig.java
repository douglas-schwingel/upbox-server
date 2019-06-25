package br.com.upboxserver.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

//    @Bean
//    public MongoClient mongoClient() {
//        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
//        UsuarioCodec usuarioCodec = new UsuarioCodec(codec);
//
//        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
//                CodecRegistries.fromCodecs(usuarioCodec));
//
//        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
//        return new MongoClient("localhost:27017", options);
//    }
}
