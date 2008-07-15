package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement11 extends Statement {
  public Statement11(ImportStatement importStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ImportStatement>("importStatement", importStatement)
    }, firstToken, lastToken);
  }
  public Statement11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement11(cloneProperties(),firstToken,lastToken);
  }
  public ImportStatement getImportStatement() {
    return ((PropertyOne<ImportStatement>)getProperty("importStatement")).getValue();
  }
}
