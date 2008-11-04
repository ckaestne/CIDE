package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprEnd extends GenASTNode {
  public exprEnd(context context, functiontype functiontype, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<context>("context", context),
      new PropertyOne<functiontype>("functiontype", functiontype)
    }, firstToken, lastToken);
  }
  public exprEnd(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new exprEnd(cloneProperties(),firstToken,lastToken);
  }
  public context getContext() {
    return ((PropertyZeroOrOne<context>)getProperty("context")).getValue();
  }
  public functiontype getFunctiontype() {
    return ((PropertyOne<functiontype>)getProperty("functiontype")).getValue();
  }
}
