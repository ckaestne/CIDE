package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement5 extends Statement {
  public Statement5(LabelledStatement labelledStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LabelledStatement>("labelledStatement", labelledStatement)
    }, firstToken, lastToken);
  }
  public Statement5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement5(cloneProperties(),firstToken,lastToken);
  }
  public LabelledStatement getLabelledStatement() {
    return ((PropertyOne<LabelledStatement>)getProperty("labelledStatement")).getValue();
  }
}
