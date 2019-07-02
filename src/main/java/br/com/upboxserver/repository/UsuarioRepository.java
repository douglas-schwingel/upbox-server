package br.com.upboxserver.repository;

import br.com.upboxserver.codec.UsuarioCodec;
import br.com.upboxserver.models.Usuario;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Douglas Schwingel
 * 
 */
@Repository
public class UsuarioRepository {
    private static final Logger logger = Logger.getLogger(UsuarioRepository.class.getName());
    private static final String PARAM_BUSCA = "username";
    private static final String URL_MONGO = "localhost:27017";
    private static final String MONGO_DB = "upbox";
    public static final String COMPARTILHADOS_COMIGO = "compartilhadosComigo";

    private MongoClient client;
    private MongoCollection<Usuario> collection;


    private void conecta() {
        Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
        UsuarioCodec usuarioCodec = new UsuarioCodec(codec);

        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromCodecs(usuarioCodec));

        MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();
        this.client = new MongoClient(URL_MONGO, options);
        this.collection = client.getDatabase(MONGO_DB).getCollection("usuarios", Usuario.class);
    }


    /**
     * @param usuario - usuario a ser salvo
     * @return Retorna um json do usuario ou null se o usuário já estiver cadastrado
     * @apiNote Salva um usuário no banco caso ainda não exista nenhum
     * usuário com o mesmo username.
     */
    public String salva(Usuario usuario) {
        Document document = populaJsonString(usuario);
        if (verificaSeUsuarioJaExiste(usuario.getUsername())) return document.toJson()      ;
        conecta();
        collection.insertOne(usuario);
        logger.log(Level.INFO, "Salvando no banco: " + usuario.getNome());
        client.close();
        return document.toJson();
    }

    /**
     * @param username - Nome do usuário a ser encontrado
     * @return O Usuario que possui o username passado ou null caso não exista
     * @apiNote Busca um usuário no banco de acordo com seu username
     */
    public String busca(String username) {
        conecta();
        MongoCursor<Usuario> cursor = collection.find(Filters.eq(PARAM_BUSCA, username), Usuario.class).iterator();
        if (cursor.hasNext()) {
            Usuario resultado = cursor.next();
            client.close();
            Document document = populaJsonString(resultado);
            return document.toJson();
        }
        logger.log(Level.INFO, "Usuário {0} não encontrado", username);
        client.close();
        return null;
    }

    /**
     * @param usuario - usuario a ser removido
     * @return Usuario deletado ou null caso não exista nenhum usuario com o mesmo username no banco
     * @apiNote Remove um usuário baseado no username passado
     */
    public String remove(Usuario usuario) {
        if (!verificaSeUsuarioJaExiste(usuario.getUsername())) {
            logger.log(Level.INFO, "Usuário {0} não existe.", usuario.getUsername());
            return null;
        }
        conecta();
        logger.log(Level.INFO, "Apagando usuário {0}", usuario.getUsername());
        Usuario resultado = collection.findOneAndDelete(Filters.eq(PARAM_BUSCA, usuario.getUsername()));
        client.close();
        Document document = populaJsonString(resultado);
        return document.toJson();
    }

    /**
     * @param username - Username do usuario que vai ser atualizado
     * @param usuario  - Usuario com os dados novos
     * @return Usuario com os dados novos.
     * @apiNote Atualiza um usuário do banco baseado em outro
     */
    public String atualiza(String username, Usuario usuario) {
        if (!verificaSeUsuarioJaExiste(username)) {
            logger.log(Level.INFO, "Usuário {0} não encontrado", username);
            return null;
        }
        conecta();
        Bson filter = new Document(PARAM_BUSCA, username);
        Bson uptade = verificaDadosParaAtualizacao(usuario);
        Usuario usuarioAntigo = collection.findOneAndUpdate(filter, uptade);
        logger.log(Level.INFO, "Atualizado: {0}", usuarioAntigo.getUsername());
        Document document = populaJsonString(usuario);
        client.close();
        return document.toJson();
    }
//
    /**
     *
     * @param nomeArquivo - Nome do arquivo que será compartilhado
     * @param owner - Dono do arquivo original
     * @param destinatario - Username de com quem será compartilhado o arquivo
     * @return True se a operação foi bem sucedida e false se houve um erro
     * @apiNote Salva no banco se um arquivo foi compartilhado
     */

    public boolean compartilha(String nomeArquivo, String owner, String destinatario) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put("owner", owner);
        basicDBObject.put("arquivo", nomeArquivo);
        BasicDBObject username = new BasicDBObject("username", destinatario);
        username.put(COMPARTILHADOS_COMIGO, basicDBObject);
        System.out.println(username.toJson());
        conecta();
        MongoCursor<Usuario> cursor = collection.find(username).iterator();
        if (cursor.hasNext()) {
            return false;
        }

        if (owner.equals(destinatario)) {
            return false;
        }
        Document document = new Document(COMPARTILHADOS_COMIGO, basicDBObject);
        Bson filter = new Document("username", destinatario);
        Document update = new Document("$push", document);
        UpdateResult updateResult = collection.updateOne(filter, update);
        client.close();
        return updateResult != null;
    }

    /**
     *
     * @param usuario - usuario que será verificado
     * @return Um Document do Mongo com os dados verificados
     */
    private Document verificaDadosParaAtualizacao(Usuario usuario) {
        Document document = new Document();

        if (usuario.getUsername() != null) document.put("username", usuario.getUsername());
        if (usuario.getUuid() != null) document.put("uuid", usuario.getUuid());
        if (usuario.getNome() != null) document.put("nome", usuario.getNome());
        if (usuario.getEmail() != null) document.put("email", usuario.getEmail());
        if (usuario.getSenha() != null) document.put("senha", usuario.getSenha());
        if (usuario.getId() != null) document.put("_id", usuario.getId());
        if (usuario.getArquivosCompartilhados() != null)
            document.put(COMPARTILHADOS_COMIGO, usuario.getArquivosCompartilhados());
        return new Document("$set", document);
    }


    //    Métodos auxiliares

    /**
     *
     * @param usuario - Usuario que será transformado em Json
     * @return Document com os dados do usuario que será trnsformado em Json
     */
    private Document populaJsonString(Usuario usuario) {
        Document document = new Document();
        document.put("nome", usuario.getNome());
        document.put("email", usuario.getEmail());
        document.put("username", usuario.getUsername());
        document.put("senha", usuario.getSenha());
        document.put("uuid", usuario.getUuid().toString());
        document.put(COMPARTILHADOS_COMIGO, usuario.getArquivosCompartilhados());
        return document;
    }

    /**
     *
     * @param username - username do usuario que será verificado no banco
     * @return true se já existir e false caso não exista
     */
    private boolean verificaSeUsuarioJaExiste(String username) {
        if (busca(username) != null) {
            logger.log(Level.INFO, "Usuario já cadastrado: {0}", username);
            client.close();
            return true;
        }
        return false;
    }

    public Set<BasicDBObject> listaCompartilhadosComigo(String username) {
        conecta();
        MongoCursor<Usuario> cursor = collection.find(Filters.eq(PARAM_BUSCA, username), Usuario.class).iterator();
        if (cursor.hasNext()) {
            Usuario usuario = cursor.next();
            return usuario.getArquivosCompartilhados();
        }
        return null;
    }
}
