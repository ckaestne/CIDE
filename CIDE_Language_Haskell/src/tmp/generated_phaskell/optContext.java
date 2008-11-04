package tmp.generated_phaskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class optContext extends GenASTNode {
  public optContext(context context, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<context>("context", context)
    }, firstToken, lastToken);
  }
  public optContext(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new optContext(cloneProperties(),firstToken,lastToken);
  }
  public context getContext() {
    return ((PropertyZeroOrOne<context>)getProperty("context")).getValue();
  }
}
