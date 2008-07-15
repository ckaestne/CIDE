package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement1 extends Statement {
  public Statement1(LabeledStatement labeledStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<LabeledStatement>("labeledStatement", labeledStatement)
    }, firstToken, lastToken);
  }
  public Statement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement1(cloneProperties(),firstToken,lastToken);
  }
  public LabeledStatement getLabeledStatement() {
    return ((PropertyOne<LabeledStatement>)getProperty("labeledStatement")).getValue();
  }
}
