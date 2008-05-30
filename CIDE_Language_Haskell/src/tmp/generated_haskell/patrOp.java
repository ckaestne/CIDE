package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class patrOp extends GenASTNode {
  public patrOp(constructorOperator constructorOperator, patr patr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<constructorOperator>("constructorOperator", constructorOperator),
      new PropertyOne<patr>("patr", patr)
    }, firstToken, lastToken);
  }
  public patrOp(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new patrOp(cloneProperties(),firstToken,lastToken);
  }
  public constructorOperator getConstructorOperator() {
    return ((PropertyOne<constructorOperator>)getProperty("constructorOperator")).getValue();
  }
  public patr getPatr() {
    return ((PropertyOne<patr>)getProperty("patr")).getValue();
  }
}
