package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class typeEnd2 extends typeEnd {
  public typeEnd2(indexer_declaration_no_interface indexer_declaration_no_interface, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<indexer_declaration_no_interface>("indexer_declaration_no_interface", indexer_declaration_no_interface)
    }, firstToken, lastToken);
  }
  public typeEnd2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new typeEnd2(cloneProperties(),firstToken,lastToken);
  }
  public indexer_declaration_no_interface getIndexer_declaration_no_interface() {
    return ((PropertyOne<indexer_declaration_no_interface>)getProperty("indexer_declaration_no_interface")).getValue();
  }
}
