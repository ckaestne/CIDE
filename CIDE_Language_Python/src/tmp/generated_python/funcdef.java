package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class funcdef extends GenASTNode {
  public funcdef(AnyName anyName, parameters parameters, suite suite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AnyName>("anyName", anyName),
      new PropertyOne<parameters>("parameters", parameters),
      new PropertyOne<suite>("suite", suite)
    }, firstToken, lastToken);
  }
  public funcdef(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new funcdef(cloneProperties(),firstToken,lastToken);
  }
  public AnyName getAnyName() {
    return ((PropertyOne<AnyName>)getProperty("anyName")).getValue();
  }
  public parameters getParameters() {
    return ((PropertyOne<parameters>)getProperty("parameters")).getValue();
  }
  public suite getSuite() {
    return ((PropertyOne<suite>)getProperty("suite")).getValue();
  }
}
