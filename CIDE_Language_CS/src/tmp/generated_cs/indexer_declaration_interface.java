package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class indexer_declaration_interface extends GenASTNode {
  public indexer_declaration_interface(indexer_base indexer_base, formal_parameter_list formal_parameter_list, accessor_declarations accessor_declarations, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<indexer_base>("indexer_base", indexer_base),
      new PropertyOne<formal_parameter_list>("formal_parameter_list", formal_parameter_list),
      new PropertyOne<accessor_declarations>("accessor_declarations", accessor_declarations)
    }, firstToken, lastToken);
  }
  public indexer_declaration_interface(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new indexer_declaration_interface(cloneProperties(),firstToken,lastToken);
  }
  public indexer_base getIndexer_base() {
    return ((PropertyOne<indexer_base>)getProperty("indexer_base")).getValue();
  }
  public formal_parameter_list getFormal_parameter_list() {
    return ((PropertyOne<formal_parameter_list>)getProperty("formal_parameter_list")).getValue();
  }
  public accessor_declarations getAccessor_declarations() {
    return ((PropertyOne<accessor_declarations>)getProperty("accessor_declarations")).getValue();
  }
}
