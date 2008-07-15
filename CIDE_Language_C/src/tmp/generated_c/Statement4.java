package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement4 extends Statement {
  public Statement4(SelectionStatement selectionStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SelectionStatement>("selectionStatement", selectionStatement)
    }, firstToken, lastToken);
  }
  public Statement4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement4(cloneProperties(),firstToken,lastToken);
  }
  public SelectionStatement getSelectionStatement() {
    return ((PropertyOne<SelectionStatement>)getProperty("selectionStatement")).getValue();
  }
}
