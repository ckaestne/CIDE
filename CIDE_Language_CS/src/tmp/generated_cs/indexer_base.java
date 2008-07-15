package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class indexer_base extends GenASTNode {
  public indexer_base(identifier identifier, ArrayList<indexer_baseInt> indexer_baseInt, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<identifier>("identifier", identifier),
      new PropertyZeroOrMore<indexer_baseInt>("indexer_baseInt", indexer_baseInt)
    }, firstToken, lastToken);
  }
  public indexer_base(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new indexer_base(cloneProperties(),firstToken,lastToken);
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
  public ArrayList<indexer_baseInt> getIndexer_baseInt() {
    return ((PropertyZeroOrMore<indexer_baseInt>)getProperty("indexer_baseInt")).getValue();
  }
}
