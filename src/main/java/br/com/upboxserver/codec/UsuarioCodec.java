package br.com.upboxserver.codec;

import br.com.upboxserver.exception.UsuarioException;
import br.com.upboxserver.models.Usuario;
import org.bson.*;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.UUID;

public class UsuarioCodec implements CollectibleCodec<Usuario> {

    private Codec<Document> codec;

    public UsuarioCodec(Codec<Document> codec) {
        this.codec = codec;
    }


    @Override
    public Usuario generateIdIfAbsentFromDocument(Usuario usuario) {
        return documentHasId(usuario) ? usuario.criarId() : usuario;
    }

    @Override
    public boolean documentHasId(Usuario usuario) {
        return usuario.getId() == null;
    }

    @Override
    public BsonValue getDocumentId(Usuario usuario) {
        if(!documentHasId(usuario)) throw new UsuarioException("Usuário não tem ID");
        return new BsonString(usuario.getId().toHexString());
    }

    @Override
    public Usuario decode(BsonReader reader, DecoderContext decoderContext) {
        Document document = codec.decode(reader, decoderContext);
        return populaUsuario(document);
    }

    @Override
    public void encode(BsonWriter writer, Usuario usuario, EncoderContext encoderContext) {
        codec.encode(writer, criaDocument(usuario), encoderContext);
    }

    //    Métodos auxiliares - Não fazem parte da interface CollectibleCodec

    private Usuario populaUsuario(Document document) {
        Usuario usuario = new Usuario();
        usuario.setId(document.getObjectId("_id"));
        usuario.setUuid(UUID.fromString(document.getString("uuid")));
        usuario.setNome(document.getString("nome"));
        usuario.setEmail(document.getString("email"));
        usuario.setUsername(document.getString("username"));
        usuario.setSenha(document.getString("senha"));
        usuario.setArquivosCompartilhados((List<Document>) document.get("compartilhadosComigo"));
        return usuario;
    }

    private Document criaDocument(Usuario usuario) {
        Document document = new Document();
        document.put("_id", usuario.getId());
        document.put("uuid", usuario.getUuid().toString());
        document.put("nome", usuario.getNome());
        document.put("email", usuario.getEmail());
        document.put("username", usuario.getUsername());
        document.put("compartilhadosComigo", usuario.getArquivosCompartilhados());
        String salto = BCrypt.gensalt();
        String senhaHash = BCrypt.hashpw(usuario.getSenha(), salto);
        document.put("senha", senhaHash);
        return document;
    }

    @Override
    public Class<Usuario> getEncoderClass() {
        return Usuario.class;
    }
}
