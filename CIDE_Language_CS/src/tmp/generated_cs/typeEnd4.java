package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class typeEnd4 extends typeEnd {
  public typeEnd4(indexer_declaration_interface indexer_declaration_interface, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<indexer_declaration_interface>("indexer_declaration_interface", indexer_declaration_interface)
    }, firstToken, lastToken);
  }
  public typeEnd4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typeEnd4(cloneProperties(),firstToken,lastToken);
  }
  public indexer_declaration_interface getIndexer_declaration_interface() {
    return ((PropertyOne<indexer_declaration_interface>)getProperty("indexer_declaration_interface")).getValue();
  }
}
