package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atomtrailerEnd4 extends atomtrailerEnd {
  public atomtrailerEnd4(ASTStringNode dot, AnyName anyName, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("dot", dot),
      new PropertyOne<AnyName>("anyName", anyName)
    }, firstToken, lastToken);
  }
  public atomtrailerEnd4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atomtrailerEnd4(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getDot() {
    return ((PropertyOne<ASTStringNode>)getProperty("dot")).getValue();
  }
  public AnyName getAnyName() {
    return ((PropertyOne<AnyName>)getProperty("anyName")).getValue();
  }
}
